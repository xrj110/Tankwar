package tank;

//import com.sun.deploy.net.MessageHeader;



import org.testng.annotations.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class GamePanel extends JFrame {

    /** 定义双缓存图片 */
    private Image offScreenImage = null;
    //游戏状态: 0 游戏未开始，1 单人模式，2 双人模式， 3 游戏暂停， 4 游戏失败，5 游戏成功
    public int state= 0;
    //临时变量
    private int model=0;
    //singe 1, double 2;
    private int a = 1;
    //重绘次数
    public int count = 0;
    //窗口长宽
    private int width = 800;
    private int height = 610;
    //敌人数量
    private int enemyCount = 0;
    //高度
    private int y = 150;
    //是否开始
    private boolean start = false;
    //物体集合
    private int level=1;
    public List<Bullet> bulletList = new ArrayList<>();
    public List<Bot> botList = new ArrayList<>();
    public List<Tank> tankList = new ArrayList<>();
    public List<Wall> wallList = new ArrayList<>();
    public List<Bullet> removeList = new ArrayList<>();
    public List<Base> baseList = new ArrayList<>();
    public List<BlastObj> blastList = new ArrayList<>();

    Properties properties=System.getProperties();

    private Toolkit Toolkits;
    //背景图片
    public Image background = Toolkits.getDefaultToolkit().getImage("images/background.jpg");
    //指针图片
    private Image select = Toolkits.getDefaultToolkit().getImage("images/selecttank.gif");
    //基地
    private Base base = new Base(Toolkits.getDefaultToolkit().getImage("images/star.gif")
,365, 560, this);


    //create player
    private PlayerOne playerOne = null;

    private PlayerTwo playerTwo = null;
    public PlayerOne getPlayerOne() {
        return playerOne;
    }

    public void refreshPlayerOne() {
        this.playerOne = new PlayerOne(
                125, 510, this);
    }

    public PlayerTwo getPlayerTwo() {
        return playerTwo;
    }

    public void refreshPlayerTwo() {
        this.playerTwo = new PlayerTwo(
                625, 510, this);
    }

    //窗口的启动方法
    public void launch(){
        //标题
        setTitle("Tank Wars");
        //窗口初始大小
        setSize(width, height);
        //用户不能调整大小
        setResizable(false);
        //使窗口可见
        setVisible(true);
        //获取屏幕分辨率，使窗口生成时居中
        setLocationRelativeTo(null);
        //添加关闭事件
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //添加键盘事件
        KeyMonitor keyMonitor = new KeyMonitor();
        keyMonitor.setGamePanel(this);

        this.addKeyListener(keyMonitor);
        //add walls 60*60
        if (level==1){
            for(int i = 0; i< 14; i ++){
                wallList.add(new Wall( i*60 ,170, this ));
            }
        }
        wallList.add(new Wall( 305 ,560,this ));
        wallList.add(new Wall( 305 ,500,this ));
        wallList.add(new Wall( 365 ,500,this ));
        wallList.add(new Wall( 425 ,500,this ));
        wallList.add(new Wall( 425 ,560,this ));
        //add base
        baseList.add(base);

        while (true){
            if(botList.size() == 0 && enemyCount == 10){
                state = 5;
            }
            if(tankList.size() == 0 && (state == 1 || state == 2)){
                state = 4;
            }
            if(state == 1 || state == 2){
                if (count % 100 == 1 && enemyCount < 10) {
                    Random r = new Random();
                    int rnum =r.nextInt(800);
                    botList.add(new Bot( rnum, 110, this));
                    enemyCount++;
                }
            }
            repaint();
            try {
                //线程休眠
                Thread.sleep(25);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    public void quit(){
        System.exit(0);
    }

    @Override
    public void paint(Graphics g) {
        // 创建和容器一样大小的Image图片
        if(offScreenImage ==null){
            offScreenImage=this.createImage(width, height);
        }
        // 获得该图片的画布
        Graphics gImage= offScreenImage.getGraphics();
        // 背景颜色
        gImage.setColor(Color.gray);
        // 填充整个画布
        gImage.fillRect(0, 0, width, height);
        //改变画笔的颜色
        gImage.setColor(Color.orange);
        //改变文字大小和样式
        gImage.setFont(new Font("正楷",Font.BOLD,15));
        gImage.drawString("Current Level:"+level,20,50);
        gImage.setFont(new Font("正楷",Font.BOLD,20));
        if(state == 0){
            //添加文字
            gImage.drawString("Game Models",220,100);
            gImage.drawString("One Player",220,200);
            gImage.drawString("Two Player",220,300);
            gImage.drawString("Pressing 1 or 2 to choice the model，press Enter begin the game",50,400);

            gImage.drawImage(select,160,y,null);
        }
        else if(state == 1||state == 2){
            gImage.setColor(Color.red);
            gImage.setFont(new Font("仿宋",Font.BOLD,20));
            gImage.drawString("WASD control the direction",0,510);
            gImage.drawString("Spacebar fire",0,550);
            if(state == 2){
                gImage.drawString("Arrow keys control direction",575,510);
                gImage.drawString("K fire",575,550);
            }

            //paint重绘游戏元素
            for(Tank tank : tankList){
                tank.paintSelf(gImage);
            }
            for(Bullet bullet: bulletList){
                bullet.paintSelf(gImage);
            }
            bulletList.removeAll(removeList);
            for(Bot bot: botList){
                bot.paintSelf(gImage);
            }
            for (Wall wall: wallList){
                wall.paintSelf(gImage);
            }
            for(Base base : baseList){
                base.paintSelf(gImage);
            }
            for(BlastObj blast : blastList){
                blast.paintSelf(gImage);
            }
            //重绘次数+1
            count++;
        }
        else if(state == 3){
            gImage.drawString("Game Stop",220,200);
        }
        else if(state == 4){
            gImage.drawString("Defeat, do you want to retry ?",80,100);
            gImage.drawString("Yes",220,200);
            gImage.drawString("No",220,300);
            gImage.drawString("Pressing 1 or 2 to choice the model，press Enter begin the game",50,400);
            gImage.drawImage(select,160,y,null);

        }
        else if(state == 5){
            if (level==1){
                gImage.drawString("Victory, do you want to enter the next level?",80,100);
                gImage.drawString("Yes",220,200);
                gImage.drawString("No",220,300);
                gImage.drawString("Pressing 1 or 2 to choice the model，press Enter begin the game",50,400);
                gImage.drawImage(select,160,y,null);
            }
            else
                gImage.drawString("Victory",220,200);
        }

        /* 将缓冲区绘制好的图形整个绘制到容器的画布中 */
        g.drawImage(offScreenImage, 0, 0, null);
        System.out.println();
    }

    private class KeyMonitor extends KeyAdapter {
        GamePanel gamePanel;

        public void setGamePanel(GamePanel gamePanel) {
            this.gamePanel = gamePanel;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            //super.keyPressed(e);
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_1:
                    y = 150;
                    a = 1;
                    break;
                case KeyEvent.VK_2:
                    y = 250;
                    a = 2;
                    break;
                case KeyEvent.VK_0:
                    botList.clear();
                    break;
                case KeyEvent.VK_ENTER:
                    //lose
                    if (state==4&&a==1){//retry
                        if (model==1) {
                            state = 1;
                            if (baseList.isEmpty()){
                                baseList.add(base);
                            }
                            if (tankList.isEmpty()){
                                refreshPlayerOne();
                                tankList.add(playerOne);
                            }
                        }
                        else {
                            state=2;

                            if (baseList.isEmpty()){
                                baseList.add(base);
                            }
                            if (tankList.isEmpty()){
                                refreshPlayerOne();
                                refreshPlayerTwo();
                                tankList.add(playerOne);
                                tankList.add(playerTwo);
                            }
                            System.out.println(state);
                        }
                        bulletList.clear();
                        break;
                    }
                    if (state==4&&a==2){//quit
                        quit();
                        break;
                    }
                    if(a == 1 && !start){//enter single
                        state=1;
                        model=1;
                        refreshPlayerOne();
                        tankList.add(playerOne);
                    }else if (a==2&&!start){//enter double
                        state=2;
                        model=2;
                        refreshPlayerOne();
                        refreshPlayerTwo();
                        tankList.add(playerOne);
                        tankList.add(playerTwo);
                    }
                    start = true;
                    break;
                case KeyEvent.VK_P:
                    if(state != 3){
                        a = state;
                        state = 3;
                    }
                    else{
                        state = a;
                        if(a == 0) {
                            a = 1;
                        }
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    try {
                        Save();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    quit();
                    break;

                default:
                    if (model==1) {
                        playerOne.keyPressed(e);

                    }

                    if (model==2)
                    {
                        playerOne.keyPressed(e);
                        playerTwo.keyPressed(e);

                    }


                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){




            if (model==1) {
                playerOne.keyReleased(e);

            }

            if (model==2)
            {
                playerOne.keyReleased(e);
                playerTwo.keyReleased(e);

            }

        }

    }

    public static void main(String[] args) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.launch();
    }

    private void Save() throws IOException {


        OutputStream f = new FileOutputStream("C:/Users/"+properties.getProperty("user.name")+"/Documents/TankWar");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(f);
        Information information = new Information();
        information.setLevel(level+"");
//        information.setBotList((ArrayList) botList);
//        information.setTankList((ArrayList)tankList);
        information.setWallList((ArrayList)wallList);
        information.setState(state);
        objectOutputStream.writeObject(information);
        objectOutputStream.close();



    }

    private void read() throws IOException, ClassNotFoundException {
        InputStream f = new FileInputStream("C:/Users/"+properties.getProperty("user.name")+"/Documents/TankWar");
        ObjectInputStream objectInputStream = new ObjectInputStream(f);

        Information information=(Information) objectInputStream.readObject();
    }
    @Test
    public void test() throws IOException, ClassNotFoundException {
        InputStream f = new FileInputStream("C:/Users/"+properties.getProperty("user.name")+"/Documents/TankWar");
        ObjectInputStream objectInputStream = new ObjectInputStream(f);

        Information information=(Information) objectInputStream.readObject();
        System.out.println(information.toString());
    }
}