package gui;

import entity.choice;
import entity.choicequestion;
import entity.question;
import servlet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MaintainQuestionsInterface {
    private final JFrame frame;
    private JPanel panel1;
    private final question question1;
    private final Font font1;
    private JTextField textField1; //难度系数输入框
    private JPanel panel_choice; //选择题查看面板选项面板滑动面板中的面板
    private JScrollPane jScrollPaneChoice; //选择题查看面板选项面板滑动面板
    private Dimension dimension1; //选择题查看面板选项面板滑动面板中的面板大小
    private ChoiceOption_T choiceOption_T; //选择题选项
    private JButton buttonAddChoice; //选择题增加选项按钮
    private JButton buttonInitializeChoices; //选择题初始化选项按钮
    private JTextArea textArea1; //题目内容
    private JTextArea textArea2; //题目答案
    private JRadioButton radioButton1; //正确单选框
    private JRadioButton radioButton2; // 错误单选框
    private JList<String> jList;
    private TeacherInterface teacherInterface;
    private JRadioButton is;  //检查试题库面板，显示试题单选框

    MaintainQuestionsInterface(question q, JList<String> jList, TeacherInterface ti, JRadioButton is) {
        this.question1 = q;
        this.jList = jList;
        this.teacherInterface = ti;
        this.is = is;
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame = new JFrame("试题类型：" + question1.getForm());
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        if (question1.getForm().equals("选择题")) {
            initializeChoicePanel();
        } else if (question1.getForm().equals("判断题")) {
            initializeJudgementPanel();
        } else if (question1.getForm().equals("简答题")) {
            initializeAnswerPanel();
        } else {
            JOptionPane.showMessageDialog(null, "没找到试题种类");
        }
        initializeFrame();
        frame.setContentPane(panel1);
    }

    MaintainQuestionsInterface(question q) {
        this.question1 = q;
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        frame = new JFrame("试题类型：" + question1.getForm());
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        if (question1.getForm().equals("选择题")) {
            initializeChoicePanel_Look();
        } else if (question1.getForm().equals("判断题")) {
            initializeJudgementPanel_Look();
        } else if (question1.getForm().equals("简答题")) {
            initializeAnswerPanel_Look();
        } else {
            JOptionPane.showMessageDialog(null, "没找到试题种类");
        }
        initializeFrame();
        frame.setContentPane(panel1);
    }

    private void initializeFrame() {
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        frame.setIconImage(icon.getImage());
        frame.setSize(650, 450);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - frame.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame.getHeight() / 2;
        frame.setLocation(x, y);
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }

    private void initializeChoicePanel() {  //查看选择题面板
        JLabel label1 = new JLabel("难度系数");
        JLabel label2 = new JLabel("题目内容：");
        JLabel label3 = new JLabel("正确答案：");
        JButton button1 = new JButton("修改题目");
        JButton button2 = new JButton("提交题目");
        JButton button3 = new JButton("删除题目");
        buttonAddChoice = new JButton("增加选项");
        buttonInitializeChoices = new JButton("初始化");
        textField1 = new JTextField();
        textArea1 = new JTextArea();
        panel_choice = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneChoice = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label1.setBounds(10, 80, 100, 20);
        label2.setBounds(10, 10, 120, 20);
        label3.setBounds(10, 280, 100, 20);
        //
        JButtonListener buttonListener = new JButtonListener();
        button1.setFont(font1);
        button2.setFont(font1);
        button3.setFont(font1);
        buttonAddChoice.setFont(font1);
        buttonInitializeChoices.setFont(font1);
        button1.setBounds(5, 150, 95, 25);
        button2.setBounds(5, 190, 95, 25);
        button3.setBounds(5, 230, 95, 25);
        buttonAddChoice.setBounds(5, 310, 95, 25);
        buttonInitializeChoices.setBounds(5, 350, 95, 25);
        button1.setActionCommand("修改题目1");
        button2.setActionCommand("提交题目1");
        button3.setActionCommand("删除题目1");
        button1.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
        button3.addActionListener(buttonListener);
        buttonAddChoice.addActionListener(buttonListener);
        buttonInitializeChoices.addActionListener(buttonListener);
        buttonAddChoice.setEnabled(false);
        buttonInitializeChoices.setEnabled(false);
        //难度系数框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 110, 60, 25);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField1.setDocument(ld);
        textField1.setText(String.valueOf(question1.getDifficulty()));
        textField1.setEditable(false);
        //题目内容/答案框 初始化
        textArea1.setFont(font1);
        textArea1.setLineWrap(true);
        textArea1.setText(question1.getTitle());
        textArea1.setEditable(false);
        //选项面板
        panel_choice.setLayout(null);
        dimension1 = new Dimension(493, 198);
        panel_choice.setPreferredSize(dimension1);
        panel_choice.setBackground(new Color(0x4E977F));
        addAllChoiceOptions();
        //滚动面板初始化
        jScrollPane1.setViewportView(textArea1);
        jScrollPane1.setBounds(110, 10, 512, 190);
        jScrollPaneChoice.setViewportView(panel_choice);
        jScrollPaneChoice.setBounds(110, 210, 512, 200);
        //面板初始化
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(buttonAddChoice);
        panel1.add(buttonInitializeChoices);
        panel1.add(textField1);
        panel1.add(jScrollPane1);
        panel1.add(jScrollPaneChoice);
    }

    private void initializeChoicePanel_Look() {  //查看选择题面板
        JLabel label1 = new JLabel("难度系数");
        JLabel label2 = new JLabel("题目内容：");
        JLabel label3 = new JLabel("正确答案：");
        textField1 = new JTextField();
        textArea1 = new JTextArea();
        panel_choice = new JPanel();
        JScrollPane jScrollPane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPaneChoice = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label1.setBounds(10, 80, 100, 20);
        label2.setBounds(10, 10, 120, 20);
        label3.setBounds(10, 280, 100, 20);
        //难度系数框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 110, 60, 25);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField1.setDocument(ld);
        textField1.setText(String.valueOf(question1.getDifficulty()));
        textField1.setEditable(false);
        //题目内容/答案框 初始化
        textArea1.setFont(font1);
        textArea1.setLineWrap(true);
        textArea1.setText(question1.getTitle());
        textArea1.setEditable(false);
        //选项面板
        panel_choice.setLayout(null);
        dimension1 = new Dimension(493, 198);
        panel_choice.setPreferredSize(dimension1);
        panel_choice.setBackground(new Color(0x4E977F));
        addAllChoiceOptions();
        //滚动面板初始化
        jScrollPane1.setViewportView(textArea1);
        jScrollPane1.setBounds(110, 10, 512, 190);
        jScrollPaneChoice.setViewportView(panel_choice);
        jScrollPaneChoice.setBounds(110, 210, 512, 200);
        //面板初始化
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(textField1);
        panel1.add(jScrollPane1);
        panel1.add(jScrollPaneChoice);
    }

    private void initializeJudgementPanel() {  //查看判断题面板
        JLabel label1 = new JLabel("难度系数");
        JLabel label2 = new JLabel("题目内容：");
        JLabel label3 = new JLabel("正确答案：");
        JButton button1 = new JButton("修改题目");
        JButton button2 = new JButton("提交题目");
        JButton button3 = new JButton("删除题目");
        textField1 = new JTextField();
        textArea1 = new JTextArea();
        radioButton1 = new JRadioButton("对");
        radioButton2 = new JRadioButton("错");
        JScrollPane jScrollPane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label1.setBounds(10, 80, 100, 20);
        label2.setBounds(10, 10, 120, 20);
        label3.setBounds(110, 330, 100, 20);
        //
        button1.setFont(font1);
        button2.setFont(font1);
        button3.setFont(font1);
        button1.setBounds(5, 150, 95, 25);
        button2.setBounds(5, 190, 95, 25);
        button3.setBounds(5, 230, 95, 25);
        button1.setActionCommand("修改题目2");
        button2.setActionCommand("提交题目2");
        button3.setActionCommand("删除题目2");
        button1.addActionListener(new JButtonListener());
        button2.addActionListener(new JButtonListener());
        button3.addActionListener(new JButtonListener());
        //难度系数框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 110, 60, 25);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField1.setDocument(ld);
        textField1.setText(String.valueOf(question1.getDifficulty()));
        textField1.setEditable(false);
        //题目内容框 初始化
        textArea1.setFont(font1);
        textArea1.setLineWrap(true);
        textArea1.setText(question1.getTitle());
        textArea1.setEditable(false);
        //
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        radioButton1.setFont(font1);
        radioButton2.setFont(font1);
        radioButton1.setBounds(220, 330, 60, 25);
        radioButton2.setBounds(290, 330, 60, 25);
        if (question1.getAnswer().equals("1")) {
            radioButton1.setSelected(true);
            radioButton2.setSelected(false);
        } else {
            radioButton1.setSelected(false);
            radioButton2.setSelected(true);
        }
        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);
        //滚动面板初始化
        jScrollPane1.setViewportView(textArea1);
        jScrollPane1.setBounds(110, 10, 500, 300);
        //面板初始化
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(textField1);
        panel1.add(radioButton1);
        panel1.add(radioButton2);
        panel1.add(jScrollPane1);
    }

    private void initializeJudgementPanel_Look() {  //查看判断题面板
        JLabel label1 = new JLabel("难度系数");
        JLabel label2 = new JLabel("题目内容：");
        JLabel label3 = new JLabel("正确答案：");
        textField1 = new JTextField();
        textArea1 = new JTextArea();
        radioButton1 = new JRadioButton("对");
        radioButton2 = new JRadioButton("错");
        JScrollPane jScrollPane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label1.setBounds(10, 80, 100, 20);
        label2.setBounds(10, 10, 120, 20);
        label3.setBounds(110, 330, 100, 20);
        //难度系数框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 110, 60, 25);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField1.setDocument(ld);
        textField1.setText(String.valueOf(question1.getDifficulty()));
        textField1.setEditable(false);
        //题目内容框 初始化
        textArea1.setFont(font1);
        textArea1.setLineWrap(true);
        textArea1.setText(question1.getTitle());
        textArea1.setEditable(false);
        //
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        radioButton1.setFont(font1);
        radioButton2.setFont(font1);
        radioButton1.setBounds(220, 330, 60, 25);
        radioButton2.setBounds(290, 330, 60, 25);
        if (question1.getAnswer().equals("1")) {
            radioButton1.setSelected(true);
            radioButton2.setSelected(false);
        } else {
            radioButton1.setSelected(false);
            radioButton2.setSelected(true);
        }
        radioButton1.setEnabled(false);
        radioButton2.setEnabled(false);
        //滚动面板初始化
        jScrollPane1.setViewportView(textArea1);
        jScrollPane1.setBounds(110, 10, 500, 300);
        //面板初始化
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(textField1);
        panel1.add(radioButton1);
        panel1.add(radioButton2);
        panel1.add(jScrollPane1);
    }

    private void initializeAnswerPanel() {  //查看简答题面板
        JLabel label1 = new JLabel("难度系数");
        JLabel label2 = new JLabel("题目内容：");
        JLabel label3 = new JLabel("正确答案：");
        JButton button1 = new JButton("修改题目");
        JButton button2 = new JButton("提交题目");
        JButton button3 = new JButton("删除题目");
        textField1 = new JTextField();
        textArea1 = new JTextArea();
        textArea2 = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane jScrollPane2 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label1.setBounds(10, 80, 100, 20);
        label2.setBounds(10, 10, 120, 20);
        label3.setBounds(10, 280, 100, 20);
        //
        button1.setFont(font1);
        button2.setFont(font1);
        button3.setFont(font1);
        button1.setBounds(5, 150, 95, 25);
        button2.setBounds(5, 190, 95, 25);
        button3.setBounds(5, 230, 95, 25);
        button1.setActionCommand("修改题目3");
        button2.setActionCommand("提交题目3");
        button3.setActionCommand("删除题目3");
        button1.addActionListener(new JButtonListener());
        button2.addActionListener(new JButtonListener());
        button3.addActionListener(new JButtonListener());
        //难度系数框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 110, 60, 25);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField1.setDocument(ld);
        textField1.setText(String.valueOf(question1.getDifficulty()));
        textField1.setEditable(false);
        //题目内容/答案框 初始化
        textArea1.setFont(font1);
        textArea1.setLineWrap(true);
        textArea1.setText(question1.getTitle());
        textArea1.setEditable(false);
        textArea2.setFont(font1);
        textArea2.setLineWrap(true);
        textArea2.setText(question1.getAnswer());
        textArea2.setEditable(false);
        //滚动面板初始化
        jScrollPane1.setViewportView(textArea1);
        jScrollPane1.setBounds(110, 10, 500, 200);
        jScrollPane2.setViewportView(textArea2);
        jScrollPane2.setBounds(110, 220, 500, 180);
        //面板初始化
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(textField1);
        panel1.add(jScrollPane1);
        panel1.add(jScrollPane2);
    }

    private void initializeAnswerPanel_Look() {  //查看简答题面板
        JLabel label1 = new JLabel("难度系数");
        JLabel label2 = new JLabel("题目内容：");
        JLabel label3 = new JLabel("正确答案：");
        textField1 = new JTextField();
        textArea1 = new JTextArea();
        textArea2 = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane jScrollPane2 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label1.setBounds(10, 80, 100, 20);
        label2.setBounds(10, 10, 120, 20);
        label3.setBounds(10, 280, 100, 20);
        //难度系数框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 110, 60, 25);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField1.setDocument(ld);
        textField1.setText(String.valueOf(question1.getDifficulty()));
        textField1.setEditable(false);
        //题目内容/答案框 初始化
        textArea1.setFont(font1);
        textArea1.setLineWrap(true);
        textArea1.setText(question1.getTitle());
        textArea1.setEditable(false);
        textArea2.setFont(font1);
        textArea2.setLineWrap(true);
        textArea2.setText(question1.getAnswer());
        textArea2.setEditable(false);
        //滚动面板初始化
        jScrollPane1.setViewportView(textArea1);
        jScrollPane1.setBounds(110, 10, 500, 200);
        jScrollPane2.setViewportView(textArea2);
        jScrollPane2.setBounds(110, 220, 500, 180);
        //面板初始化
        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(textField1);
        panel1.add(jScrollPane1);
        panel1.add(jScrollPane2);
    }

    private void refreshQuestions() {
        teacherInterface.getTestQuestionCatalog();
        changedbimpl changedbimpl = new changedbimpl();
        question[] questions = changedbimpl.checkquestion();
        String[] strings = new String[questions.length];
        int t = 0;
        for (question x : questions) {
            strings[t] = x.getForm();
            strings[t] += " ";
            strings[t] += x.getTitle();
            t++;
        }
        if (is.isSelected()) {
            jList.setListData(strings);
            jList.repaint();
        }
    }

    private void addAllChoiceOptions() {
        String answer = question1.getAnswer();
        choicequestion choicequestion = (entity.choicequestion) question1;
        choice[] choices = choicequestion.getChoices();
        choiceOption_T = new ChoiceOption_T();
        choiceOption_T.textAreas.get(0).setText(choices[0].getContent());
        choiceOption_T.textAreas.get(1).setText(choices[1].getContent());
        choiceOption_T.textAreas.get(0).setEditable(false);
        choiceOption_T.textAreas.get(1).setEditable(false);
        choiceOption_T.radioButtons.get(0).setEnabled(false);
        choiceOption_T.radioButtons.get(1).setEnabled(false);
        for (int i = 2; i < choices.length; i++) {
            choiceOption_T.addAChoice();
            choiceOption_T.textAreas.get(i).setText(choices[i].getContent());
            if (choiceOption_T.position == 0 && choiceOption_T.getSize() >= 4) {
                dimension1.setSize(493, dimension1.getHeight() + 95);
                panel_choice.setPreferredSize(dimension1);
            }
        }
        for (int i = 0; i < choices.length; i++) {
            panel_choice.add(choiceOption_T.radioButtons.get(i));
            panel_choice.add(choiceOption_T.scrollPanes.get(i));
            choiceOption_T.radioButtons.get(i).setEnabled(false);
            choiceOption_T.textAreas.get(i).setEditable(false);
            if (answer.charAt(i) == '0') {
                choiceOption_T.radioButtons.get(i).setSelected(false);
            } else if (answer.charAt(i) == '1') {
                choiceOption_T.radioButtons.get(i).setSelected(true);
            } else {
                JOptionPane.showMessageDialog(null, "获取的选择题答案格式不对");
            }
        }
        panel_choice.repaint();
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            if ("修改题目1".equals(actionCommand)) {
                System.out.println("选择题修改题目");
                textField1.setEditable(true);
                textArea1.setEditable(true);
                buttonAddChoice.setEnabled(true);
                buttonInitializeChoices.setEnabled(true);
                for (int i = 0; i < choiceOption_T.getSize(); i++) {
                    choiceOption_T.radioButtons.get(i).setEnabled(true);
                    choiceOption_T.textAreas.get(i).setEditable(true);
                }
            } else if ("增加选项".equals(actionCommand)) {
                if (choiceOption_T.position == 1 && choiceOption_T.getSize() >= 3) {
                    dimension1.setSize(493, dimension1.getHeight() + 95);
                    panel_choice.setPreferredSize(dimension1);
                }
                choiceOption_T.addAChoice();
                panel_choice.add(choiceOption_T.radioButtons.get(choiceOption_T.getSize() - 1));
                panel_choice.add(choiceOption_T.scrollPanes.get(choiceOption_T.getSize() - 1));
                jScrollPaneChoice.revalidate();
                jScrollPaneChoice.repaint();
            } else if ("初始化".equals(actionCommand)) {
                dimension1.setSize(493, 200);
                panel_choice.setPreferredSize(dimension1);
                System.out.println(choiceOption_T.getSize());
                for (int i = choiceOption_T.getSize() - 1; i > 1; i--) {
                    panel_choice.remove(choiceOption_T.radioButtons.get(i));
                    panel_choice.remove(choiceOption_T.scrollPanes.get(i));
                }
                choiceOption_T.clearOption();
                jScrollPaneChoice.revalidate();
                jScrollPaneChoice.repaint();
            } else if ("提交题目1".equals(actionCommand)) {
                String title = textArea1.getText();
                if (!title.equals("")) {
                    double degreeOfDifficulty;
                    try {
                        degreeOfDifficulty = Double.parseDouble(textField1.getText());
                        String[] choiceContent = choiceOption_T.getChoiceContent();
                        choice[] choices = new choice[choiceContent.length];
                        for (int i = 0; i < choiceContent.length; i++) {
                            choices[i] = new choice();
                            choices[i].setContent(choiceContent[i]);
                        }
                        String choiceAnswer = choiceOption_T.getChoiceAnswer();
                        choicequestion choicequestion = (choicequestion) question1;
                        choicequestion.setAnswer(choiceAnswer);
                        choicequestion.setDifficulty(degreeOfDifficulty);
                        choicequestion.setChoices(choices);
                        changedbimpl changedbimpl = new changedbimpl(choicequestion);
                        changedbimpl.changequestion();
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "输入的难度系数格式错误");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "题目不可为空");
                }
                JOptionPane.showMessageDialog(null, "修改题目成功");
                try {
                    refreshQuestions();
                } catch (Exception e1) {
                    System.out.println("更新题目错误");
                }
                frame.dispose();
            } else if ("删除题目1".equals(actionCommand)) {
                changedbimpl changedbimpl = new changedbimpl(question1);
                changedbimpl.delquestion();
                JOptionPane.showMessageDialog(null, "删除题目成功");
                try {
                    refreshQuestions();
                } catch (Exception e1) {
                    System.out.println("更新题目错误");
                }
                frame.dispose();
            } else if ("修改题目2".equals(actionCommand)) {
                textField1.setEditable(true);
                textArea1.setEditable(true);
                radioButton1.setEnabled(true);
                radioButton2.setEnabled(true);
                panel1.repaint();
            } else if ("提交题目2".equals(actionCommand)) {
                String title = textArea1.getText();
                if (!title.equals("")) {
                    try {
                        double difficulty = Double.parseDouble(textField1.getText());
                        String answer;
                        if (radioButton1.isSelected()) {
                            answer = "1";
                            question1.setTitle(title);
                            question1.setDifficulty(difficulty);
                            question1.setAnswer(answer);
                            changedbimpl changedbimpl = new changedbimpl(question1);
                            changedbimpl.changequestion();
                            JOptionPane.showMessageDialog(null, "修改题目成功");
                            try {
                                refreshQuestions();
                            } catch (Exception e1) {
                                System.out.println("更新题目错误");
                            }
                            frame.dispose();
                        } else if (radioButton2.isSelected()) {
                            answer = "0";
                            question1.setTitle(title);
                            question1.setDifficulty(difficulty);
                            question1.setAnswer(answer);
                            changedbimpl changedbimpl = new changedbimpl(question1);
                            changedbimpl.changequestion();
                            JOptionPane.showMessageDialog(null, "修改题目成功");
                            try {
                                refreshQuestions();
                            } catch (Exception e1) {
                                System.out.println("更新题目错误");
                            }
                            frame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "请选择正确答案");
                        }
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "难度系数格式错误");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "题目不可为空");
                }
            } else if ("删除题目2".equals(actionCommand)) {
                changedbimpl changedbimpl = new changedbimpl(question1);
                changedbimpl.delquestion();
                JOptionPane.showMessageDialog(null, "删除题目成功");
                try {
                    refreshQuestions();
                } catch (Exception e1) {
                    System.out.println("更新题目错误");
                }
                frame.dispose();
            } else if ("修改题目3".equals(actionCommand)) {
                textField1.setEditable(true);
                textArea1.setEditable(true);
                textArea2.setEditable(true);
                panel1.repaint();
            } else if ("提交题目3".equals(actionCommand)) {
                String title = textArea1.getText();
                String answer = textArea2.getText();
                if (!(title.equals("") || answer.equals(""))) {
                    try {
                        double difficulty = Double.parseDouble(textField1.getText());
                        question1.setTitle(title);
                        question1.setDifficulty(difficulty);
                        question1.setAnswer(answer);
                        changedbimpl changedbimpl = new changedbimpl(question1);
                        changedbimpl.changequestion();
                        JOptionPane.showMessageDialog(null, "修改题目成功");
                        try {
                            refreshQuestions();
                        } catch (Exception e1) {
                            System.out.println("更新题目错误");
                        }
                        frame.dispose();
                    } catch (NumberFormatException e1) {
                        JOptionPane.showMessageDialog(null, "难度系数格式错误");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "题目与答案不可为空");
                }
            } else if ("删除题目3".equals(actionCommand)) {
                changedbimpl changedbimpl = new changedbimpl(question1);
                changedbimpl.delquestion();
                JOptionPane.showMessageDialog(null, "删除题目成功");
                try {
                    refreshQuestions();
                } catch (Exception e1) {
                    System.out.println("更新题目错误");
                }
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "不识别的命令");
            }
        }
    }

}



