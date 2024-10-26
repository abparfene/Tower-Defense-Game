import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class texture {
    

    spriteSheet gameTiles , enemyFast;
    bufferedImageLoader loader;
    public BufferedImage[] enemyTypeFast = new BufferedImage[32];
    texture (){

        loader = new bufferedImageLoader();
        try {
    		gameTiles = new spriteSheet(loader.loadImage("baseSprites.png"));
            enemyFast = new spriteSheet(loader.loadImage("enemyTypeFastSprites.png"));

    		
    	}catch(IOException e) {
    		
    	}

    }

    BufferedImage getTexture(String type){

        BufferedImage img = gameTiles.grabImage(1,2,48);
        switch(type){

            case "start":
                img = gameTiles.grabImage(1,1,48);
                break;
            case "end":
                img = gameTiles.grabImage(2,2,48);
                break;
            case "up":
                img = gameTiles.grabImage(1,2,48);
                break;
            case "down":
                img = gameTiles.grabImage(1,3,48);
                break;

            case "left":
                img = gameTiles.grabImage(2,3,48);
                break;
            case "right":
                img = gameTiles.grabImage(1,4,48);
                break;
            case "tower":
            try {                
                img = ImageIO.read(new File("tower.png"));
             } catch (IOException ex) {
                  
             }
             break;
             case "enemy":
             try {                
                img = ImageIO.read(new File("enemy4.png"));
             } catch (IOException ex) {
                  
             }break;
             case "arrow":
             try {                
                img = ImageIO.read(new File("arrow.png"));
             } catch (IOException ex) {
                  
             }break;
            default:
                break;
        }
        

        return img;
    }

    void initTexture(){
        int position = 0;
        for(int i = 1 ; i<5;i++){

            for(int j = 1;j<8;j++){

                enemyTypeFast[position]=enemyFast.grabImage(j,i,48);
                position++;
            }
        }
    }
}
