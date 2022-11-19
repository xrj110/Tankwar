package tank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Information implements Serializable {
    ArrayList wallList;
//    ArrayList BotList;
//    ArrayList TankList;
    String level;

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

//    public List getBotList() {
//        return BotList;
//    }
//
//    public void setBotList(ArrayList botList) {
//        BotList = botList;
//    }
//
//    public List getTankList() {
//        return TankList;
//    }
//
//    public void setTankList(ArrayList tankList) {
//        TankList = tankList;
//    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
