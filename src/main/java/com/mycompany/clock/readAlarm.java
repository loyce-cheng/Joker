package com.mycompany.clock;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
    
    public final class readAlarm {
        public int cnt = 0;
        public int ccnt;
        public alarm[] nt;
        public void check1(){
            File file = new File("allAlarm.txt"); // 替换为您的文件路径
            try (Scanner scanner = new Scanner(file)) {
                 while (scanner.hasNextLine()) {
                     ccnt++;
                     String line = scanner.nextLine();
                     String[] data = line.split(",");
                     //System.out.println("当前闹钟序号为 " + ccnt);
                 }
                } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        public void sort(){
            int n = ccnt; 
            for (int i = 0; i < n - 1; i++) {  
                for (int j = 0; j < n - i - 1; j++) {  
                if (nt[j].hour > nt[j + 1].hour || (nt[j].hour == nt[j + 1].hour && nt[j].minutes > nt[j + 1].minutes)) {  
                    // 交换 nt[j] 和 nt[j+1]  
                    alarm temp = nt[j];  
                    nt[j] = nt[j + 1];  
                    nt[j + 1] = temp;  
                }  
            }  
        }  
        }
        public readAlarm(){
            check1();
            nt = new alarm[ccnt];
            for(int i = 0; i < ccnt; ++i){
                nt[i] = new alarm();
            }
            File file = new File("allAlarm.txt"); // 替换为您的文件路径
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] data = line.split(",");
                    if (data.length >= 4) { // 确保至少有4个数据项
                        nt[cnt].hour = Integer.parseInt(data[0]);
                        nt[cnt].minutes = Integer.parseInt(data[1]);
                        nt[cnt].frequency = data[2];
                        nt[cnt].music = Integer.parseInt(data[3]);
                        for(int i = 4; i < 11; ++i){
                            nt[cnt].vb[i-4] = Integer.parseInt(data[i]);
                        }
                        cnt++;
                        //System.out.println("当前闹钟序号为 " + cnt);
                       // System.out.println(nt[cnt].hour + " "+nt[cnt].minutes);
                        
                    } else {
                        System.out.println("Invalid data format: " + line);
                    }
                }
                sort();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }