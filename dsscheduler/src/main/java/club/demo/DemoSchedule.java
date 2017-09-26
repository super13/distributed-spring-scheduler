package club.demo;


import club.super13.ds.LeaderTaskType;
import club.super13.ds.LeaderTaskTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
@Lazy(false)
public class DemoSchedule {

    @Scheduled(cron = "0 0/1 * * * ?")
    @LeaderTaskType(type = LeaderTaskTypeEnum.LEADER_ONLY)
    public void printPerMin(){
        log.info(new Date().toString());

    }

}
