package gui;

import entity.choice;
import entity.choicequestion;
import entity.question;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

class OnlyLookQuestion {
    private final JFrame frame;
    private JPanel panel1;
    private final question question1;
    private final Font font1;
    private JTextField textField1; //难度系数输入框
    private JPanel panel_choice; //选择题查看面板选项面板滑动面板中的面板
    private JScrollPane jScrollPaneChoice; //选择题查看面板选项面板滑动面板
    private Dimension dimension1; //选择题查看面板选项面板滑动面板中的面板大小
    private ChoiceOption_T choiceOption_T; //选择题选项
    private JTextArea textArea1; //题目内容
    private JTextArea textArea2; //题目答案
    private JRadioButton radioButton1; //正确单选框
    private JRadioButton radioButton2; // 错误单选框

    OnlyLookQuestion(question question) {
        question1 = question;
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

    private void initializeFrame() {
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/aa.png";
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
        textArea1.setText(question1.getTitle());
        textArea1.setEditable(false);
        textArea2.setFont(font1);
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

}
