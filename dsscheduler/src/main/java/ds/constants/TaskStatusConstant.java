package ds.constants;

/**
 * Created by super13 on 7/26/17.
 */
public enum TaskStatusConstant {
    RUNNING(1,"Running"),
    ENDED(2,"Ended"),
    ERROR(-1,"Error")
    ;
    TaskStatusConstant(int status,String statusMsg){
        setStatus(status);
        setStatusMsg(statusMsg);
    }
    private int status;
    private String statusMsg;


    public int getStatus() {
        return status;
    }

    public TaskStatusConstant setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public TaskStatusConstant setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
        return this;
    }
}
