/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clock;

/**
 *
 * @author 226797
 */
public class Clock {
    public static void main(String[] args) {
      
        //启动music格式，可以换曲子,1~5
        //长曲子启动满2s才动
        /*MusicDialog s = new MusicDialog(1);
        s.setVisible(true);*/
        NumClockThread temp = new NumClockThread();
        Thread th = new Thread(temp);
        th.start();
        System.out.println("Hello World!");
    }
  
}
