package club.super13.ds.model;

import java.util.Date;

public class ScheduleNode {
    private Long id;

    private String host;

    private Date lastPing;

    private Date createTime;

    private Integer isLeader;


    public Long getId() {
        return id;
    }

    public ScheduleNode setId(Long id) {
        this.id = id;
        return this;
    }

    public String getHost() {
        return host;
    }

    public ScheduleNode setHost(String host) {
        this.host = host;
        return this;
    }

    public Date getLastPing() {
        return lastPing;
    }

    public ScheduleNode setLastPing(Date lastPing) {
        this.lastPing = lastPing;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ScheduleNode setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getIsLeader() {
        return isLeader;
    }

    public ScheduleNode setIsLeader(Integer isLeader) {
        this.isLeader = isLeader;
        return this;
    }
}
