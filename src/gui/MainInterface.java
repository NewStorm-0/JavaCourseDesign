package gui;

import entity.students;
import entity.teacher;
import servlet.registerimpl;
import servlet.signimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainInterface {
    private JFrame frame1;
    private JFrame frame2;
    private JWindow window;
    private ImageIcon icon; //图标
    private JTextField textField1; //用户名输入文本框
    private JTextField textField2; //注册页面用户名输入框
    private JTextField textField3; //注册页面姓名输入框
    private JPasswordField passwordField1; //密码输入框
    private JPasswordField passwordField2; //注册页面密码框
    private JPasswordField passwordField3; //注册页面重复密码框
    private JRadioButton radioButton1; //单选按钮（是否显示密码）
    private JButtonListener buttonListener; //账号按钮监听事件

    public MainInterface() {
        initializeWindow();
        initializeFrame1();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(null, "发生了错误", "Error", JOptionPane.ERROR_MESSAGE);
        }
        window.dispose();
        frame1.setVisible(true);
    }

    private void initializeWindow() {
        window = new JWindow();
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/a.jpg";
        ImageIcon happyPng = new ImageIcon(iconPath);
        Image img = happyPng.getImage().getScaledInstance(350, 200, Image.SCALE_SMOOTH);
        JLabel label0 = new JLabel(new ImageIcon(img));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.window.setSize(350, 200);
        int x = (int) screenSize.getWidth() / 2 - window.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - window.getHeight() / 2;
        this.window.setLocation(x, y);
        this.window.add(label0);
        this.window.setVisible(true);
    }

    private void initializeFrame1() {
        frame1 = new JFrame("卷");
        Container panel = frame1.getContentPane();
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        icon = new ImageIcon(iconPath);
        JLabel label1 = new JLabel("账户：");
        JLabel label2 = new JLabel("密码：");
        Font font1 = new Font("黑体", Font.PLAIN, 20);
        Font font2 = new Font("Microsoft YaHei UI", Font.PLAIN, 17);
        JButton button3 = new JButton("还没有账号？点此注册");
        JRadioButtonListener radioButtonListener = new JRadioButtonListener();
        JButton button1 = new JButton("教师账号登录");
        JButton button2 = new JButton("学生账号登录");
        //背景图片
        String picturePath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/001.jpg";
        ImageIcon happyPng = new ImageIcon(picturePath);
        Image img = happyPng.getImage().getScaledInstance(550, 350, Image.SCALE_SMOOTH);
        JLabel label0 = new JLabel(new ImageIcon(img));
        label0.setSize(550, 350);
        label0.setLocation(0, 0);
        //用户名输入框
        textField1 = new JTextField("卷神");
        textField1.setFont(font1);
        textField1.setBounds(205, 52, 176, 25);
        textField1.setOpaque(false);
        //密码输入框
        passwordField1 = new JPasswordField("1234567");
        passwordField1.setBounds(204, 102, 176, 26);
        passwordField1.setFont(font2);
        passwordField1.setOpaque(false);
        //单选按钮显示密码
        radioButton1 = new JRadioButton("显示密码");
        radioButton1.setBounds(203, 132, 75, 20);
        radioButton1.addActionListener(radioButtonListener);
        radioButton1.setOpaque(false);
        //账号按钮监听事件
        buttonListener = new JButtonListener();
        //教师登录按钮
        button1.setBounds(150, 155, 110, 40);
        button1.addActionListener(buttonListener);
        //button1.setOpaque(false);
        //学生登录按钮
        button2.setBounds(270, 155, 110, 40);
        button2.addActionListener(buttonListener);
        //button2.setOpaque(false);
        //注册按钮
        button3.setBounds(150, 205, 230, 40);
        button3.addActionListener(buttonListener);
        //button3.setOpaque(false);
        //其他初始化
        panel.setLayout(null);
        label1.setFont(font1);
        label2.setFont(font1);
        label1.setBounds(150, 50, 60, 25);
        label2.setBounds(150, 100, 60, 25);
        frame1.setSize(550, 350);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        frame1.setLocation(x, y);
        //添加组件
        panel.add(label1);
        panel.add(label2);
        panel.add(textField1);
        panel.add(passwordField1);
        panel.add(radioButton1);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(label0);
        //frame初始化
        frame1.setIconImage(icon.getImage());
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setVisible(false);
    }

    private void initializeFrame2() {
        //注册窗口生成
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame2 = new JFrame("注册页面");
        //字体
        Font font3 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        //面板配置
        Container panel = frame2.getContentPane();
        panel.setLayout(new GridLayout(5, 2, 10, 8));
        //组件配置
        JLabel label1 = new JLabel("用户名：");
        JLabel label2 = new JLabel("密码：");
        JLabel label3 = new JLabel("重复输入密码:");
        JLabel label4 = new JLabel("姓名：");
        label1.setFont(font3);
        label2.setFont(font3);
        label3.setFont(font3);
        label4.setFont(font3);
        textField2 = new JTextField(8);
        textField3 = new JTextField(8);
        passwordField2 = new JPasswordField(8);
        passwordField3 = new JPasswordField(8);
        textField2.setFont(font3);
        textField3.setFont(font3);
        passwordField2.setFont(font3);
        passwordField3.setFont(font3);
        JButton button3 = new JButton("教师账号注册");
        JButton button4 = new JButton("学生账号注册");
        button3.setFont(font3);
        button4.setFont(font3);
        button3.addActionListener(buttonListener);
        button4.addActionListener(buttonListener);
        //添加组件
        panel.add(label1);
        panel.add(textField2);
        panel.add(label4);
        panel.add(textField3);
        panel.add(label2);
        panel.add(passwordField2);
        panel.add(label3);
        panel.add(passwordField3);
        panel.add(button3);
        panel.add(button4);
        //注册窗口配置
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame2.setSize(300, 200);
        int x = (int) screenSize.getWidth() / 2 - frame2.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame2.getHeight() / 2;
        frame2.setLocation(x, y);
        frame2.setIconImage(icon.getImage());
        frame2.setResizable(false);
        frame2.setDefaultLookAndFeelDecorated(true);
        frame2.addWindowListener(new WindowDestroyer());
        frame2.setVisible(true);
        frame1.setEnabled(false);
    }

    void mainFrameVisible() {
        this.frame1.setVisible(true);
    }

    void setFrameCenter() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        frame1.setLocation(x, y);
    }

    private class JRadioButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radioButton1.isSelected()) {
                textField1.setText("男神");
                passwordField1.setText("wangzhongwang");
                MainInterface.this.passwordField1.setEchoChar((char) 0);
            } else {
                MainInterface.this.passwordField1.setEchoChar('•');
            }
        }
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("教师账号登录")) {
                if (checkLogMsg()) {
                    System.out.println("账号：" + textField1.getText());
                    String account = textField1.getText();
                    //连接数据库查询
                    String password = new String(passwordField1.getPassword());
                    teacher teacher = new teacher(account, password);
                    signimpl signimpl = new signimpl(teacher);
                    if ((teacher = signimpl.teachersign()) != null) {
                        TeacherInterface teacherInterface = new TeacherInterface(teacher, MainInterface.this);
                        MainInterface.this.frame1.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(frame2, "用户名或密码错误", "登录错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (e.getActionCommand().equals("学生账号登录")) {
                if (checkLogMsg()) {
                    System.out.println("账号：" + textField1.getText());
                    String account = textField1.getText();
                    String password = new String(passwordField1.getPassword());
                    //连接数据库查询
                    students students = new students(account, password);
                    signimpl signimpl = new signimpl(students);
                    if ((students = signimpl.studentsign()) != null) {
                        System.out.println("学生：" + students.getName());
                        StudentInterface studentInterface = new StudentInterface(students, MainInterface.this);
                        MainInterface.this.frame1.setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(frame2, "用户名或密码错误", "登录错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (e.getActionCommand().equals("还没有账号？点此注册")) {
                initializeFrame2();
            } else if (e.getActionCommand().equals("教师账号注册")) {
                if (checkRegisterMsg()) {
                    String password = new String(passwordField2.getPassword());
                    String account = textField2.getText();
                    String name = textField3.getText();
                    //开始注册
                    teacher teacher = new teacher(account, password, name);
                    registerimpl registerimpl = new registerimpl(teacher);
                    try {
                        registerimpl.teacher_register();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    //数据库注册完成
                    System.out.println("教师账号注册\n账号为：" + account + "\n密码为：" + password + "\n姓名为：" + name);
                    JOptionPane.showMessageDialog(null, "注册成功", "提示：", JOptionPane.PLAIN_MESSAGE);
                    frame2.dispose();
                    frame1.setEnabled(true);
                    frame1.setVisible(true);
                }
            } else if (e.getActionCommand().equals("学生账号注册")) {
                if (checkRegisterMsg()) {
                    String password = new String(passwordField2.getPassword());
                    String account = textField2.getText();
                    String name = textField3.getText();
                    //开始注册
                    students students = new students(account, password, name);
                    registerimpl registerimpl = new registerimpl(students);
                    try {
                        registerimpl.student_register();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                    //数据库注册完成
                    System.out.println("学生账号注册\n账号为：" + account + "\n密码为：" + password + "\n姓名为：" + name);
                    JOptionPane.showMessageDialog(null, "注册成功", "提示：", JOptionPane.PLAIN_MESSAGE);
                    frame2.dispose();
                    frame1.setEnabled(true);
                    frame1.setVisible(true);
                }
            } else {
                JOptionPane.showMessageDialog(null, "发生了错误", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        boolean checkRegisterMsg() {
            if (textField2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入用户名");
                textField2.setFocusable(true);
                return false;
            } else if (textField3.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入姓名");
                textField3.setFocusable(true);
                return false;
            } else if (textField2.getText().matches("\\s+")) {
                JOptionPane.showMessageDialog(null, "用户名不能为纯空白字符");
                textField2.setFocusable(true);
                return false;
            } else if (textField3.getText().matches("\\s+")) {
                JOptionPane.showMessageDialog(null, "姓名不能为纯空白字符");
                textField3.setFocusable(true);
                return false;
            } else {
                String password = new String(passwordField2.getPassword());
                if (password.equals(new String(passwordField3.getPassword()))) {
                    if (password.length() < 6) {
                        JOptionPane.showMessageDialog(null, "密码最少为六位");
                        return false;
                    } else if (password.matches("\\S*\\s+\\S*+.*")) {
                        JOptionPane.showMessageDialog(null, "密码中不能包含空白字符");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "两次输入的密码不同，请重新输入");
                    return false;
                }
            }
        }

        boolean checkLogMsg() {
            if (textField1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "请输入用户名");
                textField1.setFocusable(true);
                return false;
            } else if (textField1.getText().matches("\\s+")) {
                JOptionPane.showMessageDialog(null, "用户名不能为纯空白字符");
                textField1.setFocusable(true);
                return false;
            } else {
                String password = new String(passwordField1.getPassword());
                if (password.length() < 6) {
                    JOptionPane.showMessageDialog(null, "密码最少为六位");
                    return false;
                } else if (password.matches("\\S*\\s+\\S*+.*")) {
                    JOptionPane.showMessageDialog(null, "密码中不能包含空白字符");
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    private class WindowDestroyer extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            if (e.getWindow() == frame2) {
                frame1.setEnabled(true);
            }
            super.windowClosing(e);
        }
    }
}

