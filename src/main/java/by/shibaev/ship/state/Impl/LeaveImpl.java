package by.shibaev.ship.state.Impl;

import by.shibaev.ship.entity.Port;
import by.shibaev.ship.entity.Ship;
import by.shibaev.ship.service.PortService;
import by.shibaev.ship.state.ProcessState;
import org.apache.logging.log4j.Level;

public class LeaveImpl implements ProcessState {
    @Override
    public void doAction(Ship ship) {
        PortService portService = PortService.INSTANCE;
        portService.leave();
        logger.log(Level.INFO,ship.getName() + " leave dock");
        ship.setActive(false);
        Thread.currentThread().interrupt();
    }
}
