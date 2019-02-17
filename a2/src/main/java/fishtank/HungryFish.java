package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class HungryFish extends FishTankEntity {

    /**
     * How this fish appears on the screen.
     */
    private String appearance;

    /**
     * Indicates whether this fish is moving right.
     */
    private boolean goingRight;

    /**
     * This fish's first coordinate.
     */
    private int r;
    /**
     * This fish's second coordinate.
     */
    private int c;
    /**
     * The colour of this fish.
     */
    final private Color colour;


    /**
     * Constructs a new hungry fish.
     */
    public HungryFish() {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><MEHUNGRY>";
        goingRight = true;
    }

    public boolean getGoingRight(){return goingRight;}
    /**
     * Set this item's location.
     *
     * @param a the first coordinate.
     * @param b the second coordinate.
     */
    public void setLocation(int a, int b) {
        c = a;
        r = b;
    }

    int getX() {
        return c;
    }

    int getY() {
        return r;
    }


    /**
     * Causes this fish to blow a bubble.
     */
    protected void blowBubble() {
        Bubble b = new Bubble();
        int a;
        if (r == 0) {
            a = r + 1;
            while (FishTank.getEntity(c, a) != null && a < 47) {
                a = a + 1;
            }
        } else {
            a = r - 1;
            while (FishTank.getEntity(c, a) != null && a > 1) {
                a = a - 1;
            }
        }
        b.setLocation(c, a);
        FishTank.addEntity(c, a, b);

        System.out.println("fish" + c + " " + r);
        System.out.println("bubble" + c + " " + a);


    }


    /**
     * Build and initialize this fish's forward and backward
     * appearances.
     */
    private String reverseAppearance() {
        System.out.println("Turning around" + this.appearance);
        StringBuilder reverse = new StringBuilder("");
        for (int i = appearance.length() - 1; i >= 0; i--) {
            switch (appearance.charAt(i)) {
                case ')':
                    reverse.append('(');
                    break;
                case '(':
                    reverse.append(')');
                    break;
                case '>':
                    reverse.append('<');
                    break;
                case '<':
                    reverse.append('>');
                    break;
                case '}':
                    reverse.append('{');
                    break;
                case '{':
                    reverse.append('}');
                    break;
                case '[':
                    reverse.append(']');
                    break;
                case ']':
                    reverse.append('[');
                    break;
                default:
                    reverse.append(appearance.charAt(i));
                    break;
            }
        }
        //System.out.println("Turned around" + this.appearance);
        String appearance = reverse.toString();
        return appearance;
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

    public void setGoingRight(boolean goingRight) {
        this.goingRight = goingRight;
    }

    /**
     * The font used to draw instances of this class.
     */
    final private static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


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
        g.drawString(s, y * fm.charWidth('W'), x * fm.getAscent());
    }


    /**
     * Draws this fish tank item.
     *
     * @param g the graphics context in which to draw this item.
     */
    void draw(Graphics g) {
        drawString(g, appearance, r, c);
    }


    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */

    public void find_seaweed() {
        System.out.println(seaweed_c);
        for (int i = 1; i <= 46 - r; i++) {
            FishTankEntity e = FishTank.getEntity(c, r + i);
            if (e instanceof Seaweed) {
                change_seaweed((Seaweed) e);
            }
        }
    }

    public void change_seaweed(Seaweed e) {
        if ((e.getY() - r) <= e.getLenght() && (e.getY() - r) >= 2) {
            System.out.println(c + "！！！！！！！！！changed");
            e.setLenght(e.getY() - r);

        }
    }


    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {

        double d;

        // 2.random turn around
        d = Math.random();
        if (d < 0.1) {
            turnAround();
        }
        //1. Move one spot to the right or left.// touch something than turn
        if (goingRight && c <= 103) {
            if (this.no_collision(c + 1, r)) {
                c += 1;
                change_x = 1;
            } else {
                turnAround();
            }
        } else if (!goingRight && c >= 3) {
            if (this.no_collision(c - 1, r)) {
                c -= 1;
                change_x = -1;
            } else {
                turnAround();
            }
        }


        //3.Figure out whether I turn around.
        if (goingRight && c == 104) {
            turnAround();
        } else if (!goingRight && c == 2) {
            turnAround();
        }
        // 4. Figure out whether I blow a bubble.
        d = Math.random();
        if (d < 0.1) {
            blowBubble();
        }


        // 5. Figure out whether to move up or down, or neither.
        d = Math.random();
        if (d < 0.1 && r <= 47 && this.no_collision(c, r + 1)) {
            r += 1;
            change_y = 1;
        } else if (d < 0.2 && r >= 3 && this.no_collision(c, r - 1)) {
            r -= 1;
            change_y = -1;
        }


        if (seaweed_c.contains(c)) {
            find_seaweed();
        }

    }
}
