/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clock;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
 *
 * @author 226797
 */
public class MusicThread implements Runnable{
    //使用多线程完成音频的播放
    //单线程播放音乐两个以上时会出现问题
    private String filepath;
    private boolean loop = true;
    Clip clip;
    public MusicThread(int x){
        switch(x){
            case 1:
                filepath = "./src/main/java/com/mycompany/clock/music/Dvalin_Is_Landing.wav";
                break;
            case 2:
                filepath = "./src/main/java/com/mycompany/clock/music/A_Sparky_Clash.wav";
                break;
            case 3:
                filepath =  "./src/main/java/com/mycompany/clock/music/Hunt_for_Delicacy.wav";
                break;
            case 4:
                filepath = "./src/main/java/com/mycompany/clock/music/what.wav";
                break;
            case 5:
                filepath = "./src/main/java/com/mycompany/clock/music/pain.wav";
                break;
            case 6:
                filepath =  "./src/main/java/com/mycompany/clock/music/en.wav";
                break;
            default:
                break;
        }
    }
    public void setLoop(boolean loop) {
        this.loop = loop;
    }
    public void stopThread(){
        this.loop = false;
        clip.stop();
        clip.close();
    }
    @Override
    public void run(){
        try{
            File musicFile = new File(filepath);
            if (musicFile.exists()) {
            // 文件夹已存在
                System.out.println("文件夹已存在");
            } else {
             // 文件夹不存在
             System.out.println("文件夹不存在");
                }
            AudioInputStream ais = AudioSystem.getAudioInputStream(musicFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.start();
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex){
            ex.printStackTrace();
            Logger.getLogger(MusicThread.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
}/*在run()方法中，我们首先通过AudioSystem.getAudioInputStream()方法获取音频输入流，
然后使用AudioSystem.getClip()方法创建一个音频剪辑，接着打开剪辑并播放音乐，最后停止播放并关闭剪辑。
调用时，创建了一个MusicPlayer对象，并创建一个Thread对象，将MusicPlay对象作为参数传入，
然后调用start()方法启动线程。
        MusicThread musicPlayer = new MusicThread(number);
        Thread musicThread = new Thread(musicPlayer);
        musicThread.start();
    }*/
