package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FishTest {

    /* Note: FishTest is in the package fish tank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be auto grading your tests, so make sure
       to follow this naming convention!
     */
    //blow bubble, turn around, 10 percent
    private Fish fish;


    @Before
    public void setUp() {
        TestUtilities.clearWholeFishtank();
        fish = new Fish();
    }

    //1 turn around
    @Test
    public void turnAroundRight() {
        fish.setGoingRight(false);
        fish.turnAround();
        assertTrue("no change from left to right", fish.getGoingRight());
    }

    @Test
    public void turnAroundLeft() {
        fish.setGoingRight(true);
        fish.turnAround();
        assertTrue("no change from left to right", !fish.getGoingRight());
    }

    //2 going right
    @Test
    public void testGoingRightTrue() {
        //true - true
        fish.setGoingRight(true);
        assertTrue("testGoingRightFail", fish.getGoingRight());
    }

    @Test
    public void testGoingRightTrueFalse() {
        //true - true
        fish.setGoingRight(false);
        assertTrue("testGoingRightFail", !fish.getGoingRight());
    }

    @Test
    //3/1 blow bubble
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
        // refactored &
        //fixed the starter code
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.setGoingRight(false);//notice: I can edit package private attributes!
            fish.update();
            System.out.println(fish.getX() + " " + fish.getY());
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(4, 9);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue("No bubble is Made", bubbleMade);
    }

    //3/2 blow bubble 10 percent
    @Test
    public void testFishBubblesTenPercent() {

        int count = 0;
        for (int j = 10; j < 35; j++) {
            for (int a = 2; a < 40; a++) {
                fish.setLocation(j, a);
                fish.setGoingRight(false);
                fish.update();
            }
            for (int z = 2; z < 40; z++) {
                FishTankEntity e = FishTank.getEntity(j - 1, z);
                if (e instanceof Bubble) {
                    count++;
                }
            }
        }
        System.out.println(count);

        boolean runTenPercentOfTime = (count >= 44 && count <= 133);
        assertTrue(runTenPercentOfTime);
    }

    //4. collision
    @Test
    public void testCollisionRight() {
        Seaweed seaweed = new Seaweed(3);
        seaweed.setLocation(30, 30);
        boolean check = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(29, 30);
            fish.setGoingRight(true);
            fish.update();
            if (!fish.getGoingRight()) {
                check = true;
                break;
            }
        }
        assertTrue("turn around when right has obj", check);
    }

    @Test
    public void testCollisionUp() {
        Seaweed seaweed = new Seaweed(3);
        seaweed.setLocation(28, 28);
        Seaweed seaweed2 = new Seaweed(3);
        seaweed2.setLocation(29, 28);
        Seaweed seaweed3 = new Seaweed(3);
        seaweed3.setLocation(28, 30);
        Seaweed seaweed4 = new Seaweed(3);
        seaweed4.setLocation(29, 30);
        boolean check = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(28, 29);
            fish.setGoingRight(true);
            fish.update();
            System.out.println(fish.getX() + " " + fish.getY());
            if (fish.getX() == 29 && fish.getY() == 29) {
                check = true;
                break;
            }
        }
        assertTrue("don't move up down when has obj", check);
    }

    @Test
    public void testBoundaryRight() {
        Seaweed seaweed = new Seaweed(1);
        seaweed.setLocation(2, 3);
        Seaweed seaweed2 = new Seaweed(1);
        seaweed2.setLocation(3, 3);
        Seaweed seaweed3 = new Seaweed(1);
        seaweed3.setLocation(4, 3);
        boolean check = false;
        for (int i = 0; i < 200; i++) {

            fish.setLocation(2, 2);
            fish.setGoingRight(false);
            fish.update();
            //System.out.println(fish.getX()+" "+fish.r);
            if (fish.getGoingRight()) {
                check = true;
                break;
            }
        }
        assertTrue("turn around when right has obj", check);
    }
    //collision at boundary


}




