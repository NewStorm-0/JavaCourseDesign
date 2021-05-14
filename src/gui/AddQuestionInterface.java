package gui;

import entity.*;
import servlet.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


class AddQuestionInterface {
    private JFrame frame1; //主界面
    private Font font1; //字体
    private JRadioButton radioButton1; //选择题单选按钮
    private JRadioButton radioButton2; //判断题单选按钮
    private JRadioButton radioButton3; //简答题单选按钮
    private JRadioButton radioButton2_A; //判断题答案对单选按钮
    private JRadioButton radioButton2_B; //判断题答案错单选按钮
    private JPanel panel1; //选择题编辑面板
    private JPanel panel2; //判断题编辑面板
    private JPanel panel3; //简答题编辑面板
    private JTextArea textArea1; //选择题题目输入框
    private JTextArea textArea2; //判断题题目输入框
    private JTextArea textArea3; //简答题题目输入框
    private JTextArea textArea4; //简答题答案示例输入框
    private JTextField textField1; //选择题难度系数输入框
    private JTextField textField2; //判断题难度系数输入框
    private JTextField textField3; //简答题难度系数输入框
    private JButtonListener buttonListener1; //按钮监听事件
    private JPanel panel1_A; //选择题编辑面板选项面板
    private JScrollPane jScrollPane1; //选择题编辑面板选项面板滑动面板
    private Dimension dimension1; //选择题编辑面板选项面板构件
    private final ChoiceOption_T choiceOption_T = new ChoiceOption_T(); //选择题选项
    private final teacher teacher;
    private final JList<String> jList;
    private final JRadioButton is;
    private final TeacherInterface teacherInterface;

    AddQuestionInterface(teacher teacher, JList<String> jList, JRadioButton is, TeacherInterface ti) {
        this.teacher = teacher;
        this.jList = jList;
        this.is = is;
        teacherInterface = ti;
        initializeInterface();
    }

    private void initializeInterface() {
        //主题风格设置
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        frame1 = new JFrame("卷——添加试题");
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        ButtonGroup buttonGroup1 = new ButtonGroup();
        JRadioButtonListener radioButtonListener = new JRadioButtonListener();
        radioButton1 = new JRadioButton("选择题");
        radioButton2 = new JRadioButton("判断题");
        radioButton3 = new JRadioButton("简答题");
        buttonListener1 = new JButtonListener();
        initializePanel1();
        JPanel panelRadioButton = new JPanel(); //题型单选按钮面板
        JLabel label1 = new JLabel("题型选择：");
        JLabel label2 = new JLabel("");
        //单选按钮组设置
        buttonGroup1.add(radioButton1);
        buttonGroup1.add(radioButton2);
        buttonGroup1.add(radioButton3);
        //题型单选按钮初始化
        radioButton1.setFont(font1);
        radioButton2.setFont(font1);
        radioButton3.setFont(font1);
        radioButton1.setBounds(132, 5, 85, 15);
        radioButton2.setBounds(310, 5, 85, 15);
        radioButton3.setBounds(488, 5, 85, 15);
        radioButton1.addActionListener(radioButtonListener);
        radioButton2.addActionListener(radioButtonListener);
        radioButton3.addActionListener(radioButtonListener);
        radioButton1.setSelected(true);
        //题型单选按钮面板设置
        panelRadioButton.setBounds(0, 0, 650, 25);
        panelRadioButton.setLayout(null);
        panelRadioButton.add(radioButton1);
        panelRadioButton.add(radioButton2);
        panelRadioButton.add(radioButton3);
        panelRadioButton.add(label1);
        panelRadioButton.add(label2);
        //
        //标签设置
        label1.setFont(font1);
        label1.setBounds(10, 5, 100, 15);
        label2.setBounds(1, 0, 634, 24);
        label2.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        //
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        frame1.setSize(650, 450);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setLocation(x, y);
        frame1.setIconImage(icon.getImage());
        frame1.add(panelRadioButton);
        frame1.add(panel1);
        frame1.setVisible(true);
    }

