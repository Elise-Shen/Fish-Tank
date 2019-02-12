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

    @Before
    public void setUp() {
        fish = new Fish();
    }

    @Test
    public void testFishBubbles() {
        //Note: This test currently fails, but should pass once you've
      // refactored &
        //fixed the starter code.
        boolean bubbleMade = false;
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                false; //notice: I can edit package private attributes!
            fish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(9, 5);
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
        for (int i = 0; i < 200; i++) {
            fish.setLocation(5, 10);
            fish.goingRight =
                    false; //notice: I can edit package private attributes!
            fish.update();
            //fish should move one tile left and eventually blow a bubble.
            FishTankEntity e = FishTank.getEntity(9, 5);
            if (e instanceof Bubble) {
                count ++;
            }
        }
        boolean runTenPercentOfTime = count<=10 && count>=30;
        assertTrue("Not blow a bubble at their location (after moving) about 10% of the time",runTenPercentOfTime);
    }
    @Test
    public void testGoingRight(){
        //true - true
        fish.setGoingRight(true);
        assertTrue("testGoingRightFail",fish.goingRight==true);
    }

    @Test
    public void testMoveUp(){
        int count=0;
        for(int i=0; i<200;i++){
            fish.setLocation(5,10);
            fish.update();



        }

    }
}

