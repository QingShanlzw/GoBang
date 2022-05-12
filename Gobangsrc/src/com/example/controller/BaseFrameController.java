package com.example.controller;

import com.example.model.CheckBoard;
import com.example.model.Node;
import com.example.util.constant;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/*基本界面处理：
绘制棋盘
绘制图标
 */
public class BaseFrameController {
    //画棋盘
    public void paintCheckBroad(Graphics g){
        for(Node[] nodes: CheckBoard.CheckBoard){
            for(Node node:nodes){
                node.drawNode(g);
            }
        }
    }
    //画图标
    public void paintIcon(Graphics g){
        try {
            Image whiteImg= ImageIO.read(new File(constant.WHITE_ICON));
            Image blackImg= ImageIO.read(new File(constant.BLACK_ICON));
            Image restartImg= ImageIO.read(new File(constant.RESTART_ICON));
            g.drawImage(whiteImg,constant.WHITE_ICON_X,constant.WHITE_ICON_Y,constant.ICON_W,constant.ICON_H,null);
            g.drawImage(blackImg,constant.BLACK_ICON_X,constant.BLACK_ICON_Y,constant.ICON_W,constant.ICON_H,null);
            g.drawImage(restartImg,constant.RESTART_ICON_X,constant.RESTART_ICON_Y,constant.ICON_W,constant.ICON_H,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