    private void initializePanel1() {
        //选择题
        panel1 = new JPanel();
        panel1_A = new JPanel();
        textArea1 = new JTextArea();
        textField1 = new JTextField("0.0");
        dimension1 = new Dimension(493, 199);
        JLabel label1 = new JLabel("题目信息：");
        JLabel label2 = new JLabel("难度系数");
        JButton button1 = new JButton("增加选项");
        JButton button2 = new JButton("初始化");
        JButton button3 = new JButton("提交");
        jScrollPane1 = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane jScrollPaneTextArea = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //选项显示面板
        panel1_A.setPreferredSize(dimension1);
        panel1_A.setBackground(new Color(0x3E3E6C));
        panel1_A.setLayout(null);
        panel1_AAddAllChoiceOption();
        //题目输入框初始化
        textArea1.setFont(font1);
        textArea1.setLineWrap(true);
        textArea1.setEditable(true);
        //难度系数输入框初始化
        textField1.setFont(font1);
        textField1.setBounds(10, 98, 80, 22);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField1.setDocument(ld);
        textField1.setText("0.50");
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label1.setBounds(20, 10, 100, 20);
        label2.setBounds(20, 75, 100, 20);
        //增加选项按钮
        button1.setFont(font1);
        button1.setBounds(2, 135, 95, 25);
        button1.addActionListener(buttonListener1);
        //初始化按钮
        button2.setFont(font1);
        button2.setBounds(2, 180, 95, 25);
        button2.addActionListener(buttonListener1);
        //提交按钮
        button3.setFont(font1);
        button3.setBounds(2, 340, 95, 30);
        button3.setActionCommand("提交1");
        button3.addActionListener(buttonListener1);
        //滚动面板
        jScrollPane1.setViewportView(panel1_A);
        jScrollPane1.setBounds(99, 130, 512, 200);
        //题目信息滑动
        jScrollPaneTextArea.setViewportView(textArea1);
        jScrollPaneTextArea.setBounds(100, 10, 510, 110);
        //面板初始化
        panel1.setLayout(null);
        panel1.add(jScrollPaneTextArea);
        panel1.add(textField1);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(button1);
        panel1.add(button2);
        panel1.add(button3);
        panel1.add(jScrollPane1);
        panel1.setBounds(0, 25, 650, 425);
        panel1.setBackground(Color.GRAY);
    }

    private void initializePanel2() {
        panel2 = new JPanel();
        textArea2 = new JTextArea();
        textField2 = new JTextField("0.00");
        ButtonGroup buttonGroup2 = new ButtonGroup();
        JButton buttonA = new JButton("修改");
        JButton buttonB = new JButton("确认");
        JButton buttonC = new JButton("提交");
        radioButton2_A = new JRadioButton("对");
        radioButton2_B = new JRadioButton("错");
        JLabel labelA = new JLabel("题目信息：");
        JLabel labelB = new JLabel("难度系数：");
        JLabel labelC = new JLabel("请输入一个0~1的数来表示题目难度.(难度系数越大,题目得分率越高,难度也就越小.)");
        JLabel labelD = new JLabel("正确答案：");
        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //难度系数输入框初始化
        textField2.setFont(font1);
        textField2.setBounds(120, 220, 40, 25);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField2.setDocument(ld);
        textField2.setText("0.50");
        //题目输入框初始化
        textArea2.setFont(font1);
        textArea2.setLineWrap(true);
        textArea2.setEditable(true);
        //答案对错选择组初始化
        buttonGroup2.add(radioButton2_A);
        buttonGroup2.add(radioButton2_B);
        //按钮初始化
        buttonA.setFont(font1);
        buttonB.setFont(font1);
        buttonC.setFont(font1);
        buttonA.setActionCommand("修改2");
        buttonB.setActionCommand("确认2");
        buttonC.setActionCommand("提交2");
        buttonA.setBounds(10, 35, 80, 25);
        buttonB.setBounds(10, 70, 80, 25);
        buttonC.setBounds(280, 320, 90, 40);
        buttonA.addActionListener(buttonListener1);
        buttonB.addActionListener(buttonListener1);
        buttonC.addActionListener(buttonListener1);
        //单选按钮初始化
        radioButton2_A.setFont(font1);
        radioButton2_B.setFont(font1);
        radioButton2_A.setBounds(150, 255, 50, 25);
        radioButton2_B.setBounds(210, 255, 50, 25);
        radioButton2_A.setOpaque(false);
        radioButton2_B.setOpaque(false);
        //标签初始化
        labelA.setFont(font1);
        labelB.setFont(font1);
        labelD.setFont(font1);
        labelA.setBounds(17, 10, 100, 17);
        labelB.setBounds(40, 224, 100, 17);
        labelC.setBounds(165, 220, 470, 25);
        labelD.setBounds(40, 255, 100, 25);
        //滚动条
        scrollPane.setViewportView(textArea2);
        scrollPane.setBounds(120, 10, 510, 200);
        //面板初始化
        panel2.setLayout(null);
        panel2.add(scrollPane);
        panel2.add(textField2);
        panel2.add(buttonA);
        panel2.add(buttonB);
        panel2.add(buttonC);
        panel2.add(radioButton2_A);
        panel2.add(radioButton2_B);
        panel2.add(labelA);
        panel2.add(labelB);
        panel2.add(labelC);
        panel2.add(labelD);
        panel2.setBounds(0, 25, 650, 425);
        panel2.setBackground(Color.GRAY);
    }

