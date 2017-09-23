package club.super13.ds;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;

/**
 * Created by super13 on 7/17/17.
 */

@Configuration
public class ScheduleTaskConfiguration {
    @Bean
    public TaskScheduler myScheduler() {
        LeaderTaskScheduler scheduler = new LeaderTaskScheduler();
        scheduler.setPoolSize(10);
        return scheduler;
    }

}
