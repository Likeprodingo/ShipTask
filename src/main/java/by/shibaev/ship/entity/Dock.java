package by.shibaev.ship.entity;

public class Dock extends Entity{
    private boolean isEmpty;

    public Dock(){
        isEmpty = true;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void enterDock(){
        isEmpty = false;
    }

    public void leaveDock(){
        isEmpty = false;
    }
}
