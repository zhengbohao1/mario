package beans;

import util.Constant;

import java.awt.image.BufferedImage;

public class Obstacle implements Runnable{
    //存放障碍物的信息
    //障碍物的坐标记录
    private int x;
    private int y;
    //定义一个变量记录当前障碍物的图片信息
    private BufferedImage obstacleImage;
    //定义障碍物的类型
    private int type;
    //定义变量存放当前的背景
    private BackGround bg;
    private  Thread thread=new Thread(this);

    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        //根据障碍物的类型，从Constant的障碍物集合中获取对应的图片
        this.obstacleImage= Constant.obstacles.get(type);
        if(type==11){
            thread.start();
        }
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public BufferedImage getObstacleImage() {
        return obstacleImage;
    }


    public int getType() {
        return type;
    }


    @Override
    public void run() {
        while(true){
            if(this.bg.isIsreachflag())
            {
                if(this.y<500){
                    this.y+=5;//旗子还没落地，+=5
                }
                else{
                    this.bg.setIsbase(true);//旗子已经落到地上
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
