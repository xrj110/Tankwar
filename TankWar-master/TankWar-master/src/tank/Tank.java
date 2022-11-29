package tank;



import java.awt.*;
import java.io.Serializable;
import java.util.List;

public class Tank extends GameObject implements Serializable {


    protected int index;
    private boolean attackCoolDown =true;//攻击冷却状态
    private int attackCoolDownTime =1000;//攻击冷却时间毫秒间隔1000ms发射子弹
    private Image upImage; //向上移动时的图片
    private Image downImage;//向下移动时的图片
    private Image rightImage;//向右移动时的图片
    private Image leftImage;//向左移动时的图片
    boolean alive = true;
    //坦克size
    int width = 40;
    int height = 50;
    //坦克初始方向
    Direction direction = Direction.UP;
    //坦克速度
    private int speed = 3;
    //坦克头部坐标
    Point p;

    public void setUpImage(Image upImage) {
        this.upImage = upImage;
    }

    public void setDownImage(Image downImage) {
        this.downImage = downImage;
    }

    public void setRightImage(Image rightImage) {
        this.rightImage = rightImage;
    }

    public void setLeftImage(Image leftImage) {
        this.leftImage = leftImage;
    }

    //坦克坐标，方向，图片，方向，面板
    public Tank( int x, int y,  GamePanel gamePanel) {
        super(x, y, gamePanel);

    }

    public void leftward(){
        direction = Direction.LEFT;
        setImg(leftImage);
        if(!hitWall(x-speed, y) && !moveToBorder(x-speed, y) && alive){
            this.x -= speed;
        }
    }
    public void rightward(){
        direction = Direction.RIGHT;
        setImg(rightImage);
        if(!hitWall(x+speed, y) && !moveToBorder(x+speed, y) && alive){
            this.x += speed;
        }
    }
    public void upward(){
        direction = Direction.UP;
        setImg(upImage);
        if(!hitWall(x, y-speed) && !moveToBorder(x, y- speed) && alive){
            this.y -= speed;
        }
    }
    public void downward(){
        direction = Direction.DOWN;
        setImg(downImage);
        if(!hitWall(x, y+speed) && !moveToBorder(x, y+speed) && alive){
            this.y += speed;
        }
    }

    public void attack(){
        Point p = getHeadPoint();
        if(attackCoolDown && alive){
            Bullet bullet = new Bullet(Toolkit.getDefaultToolkit().getImage("images/bullet/bulletGreen.gif"),p.x,p.y,direction, this.gamePanel);
            this.gamePanel.bulletList.add(bullet);
            attackCoolDown = false;
            new AttackCD().start();
        }
    }

    public boolean hitWall(int x, int y){
        //假设玩家坦克前进，下一个位置形成的矩形
        Rectangle next = new Rectangle(x, y, width, height);
        //地图里所有的墙体
        List<Wall> walls = this.gamePanel.wallList;
        //判断两个矩形是否相交（即是否撞墙）
        for(Wall w:walls){
            if(w.getRec().intersects(next)){
                return true;
            }
        }
        return false;
    }
    public int getIndex() {
        return index;
    }

    public boolean moveToBorder(int x, int y){
        if(x < 0){
            return true;
        }else if(x > this.gamePanel.getWidth()-width){
            return true;
        }
        if(y < 0){
            return true;
        }else if(y > this.gamePanel.getHeight()-height){
            return true;
        }
        return false;
    }

    public class AttackCD extends Thread{
        public void run(){
            attackCoolDown=false;//将攻击功能设置为冷却状态
            try{
                Thread.sleep(attackCoolDownTime);//休眠1秒
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            attackCoolDown=true;//将攻击功能解除冷却状态
            this.interrupt();
        }
    }

    //根据方向确定头部位置，x和y是左下角的点
    public Point getHeadPoint(){
        switch (direction){
            case UP:
                return new Point(x + width/2, y );
            case LEFT:
                return new Point(x, y + height/2);
            case DOWN:
                return new Point(x + width/2, y + height);
            case RIGHT:
                return new Point(x + width, y + height/2);
            default:
                return null;
        }
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

