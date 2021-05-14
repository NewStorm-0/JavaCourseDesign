package gui;

import entity.question;
import servlet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

class ChooseQuestion {
    private JDialog frame1;
    private Font font1;
    private Container panel1;
    private JTextField textField1;
    private JList<String> list;
    private final String form;
    private final question[] questions;
    private final int index;
    private final JFrame parentFrame;
    private question[] questions_search;
    private JTextField showTextField;

    ChooseQuestion(question[] questions, JTextField tf, int i, String s, JFrame frame) {
        this.questions = questions;
        showTextField = tf;
        index = i;
        form = s;
        parentFrame = frame;
        initializeFrame();
    }

    private void initializeFrame() {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame1 = new JDialog(parentFrame, "卷-添加" + form, true);
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        panel1 = frame1.getContentPane();
        initializePanel1();
        //
        this.frame1.setSize(390, 250);
        this.frame1.setLocationRelativeTo(parentFrame);
        frame1.setIconImage(icon.getImage());
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setResizable(false);
        frame1.setVisible(true);
    }

    private void initializePanel1() {
        textField1 = new JTextField();
        JButton buttonSearch = new JButton();
        JButton buttonChoose = new JButton("选 择");
        JButtonListener buttonListener = new JButtonListener();
        //搜索输入框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 10, 219, 30);
        //搜索按钮初始化
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/search.jpg";
        ImageIcon icon = new ImageIcon(iconPath);
        Image img = icon.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        buttonSearch.setIcon(new ImageIcon(img));
        buttonSearch.setBounds(239, 10, 30, 30);
        buttonSearch.setActionCommand("查找");
        buttonSearch.addActionListener(buttonListener);
        //选择按钮初始化
        buttonChoose.setFont(font1);
        buttonChoose.setBounds(279, 10, 90, 30);
        buttonChoose.addActionListener(buttonListener);
        //列表框
        list = new JList<>();
        list.setSelectionMode(0);
        list.setFont(font1);
        list.setSelectionBackground(new Color(36, 116, 203));
        list.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        initializeListData();
        //滚动面板
        JScrollPane scrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setBounds(10, 50, 360, 160);
        //
        panel1.setLayout(null);
        panel1.add(textField1);
        panel1.add(buttonSearch);
        panel1.add(scrollPane);
        panel1.add(buttonChoose);
    }

    private void initializeListData() {
        if (form.equals("选择题")) {
            changedbimpl changedbimpl = new changedbimpl();
            questions_search = changedbimpl.checkchoicequestion();
        } else if (form.equals("简答题")) {
            changedbimpl changedbimpl = new changedbimpl();
            questions_search = changedbimpl.checkanswerquestion();
        } else {
            changedbimpl changedbimpl = new changedbimpl();
            questions_search = changedbimpl.checkjudgequestion();
        }
        setListData();
    }

    private void setListData() {
        String[] data = new String[questions_search.length];
        int i = 0;
        for (question q : questions_search) {
            data[i] = q.getTitle();
            i++;
        }
        list.setListData(data);
        list.repaint();
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("查找")) {
                String s = textField1.getText();
                if (s.equals("")) {
                    if (form.equals("选择题")) {
                        changedbimpl changedbimpl = new changedbimpl();
                        questions_search = changedbimpl.checkchoicequestion();
                    } else if (form.equals("简答题")) {
                        changedbimpl changedbimpl = new changedbimpl();
                        questions_search = changedbimpl.checkanswerquestion();
                    } else {
                        changedbimpl changedbimpl = new changedbimpl();
                        questions_search = changedbimpl.checkjudgequestion();
                    }
                } else {
                    if (form.equals("选择题")) {
                        System.out.println(":" + s + ":");
                        changedbimpl changedbimpl = new changedbimpl(s);
                        questions_search = changedbimpl.searchchoicequestion();
                    } else if (form.equals("简答题")) {
                        changedbimpl changedbimpl = new changedbimpl(s);
                        questions_search = changedbimpl.searchanswerquestion();
                    } else {
                        changedbimpl changedbimpl = new changedbimpl(s);
                        questions_search = changedbimpl.searchjudgequestion();
                    }
                }
                setListData();
            } else if (e.getActionCommand().equals("选 择")) {
                questions[index] = questions_search[list.getSelectedIndex()];
                showTextField.setText(questions_search[list.getSelectedIndex()].getTitle());
                frame1.dispose();
            }
        }
    }

}
