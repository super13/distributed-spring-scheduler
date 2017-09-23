package club.super13.ds.service;

public interface ScheduleNodeService {
    boolean isLeader();

    void pingNode();

    void checkLeaderShip();
}
