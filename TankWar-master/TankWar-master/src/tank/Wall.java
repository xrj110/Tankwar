package tank;
import javax.swing.*;
import java.awt.*;

public class Wall extends GameObject {

    public int width = 60;
    public int height = 60;

    public Wall( int x, int y, GamePanel gamePanel){

        super(x, y, gamePanel);

//        img=Toolkits.getDefaultToolkit().getImage("images/walls.gif");
        img=new ImageIcon("images/walls.gif").getImage();
        }



    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}

