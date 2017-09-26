package club.super13.ds.mapping;

import club.super13.ds.model.ScheduleNode;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScheduleNodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ScheduleNode record);

    int insertSelective(ScheduleNode record);

    ScheduleNode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ScheduleNode record);

    int updateByPrimaryKey(ScheduleNode record);

    ScheduleNode selectByHost(String host);

    List<ScheduleNode> selectAllScheduleNodes();
}