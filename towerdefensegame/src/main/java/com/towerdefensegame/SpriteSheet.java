import java.awt.image.BufferedImage;

public class spriteSheet {

	private BufferedImage image;
	
	public spriteSheet(BufferedImage TheImage ) {
		this.image=TheImage;
		
	}
	
	public BufferedImage grabImage(int col, int row ,int sqruarSize) {
		
		BufferedImage img = image.getSubimage((col * sqruarSize) - sqruarSize, (row * sqruarSize ) - sqruarSize, sqruarSize, sqruarSize);
		return img;
	}
}
