import java.awt.*;
import javax.swing.*;

@SuppressWarnings("unused")
public class backgroudGrid {
    int width;
    int heigth;
    int squareSize;
    String[][] grid;

    public backgroudGrid(int len , int heigth , int squareSize){

        this.width = len;
        this.heigth = heigth;
        this.squareSize = squareSize;
        this.grid = new String[this.width][this.heigth];
    }



    void loadGrid(){
        //loads grid of a file

    }
    
}
