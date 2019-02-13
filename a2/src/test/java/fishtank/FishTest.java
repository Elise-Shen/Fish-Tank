package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FishTest {

    /* Note: FishTest is in the package fishtank, so it has access to package
       private attributes.

       Also note: It's *vital* that you name any other test classes
       (ClassName)Test in the same directory as this one is in.
       properly capitalized -- we will be autograding your tests, so make sure
       to follow this naming convention!
     */
    private Fish fish;
    private Bubble bubble;

    @Before
    public void setUp() {
        fish = new Fish();
        bubble = new Bubble();
    }

    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
      // refactored &
        //fixed the starter code
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                false; //notice: I can edit package private attributes!
            fish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(4, 9);
            if (e instanceof Bubble) {
                bubbleMade = true;
                break;
            }
        }
        //You could also write "assert bubbleMade", but using the JUnit version
        //makes the message much nicer if it fails.
        assertTrue("No bubble is Made",bubbleMade);
    }

    @Test
    public void testFishBubbles10Percent() {
        //Note: This test currently fails, but should pass once you've
        // refactored &
        //fixed the starter code.
        int count = 0;
        for (int i = 0; i < 1000; i++) {
            fish.setLocation(5, 2);
            fish.goingRight =
                    false; //c--
            fish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(4, 1);

            if (e instanceof Bubble) {
                count ++;

            }
        }
        System.out.println(count);
        boolean runTenPercentOfTime = count>100 && count<150;
        assertTrue("Not blow a bubble at their location (after moving) about 10% of the time",runTenPercentOfTime);
    }

    @Test
    public void testGoingRight(){
        //true - true
        fish.setGoingRight(true);
        assertTrue("testGoingRightFail",fish.goingRight==true);
    }

    @Test
    public void testFishBubblesTenPercent() {

        int count = 0;
        for (int j  = 10; j < 20; j++) {
            for (int i = 0; i < 100; i++) {
                fish.setLocation(j, 5);
                fish.goingRight =
                        false;
                fish.update();
            }
            for (int z = 0; z < 100;z++) {
                FishTankEntity e = FishTank.getEntity(z, j);
                if (e instanceof Bubble) {
                    count ++;
                }
            }
        }
        boolean runTenPercentOfTime = (count>=50 && count<=150);
        assertTrue(runTenPercentOfTime);
    }

    @Test
    public void test_bubble_collision(){
        boolean check=false;
        // create 3 object
        FishTank.addEntity(10,10,new Seaweed(1));
        FishTank.addEntity(11,10,new Seaweed(1));
        FishTank.addEntity(12,10,new Seaweed(1));
        FishTank.addEntity(11,11,bubble);
        bubble.update();

        if (bubble.getX()==11&&bubble.getY()==11){
            check = true;
        }
        assertTrue("bubble not move", check);




    }

    }


