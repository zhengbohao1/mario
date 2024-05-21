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
    public Obstacle(){

    }

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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getObstacleImage() {
        return obstacleImage;
    }

    public void setObstacleImage(BufferedImage obstacleImage) {
        this.obstacleImage = obstacleImage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public BackGround getBg() {
        return bg;
    }

    public void setBg(BackGround bg) {
        this.bg = bg;
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
