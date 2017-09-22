package ds.service;

public interface ScheduleNodeService {
    boolean isLeader();

    void pingNode();

    void checkLeaderShip();
}
