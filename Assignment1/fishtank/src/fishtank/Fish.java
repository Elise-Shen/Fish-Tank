package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class Fish {

    /** How this fish appears on the screen. */
    String appearance;

    /** Indicates whether this fish is moving right. */
    boolean goingRight;

    /** This fish's first coordinate. */
    int r;
    /** This fish's second coordinate. */
    int c;
    /** The colour of this fish. */
    Color colour;
    // Proglem 2 !!!

    int height;
    int width;

    public void setBound(int height, int width) {
        this.height = height;
        this.width = width;
    }



    /**
     * Constructs a new fish.
     */
    public Fish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><>";
        goingRight = true;
    }



    /**
     * Set this item's location.
     * @param a the first coordinate.
     * @param b  the second coordinate.
     */
    public void setLocation(int a, int b) {
        r = a;
        c = b;
    }



    /**PROBLEM 3: FISH OVERLAP BY BUBBLE
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {
        Bubble b = new Bubble();
        b.setLocation(c, r);
        System.out.println(r + " " + c);
        if (FishTank.myLittleFishies[r][c] == null) {
            FishTank.myLittleFishies[r][c] = b;
        }
    }



    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
        String reverse = "";
        for (int i=appearance.length()-1; i>=0; i--) {
            switch (appearance.charAt(i)) {
                case ')': reverse += '('; break;
                case '(': reverse += ')'; break;
                case '>': reverse += '<'; break;
                case '<': reverse += '>'; break;
                case '}': reverse += '{'; break;
                case '{': reverse += '}'; break;
                case '[': reverse += ']'; break;
                case ']': reverse += '['; break;
                default: reverse += appearance.charAt(i); break;
            }
        }

        return reverse;
    }



    /**
     * Turns this fish around, causing it to reverse direction.
     */
    protected void turnAround() {
        goingRight = !goingRight;
        if (goingRight) {
            appearance = reverseAppearance();
        } else {
            appearance = reverseAppearance();
        }
    }

    /** The font used to draw instances of this class. */
    static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


    /**
     * Draws the given string in the given graphics context at
     * at the given cursor location.
     *
     * @param  g  the graphics context in which to draw the string.
     * @param  s  the string to draw.
     * @param  x  the x-coordinate of the string's cursor location.
     * @param  y  the y-coordinate of the string's cursor location.
     */
    void drawString(Graphics g, String s, int x, int y) {
        g.setColor(colour);
        g.setFont(FONT);
        FontMetrics fm = g.getFontMetrics(FONT);
        g.drawString(s, y*fm.charWidth('W'), x*fm.getAscent());
    }



    /**
     * Draws this fish tank item.
     *
     * @param  g  the graphics context in which to draw this item.
     */
    public void draw(Graphics g) {
        drawString(g, appearance, r, c);
    }



    /** PROBLEM 2:FISH OUT OF RANGE
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void move() {
        // Figure out whether I blow a bubble.
        double d = Math.random();
        if (d < 0.1) {
            blowBubble(); }

        // Figure out whether I turn around.

        if (c >= (width /6)-3 | c <= 0) {


            turnAround();
        }

        // Figure out whether to move up or down, or neither.
        d = Math.random();
        if (d < 0.1 && r<=(height/10)-2) {

            r += 1;//goes down
        } else if (d < 0.2 && r>=4) {
            r -= 1;//goes up
        }


        // Move one spot to the right or left.
        //System.out.println("error?"+c);
        if (goingRight && c<= (width /6)-4) {

            c += 1;//right

        } else if (!goingRight && c>=1) {
            c -= 1;//left

        }
    }
}




