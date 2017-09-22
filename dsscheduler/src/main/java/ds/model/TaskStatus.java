package ds.model;
public class TaskStatus {
    private Long id;

    private String taskId;

    private String host;

    private Integer status;

    private String statusMsg;

    private Long startTime;

    private Long endTime;

    private Long timeSpends;

    private String param;


    public Long getId() {
        return id;
    }

    public TaskStatus setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTaskId() {
        return taskId;
    }

    public TaskStatus setTaskId(String taskId) {
        this.taskId = taskId;
        return this;
    }

    public String getHost() {
        return host;
    }

    public TaskStatus setHost(String host) {
        this.host = host;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public TaskStatus setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public TaskStatus setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
        return this;
    }

    public Long getStartTime() {
        return startTime;
    }

    public TaskStatus setStartTime(Long startTime) {
        this.startTime = startTime;
        return this;
    }

    public Long getEndTime() {
        return endTime;
    }

    public TaskStatus setEndTime(Long endTime) {
        this.endTime = endTime;
        return this;
    }

    public Long getTimeSpends() {
        return timeSpends;
    }

    public TaskStatus setTimeSpends(Long timeSpends) {
        this.timeSpends = timeSpends;
        return this;
    }

    public String getParam() {
        return param;
    }

    public TaskStatus setParam(String param) {
        this.param = param;
        return this;
    }
}
