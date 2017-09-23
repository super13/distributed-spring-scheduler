package club.super13.ds.mapping;

import club.super13.ds.model.TaskStatus;

public interface TaskStatusMapper {
    int deleteByPrimaryKey(Long id);

    int insert(TaskStatus record);

    int insertSelective(TaskStatus record);

    TaskStatus selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TaskStatus record);

    int updateByPrimaryKeyWithBLOBs(TaskStatus record);

    int updateByPrimaryKey(TaskStatus record);
}