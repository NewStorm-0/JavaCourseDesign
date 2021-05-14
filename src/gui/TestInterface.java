package gui;

import entity.*;
import servlet.changedbimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Objects;

public class TestInterface {
    private JFrame frame1;
    private Font font1;
    private final JButtonListener buttonListener;
    private JPanel panel_right; //--------------
    private JLabel label_time;
    private int[] time;
    private JLabel label1; //题目标签
    private JPanel panel_question; //-----------------
    private JButton[] buttonList;
    private Dimension dimension_pq; //选择显示的题目的面板大小
    private JPanel panel_choiceQuestion; //--------------
    private JPanel panel_cq_choice;
    private ChoiceOption_S choiceOption_s;
    private Dimension dimension_pcq;  //选项面板大小
    private JPanel panel_judgmentQuestion; //-------------
    private ButtonGroup buttonGroup;
    private JRadioButton radioButton_true;
    private JRadioButton radioButton_false;
    private JPanel panel_answerQuestion; //--------------
    private JTextArea textArea_paq;
    private JTextArea title_cq;
    private JTextArea title_jq;
    private JTextArea title_aq;
    private JPanel panel_function2;
    private int position;
    private final testpaper testPaper1;
    private final question[] questions;
    private final String[] answers;
    private final CountDown countDown;
    private final StudentInterface studentInterface;
    private final students student;
    private final int classId;
    private int second;
    private int second_gross;

    TestInterface(students s, testpaper tp, StudentInterface si, int nowSecond, int grossSecond, int classId) {
        this.second = nowSecond;
        buttonListener = new JButtonListener();
        student = s;
        testPaper1 = tp;
        questions = testPaper1.getQuestionlist();
        answers = new String[questions.length];
        studentInterface = si;
        second_gross = grossSecond;
        this.classId = classId;
        int hour = second / 3600;
        int minute = (second % 3600) / 60;
        int ss = second % 60;
        time = new int[]{hour, minute, ss};
        initializeFrame();
        countDown = new CountDown();
        countDown.start();
    }

    TestInterface(students s, testpaper tp, StudentInterface si, int nowSecond, int grossSecond, String[] answers, int classId) {
        this.second = nowSecond;
        buttonListener = new JButtonListener();
        student = s;
        testPaper1 = tp;
        questions = testPaper1.getQuestionlist();
        this.answers = answers;
        studentInterface = si;
        second_gross = grossSecond;
        this.classId = classId;
        int hour = second / 3600;
        int minute = (second % 3600) / 60;
        int ss = second % 60;
        time = new int[]{hour, minute, ss};
        initializeFrame();
        for (int i = 0; i < answers.length; i++) {
            changeQuestion(i);
            buttonList[i].setBackground(new Color(73, 81, 84));
            if (answers[i] != null) {
                buttonList[i].setForeground(new Color(243, 58, 48));
            } else {
                buttonList[i].setForeground(new Color(165, 158, 146));
            }
        }
        changeQuestion(0);
        countDown = new CountDown();
        countDown.start();
    }

