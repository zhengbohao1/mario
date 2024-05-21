package beans;

import util.Constant;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {
    //记录当前场景需要的图片
    private BufferedImage bg;
    //记录当前在哪个关卡
    private int level;

    private List<Enemy>enemies=new ArrayList<>();

    //创建一个集合，用于存放障碍物
    private List<Obstacle> obstacles=new ArrayList<>();

    public boolean isIsreachflag() {
        return isreachflag;
    }

    public void setIsreachflag(boolean isreachflag) {
        this.isreachflag = isreachflag;
    }

    private boolean isreachflag=false;

    public boolean isIsbase() {
        return isbase;
    }

    public void setIsbase(boolean isbase) {
        this.isbase = isbase;
    }

    //判断旗子是否落地
    private boolean isbase=false;

    public BackGround(){

    }
    public BackGround(int level)   {
        this.level=level;
        //准备背景图

        //关卡障碍物的设置
        if(level==1){
            bg= Constant.bg2;
            //当前是第一关
            for(int i=0;i<=900;i=i+30){
                obstacles.add(new Obstacle(i,540,8,this));
                obstacles.add(new Obstacle(i,570,6,this));
                //obstacles.add(new Obstacle(i,30,13,this));
            }
            //绘制一把枪
            obstacles.add(new Obstacle(0,260,15,this));
            //绘制子弹
            enemies.add(new Enemy(60,270,300,270,4,this));
            enemies.add(new Enemy(200,270,300,270,4,this) );
            //绘制豆子
            obstacles.add(new Obstacle(100,450,7,this));
            obstacles.add(new Obstacle(130,450,1,this));
            obstacles.add(new Obstacle(160,450,7,this));
            obstacles.add(new Obstacle(190,450,1,this));

            obstacles.add(new Obstacle(340,360,1,this));
            obstacles.add(new Obstacle(370,360,1,this));
            obstacles.add(new Obstacle(400,360,1,this));
            //放一个城墙
            //基础的x为450，第一层往右加4个30
            //第二层的基础x为480，第二层往右加3个30
            //第三层的基础x为510，第三层往右加2个30
            for(int i=1;i<=4;i++){
                for(int j=1;j<=5-i;j++){
                    obstacles.add(new Obstacle(250+(i-1)*30+(j-1)*30,510-(i-1)*30,7,this));
                }
            }
            //绘制天上的平面1
            for(int i=450;i<=830;i=i+30){
                obstacles.add(new Obstacle(i,360,6,this));
            }
            //绘制天上的平面2（左）
            for(int i=0;i<=400;i=i+30){
                obstacles.add(new Obstacle(i,300,6,this));
            }
            enemies.add(new Enemy(50,305,305,3,this));
            enemies.add(new Enemy(200,305,305,3,this));
            //绘制天上的平台3（右）
            for(int i=450;i<=830;i=i+30){
                obstacles.add(new Obstacle(i,240,6,this));
            }
            for(int i=620;i<=850;i+=200){
                enemies.add(new Enemy(i,245,245,3,this));
            }
            //绘制天上的豆1
            obstacles.add(new Obstacle(680,280,7,this));
            obstacles.add(new Obstacle(710,280,1,this));
            obstacles.add(new Obstacle(740,280,7,this));
            obstacles.add(new Obstacle(770,280,1,this));
            //绘制天上的豆2
            obstacles.add(new Obstacle(130,230,7,this));
            obstacles.add(new Obstacle(160,230,1,this));
            obstacles.add(new Obstacle(190,230,7,this));
            obstacles.add(new Obstacle(220,230,1,this));
            //绘制天上的豆3
            obstacles.add(new Obstacle(650,150,7,this));
            obstacles.add(new Obstacle(680,150,1,this));
            obstacles.add(new Obstacle(710,150,7,this));
            obstacles.add(new Obstacle(740,150,1,this));
            //绘制蹦蹦床
            obstacles.add(new Obstacle(370,510,14,this));
            //绘制三角块
            for(int i=1;i<=4;i++){
                for(int j=1;j<=5-i;j++){
                    obstacles.add(new Obstacle(400+(j-1)*30,510-(i-1)*30,7,this));
                }
            }
            //2水管的描绘，底部
            obstacles.add(new Obstacle(570,530,0,this));
            obstacles.add(new Obstacle(600,530,9,this));
            obstacles.add(new Obstacle(570,500,0,this));
            obstacles.add(new Obstacle(600,500,9,this));
            //2水管的顶部
            obstacles.add(new Obstacle(565,470,2,this));
            obstacles.add(new Obstacle(575,470,2,this));
            obstacles.add(new Obstacle(605,470,4,this));

            enemies.add(new Enemy(700,510,true,1,this));
            //绘制食人花
            enemies.add(new Enemy(580,500,2,true,this,450,500));
        }
        else if(level==2){
            bg=Constant.bg4;
            for(int i=0;i<=900;i=i+30){
                obstacles.add(new Obstacle(i,540,8,this));
                obstacles.add(new Obstacle(i,570,6,this));
                obstacles.add(new Obstacle(i,30,13,this));
            }
            //绘制刺
            for(int i=0;i<=900;i+=200){
                enemies.add(new Enemy(i,35,35,3,this));
            }
            //绘制第一个递增
            for(int i=1;i<=4;i++){
                for(int j=1;j<=5-i;j++){
                    obstacles.add(new Obstacle(100+(i-1)*30+(j-1)*30,510-(i-1)*30,7,this));
                }
            }
            //绘制一层平台
            for(int i=250;i<=395;i=i+30){
                obstacles.add(new Obstacle(i,360,6,this));
            }
            //绘制蹦蹦床
            obstacles.add(new Obstacle(230,510,14,this));
            obstacles.add(new Obstacle(420,510,14,this));
            //obstacles.add(new Obstacle(420,510,3,this));
            //绘制第二个递减城墙
            for(int i=1;i<=4;i++){
                for(int j=1;j<=5-i;j++){
                    obstacles.add(new Obstacle(450+(j-1)*30,510-(i-1)*30,7,this));
                }
            }
            //放一个乌龟
            enemies.add(new Enemy(430,510,true,1,this));

            //绘制豆子
            obstacles.add(new Obstacle(290,270,7,this));
            obstacles.add(new Obstacle(320,270,1,this));
            obstacles.add(new Obstacle(350,270,7,this));
            //绘制水管
            //2水管的描绘，底部
            obstacles.add(new Obstacle(590,530,0,this));
            obstacles.add(new Obstacle(620,530,9,this));
            obstacles.add(new Obstacle(590,500,0,this));
            obstacles.add(new Obstacle(620,500,9,this));
            //2水管的顶部
            obstacles.add(new Obstacle(585,470,2,this));
            obstacles.add(new Obstacle(595,470,2,this));
            obstacles.add(new Obstacle(625,470,4,this));
            //绘制第三个递增
            for(int i=1;i<=4;i++){
                for(int j=1;j<=5-i;j++){
                    obstacles.add(new Obstacle(680+(i-1)*30+(j-1)*30,510-(i-1)*30,7,this));
                }
            }
            //绘制第二层的平台
            for(int i=450;i<=750;i=i+30){
                obstacles.add(new Obstacle(i,300,6,this));
            }
            //绘制第二层的豆子
            obstacles.add(new Obstacle(550,220,7,this));
            obstacles.add(new Obstacle(580,220,1,this));
            obstacles.add(new Obstacle(610,220,7,this));
            //绘制食人花
            enemies.add(new Enemy(600,500,2,true,this,450,500));
            //再来平台
            for(int i=0;i<=110;i=i+30){
                obstacles.add(new Obstacle(i,400,6,this));
            }
            obstacles.add(new Obstacle(60,320,1,this));
        }
        else if(level==3){
            bg=Constant.bg3;
            for(int i=0;i<=900;i=i+30){
                obstacles.add(new Obstacle(i,540,8,this));
                obstacles.add(new Obstacle(i,570,6,this));
            }
            obstacles.add(new Obstacle(640,300,10,this));
            obstacles.add(new Obstacle(745,320,11,this));
            obstacles.add(new Obstacle(780,455,12,this));
            //第一层递增
            for(int i=1;i<=5;i++){
                for(int j=1;j<=6-i;j++){
                    obstacles.add(new Obstacle(100+(i-1)*30+(j-1)*30,510-(i-1)*30,7,this));
                }
            }
            //绘制蹦蹦床
            obstacles.add(new Obstacle(250,480,14,this));
            obstacles.add(new Obstacle(250,510,7,this));
            obstacles.add(new Obstacle(510,480,14,this));
            obstacles.add(new Obstacle(510,510,7,this));
            //绘制天上的平台
            for(int i=0;i<=280;i=i+30){
                obstacles.add(new Obstacle(i,280,6,this));
            }
            //绘制枪
            obstacles.add(new Obstacle(0,240,15,this));
            //绘制子弹
            enemies.add(new Enemy(60,250,200,270,4,this));
            //绘制小方块
            obstacles.add(new Obstacle(300,390,7,this));
            obstacles.add(new Obstacle(380,390,7,this));
            obstacles.add(new Obstacle(460,390,7,this));
            obstacles.add(new Obstacle(340,330,7,this));
            obstacles.add(new Obstacle(420,330,7,this));
            //绘制第一个递减
            for(int i=1;i<=5;i++){
                for(int j=1;j<=6-i;j++){
                    obstacles.add(new Obstacle(540+(j-1)*30,510-(i-1)*30,7,this));
                }
            }
            //绘制天上的平台2
            for(int i=340;i<=700;i=i+30){
                obstacles.add(new Obstacle(i,230,6,this));
            }
            //绘制天上的平台3
            for(int i=0;i<=900;i=i+30){
                obstacles.add(new Obstacle(i,50,13,this));
            }
            //绘制豆子
            for(int i=10;i<=220;i+=100){
                obstacles.add(new Obstacle(i,200,1,this));
            }
            for(int i=400;i<=705;i+=120){
                obstacles.add(new Obstacle(i,150,1,this));
            }
            enemies.add(new Enemy(430,510,true,1,this));
            enemies.add(new Enemy(430,510,false,1,this));
            enemies.add(new Enemy(70,80,80,3,this));
            enemies.add(new Enemy(170,80,80,3,this));
            enemies.add(new Enemy(450,80,80,3,this));
            enemies.add(new Enemy(600,80,80,3,this));
        }
    }
    public BufferedImage getBg() {
        return bg;
    }


    public int getLevel() {
        return level;
    }


    public List<Obstacle> getObstacles() {
        return obstacles;
    }


    public List<Enemy> getEnemies() {
        return enemies;
    }

}
