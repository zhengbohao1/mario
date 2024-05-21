package beans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener,Runnable {
    //定义一个集合用于存放所有的背景
    private List<BackGround> backGrounds=new ArrayList<>();
    //定义一个变量，存放当前的背景
    private BackGround current_backGround=new BackGround();
    //定义变量，记录马里奥
    private Mario mario;

    public Mario getMario() {
        return mario;
    }

    public void setMario(Mario mario) {
        this.mario = mario;
    }
//重写paint方法，实现场景、物理的绘制

    @Override
    public void paint(Graphics g) {
        //创建一张图片
        Image image = createImage(900, 600);
        //设置图片
        Graphics graphics = image.getGraphics();
        //绘制图片
        graphics.drawImage(current_backGround.getBg(), 0, 0,900,600, this);
        //把敌人绘制出来
        for (Enemy enemy : current_backGround.getEnemies()) {
            graphics.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
        }
        //把障碍物绘制出来
        for (Obstacle obstacle : current_backGround.getObstacles()) {
            graphics.drawImage(obstacle.getObstacleImage(), obstacle.getX(), obstacle.getY(), this);
        }


        graphics.drawImage(mario.getImage(), mario.getX(), mario.getY(), this);
        Color c=graphics.getColor();
        graphics.setColor(Color.red);
        graphics.setFont(new Font("宋体",Font.BOLD,25));
        graphics.drawString("分数："+mario.getScore(), 400, 130);
        graphics.setColor(c);
        //描绘到当前窗口中
        g.drawImage(image, 0, 0,  this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==37){
            mario.runLeft();
        }
        if(e.getKeyCode()==39){
            mario.runRight();
        }
        if(e.getKeyCode()==38){
            mario.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==37){
            mario.stopLeft();
        }
        if (e.getKeyCode()==39){
            mario.stopRight();
        }
    }

    @Override
    public void run() {
        //无限次绘制马里奥
        while(true){
            repaint();
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //判断马里奥是否通关
            if(mario.getX()>900){
                current_backGround=backGrounds.get(current_backGround.getLevel());
                mario.setBg(current_backGround);//换了个背景
                mario.setX(20);//然后把马里奥放回起始点，实现闯关的感觉
                mario.setY(510);
            }
            if(mario.isIsdeath()){
                JOptionPane.showMessageDialog(this,"你挂了(bushi)");
                System.exit(0);
            }
            if(mario.isIscastel()){
                JOptionPane.showMessageDialog(this,"恭喜你通关了");
                System.exit(0);
            }
        }
    }

    public List<BackGround> getBackGrounds() {
        return backGrounds;
    }

    public void setBackGrounds(List<BackGround> backGrounds) {
        this.backGrounds = backGrounds;
    }

    public BackGround getCurrent_backGround() {
        return current_backGround;
    }

    public void setCurrent_backGround(BackGround current_backGround) {
        this.current_backGround = current_backGround;
    }
}
