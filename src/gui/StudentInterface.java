package gui;

import entity.*;
import servlet.accountchangeimpl;
import servlet.changedbimpl;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

class StudentInterface {
    private final students student;
    private final MainInterface mainInterface;
    private Font font1;
    private JFrame frame1;
    private Container panel0;
    private JButtonListener buttonListener1;
    //个人账号中心面板
    private JPanel panel1; //个人账号中心面板
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;
    //试题库面板
    private JPanel panel2;
    private JPanel panelLeft;
    private JList<String> jList1;
    private JScrollPane jScrollPane;
    private question[] questions;
    private SearchInterface searchInterface;
    //考试页面面板
    private JDialog dialog_chooseExam;
    private JTable table_chooseExam;
    private testpaper[] testPapers;
    private String[] dateArray;
    //我的班级界面面板
    private JPanel panel3;
    private JDialog dialog_joinClass;
    private JTextField textField_className;
    private JTextField textField_classPassword;


    StudentInterface(students student, MainInterface mainInterface) {
        this.student = student;
        this.mainInterface = mainInterface;
        initializeFrame();
    }

    private void initializeFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame1 = new JFrame("卷");
        panel0 = frame1.getContentPane();
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        buttonListener1 = new JButtonListener();
        JButton button1 = new JButton("个人中心");
        JButton button2 = new JButton("退出登录");
        JButton button3 = new JButton("试题库");
        JButton button4 = new JButton("考试页面");
        JButton button5 = new JButton("我的班级");
        JLabel label1 = new JLabel("当前登录学生：" + student.getName());
        //个人账号中心按钮初始化
        button1.setFont(font1);
        button1.setBounds(10, 60, 100, 30);
        button1.addActionListener(buttonListener1);
        //标签初始化
        label1.setFont(font1);
        label1.setBounds(10, 10, 200, 30);
        //退出登录按钮
        button2.setFont(font1);
        button2.setBounds(570, 20, 100, 30);
        button2.addActionListener(buttonListener1);
        //试题库按钮初始化
        button3.setFont(font1);
        button3.setBounds(10, 110, 100, 30);
        button3.addActionListener(buttonListener1);
        //考试页面按钮初始化
        button4.setFont(font1);
        button4.setBounds(10, 160, 100, 30);
        button4.addActionListener(buttonListener1);
        //我的班级按钮初始化
        button5.setFont(font1);
        button5.setBounds(10, 210, 100, 30);
        button5.addActionListener(buttonListener1);
        //主窗口初始化
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame1.setSize(700, 400);
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        this.frame1.setLocation(x, y);
        frame1.setIconImage(icon.getImage());
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setMinimumSize(new Dimension(700, 400));
        frame1.setResizable(false);
        //用户主界面面板添加组件
        panel0.setLayout(null);
        panel0.add(label1);
        panel0.add(button1);
        panel0.add(button2);
        panel0.add(button3);
        panel0.add(button4);
        panel0.add(button5);
        //设置窗口
        this.frame1.setVisible(true);
    }

    private void initializePanel1() { //个人账号中心面板
        panel1 = new JPanel();
        panel1.setLayout(null);
        //标签初始化
        JLabel label1 = new JLabel("原密码：");
        JLabel label2 = new JLabel("新密码：");
        label1.setFont(font1);
        label2.setFont(font1);
        label1.setBounds(90, 10, 62, 15);
        label2.setBounds(90, 40, 62, 15);
        //返回按钮初始化
        JButton button = new JButton("返回");
        button.setFont(font1);
        button.addActionListener(buttonListener1);
        button.setBounds(10, 10, 70, 25);
        //原密码输入框初始化
        passwordField1 = new JPasswordField();
        passwordField1.setFont(font1);
        passwordField1.setBounds(150, 10, 100, 20);
        //新密码输入框初始化
        passwordField2 = new JPasswordField();
        passwordField2.setFont(font1);
        passwordField2.setBounds(150, 40, 100, 20);
        //修改密码按钮初始化
        JButton button2 = new JButton("修改密码");
        button2.setFont(font1);
        button2.addActionListener(buttonListener1);
        button2.setBounds(260, 10, 100, 50);
        //JTable初始化
        changedbimpl changedbimpl = new changedbimpl();
        String[] initialData = changedbimpl.checktestdoneforstudent(student.getId());
        String[] columnNames = new String[]{"考试名称", "你的得分", "试卷总分"};
        String[][] examData = new String[initialData.length / 3][3];
        if (initialData.length == 1) {
            //examData = new String[][]{{"暂无", "暂无", "暂无"}};
        } else {
            for (int i = 0; i < initialData.length; i += 3) {
                examData[i / 3][0] = initialData[i];
                examData[i / 3][1] = initialData[i + 1];
                examData[i / 3][2] = initialData[i + 2];
            }
        }
        JTable table = new JTable(examData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        JTableHeader jTableHeader = table.getTableHeader();
        jTableHeader.setFont(font1);
        jTableHeader.setResizingAllowed(false);
        jTableHeader.setReorderingAllowed(false);
        table.setFont(font1);
        table.setShowGrid(true);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 70, 670, 285);
        //个人账号中心面板添加组件
        panel1.add(button2);
        panel1.add(passwordField1);
        panel1.add(passwordField2);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(button);
        panel1.add(scrollPane);
    }

    private void initializePanel2() { //试题库面板
        frame1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) { //窗口大小改变监听事件
                panelLeft.setSize(100, frame1.getHeight() - 38);
                jScrollPane.setSize(frame1.getWidth() - 115, frame1.getHeight() - 38);
                frame1.revalidate();
            }
        });
        //
        panel2 = new JPanel();
        panelLeft = new JPanel();
        jList1 = new JList<>(getTestQuestionCatalog());
        jScrollPane = new JScrollPane(jList1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JButton buttonBack = new JButton("返 回");
        JButton buttonRefresh = new JButton("刷 新");
        JButton buttonSearch = new JButton("搜索");
        //返回按钮初始化
        buttonBack.setFont(font1);
        buttonBack.addActionListener(buttonListener1);
        buttonBack.setBounds(3, 10, 95, 25);
        //刷新试题按钮
        buttonRefresh.setFont(font1);
        buttonRefresh.addActionListener(buttonListener1);
        buttonRefresh.setBounds(3, 45, 95, 25);
        //搜索按钮初始化
        buttonSearch.setFont(font1);
        buttonSearch.addActionListener(buttonListener1);
        buttonSearch.setBounds(3, 80, 95, 25);
        //左面板初始化
        panelLeft.setLayout(null);
        panelLeft.setBounds(0, 0, 101, frame1.getHeight() - 38);
        panelLeft.add(buttonBack);
        panelLeft.add(buttonRefresh);
        panelLeft.add(buttonSearch);
        panelLeft.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        //列表框初始化
        jList1.setSelectionMode(0);
        jList1.setFont(font1);
        jList1.setSelectionBackground(new Color(36, 116, 203));
        jList1.addMouseListener(new JListListener());
        jList1.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        //右面板（滚动面板）初始化
        jScrollPane.setBounds(101, 0, 583, frame1.getHeight() - 38);
        //试题库面板初始化，添加左右面板
        panel2.setLayout(null);
        panel2.add(panelLeft);
        panel2.add(jScrollPane);
    }

    private void initializePanel3() {  //我的班级面板
        panel3 = new JPanel();
        panel3.setLayout(null);
        //
        JLabel label1 = new JLabel("我加入的班级");
        label1.setFont(font1);
        label1.setBounds(310, 10, 200, 25);
        JLabel label2 = new JLabel("班级任务列表  当前查看班级：无");
        label2.setFont(font1);
        label2.setSize(240, 25);
        label2.setLocation((700 - label2.getWidth() - 115) / 2 + 115, 155);
        //返回按钮初始化
        JButton buttonBack = new JButton("返 回");
        buttonBack.setFont(font1);
        buttonBack.addActionListener(buttonListener1);
        buttonBack.setBounds(10, 10, 95, 30);
        //加入班级按钮初始化
        JButton buttonJoinClass = new JButton("加入班级");
        buttonJoinClass.setFont(font1);
        buttonJoinClass.setBounds(10, 50, 95, 30);
        buttonJoinClass.addActionListener(buttonListener1);
        //查看成绩按钮初始化
        JButton buttonViewResults = new JButton("查看成绩");
        buttonViewResults.setFont(font1);
        buttonViewResults.setBounds(10, 90, 95, 30);
        buttonViewResults.addActionListener(buttonListener1);
        //列表框初始化
        JList<String> list1 = new JList<>();
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list1.setFont(font1);
        list1.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        JList<String> list;
        String[] string_className = getClassName();
        if (string_className == null) {
            list = new JList<>();
        } else {
            list = new JList<>(getClassName());
        }
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(font1);
        list.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                list1.setListData(getClassTask(list.getSelectedIndex()));
                ListModel<String> listModel = list.getModel();
                label2.setText("班级任务列表  当前查看班级：" + listModel.getElementAt(list.getSelectedIndex()));
            }
        });
        //
        JScrollPane scrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(115, 45, 560, 100);
        JScrollPane scrollPane1 = new JScrollPane(list1, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(115, 190, 560, 100);
        //添加组件
        panel3.add(buttonBack);
        panel3.add(label1);
        panel3.add(buttonJoinClass);
        panel3.add(scrollPane);
        panel3.add(label2);
        panel3.add(scrollPane1);
    }

    private void initializeDialog_chooseExam() {
        dialog_chooseExam = new JDialog(frame1, "选择要进行的考试", true);
        Container panel = dialog_chooseExam.getContentPane();
        changedbimpl changedbimpl = new changedbimpl(student);
        dateArray = changedbimpl.checkstudenttask();
        JButton button = new JButton("选 择");
        //表格初始化
        String[] columnNames = new String[]{"考试名称", "总时长/分钟", "总分", "出卷人", "已进行时长/分钟"};
        String[][] examData = new String[dateArray.length / 4][5];
        testPapers = new testpaper[dateArray.length / 4];
        for (int i = 0; i < testPapers.length; i++) {
            int temp = Integer.parseInt(dateArray[4 * i]);
            testPapers[i] = changedbimpl.checkpointedpaper(temp);
            examData[i][0] = testPapers[i].getName();
            examData[i][1] = String.valueOf(Integer.parseInt(dateArray[4 * i + 2]) / 60);
            examData[i][2] = String.valueOf(testPapers[i].getPaperpoint());
            examData[i][3] = testPapers[i].getCreater();
            double x1 = Double.parseDouble(dateArray[4 * i + 1]);
            x1 = x1 / 60;
            examData[i][4] = String.valueOf(x1);
        }
        table_chooseExam = new JTable(examData, columnNames) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        JTableHeader jTableHeader = table_chooseExam.getTableHeader();
        TableColumn tableColumn1 = table_chooseExam.getColumnModel().getColumn(3);
        tableColumn1.setPreferredWidth(50);
        tableColumn1 = table_chooseExam.getColumnModel().getColumn(1);
        tableColumn1.setPreferredWidth(50);
        tableColumn1 = table_chooseExam.getColumnModel().getColumn(2);
        tableColumn1.setPreferredWidth(30);
        jTableHeader.setFont(font1);
        jTableHeader.setResizingAllowed(false);
        jTableHeader.setReorderingAllowed(false);
        table_chooseExam.setFont(font1);
        table_chooseExam.setShowGrid(true);
        table_chooseExam.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //按钮初始化
        button.setFont(font1);
        button.setBounds(260, 170, 120, 30);
        button.setActionCommand("选择_exam");
        button.addActionListener(buttonListener1);
        //滑动面板
        JScrollPane scrollPane = new JScrollPane(table_chooseExam, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 10, 600, 150);
        //
        panel.setLayout(null);
        panel.add(scrollPane);
        panel.add(button);
        //
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        dialog_chooseExam.setFont(font1);
        dialog_chooseExam.setSize(635, 240);
        dialog_chooseExam.setLocationRelativeTo(frame1);
        dialog_chooseExam.setIconImage(icon.getImage());
        dialog_chooseExam.setDefaultLookAndFeelDecorated(true);
        dialog_chooseExam.setResizable(false);
        dialog_chooseExam.setVisible(true);
    }

    private void initializeDialog_joinClass() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog_joinClass = new JDialog(frame1, "加入班级", true);
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        Container panelThis = dialog_joinClass.getContentPane();
        JLabel label1 = new JLabel("班级名称：");
        JLabel label2 = new JLabel("班级口令：");
        JButton button = new JButton("加入班级");
        textField_className = new JTextField();
        textField_classPassword = new JTextField();
        label1.setFont(font1);
        label1.setBounds(10, 10, 90, 30);
        label2.setFont(font1);
        label2.setBounds(10, 50, 90, 30);
        button.setFont(font1);
        button.setBounds(10, 90, 180, 30);
        button.addActionListener(buttonListener1);
        button.setActionCommand("加入_class");
        textField_className.setFont(font1);
        textField_className.setBounds(90, 10, 100, 30);
        textField_classPassword.setFont(font1);
        textField_classPassword.setBounds(90, 50, 100, 30);
        panelThis.setLayout(null);
        panelThis.add(label1);
        panelThis.add(label2);
        panelThis.add(button);
        panelThis.add(textField_className);
        panelThis.add(textField_classPassword);
        this.dialog_joinClass.setSize(215, 160);
        this.dialog_joinClass.setLocationRelativeTo(frame1);
        dialog_joinClass.setIconImage(icon.getImage());
        dialog_joinClass.setDefaultLookAndFeelDecorated(true);
        dialog_joinClass.setResizable(false);
        dialog_joinClass.setVisible(true);
    }

    private boolean checkNewPassword() {
        String newPassword = new String(passwordField2.getPassword());
        if (newPassword.length() < 6) {
            JOptionPane.showMessageDialog(null, "新密码最少为6位");
            return false;
        } else if (newPassword.matches("\\S*\\s+\\S*+.*")) {
            JOptionPane.showMessageDialog(null, "新密码中不能包含空白字符");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkClassName() {
        String name = textField_className.getText();
        if (name.length() > 98) {
            JOptionPane.showMessageDialog(frame1, "班级名称太长，请限制字符小于99");
            return false;
        } else if (name.equals("")) {
            JOptionPane.showMessageDialog(frame1, "班级名称不能为空");
            return false;
        } else {
            return true;
        }
    }

    private String[] getClassTask(int index) {
        changedbimpl changedbimpl = new changedbimpl(student);
        String[] strings = changedbimpl.checkstudentclass();
        String[] strings1 = new String[strings.length / 2];
        for (int i = 0; i < strings.length / 2; i++) {
            strings1[i] = strings[i];
        }
        return changedbimpl.checkclasstask(Integer.parseInt(strings1[index]));
    }

    private String[] getTestQuestionCatalog() { //获取试题目录
        changedbimpl changedbimpl = new changedbimpl();
        this.questions = changedbimpl.checkquestion();
        String[] strings = new String[questions.length];
        int t = 0;
        for (question x : questions) {
            strings[t] = x.getForm();
            strings[t] += " ";
            strings[t] += x.getTitle();
            t++;
        }
        return strings;
    }

    private String[] getClassName() {
        changedbimpl changedbimpl = new changedbimpl(student);
        String[] strings = changedbimpl.checkstudentclass();
        if (strings != null) {
            String[] strings1 = new String[strings.length / 2];
            for (int i = strings.length / 2, i1 = 0; i < strings.length; i++, i1++) {
                strings1[i1] = strings[i];
            }
            return strings1;
        }
        return null;
    }

    void setVisible() {
        frame1.setVisible(true);
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("个人中心")) {
                initializePanel1();
                StudentInterface.this.frame1.setContentPane(panel1);
                StudentInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("修改密码")) {
                String oldPassword = new String(passwordField1.getPassword());
                String newPassword = new String(passwordField2.getPassword());
                if (oldPassword.equals(student.getPassword())) {
                    if (checkNewPassword()) {
                        if (student.getPassword().equals(newPassword)) {
                            JOptionPane.showMessageDialog(null, "新密码与旧密码相同", "提示", JOptionPane.WARNING_MESSAGE);
                        } else {
                            student.setPassword(newPassword);
                            accountchangeimpl accountchangeimpl = new accountchangeimpl(student);
                            try {
                                accountchangeimpl.teacheraccountchange();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                            JOptionPane.showMessageDialog(null, "新密码修改成功");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "原密码输入错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getActionCommand().equals("返回")) {  //从个人中心面板返回
                StudentInterface.this.frame1.setContentPane(panel0);
                StudentInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("返 回")) {  //从试题库面板及其他面板返回
                StudentInterface.this.frame1.setResizable(false);
                StudentInterface.this.frame1.setSize(700, 400);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
                int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
                StudentInterface.this.frame1.setLocation(x, y);
                StudentInterface.this.frame1.setContentPane(panel0);
                StudentInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("试题库")) {
                try {
                    initializePanel2();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                StudentInterface.this.frame1.setResizable(true);
                StudentInterface.this.frame1.setContentPane(panel2);
                StudentInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("退出登录")) {
                frame1.dispose();
                StudentInterface.this.mainInterface.mainFrameVisible();
                StudentInterface.this.mainInterface.setFrameCenter();
            } else if (e.getActionCommand().equals("刷 新")) {
                jList1.setListData(getTestQuestionCatalog());
                jList1.repaint();
            } else if (e.getActionCommand().equals("搜索")) {
                if (searchInterface == null) {
                    searchInterface = new SearchInterface();
                } else {
                    searchInterface.setLook();
                }
            } else if (e.getActionCommand().equals("考试页面")) {
                initializeDialog_chooseExam();
            } else if (e.getActionCommand().equals("选择_exam")) {
                int second = Integer.parseInt(dateArray[4 * table_chooseExam.getSelectedRow() + 2]) - Integer.parseInt(dateArray[4 * table_chooseExam.getSelectedRow() + 1]);
                int classId = Integer.parseInt(dateArray[4 * table_chooseExam.getSelectedRow() + 3]);
                if (Integer.parseInt(dateArray[4 * table_chooseExam.getSelectedRow() + 1]) == 0) {
                    TestInterface testInterface = new TestInterface(student, testPapers[table_chooseExam.getSelectedRow()], StudentInterface.this, second, Integer.parseInt(dateArray[4 * table_chooseExam.getSelectedRow() + 2]), classId);
                } else {
                    changedbimpl changedbimpl = new changedbimpl();
                    testDone testdone = changedbimpl.checktestdone(testPapers[table_chooseExam.getSelectedRow()].getId(), student.getId(), classId);
                    String[] answers = new String[testdone.getAnswerquestionDones().length + testdone.getJudgequestionDones().length + testdone.getChoicequestionDones().length];
                    int i = 0;
                    for (choicequestionDone temp : testdone.getChoicequestionDones()) {
                        answers[i] = temp.getStudentanswer();
                        i++;
                    }
                    for (judgequestionDone temp : testdone.getJudgequestionDones()) {
                        answers[i] = temp.getStudentanswer();
                        i++;
                    }
                    for (answerquestionDone temp : testdone.getAnswerquestionDones()) {
                        answers[i] = temp.getStudentanswer();
                        i++;
                    }
                    TestInterface testInterface = new TestInterface(student, testPapers[table_chooseExam.getSelectedRow()], StudentInterface.this, second, Integer.parseInt(dateArray[4 * table_chooseExam.getSelectedRow() + 2]), answers, classId);
                }
                dialog_chooseExam.dispose();
                dialog_chooseExam = null;
                frame1.setVisible(false);
            } else if (e.getActionCommand().equals("我的班级")) {
                initializePanel3();
                StudentInterface.this.frame1.setContentPane(panel3);
                StudentInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("加入班级")) {
                initializeDialog_joinClass();
            } else if (e.getActionCommand().equals("加入_class")) {
                if (checkClassName()) {
                    String className = textField_className.getText();
                    String password = textField_classPassword.getText();
                    changedbimpl changedbimpl = new changedbimpl();
                    int condition = changedbimpl.addstudentintoclass(className, password, student.getName(), student.getId());
                    if (condition != 1) {
                        JOptionPane.showMessageDialog(frame1, "加入班级成功");
                        dialog_joinClass.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame1, "加入班级失败");
                    }
                }
            }
        }
    }

    private class JListListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            NewThread2 newThread2 = new NewThread2();
            newThread2.start();
        }
    }

    private class NewThread2 extends Thread {
        @Override
        public void run() {
            MaintainQuestionsInterface maintainQuestionsInterface = new MaintainQuestionsInterface(questions[jList1.getSelectedIndex()]);
        }
    }

}
