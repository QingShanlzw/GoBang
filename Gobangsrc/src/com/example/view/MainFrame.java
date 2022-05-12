package com.example.view;

import com.example.controller.MainFrameController;
import com.example.model.Stamp;
import com.example.util.constant;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

//主界面
public class MainFrame extends BaseFrame{
    //创建处理器
    MainFrameController mainFrameController=new MainFrameController();
    public MainFrame() {
        //添加鼠标监听事件
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point=e.getPoint();//鼠标当前点击的位置
                if(!Stamp.isGameStart()){
                    if(!Stamp.isWin()){
                        //选先手，开始游戏
                        mainFrameController.chooseStart(point);
                    }else{
                        if(mainFrameController.replay(point)){
                            repaint();
                        }
                    }

                } else {
                    //游戏开始了，落子
                    if(mainFrameController.placing(point)){
                        repaint();
                    }

                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        if(!Stamp.isGameStart()){
            //清空窗口内容
            g.clearRect(0,0, constant.WINDOW_W,constant.WINDOW_H);
            super.paint(g);
        }
        if(Stamp.isGameStart() && Stamp.getCurrentNode()!=null){
            //绘制当前落子
            Stamp.getCurrentNode().playNode(g);
            if(Stamp.isWin()){
                try {
                    mainFrameController.msg(g);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //处理赢的标记，其余全部回到最初
                mainFrameController.init();
            }
        }

    }
}
