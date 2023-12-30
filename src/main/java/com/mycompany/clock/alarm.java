/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clock;

/**
 *
 * @author 226797
 */
public class alarm{
            public int[]vb = new int[7];
            public int hour;
            public int minutes;
            public String frequency ;
            public int music;
            public alarm(){
                hour = 0;
                minutes = 0;
                music = 1;
            }
            public alarm(int h, int m){
                hour = h;
                minutes = m;
            }
        }
