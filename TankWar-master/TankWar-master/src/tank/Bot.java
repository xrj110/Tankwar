package tank;
import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Bot extends Tank implements Serializable {
    Toolkit Toolkits;
    int moveTime = 0;
    public Bot( int x, int y,  GamePanel gamePanel) {
        super( x, y, gamePanel);

        setUpImage(Toolkits.getDefaultToolkit().getImage("images/enemy/enemy1U.gif"));
        setDownImage(Toolkits.getDefaultToolkit().getImage("images/enemy/enemy1D.gif"));
        setLeftImage(Toolkits.getDefaultToolkit().getImage("images/enemy/enemy1L.gif"));
        setRightImage(Toolkits.getDefaultToolkit().getImage("images/enemy/enemy1R.gif"));

    }

    public void go(){
        attack();
        if(moveTime>=20) {
            direction=randomDirection();
            moveTime=0;
        }else {
            moveTime+=1;
        }
        switch (direction) {
            case UP :
                upward();
                break;
            case DOWN :
                downward();
                break;
            case RIGHT :
                rightward();
                break;
            case LEFT :
                leftward();
                break;
        }
    }

    //电脑坦克随机方向
    public Direction randomDirection() {
        Random r = new Random();
        int rnum = r.nextInt(4);
        switch(rnum) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.LEFT;
            default:
                return Direction.DOWN;
        }
    }

    //只有4%几率攻击
    public void attack() {
        Point p = getHeadPoint();
        Random r = new Random();
        int rnum =r.nextInt(100);
        if(rnum<4) {
            EnemyBullet enemyBullet = new EnemyBullet(Toolkit.getDefaultToolkit().getImage("images/bullet/bulletYellow.gif"),p.x,p.y,direction,gamePanel);
            this.gamePanel.bulletList.add(enemyBullet);
        }
    }

    @Override
    public void paintSelf(Graphics g) {
        g.drawImage(img,x,y,null);
        this.go();
    }

    @Override
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}

