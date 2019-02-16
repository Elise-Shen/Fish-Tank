package fishtank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FollowingFishTest {
  private Fish followee;

  private FollowingFish follower;

  @Before
  public void setUp() {
    followee = mock(Fish.class);
    when(followee.getX()).thenReturn(30);
    when(followee.getY()).thenReturn(30);

    follower = new FollowingFish(followee);
    //follower.setLocation(6, 11);

  }

  @Test
  public void fromUpA() {
    //it should take exactly 15 updates to get from
    //(30,3) to (30,28)
    follower.setLocation(30, 3);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromDownA() {
    //it should take exactly 15 updates to get from
    //(30,40) to (20, 28)
    follower.setLocation(30, 40);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromLeftA() {
    //it should take exactly 15 updates to get from
    //(20, 30) to (30, 32)
    follower.setLocation(20, 30);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromRightA() {
    //it should take exactly 15 updates to get from
    //(39, 30) to (30, 28)
    follower.setLocation(39, 30);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromUP_LeftA() {
    //it should take exactly 15 updates to get from
    //(20, 20) to (30, 28)
    follower.setLocation(20, 20);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromUP_RightA() {
    //it should take exactly 15 updates to get from
    //(20, 40) to (30, 32)
    follower.setLocation(20, 40);
    System.out.println(follower.c + " " + follower.r);

    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromDown_LeftA() {
    //it should take exactly 15 updates to get from
    //(20, 40) to (30, 32)
    follower.setLocation(20, 40);
    System.out.println(follower.c + " " + follower.r);

    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromDown_RightA() {
    //it should take exactly 15 updates to get from
    //(40, 40) to (30, 28)
    follower.setLocation(40, 40);
    System.out.println(follower.c + " " + follower.r);

    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  //B
  @Test
  public void fromUP_LeftB() {
    //it should take exactly 15 updates to get from
    //(29,29) to ()
    follower.setLocation(29, 29);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromUP_RightB() {
    //it should take exactly 15 updates to get from
    //(31, 29) to (30, 32)
    follower.setLocation(31, 31);
    System.out.println(follower.c + " " + follower.r);

    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromDown_LeftB() {
    //it should take exactly 15 updates to get from
    //(29, 31) to (30, 32)
    follower.setLocation(29, 31);
    System.out.println(follower.c + " " + follower.r);

    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromDown_RightB() {
    //it should take exactly 15 updates to get from
    //(31, 31) to (30, 28)
    follower.setLocation(31, 31);
    System.out.println(follower.c + " " + follower.r);

    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  //C
  @Test
  public void fromUpC() {
    //it should take exactly 15 updates to get from
    //(30,29)
    followee.change_y=-1;
    follower.setLocation(30, 29);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromDownC() {
    //it should take exactly 15 updates to get from
    //(30,31)
    followee.change_y=1;
    follower.setLocation(30, 31);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromLeftC() {
    //it should take exactly 15 updates to get from
    //(29,30)
    followee.change_x=-1;
    follower.setLocation(29, 30);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }

  @Test
  public void fromRightC() {
    //it should take exactly 15 updates to get from
    //(31, 30) to (30, 28)
    followee.change_x=1;
    follower.setLocation(31, 30);
    System.out.println(follower.c + " " + follower.r);
    for (int i = 0; i < 100; i++) {
      follower.update();
    }
    int vertDist = Math.abs(follower.getY() - followee.getY());
    int horizDist = Math.abs(follower.getX() - followee.getX());
    //Follower should be exactly 2 units below followee.
    assertEquals(2, vertDist);
    assertEquals(0, horizDist);
  }
}
