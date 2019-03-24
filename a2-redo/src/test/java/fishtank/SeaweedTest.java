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
        TestUtilities.clearWholeFishtank();
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
            fish.setGoingRight(true);
            fish.update();
            seaweed.update();
            System.out.println(fish.getX() + " " + fish.getY());
            System.out.println(seaweed.getLenght());
            if (seaweed.getLenght() == 30 - fish.getY()) {
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
            fish.setGoingRight(true);
            fish.update();
            seaweed.update();
            seaweed2.update();
            System.out.println(fish.getX() + " " + fish.getY());
            System.out.println(seaweed.getLenght() + " " + seaweed2.getLenght());
            if (seaweed.getLenght() == 30 - fish.getY() && seaweed2.getLenght() == 31 - fish.getY()) {
                check = true;
                break;
            }
        }
        assertTrue("length reduced", check);
    }

    @Test
    public void seaweed_test_recover() {
        seaweed.setLenght(1);
        seaweed.setCountup(199);
        seaweed.update();
        boolean check = false;
        if (seaweed.getLenght() == 5) {
            check = true;
        }
        assertTrue("length recovered", check);

    }

}
