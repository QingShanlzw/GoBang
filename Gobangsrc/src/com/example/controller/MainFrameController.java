package com.example.controller;

import com.example.model.CheckBoard;
import com.example.model.Node;
import com.example.model.Position;
import com.example.model.Stamp;
import com.example.util.constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.SplittableRandom;

//主界面操作的处理
public class MainFrameController {

    /*
    选择先手，开始游戏
    判断点击的坐标是那一个位置【白棋、黑棋】
    修改状态，开始游戏
     */
    public void chooseStart(Point point) {
        //选择了白棋，白棋先下
        if(constant.WHITE_ICON_X<= point.x && point.x<=constant.WHITE_ICON_X+constant.ICON_W
        && constant.WHITE_ICON_Y<= point.y && point.y<=constant.WHITE_ICON_Y+constant.ICON_H){
            Stamp.setGameStart(true);
            Stamp.setWhich(1);
            return;

        }
        //选择了黑棋，黑棋先下
        if(constant.BLACK_ICON_X<= point.x && point.x<=constant.BLACK_ICON_X+constant.ICON_W
                && constant.BLACK_ICON_Y<= point.y && point.y<=constant.BLACK_ICON_Y+constant.ICON_H){
            Stamp.setGameStart(true);
            Stamp.setWhich(-1);
            return;

        }

    }

    /*
    落子操作
    改变状态
     */
    public boolean placing(Point point) {
        Node node=findNode(point);
        if(node!=null){
            node.setValue(Stamp.getWhich());
            if(node.getValue()==constant.WHITE){
                node.setPath(constant.WHITE_PATH);
            }else if(node.getValue()==constant.BLACK){
                node.setPath(constant.BLACK_PATH);
            }
            Stamp.setCurrentNode(node);
            if (isWin()) {
                Stamp.setWin(true);//设置标记

            }else {
                Stamp.setWhich(Stamp.getWhich()*-1);
            }
            return true;
        }
        return false;
    }

    //找合适落子的位置
    //遍历棋盘
    private Node findNode(Point point) {
        for (Node[] nodes : CheckBoard.CheckBoard) {
            for (Node node : nodes) {
                if(node.clickNode(point.x, point.y) && node.getValue()==constant.NONE){
                    return node;
                };

            }

        }
        return null;
    }
    /*
    判断是否胜利
     */
    private boolean isWin(){
        if(horizon() || vertical() || lurd() || ldru()){
            return true;
        }
        return false;
    }
    /*
    水平方向判断
     */
    private boolean horizon(){
        Node node=Stamp.getCurrentNode();
        Position position=Stamp.getCurrentNode().getPosition();
        int fd=frotDis(position.getCol());
        int bd=backDis(position.getCol());

        int start=position.getCol()-fd;
        int end=position.getCol()+bd;

        int row=position.getRow();

        int star=0;//记录棋子个数
        for(int i=start;i<=end;i++){
            if(node.getValue()!=CheckBoard.CheckBoard[row][i].getValue()){
                star=0;
            }else{
                star++;
                if(star==5){
                    return true;
                }
            }
        }
        return false;

    }
    /*
    竖直方向判断
     */
    private boolean vertical(){
        Node node=Stamp.getCurrentNode();
        Position position=Stamp.getCurrentNode().getPosition();
        int fd=frotDis(position.getRow());
        int bd=backDis(position.getRow());

        int start=position.getRow()-fd;
        int end=position.getRow()+bd;

        int col=position.getCol();

        int star=0;
        for(int i=start;i<=end;i++){
            if(node.getValue()!=CheckBoard.CheckBoard[i][col].getValue()){
                star=0;
            }else{
                star++;
                if(star==5){
                    return true;
                }
            }
        }
        return false;
    }
    /*
    左上右下判断
     */
    private boolean lurd(){
        Node node=Stamp.getCurrentNode();
        Position position=Stamp.getCurrentNode().getPosition();

        //行的前面距离
        int fdRow=frotDis(position.getRow());
        //行的后边距离
        int bdRow=backDis(position.getRow());
        //列的前面距离
        int fdCol=frotDis(position.getCol());
        //列的后面距离
        int bdCol=frotDis(position.getCol());

        //取行和列中的小的一方的值
        int fd=Math.min(fdRow,fdCol);
        int bd=Math.min(bdCol,bdRow);

        int startRow=position.getRow()-fd;
        int endRow=position.getRow()+bd;
        int startCol=position.getCol()-fd;
        int endCol=position.getCol()+bd;

        //开始从左上角遍历
        int star=0;
        for(int row=startRow,col=startCol;row<=endRow && col<=endCol;row++,col++){
            if(node.getValue()!=CheckBoard.CheckBoard[row][col].getValue()){
                star=0;
            }else{
                star++;
                if(star==5){
                    return true;
                }
            }
        }
        return false;
    }

    /*
    左下右上胜利判断
     */
    private boolean ldru(){
        Node node=Stamp.getCurrentNode();
        Position position=Stamp.getCurrentNode().getPosition();

        //行的前面距离
        int fdRow=frotDis(position.getRow());
        //行的后边距离
        int bdRow=backDis(position.getRow());
        //列的前面距离
        int fdCol=frotDis(position.getCol());
        //列的后面距离
        int bdCol=frotDis(position.getCol());

        int fd=Math.min(fdRow,bdCol);
        int bd=Math.min(fdCol,bdRow);

        int startRow=position.getRow()-fd;
        int endRow=position.getRow()+bd;
        int startCol=position.getCol()+fd;
        int endCol= position.getCol()-bd;

        int star=0;
        for(int row=startRow,col=startCol;row<=endRow && col>=endCol;row++,col--){
            if(node.getValue()!=CheckBoard.CheckBoard[row][col].getValue()){
                star=0;
            }else {
                star++;
                if (star==5){
                    return true;
                }
            }
        }
        return false;


    }
    //通过中间值去找起始距离
    private int frotDis(int mid){
        return mid>4?4:mid;
    }
    private int backDis(int mid){
        int len=CheckBoard.CheckBoard.length;
        return mid+4<len?4:len-mid-1;
    }

    //将所有的标记重置
    public void init() {
        Stamp.setWhich(constant.NONE);
        Stamp.setGameStart(false);
        Stamp.setCurrentNode(null);
        for (Node[] nodes : CheckBoard.CheckBoard) {
            for (Node node : nodes) {
                node.setPath(null);
                node.setValue(0);
            }

        }
    }

    public boolean replay(Point point) {
        if(constant.RESTART_ICON_X<=point.x && point.x<=constant.RESTART_ICON_X+constant.ICON_W
        && constant.RESTART_ICON_Y<= point.y && point.y<=constant.RESTART_ICON_Y+constant.ICON_H){
            Stamp.setWin(false);
            return true;
        }
        return false;
    }

    //给出胜利提示
    public void msg(Graphics g) throws IOException {
        String str=null;
        if(Stamp.getWhich()==constant.WHITE){
            str="恭喜白棋胜利！！";
        }else if(Stamp.getWhich()==constant.BLACK){
            str="恭喜黑棋胜利！！";
        }
        Image winImg= ImageIO.read(new File(constant.WIN_ICON_PATH));
        g.drawImage(winImg,constant.WIN_ICON_X,constant.WIN_ICON_Y,constant.WIN_ICON_W,constant.WIN_ICON_H,null);
        g.setFont(new Font("微软雅黑",Font.BOLD,30));
        g.drawString(str,250,330);
    }
}
