package beans;

import util.Action;
import util.Constant;

import java.awt.image.BufferedImage;
import java.util.Iterator;

public class Mario implements Runnable{
    //记录马里奥的坐标信息
    private int x;
    private int y;
    //判断是否到门口承包
    private  boolean iscastel=false;
    //表示分数
    private int score=0;
    private boolean isdeath=false;
    //记录马里奥的状态
    private String status;
    //定义一张图片记录马里奥当前对应的动作
    private BufferedImage image;
    //创建一个变量记录当前的背景
    private BackGround bg=new BackGround();
    //创建线程的方式创建马里奥
    private Thread thread;
    //定义变量，记录马里奥的移动速度
    private int xspeed;
    private int yspeed;//跳跃速度
    //定义变量，记录马里奥的上升状态
    private int up;
    //定义图片的索引，针对左移动和右移动
    private int index;
    private int uptime=0;
    //判断是否可以起飞
    private boolean isfly=false;
    public Mario(){

    }
    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        //默认站在右边
        status= Action.STAND_RIGHT;
        image= Constant.standR;
        thread=new Thread(this);
        thread.start();
    }
    public void death(){
        isdeath=true;
    }
    //马里奥的向左移动的方法
    public void runLeft(){
        //判断当前是否为跳跃状态
        if(!status.contains("jump")){
            status=Action.RUN_LEFT;
        }
        else{
            status=Action.JUMP_LEFT;
        }
        xspeed=-5;
        if(bg.isIsreachflag()){
            xspeed=0;
        }
    }
    //马里奥向右移动的方法
    public void runRight(){
        if(!status.contains("jump")){
            status=Action.RUN_RIGHT;
        }
        else{
            status=Action.JUMP_RIGHT;
        }
        xspeed=5;
        if(bg.isIsreachflag()){
            xspeed=0;
        }
    }
    //马里奥向左移动停止的方法
    public void stopLeft(){
        if(!status.contains("jump")){
            status=Action.STOP_LEFT;
        }
        else{
            status=Action.JUMP_LEFT;//继续左跳，不要停。
        }
        xspeed=0;
    }
    //马里奥向右移动停止的方法
    public void stopRight(){
        if(!status.contains("jump")){
            status=Action.STOP_RIGHT;
        }
        else{
            status=Action.JUMP_RIGHT;
        }
        xspeed=0;
    }
    public void jump(){
        if(!status.contains("jump")){
            if(status.contains("right")){
                status=Action.JUMP_RIGHT;
            }
            else{
                status=Action.JUMP_LEFT;
            }
            yspeed=-10;
            uptime=7;//即最后上升70个系统单位
        }
        if(bg.isIsreachflag()){
            yspeed=0;
        }
   }

   public void fall(){
        if(status.contains("left")){
            status=Action.JUMP_LEFT;//把图片设置为左跳的图片，并不是代表又向左往上跳
        }
        else {
            status=Action.JUMP_RIGHT;
        }
        yspeed=10;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public BackGround getBg() {
        return bg;
    }

    public void setBg(BackGround bg) {
        this.bg = bg;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    public int getXspeed() {
        return xspeed;
    }

    public void setXspeed(int xspeed) {
        this.xspeed = xspeed;
    }

    public int getYspeed() {
        return yspeed;
    }

    public boolean isIsdeath() {
        return isdeath;
    }

    public int getScore() {
        return score;
    }

    public void setIsdeath(boolean isdeath) {
        this.isdeath = isdeath;
    }

    public void setYspeed(int yspeed) {
        this.yspeed = yspeed;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }
    public boolean isIscastel() {
        return iscastel;
    }
    @Override
    public void run() {
        //控制马里奥无线移动
        while(true){
            //判断是否在障碍物上
            boolean flag=false;
            //判断是否可以向右走
            boolean canright=true;
            //是否可以往左走
            boolean canleft=true;
            //判断马里奥是否到达旗杆处
            if(bg.getLevel()==3&&this.x>=745){
                this.bg.setIsreachflag(true);
                //判断是否旗子已经落地
                if(this.bg.isIsbase()){
                    status=Action.RUN_RIGHT;
                    //在800放一个城堡
                    if(this.x<800){
                        x+=5;
                        //不用xspeed，而是用这里直接+=5
                    }
                    else{
                        iscastel=true;
                    }
                }
                else{
                    if(y<510){
                        xspeed=0;
                        this.y+=5;
                        status=Action.JUMP_RIGHT;
                    }
                    if(y>=510){
                        xspeed=0;
                        this.y=510;
                        status=Action.STAND_RIGHT;
                    }
                }
            }
            else{
                //遍历当前场景内所有的障碍物
                Iterator<Obstacle> iterator = bg.getObstacles().iterator();
                while(iterator.hasNext()){
                    Obstacle o = iterator.next();
                    //判断当前马里奥是否在障碍物上
                    if(o.getY()==this.y+30&&(o.getX()>this.x-30&&o.getX()<this.x+25)){
                        if(o.getType()==14)isfly=true;
                        flag=true;
                        //break;
                    }
                    //判断是否跳起来顶到砖块
                    if(o.getY()>=this.y-30&&o.getX()>this.x-30&&o.getX()<this.x+25&&o.getY()<=this.y-20){
                        if(o.getType()==1){
                            iterator.remove();
                            score+=1;
                        }
                        uptime=0;
                    }
                    if(isfly==true){
                        uptime=10;
                        yspeed=-10;
                        isfly=false;
                    }
                    //判断马里奥是否可以往右走,障碍物是否阻挡
                    if(o.getX()==this.x+25&&o.getY()>this.y-30&&o.getY()<this.y+25){
                        canright=false;
                    }
                    if(o.getX()==this.x-30&&o.getY()>this.y-30&&o.getY()<this.y+25){
                        canleft=false;
                    }
                }
                //判断是否杀死敌人或者被杀死
                Iterator<Enemy> iterator2 = bg.getEnemies().iterator();
                while(iterator2.hasNext()){
                    Enemy e = iterator2.next();
                    if(e.getY()==this.y+20&&(e.getX()-25<this.x&&e.getX()+35>=this.x)){
                        if(e.getType()==1){
                            e.death();
                            iterator2.remove();
                            score+=2;
                            uptime=3;
                            yspeed=-10;
                        }
                        else if(e.getType()==2){
                            //马里奥死亡
                            death();
                        }
                    }
                    if(e.getX()+20>=this.x&&e.getX()-20<this.x&&e.getY()+20>=this.y&&e.getY()-20<this.y){
                        death();
                    }
                }
                //进行马里奥跳跃的操作
                if(flag&&uptime==0){//跳完了，或者说跳的次数为0
                    if(status.contains("left")){
                        if(xspeed!=0){
                            status=Action.RUN_LEFT;
                        }
                        else{
                            status=Action.STOP_LEFT;
                        }
                    }
                    else{
                        if(xspeed!=0){
                            status=Action.RUN_RIGHT;
                        }
                        else{
                            status=Action.STOP_RIGHT;
                        }
                    }
                }
                else{//准备跳跃或者向下降落，或者下面没有障碍物
                    if(uptime>0){
                        uptime--;
                    }
                    else{//跳完了，开始降落，或者uptime=0但是flag等于false，就是下面没有路，它就往下掉。
                        fall();
                    }
                    y+=yspeed;//yspeed有正负
                    //yspeed+=1;
                }
            }

            //判断当前是否移动，xspeed<0则左移动，xspeed>0则右移动
            if((canleft&&xspeed<0)||(xspeed>0&&canright)){
                x+=xspeed;
                //判断是否到达左边界
                if(x<0){
                    x=0;
                }
            }
            //先判断状态，是否为移动
            if(status.contains("run")){
                index=index==0?1:0;
            }
            //根据马里奥的状态设置不同的图片
            if(Action.RUN_LEFT.equals(status)){
                image=Constant.runL.get(index);
            }
            if(Action.RUN_RIGHT.equals(status)){
                image=Constant.runR.get(index);
            }
            if(Action.STOP_LEFT.equals(status)){
                image=Constant.standL;
            }
            if(Action.STOP_RIGHT.equals(status)){
                image=Constant.standR;
            }
            if (Action.JUMP_LEFT.equals(status)){
                image=Constant.jumpL;
            }
            if (Action.JUMP_RIGHT.equals(status)){
                image=Constant.jumpR;
            }
            //我们需要控制一下线程的速度
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
