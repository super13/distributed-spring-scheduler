package ds.model;

public class ScheduleNode {
    private Long id;

    private String host;

    private Long lastPing;

    private Long createTime;

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

    public Long getLastPing() {
        return lastPing;
    }

    public ScheduleNode setLastPing(Long lastPing) {
        this.lastPing = lastPing;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public ScheduleNode setCreateTime(Long createTime) {
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
