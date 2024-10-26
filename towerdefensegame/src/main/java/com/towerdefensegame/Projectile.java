import java.awt.Graphics2D;
import java.awt.Rectangle;

public class projectile {
    private int x, y;  // Current position of the projectile
    private int speed;
    private int damage ;
    enemy target;  // The enemy this projectile is targeting
    public boolean isActive;  // Whether the projectile is still active (not hit anything yet)
    Rectangle bounds;
    texture tex;

    public projectile(int x, int y, int speed, int damage, enemy target, texture tex) {

        this.x = x;
        this.y = y;
        this.speed = speed;
        this.damage = damage;
        this.target = target;
        this.tex = tex;
        isActive = true;

    }
    void render(Graphics2D g2d){

        g2d.drawImage(tex.getTexture("arrow"), x,y, null);
    }

    // Move projectile towards the target
    public void move() {
        if (isActive && target != null) {
            // Calculate direction to the target
            int targetX = target.coordX +24;
            int targetY = target.coordY +24;
            int dx = targetX - x;
            int dy = targetY - y;
            double distance = Math.sqrt(dx*dx + dy*dy);
            x += (int) (speed * (dx / distance));
            y += (int) (speed * (dy / distance));
           

            if(distance <= speed){
                target.takeDamage(damage);
                isActive = false;
            }
        }
    }
}