/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clock;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.DayOfWeek;  
/**
 *
 * @author 226797
 */
public class NumClockThread implements Runnable{
    int year;
    int month;
    int day;
    Calendar calendar;
    int hour;
    int minute;
    int second;
    int dayOfWeek;
    boolean flag = false;
    String time;
    NumClock NC = null;//UI窗口界面空对象
    TimeZone zone;

    public NumClockThread(){
        NC = new NumClock();
        NC.setVisible(true);
    }
    public void alarmclock(){
        readAlarm a = new readAlarm();
        alarm tmp = new alarm(hour,minute);
        int x = binarySearch(a.nt,tmp);//二分法查找是否存在相等的数据
        if(x == -1) return;//没找到
        tmp = a.nt[x];
        if(tmp.frequency.equals("每天")){
            for(int i = 0;i < 7; ++i)tmp.vb[i] = 1;
        }
        if(tmp.vb[dayOfWeek] == 0) return; //如果没选中该星期则不弹出
        if(tmp.frequency.equals("工作日")){
            if(dayOfWeek == 6 || dayOfWeek == 7) return;//六日不弹窗
        }
        if(tmp.frequency.equals("休息日")){
            if(dayOfWeek > 0 && dayOfWeek < 6) return;//工作日不弹窗
        }
        if(!flag){
            if(tmp.frequency.equals("一次"))
                 for(int i = 0;i < 7; ++i)tmp.vb[i] = 0;
            flag = true;
            MusicDialog t = new MusicDialog(tmp.music);
            t.setVisible(true);
        }
        
    }
     public static int binarySearch(alarm[] array, alarm target) {  
        int left = 0;  
        int right = array.length - 1;  
        int result = -1; // 如果未找到，则返回-1  
  
        while (left <= right) {  
            int mid = left + (right - left) / 2;  
            if (array[mid].hour == target.hour && array[mid].minutes == target.minutes) {  
                result = mid; // 找到目标对象，记录其索引  
                right = mid - 1; // 在左半部分继续查找（因为可能有多个相同的对象）  
            } else if (array[mid].hour < target.hour || (array[mid].hour == target.hour && array[mid].minutes < target.minutes)) {  
                left = mid + 1; // 在右半部分继续查找  
            } else {  
                right = mid - 1; // 在左半部分继续查找  
            }  
        }  
        return result;  
    }  
     int x = 0;
    @Override
    public void run(){
        while(true){
            LocalDate today = LocalDate.now(); // 获取当前日期  
            ++x;
            DayOfWeek s = today.getDayOfWeek(); // 获取星期几
            dayOfWeek = today.getDayOfWeek().getValue(); // 获取星期几 1-7
            zone = NC.getZone();
            calendar = Calendar.getInstance(zone);
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH) + 1;
            day = calendar.get(Calendar.DATE);
            hour = calendar.get(Calendar.HOUR_OF_DAY);
             if(minute !=  calendar.get(Calendar.MINUTE)) flag =false;
            minute = calendar.get(Calendar.MINUTE);
            System.out.println(x);
            if(x == 30){ flag = false;x=0;}
            second = calendar.get(Calendar.SECOND);
            // 格式化时间
            time = String.format("%02d:%02d:%02d", hour, minute, second);
            NC.SendTime(year, month, day, hour, minute, second, time,s.toString());//初始化发数据过去
            alarmclock();
            try{
                 Thread.sleep(1000);//休息1秒，再来获取时间日期更新并同时GUI界面更新获取数据显示
            }catch(InterruptedException ex){
                Logger.getLogger(NumClockThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
