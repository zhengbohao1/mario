package util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class Constant {
    //窗口放一个背景
    public static BufferedImage bg;
    public static BufferedImage bg2;
    public static BufferedImage bg3;
    public static BufferedImage bg4;
    //马里奥动作图片
    //马里奥右跳动作
    public static BufferedImage jumpR;
    //马里奥左跳动作
    public static BufferedImage jumpL;
    //马里奥左边站立
    public static BufferedImage standL;
    //右边站立
    public static BufferedImage standR;
    //public static BufferedImage bed;
    //左跑
    public static List<BufferedImage> runL=new ArrayList<>();
    //右跑
    public static List<BufferedImage> runR=new ArrayList<>();
    //为障碍物定义一个集合
    public static List<BufferedImage> obstacles=new ArrayList<>();
    //放城堡
    //public static BufferedImage castle;
    //加载到系统里面
    //定义一个变量，记录文件的前缀
    public static String prefix="src/image/";

    public static List<BufferedImage> turtle_enemies=new ArrayList<>();//乌龟
    public static List<BufferedImage> flower_enemies=new ArrayList<>();//食人花
    public static BufferedImage ci_enermy;
    public static BufferedImage bullet_enermy;

    //初始化图片到系统中
    public static void initImage() throws IOException {
        //加载背景图片
        //;
        //bg= ImageIO.read(Constant.class.getResource(prefix + "firststage.jpg"));
        bg2= ImageIO.read(new File(prefix+"background2.jpg"));
        bg3= ImageIO.read(new File(prefix+"background3.jpg"));
        bg4= ImageIO.read(new File(prefix+"background4.jpg"));
        //castle=ImageIO.read(new File(prefix+"ob13.png"));
        //bed=ImageIO.read(new File(prefix+"ob15.png"));
        //加载马里奥右跳图片
        jumpR=ImageIO.read(new File(prefix+"2.png"));
        //加载马里奥左跳图片
        jumpL=ImageIO.read(new File(prefix+"7.png"));
        standL=ImageIO.read(new File(prefix+"6.png"));
        standR=ImageIO.read(new File(prefix+"4.png"));
        runL.add(ImageIO.read(new File(prefix+"8.png")));
        runL.add(ImageIO.read(new File(prefix+"10.png")));
        runR.add(ImageIO.read(new File(prefix+"2.png")));
        runR.add(ImageIO.read(new File(prefix+"5.png")));
        ci_enermy=ImageIO.read(new File(prefix+"ci.png"));
        bullet_enermy=ImageIO.read(new File(prefix+"bullet.png"));
        //加载障碍物的图片
        for (int i=1;i<=16;i++){
            //集合的第一个元素是不可以被破坏的
            //集合的第三块是可以被破坏的
            obstacles.add(ImageIO.read(new File(prefix+"ob"+i+".png")));
        }
        //加载敌人
        for (int i=1;i<=5;i++){
            turtle_enemies.add(ImageIO.read(new File(prefix+"turtle"+i+".png")));
        }
        for (int i=1;i<=2;i++){
            flower_enemies.add(ImageIO.read(new File(prefix+"flower"+i+".png")));
        }

    }

}