    private void initializePanel3() {
        panel3 = new JPanel();
        textArea3 = new JTextArea();
        textArea3.setFont(font1);
        textArea3.setLineWrap(true);
        textArea4 = new JTextArea();
        textArea4.setFont(font1);
        textArea4.setLineWrap(true);
        textField3 = new JTextField();
        textField3.setFont(font1);
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar(".0123456789");
        textField3.setDocument(ld);
        textField3.setText("0.50");
        textField3.setBounds(10, 140, 85, 25);
        JLabel label1 = new JLabel("题目信息：");
        JLabel label2 = new JLabel("答案示例：");
        JLabel label3 = new JLabel("难度系数");
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label1.setBounds(20, 10, 80, 20);
        label2.setBounds(20, 190, 80, 20);
        label3.setBounds(20, 110, 80, 20);
        JScrollPane scrollPane3_1 = new JScrollPane(textArea3, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane3_2 = new JScrollPane(textArea4, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane3_1.setBounds(100, 10, 530, 170);
        scrollPane3_2.setBounds(100, 190, 530, 185);
        JButton button1 = new JButton("提交");
        button1.setFont(font1);
        button1.setActionCommand("提交3");
        button1.setBounds(10, 340, 80, 34);
        button1.addActionListener(buttonListener1);
        //
        panel3.setLayout(null);
        panel3.add(textField3);
        panel3.add(label1);
        panel3.add(label2);
        panel3.add(label3);
        panel3.add(scrollPane3_1);
        panel3.add(scrollPane3_2);
        panel3.add(button1);
        panel3.setBounds(0, 25, 650, 425);
        panel3.setBackground(Color.GRAY);
    }

    void setLook() {
        frame1.setVisible(true);
    }

    private void panel1_AAddAllChoiceOption() {
        for (int i = 0; i < choiceOption_T.getSize(); i++) {
            panel1_A.add(choiceOption_T.radioButtons.get(i));
            panel1_A.add(choiceOption_T.scrollPanes.get(i));
        }
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
        try {
            if (is.isSelected()) {
                jList.setListData(strings);
                jList.repaint();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("刷新试题错误");
        }
    }

    private class JRadioButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (radioButton1.isSelected()) {
                if (panel2 != null) {
                    frame1.remove(panel2);
                }
                if (panel3 != null) {
                    frame1.remove(panel3);
                }
                frame1.add(panel1);
                frame1.repaint();
            } else if (radioButton2.isSelected()) {
                frame1.remove(panel1);
                if (panel3 != null) {
                    frame1.remove(panel3);
                }
                if (panel2 == null) {
                    initializePanel2();
                }
                frame1.add(panel2);
                frame1.validate();
                frame1.repaint();
            } else if (radioButton3.isSelected()) {
                frame1.remove(panel1);
                if (panel2 != null) {
                    frame1.remove(panel2);
                }
                if (panel3 == null) {
                    initializePanel3();
                }
                frame1.add(panel3);
                frame1.validate();
                frame1.repaint();
            }
        }
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("修改2")) {
                textArea2.setEditable(true);
            } else if (e.getActionCommand().equals("确认2")) {
                textArea2.setEditable(false);
            } else if (e.getActionCommand().equals("提交1")) {
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
                        choicequestion choicequestion = new choicequestion(degreeOfDifficulty, choiceAnswer, title, choices);
                        changedbimpl changedbimpl = new changedbimpl(choicequestion);
                        changedbimpl.addquestion();
                        JOptionPane.showMessageDialog(null, "提交成功");
                        refreshQuestions();
                        textArea1.setText("");
                        textField1.setText("0.5");
                        dimension1.setSize(493, 200);
                        panel1_A.setPreferredSize(dimension1);
                        System.out.println(choiceOption_T.getSize());
                        for (int i = choiceOption_T.getSize() - 1; i > 1; i--) {
                            panel1_A.remove(choiceOption_T.radioButtons.get(i));
                            panel1_A.remove(choiceOption_T.scrollPanes.get(i));
                        }
                        choiceOption_T.clearOption();
                        jScrollPane1.revalidate();
                        jScrollPane1.repaint();
                        frame1.dispose();
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "输入的难度系数格式错误");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "题目不可为空");
                }
            } else if (e.getActionCommand().equals("提交2")) {
                String title = textArea2.getText();
                if (!title.equals("")) {
                    double degreeOfDifficulty;
                    try {
                        degreeOfDifficulty = Double.parseDouble(textField2.getText());
                        int answer;
                        if (radioButton2_A.isSelected()) {
                            answer = 1;
                            judgequestion judgequestion = new judgequestion(degreeOfDifficulty, Integer.toString(answer), title);
                            changedbimpl changedbimpl = new changedbimpl(judgequestion);
                            changedbimpl.addquestion();
                            JOptionPane.showMessageDialog(null, "提交成功");
                            refreshQuestions();
                            textArea2.setText("");
                            textField2.setText("");
                            frame1.dispose();
                        } else if (radioButton2_B.isSelected()) {
                            answer = 0;
                            judgequestion judgequestion = new judgequestion(degreeOfDifficulty, Integer.toString(answer), title);
                            changedbimpl changedbimpl = new changedbimpl(judgequestion);
                            changedbimpl.addquestion();
                            JOptionPane.showMessageDialog(null, "提交成功");
                            refreshQuestions();
                            textArea2.setText("");
                            textField2.setText("0.50");
                            frame1.dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "请选择正确答案");
                        }
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "输入的难度系数格式错误");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "题目不可为空");
                }
            } else if (e.getActionCommand().equals("提交3")) {
                String title = textArea3.getText();
                String answerExample = textArea4.getText();
                if (!(title.equals("") || answerExample.equals(""))) {
                    double degreeOfDifficulty;
                    try {
                        degreeOfDifficulty = Double.parseDouble(textField3.getText());
                        answerquestion answerquestion = new answerquestion(degreeOfDifficulty, answerExample, title);
                        changedbimpl changedbimpl = new changedbimpl(answerquestion);
                        changedbimpl.addquestion();
                        JOptionPane.showMessageDialog(null, "提交成功");
                        refreshQuestions();
                        textArea3.setText("");
                        textArea4.setText("");
                        textField3.setText("0.5");
                        frame1.dispose();
                    } catch (NumberFormatException numberFormatException) {
                        JOptionPane.showMessageDialog(null, "输入的难度系数格式错误");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "题目与答案不可为空");
                }
            } else if (e.getActionCommand().equals("增加选项")) {
                if (choiceOption_T.position == 1 && choiceOption_T.getSize() >= 3) {
                    dimension1.setSize(493, dimension1.getHeight() + 95);
                    panel1_A.setPreferredSize(dimension1);
                }
                choiceOption_T.addAChoice();
                panel1_A.add(choiceOption_T.radioButtons.get(choiceOption_T.getSize() - 1));
                panel1_A.add(choiceOption_T.scrollPanes.get(choiceOption_T.getSize() - 1));
                jScrollPane1.revalidate();
                jScrollPane1.repaint();
            } else if (e.getActionCommand().equals("初始化")) {
                dimension1.setSize(493, 200);
                panel1_A.setPreferredSize(dimension1);
                System.out.println(choiceOption_T.getSize());
                for (int i = choiceOption_T.getSize() - 1; i > 1; i--) {
                    panel1_A.remove(choiceOption_T.radioButtons.get(i));
                    panel1_A.remove(choiceOption_T.scrollPanes.get(i));
                }
                choiceOption_T.clearOption();
                jScrollPane1.revalidate();
                jScrollPane1.repaint();
            }
        }
    }

}

class LimitedDocument extends PlainDocument {

    private int _maxLength = -1;
    private String _allowCharAsString = null;

    public LimitedDocument() {
        super();
    }

    public LimitedDocument(int maxLength) {
        super();
        this._maxLength = maxLength;
    }

    public void insertString(int offset, String str, AttributeSet attrSet) throws BadLocationException {
        if (str == null) {
            return;
        }
        if (_allowCharAsString != null && str.length() == 1) {
            if (_allowCharAsString.indexOf(str) == -1) {
                return;
            }
        }
        char[] charVal = str.toCharArray();
        String strOldValue = getText(0, getLength());
        byte[] tmp = strOldValue.getBytes();
        if (_maxLength != -1 && (tmp.length + charVal.length > _maxLength)) {
            return;
        }
        super.insertString(offset, str, attrSet);
    }

    public void setAllowChar(String str) {
        _allowCharAsString = str;
    }
}
