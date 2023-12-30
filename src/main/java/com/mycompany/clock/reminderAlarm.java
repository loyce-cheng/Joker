package com.mycompany.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimeZone;

public class reminderAlarm extends JFrame{
    private String alarmTime;
    private Timer timer;
    private boolean isAlarmOn = false;
    JButton button1=new JButton("确定");
    JButton button2=new JButton("贪睡");
    Box vBox0=Box.createVerticalBox();
    Box hBox1=Box.createHorizontalBox();
    Box hbox0=Box.createHorizontalBox();
    Font font = new Font("宋体", Font.PLAIN, 40);
    Font font2 = new Font("宋体", Font.PLAIN, 25);
    JLabel label1=new JLabel("闹钟时间到！");
    public reminderAlarm(){
         snooze();
    }

    public void snooze() {

        // 当点击贪睡按钮时，关闭当前的提醒
        JOptionPane.showMessageDialog(this, "贪睡模式启动，10分钟后再次提醒。");
        button2.setEnabled(false); // 贪睡后，按钮再次不可用
        isAlarmOn = false;
        dispose();  // 关闭窗口
        // 设置10分钟后再次触发提醒
        
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (!isAlarmOn) { // 如果在10分钟内没有再次触发闹钟，则再次提醒
                    //闹钟功能
                }
            }
        }, 10 * 60 * 1000); // 10分钟后执行
    }
   
}
