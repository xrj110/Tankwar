package tank;

import java.awt.event.KeyEvent;

public class TEST {
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        switch (key){
            case KeyEvent.VK_A:
                System.out.println("hi");
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) {
        TEST test = new TEST();
        while (true){

        }

    }
}
