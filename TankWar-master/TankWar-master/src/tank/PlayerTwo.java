package tank;




import java.awt.*;
import java.awt.event.KeyEvent;

public class PlayerTwo extends Tank {

    private boolean up = false;
    private boolean left = false;
    private boolean right = false;
    private boolean down = false;
    Toolkit Toolkits;
    public PlayerTwo(int x, int y,  GamePanel gamePanel){
        super(x, y, gamePanel);
        index=2;
        img=Toolkits.getDefaultToolkit().getImage("images/player2/p2tankU.gif");
        this.setUpImage(Toolkits.getDefaultToolkit().getImage("images/player2/p2tankU.gif"));
        this.setDownImage(Toolkits.getDefaultToolkit().getImage("images/player2/p2tankD.gif"));
        this.setLeftImage(Toolkits.getDefaultToolkit().getImage("images/player2/p2tankL.gif"));
        this.setRightImage(Toolkits.getDefaultToolkit().getImage("images/player2/p2tankR.gif"));
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_K:
                this.attack();
                break;
            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            default:
                break;
        }
    }

    public void move(){
        if(left){
            leftward();
        }
        else if(right){
            rightward();
        }
        else if(up){
            upward();
        }else if(down){
            downward();
        }
    }

    public void paintSelf(Graphics g) {
        g.drawImage(img, x, y, null);
        System.out.println("x:"+x+" |y:"+y+"| img:"+img);
        move();
    }

    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}

