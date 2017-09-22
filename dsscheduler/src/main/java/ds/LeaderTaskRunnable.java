package ds;


import ds.constants.TaskStatusConstant;
import ds.model.TaskStatus;
import ds.service.ScheduleNodeService;
import ds.service.TaskStatusService;
import ds.utils.LocalIpAddressUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.support.ScheduledMethodRunnable;
import org.springframework.util.StringUtils;
import org.springframework.util.StringValueResolver;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by super13 on 7/17/17.
 */

@Slf4j
public class LeaderTaskRunnable implements Runnable{


    private ScheduledMethodRunnable task;

    private StringValueResolver embeddedValueResolver;

    private String[] leaderDefaultTasks = {"pingNode", "checkLeaderShip", "pkRandScan"};

    private Set<String> ignoreRecordsTasksSet = new TreeSet<>(Arrays.asList(leaderDefaultTasks));


    private TaskStatusService taskStatusService;

    private ScheduleNodeService scheduleNodeService;



    public LeaderTaskRunnable(Runnable task, TaskStatusService taskStatusService, ScheduleNodeService scheduleNodeService, StringValueResolver embeddedValueResolver) {
        this.task = (ScheduledMethodRunnable) task;
        this.taskStatusService = taskStatusService;
        this.scheduleNodeService = scheduleNodeService;
        this.embeddedValueResolver=embeddedValueResolver;
    }

    @Override
    public void run() {
        long t = System.currentTimeMillis();
        String host = LocalIpAddressUtil.resolveLocalIp();
        String methodName = task.getMethod().getName();
        LeaderTaskType annotation = task.getMethod().getDeclaredAnnotation(LeaderTaskType.class);
        if (Objects.isNull(annotation)) {
            log.error("定时任务：" + methodName + "，必须要有@LeaderTaskType注解才能执行定时任务");
            System.out.println("ERROR:定时任务：" + methodName + ",必须要有@LeaderTaskType注解才能执行定时任务");
            return;
        }
        String specificHost=annotation.specificHost();
        if (this.embeddedValueResolver != null) {
            specificHost = this.embeddedValueResolver.resolveStringValue(specificHost);
        }
        if(!StringUtils.isEmpty(specificHost)){//指定host执行，优先级最高
            if(!host.equals(specificHost.trim())){//非指定机子，不执行
                if (!ignoreRecordsTasksSet.contains(methodName)) {
                    log.info("本机非指定host:{},本机host:{},不执行该任务:{}" ,specificHost,host, methodName);
                }
                return ;
            }
        }else {
            if (annotation.type().equals(LeaderTaskTypeEnum.LEADER_ONLY)) {
                if (!scheduleNodeService.isLeader()) {
                    if (!ignoreRecordsTasksSet.contains(methodName)) {
                        log.info("本机非leader(参考p_schedule_nodes表),不执行该任务:" + methodName);
                    }
                    return;
                }
            }
        }
        TaskStatus taskStatus = new TaskStatus()
                .setStatus(TaskStatusConstant.RUNNING.getStatus())
                .setStatusMsg(TaskStatusConstant.RUNNING.getStatusMsg())
                .setHost(host)
                .setStartTime(System.currentTimeMillis())
                .setEndTime(1000L)//随便之前的时间
                .setTaskId(methodName);
        if (!ignoreRecordsTasksSet.contains(methodName)) {
            log.debug("task %s begin.", methodName);
            taskStatusService.addTaskStatus(taskStatus);
        }
        String eMsg = "";
        try {
            task.run();
        } catch (Exception e) {
            eMsg = e.getMessage();
            log.error("定时任务执行发生异常", e);
        }


        long cost = System.currentTimeMillis() - t;
        if (!ignoreRecordsTasksSet.contains(methodName)) {
            log.debug("task %s end.cost:%d ms", methodName, cost);
            taskStatus.setTimeSpends(cost)
                    .setEndTime(System.currentTimeMillis())
                    .setStatus(TaskStatusConstant.ENDED.getStatus())
                    .setStatusMsg(TaskStatusConstant.ENDED.getStatusMsg());
            if (!StringUtils.isEmpty(eMsg)) {
                eMsg = eMsg.substring(0, eMsg.length() > 200 ? 200 : eMsg.length());
                taskStatus.setStatusMsg(eMsg).setStatus(TaskStatusConstant.ERROR.getStatus());
            }
            taskStatusService.updateByIdTaskStatusSelective(taskStatus);
        }

    }

}
