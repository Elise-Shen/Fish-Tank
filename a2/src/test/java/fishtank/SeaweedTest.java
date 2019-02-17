package fishtank;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SeaweedTest {
    private Seaweed seaweed;
    private Fish fish;
    private Seaweed seaweed2;

    @Before
    public void setUp() {
        seaweed = new Seaweed(5);
        FishTank.addEntity(30, 30, seaweed);
        System.out.println(FishTank.getEntity(30, 30) instanceof Seaweed);
        fish = new Fish();
        seaweed2 = new Seaweed(5);
        FishTank.addEntity(30, 31, seaweed2);
    }

    @Test
    public void seaweed_test_cut() {
        seaweed.setLocation(30, 30);
        boolean check = false;
        for (int i = 0; i <= 5; i++) {
            fish.setLocation(29, 29);
            fish.goingRight = true;
            fish.update();
            seaweed.update();
            System.out.println(fish.getX() + " " + fish.getY());
            System.out.println(seaweed.l);
            if (seaweed.l == 30 - fish.getY()) {
                check = true;
                break;
            }
        }
        assertTrue("length reduced", check);
    }

    @Test
    public void seaweed_test_cut_multiple() {
        seaweed.setLocation(30, 30);
        seaweed2.setLocation(30, 31);
        boolean check = false;
        for (int i = 0; i <= 5; i++) {
            fish.setLocation(29, 29);
            fish.goingRight = true;
            fish.update();
            seaweed.update();
            seaweed2.update();
            System.out.println(fish.getX() + " " + fish.getY());
            System.out.println(seaweed.l + " " + seaweed2.l);
            if (seaweed.l == 30 - fish.getY() && seaweed2.l == 31 - fish.getY()) {
                check = true;
                break;
            }
        }
        assertTrue("length reduced", check);
    }

    @Test
    public void seaweed_test_recover() {
        seaweed.l = 1;
        seaweed.count_up = 199;
        seaweed.update();
        boolean check = false;
        if (seaweed.l == 5) {
            check = true;
        }
        assertTrue("length recovered", check);

    }

}
