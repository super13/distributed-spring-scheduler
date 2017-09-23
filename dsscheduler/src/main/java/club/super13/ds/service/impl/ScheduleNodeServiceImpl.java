package club.super13.ds.service.impl;

import club.super13.ds.service.ScheduleNodeService;
import org.springframework.stereotype.Service;

/**
 * Created by Super13 on 9/23/17.
 */
@Service
public class ScheduleNodeServiceImpl implements ScheduleNodeService {
    @Override
    public boolean isLeader() {
        return false;
    }

    @Override
    public void pingNode() {

    }

    @Override
    public void checkLeaderShip() {

    }
}
