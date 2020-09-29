package by.shibaev.ship.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Port {
    public static final Port INSTANCE = new Port();
    private static final int DOCK_NUMBER = 10;
    private static final int CAPACITY = 10;
    private List<Box> storage;
    private int busyPlace;
    private final List<Dock> docks;

    private Port(){
        storage = new ArrayList<>();
        docks = new ArrayList<>();
        for (int i = 0; i < DOCK_NUMBER; i++) {
            docks.add(new Dock());
        }
    }

    public List<Dock> getDocks() {
        return Collections.unmodifiableList(docks);
    }

    public int getBusyPlace() {
        return busyPlace;
    }

    public Optional<Box> takeBox(){
        Optional<Box> result;
        if(storage.size() > 0){
            Box box = storage.get(storage.size() - 1);
            storage.remove(box);
            busyPlace -= box.getVolume();
            result = Optional.of(box);
        }
        else {
            result = Optional.empty();
        }
        return result;
    }

    public boolean add(Box box){
        boolean complete = false;
        if(busyPlace + box.getVolume() <= CAPACITY){
            busyPlace += box.getVolume();
            storage.add(box);
            complete = true;
        }
        return complete;
    }
}
