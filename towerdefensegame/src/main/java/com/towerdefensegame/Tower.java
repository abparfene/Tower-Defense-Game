import java.util.LinkedList;
import java.awt.Graphics2D;
public class tower {

    String towerName;
    double attackCooldown; //in miliseconds
    int attackDamage;
    int towerCoordX, towerCoordY;
    int range;
    long lastAttackTime =0;
    int cost;
    int level;
    gameWindow game;
    texture tex;
    gamePanel gamePanel;
    LinkedList<projectile> listOfProjectiles;

    public tower( int x, int y, int type,int squareSize ,gamePanel gamePanel){
        
        tex =new texture();
        this.towerCoordX = x*squareSize;
        this.towerCoordY = y*squareSize;
        this.level = 1;
        this.gamePanel=gamePanel;
        listOfProjectiles = new LinkedList<projectile>();

        switch (type) {
            case 1:
            //arrow
                this.range = 200;
                this.attackDamage = 2;
                this.cost = 4;
                this.attackCooldown= 200;            
                break;
            case 2 :
            //canon
                this.range = 200;
                this.attackDamage = 5;
                this.cost = 4;
                this.attackCooldown= 0.67*level;    
                break;
            case 3:
            //wizard
                this.range = 200;
                this.attackDamage = 5;
                this.cost = 4;
                this.attackCooldown= 0.5*level;    
                break;

            default:
                break;
        }

        
    }

    public tower(int type){
        switch (type) {
            case 1:
            //arrow
                this.range = 200;
                this.attackDamage = 2;
                this.cost = 4;
                this.attackCooldown= 100;            
                break;
            case 2 :
            //canon
                this.range = 200;
                this.attackDamage = 5;
                this.cost = 4;
                this.attackCooldown= 0.67*level;    
                break;
            case 3:
            //wizard
                this.range = 200;
                this.attackDamage = 5;
                this.cost = 4;
                this.attackCooldown= 0.5*level;    
                break;

            default:
                break;
        }

    }

    void upgrade(){

        this.level++;
    }
    public boolean isInAttackRange(int enemyX, int enemyY){
        int distance = (int) Math.sqrt(Math.pow(enemyX - towerCoordX+24, 2) + Math.pow(enemyY - towerCoordY+24, 2));
        
        if (distance <= range)
            return true;
            else
                return false;
    }

    public void attack(enemy enemy){
        long currentTime = System.currentTimeMillis();
        if((currentTime - lastAttackTime >= attackCooldown )){
            projectile p = new projectile(towerCoordX, towerCoordY, 5, this.attackDamage, enemy, tex);
            this.addProjectileIntoList(p);
            lastAttackTime = currentTime;
        }
    }
    void render(Graphics2D g2d){


        g2d.drawImage(tex.getTexture("tower"), towerCoordX, towerCoordY, null);
        g2d.drawOval(towerCoordX-range+24, towerCoordY-range+24, 2*range, 2*range);

        for(projectile p : listOfProjectiles){
            p.render(g2d);
        }

    }

    public void checkInRange(LinkedList<enemy> EnemyList){  //essentially the update method for this class, but it has a prettier name

        for(enemy e:EnemyList){

            if(isInAttackRange(e.coordX, e.coordY) && e.hitpoints>0){

               // System.out.println("I HAVE ENEMY IN RANGE");
                attack(e);
                if(e.hitpoints<=0){
                    e.isAlive = false;
                }
            }
               
        }
        for(int i = 0 ; i< listOfProjectiles.size();i++) {
            projectile p = listOfProjectiles.get(i);  
            p.move();

            if(!p.isActive){
            listOfProjectiles.remove();    
            }
        } 

    }
    @Override
    public String toString(){
        return 
                "STATS"+"\n" +
                "Type" + 1 +"\n"+
                "Attack :" + "5\10\15"+"\n"+
                "Range: " + range +"\n"+
                "Atack speed" + attackCooldown/1000+"\n"+
                "DPS:" + (attackDamage*attackCooldown/1000)
        ;
    }

    public int getCost(){

        return cost;
    }
    public void clearProjectiles(){
        listOfProjectiles.clear();
    }

    public int getTowerCoordX(){
        return towerCoordX;
    }
    public int getTowerCoordY(){
        return towerCoordY;
    }
    public LinkedList<projectile> getProjectileList(){
        return listOfProjectiles;
    }
    public void addProjectileIntoList(projectile p){

        listOfProjectiles.add(p);
    }
}
