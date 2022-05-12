package com.example.model;

import com.example.util.constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/*棋盘的每一个格子【棋子】
坐标(x,y)
当前棋子的值【0：没有棋子；-1：黑棋；1：白棋】
当前棋子的路径
当前在二维数组中的位置

画格子【画自己】
落子【画棋子】
判断点击是否是这个位置
 */
public class Node {
    //格子左上角坐标
    private int x;
    private int y;
    private int value;//棋子当前的值
    private String path; //棋子的图片路径
    private Position position;//棋子在二维数组中的位置

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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Position getPosition() {
        return position;
    }

    public Node(int x, int y, Position position) {
        this.x = x;
        this.y = y;
        this.position = position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }


    //画格子
    public void drawNode(Graphics g){
        g.setColor(new Color(0,0,0));
        g.drawRect(this.x,this.y, constant.NODE_W,constant.NODE_W);
    }
    //判断点击是否是当前格子落子的范围
    public boolean clickNode(int x,int y){
        int startX=this.x-constant.R;
        int endX=this.x+constant.R;
        int startY=this.y-constant.R;
        int endY=this.y+constant.R;
        if(x<=endX && x>=startX && y<=endY && y>=startY){
            return true;
        }
        return false;
    }
    //落子
    public void playNode(Graphics g){
        try {
            Image image= ImageIO.read(new File(this.path));
            g.drawImage(image,this.x-constant.PIECE_W/2,this.y-constant.PIECE_W/2,constant.PIECE_W,constant.PIECE_W,null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
