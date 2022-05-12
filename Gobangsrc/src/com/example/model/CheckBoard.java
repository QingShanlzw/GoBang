package com.example.model;

import com.example.util.constant;

//画棋盘
public class CheckBoard {
    public static Node[][] CheckBoard=new Node[20][20];
    static {
        for(int i=0;i<CheckBoard.length;i++){
            for(int j=0;j<CheckBoard[i].length;j++){
                CheckBoard[i][j]=new Node((j+1)* constant.NODE_W,(i+1)*constant.NODE_W,new Position(i,j));

            }
        }
    }
}
