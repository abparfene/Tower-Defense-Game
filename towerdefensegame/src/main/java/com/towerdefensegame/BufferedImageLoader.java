import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
//22.02
public class bufferedImageLoader {

	
	
	public BufferedImage loadImage(String path) throws IOException {
		
		return ImageIO.read(getClass().getResource(path));
	}
	
}
