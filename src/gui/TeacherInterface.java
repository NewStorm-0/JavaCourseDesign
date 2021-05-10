package gui;

import entity.*;

import javax.swing.*;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import servlet.*;

class TeacherInterface {
    private JFrame frame1; //用户主界面
    private Container panel0; //用户主界面面板
    private JPanel panel1; //个人账号中心面板
    private JPanel panel2; //试题库面板
    private JPanel panel3; //组卷面板
    private JPanel panel4; //试卷一览面板
    private JPanel panelLeft; //试题库左面板
    private Font font1; //Microsoft YaHei UI
    private JPasswordField passwordField1; //原密码输入框
    private JPasswordField passwordField2; //新密码输入框
    private JList<String> jList1; //试题库列表框
    private JPopupMenu popupMenu1; //试题库右键菜单
    private JScrollPane jScrollPane; //试题库列表框滚动面板
    private JButtonListener buttonListener1; //按钮监听事件
    private AddQuestionInterface addQuestionInterface; //添加试题界面
    private RuleInterface ruleInterface; //添加规则界面
    private SearchInterface searchInterface; //搜索窗口界面
    private JRadioButton radioButton1; //单选按钮（显示试题）
    private JRadioButton radioButton2; //单选按钮（显示规则）
    private JLabel label1; //标签（显示内容）
    private final teacher teacher;
    private final MainInterface mainInterface;
    private question[] questions;
    private rules[] rules1;
    private JScrollPane scrollPane_exam; //试卷加题滑动面板
    private PaintPanel panel_exam_right;
    private JDialog dialog_chooseRule;
    private JDialog dialog_inputName;
    private JComboBox<String> comboBox_chooseRule; //选择规则下拉框
    private GenerateTestElement generateTestElement; //
    private Dimension dimension_per;
    private JTextField textField_testName;  //试卷名称输入框
    private rules rule_choose;  //生成试卷时选择的规则
    private JScrollPane scrollPane_lookExam;
    private PaintPanel panel_lookExam_right;
    private JDialog dialog_chooseExam;
    private JComboBox<String> comboBox_chooseExam; //选择试卷下拉框
    private GenerateTestElement generateTestElement_lookExam; //
    private Dimension dimension_ple;
    private testpaper[] testPapers;
    //班级管理页面
    private JPanel panel_class;
    private JDialog dialog_addClass;
    private JTextField textField_className;
    private JTextField textField_classPassword;
    private JList<String> list_class;
    private JPanel panel_viewClass;
    private classes[] classes_view;
    private classes class_choose;
    private JTextField textField_examTime;
    private JList<String> list_task; //班级任务下拉框
    private String[] task;
    private int[] studentsArray;

    TeacherInterface(teacher teacher, MainInterface mainInterface) {
        this.teacher = teacher;
        this.mainInterface = mainInterface;
        initializeFrame1();
    }

    private void initializeFrame1() {
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
        JButton button4 = new JButton("组卷页面");
        JButton button5 = new JButton("试卷一览");
        JButton button6 = new JButton("班级管理");
        JLabel label1 = new JLabel("当前登录教师：" + teacher.getName());
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
        //试卷一览按钮初始化
        button5.setFont(font1);
        button5.setBounds(10, 210, 100, 30);
        button5.addActionListener(buttonListener1);
        //班级管理按钮初始化
        button6.setFont(font1);
        button6.setBounds(10, 260, 100, 30);
        button6.addActionListener(buttonListener1);
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
        panel0.add(button6);
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
        //个人账号中心面板添加组件
        panel1.add(button2);
        panel1.add(passwordField1);
        panel1.add(passwordField2);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(button);
    }

