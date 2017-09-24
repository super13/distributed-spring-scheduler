package club.super13.ds.service.impl;

import club.super13.ds.mapping.ScheduleNodeMapper;
import club.super13.ds.model.ScheduleNode;
import club.super13.ds.service.ScheduleNodeService;
import club.super13.ds.utils.LocalIpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Super13 on 9/23/17.
 */
@Service
@Slf4j
public class ScheduleNodeServiceImpl implements ScheduleNodeService {
    @Autowired
    private ScheduleNodeMapper scheduleNodeMapper;

    private final long INACTIVETIME=60*16*1000;//16分钟

    @Override
    public boolean isLeader() {
        boolean ret=false;

        String host=LocalIpAddressUtil.resolveLocalIp();

        ScheduleNode node = scheduleNodeMapper.selectByHost(host);
        if(node!=null&&node.getIsLeader().equals(1)) {
            ret=true;
        }
        return ret;
    }

    @Override
    public void pingNode() {
        String host= LocalIpAddressUtil.resolveLocalIp();

        ScheduleNode node = scheduleNodeMapper.selectByHost(host);
        if(node==null){
            node=new ScheduleNode();
            node.setHost(host)
                    .setIsLeader(CollectionUtils.isEmpty(scheduleNodeMapper.selectAllScheduleNodes())?1:0)
                    .setLastPing(System.currentTimeMillis());
            scheduleNodeMapper.insertSelective(node);
        }else{
            node.setLastPing(System.currentTimeMillis());
            scheduleNodeMapper.updateByPrimaryKeySelective(node);
        }
    }

    @Override
    public void checkLeaderShip() {
        final List<ScheduleNode> allNodes = scheduleNodeMapper.selectAllScheduleNodes();
        final long inactiveCase=System.currentTimeMillis()-INACTIVETIME;
        final List<ScheduleNode> activeNodes = allNodes.stream().filter(s -> s.getLastPing() > inactiveCase).collect(Collectors.toList());
        Optional<ScheduleNode> leaderNode =allNodes.stream().filter(s-> s.getIsLeader().equals(1)).findFirst();
        if(leaderNode.isPresent()&&activeNodes.contains(leaderNode.get())){
            log.info("active Leader Node:%s",leaderNode.get().getHost());
            setLeaderFlag(allNodes,0);
            leaderNode.get().setIsLeader(1);
            updateNodes(allNodes);
        }else{
            log.info("first active node become leader");
            if(activeNodes!=null&&activeNodes.size()>0){
                ScheduleNode scheduleNode=activeNodes.get(0);
                setLeaderFlag(allNodes,0);
                scheduleNode.setIsLeader(1);
                updateNodes(allNodes);
            }
        }

    }
    private void updateNodes(List<ScheduleNode> scheduleNodes){
        for(ScheduleNode scheduleNode:scheduleNodes){
            scheduleNodeMapper.updateByPrimaryKeySelective(scheduleNode);
        }
    }

    private void setLeaderFlag(final List<ScheduleNode> list, final Integer value) {
        for (ScheduleNode systemNode : list) {
            systemNode.setIsLeader(value);
        }
    }
}
