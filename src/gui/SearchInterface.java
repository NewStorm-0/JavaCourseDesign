package gui;

import entity.question;
import entity.rules;
import servlet.changedbimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;

class SearchInterface {
    private JFrame frame1;
    private Font font1;
    private Container panel0;
    private JTextField textField1;
    private JComboBox<String> comboBox;
    private JList<String> list;
    private int choose;
    private question[] questions;
    private rules[] rule;
    private JRadioButton is; //检查试题库面板，显示试题单选框
    private TeacherInterface teacherInterface;
    private JList<String> teacherList;

    SearchInterface(JRadioButton is, TeacherInterface ti, JList<String> tl) {
        this.is = is;
        this.teacherInterface = ti;
        teacherList = tl;
        initializeFrame();
    }

    SearchInterface() {
        initializeFrame_Look();
    }

    private void initializeFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame1 = new JFrame("卷-搜索");
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        panel0 = frame1.getContentPane();
        initializePanel0();
        //
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame1.setSize(390, 250);
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        this.frame1.setLocation(x, y);
        frame1.setIconImage(icon.getImage());
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setResizable(false);
        frame1.setVisible(true);
    }

    private void initializeFrame_Look() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame1 = new JFrame("卷——搜索");
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        panel0 = frame1.getContentPane();
        initializePanel0_Look();
        //
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame1.setSize(390, 250);
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        this.frame1.setLocation(x, y);
        frame1.setIconImage(icon.getImage());
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setResizable(false);
        frame1.setVisible(true);
    }

    private void initializePanel0() {
        textField1 = new JTextField();
        JButton buttonSearch = new JButton();
        comboBox = new JComboBox<>();
        //搜索输入框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 10, 200, 30);
        //搜索按钮初始化
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/search.jpg";
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        buttonSearch.setIcon(new ImageIcon(img));
        buttonSearch.setBounds(335, 10, 30, 30);
        buttonSearch.addActionListener(new JButtonListener());
        //搜索类型下拉选择框初始化
        comboBox.setFont(font1);
        comboBox.addItem("试题");
        comboBox.addItem("组卷规则");
        comboBox.setBounds(215, 10, 110, 30);
        //列表框
        list = new JList<>();
        list.setSelectionMode(0);
        list.setFont(font1);
        list.setSelectionBackground(new Color(36, 116, 203));
        list.addMouseListener(new JListListener());
        list.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        //滚动面板
        JScrollPane scrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 50, 360, 160);
        //
        panel0.setLayout(null);
        panel0.add(textField1);
        panel0.add(buttonSearch);
        panel0.add(comboBox);
        panel0.add(scrollPane);
    }

    private void initializePanel0_Look() {
        textField1 = new JTextField();
        JButton buttonSearch = new JButton();
        comboBox = new JComboBox<>();
        //搜索输入框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 10, 200, 30);
        //搜索按钮初始化
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/search.jpg";
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        buttonSearch.setIcon(new ImageIcon(img));
        buttonSearch.setBounds(335, 10, 30, 30);
        buttonSearch.addActionListener(new JButtonListener());
        //搜索类型下拉选择框初始化
        comboBox.setFont(font1);
        comboBox.addItem("试题");
        comboBox.setBounds(215, 10, 110, 30);
        //列表框
        list = new JList<>();
        list.setSelectionMode(0);
        list.setFont(font1);
        list.setSelectionBackground(new Color(36, 116, 203));
        list.addMouseListener(new JListListener_Student());
        list.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        //滚动面板
        JScrollPane scrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 50, 360, 160);
        //
        panel0.setLayout(null);
        panel0.add(textField1);
        panel0.add(buttonSearch);
        panel0.add(comboBox);
        panel0.add(scrollPane);
    }

    private String[] getTestQuestionCatalog() { //获取试题目录
        String[] content = new String[questions.length];
        for (int i = 0; i < questions.length; i++) {
            content[i] = "";
            content[i] += questions[i].getForm() + " " + questions[i].getTitle();
        }
        return content;
    }

    private String[] getRules() { //获取规则
        String[] rules = new String[rule.length];
        for (int i = 0; i < rules.length; i++) {
            rules[i] = "";
            rules[i] += rule[i].getName();
        }
        return rules;
    }

    void setLook() {
        frame1.setVisible(true);
    }

    void dispose() {
        frame1.dispose();
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (Objects.equals(comboBox.getSelectedItem(), "试题")) {
                try {
                    String searchstring = textField1.getText();
                    if (searchstring.equals("")) {
                        JOptionPane.showMessageDialog(frame1, "搜索内容不可为空");
                    } else {
                        changedbimpl changedbimpl = new changedbimpl(searchstring);
                        questions = changedbimpl.searchquestion();
                        choose = 0;
                        list.setListData(getTestQuestionCatalog());
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            } else if (Objects.equals(comboBox.getSelectedItem(), "组卷规则")) {
                try {
                    String searchstring = textField1.getText();
                    if (searchstring.equals("")) {
                        JOptionPane.showMessageDialog(frame1, "搜索内容不可为空");
                    } else {
                        changedbimpl changedbimpl = new changedbimpl(searchstring);
                        rule = changedbimpl.searchrules();
                        choose = 1;
                        list.setListData(getRules());
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    private class JListListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (choose == 0 && e.getButton() == MouseEvent.BUTTON1) {
                if (questions != null) {
                    NewThread2 newThread2 = new NewThread2();
                    newThread2.start();
                }

            } else if (choose == 1 && e.getButton() == MouseEvent.BUTTON1) {
                if (rule != null) {
                    RuleInterface ruleInterface = new RuleInterface(teacherList, teacherInterface, is, rule[list.getSelectedIndex()]);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                list.clearSelection();
            }
        }
    }

    private class JListListener_Student extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (questions != null) {
                    MaintainQuestionsInterface maintainQuestionsInterface = new MaintainQuestionsInterface(questions[list.getSelectedIndex()]);
                }
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                list.clearSelection();
            }
        }
    }

    private class NewThread2 extends Thread {
        @Override
        public void run() {
            MaintainQuestionsInterface maintainQuestionsInterface = new MaintainQuestionsInterface(questions[list.getSelectedIndex()], teacherList, teacherInterface, is);
        }
    }

}

