package by.shibaev.ship.state;

import by.shibaev.ship.entity.Ship;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface ProcessState {
    public static Logger logger = LogManager.getLogger();
    void doAction(Ship ship);
}
