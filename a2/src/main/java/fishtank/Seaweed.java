package fishtank;

import java.awt.*;

/**
 * Seaweed.
 */
public class Seaweed extends FishTankEntity {
    /**
     * The font used to draw instances of this class.
     */
    final private static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


    /**
     * The number of weed segments.
     */
    private int l;

    /**
     * Indicates whether the bottom segment is leaning right.
     */
    private boolean leanRight;

    /**
     * My colour. Ah,the vagaries of British vs. US spelling.
     */
    private final Color colour;

    /**
     * This bubble's first coordinate.
     */
    private int my_curr_row;
    /**
     * This bubble's second coordinate.
     */
    private int my_curr_col;

    private final int l_original;
    //int l_original=15;
    private int count_up = 0;


    /**
     * Constructs a new seaweed item at the specified cursor
     * location (x,y),l segments tall.
     *
     * @param my_curr_col the x coordinate of the bubble's cursor location.
     * @param my_curr_row the y coordinate of the bubble's cursor location.
     * @param l           the number of segments this seaweed is tall.
     */
    public Seaweed(int l) {
        this.l = l;
        this.l_original = this.l;
        colour = Color.green.darker().darker();
    }

    public int get_loriginal(){return l_original;}
    public int getLenght() {
        return l;
    }
    public int getCount_up(){return count_up;}
    public void setLenght(int len){
        this.l = len;
    }
    public void setCountup(int c){count_up = c;}


    /**
     * Draws this fish tank item.  Looks lovely waving in the current, doesn't
     * it?
     *
     * @param g the graphics context in which to draw this item.
     */
    public void draw(Graphics g) {

        // WWhich way does the first segment lean?
        boolean lR = leanRight;
        // error 1
        for (int i = 0; i < l; i++) {// Draw a "/" seaweed segment: even numbered and leaning to the right.
            if (i % 2 == 0)
                if (lR) {
                    //System.out.println("x1");
                    // Draw the string
                    drawString(g, "/", my_curr_row, (-i + my_curr_col));
                }
            if (i % 2 == 1) { // to make a point about comparing to true or false.
                if (!lR) {
                    //System.out.println("x4");
                    // Draw the string for the last kind of leaning of the segment at location  my_curr_row,(-i+my_curr_col)
                    drawString(g, "/", my_curr_row, (-i + my_curr_col));
                }
            }
            if (i % 2 == 1) // Draw a "/" seaweed segment: odd numbered and leaning to the right.
                if (lR) {
                    //System.out.println("x2");
                    // Draw the string
                    drawString(g, "\\", my_curr_row, (-i + my_curr_col));
                }
            if (i % 2 == 0) // Draw a "/" seaweed segment: even numbered and leaning to the left.
                if (!lR) {
                    //System.out.println("x3");
                    // Draw the string
                    drawString(g, "\\", my_curr_row, (-i + my_curr_col));
                }

        }
    }

    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param g the graphics context in which to draw the string.
     * @param s the string to draw.
     * @param x the x-coordinate of the string's cursor location.
     * @param y the y-coordinate of the string's cursor location.
     */
    void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, x * fm.charWidth('W'), y * fm.getAscent());
    }


    /**
     * Set this item's location.
     *
     * @param a the first coordinate.
     * @param b the second coordinate.
     */
    public void setLocation(int a, int b) {
        this.my_curr_col = a;
        this.my_curr_row = b;
        seaweed_c.add(a);
    }

    @Override
    int getX() {
        return my_curr_col;
    }

    @Override
    int getY() {
        return my_curr_row;
    }

//  public void reduce_length(){
//    for (int i=1;i<=l;i++){
//
//      FishTankEntity e = FishTank.getEntity(my_curr_col,my_curr_row-i);
//      if (e==null){
//        System.out.println("---");}
//      if (e instanceof Fish || e instanceof HungryFish){
//        System.out.println("!!crash seaweed");
//        l = i;
//        break;
//
//      }
//    }
//  }

    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {

        count_up++;
        //reduce_length();
        //System.out.println("------------------"+count_up);

        leanRight = !leanRight;

        //go back to original length
        if (count_up == 200) {
            l = l_original;
            count_up = 0;
        }
    }


}
