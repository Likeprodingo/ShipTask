package by.shibaev.ship.state.Impl;

import by.shibaev.ship.entity.Ship;
import by.shibaev.ship.state.ProcessState;
import org.apache.logging.log4j.Level;

public class ArriveImpl implements ProcessState {
    @Override
    public void doAction(Ship ship) {
        logger.log(Level.INFO, ship.getName() + " arrived");
        ship.setState(new DockRegistrationImpl());
    }
}
