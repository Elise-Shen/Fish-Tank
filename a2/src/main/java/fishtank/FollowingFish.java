package fishtank;
import java.awt.*;

/**
 * A fish.
 */
public class FollowingFish extends FishTankEntity {

    /**
     * How this fish appears on the screen.
     */
    public String appearance;

    /**
     * Indicates whether this fish is moving right.
     */
    boolean goingRight;

    /**
     * This fish's first coordinate.
     */
    int r;
    /**
     * This fish's second coordinate.
     */
    int c;
    /**
     * The colour of this fish.
     */
    private Color colour;

    /**
     * The entity that our fish is following
     */

    Fish de;
    //  follwoing要追de，追上之后保持
    //追：首先测距离，c-de.c>2 ->得追
    // 遇到障碍绕过，方向不变

    /**
     * Constructs a new hungry fish.
     */
    public FollowingFish(Fish f) {
        colour = Color.cyan.darker().darker().darker();
        appearance = "><FOLLOW>";
        goingRight = true;
        de = f;
    }


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
        System.out.println("Turnign around" + this.appearance);
        String reverse = "";
        for (int i = appearance.length() - 1; i >= 0; i--) {
            switch (appearance.charAt(i)) {
                case ')':
                    reverse += '(';
                    break;
                case '(':
                    reverse += ')';
                    break;
                case '>':
                    reverse += '<';
                    break;
                case '<':
                    reverse += '>';
                    break;
                case '}':
                    reverse += '{';
                    break;
                case '{':
                    reverse += '}';
                    break;
                case '[':
                    reverse += ']';
                    break;
                case ']':
                    reverse += '[';
                    break;
                default:
                    reverse += appearance.charAt(i);
                    break;
            }
        }
        System.out.println("Turned around" + this.appearance);
        appearance = reverse;
        return reverse;
    }


    /**
     * Turns this fish to fc
     */


    /**
     * The font used to draw instances of this class.
     */
    static Font FONT = new Font("Monospaced", Font.PLAIN, 10);


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
        if (Math.abs(de.getY() - r)>2){
            //Rightup
            if (goingRight && c - gx < 0 && r - gy > 0 && no_collision(c + 1, r - 1)) {
                c += 1;
                r -= 1;
                available = true;
            }
            // Rightdown
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
        //followee在左边,following fish 在往右边走--following fish 掉头
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
            //决定方向
            turnToFace();
            boolean best = bestMove();
            if (!best){
                //左右有东西的时候停住
                if (goingRight && c<=103 && this.no_collision(c+1,r)) {
                    c += 1;//move left
                } else if (!goingRight && c>=3&&this.no_collision(c-1,r)) {
                    c -= 1;//move right
                }

                //和ee的差距大于2，上下移动
                if(Math.abs(de.getY() - r) > 2) {
                    if(de.getY() < r&& r>=3&&this.no_collision(c,r-1)) {
                        r -= 1;//move up
                    } else if (de.getY()>=r && r<=47 && this.no_collision(c,r+1)) {
                        r += 1;//move down
                    }
                }}
            //testing

        }

        else if (d == 2){

            //调整斜着的:同列，空一格-->变成正上方

            if(Math.abs(de.getY() - r) ==1 && Math.abs(de.getX() - c)==1) {
                if(de.getY() < r&& r>=3&&this.no_collision(de.getX(),r+1)) {
                    c=de.getX();
                    r += 1;//move down 远离
                } else if (de.getY()>=r && r<=47 && this.no_collision(de.getX(),r-1)) {
                    c=de.getX();
                    r -= 1;//move up 远离
                }
            }
            //调整横着的--变成斜着 --等下一轮变成竖着
            if(Math.abs(de.getY() - r) ==0 &&Math.abs(de.getX() - c)==2){

                if(goingRight) {

                    if (c <= 45 && r >= 3 && this.no_collision(c + 1, r - 1)) {
                        c += 1;
                        r -= 1;
                    } else if (c <= 45 && r <= 105 && this.no_collision(c + 1, r + 1)) {
                        c += 1;
                        r += 1;
                    }
                }
                else{
                        if(c>=3&&r>=3&&this.no_collision(c-1,r-1)){
                            c-=1;r-=1;}
                        else if(c>=3&&r<=105&&this.no_collision(c-1,r+1)){
                            c-=1;r+=1;}
                    }


            }

        }

        else if (d<2){
            //不能动就别动了
            //moveup
            //System.out.println(de.change_y);
            if(de.change_y==-1&& r>=3 && this.no_collision(c,r-1)){
                r-=1;

            }//move down
            else if (de.change_y==1&& r<=45 && this.no_collision(c,r+1)){
                r+=1;
            }//move left
            else if(de.change_x==-1&& r>=3 && this.no_collision(c-1,r)){
                r-=1;

            }//move right
            else if (de.change_x==1&& r<=45 && this.no_collision(c+1,r)){
                r+=1;
            }


        }
        System.out.println("------Fish:"+de.getX()+" "+de.getY());
        System.out.println("********following"+c+" "+r);
        }


    }


