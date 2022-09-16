import xxsurajbxx.game.Entity;
import xxsurajbxx.game.SpriteSheet;
import xxsurajbxx.game.SpriteAnimations;import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;import java.lang.Math;
public class Player extends Entity {
    public static final int KUNAI=49;
    public static final int STANCE=1;
    public static final int WALKING=2;
    public static final int SPRINTING=3;
    public static final int CROUCHING=4;
    public static final int JUMPING=5;
    public static final int FALLING=6;
    public static final int F_LIGHT=7;
    public static final int D_LIGHT=8;
    public static final int LIGHT=9;
    public static final int R_LIGHT=10;
    public static final int AIR_SPECIAL=11;
    public static final int D_SPECIAL=12;
    public static final int SPECIAL=13;    private static final double fourtyFiveInRads = 45*Math.PI/180;
    private static final double hypotenuseAngle = Math.atan(11/24)+fourtyFiveInRads;
    private static final double hypotenuseLength = Math.sqrt(697);
    public SpriteSheet.Sprite dmgFrames[];
    public byte dmgAnimations[];
    public boolean crouched;
    public byte orientation;
    public int numofThrowables;
    private long AS_timer;
    private long DS_timer;
    private long S_timer;
    private double throwableInfo[][];
    private byte animation;
    private SpriteAnimations SA;
    private SpriteSheet SS;
    public Player(String name, double health, int x, int y, int width, int height, int hitBoxWidth, int hitBoxHeight) {
        super(health, x, y, width, height, hitBoxWidth, hitBoxHeight);        throwableInfo = new double[10][4];
        numofThrowables = 0;        AS_timer=0;
        S_timer=0;
        DS_timer=0;        switch(name) {            case "naruto":
            SS = new SpriteSheet("NarutoSprite.PNG", 63);
            
            //standing
            SS.addSprite(0, 0, 43, 58, 0.125);
            SS.addSprite(43, 0, 43, 56, 0.125);
            SS.addSprite(86, 0, 43, 55, 0.125);
            SS.addSprite(129, 0, 43, 55, 0.125);
            SS.addSprite(172, 0, 43, 56, 0.125);
            SS.addSprite(215, 0, 43, 57, 0.125);
            //walking
            SS.addSprite(258, 0, 23, 60, 0.125);
            SS.addSprite(281, 0, 38, 59, 0.125);
            SS.addSprite(319, 0, 36, 58, 0.125);
            SS.addSprite(355, 0, 23, 59, 0.125);
            SS.addSprite(378, 0, 36, 59, 0.125);
            SS.addSprite(414, 0, 31, 59, 0.125);
            //sprinting
            SS.addSprite(445, 0, 44, 48, 0.125);
            SS.addSprite(489, 0, 58, 43, 0.125);
            SS.addSprite(547, 0, 50, 48, 0.125);
            SS.addSprite(597, 0, 41, 46, 0.125);
            SS.addSprite(638, 0, 55, 45, 0.125);
            SS.addSprite(693, 0, 52, 49, 0.125);
            //crouching
            SS.addSprite(745, 0, 36, 51, 0.125);
            SS.addSprite(781, 0, 31, 43, 0);
            //jumping
            SS.addSprite(812, 0, 34, 63, 0);
            //falling
            SS.addSprite(846, 0, 49, 64, 0);
            //forward light
            SS.addSprite(1721, 0, 41, 57, 0.125);
            SS.addSprite(1762, 0, 39, 56, 0.125);
            SS.addSprite(1801, 0, 62, 57, 0.125);
            SS.addSprite(1863, 0, 56, 58, 0.125);
            SS.addSprite(1919, 0, 40, 55, 0.125);
            SS.addSprite(1959, 0, 51, 57, 0.125);
            SS.addSprite(2010, 0, 57, 50, 0.125);
            SS.addSprite(2067, 0, 56, 46, 0.125);
            SS.addSprite(2123, 0, 41, 56, 0.125);
            SS.addSprite(2164, 0, 56, 52, 0.125);
            SS.addSprite(2220, 0, 57, 54, 0.125);
            SS.addSprite(2277, 0, 49, 49, 0.125);
            //down light            SS.addSprite(1480, 0, 44, 42, 0.125);
            SS.addSprite(1524, 0, 39, 43, 0.125);
            SS.addSprite(1563, 0, 63, 44, 0.125);
            SS.addSprite(1626, 0, 55, 45, 0.125);
            SS.addSprite(1681, 0, 40, 43, 0.125);            //light            SS.addSprite(2326, 0, 46, 56, 0.125);
            SS.addSprite(2372, 0, 48, 48, 0.125);
            SS.addSprite(2420, 0, 46, 72, 0.125);
            SS.addSprite(2466, 0, 47, 64, 0.125);
            SS.addSprite(2513, 0, 47, 64, 0.125);            //running light            SS.addSprite(2560, 0, 50, 42, 0.125);
            SS.addSprite(2610, 0, 46, 55, 0.125);
            SS.addSprite(2656, 0, 49, 55, 0.125);
            SS.addSprite(2705, 0, 51, 51, 0.125);
            SS.addSprite(2756, 0, 46, 46, 0.125);            //kunai            SS.addSprite(3533, 0, 11, 24, 0);            //air throw
            SS.addSprite(3399, 0, 34, 58, 0.125);
            SS.addSprite(3433, 0, 52, 51, 0.125);
            SS.addSprite(3485, 0, 48, 47, 0.125);            //crouched throw            SS.addSprite(3207, 0, 34, 39, 0.125);
            SS.addSprite(3241, 0, 34, 39, 0.125);
            SS.addSprite(3275, 0, 40, 37, 0.125);
            SS.addSprite(3315, 0, 44, 41, 0.125);
            SS.addSprite(3359, 0, 40, 40, 0.125);            //throw
            SS.addSprite(2966, 0, 36, 57, 0.125);
            SS.addSprite(3002, 0, 55, 52, 0.125);
            SS.addSprite(3057, 0, 53, 45, 0.125);
            SS.addSprite(3110, 0, 51, 46, 0.125);
            SS.addSprite(3161, 0, 46, 47, 0.125);
            
            SA = new SpriteAnimations(SS, 13, 12);

            SA.createSpriteGroup(0, 6);
            SA.createSpriteGroup(6, 12);
            SA.createSpriteGroup(12, 18);
            SA.createSpriteGroup(18, 20);
            SA.createSpriteGroup(20, 21);
            SA.createSpriteGroup(21, 22);
            SA.createSpriteGroup(22, 34);            SA.createSpriteGroup(34, 39);
            SA.createSpriteGroup(39, 44);
            SA.createSpriteGroup(44, 49);
            SA.createSpriteGroup(50, 53);
            SA.createSpriteGroup(53, 58);
            SA.createSpriteGroup(58, 63);
            SA.setAnimation(STANCE);

            crouched = false;
            orientation=1;

            //update this
            dmgAnimations = new byte[4];
            dmgAnimations[0] = F_LIGHT;
            dmgAnimations[1] = D_LIGHT;
            dmgAnimations[2] = LIGHT;
            dmgAnimations[3] = R_LIGHT;
            dmgFrames = new SpriteSheet.Sprite[7];
            dmgFrames[0] = SS.getSprite(25);
            dmgFrames[1] = SS.getSprite(29);
            dmgFrames[2] = SS.getSprite(33);
            dmgFrames[3] = SS.getSprite(37);
            dmgFrames[4] = SS.getSprite(42);
            dmgFrames[5] = SS.getSprite(46);            break;        }
    }    public void throw_weapon(int x, int y, double angle, int type) {        switch(type) {            case AIR_SPECIAL:            AS_timer = System.nanoTime();            break;            case D_SPECIAL:            DS_timer = System.nanoTime();            break;            case SPECIAL:            S_timer = System.nanoTime();            break;        }        throwableInfo[numofThrowables][0] = x;
        throwableInfo[numofThrowables][1] = y;        throwableInfo[numofThrowables][2] = angle;
        throwableInfo[numofThrowables][3] = x;        numofThrowables++;    }
    public void update() {
        move();        updateThrowables();        long currentTime = System.nanoTime();
        if(currentTime-AS_timer>=375000000&&currentTime-DS_timer>=500000000&&currentTime-S_timer>=625000000) {            if(jumping) {setAnimation(JUMPING);}
            else if(falling) {setAnimation(FALLING);}
            else if(animation==JUMPING||animation==FALLING||animation==AIR_SPECIAL||animation==D_SPECIAL||animation==SPECIAL) {setAnimation(STANCE);}            else if(SA.frameInfo!=null) {y=300-SA.frameInfo.height;}        }
        SA.updateImage();
    }    public void drawThrowables(Graphics g) {        Graphics2D g2 = (Graphics2D)g;        SpriteSheet.Sprite kunai = SS.getSprite(KUNAI);        for(int i=0; i<numofThrowables; i++) {            g2.rotate(throwableInfo[i][2]*Math.PI/180, throwableInfo[i][0]+5, throwableInfo[i][1]+12);            g2.drawImage(SS.getSpriteImage(KUNAI), (int)throwableInfo[i][0], (int)throwableInfo[i][1], kunai.width, kunai.height, null);            g2.rotate(-throwableInfo[i][2]*Math.PI/180, throwableInfo[i][0]+5, throwableInfo[i][1]+12);        }    }    public void kunaiCollisions(Player p) {        for(int i=0; i<numofThrowables; i++) {            switch((int)throwableInfo[i][2]) {                case 90:                if((throwableInfo[i][0]>=p.x&&throwableInfo[i][0]<=p.x+p.hitBoxWidth&&throwableInfo[i][1]>=p.y&&throwableInfo[i][1]<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]+24>=p.x&&throwableInfo[i][0]+24<=p.x+p.hitBoxWidth&&throwableInfo[i][1]>=p.y&&throwableInfo[i][1]<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]>=p.x&&throwableInfo[i][0]<=p.x+p.hitBoxWidth&&throwableInfo[i][1]+11>=p.y&&throwableInfo[i][1]+11<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]+24>=p.x&&throwableInfo[i][0]+24<=p.x+p.hitBoxWidth&&throwableInfo[i][1]+11>=p.y&&throwableInfo[i][1]+11<=p.y+p.hitBoxHeight)) {                    p.health-=0.5;                    removeThrowable(i);                }                break;                case 270:                if((throwableInfo[i][0]>=p.x&&throwableInfo[i][0]<=p.x+p.hitBoxWidth&&throwableInfo[i][1]>=p.y&&throwableInfo[i][1]<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]-24>=p.x&&throwableInfo[i][0]-24<=p.x+p.hitBoxWidth&&throwableInfo[i][1]>=p.y&&throwableInfo[i][1]<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]>=p.x&&throwableInfo[i][0]<=p.x+p.hitBoxWidth&&throwableInfo[i][1]+11>=p.y&&throwableInfo[i][1]+11<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]-24>=p.x&&throwableInfo[i][0]-24<=p.x+p.hitBoxWidth&&throwableInfo[i][1]+11>=p.y&&throwableInfo[i][1]+11<=p.y+p.hitBoxHeight)) {                    p.health-=0.5;
                    removeThrowable(i);                }                break;                case 135:                if((throwableInfo[i][0]>=p.x&&throwableInfo[i][0]<=p.x+p.hitBoxWidth&&throwableInfo[i][1]>=p.y&&throwableInfo[i][1]<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]+24*Math.cos(fourtyFiveInRads)>=p.x&&throwableInfo[i][0]+24*Math.cos(fourtyFiveInRads)<=p.x+p.hitBoxWidth&&throwableInfo[i][1]+24*Math.sin(fourtyFiveInRads)>=p.y&&throwableInfo[i][1]+24*Math.sin(fourtyFiveInRads)<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]-11*Math.cos(fourtyFiveInRads)>=p.x&&throwableInfo[i][0]-11*Math.cos(fourtyFiveInRads)<=p.x+p.hitBoxWidth&&throwableInfo[i][1]+11*Math.sin(fourtyFiveInRads)>=p.y&&throwableInfo[i][1]+11*Math.sin(fourtyFiveInRads)<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]+hypotenuseLength*Math.cos(hypotenuseAngle)>=p.x&&throwableInfo[i][0]+hypotenuseLength*Math.cos(hypotenuseAngle)<=p.x+p.hitBoxWidth&&throwableInfo[i][1]+hypotenuseLength*Math.sin(hypotenuseAngle)>=p.y&&throwableInfo[i][1]+hypotenuseLength*Math.sin(hypotenuseAngle)<=p.y+p.hitBoxHeight)) {                    p.health-=2;
                    removeThrowable(i);                }                break;
                case 225:                if((throwableInfo[i][0]>=p.x&&throwableInfo[i][0]<=p.x+p.hitBoxWidth&&throwableInfo[i][1]>=p.y&&throwableInfo[i][1]<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]-24*Math.cos(fourtyFiveInRads)>=p.x&&throwableInfo[i][0]-24*Math.cos(fourtyFiveInRads)<=p.x+p.hitBoxWidth&&throwableInfo[i][1]-24*Math.sin(fourtyFiveInRads)>=p.y&&throwableInfo[i][1]-24*Math.sin(fourtyFiveInRads)<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]+11*Math.cos(fourtyFiveInRads)>=p.x&&throwableInfo[i][0]+11*Math.cos(fourtyFiveInRads)<=p.x+p.hitBoxWidth&&throwableInfo[i][1]-11*Math.sin(fourtyFiveInRads)>=p.y&&throwableInfo[i][1]-11*Math.sin(fourtyFiveInRads)<=p.y+p.hitBoxHeight)||(throwableInfo[i][0]-hypotenuseLength*Math.cos(hypotenuseAngle)>=p.x&&throwableInfo[i][0]-hypotenuseLength*Math.cos(hypotenuseAngle)<=p.x+p.hitBoxWidth&&throwableInfo[i][1]-hypotenuseLength*Math.sin(hypotenuseAngle)>=p.y&&throwableInfo[i][1]-hypotenuseLength*Math.sin(hypotenuseAngle)<=p.y+p.hitBoxHeight)) {                    p.health-=2;
                    removeThrowable(i);
                }                break;            }        }    }
    public void setAnimation(int index) {
        animation = (byte)index;
        SA.setAnimation(index);
    }
    public byte getAnimation() {
        return animation;
    }
    public BufferedImage getFrame() {
        return SA.frame;
    }
    public SpriteSheet.Sprite getFrameInfo() {
        return SA.frameInfo;
    }
    private void updateThrowables() {        for(int i=0; i<numofThrowables; i++) {            switch((int)throwableInfo[i][2]) {                case 90:                throwableInfo[i][0] += 10;                break;
                case 270:                throwableInfo[i][0] -= 10;                break;                case 135:                throwableInfo[i][0] += 10;
                throwableInfo[i][1] += 10;                break;                case 225:                throwableInfo[i][0] -= 10;
                throwableInfo[i][1] += 10;                break;            }        }        for(int i=0; i<numofThrowables; i++) {            if(throwableInfo[i][1]>=300||Math.abs(throwableInfo[i][0]-throwableInfo[i][3])>500) {                removeThrowable(i);                continue;            }        }    }    private void removeThrowable(int index) {        for(int i=index; i<numofThrowables; i++) {
            throwableInfo[i] = throwableInfo[i+1];
        }        numofThrowables--;    }}