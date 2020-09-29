package by.shibaev.ship.state.Impl;

import by.shibaev.ship.entity.Ship;
import by.shibaev.ship.service.PortService;
import by.shibaev.ship.state.ProcessState;
import org.apache.logging.log4j.Level;

public class UploadImpl implements ProcessState {
    @Override
    public void doAction(Ship ship) {
        PortService port = PortService.INSTANCE;
        if (port.upload(ship)) {
            ship.setState(new LeaveImpl());
        }
        logger.log(Level.INFO,ship.getName() + " upload");
    }
}
