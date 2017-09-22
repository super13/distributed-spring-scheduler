package ds.service;

import ds.model.TaskStatus;

public interface TaskStatusService {
    void addTaskStatus(TaskStatus taskStatus);

    void updateByIdTaskStatusSelective(TaskStatus taskStatus);
}
