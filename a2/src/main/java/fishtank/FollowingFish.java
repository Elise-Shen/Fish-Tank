package fishtank;

import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity {

    /**
     * How this fish appears on the screen.
     */
    private final String appearance;

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
     * The entity that our fish is following
     */

    private final Fish de;


    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
    }

    //public boolean getGoingRight(){return goingRight;}
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
        System.out.println("Turned around" + this.appearance);
        String appearance = reverse.toString();
        return appearance;
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
        g.drawString(s, x * fm.charWidth('W'), y * fm.getAscent());
    }


    /**
     * Draws this fish tank item.
     *
     * @param g the graphics context in which to draw this item.
     */
    void draw(Graphics g) {
        drawString(g, appearance, r, c);
    }

    public boolean bestMove() {
        int gx = de.getX();
        int gy = de.getY();
        boolean available = false;
        // above or below can't <=2 -->斜着走
        if (Math.abs(de.getY() - r) > 2) {
            //Right up
            if (goingRight && c - gx < 0 && r - gy > 0 && no_collision(c + 1, r - 1)) {
                c += 1;
                r -= 1;
                available = true;
            }
            // Right down
            else if (goingRight && c - gx < 0 && r - gy < 0 && no_collision(c + 1, r + 1)) {
                c += 1;
                r += 1;
                available = true;
            }
            //Left up
            else if (!goingRight && c - gx > 0 && r - gy > 0 && no_collision(c - 1, r - 1)) {
                c -= 1;
                r -= 1;
                available = true;
            }
            //Left down
            else if (!goingRight && c - gx > 0 && r - gy < 0 && no_collision(c - 1, r + 1)) {
                c -= 1;
                r += 1;
                available = true;
            }
        }
        return available;
    }


    protected void turnToFace() {
        if (de.getX() < this.getX() && goingRight) {
            goingRight = false;
            reverseAppearance();
        } else if (de.getX() > this.getX() && !goingRight) {
            goingRight = true;
            reverseAppearance();
        }
    }


    /**
     * Causes this item to take its turn in the fish-tank simulation.
     */
    public void update() {
        int d = Math.abs(de.getY() - r) + Math.abs(de.getX() - c);
        //Approach
        if (d > 2) {
            //decide direction
            turnToFace();
            boolean best = bestMove();
            if (!best) {
                // stop moving if have object at that location
                if (goingRight && c <= 103 && this.no_collision(c + 1, r)) {
                    c += 1;//move right
                } else if (!goingRight && c >= 3 && this.no_collision(c - 1, r)) {
                    c -= 1;//move left
                }

                // move up/down when dis >2
                if (Math.abs(de.getY() - r) > 2) {
                    if (de.getY() < r && r >= 3 && this.no_collision(c, r - 1)) {
                        r -= 1;//move up
                    } else if (de.getY() >= r && r <= 47 && this.no_collision(c, r + 1)) {
                        r += 1;//move down
                    }
                }
            }
            //testing

        } else if (d == 2) {

            //Diagonal: move up/down

            if (Math.abs(de.getY() - r) == 1 && Math.abs(de.getX() - c) == 1) {
                if (de.getY() < r && r >= 3 && this.no_collision(de.getX(), r + 1)) {
                    c = de.getX();
                    r += 1;//move down 远离
                } else if (de.getY() >= r && r <= 47 && this.no_collision(de.getX(), r - 1)) {
                    c = de.getX();
                    r -= 1;//move up 远离
                }
            }
            // horizontal -- diagonal --- next round be vertical
            if (Math.abs(de.getY() - r) == 0 && Math.abs(de.getX() - c) == 2) {

                if (goingRight) {

                    if (c <= 45 && r >= 3 && this.no_collision(c + 1, r - 1)) {
                        c += 1;
                        r -= 1;
                    } else if (c <= 45 && r <= 105 && this.no_collision(c + 1, r + 1)) {
                        c += 1;
                        r += 1;
                    }
                } else {
                    if (c >= 3 && r >= 3 && this.no_collision(c - 1, r - 1)) {
                        c -= 1;
                        r -= 1;
                    } else if (c >= 3 && r <= 105 && this.no_collision(c - 1, r + 1)) {
                        c -= 1;
                        r += 1;
                    }
                }


            }

        } else if (d < 2) {
            //don't move if can't
            //move up
            //System.out.println(de.change_y);
            //y:-1
            if (de.change_y == -1) {
                //顶头在内部
                if (r >= 3 && this.no_collision(c, r - 1)) {
                    r -= 1;
                }
                //left
                else if (c <= 105 && r <= 2 && !this.goingRight && this.no_collision(c + 1, r)) {
                    goingRight = true;
                    reverseAppearance();
                    c += 1;
                    goingRight = false;
                    reverseAppearance();
                } else if (c >= 3 && r <= 2 && this.goingRight && this.no_collision(c - 1, r)) {
                    goingRight = false;
                    reverseAppearance();
                    c -= 1;
                    goingRight = true;
                    reverseAppearance();
                }


            }//move down y:1
            else if (de.change_y == 1) {
                if (r <= 45 && this.no_collision(c, r + 1)) {
                    r += 1;
                } else if (r >= 46 && c <= 105 && !this.goingRight && this.no_collision(c + 1, r)) {
                    goingRight = true;
                    reverseAppearance();
                    c += 1;
                    goingRight = false;
                    reverseAppearance();
                } else if (c >= 3 && r >= 46 && this.goingRight && this.no_collision(c - 1, r)) {
                    goingRight = false;
                    reverseAppearance();
                    c -= 1;
                    goingRight = true;
                    reverseAppearance();
                }

            }//move left
            else if (de.change_x == -1 && c >= 3 && this.no_collision(c - 1, r)) {
                c -= 1;

            }//move right
            else if (de.change_x == 1 && c <= 105 && this.no_collision(c + 1, r)) {
                c += 1;
            }


        }
        System.out.println("------Fish:" + de.getX() + " " + de.getY());
        System.out.println("********following" + c + " " + r);
    }


}