    private void initializeFrame() {
        frame1 = new JFrame("卷——考试");
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame1.setSize((int) screenSize.getWidth() - 30, (int) screenSize.getHeight() - 30);
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        frame1.setLocation(x, y);
        initializePanel_right();
        initializeButtonList();
        initializePanel_function2();
        initialize_panel_choiceQuestion();
        initialize_panel_judgmentQuestion();
        initialize_panel_answerQuestion();
        frame1.setLayout(null);
        frame1.add(panel_right);
        frame1.add(panel_function2);
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                studentInterface.setVisible();
            }
        });
        changeQuestion(0);
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        frame1.setIconImage(icon.getImage());
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setBackground(new Color(48, 52, 54));
        frame1.setResizable(false);
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                super.windowClosing(e);
            }
        });
        frame1.setVisible(true);
    }

    private void initializePanel_right() {
        panel_right = new JPanel();
        panel_right.setBounds(frame1.getWidth() - 350, 2, 335, frame1.getHeight() - 40);
        JPanel panel_function = new JPanel();
        panel_function.setBounds(2, panel_right.getHeight() * 3 / 5, panel_right.getWidth() - 4, panel_right.getHeight() * 2 / 5 - 2);
        panel_function.setBackground(new Color(48, 52, 54));
        JScrollPane scrollPane_question = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane_question.setBounds(2, 2, panel_right.getWidth() - 4, (panel_right.getHeight() - 2) * 3 / 5);
        panel_question = new JPanel();
        dimension_pq = new Dimension(scrollPane_question.getWidth() - 19, scrollPane_question.getHeight() - 2);
        panel_question.setPreferredSize(dimension_pq);
        Color color1 = new Color(48, 52, 54);
        panel_question.setBackground(color1);
        scrollPane_question.setViewportView(panel_question);
        label_time = new JLabel("考试剩余时间：" + time[0] + "小时" + time[1] + "分" + time[2] + "秒");
        JButton button_submit = new JButton("提 交");
        JButton button_exit = new JButton("退 出");
        //按钮初始化
        button_submit.setFont(font1);
        button_exit.setFont(font1);
        button_submit.setBounds((panel_function.getWidth() - 120) / 2, (panel_function.getHeight() - 130) / 3 + 50, 120, 40);
        button_exit.setBounds((panel_function.getWidth() - 120) / 2, (panel_function.getHeight() - 130) * 2 / 3 + 90, 120, 40);
        button_submit.addActionListener(buttonListener);
        button_exit.addActionListener(buttonListener);
        button_submit.setOpaque(true);
        button_exit.setOpaque(true);
        button_submit.setContentAreaFilled(false);
        button_exit.setContentAreaFilled(false);
        button_submit.setBackground(color1);
        button_exit.setBackground(color1);
        button_submit.setForeground(new Color(255, 197, 26));
        button_exit.setForeground(new Color(210, 81, 76));
        button_submit.setBorder(BorderFactory.createLineBorder(new Color(255, 197, 26), 2, true));
        button_exit.setBorder(BorderFactory.createLineBorder(new Color(255, 197, 26), 2, true));
        button_submit.setFocusPainted(false);
        button_exit.setFocusPainted(false);
        //计时标签初始化
        String iconPath_time = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/time.jpg";
        ImageIcon icon_time = new ImageIcon(iconPath_time);
        Image img = icon_time.getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH);
        label_time.setIcon(new ImageIcon(img));
        label_time.setFont(font1);
        label_time.setBounds((panel_function.getWidth() - 280) / 2, 10, 280, 30);
        label_time.setForeground(new Color(165, 158, 146));
        //功能面板初始化
        panel_function.setLayout(null);
        panel_function.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        panel_function.add(label_time);
        panel_function.add(button_submit);
        panel_function.add(button_exit);
        //
        panel_question.setLayout(null);
        panel_question.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        //右面板添加面板
        panel_right.setLayout(null);
        panel_right.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        panel_right.add(panel_function);
        panel_right.add(scrollPane_question);
    }

    private void initializePanel_function2() {  //上一题下一题面板
        panel_function2 = new JPanel();
        panel_function2.setLayout(null);
        panel_function2.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        panel_function2.setBounds(1, frame1.getHeight() - 98, frame1.getWidth() - panel_right.getWidth(), 60);
        panel_function2.setBackground(new Color(48, 52, 54));
        JButton button1 = new JButton("上一题");
        JButton button2 = new JButton("下一题");
        button1.setFont(font1);
        button2.setFont(font1);
        button1.setBounds((panel_function2.getWidth() - 200) / 3, 10, 100, 40);
        button2.setBounds((panel_function2.getWidth() - 200) * 2 / 3 + 100, 10, 100, 40);
        button1.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
        button1.setOpaque(true);
        button2.setOpaque(true);
        button1.setContentAreaFilled(false);
        button2.setContentAreaFilled(false);
        Color b = new Color(48, 52, 54);
        button1.setBackground(b);
        button2.setBackground(b);
        Color f = new Color(61, 166, 242);
        button1.setForeground(f);
        button2.setForeground(f);
        Color border = new Color(0, 76, 163);
        button1.setBorder(BorderFactory.createLineBorder(border, 2, true));
        button2.setBorder(BorderFactory.createLineBorder(border, 2, true));
        panel_function2.add(button1);
        panel_function2.add(button2);
    }

    private void initializeButtonList() {
        buttonList = new JButton[testPaper1.getQuestionnumber()];
        int[] temp = new int[1];
        getAppropriateNumber((int) dimension_pq.getWidth() / 55, 10, temp);
        int number = temp[0];
        int interval = (int) (dimension_pq.getWidth() - 55 * number) / (number + 1);
        int x = interval, y = 10;
        Font font_temp = new Font("Microsoft YaHei UI", Font.BOLD, 15);
        for (int i = 0; i < buttonList.length; i++) {
            if (y > dimension_pq.getHeight()) {
                dimension_pq.setSize(dimension_pq.getWidth(), dimension_pq.getHeight() + 65);
            }
            buttonList[i] = new JButton(String.valueOf(i + 1));
            buttonList[i].setFont(font_temp);
            buttonList[i].setContentAreaFilled(false);
            buttonList[i].setOpaque(true);
            buttonList[i].setBackground(new Color(73, 81, 84));
            buttonList[i].setForeground(new Color(165, 158, 146));
            buttonList[i].setSize(55, 55);
            buttonList[i].setLocation(x, y);
            buttonList[i].setActionCommand("buttonList" + i);
            buttonList[i].addActionListener(buttonListener);
            panel_question.add(buttonList[i]);
            if ((i + 1) % number == 0) {
                x = interval;
                y += 65;
            } else {
                x += 55 + interval;
            }
        }
    }

    private void getAppropriateNumber(int number, int limit, int[] integer) {
        int interval = (int) (dimension_pq.getWidth() - 50 * number) / (number + 1);
        if (interval < limit) {
            getAppropriateNumber(number - 1, limit, integer);
        } else {
            integer[0] = number;
            return;
        }
    }

    private void initialize_panel_choiceQuestion() {
        panel_choiceQuestion = new JPanel();
        panel_choiceQuestion.setBounds(1, 1, frame1.getWidth() - 351, frame1.getHeight() - 99);
        panel_choiceQuestion.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        dimension_pcq = new Dimension();
        dimension_pcq.setSize(panel_choiceQuestion.getWidth() - 105 - 19, 198);
        panel_cq_choice = new JPanel();
        panel_cq_choice.setLayout(null);
        panel_cq_choice.setBackground(new Color(48, 52, 55));
        panel_cq_choice.setPreferredSize(dimension_pcq);
        label1 = new JLabel("999. 选择题");
        JLabel label2 = new JLabel("题目信息：");
        JLabel label3 = new JLabel("回答区域：");
        title_cq = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(title_cq, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane2 = new JScrollPane(panel_cq_choice, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        label1.setBounds((panel_choiceQuestion.getWidth() - 110) / 2, 10, 110, 35);
        label1.setOpaque(false);
        label1.setForeground(new Color(215, 218, 211));
        label2.setFont(font1);
        label2.setBounds(10, 60, 80, 30);
        label2.setOpaque(false);
        label2.setForeground(new Color(215, 218, 211));
        label3.setFont(font1);
        label3.setBounds(10, panel_choiceQuestion.getHeight() - 205, 80, 30);
        label3.setOpaque(false);
        label3.setForeground(new Color(215, 218, 211));
        //题目信息文本框
        title_cq.setFont(font1);
        title_cq.setBackground(new Color(64, 70, 72));
        title_cq.setForeground(new Color(192, 186, 178));
        title_cq.setLineWrap(true);
        title_cq.setEditable(false);
        //滚动面板初始化
        scrollPane1.setBounds(100, 60, panel_choiceQuestion.getWidth() - 105, panel_choiceQuestion.getHeight() - 275);
        scrollPane2.setBounds(100, panel_choiceQuestion.getHeight() - 205, panel_choiceQuestion.getWidth() - 105, 200);
        //添加
        panel_choiceQuestion.setLayout(null);
        panel_choiceQuestion.setBackground(new Color(48, 52, 55));
        panel_choiceQuestion.add(label2);
        panel_choiceQuestion.add(label3);
        panel_choiceQuestion.add(scrollPane1);
        panel_choiceQuestion.add(scrollPane2);
    }

    private void initialize_panel_judgmentQuestion() {
        panel_judgmentQuestion = new JPanel();
        panel_judgmentQuestion.setBounds(1, 1, frame1.getWidth() - 351, frame1.getHeight() - 99);
        panel_judgmentQuestion.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        JLabel label2 = new JLabel("题目信息：");
        JLabel label3 = new JLabel("回答区域：");
        title_jq = new JTextArea();
        radioButton_true = new JRadioButton("对");
        radioButton_false = new JRadioButton("错");
        JScrollPane scrollPane1 = new JScrollPane(title_jq, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label2.setFont(font1);
        label2.setBounds(10, 60, 80, 30);
        label2.setForeground(new Color(215, 218, 211));
        label3.setFont(font1);
        label3.setBounds(10, panel_judgmentQuestion.getHeight() - 45, 80, 30);
        label3.setForeground(new Color(215, 218, 211));
        //单选按钮
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton_true);
        buttonGroup.add(radioButton_false);
        radioButton_true.setFont(font1);
        radioButton_false.setFont(font1);
        radioButton_true.setOpaque(false);
        radioButton_false.setOpaque(false);
        radioButton_true.setBounds((panel_judgmentQuestion.getWidth() - 260) / 3 + 100, panel_judgmentQuestion.getHeight() - 45, 80, 30);
        radioButton_false.setBounds((panel_judgmentQuestion.getWidth() - 260) * 2 / 3 + 180, panel_judgmentQuestion.getHeight() - 45, 80, 30);
        radioButton_true.setForeground(new Color(255, 197, 26));
        radioButton_false.setForeground(new Color(255, 197, 26));
        //题目信息文本框
        title_jq.setFont(font1);
        title_jq.setBackground(new Color(64, 70, 72));
        title_jq.setForeground(new Color(192, 186, 178));
        title_jq.setLineWrap(true);
        title_jq.setEditable(false);
        //滚动面板初始化
        scrollPane1.setBounds(100, 60, panel_judgmentQuestion.getWidth() - 105, panel_judgmentQuestion.getHeight() - 115);
        //添加
        panel_judgmentQuestion.setLayout(null);
        panel_judgmentQuestion.setBackground(new Color(48, 52, 55));
        panel_judgmentQuestion.add(label2);
        panel_judgmentQuestion.add(label3);
        panel_judgmentQuestion.add(radioButton_true);
        panel_judgmentQuestion.add(radioButton_false);
        panel_judgmentQuestion.add(scrollPane1);
    }

    private void initialize_panel_answerQuestion() {
        panel_answerQuestion = new JPanel();
        panel_answerQuestion.setBounds(1, 1, frame1.getWidth() - 351, frame1.getHeight() - 99);
        panel_answerQuestion.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        JLabel label2 = new JLabel("题目信息：");
        JLabel label3 = new JLabel("回答区域：");
        textArea_paq = new JTextArea();
        title_aq = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(title_aq, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane2 = new JScrollPane(textArea_paq, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label2.setFont(font1);
        label2.setBounds(10, 60, 80, 30);
        label2.setForeground(new Color(215, 218, 211));
        label3.setFont(font1);
        label3.setBounds(10, (panel_answerQuestion.getHeight() - 90) / 2 + 80, 80, 30);
        label3.setForeground(new Color(215, 218, 211));
        //回答文本框
        textArea_paq.setFont(font1);
        textArea_paq.setBackground(new Color(64, 70, 72));
        textArea_paq.setForeground(new Color(192, 186, 178));
        textArea_paq.setLineWrap(true);
        //题目信息文本框
        title_aq.setFont(font1);
        title_aq.setBackground(new Color(64, 70, 72));
        title_aq.setForeground(new Color(192, 186, 178));
        title_aq.setLineWrap(true);
        title_aq.setEditable(false);
        //滚动面板初始化
        scrollPane1.setBounds(100, 60, panel_answerQuestion.getWidth() - 105, (panel_answerQuestion.getHeight() - 90) / 2);
        scrollPane2.setBounds(100, (panel_answerQuestion.getHeight() - 90) / 2 + 80, panel_answerQuestion.getWidth() - 105, (panel_answerQuestion.getHeight() - 90) / 2);
        //添加
        panel_answerQuestion.setLayout(null);
        panel_answerQuestion.setBackground(new Color(48, 52, 55));
        panel_answerQuestion.add(label2);
        panel_answerQuestion.add(label3);
        panel_answerQuestion.add(scrollPane1);
        panel_answerQuestion.add(scrollPane2);
    }

    private void changeQuestion(int x) {
        position = x;
        buttonList[position].setBackground(new Color(27, 76, 135));
        if (questions[x].getForm().equals("选择题")) {
            dimension_pcq.setSize(panel_choiceQuestion.getWidth() - 105 - 19, 198);
            panel_cq_choice.removeAll();
            if (panel_judgmentQuestion != null) {
                frame1.remove(panel_judgmentQuestion);
            }
            if (panel_answerQuestion != null) {
                frame1.remove(panel_answerQuestion);
            }
            choicequestion choicequestion = (entity.choicequestion) questions[x];
            choice[] choices = choicequestion.getChoices();
            choiceOption_s = new ChoiceOption_S(choices);
            int[] temp = new int[1];
            getAppropriateNumber((int) dimension_pcq.getWidth() / 245, 10, temp);
            int number = temp[0];
            int interval = (int) (dimension_pcq.getWidth() - 245 * number) / (number + 1);
            int x1 = interval, y1 = 45, x2 = interval + 40, y2 = 5;
            for (int k = 0; k < choices.length; k++) {
                if (y1 > dimension_pcq.getHeight()) {
                    dimension_pcq.setSize(dimension_pcq.getWidth(), dimension_pcq.getHeight() + 95);
                }
                choiceOption_s.getRadioButton(k).setLocation(x1, y1);
                choiceOption_s.getScrollPane(k).setLocation(x2, y2);
                if ((k + 1) % number == 0) {
                    x1 = interval;
                    x2 = interval + 40;
                    y1 += 95;
                    y2 += 95;
                } else {
                    x1 += 245 + interval;
                    x2 += 245 + interval;
                }
                panel_cq_choice.add(choiceOption_s.getRadioButton(k));
                panel_cq_choice.add(choiceOption_s.getScrollPane(k));
            }
            if (answers[position] != null) {
                for (int i = 0; i < answers[position].length(); i++) {
                    if (answers[position].charAt(i) == '1') {
                        choiceOption_s.getRadioButton(i).setSelected(true);
                    }
                }
            }
            title_cq.setText(questions[x].getTitle());
            label1.setText(x + 1 + ". " + questions[x].getForm());
            panel_choiceQuestion.add(label1);
            panel_cq_choice.revalidate();
            panel_cq_choice.repaint();
            panel_choiceQuestion.revalidate();
            panel_choiceQuestion.repaint();
            frame1.add(panel_choiceQuestion);
        } else if (questions[x].getForm().equals("判断题")) {
            if (panel_choiceQuestion != null) {
                frame1.remove(panel_choiceQuestion);
            }
            if (panel_answerQuestion != null) {
                frame1.remove(panel_answerQuestion);
            }
            if (answers[position] != null) {
                if (answers[position].equals("0")) {
                    radioButton_true.setSelected(false);
                    radioButton_false.setSelected(true);
                } else if (answers[position].equals("1")) {
                    radioButton_true.setSelected(true);
                    radioButton_false.setSelected(false);
                } else if (answers[position].equals("-1")) {
                    buttonGroup.clearSelection();
                } else {
                    System.out.println("判断题答案出错");
                }
            } else {
                buttonGroup.clearSelection();
            }
            title_jq.setText(questions[x].getTitle());
            label1.setText(x + 1 + ". " + questions[x].getForm());
            panel_judgmentQuestion.add(label1);
            panel_judgmentQuestion.revalidate();
            panel_judgmentQuestion.repaint();
            frame1.add(panel_judgmentQuestion);
        } else if (questions[x].getForm().equals("简答题")) {
            if (panel_judgmentQuestion != null) {
                frame1.remove(panel_judgmentQuestion);
            }
            if (panel_choiceQuestion != null) {
                frame1.remove(panel_choiceQuestion);
            }
            if (answers[position] != null) {
                textArea_paq.setText(answers[position]);
            } else {
                textArea_paq.setText("");
            }
            title_aq.setText(questions[x].getTitle());
            label1.setText(x + 1 + ". " + questions[x].getForm());
            panel_answerQuestion.add(label1);
            panel_answerQuestion.revalidate();
            panel_answerQuestion.repaint();
            frame1.add(panel_answerQuestion);
        } else {
            System.out.println("题目类型匹配错误");
        }
        frame1.revalidate();
        frame1.repaint();
    }

    private void saveAnswer() {
        buttonList[position].setBackground(new Color(73, 81, 84));
        String form = questions[position].getForm();
        if (form.equals("选择题")) {
            StringBuilder temp = new StringBuilder("");
            boolean empty = true;
            for (int i = 0; i < choiceOption_s.getSize(); i++) {
                if (choiceOption_s.getRadioButton(i).isSelected()) {
                    temp.append("1");
                    empty = false;
                } else {
                    temp.append("0");
                }
            }
            if (empty) {
                answers[position] = null;
            } else {
                answers[position] = temp.toString();
            }
        } else if (form.equals("判断题")) {
            if (radioButton_true.isSelected() && !radioButton_false.isSelected()) {
                answers[position] = "1";
            } else if ((!radioButton_true.isSelected()) && radioButton_false.isSelected()) {
                answers[position] = "0";
            } else if (!(radioButton_true.isSelected() || radioButton_false.isSelected())) {
                answers[position] = null;
            } else {
                JOptionPane.showMessageDialog(frame1, "判断题出现多选情况", "错误：", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (textArea_paq.getText().equals("")) {
                answers[position] = null;
            } else {
                answers[position] = textArea_paq.getText();
            }
        }
        if (answers[position] != null) {
            buttonList[position].setForeground(new Color(243, 58, 48));
        } else {
            buttonList[position].setForeground(new Color(165, 158, 146));
        }
    }

    private void supplyAnswer() {
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == null) {
                if (questions[i].getForm().equals("选择题")) {
                    choicequestion choicequestion = (entity.choicequestion) questions[i];
                    choice[] choices = choicequestion.getChoices();
                    StringBuilder a = new StringBuilder();
                    for (int j = 0; j < choices.length; j++) {
                        a.append("0");
                    }
                    answers[i] = a.toString();
                } else if (questions[i].getForm().equals("判断题")) {
                    answers[i] = "-1";
                } else {
                    answers[i] = "";
                }
            }
        }
    }

    private void automaticMark() {
        JDialog dialog = new JDialog(frame1, "客观题得分结果");
        dialog.setBackground(new Color(48, 52, 55));
        dialog.setFont(font1);
        dialog.setSize(400, 240);
        dialog.setLocationRelativeTo(frame1);
        dialog.setUndecorated(true);
        dialog.setDefaultLookAndFeelDecorated(true);
        dialog.setResizable(false);
        Container panel0 = dialog.getContentPane();
        panel0.setLayout(null);
        panel0.setBackground(new Color(48, 52, 55));
        JLabel label1 = new JLabel("客观题得分结果:");
        label1.setFont(font1);
        label1.setForeground(new Color(211, 206, 200));
        label1.setBounds(20, 20, 180, 30);
        JLabel label2 = new JLabel("自动批改客观题中");
        label2.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 30));
        label2.setBounds((dialog.getWidth() - 220) / 2, 70, 220, 60);
        label2.setForeground(new Color(211, 206, 200));
        panel0.add(label1);
        panel0.add(label2);
        dialog.setVisible(true);
        int choiceQuestionsNumber = testPaper1.getChoicenumber();
        int judgmentQuestionsNumber = testPaper1.getJudgenumber();
        int grade = 0, choiceQuestionRight = 0, judgmentQuestionRight = 0, grade_choice = 0, grade_judgment = 0;
        for (int i = 0; i < choiceQuestionsNumber; i++) {
            if (answers[i].equals(questions[i].getAnswer())) {
                grade += questions[i].getPoint();
                grade_choice += questions[i].getPoint();
                choiceQuestionRight++;
            }
        }
        for (int i = judgmentQuestionsNumber; i <judgmentQuestionsNumber+choiceQuestionsNumber ; i++) {
            if (answers[i].equals(questions[i].getAnswer())) {
                grade += questions[i].getPoint();
                grade_judgment += questions[i].getPoint();
                judgmentQuestionRight++;
            }
        }
        JLabel label3 = new JLabel("你的客观题得分为：" + grade + "分");
        label3.setFont(font1);
        label3.setBounds((dialog.getWidth() - 230) / 2, 70, 230, 30);
        label3.setForeground(new Color(211, 206, 200));
        JLabel label4 = new JLabel("选择题做对个数：" + choiceQuestionRight + "(共" + choiceQuestionsNumber + "道)，得分为" + grade_choice + "分");
        label4.setFont(font1);
        label4.setBounds((dialog.getWidth() - 320) / 2, 120, 320, 30);
        label4.setForeground(new Color(211, 206, 200));
        JLabel label5 = new JLabel("判断题做对个数：" + judgmentQuestionRight + "(共" + judgmentQuestionsNumber + "道)，得分为" + grade_judgment + "分");
        label5.setFont(font1);
        label5.setBounds((dialog.getWidth() - 320) / 2, 170, 320, 30);
        label5.setForeground(new Color(211, 206, 200));
        JButton button_back = new JButton("退 出");
        button_back.setFont(font1);
        button_back.setOpaque(false);
        button_back.setContentAreaFilled(false);
        Color b = new Color(48, 52, 54);
        button_back.setBackground(b);
        Color f = new Color(61, 166, 242);
        button_back.setForeground(f);
        Color border = new Color(0, 76, 163);
        button_back.setBorder(BorderFactory.createLineBorder(border, 2, true));
        button_back.setBounds((dialog.getWidth() - 100) / 2, 205, 100, 30);
        button_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                studentInterface.setVisible();
                frame1.dispose();
            }
        });
        panel0.remove(label2);
        panel0.add(label3);
        panel0.add(label4);
        panel0.add(label5);
        panel0.add(button_back);
        panel0.repaint();
        panel0.revalidate();
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("提 交")) {
                saveAnswer();
                boolean over = true;
                for (int i = 0; i < answers.length; i++) {
                    if (answers[i] == null) {
                        over = false;
                        break;
                    }
                }
                if (over) {
                    countDown.setOver();
                    choicequestionDone[] choicequestionDones = new choicequestionDone[testPaper1.getChoicenumber()];
                    answerquestionDone[] answerquestionDones = new answerquestionDone[testPaper1.getAnswernumber()];
                    judgequestionDone[] judgequestionDones = new judgequestionDone[testPaper1.getJudgenumber()];
                    int i1 = 0, i2 = 0, i3 = 0;
                    for (int i = 0; i < questions.length; i++) {
                        if (questions[i].getForm().equals("选择题")) {
                            choicequestion choicequestion = (choicequestion) questions[i];
                            choicequestionDones[i1] = new choicequestionDone();
                            choicequestionDones[i1].setForm(choicequestion.getForm());
                            choicequestionDones[i1].setDifficulty(choicequestion.getDifficulty());
                            choicequestionDones[i1].setAnswer(choicequestion.getAnswer());
                            choicequestionDones[i1].setTitle(choicequestion.getTitle());
                            choicequestionDones[i1].setPoint(choicequestion.getPoint());
                            choicequestionDones[i1].setChoices(choicequestion.getChoices());
                            if(choicequestionDones[i1].getAnswer().equals(answers[i]))
                                choicequestionDones[i1].setScore(choicequestion.getPoint());
                            else
                                choicequestionDones[i1].setScore(0);
                            choicequestionDones[i1].setStudentanswer(answers[i]);
                            i1++;
                        } else if (questions[i].getForm().equals("简答题")) {
                            answerquestion answerquestion = (answerquestion) questions[i];
                            answerquestionDones[i2] = new answerquestionDone();
                            answerquestionDones[i2].setForm(answerquestion.getForm());
                            answerquestionDones[i2].setDifficulty(answerquestion.getDifficulty());
                            answerquestionDones[i2].setAnswer(answerquestion.getAnswer());
                            answerquestionDones[i2].setTitle(answerquestion.getTitle());
                            answerquestionDones[i2].setPoint(answerquestion.getPoint());
                            answerquestionDones[i2].setScore(0);
                            answerquestionDones[i2].setStudentanswer(answers[i]);
                            i2++;
                        } else {
                            judgequestion judgequestion = (judgequestion) questions[i];
                            judgequestionDones[i3] = new judgequestionDone();
                            judgequestionDones[i3].setForm(judgequestion.getForm());
                            judgequestionDones[i3].setDifficulty(judgequestion.getDifficulty());
                            judgequestionDones[i3].setAnswer(judgequestion.getAnswer());
                            judgequestionDones[i3].setTitle(judgequestion.getTitle());
                            judgequestionDones[i3].setPoint(judgequestion.getPoint());
                            if(judgequestionDones[i3].getAnswer().equals(answers[i]))
                                judgequestionDones[i3].setScore(judgequestion.getPoint());
                            else
                                judgequestionDones[i3].setScore(0);
                            judgequestionDones[i3].setStudentanswer(answers[i]);
                            i3++;
                        }
                    }
                    testDone testDone = new testDone();
                    testDone.setId(testPaper1.getId());
                    testDone.setJudgenumber(testPaper1.getJudgenumber());
                    testDone.setAnswernumber(testPaper1.getAnswernumber());
                    testDone.setChoicenumber(testPaper1.getChoicenumber());
                    testDone.setCreater(testPaper1.getCreater());
                    testDone.setName(testPaper1.getName());
                    testDone.setPaperpoint(testPaper1.getPaperpoint());
                    testDone.setQuestionnumber(testPaper1.getQuestionnumber());
                    testDone.setAnswerquestionDones(answerquestionDones);
                    testDone.setChoicequestionDones(choicequestionDones);
                    testDone.setJudgequestionDones(judgequestionDones);
                    changedbimpl changedbimpl = new changedbimpl(student, testDone);
                    int state = 2;
                    int times = second_gross - (time[0] * 3600 + time[1] * 60 + time[2]);
                    changedbimpl.addtestdone(0,times, classId, state);
                    frame1.setEnabled(false);
                    automaticMark();
                } else {
                    int execute = JOptionPane.showConfirmDialog(frame1, "还有题目未完成，仍要继续提交吗？", "提示", JOptionPane.YES_NO_OPTION);
                    if (execute == JOptionPane.YES_OPTION) {
                        supplyAnswer();
                        countDown.setOver();
                        choicequestionDone[] choicequestionDones = new choicequestionDone[testPaper1.getChoicenumber()];
                        answerquestionDone[] answerquestionDones = new answerquestionDone[testPaper1.getAnswernumber()];
                        judgequestionDone[] judgequestionDones = new judgequestionDone[testPaper1.getJudgenumber()];
                        int i1 = 0, i2 = 0, i3 = 0;
                        for (int i = 0; i < questions.length; i++) {
                            if (questions[i].getForm().equals("选择题")) {
                                choicequestion choicequestion = (choicequestion) questions[i];
                                choicequestionDones[i1] = new choicequestionDone();
                                choicequestionDones[i1].setForm(choicequestion.getForm());
                                choicequestionDones[i1].setDifficulty(choicequestion.getDifficulty());
                                choicequestionDones[i1].setAnswer(choicequestion.getAnswer());
                                choicequestionDones[i1].setTitle(choicequestion.getTitle());
                                choicequestionDones[i1].setPoint(choicequestion.getPoint());
                                choicequestionDones[i1].setChoices(choicequestion.getChoices());
                                if(choicequestionDones[i1].getAnswer().equals(answers[i]))
                                    choicequestionDones[i1].setScore(choicequestion.getPoint());
                                else
                                choicequestionDones[i1].setScore(0);
                                choicequestionDones[i1].setStudentanswer(answers[i]);
                                i1++;
                            } else if (questions[i].getForm().equals("简答题")) {
                                answerquestion answerquestion = (answerquestion) questions[i];
                                answerquestionDones[i2] = new answerquestionDone();
                                answerquestionDones[i2].setForm(answerquestion.getForm());
                                answerquestionDones[i2].setDifficulty(answerquestion.getDifficulty());
                                answerquestionDones[i2].setAnswer(answerquestion.getAnswer());
                                answerquestionDones[i2].setTitle(answerquestion.getTitle());
                                answerquestionDones[i2].setPoint(answerquestion.getPoint());
                                answerquestionDones[i2].setScore(0);
                                answerquestionDones[i2].setStudentanswer(answers[i]);
                                i2++;
                            } else {
                                judgequestion judgequestion = (judgequestion) questions[i];
                                judgequestionDones[i3] = new judgequestionDone();
                                judgequestionDones[i3].setForm(judgequestion.getForm());
                                judgequestionDones[i3].setDifficulty(judgequestion.getDifficulty());
                                judgequestionDones[i3].setAnswer(judgequestion.getAnswer());
                                judgequestionDones[i3].setTitle(judgequestion.getTitle());
                                judgequestionDones[i3].setPoint(judgequestion.getPoint());
                                if(judgequestionDones[i3].getAnswer().equals(answers[i]))
                                    judgequestionDones[i3].setScore(judgequestion.getPoint());
                                else
                                    judgequestionDones[i3].setScore(0);
                                judgequestionDones[i3].setStudentanswer(answers[i]);
                                i3++;
                            }
                        }
                        testDone testDone = new testDone();
                        testDone.setId(testPaper1.getId());
                        testDone.setJudgenumber(testPaper1.getJudgenumber());
                        testDone.setAnswernumber(testPaper1.getAnswernumber());
                        testDone.setChoicenumber(testPaper1.getChoicenumber());
                        testDone.setCreater(testPaper1.getCreater());
                        testDone.setName(testPaper1.getName());
                        testDone.setPaperpoint(testPaper1.getPaperpoint());
                        testDone.setQuestionnumber(testPaper1.getQuestionnumber());
                        testDone.setAnswerquestionDones(answerquestionDones);
                        testDone.setChoicequestionDones(choicequestionDones);
                        testDone.setJudgequestionDones(judgequestionDones);
                        changedbimpl changedbimpl = new changedbimpl(student, testDone);
                        int state = 2;
                        int times = second_gross - (time[0] * 3600 + time[1] * 60 + time[2]);
                        changedbimpl.addtestdone(0,times, classId, state);
                        frame1.setEnabled(false);
                        automaticMark();
                    }
                }
            } else if (e.getActionCommand().equals("退 出")) {
                saveAnswer();
                supplyAnswer();
                countDown.setOver();
                choicequestionDone[] choicequestionDones = new choicequestionDone[testPaper1.getChoicenumber()];
                answerquestionDone[] answerquestionDones = new answerquestionDone[testPaper1.getAnswernumber()];
                judgequestionDone[] judgequestionDones = new judgequestionDone[testPaper1.getJudgenumber()];
                int i1 = 0, i2 = 0, i3 = 0;
                for (int i = 0; i < questions.length; i++) {
                    if (questions[i].getForm().equals("选择题")) {
                        choicequestion choicequestion = (choicequestion) questions[i];
                        choicequestionDones[i1] = new choicequestionDone();
                        choicequestionDones[i1].setForm(choicequestion.getForm());
                        choicequestionDones[i1].setDifficulty(choicequestion.getDifficulty());
                        choicequestionDones[i1].setAnswer(choicequestion.getAnswer());
                        choicequestionDones[i1].setTitle(choicequestion.getTitle());
                        choicequestionDones[i1].setPoint(choicequestion.getPoint());
                        choicequestionDones[i1].setChoices(choicequestion.getChoices());
                        choicequestionDones[i1].setScore(0);
                        choicequestionDones[i1].setStudentanswer(answers[i]);
                        i1++;
                    } else if (questions[i].getForm().equals("简答题")) {
                        answerquestion answerquestion = (answerquestion) questions[i];
                        answerquestionDones[i2] = new answerquestionDone();
                        answerquestionDones[i2].setForm(answerquestion.getForm());
                        answerquestionDones[i2].setDifficulty(answerquestion.getDifficulty());
                        answerquestionDones[i2].setAnswer(answerquestion.getAnswer());
                        answerquestionDones[i2].setTitle(answerquestion.getTitle());
                        answerquestionDones[i2].setPoint(answerquestion.getPoint());
                        answerquestionDones[i2].setScore(0);
                        answerquestionDones[i2].setStudentanswer(answers[i]);
                        i2++;
                    } else {
                        judgequestion judgequestion = (judgequestion) questions[i];
                        judgequestionDones[i3] = new judgequestionDone();
                        judgequestionDones[i3].setForm(judgequestion.getForm());
                        judgequestionDones[i3].setDifficulty(judgequestion.getDifficulty());
                        judgequestionDones[i3].setAnswer(judgequestion.getAnswer());
                        judgequestionDones[i3].setTitle(judgequestion.getTitle());
                        judgequestionDones[i3].setPoint(judgequestion.getPoint());
                        judgequestionDones[i3].setScore(0);
                        judgequestionDones[i3].setStudentanswer(answers[i]);
                        i3++;
                    }
                }
                testDone testDone = new testDone();
                testDone.setId(testPaper1.getId());
                testDone.setJudgenumber(testPaper1.getJudgenumber());
                testDone.setAnswernumber(testPaper1.getAnswernumber());
                testDone.setChoicenumber(testPaper1.getChoicenumber());
                testDone.setCreater(testPaper1.getCreater());
                testDone.setName(testPaper1.getName());
                testDone.setPaperpoint(testPaper1.getPaperpoint());
                testDone.setQuestionnumber(testPaper1.getQuestionnumber());
                testDone.setAnswerquestionDones(answerquestionDones);
                testDone.setChoicequestionDones(choicequestionDones);
                testDone.setJudgequestionDones(judgequestionDones);
                changedbimpl changedbimpl = new changedbimpl(student, testDone);
                int state = 1;
                int times = second_gross - (time[0] * 3600 + time[1] * 60 + time[2]);
                changedbimpl.addtestdone(0,times, classId, state);
                frame1.dispose();
                studentInterface.setVisible();
            } else if (e.getActionCommand().equals("上一题")) {
                if (position == 0) {
                    saveAnswer();
                    JOptionPane.showMessageDialog(frame1, "已经是第一道题");
                } else {
                    saveAnswer();
                    changeQuestion(position - 1);
                }
            } else if (e.getActionCommand().equals("下一题")) {
                if (position == questions.length - 1) {
                    saveAnswer();
                    JOptionPane.showMessageDialog(frame1, "已经是最后一道题");
                } else {
                    saveAnswer();
                    changeQuestion(position + 1);
                }
            } else if (e.getActionCommand().startsWith("buttonList")) {
                saveAnswer();
                int number = Integer.parseInt(e.getActionCommand().substring(10));
                changeQuestion(number);
            }
        }
    }

    private class CountDown extends Thread {
        private int over = 0;

        @Override
        public void run() {
            super.run();
            while ((time[0] + time[1] + time[2]) != 0 && over == 0) {
                try {
                    Thread.sleep(998);
                    if (time[2] == 0) {
                        if (time[1] == 0) {
                            time[0] -= 1;
                            time[1] = 59;
                        } else {
                            time[1] -= 1;
                        }
                        time[2] = 59;
                    } else {
                        time[2] -= 1;
                    }
                    label_time.setText("考试剩余时间：" + time[0] + "小时" + time[1] + "分" + time[2] + "秒");
                    if (time[0] == 0 && time[1] < 10) {
                        label_time.setForeground(new Color(225, 23, 13));
                    }
                    label_time.repaint();
                    panel_right.repaint();
                    panel_right.invalidate();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println("计时出现错误");
                }
            }
            if (over == 0) {
                saveAnswer();
                supplyAnswer();
                choicequestionDone[] choicequestionDones = new choicequestionDone[testPaper1.getChoicenumber()];
                answerquestionDone[] answerquestionDones = new answerquestionDone[testPaper1.getAnswernumber()];
                judgequestionDone[] judgequestionDones = new judgequestionDone[testPaper1.getJudgenumber()];
                int i1 = 0, i2 = 0, i3 = 0;
                for (int i = 0; i < questions.length; i++) {
                    if (questions[i].getForm().equals("选择题")) {
                        choicequestion choicequestion = (choicequestion) questions[i];
                        choicequestionDones[i1] = new choicequestionDone();
                        choicequestionDones[i1].setForm(choicequestion.getForm());
                        choicequestionDones[i1].setDifficulty(choicequestion.getDifficulty());
                        choicequestionDones[i1].setAnswer(choicequestion.getAnswer());
                        choicequestionDones[i1].setTitle(choicequestion.getTitle());
                        choicequestionDones[i1].setPoint(choicequestion.getPoint());
                        choicequestionDones[i1].setChoices(choicequestion.getChoices());
                        choicequestionDones[i1].setScore(0);
                        choicequestionDones[i1].setStudentanswer(answers[i]);
                        i1++;
                    } else if (questions[i].getForm().equals("简答题")) {
                        answerquestion answerquestion = (answerquestion) questions[i];
                        answerquestionDones[i2] = new answerquestionDone();
                        answerquestionDones[i2].setForm(answerquestion.getForm());
                        answerquestionDones[i2].setDifficulty(answerquestion.getDifficulty());
                        answerquestionDones[i2].setAnswer(answerquestion.getAnswer());
                        answerquestionDones[i2].setTitle(answerquestion.getTitle());
                        answerquestionDones[i2].setPoint(answerquestion.getPoint());
                        answerquestionDones[i2].setScore(0);
                        answerquestionDones[i2].setStudentanswer(answers[i]);
                        i2++;
                    } else {
                        judgequestion judgequestion = (judgequestion) questions[i];
                        judgequestionDones[i3] = new judgequestionDone();
                        judgequestionDones[i3].setForm(judgequestion.getForm());
                        judgequestionDones[i3].setDifficulty(judgequestion.getDifficulty());
                        judgequestionDones[i3].setAnswer(judgequestion.getAnswer());
                        judgequestionDones[i3].setTitle(judgequestion.getTitle());
                        judgequestionDones[i3].setPoint(judgequestion.getPoint());
                        judgequestionDones[i3].setScore(0);
                        judgequestionDones[i3].setStudentanswer(answers[i]);
                        i3++;
                    }
                }
                testDone testDone = new testDone();
                testDone.setJudgenumber(testPaper1.getJudgenumber());
                testDone.setAnswernumber(testPaper1.getAnswernumber());
                testDone.setChoicenumber(testPaper1.getChoicenumber());
                testDone.setCreater(testPaper1.getCreater());
                testDone.setName(testPaper1.getName());
                testDone.setPaperpoint(testPaper1.getPaperpoint());
                testDone.setQuestionnumber(testPaper1.getQuestionnumber());
                testDone.setAnswerquestionDones(answerquestionDones);
                testDone.setChoicequestionDones(choicequestionDones);
                testDone.setJudgequestionDones(judgequestionDones);
                changedbimpl changedbimpl = new changedbimpl(student, testDone);
                int state = 2;
                int times = second_gross - (time[0] * 3600 + time[1] * 60 + time[2]);
                changedbimpl.addtestdone(0,times, classId, state);
                frame1.setEnabled(false);
                JOptionPane.showMessageDialog(frame1, "考试时间已结束,系统已自动帮您提交了试卷.即将退出考试界面.");
                frame1.setEnabled(false);
                automaticMark();
            } else if (over == 1) {
                this.interrupt();
                System.out.println("退出计时");
            }
        }

        void setOver() {
            over = 1;
        }

    }

}
