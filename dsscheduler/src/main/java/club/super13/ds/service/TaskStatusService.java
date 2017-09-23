package club.super13.ds.service;

import club.super13.ds.model.TaskStatus;

public interface TaskStatusService {
    void addTaskStatus(TaskStatus taskStatus);

    void updateByIdTaskStatusSelective(TaskStatus taskStatus);
}
