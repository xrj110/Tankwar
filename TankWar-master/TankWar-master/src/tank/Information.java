package tank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Information implements Serializable {
    ArrayList wallList=new ArrayList();
    ArrayList BotList=new ArrayList();
    ArrayList TankList=new ArrayList();
    String level;
    int enemyCount;
    int count;

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount(int enemyCount) {
        this.enemyCount = enemyCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List getWallList() {
        return wallList;
    }

    public void setWallList(ArrayList wallList) {
        this.wallList = wallList;
    }

    public void saveWall(List<Wall> WallList,List<Bot> BotList,List<Tank> TankList){
        for (Object temp:WallList
             ) {
            ObjectRecord objectRecord = new ObjectRecord();
            objectRecord.setX(((GameObject) temp).getX());
            objectRecord.setY(((GameObject) temp).getY());
            this.wallList.add(objectRecord);
        }
        for (Object temp:BotList
        ) {
            ObjectRecord objectRecord = new ObjectRecord();
            objectRecord.setX(((GameObject) temp).getX());
            objectRecord.setY(((GameObject) temp).getY());
            this.BotList.add(objectRecord);
        }

        for (Object temp:TankList
        ) {
            ObjectRecord objectRecord = new ObjectRecord();
            objectRecord.setX(((GameObject) temp).getX());
            objectRecord.setY(((GameObject) temp).getY());
            this.TankList.add(objectRecord);
        }

    }



    public List getBotList() {
        return BotList;
    }

    public void setBotList(ArrayList botList) {
        BotList = botList;
    }

    public List getTankList() {
        return TankList;
    }

    public void setTankList(ArrayList tankList) {
        TankList = tankList;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
class ObjectRecord implements Serializable{
    int x;
    int y;


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
