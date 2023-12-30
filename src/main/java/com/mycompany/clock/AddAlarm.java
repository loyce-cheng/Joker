package com.mycompany.clock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;

public class AddAlarm extends JFrame {
    JPanel panel=new JPanel();
    String tempFrequency;
    String newAlarm;
    String backgroundMusic;
    int bgMusicChoice;
    Font font1=new Font("Serif", Font.BOLD, 40);
    Font font2=new Font("SansSerif", Font.ITALIC, 40);
    Font font = new Font("宋体", Font.PLAIN, 40);
    public JTextField hourField;//记录小时
    public JTextField minuteField;//记录分钟
    public JButton setAlarmButton;//确定设置按钮
    private JButton cancelButton;//确定取消按钮
    public JRadioButton workdayRadioButton;
    public JRadioButton weekendRadioButton;
    public JRadioButton everydayRadioButton;
    public JRadioButton onlyOnceRadioButton;
    public  JCheckBox[] checkBoxes;
    public JComboBox<String> comboBox;
    JLabel music=new JLabel("闹钟音乐");
    JLabel hour=new JLabel("时");
    JLabel minute=new JLabel("分");
    JLabel frequency=new JLabel("请选择频率           ");
    Box box1=Box.createHorizontalBox();//放时间
    Box box0=Box.createHorizontalBox();//选择一次、每天、工作日、休息日
    Box box2=Box.createHorizontalBox();//放按钮
    Box box3=Box.createHorizontalBox();//选择闹钟音乐
    public Box box4=Box.createHorizontalBox();//选择星期1~7
    private int[] variables = new int[7]; 
    private class CheckBoxListener implements ItemListener {  
        private int index;  
  
        public CheckBoxListener(int index) {  
            this.index = index;  
        }  
  
        @Override  
        public void itemStateChanged(ItemEvent e) {  
            if (e.getStateChange() == ItemEvent.SELECTED) {  
                variables[index] = 1; // 如果复选框被选中，对应的变量值为1  
                System.out.println("星期" + (index + 1) + " 的变量值为 " + variables[index]);  
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {  
                variables[index] = 0; // 如果复选框未被选中，对应的变量值为0  
                System.out.println("星期" + (index + 1) + " 的变量值为 " + variables[index]);  
            }  
        }  
    }  

