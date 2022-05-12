package com.example.view;

import com.example.controller.BaseFrameController;
import com.example.util.constant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//显示初始内容
public class BaseFrame extends JFrame {
    BaseFrameController baseFrameController=new BaseFrameController();

    public BaseFrame(){
        super.setLocation(constant.WINDOW_X,constant.WINDOW_Y);//窗体位置
        super.setSize(constant.WINDOW_W,constant.WINDOW_H);//窗体宽高
        super.setTitle("五子棋");
        super.setBackground(new Color(154, 246, 224));//背景色
        super.setResizable(false);
        super.setVisible(true);
        super.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        baseFrameController.paintCheckBroad(g);
        baseFrameController.paintIcon(g);
    }
}
