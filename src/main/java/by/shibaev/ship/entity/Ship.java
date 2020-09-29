package by.shibaev.ship.entity;

import by.shibaev.ship.state.Impl.ArriveImpl;
import by.shibaev.ship.state.ProcessState;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Ship extends Entity implements Runnable {

    public enum TripAim {
        IMPORT,
        EXPORT
    }
    private boolean isActive = true;
    private TripAim aim;
    private ProcessState state;
    private List<Box> boxes;
    private String name;
    private int capacity;

    public Ship(TripAim aim, List<Box> boxes, String name, int capacity) {
        this.aim = aim;
        this.state = new ArriveImpl();
        this.boxes = boxes;
        this.name = name;
        this.capacity = capacity;
    }

    public Ship(TripAim aim, String name, int capacity) {
        this.boxes = new ArrayList<>();
        this.aim = aim;
        this.state = new ArriveImpl();
        this.name = name;
        this.capacity = capacity;
    }

    public TripAim getAim() {
        return aim;
    }

    public void setAim(TripAim aim) {
        this.aim = aim;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public ProcessState getState() {
        return state;
    }

    public void setState(ProcessState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public boolean addBox(Box box) {
        boolean isAdded = false;
        if (boxes.size() < capacity) {
            boxes.add(box);
            isAdded = true;
        }
        return isAdded;
    }

    public Optional<Box> takeBox() {
        Optional<Box> result;
        if (boxes.size() > 0) {
            Box box = boxes.get(boxes.size() - 1);
            result = Optional.of(box);
            boxes.remove(box);
        } else {
            result = Optional.empty();
        }
        return result;
    }

    public void doAction() {
        state.doAction(this);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            doAction();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (capacity != ship.capacity) return false;
        if (state != null ? !state.equals(ship.state) : ship.state != null) return false;
        if (boxes != null ? !boxes.equals(ship.boxes) : ship.boxes != null) return false;
        return name != null ? name.equals(ship.name) : ship.name == null;
    }

    @Override
    public int hashCode() {
        int result = state != null ? state.hashCode() : 0;
        result = 31 * result + (boxes != null ? boxes.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ship{");
        sb.append("state=").append(state);
        sb.append(", boxes=").append(boxes);
        sb.append(", name='").append(name).append('\'');
        sb.append(", capacity=").append(capacity);
        sb.append('}');
        return sb.toString();
    }
}
