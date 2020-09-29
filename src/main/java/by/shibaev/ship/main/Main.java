package by.shibaev.ship.main;

import by.shibaev.ship.entity.Box;
import by.shibaev.ship.entity.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Box> list = new ArrayList<>();
        list.add(new Box(5));
        list.add(new Box(2));
        list.add(new Box());
        Ship ship1 = new Ship(Ship.TripAim.IMPORT,list, "Mari", 5);
        list = new ArrayList<>();
        list.add(new Box(5));
        list.add(new Box(2));
        list.add(new Box(2));
        list.add(new Box(1));
        Ship ship2 = new Ship(Ship.TripAim.IMPORT,list, "Mua", 5);
        Ship ship3 = new Ship(Ship.TripAim.EXPORT, "Via", 5);
        list = new ArrayList<>();
        list.add(new Box(5));
        list.add(new Box(2));
        Ship ship4 = new Ship(Ship.TripAim.IMPORT,list, "Dark", 5);
        Thread thread1 = new Thread(ship1);
        Thread thread2 = new Thread(ship2);
        Thread thread3 = new Thread(ship3);
        Thread thread4 = new Thread(ship4);

        try {
            thread1.start();
            Thread.sleep(300);
            thread2.start();
            Thread.sleep(500);
            thread3.start();
            Thread.sleep(300);
            thread4.start();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
