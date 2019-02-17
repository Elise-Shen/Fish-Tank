package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

//collision, rate, destroy
public class BubbleTest {
    private Bubble bubble;

    @Before
    public void setUp() {
        bubble = new Bubble();
    }

    @Test
    public void bubble_collision() {
        //to make bubble move: d meet,no collision
        boolean check = false;
        // create 3 object
        FishTank.addEntity(10, 10, new Seaweed(1));
        FishTank.addEntity(11, 10, new Seaweed(1));
        FishTank.addEntity(12, 10, new Seaweed(1));
        FishTank.addEntity(11, 11, bubble);
        bubble.update();

        if (bubble.getX() == 11 && bubble.getY() == 11) {
            check = true;
        }
        assertTrue("bubble_collision: bubble not move", check);
    }

    @Test
    public void bubble_floatUp_rate() {
        int count = 0;
        for (int i = 0; i <= 1000; i++) {
            bubble.setLocation(20, 10);
            bubble.update();
            if (bubble.getY() == 9 && bubble.getX() == 20) {
                count++;
            }
        }
        System.out.println("num" + count);
        //28-38
        boolean check = (280 <= count && count <= 380);
        assertTrue("bubble_floatUp_rate", check);
    }

    @Test
    public void bubble_floatUpRight_rate() {
        int count = 0;
        for (int i = 0; i <= 1000; i++) {
            bubble.setLocation(20, 10);
            bubble.update();
            if (bubble.getY() == 9 && bubble.getX() == 21) {
                count++;
            }
        }
        System.out.println("num" + count);
        //28-38
        boolean check = (280 <= count && count <= 380);
        assertTrue("bubble_floatUpRight_rate", check);
    }

    @Test
    public void bubble_floatUpLeft_rate() {
        int count = 0;
        for (int i = 0; i <= 1000; i++) {
            bubble.setLocation(20, 10);
            bubble.update();
            if (bubble.getY() == 9 && bubble.getX() == 19) {
                count++;
            }
        }
        System.out.println("num" + count);
        //28-38
        boolean check = (280 <= count && count <= 380);
        assertTrue("bubble_floatUpLeft_rate", check);
    }

    @Test
    public void bubble_destroy_y() {
        FishTank.addEntity(11, 3, new Seaweed(1));
        FishTank.addEntity(9, 3, new Seaweed(1));
        int count = 0;
        for (int i = 0; i <= 1000; i++) {
            bubble.setLocation(10, 4);
            bubble.update();
            if (!bubble.exists()) {
                count++;
            }
            bubble.setExists(true);
        }
        System.out.println(count);
        boolean check = (280 <= count && count <= 380);
        assertTrue("bubble_destroy_y", check);
    }

    @Test
    public void bubble_destroy_xLeft() {
        //可能会不动

        FishTank.addEntity(2, 9, new Seaweed(1));
        FishTank.addEntity(3, 9, new Seaweed(1));
        int count = 0;
        for (int i = 0; i <= 1000; i++) {
            bubble.setLocation(2, 10);
            bubble.update();
            //System.out.println(bubble.x+""+bubble.y);
            if (!bubble.exists()) {
                count++;
            }
            bubble.setExists(true);
        }
        System.out.println(count);
        boolean check = (280 <= count && count <= 380);

        assertTrue("bubble_destroy_xLeft", check);
    }

    @Test
    public void bubble_destroy_xRight() {
        FishTank.addEntity(104, 9, new Seaweed(1));
        FishTank.addEntity(103, 9, new Seaweed(1));
        int count = 0;
        for (int i = 0; i <= 1000; i++) {
            bubble.setLocation(104, 10);
            bubble.update();
            if (!bubble.exists()) {
                count++;
            }
            bubble.setExists(true);
        }
        System.out.println(count);
        boolean check = (280 <= count && count <= 380);

        assertTrue("bubble_destroy_xLeft", check);
    }


}
