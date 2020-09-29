package by.shibaev.ship.state.Impl;

import by.shibaev.ship.entity.Ship;
import by.shibaev.ship.service.PortService;
import by.shibaev.ship.state.ProcessState;
import org.apache.logging.log4j.Level;

public class DockRegistrationImpl implements ProcessState {
    @Override
    public void doAction(Ship ship) {
        PortService portService = PortService.INSTANCE;
        portService.arrive();
        logger.log(Level.INFO,ship.getName() + "entered dock");
        if(ship.getAim() == Ship.TripAim.EXPORT){
            ship.setState(new UploadImpl());
        }
        else{
            ship.setState(new UnloadImpl());
        }
    }
}
