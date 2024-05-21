import beans.BackGround;
import beans.Mario;
import beans.MyFrame;
import util.Constant;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //创建窗口对象
        MyFrame myFrame=new MyFrame();
        //设置窗口大小
        myFrame.setSize(900,600);
        //设置窗口的居中
        myFrame.setLocationRelativeTo(null);
        //设置窗口的可见性
        myFrame.setVisible(true);
        //设置窗口关闭程序结束
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //设置键盘监听事件
        myFrame.addKeyListener(myFrame);
        //设置窗口的大小不可以改变
        myFrame.setResizable(false);
        //设置窗口的标题
        myFrame.setTitle("郑博豪的超级马里奥");
        //加载图片
        Constant.initImage();
        //创建全部的场景
        for (int i = 1; i <= 3; i++) {
            //I代表的是当前的关卡
            myFrame.getBackGrounds().add(new BackGround(i));//level等于123
        }
        //设置当前场景
        myFrame.setCurrent_backGround(myFrame.getBackGrounds().get(0));//集合中的第几个图
        //创建马里奥
        Mario mario=new Mario(20,510);
        myFrame.setMario(mario);
        mario.setBg(myFrame.getBackGrounds().get(0));
        //绘制场景
        myFrame.repaint();
        //开启线程
        Thread t=new Thread(myFrame);
        t.start();
    }
}
