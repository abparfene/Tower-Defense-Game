import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
@SuppressWarnings("unused")
public class mouseInput implements MouseListener {


    int mY;
    int mX;
    boolean clicked = false;
    String typeTile;
    Component source;
    sideWindow panel;
    String brush;
    public mouseInput(sideWindow panel){
        this.panel = panel;
    }

    public mouseInput(){
        
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        
        mY = e.getY();
        mX = e.getX();
        clicked = true;
        source = e.getComponent();
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mY = e.getY();
        mX = e.getX();
        source = e.getComponent();
        if (source instanceof gamePanel) {
            
            // Handle gamePanel-specific logic
        } else if (source instanceof sideWindow) {
               
            switch (panel.gameInstance.state) {
                case startScreen:
                    
                    break;
                case levelBuilder:
                    Rectangle[] rectArr = panel.getRect();
                    Rectangle start = rectArr[0];
                    if(start.contains(e.getPoint())){
                        brush = "start";
                    }
                    Rectangle end = rectArr[1];
                    if(end.contains(e.getPoint())){
                        brush = "end";
                    }
                    Rectangle down = rectArr[2];
                    if(down.contains(e.getPoint())){
                        brush = "down";
                    }
                    Rectangle up = rectArr[3];;
                    if(up.contains(e.getPoint())){
                        brush = "up";
                    }
                    Rectangle left = rectArr[4];
                    if(left.contains(e.getPoint())){
                        brush = "left";
                    }   
                    Rectangle right = rectArr[5];
                    if(right.contains(e.getPoint())){
                        brush = "right";
                    }                                    
                    gamePanel.setBrush(brush);
                    // Handle sideWindow-specific logic
                    break;
                case gamePlay:
                    Rectangle[] towerArr = panel.getTowerRect();
                    Rectangle tower1 = towerArr[0];
                    if(tower1.contains(e.getPoint())){
                        brush = "tower";
                    }
                    Rectangle tower2 = towerArr[1];
                    if(tower2.contains(e.getPoint())){
                        brush = "tower";
                    }
                    Rectangle tower3 = towerArr[2];
                    if(tower3.contains(e.getPoint())){
                        brush = "tower";
                    }
                    gamePanel.setBrush(brush);

                    break;
                    
            }

            





        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
        source = e.getComponent();
        if (source instanceof gamePanel) {
            mY = e.getY();
            mX = e.getX();
            // Handle gamePanel-specific logic
        } else if (source instanceof sideWindow) {
            
            



            // Handle sideWindow-specific logic
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
    

    public boolean hasBeenClicked() {
        return clicked;
    }

    
    public int[] getCoordinates() {
        return new int[]{mX, mY};
    }
    
    public void resetClick() {
        clicked = false; 
    }
}
