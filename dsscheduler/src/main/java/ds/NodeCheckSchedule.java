package ds;

import ds.service.ScheduleNodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@PropertySource("classpath:scheduled.properties")
public class NodeCheckSchedule {

    @Autowired
    private ScheduleNodeService scheduleNodeService;


    @Scheduled(cron = "0 0/5 * * * ?")
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
