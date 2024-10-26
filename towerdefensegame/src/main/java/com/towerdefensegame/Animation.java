import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Animation {

    private int speed, frames, index = 0, count = 0;

    private BufferedImage img1, img2, img3, img4, img5, img6, img7, img8, currentImg;

    public Animation(int speed, BufferedImage img1, BufferedImage img2, BufferedImage img3, BufferedImage img4,
            BufferedImage img5, BufferedImage img6, BufferedImage img7, BufferedImage img8) {

        this.speed = speed;
        this.img1 = img1;
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
        this.img5 = img5;
        this.img6 = img6;
        this.img7 = img7;
        this.img8 = img8;
        frames = 8;

    }

    public void runAnimation() {
        index++;
        if (index > speed) {
            index = 0;
            nextFrame();

        }
    }

    public void nextFrame() {
        switch (count) {
            case 0:
                currentImg = img1;
                break;
            case 1:
                currentImg = img2;
                break;
            case 2:
                currentImg = img3;
                break;
            case 3:
                currentImg = img4;
                break;
            case 4:
                currentImg = img5;
                break;
            case 5:
                currentImg = img6;
                break;
            case 6:
                currentImg = img7;
                break;
            case 7:
                currentImg = img8;
                break;
        }

        count++;
        if (count > frames) {
            count = 0;
        }
    }

    public void drawAnimation(Graphics2D g, double x, double y, int offset) {

        g.drawImage(currentImg, (int) x + offset, (int) y + offset, null);
    }
}
