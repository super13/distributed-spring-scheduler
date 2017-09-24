package club.super13.ds;

import club.super13.ds.service.ScheduleNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by super13 on 8/18/17.
 */
@Component
@Lazy(false)
public class NodeCheckSchedule {

    @Autowired
    private ScheduleNodeService scheduleNodeService;


    @Scheduled(cron = "0 0/2 * * * ?")
    @LeaderTaskType(type=LeaderTaskTypeEnum.PARALLEL)
    public void pingNode() {
        scheduleNodeService.pingNode();
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    @LeaderTaskType(type = LeaderTaskTypeEnum.PARALLEL)
    public void checkLeaderShip() {
        scheduleNodeService.checkLeaderShip();
    }
}
