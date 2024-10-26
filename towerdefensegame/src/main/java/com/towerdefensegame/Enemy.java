import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;
public class enemy {
    
    String origin;
    String enemyName;
    int speed ,hitpoints , armourClass;
    int coordX , coordY;
    int enemyGoldValue;
    texture tex;
    int travelDistance;
    boolean isInsideGrid;
    boolean isAlive;
    int drawingOffestX;
    int drawingOffestY;
    Random rand;
    Rectangle bounds;
    Animation animation;

    public enemy( int coordX, int coordY, int type){

        rand = new Random();
        travelDistance = 0;
        tex = new texture();
        this.coordX = coordX;
        this.coordY = coordY;
        isInsideGrid = false;
        this.hitpoints = 50;
        this.isAlive = true;
        drawingOffestX = rand.nextInt(-5,5);
        tex.initTexture();
        drawingOffestY = rand.nextInt(-5,5);
        animation= new Animation(5, tex.enemyTypeFast[0], tex.enemyTypeFast[1], tex.enemyTypeFast[2], tex.enemyTypeFast[1], tex.enemyTypeFast[0], tex.enemyTypeFast[3], tex.enemyTypeFast[4], tex.enemyTypeFast[3]);
        switch (type) {
            case 1:
                speed =1;
                break;
            case 2:
            speed =2;
                break;

            case 3:
            speed =1;
                break;
            case 4:
            speed =3;
                break;
            default:
                break;
        }
    
    }

    

    public void takeDamage(int damage){
        hitpoints -= damage;
        if(hitpoints<=0){
            isAlive=false;
        }
    }

    void render(Graphics2D g2d){
        //g2d.drawImage(tex.enemyTypeFast[2], coordX+drawingOffestX, coordY+drawingOffestY, null);
        animation.drawAnimation(g2d, coordX, coordY, drawingOffestX);

    }

    void update(){
        animation.runAnimation();
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getCoordX() {
        return coordX;
    }

    public int getCoordY() {
        return coordY;
    }
    public BufferedImage getTex() {
        return tex.getTexture("enemy");
    }





    public void checkIfInside(int width , int height ,int squareSize) {
        if(coordX>=0 && coordX<=width-squareSize && coordY>=0 && coordY<=height-squareSize){
            isInsideGrid=true;
        }else{
            isInsideGrid = false;
        }
    }
}