    private void initializePanel2() { //试题库面板
        frame1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) { //窗口大小改变监听事件
                panelLeft.setSize(100, frame1.getHeight() - 38);
                jScrollPane.setSize(frame1.getWidth() - 115, frame1.getHeight() - 70);
                label1.setBounds(110, frame1.getHeight() - 68, 80, 30);
                radioButton1.setBounds(200, frame1.getHeight() - 68, 80, 30);
                radioButton2.setBounds(290, frame1.getHeight() - 68, 120, 30);
                frame1.revalidate();
            }
        });
        //
        panel2 = new JPanel();
        panelLeft = new JPanel();
        jList1 = new JList<>(getTestQuestionCatalog());
        jScrollPane = new JScrollPane(jList1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JMenuItem menuItem1 = new JMenuItem("查看"); //试题库右键菜单：查看
        JMenuItem menuItem2 = new JMenuItem("删除"); //试题库右键菜单：删除
        JButton buttonBack = new JButton("返 回");
        JButton buttonRefresh = new JButton("刷 新");
        JButton buttonAddQuestion = new JButton("添加试题");
        JButton buttonAddRule = new JButton("添加规则");
        JButton buttonSearch = new JButton("搜索");
        popupMenu1 = new JPopupMenu();
        label1 = new JLabel("显示内容");
        radioButton1 = new JRadioButton("试题");
        radioButton2 = new JRadioButton("组卷规则");
        //返回按钮初始化
        buttonBack.setFont(font1);
        buttonBack.addActionListener(buttonListener1);
        buttonBack.setBounds(3, 10, 95, 25);
        //刷新试题按钮
        buttonRefresh.setFont(font1);
        buttonRefresh.addActionListener(buttonListener1);
        buttonRefresh.setBounds(3, 80, 95, 25);
        //添加试题按钮
        buttonAddQuestion.setFont(font1);
        buttonAddQuestion.addActionListener(buttonListener1);
        buttonAddQuestion.setBounds(3, 45, 95, 25);
        //增加规则按钮初始化
        buttonAddRule.setFont(font1);
        buttonAddRule.addActionListener(buttonListener1);
        buttonAddRule.setBounds(3, 115, 95, 25);
        //搜索按钮初始化
        buttonSearch.setFont(font1);
        buttonSearch.addActionListener(buttonListener1);
        buttonSearch.setBounds(3, 150, 95, 25);
        //左面板初始化
        panelLeft.setLayout(null);
        panelLeft.setBounds(0, 0, 101, frame1.getHeight() - 38);
        panelLeft.add(buttonBack);
        panelLeft.add(buttonRefresh);
        panelLeft.add(buttonAddQuestion);
        panelLeft.add(buttonAddRule);
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
        //右键菜单
        popupMenu1.setPopupSize(80, 55);
        popupMenu1.add(menuItem1);
        popupMenu1.add(menuItem2);
        menuItem1.setFont(font1);
        menuItem2.setFont(font1);
        menuItem1.addMouseListener(new Panel2Listener_1());
        menuItem2.addMouseListener(new Panel2Listener_2());
        //右面板（滚动面板）初始化
        jScrollPane.setBounds(101, 0, 583, frame1.getHeight() - 70);
        //标签
        label1.setFont(font1);
        label1.setBounds(110, frame1.getHeight() - 68, 80, 30);
        //单选按钮
        ButtonGroup buttonGroup1 = new ButtonGroup();
        buttonGroup1.add(radioButton1);
        buttonGroup1.add(radioButton2);
        radioButton1.setFont(font1);
        radioButton2.setFont(font1);
        radioButton1.setBounds(200, frame1.getHeight() - 68, 80, 30);
        radioButton2.setBounds(290, frame1.getHeight() - 68, 120, 30);
        radioButton1.setSelected(true);
        radioButton2.setSelected(false);
        JRadioButtonListener radioButtonListener = new JRadioButtonListener();
        radioButton1.addActionListener(radioButtonListener);
        radioButton2.addActionListener(radioButtonListener);
        //试题库面板初始化，添加左右面板
        panel2.setLayout(null);
        panel2.add(panelLeft);
        panel2.add(jScrollPane);
        panel2.add(label1);
        panel2.add(radioButton1);
        panel2.add(radioButton2);
    }

    private void initializePanel3() {  //组卷页面面板
        panel3 = new JPanel();
        panel3.setLayout(null);
        //
        JButton buttonBack = new JButton("返 回");
        JButton buttonChoose = new JButton("选择规则");
        JButton buttonGenerate = new JButton("生成试卷");
        //
        buttonBack.setFont(font1);
        buttonChoose.setFont(font1);
        buttonGenerate.setFont(font1);
        buttonBack.setBounds(10, 10, 100, 30);
        buttonChoose.setBounds(10, 50, 100, 30);
        buttonGenerate.setBounds(10, 90, 100, 30);
        buttonBack.addActionListener(buttonListener1);
        buttonChoose.addActionListener(buttonListener1);
        buttonGenerate.addActionListener(buttonListener1);
        //
        dimension_per = new Dimension(frame1.getWidth() - 155, frame1.getHeight() - 50);
        //
        panel_exam_right = new PaintPanel();
        panel_exam_right.setLayout(null);
        panel_exam_right.setPreferredSize(dimension_per);
        panel_exam_right.setBackground(new Color(111, 158, 142));
        //
        scrollPane_exam = new JScrollPane(panel_exam_right, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane_exam.setBounds(120, 0, frame1.getWidth() - 135, frame1.getHeight() - 38);
        //
        //考试页面全局变量
        //左面板
        JPanel panel_exam_left = new JPanel();
        panel_exam_left.setLayout(null);
        panel_exam_left.setBounds(0, 0, 120, frame1.getHeight() - 38);
        panel_exam_left.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel_exam_left.add(buttonBack);
        panel_exam_left.add(buttonChoose);
        panel_exam_left.add(buttonGenerate);
        //
        panel3.add(panel_exam_left);
        panel3.add(scrollPane_exam);
    }

    private void initializePanel4() {  //试卷一览面板
        panel4 = new JPanel();
        panel4.setLayout(null);
        //
        JButton buttonBack = new JButton("返 回");
        JButton buttonChoose = new JButton("选择试卷");
        //
        buttonBack.setFont(font1);
        buttonChoose.setFont(font1);
        buttonBack.setBounds(10, 10, 100, 30);
        buttonChoose.setBounds(10, 50, 100, 30);
        buttonBack.addActionListener(buttonListener1);
        buttonChoose.addActionListener(buttonListener1);
        //
        dimension_ple = new Dimension(frame1.getWidth() - 155, frame1.getHeight() - 50);
        //
        panel_lookExam_right = new PaintPanel();
        panel_lookExam_right.setLayout(null);
        panel_lookExam_right.setPreferredSize(dimension_ple);
        panel_lookExam_right.setBackground(new Color(111, 158, 142));
        //
        scrollPane_lookExam = new JScrollPane(panel_lookExam_right, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane_lookExam.setBounds(120, 0, frame1.getWidth() - 135, frame1.getHeight() - 38);
        //
        //试卷一览页面
        JPanel panel_lookExam_left = new JPanel();
        panel_lookExam_left.setLayout(null);
        panel_lookExam_left.setBounds(0, 0, 120, frame1.getHeight() - 38);
        panel_lookExam_left.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        panel_lookExam_left.add(buttonBack);
        panel_lookExam_left.add(buttonChoose);
        //
        panel4.add(panel_lookExam_left);
        panel4.add(scrollPane_lookExam);
    }

    private void initializePanel_class() {
        panel_class = new JPanel();
        panel_class.setLayout(null);
        //
        JLabel label1 = new JLabel("我创建的班级");
        label1.setFont(font1);
        label1.setBounds(330, 10, 200, 25);
        //
        JButton buttonBack = new JButton("返 回");
        JButton button_addClass = new JButton("添加班级");
        buttonBack.setFont(font1);
        buttonBack.setBounds(10, 10, 100, 30);
        buttonBack.addActionListener(buttonListener1);
        button_addClass.setFont(font1);
        button_addClass.setBounds(10, 50, 100, 30);
        button_addClass.addActionListener(buttonListener1);
        //
        list_class = new JList<>(getClassName());
        list_class.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list_class.setFont(font1);
        list_class.setSelectionBackground(new Color(36, 116, 203));
        list_class.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int index = list_class.getSelectedIndex();
                initializePanel_viewClass(index);
                frame1.setContentPane(panel_viewClass);
                frame1.revalidate();
            }
        });
        list_class.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        //
        JScrollPane scrollPane = new JScrollPane(list_class, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(120, 45, 555, 310);
        scrollPane.setBackground(Color.MAGENTA);
        //
        panel_class.add(buttonBack);
        panel_class.add(button_addClass);
        panel_class.add(label1);
        panel_class.add(scrollPane);
    }

    private void initializePanel_viewClass(int index) {
        panel_viewClass = new JPanel();
        //JFreeChart图片
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        dataset1.addValue(0, "已提交", "");  // 第一个参数是第三个参数的值，即“最高分”，第二个参数表示目录轴的分类，第三个参数表示的x轴显示标签
        dataset1.addValue(0, "未提交", "");
        JFreeChart freeChart = ChartFactory.createBarChart("", // 图表标题
                "", // 水平轴的显示标签
                "人数/人", // 垂直轴的显示标签
                dataset1, // 数据集，即要显示在图表上的数据
                PlotOrientation.VERTICAL, // 图表方向：水平，垂直
                true, // 是否显示图例
                false, // 是否显示提示
                false// 是否生成URL连接
        );
        ChartPanel chartPanel = new ChartPanel(freeChart);
        chartPanel.setMouseZoomable(false);
        chartPanel.setPreferredSize(new java.awt.Dimension(400, 300));
        chartPanel.setSize(150, 215);
        chartPanel.setLocation(0, 145);
        //
        JList<String> list_student = new JList<>(getStudentsNames(index));
        list_student.setFont(font1);
        list_student.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        list_task = new JList<>(getTaskNames(index));
        list_task.setFont(font1);
        list_task.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        list_task.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                dataset1.clear();
                changedbimpl changedbimpl = new changedbimpl();
                String[] sId = changedbimpl.checkdonestudent(class_choose.getId(), Integer.parseInt(task[list_task.getSelectedIndex()]));
                if (sId[0].equals("")) {
                    studentsArray = null;
                } else {
                    studentsArray = new int[sId.length];
                    for (int i = 0; i < studentsArray.length; i++) {
                        studentsArray[i] = Integer.parseInt(sId[i]);
                    }
                }
                int done = changedbimpl.checkdonestudent(class_choose.getId(), Integer.parseInt(task[list_task.getSelectedIndex()])).length;
                if (changedbimpl.checkdonestudent(class_choose.getId(), Integer.parseInt(task[list_task.getSelectedIndex()]))[0].equals(""))
                    done = 0;
                done += changedbimpl.checkcorrectedstudent(class_choose.getId(), Integer.parseInt(task[list_task.getSelectedIndex()])).length;
                if (changedbimpl.checkcorrectedstudent(class_choose.getId(), Integer.parseInt(task[list_task.getSelectedIndex()]))[0].equals(""))
                    done = done - 1;
                System.out.println("yi jing zuo le de" + done + " zong " + (list_student.getModel().getSize() - 1));
                dataset1.addValue(done, "已提交", "人数");  // 第一个参数是第三个参数的值，即“最高分”，第二个参数表示目录轴的分类，第三个参数表示的x轴显示标签
                dataset1.addValue(list_student.getModel().getSize() - 1 - done, "未提交", "人数");
                chartPanel.repaint();
            }
        });
        //
        JLabel label1 = new JLabel();
        label1.setFont(font1);
        label1.setText("班级名称：" + classes_view[index].getName());
        label1.setSize(label1.getText().length() * 16, 25);
        label1.setLocation((700 - label1.getWidth() - 120) / 2 + 100, 2);
        JLabel label2 = new JLabel("当前班级任务");
        label2.setFont(font1);
        label2.setBounds(320, 180, 110, 25);
        //
        JButton buttonBack = new JButton("返 回");
        buttonBack.setFont(font1);
        buttonBack.setBounds(10, 5, 100, 30);
        buttonBack.addActionListener(buttonListener1);
        //
        JButton button_publish = new JButton("发布考试");
        button_publish.setFont(font1);
        button_publish.setBounds(10, 40, 100, 30);
        button_publish.addActionListener(buttonListener1);
        //
        JButton button_correction = new JButton("批改试卷");
        button_correction.setFont(font1);
        button_correction.setBounds(10, 75, 100, 30);
        button_correction.addActionListener(buttonListener1);
        //
        JButton button_viewResults = new JButton("查看成绩");
        button_viewResults.setFont(font1);
        button_viewResults.setBounds(10, 110, 100, 30);
        button_viewResults.addActionListener(buttonListener1);
        //
        JScrollPane scrollPane = new JScrollPane(list_student, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(150, 30, 525, 150);
        JScrollPane scrollPane1 = new JScrollPane(list_task, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane1.setBounds(150, 205, 525, 150);
        //
        panel_viewClass.setLayout(null);
        panel_viewClass.add(buttonBack);
        panel_viewClass.add(scrollPane);
        panel_viewClass.add(scrollPane1);
        panel_viewClass.add(label1);
        panel_viewClass.add(label2);
        panel_viewClass.add(button_publish);
        panel_viewClass.add(chartPanel);
        panel_viewClass.add(button_correction);
        panel_viewClass.add(button_viewResults);
    }

    String[] getTestQuestionCatalog() { //获取试题目录
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

    String[] getRules() { //获取规则
        changedbimpl changedbimpl = new changedbimpl();
        this.rules1 = changedbimpl.checkrule();
        String[] strings = new String[rules1.length];
        int t = 0;
        for (rules x : rules1) {
            strings[t] = x.getName() + "  总分：" + x.getPoint();
            t++;
        }
        return strings;
    }

    void setVisible() {
        frame1.setVisible(true);
    }

    private String[] getClassName() {
        changedbimpl changedbimpl = new changedbimpl(teacher);
        classes_view = changedbimpl.checkclass();
        String[] className = new String[classes_view.length];
        for (int i = 0; i < className.length; i++) {
            classes cl = classes_view[i];
            className[i] = cl.getName();
        }
        return className;
    }

    private String[] getStudentsNames(int index) {
        class_choose = classes_view[index];
        changedbimpl changedbimpl = new changedbimpl();
        String[] strings = changedbimpl.checkclasstudent(class_choose.getId());
        strings[0] += "(老师)";
        return strings;
    }

    private String[] getTaskNames(int index) {
        class_choose = classes_view[index];
        changedbimpl changedbimpl = new changedbimpl();
        task = changedbimpl.checktaskno(class_choose.getId());
        String[] strings = changedbimpl.checkclasstask(class_choose.getId());
        return strings;
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

    private void refreshQuestions() {
        try {
            jList1.setListData(getTestQuestionCatalog());
            jList1.repaint();
        } catch (Exception e1) {
            System.out.println("刷新试题错误");
        }
    }

    private void refreshRules() {
        try {
            jList1.setListData(getRules());
            jList1.repaint();
        } catch (Exception e1) {
            System.out.println("刷新规则错误");
        }
    }

    private void initializeDialog_chooseRule() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog_chooseRule = new JDialog(frame1, "选择组卷规则", true);
        comboBox_chooseRule = new JComboBox<>();
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        Container panel_fcr = dialog_chooseRule.getContentPane();
        JLabel label_fcr = new JLabel("试卷生成规则");
        JButton button_fcr = new JButton("确定");
        //下拉框初始化
        comboBox_chooseRule.setFont(font1);
        comboBox_chooseRule.setBounds(110, 10, 125, 30);
        changedbimpl changedbimpl = new changedbimpl();
        rules1 = changedbimpl.checkrule();
        for (entity.rules rule : rules1) {
            comboBox_chooseRule.addItem(rule.getName());
        }
        //label初始化
        label_fcr.setFont(font1);
        label_fcr.setBounds(10, 10, 110, 30);
        //button初始化
        button_fcr.setFont(font1);
        button_fcr.setBounds(240, 10, 90, 30);
        button_fcr.setActionCommand("确定_chooseRule");
        button_fcr.addActionListener(new JButtonListener());
        //
        panel_fcr.setLayout(null);
        panel_fcr.add(label_fcr);
        panel_fcr.add(comboBox_chooseRule);
        panel_fcr.add(button_fcr);
        //
        dialog_chooseRule.setFont(font1);
        this.dialog_chooseRule.setSize(350, 90);
        this.dialog_chooseRule.setLocationRelativeTo(frame1);
        dialog_chooseRule.setIconImage(icon.getImage());
        dialog_chooseRule.setDefaultLookAndFeelDecorated(true);
        dialog_chooseRule.setResizable(false);
        dialog_chooseRule.setVisible(true);
    }

    private void initializeDialog_chooseExam() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog_chooseExam = new JDialog(frame1, "选择试卷", true);
        comboBox_chooseExam = new JComboBox<>();
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        Container panel_fce = dialog_chooseExam.getContentPane();
        JLabel label_fce = new JLabel("试卷");
        JButton button_fce = new JButton("确定");
        //下拉框初始化
        comboBox_chooseExam.setFont(font1);
        comboBox_chooseExam.setBounds(60, 10, 125, 30);
        changedbimpl changedbimpl = new changedbimpl();
        testPapers = changedbimpl.checkpaper();
        for (entity.testpaper tp : testPapers) {
            comboBox_chooseExam.addItem(tp.getName());
        }
        //label初始化
        label_fce.setFont(font1);
        label_fce.setBounds(10, 10, 60, 30);
        //button初始化
        button_fce.setFont(font1);
        button_fce.setBounds(200, 10, 90, 30);
        button_fce.setActionCommand("确定_chooseExam");
        button_fce.addActionListener(new JButtonListener());
        //
        panel_fce.setLayout(null);
        panel_fce.add(label_fce);
        panel_fce.add(comboBox_chooseExam);
        panel_fce.add(button_fce);
        //
        dialog_chooseExam.setFont(font1);
        this.dialog_chooseExam.setSize(310, 90);
        this.dialog_chooseExam.setLocationRelativeTo(frame1);
        dialog_chooseExam.setIconImage(icon.getImage());
        dialog_chooseExam.setDefaultLookAndFeelDecorated(true);
        dialog_chooseExam.setResizable(false);
        dialog_chooseExam.setVisible(true);
    }

    private void initializeDialog_choosePublishExam() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog_chooseExam = new JDialog(frame1, "选择试卷", true);
        comboBox_chooseExam = new JComboBox<>();
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        Container panel_fce = dialog_chooseExam.getContentPane();
        JLabel label_fce = new JLabel("试卷：");
        JLabel label_time = new JLabel("时间：");
        JLabel label_minute = new JLabel("分钟");
        JButton button_fce = new JButton("确定");
        //下拉框初始化
        comboBox_chooseExam.setFont(font1);
        comboBox_chooseExam.setBounds(60, 10, 125, 30);
        changedbimpl changedbimpl = new changedbimpl();
        testPapers = changedbimpl.checkpaper();
        for (entity.testpaper tp : testPapers) {
            comboBox_chooseExam.addItem(tp.getName());
        }
        //label初始化
        label_fce.setFont(font1);
        label_fce.setBounds(10, 10, 60, 30);
        label_time.setFont(font1);
        label_time.setBounds(195, 10, 50, 30);
        label_minute.setFont(font1);
        label_minute.setBounds(307, 10, 32, 30);
        //button初始化
        button_fce.setFont(font1);
        button_fce.setBounds(349, 10, 90, 30);
        button_fce.setActionCommand("确定_publishExam");
        button_fce.addActionListener(new JButtonListener());
        //
        textField_examTime = new JTextField();
        textField_examTime.setFont(font1);
        textField_examTime.setBounds(245, 10, 60, 30);
        LimitedDocument ld = new LimitedDocument(4);
        ld.setAllowChar("0123456789");
        textField_examTime.setDocument(ld);
        //
        panel_fce.setLayout(null);
        panel_fce.add(label_fce);
        panel_fce.add(comboBox_chooseExam);
        panel_fce.add(button_fce);
        panel_fce.add(label_time);
        panel_fce.add(textField_examTime);
        panel_fce.add(label_minute);
        //
        dialog_chooseExam.setFont(font1);
        this.dialog_chooseExam.setSize(459, 90);
        this.dialog_chooseExam.setLocationRelativeTo(frame1);
        dialog_chooseExam.setIconImage(icon.getImage());
        dialog_chooseExam.setDefaultLookAndFeelDecorated(true);
        dialog_chooseExam.setResizable(false);
        dialog_chooseExam.setVisible(true);
    }

    private void initializeDialog_addClass() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog_addClass = new JDialog(frame1, "创建班级", true);
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        Container panelThis = dialog_addClass.getContentPane();
        JLabel label1 = new JLabel("班级名称：");
        JLabel label2 = new JLabel("班级口令：");
        JButton button = new JButton("确认添加");
        textField_className = new JTextField();
        textField_classPassword = new JTextField();
        label1.setFont(font1);
        label1.setBounds(10, 10, 90, 30);
        label2.setFont(font1);
        label2.setBounds(10, 50, 90, 30);
        button.setFont(font1);
        button.setBounds(10, 90, 180, 30);
        button.addActionListener(buttonListener1);
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
        this.dialog_addClass.setSize(215, 160);
        this.dialog_addClass.setLocationRelativeTo(frame1);
        dialog_addClass.setIconImage(icon.getImage());
        dialog_addClass.setDefaultLookAndFeelDecorated(true);
        dialog_addClass.setResizable(false);
        dialog_addClass.setVisible(true);
    }

    private void initializeDialog_results() {
        JDialog dialog = new JDialog(frame1, "成绩统计", true);
        JLabel label0 = new JLabel("当前班级：大傻逼  当前试卷：第一次的期中考试你信吗");
        //
        label0.setFont(font1);
        ListModel<String> listModel1 = list_class.getModel();
        String x1 = listModel1.getElementAt(list_class.getSelectedIndex());
        listModel1 = list_task.getModel();
        String x2 = listModel1.getElementAt(list_task.getSelectedIndex());
        label0.setText("当前班级：" + x1 + "  当前试卷：" + x2);
        label0.setBounds((630 - 370) / 2, 10, 370, 30);
        //成绩表格
        String[] columnNames = new String[]{"排名", "学生名字", "分数"};
        String[][] examData = new String[][]{{"1", "zjcgg", "88"}, {"2", "zjcjj", "68"}};
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
        table.setRowHeight(30);
        table.setColumnSelectionAllowed(false);
        table.setRowSelectionAllowed(false);
        //
        JScrollPane scrollPane = new JScrollPane(table, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 50, 600, 150);
        //柱状图
        DefaultCategoryDataset dataset1 = null;
        dataset1 = new DefaultCategoryDataset();
        dataset1.addValue(1, "0~10", "");
        dataset1.addValue(2, "11~20", "");
        dataset1.addValue(3, "21~30", "");
        dataset1.addValue(4, "31~40", "");
        dataset1.addValue(5, "41~50", "");
        JFreeChart freeChart = ChartFactory.createBarChart("", // 图表标题
                "分数/分", // 水平轴的显示标签
                "人数/人", // 垂直轴的显示标签
                dataset1, // 数据集，即要显示在图表上的数据
                PlotOrientation.VERTICAL, // 图表方向：水平，垂直
                true, // 是否显示图例
                false, // 是否显示提示
                false// 是否生成URL连接
        );
        ChartPanel chartPanel = new ChartPanel(freeChart);
        chartPanel.setMouseZoomable(false);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 400));
        chartPanel.setSize(605, 355);
        chartPanel.setLocation(10, 205);
        //
        Container panel0 = dialog.getContentPane();
        panel0.setLayout(null);
        panel0.add(label0);
        panel0.add(scrollPane);
        panel0.add(chartPanel);
        //
        dialog.setSize(630, 600);
        dialog.setLocationRelativeTo(frame1);
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        dialog.setIconImage(icon.getImage());
        dialog.setDefaultLookAndFeelDecorated(true);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private void initializeScrollPane_makeExam() {
        panel_exam_right.removeAll();
        for (int i = 0; i < generateTestElement.getNumber(); i++) {
            if (i == 7) {
                dimension_per.setSize(dimension_per.getWidth(), dimension_per.getHeight() + 16);
                panel_exam_right.setPreferredSize(dimension_per);
            }
            if (i >= 8) {
                dimension_per.setSize(dimension_per.getWidth(), dimension_per.getHeight() + 46);
                panel_exam_right.setPreferredSize(dimension_per);
            }
            panel_exam_right.add(generateTestElement.getButton(i));
            panel_exam_right.add(generateTestElement.getLabel_form(i));
            panel_exam_right.add(generateTestElement.getTextField(i));
            panel_exam_right.add(generateTestElement.getLabel_grade(i));
            panel_exam_right.add(generateTestElement.getTextField_grade(i));
            panel_exam_right.add(generateTestElement.getButton_look(i));
        }
        panel_exam_right.repaint();
        panel_exam_right.validate();
        panel_exam_right.invalidate();
        scrollPane_exam.repaint();
        scrollPane_exam.validate();
        scrollPane_exam.invalidate();
    }

    private void initializeScrollPane_lookExam() {
        panel_lookExam_right.removeAll();
        for (int i = 0; i < generateTestElement_lookExam.getNumber(); i++) {
            if (i == 7) {
                dimension_ple.setSize(dimension_ple.getWidth(), dimension_ple.getHeight() + 16);
                panel_lookExam_right.setPreferredSize(dimension_ple);
            }
            if (i >= 8) {
                dimension_ple.setSize(dimension_ple.getWidth(), dimension_ple.getHeight() + 46);
                panel_lookExam_right.setPreferredSize(dimension_ple);
            }
            JTextField textField_grade = generateTestElement_lookExam.getTextField_grade(i);
            textField_grade.setEditable(false);
            JLabel label_form = generateTestElement_lookExam.getLabel_form(i);
            label_form.setLocation(5, label_form.getY());
            JTextField textField = generateTestElement_lookExam.getTextField(i);
            textField.setBounds(67, textField.getY(), textField.getWidth() + 30, textField.getHeight());
            panel_lookExam_right.add(label_form);
            panel_lookExam_right.add(textField);
            panel_lookExam_right.add(generateTestElement_lookExam.getLabel_grade(i));
            panel_lookExam_right.add(textField_grade);
            panel_lookExam_right.add(generateTestElement_lookExam.getButton_look(i));
        }
        panel_lookExam_right.repaint();
        panel_lookExam_right.validate();
        panel_lookExam_right.invalidate();
        scrollPane_lookExam.repaint();
        scrollPane_lookExam.validate();
        scrollPane_lookExam.invalidate();
    }

    private void inputTestName() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialog_inputName = new JDialog(frame1, "输入名称", true);
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        textField_testName = new JTextField();
        JLabel label = new JLabel("请输入试卷名称:");
        Container panel = dialog_inputName.getContentPane();
        JButton button = new JButton("确认");
        //
        label.setFont(font1);
        label.setBounds(10, 10, 115, 30);
        textField_testName.setFont(font1);
        textField_testName.setBounds(135, 10, 125, 30);
        button.setFont(font1);
        button.setBounds(270, 10, 80, 30);
        button.setActionCommand("确认testName");
        button.addActionListener(buttonListener1);
        //
        panel.setLayout(null);
        panel.add(label);
        panel.add(textField_testName);
        panel.add(button);
        //
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        dialog_inputName.setIconImage(icon.getImage());
        dialog_inputName.setSize(370, 90);
        dialog_inputName.setLocationRelativeTo(frame1);
        dialog_inputName.setDefaultLookAndFeelDecorated(true);
        dialog_inputName.setResizable(false);
        dialog_inputName.setVisible(true);
    }

    private class JButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("个人中心")) {
                initializePanel1();
                TeacherInterface.this.frame1.setContentPane(panel1);
                TeacherInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("修改密码")) {
                String oldPassword = new String(passwordField1.getPassword());
                String newPassword = new String(passwordField2.getPassword());
                if (oldPassword.equals(teacher.getPassword())) {
                    if (checkNewPassword()) {
                        if (teacher.getPassword().equals(newPassword)) {
                            JOptionPane.showMessageDialog(null, "新密码与旧密码相同", "提示", JOptionPane.WARNING_MESSAGE);
                        } else {
                            teacher.setPassword(newPassword);
                            accountchangeimpl accountchangeimpl = new accountchangeimpl(teacher);
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
                TeacherInterface.this.frame1.setContentPane(panel0);
                TeacherInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("返 回")) {  //从试题库面板及其他面板返回
                generateTestElement = null;
                generateTestElement_lookExam = null;
                TeacherInterface.this.frame1.setResizable(false);
                TeacherInterface.this.frame1.setSize(700, 400);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
                int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
                TeacherInterface.this.frame1.setLocation(x, y);
                TeacherInterface.this.frame1.setContentPane(panel0);
                TeacherInterface.this.frame1.revalidate();
                if (searchInterface != null)
                    searchInterface.dispose();
            } else if (e.getActionCommand().equals("试题库")) {
                try {
                    initializePanel2();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                TeacherInterface.this.frame1.setResizable(true);
                TeacherInterface.this.frame1.setContentPane(panel2);
                TeacherInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("添加试题")) {
                if (addQuestionInterface == null) {
                    NewThread1 newThread1 = new NewThread1();
                    newThread1.start();
                } else {
                    addQuestionInterface.setLook();
                }
            } else if (e.getActionCommand().equals("刷 新")) {
                if (radioButton1.isSelected()) {
                    refreshQuestions();
                } else if (radioButton2.isSelected()) {
                    refreshRules();
                }
            } else if (e.getActionCommand().equals("退出登录")) {
                frame1.dispose();
                TeacherInterface.this.mainInterface.mainFrameVisible();
                TeacherInterface.this.mainInterface.setFrameCenter();
            } else if (e.getActionCommand().equals("添加规则")) {
                if (ruleInterface == null) {
                    ruleInterface = new RuleInterface(jList1, TeacherInterface.this, radioButton1);
                } else {
                    ruleInterface.setLook();
                }
            } else if (e.getActionCommand().equals("搜索")) {
                if (searchInterface == null) {
                    searchInterface = new SearchInterface(radioButton1, TeacherInterface.this, TeacherInterface.this.jList1);
                } else {
                    searchInterface.setLook();
                }
            } else if (e.getActionCommand().equals("组卷页面")) {
                initializePanel3();
                TeacherInterface.this.frame1.setContentPane(panel3);
                TeacherInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("选择规则")) {
                initializeDialog_chooseRule();
            } else if (e.getActionCommand().equals("确定_chooseRule")) {
                try {
                    rule_choose = rules1[comboBox_chooseRule.getSelectedIndex()];
                    generateTestElement = new GenerateTestElement(rules1[comboBox_chooseRule.getSelectedIndex()], TeacherInterface.this.frame1);
                    dimension_per.setSize(frame1.getWidth() - 155, frame1.getHeight() - 50);
                    initializeScrollPane_makeExam();
                    dialog_chooseRule.dispose();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (e.getActionCommand().equals("生成试卷")) {
                if (generateTestElement == null) {
                    JOptionPane.showMessageDialog(frame1, "请先选择试卷规则", "错误", JOptionPane.ERROR_MESSAGE);
                } else {
                    inputTestName();
                }
            } else if (e.getActionCommand().equals("确认testName")) {
                try {
                    String testName = textField_testName.getText();
                    String creater = teacher.getName();
                    question[] question_test = generateTestElement.getAllQuestion();
                    for (int i = 0; i < question_test.length; i++) {
                        question_test[i].setPoint(Integer.parseInt(generateTestElement.getTextField_grade(i).getText()));
                    }
                    int gradeAll = 0;
                    //rule_choose
                    boolean allow = true;
                    for (question q : question_test) {
                        if (q == null) {
                            allow = false;
                            JOptionPane.showMessageDialog(frame1, "有题目未选择");
                            break;
                        }
                        gradeAll += q.getPoint();
                    }
                    if (gradeAll != rule_choose.getPoint()) {
                        allow = false;
                    }
                    if (allow) {
                        testpaper tp = new testpaper();
                        tp.setCreater(creater);
                        tp.setName(testName);
                        tp.setAnswernumber(question_test.length);
                        int choicenum = 0;
                        int answernum = 0;
                        int judgenum = 0;
                        for (int i = 0; i < question_test.length; i++) {
                            if (question_test[i].getForm().equals("选择题")) {
                                choicenum++;
                            } else if (question_test[i].getForm().equals("简答题")) {
                                answernum++;
                            } else
                                judgenum++;
                        }
                        tp.setChoicenumber(choicenum);
                        tp.setAnswernumber(answernum);
                        tp.setJudgenumber(judgenum);
                        tp.setQuestionnumber(choicenum + answernum + judgenum);
                        tp.setPaperpoint(gradeAll);
                        tp.setCreater(teacher.getName());
                        tp.setQuestionlist(question_test);
                        System.out.println(question_test.length);
                        changedbimpl changedbimpl = new changedbimpl(tp);
                        changedbimpl.addtestpaper();
                        for (int i = 0; i < (answernum + choicenum + judgenum); i++) {
                            changedbimpl = new changedbimpl(question_test[i]);
                            changedbimpl.addtestquestion();
                        }
                        JOptionPane.showMessageDialog(frame1, "生成试卷成功", "提示", JOptionPane.INFORMATION_MESSAGE);
                        dialog_inputName.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame1, "分数不符合要求，请使分数总和为" + rule_choose.getPoint(), "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(frame1, "请输入每道题的分数", "错误", JOptionPane.ERROR_MESSAGE);
                }

            } else if (e.getActionCommand().equals("试卷一览")) {
                initializePanel4();
                TeacherInterface.this.frame1.setContentPane(panel4);
                TeacherInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("选择试卷")) {
                initializeDialog_chooseExam();
            } else if (e.getActionCommand().equals("确定_chooseExam")) {
                testpaper testPaper_look = testPapers[comboBox_chooseExam.getSelectedIndex()];
                generateTestElement_lookExam = new GenerateTestElement(testPaper_look, frame1);
                dimension_ple.setSize(frame1.getWidth() - 155, frame1.getHeight() - 50);
                initializeScrollPane_lookExam();
                dialog_chooseExam.dispose();
            } else if (e.getActionCommand().equals("班级管理")) {
                initializePanel_class();
                TeacherInterface.this.frame1.setContentPane(panel_class);
                TeacherInterface.this.frame1.revalidate();
            } else if (e.getActionCommand().equals("添加班级")) {
                initializeDialog_addClass();
            } else if (e.getActionCommand().equals("确认添加")) {  //老师添加班级
                if (checkClassName()) {
                    String name = textField_className.getText();
                    String password = textField_classPassword.getText();
                    classes classes = new classes(name, password);
                    changedbimpl changedbimpl = new changedbimpl(classes);
                    changedbimpl.addclass();
                    int condition = changedbimpl.addteacherintoclass(teacher.getName(), teacher.getId());
                    if (condition != 1) {
                        JOptionPane.showMessageDialog(frame1, "添加班级成功");
                        dialog_addClass.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame1, "添加班级失败");
                    }
                }
            } else if (e.getActionCommand().equals("发布考试")) {
                initializeDialog_choosePublishExam();
            } else if (e.getActionCommand().equals("确定_publishExam")) {
                try {
                    testpaper testPaper_exam = testPapers[comboBox_chooseExam.getSelectedIndex()];
                    int time = Integer.parseInt(textField_examTime.getText());
                    time = time * 60;
                    changedbimpl changedbimpl = new changedbimpl();
                    changedbimpl.publishtask(class_choose.getId(), testPaper_exam.getId(), time);
                    System.out.println(class_choose.getName());
                    System.out.println(testPaper_exam.getName());
                    JOptionPane.showMessageDialog(frame1, "发布任务成功");
                    dialog_chooseExam.dispose();
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(frame1, "请输入正确的考试时间");
                }
            } else if (e.getActionCommand().equals("批改试卷")) {
                if (list_task.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(frame1, "请先选择任务!");
                } else {
                    if (studentsArray == null) {
                        JOptionPane.showMessageDialog(frame1, "该任务还暂时无需批改试卷！");
                    } else {
                        changedbimpl changedbimpl = new changedbimpl();
                        testpaper paperCorrect = changedbimpl.checktaskpaper(class_choose.getId(), Integer.parseInt(task[list_task.getSelectedIndex()]));
                        paperCorrect = changedbimpl.checkpointedpaper(paperCorrect.getId());
                        System.out.println("" + class_choose.getId() + Integer.parseInt(task[list_task.getSelectedIndex()]));
                        testDone testdone = changedbimpl.checktestdone(paperCorrect.getId(), studentsArray[0], class_choose.getId());
                        String[] answers = new String[testdone.getAnswerquestionDones().length];
                        int i = 0;
                        for (answerquestionDone temp : testdone.getAnswerquestionDones()) {
                            answers[i] = temp.getStudentanswer();
                            i++;
                        }
                        CorrectExamInterface correctExamInterface = new CorrectExamInterface(testdone, teacher, studentsArray, paperCorrect, class_choose.getId(), answers, TeacherInterface.this);
                        frame1.setVisible(false);
                    }
                }
            } else if (e.getActionCommand().equals("查看成绩")) {
                if (list_task.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(frame1, "请先选择要查看的任务!");
                } else {
                    initializeDialog_results();
                }
            }
        }
    }

    private class JRadioButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radioButton1.isSelected()) {
                refreshQuestions();
            } else if (radioButton2.isSelected()) {
                refreshRules();
            }
        }
    }

    private class NewThread1 extends Thread {
        @Override
        public void run() {
            addQuestionInterface = new AddQuestionInterface(teacher, jList1, TeacherInterface.this.radioButton1);
        }
    }

    private class JListListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (radioButton1.isSelected() && e.getButton() == MouseEvent.BUTTON1) {
                NewThread2 newThread2 = new NewThread2();
                newThread2.start();
            } else if (radioButton2.isSelected() && e.getButton() == MouseEvent.BUTTON1) {
                RuleInterface ruleInterface = new RuleInterface(jList1, TeacherInterface.this, radioButton1, rules1[jList1.getSelectedIndex()]);
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                int index = jList1.locationToIndex(e.getPoint());
                jList1.setSelectedIndex(index);
                popupMenu1.show(jScrollPane, e.getX(), e.getY());
            }
        }
    }

    private class Panel2Listener_1 extends MouseAdapter {  //右键查看菜单
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (radioButton1.isSelected() && e.getButton() == MouseEvent.BUTTON1) {
                NewThread2 newThread2 = new NewThread2();
                newThread2.start();
            } else if (radioButton2.isSelected() && e.getButton() == MouseEvent.BUTTON1) {
                RuleInterface ruleInterface = new RuleInterface(jList1, TeacherInterface.this, radioButton1, rules1[jList1.getSelectedIndex()]);
            }
        }
    }

    private class Panel2Listener_2 extends MouseAdapter {  //右键删除菜单
        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            if (radioButton1.isSelected() && e.getButton() == MouseEvent.BUTTON1) {
                //删除试题
                changedbimpl changedbimpl = new changedbimpl(questions[jList1.getSelectedIndex()]);
                changedbimpl.delquestion();
                JOptionPane.showMessageDialog(null, "删除题目成功");
                refreshQuestions();
            } else if (radioButton2.isSelected() && e.getButton() == MouseEvent.BUTTON1) {
                //删除规则
                changedbimpl changedbimpl = new changedbimpl(rules1[jList1.getSelectedIndex()]);
                changedbimpl.deleterules();
                refreshRules();
                JOptionPane.showMessageDialog(frame1, "删除规则成功");
            }
        }
    }

    private class NewThread2 extends Thread {
        @Override
        public void run() {
            MaintainQuestionsInterface maintainQuestionsInterface = new MaintainQuestionsInterface(questions[jList1.getSelectedIndex()], jList1, TeacherInterface.this, TeacherInterface.this.radioButton1);
        }
    }

    private class PaintPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setColor(Color.DARK_GRAY);
            int y = 43;
            for (int i = 0; i < 7; i++) {
                g2d.drawLine(0, y, 540, y);
                y += 46;
            }
            if (generateTestElement != null) {
                for (int i = 6; i < generateTestElement.getNumber(); i++) {
                    g2d.drawLine(0, y, 540, y);
                    y += 46;
                }
            } else if (generateTestElement_lookExam != null) {
                for (int i = 6; i < generateTestElement_lookExam.getNumber(); i++) {
                    g2d.drawLine(0, y, 540, y);
                    y += 46;
                }
            }
        }
    }

}
