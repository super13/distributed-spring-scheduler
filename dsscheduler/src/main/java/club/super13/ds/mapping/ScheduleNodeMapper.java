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

    @Select("select * from ds_schedule_nodes where host=#{host}")
    ScheduleNode selectByHost(String host);

    @Select("select * from ds_schedule_nodes")
    List<ScheduleNode> selectAllScheduleNodes();
}