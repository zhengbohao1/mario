package beans;

import util.Constant;

import java.awt.image.BufferedImage;


public class Enemy implements Runnable{
    //存储当前坐标
    private int x;
    private int y;
    //存储敌人类型
    private  int type;
    //type=1表示乌龟，type=2表示食人花
    //判断敌人运动方向
    private  boolean direction=true;
    //用于显示敌人的当前图像
    private BufferedImage image;
    //定义一个背景对象
    private BackGround bg;

    //食人花运动的极限范围
    private int max_y=0;
    private int min_y=0;

    private Thread thread=new Thread(this);

    private int image_type_turtle=0;
    private boolean ci_pengdi=false;

    private int before_y=0;

    private int before_x=0;
    private int jiange=0;

    //乌龟敌人的构造函数
    public Enemy(int x,int y,boolean direction,int type,BackGround bg){
        this.x=x;
        this.y=y;
        this.direction=direction;
        this.type=type;
        this.bg=bg;
        this.image=Constant.turtle_enemies.get(0);
        thread.start();
    }
    //食人花敌人的构造函数
    public Enemy(int x, int y, int type, boolean direction, BackGround bg, int max_y, int min_y) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.direction = direction;
        this.bg = bg;
        this.max_y = max_y;
        this.min_y = min_y;
        this.image=Constant.flower_enemies.get(0);
        thread.start();
    }
    //刺的构造函数
    public Enemy(int x, int y, int before_y,int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        this.image=Constant.ci_enermy;
        this.before_y=before_y;
        thread.start();
    }
    //子弹的构造函数
    public Enemy(int x, int y, int jiange, int before_y,int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        this.image=Constant.bullet_enermy;
        this.jiange=jiange;
        this.before_y=before_y;
        thread.start();
    }
    //创建死亡方法
    public void death(){
        this.image=Constant.turtle_enemies.get(4);
        //this.bg.getEnemies().remove(this);
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }


    public int getType() {
        return type;
    }

    public BufferedImage getImage() {
        return image;
    }


    @Override
    public void run() {
        while(true){
            //判断是否是乌龟
            if(type==1){
                if(direction){
                    this.x-=2;
                    image_type_turtle=image_type_turtle==1?0:1;
                    /*image= Constant.turtle_enemies.get(image_type_turtle);*/
                }else{
                    this.x+=2;
                    image_type_turtle=image_type_turtle==2?3:2;
               }
                image= Constant.turtle_enemies.get(image_type_turtle);
            }
            boolean canleft=true;
            boolean canright=true;

            for(int i=0;i<bg.getObstacles().size();i++){
                Obstacle ob=bg.getObstacles().get(i);
                //判断是否可以向右走
                if(ob.getX()==this.x+36&&(ob.getY()+65>this.y&&ob.getY()-25<this.y)){
                    canright=false;
                }
                //判断是否可以向左走
                if(ob.getX()==this.x-36&&(ob.getY()+65>this.y&&ob.getY()-25<this.y)){
                    canleft=false;
                }
            }

            if(direction&&!canleft||this.x==0){
                direction=false;
            }
            else if(!direction&&!canright||this.x==850){
                direction=true;
            }

            //判断是否是食人花
            if(type==2){
                if(direction){
                    this.y-=2;
                }
                else{
                    this.y+=2;
                }
                image_type_turtle=image_type_turtle==1?0:1;
                //判断食人花是否到达极限位置
                if(direction&&(this.y==max_y)){
                    direction=false;
                }
                else if(!direction&&(this.y==min_y)){
                    direction=true;
                }
                this.image=Constant.flower_enemies.get(image_type_turtle);
            }

            if(type==3){
                if(ci_pengdi){
                    this.y=this.before_y;
                    ci_pengdi=false;
                }
                else{
                    this.y+=5;
                }
                for(int i=0;i<bg.getObstacles().size();i++){
                    Obstacle ob=bg.getObstacles().get(i);
                    //判断下方是否有障碍物
                    if(ob.getX()>=this.x-30&&ob.getX()<=this.x+30&&ob.getY()<=this.y+30&&ob.getY()>=this.y){
                        ci_pengdi=true;
                        break;
                    }
                }
            }
            //是子弹
            if(type==4){
                if(x-before_x<=jiange){
                    this.x+=3;
                }
                else{
                    x=before_x;
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
