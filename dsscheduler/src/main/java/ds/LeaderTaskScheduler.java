package ds;

import ds.service.ScheduleNodeService;
import ds.service.TaskStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.util.StringValueResolver;

import java.util.Date;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by super13 on 7/14/17.
 */
public class LeaderTaskScheduler extends ThreadPoolTaskScheduler implements EmbeddedValueResolverAware {



    @Autowired
    private TaskStatusService taskStatusService;

    @Autowired
    private ScheduleNodeService scheduleNodeService;

    private StringValueResolver embeddedValueResolver;





    @Override
    public ScheduledFuture<?> schedule(Runnable runnable, Trigger trigger) {
        return super.schedule(wrap(runnable,taskStatusService),trigger);

    }

    @Override
    public ScheduledFuture<?> schedule(Runnable runnable, Date date) {
        return super.schedule(wrap(runnable,taskStatusService),date);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, Date date, long l) {
        return super.scheduleAtFixedRate(wrap(runnable,taskStatusService), date, l);
    }

    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long l) {
        return super.scheduleAtFixedRate(wrap(runnable,taskStatusService), l);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, Date date, long l) {
        return super.scheduleWithFixedDelay(wrap(runnable,taskStatusService), date, l);
    }

    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long l) {
        return super.scheduleWithFixedDelay(wrap(runnable,taskStatusService), l);
    }

    private Runnable wrap(Runnable task,TaskStatusService taskStatusService) {
        return new LeaderTaskRunnable(task,taskStatusService,scheduleNodeService,embeddedValueResolver);
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver stringValueResolver) {
        this.embeddedValueResolver=stringValueResolver;
    }
}