    public AddAlarm() {
        for(int i = 0;i < 7; ++i){
            variables[i] = 0;
        }
        setTitle("添加闹钟(24小时制)");
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // 添加组件到面板
        box1.add(hourField = new JTextField(20)); // 初始化小时文本字段
        box1.add(hour);
        box1.add(minuteField = new JTextField(20)); // 初始化分钟文本字段
        hourField.setHorizontalAlignment(JTextField.CENTER);
        minuteField.setHorizontalAlignment(JTextField.CENTER);
        box1.add(minute);
        hourField.setFont(font);
        minuteField.setFont(font);
        frequency.setFont(font2);
        
        // 创建星期一到星期天的多选按钮，并将它们添加到相应的容器中
        checkBoxes = new JCheckBox[7];  
        checkBoxes[0] = new JCheckBox("星期一");  
        box4.add(checkBoxes[0]);  
        checkBoxes[1] = new JCheckBox("星期二");  
        box4.add(checkBoxes[1]);  
        checkBoxes[2] = new JCheckBox("星期三");  
        box4.add(checkBoxes[2]);  
        checkBoxes[3] = new JCheckBox("星期四");  
        box4.add(checkBoxes[3]);  
        checkBoxes[4] = new JCheckBox("星期五");  
        box4.add(checkBoxes[4]);  
        checkBoxes[5] = new JCheckBox("星期六");  
        box4.add(checkBoxes[5]);  
        checkBoxes[6] = new JCheckBox("星期天");  
        box4.add(checkBoxes[6]);
        workdayRadioButton = new JRadioButton("工作日");
        weekendRadioButton = new JRadioButton("休息日");
        everydayRadioButton = new JRadioButton("每天");
        onlyOnceRadioButton = new JRadioButton("一次");
        onlyOnceRadioButton.setSelected(true);
        for (JCheckBox checkBox : checkBoxes) {  
            checkBox.setFont(font);  
        }
        workdayRadioButton.setFont(font);
        weekendRadioButton.setFont(font);
        everydayRadioButton.setFont(font);
        onlyOnceRadioButton.setFont(font);
        box0.add(frequency);
        box0.add(onlyOnceRadioButton);
        box0.add(everydayRadioButton);
        box0.add(workdayRadioButton);
        box0.add(weekendRadioButton);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(onlyOnceRadioButton);
        buttonGroup.add(everydayRadioButton);
        buttonGroup.add(workdayRadioButton);
        buttonGroup.add(weekendRadioButton);
        

        // 创建下拉框并添加几个选项
        comboBox = new JComboBox<>();
        comboBox.setFont(font);
        comboBox.addItem("音乐1");
        comboBox.addItem("音乐2");
        comboBox.addItem("音乐3");
        comboBox.addItem("音乐4");
        comboBox.addItem("音乐5");
        box3.add(music);
        box3.add(comboBox);
        ItemListener itemListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    JRadioButton selectedButton = (JRadioButton) e.getSource();
                    System.out.println("选中的按钮文本: " + selectedButton.getText());
                    tempFrequency=selectedButton.getText();
                }
            }
        };
        ItemListener checkListener = new ItemListener() {  
    @Override  
    public void itemStateChanged(ItemEvent e) {  
        if (e.getStateChange() == ItemEvent.SELECTED) {  
            JCheckBox selectedBox = (JCheckBox) e.getSource();  
            System.out.println("选中的复选框文本: " + selectedBox.getText());  
        }  
    }  
};
        for (int i = 0; i < 7; i++) {  
            checkBoxes[i].addItemListener(checkListener);
            checkBoxes[i].addItemListener(new CheckBoxListener(i));  
        }
        
        workdayRadioButton.addItemListener(itemListener);
        weekendRadioButton.addItemListener(itemListener);
        everydayRadioButton.addItemListener(itemListener);
        onlyOnceRadioButton.addItemListener(itemListener);
        everydayRadioButton.setSelected(true);
        onlyOnceRadioButton.setSelected(true);
        // 创建并添加两个按钮到第二个面板
        setAlarmButton = new JButton("添加"); // 初始化添加按钮
        cancelButton = new JButton("取消"); // 初始化取消按钮
        box2.add(setAlarmButton);
        box2.add(cancelButton);


        // 添加关闭窗口时的操作到取消按钮上
        cancelButton.addActionListener(new ActionListener() { // 将取消按钮添加到取消按钮监听器中，以便在单击时关闭窗口
            public void actionPerformed(ActionEvent e) {
                dispose();  // 关闭窗口
            }
        });
        setAlarmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 在这里添加添加按钮的响应代码
                //要加一个返回选择的背景音乐
                String selectedItem1 = (String) comboBox.getSelectedItem();
                newAlarm=getHour()+","+getMinute()+","+getFrequency()+","+getBackgroundMusic(selectedItem1)+",";
                for(int i = 0; i < 6; ++i){
                    newAlarm+= variables[i] + ",";
                }
                newAlarm += variables[6];
                
                System.out.println(newAlarm);
                writeAlarm(newAlarm);
                readAlarm a =new readAlarm();
                dispose();
            }
        });
        hour.setFont(font1);
        minute.setFont(font1);
        music.setFont(font1);
        setAlarmButton.setFont(font2);
        cancelButton.setFont(font2);
        add(panel);

        panel.add(box1);
        panel.add(box0);
        panel.add(box4);
        panel.add(box3);
        panel.add(box2);

        pack();
        setVisible(true); // 显示窗口
        // 添加一个监听器，用于处理按钮组的选中事件

    }
    public void writeAlarm(String str){
        File file = new File("allAlarm.txt"); //创建文件
        try{
            FileWriter output = new FileWriter(file, true);
            BufferedWriter outB = new BufferedWriter(output); //文件缓冲
            outB.write(str); //文件写入字符串
            outB.newLine(); //换行
            outB.close(); //关闭文件
            outB.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
    public  String getHour() {
        return hourField.getText().toString();
    }
    public  String getMinute() {
        return minuteField.getText().toString();
    }
   public  String getFrequency() {
        return tempFrequency;
    }
    public String getNewAlarm() {
        return newAlarm;
    }
    public int getBackgroundMusic(String str) {
        switch (str){
            case "音乐1":
                bgMusicChoice=1;
                break;
            case "音乐2":
                bgMusicChoice=2;
                break;
            case "音乐3":
                bgMusicChoice=3;
                break;
            case "音乐4":
                bgMusicChoice=4;
                break;
            case "音乐5":
                bgMusicChoice=5;
                break;
        }
        return bgMusicChoice;
    }
}