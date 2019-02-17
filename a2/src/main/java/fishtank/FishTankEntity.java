package fishtank;

import java.util.ArrayList;

/**
 * In Java, an "abstract class" is just a class that doesn't implement
 * some of its methods.
 * <p>
 * In CSC148, you've seen things like this before, where every method in a class
 * simply raised a NotImplementedError. Those are also called abstract classes,
 * and fulfill a similar purpose (try replacing a usage of FishTankEntity with
 * Object in FishTank.java and see if you can understand why it doesn't work.)
 */
public abstract class FishTankEntity {

    final static ArrayList<Integer> seaweed_c = new ArrayList<>();
    int change_x;
    int change_y;
    private boolean exists = true;

    abstract void update();

    abstract void setLocation(int x, int y);

    void delete() {
        exists = false;

    }

    boolean exists() {
        return this.exists;
    }

    void setExists(boolean exists_test) {
        this.exists = exists_test;
    }

    abstract int getX();

    abstract int getY();

    public boolean no_collision(int x, int y) {
        // return True if there's no collision
        boolean check = false;
        if (FishTank.getEntity(x, y) == null) {
            check = true;
        }

        return check;
    }
}
