import javax.swing.*;


public class NullTileException extends RuntimeException{

    public NullTileException(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
