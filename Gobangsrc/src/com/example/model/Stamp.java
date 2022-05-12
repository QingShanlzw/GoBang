package com.example.model;

/*
状态类：
游戏是否开始
是谁落子
落子在何处
是谁赢了
 */
public class Stamp {
    private static boolean gameStart=false;//初始状态游戏并未开始
    private static int which;//当前给谁落子
    private static Node currentNode;//当前落子位置
    private static boolean win=false;

    public static boolean isGameStart() {
        return gameStart;
    }

    public static void setGameStart(boolean gameStart) {
        Stamp.gameStart = gameStart;
    }

    public static int getWhich() {
        return which;
    }

    public static void setWhich(int which) {
        Stamp.which = which;
    }

    public static Node getCurrentNode() {
        return currentNode;
    }

    public static void setCurrentNode(Node currentNode) {
        Stamp.currentNode = currentNode;
    }

    public static boolean isWin() {
        return win;
    }

    public static void setWin(boolean win) {
        Stamp.win = win;
    }
}
