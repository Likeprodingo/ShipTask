package by.shibaev.ship.service;

import by.shibaev.ship.entity.Box;
import by.shibaev.ship.entity.Dock;
import by.shibaev.ship.entity.Port;
import by.shibaev.ship.entity.Ship;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PortService {
    public static final PortService INSTANCE = new PortService();
    private static final Logger logger = LogManager.getLogger();
    private static final Port port = Port.INSTANCE;
    private final Lock arriveLock = new ReentrantLock();
    private final Lock leaveLock = new ReentrantLock();
    private final Lock processLock = new ReentrantLock();

    private PortService() {
    }

    public void arrive() {
        boolean isArrived = false;
        arriveLock.lock();
        while (!isArrived) {
            for (Dock dock : port.getDocks()) {
                if (dock.isEmpty()) {
                    dock.enterDock();
                    isArrived = true;
                    break;
                }
            }
        }
        arriveLock.unlock();
    }

    public void leave() {
        leaveLock.lock();
        for (Dock dock : port.getDocks()) {
            if (!dock.isEmpty()) {
                dock.leaveDock();
                break;
            }
        }
        leaveLock.unlock();
    }

    public boolean unload(Ship ship) {
        Optional<Box> box;
        while (true) {
            try {
                processLock.lock();
                box = ship.takeBox();
                if (box.isEmpty()) {
                    break;
                }
                if (port.add(box.get())) {
                    logger.log(Level.INFO, ship.getName() + " put " +
                            box.get());
                    TimeUnit.SECONDS.sleep(1);
                } else {
                    ship.addBox(box.get());
                }
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Unload exception ", e);
            }
            processLock.unlock();
        }
        return true;
    }

    public boolean upload(Ship ship) {
        while (true) {
            try {
                processLock.tryLock(100, TimeUnit.SECONDS);
                Optional<Box> box = port.takeBox();
                if (box.isEmpty()) {
                    break;
                }
                if (!ship.addBox(box.get())) {
                    while (true) {
                        if (port.add(box.get())) {
                            break;
                        }
                    }
                    break;
                } else {
                    logger.log(Level.INFO, ship.getName() + " take " +
                            box.get());
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Upload exception ", e);
            }
            processLock.unlock();
        }
        return true;
    }
}
