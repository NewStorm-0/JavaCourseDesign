package gui;

import entity.*;
import servlet.changedbimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class CorrectExamInterface {

    private JFrame frame1;
    private Font font1;
    private final JButtonListener buttonListener;
    private JPanel panel_right; //--------------
    private JLabel label_student;
    private JLabel label1; //题号
    private JLabel label_1;  //右下角分数
    private JTextField textField_grade; //
    private JPanel panel_question; //-----------------
    private JButton[] buttonList;
    private Dimension dimension_pq; //选择显示的题目的面板大小
    private JPanel panel_answerQuestion; //--------------
    private JTextArea textArea_paq;
    private JTextArea title_aq;
    private JTextArea textArea_referenceAnswer;
    private JPanel panel_function2;
    private int questionPosition;
    private int studentPosition;
    private final testpaper testPaper1;
    private final question[] questions;
    private String[] answers;
    private final int[] studentArray;
    private final int classId;
    private final teacher teacher_correct;
    private final TeacherInterface teacherInterface;
    private final Integer[][] grades;
    private final String[] studentNames;
    private testDone testDone1;
    private int last = 0;

    CorrectExamInterface(testDone testDone, teacher teacher, int[] students, testpaper tp, int classId, String[] answers, TeacherInterface ti) {
        buttonListener = new JButtonListener();
        teacher_correct = teacher;
        testPaper1 = tp;
        question[] temp = testPaper1.getQuestionlist();
        int n1 = testPaper1.getChoicenumber() + testPaper1.getJudgenumber();
        questions = new question[testPaper1.getAnswernumber()];
        for (int i = 0; i < testPaper1.getAnswernumber(); i++) {
            questions[i] = temp[i + n1];
        }
        studentArray = students;
        testDone1 = testDone;
        teacherInterface = ti;
        this.classId = classId;
        this.answers = answers;
        grades = new Integer[studentArray.length][questions.length];
        questionPosition = 0;
        studentPosition = 0;
        changedbimpl changedbimpl = new changedbimpl();
        studentNames = new String[studentArray.length];
        for (int i = 0; i < studentNames.length; i++) {
            studentNames[i] = changedbimpl.checkstudentname(studentArray[i]);
        }
        initializeFrame();
    }

    private void initializeFrame() {
        frame1 = new JFrame("卷——批改试卷");
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame1.setSize((int) screenSize.getWidth() - 30, (int) screenSize.getHeight() - 30);
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        frame1.setLocation(x, y);
        initializePanel_right();
        initializeButtonList();
        initializePanel_function2();
        initialize_panel_answerQuestion();
        frame1.setLayout(null);
        frame1.add(panel_right);
        frame1.add(panel_function2);
        frame1.add(panel_answerQuestion);
        frame1.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                teacherInterface.setVisible();
            }
        });
        changeQuestion(0);
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        frame1.setIconImage(icon.getImage());
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setBackground(new Color(48, 52, 54));
        frame1.setResizable(false);
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
        label_student = new JLabel("当前学生：" + studentNames[0]);
        JButton button_submit = new JButton("提 交");
        JButton button_exit = new JButton("退 出");
        textField_grade = new JTextField();
        JLabel label_0 = new JLabel("分数:");
        label_1 = new JLabel("10分");
        //按钮初始化
        button_submit.setFont(font1);
        button_exit.setFont(font1);
        button_submit.setBounds((panel_function.getWidth() - 200) / 3, (panel_function.getHeight() - 100) * 2 / 3 + 70, 100, 30);
        button_exit.setBounds((panel_function.getWidth() - 200) * 2 / 3 + 100, (panel_function.getHeight() - 100) * 2 / 3 + 70, 100, 30);
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
        //当前学生标签初始化
        label_student.setFont(font1);
        label_student.setBounds((panel_function.getWidth() - 170) / 2, 10, 170, 30);
        label_student.setForeground(new Color(165, 158, 146));
        //功能面板初始化
        panel_function.setLayout(null);
        panel_function.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        panel_function.add(label_student);
        panel_function.add(button_submit);
        panel_function.add(button_exit);
        panel_function.add(label_0);
        panel_function.add(label_1);
        panel_function.add(textField_grade);
        //问题按钮面板初始化
        panel_question.setLayout(null);
        panel_question.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        //分数输入框初始化
        textField_grade.setFont(font1);
        textField_grade.setBounds((panel_function.getWidth() - 230) / 2 + 55, (panel_function.getHeight() - 100) / 3 + 40, 100, 30);
        textField_grade.setForeground(new Color(210, 206, 197));
        textField_grade.setBackground(new Color(15, 16, 17));
        textField_grade.setCaretColor(new Color(210, 206, 197));
        LimitedDocument ld = new LimitedDocument(6);
        ld.setAllowChar("0123456789");
        textField_grade.setDocument(ld);
        //label_0初始化 label_1初始化
        label_0.setFont(font1);
        label_0.setBounds((panel_function.getWidth() - 230) / 2, (panel_function.getHeight() - 100) / 3 + 40, 60, 30);
        label_0.setOpaque(false);
        label_0.setForeground(new Color(255, 189, 19));
        label_1.setFont(font1);
        label_1.setBounds((panel_function.getWidth() - 230) / 2 + 160, (panel_function.getHeight() - 100) / 3 + 40, 45, 30);
        label_1.setOpaque(false);
        label_1.setBorder(BorderFactory.createLineBorder(new Color(59, 64, 67), 2, true));
        label_1.setForeground(new Color(255, 189, 19));
        //右面板添加面板
        panel_right.setLayout(null);
        panel_right.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        panel_right.add(panel_function);
        panel_right.add(scrollPane_question);
    }

    private void initializePanel_function2() {  //上一学生下一学生面板
        panel_function2 = new JPanel();
        panel_function2.setLayout(null);
        panel_function2.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        panel_function2.setBounds(1, frame1.getHeight() - 98, frame1.getWidth() - panel_right.getWidth(), 60);
        panel_function2.setBackground(new Color(48, 52, 54));
        JButton button1 = new JButton("上一位学生");
        JButton button2 = new JButton("下一位学生");
        button1.setFont(font1);
        button2.setFont(font1);
        button1.setBounds((panel_function2.getWidth() - 240) / 3, 10, 120, 40);
        button2.setBounds((panel_function2.getWidth() - 240) * 2 / 3 + 100, 10, 120, 40);
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
        buttonList = new JButton[questions.length];
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

    private void initialize_panel_answerQuestion() {
        panel_answerQuestion = new JPanel();
        panel_answerQuestion.setBounds(1, 1, frame1.getWidth() - 351, frame1.getHeight() - 99);
        panel_answerQuestion.setBorder(BorderFactory.createLineBorder(new Color(0x9F575A54, true)));
        label1 = new JLabel("1. 填空题(" + questions[0].getPoint() + "分)");
        JLabel label2 = new JLabel("题目信息：");
        JLabel label3 = new JLabel("回答区域：");
        JLabel label4 = new JLabel("参考答案：");
        textArea_paq = new JTextArea();
        title_aq = new JTextArea();
        textArea_referenceAnswer = new JTextArea();
        JScrollPane scrollPane1 = new JScrollPane(title_aq, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane2 = new JScrollPane(textArea_paq, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JScrollPane scrollPane3 = new JScrollPane(textArea_referenceAnswer, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //标签初始化
        label1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
        label1.setBounds((panel_answerQuestion.getWidth() - 160) / 2, 10, 160, 35);
        label1.setOpaque(false);
        label1.setForeground(new Color(215, 218, 211));
        label2.setFont(font1);
        label2.setBounds(10, 60, 80, 30);
        label2.setForeground(new Color(215, 218, 211));
        label3.setFont(font1);
        label3.setBounds(10, (panel_answerQuestion.getHeight() - 95) / 3 + 70, 80, 30);
        label3.setForeground(new Color(215, 218, 211));
        label4.setFont(font1);
        label4.setBounds(10, (panel_answerQuestion.getHeight() - 95) * 2 / 3 + 80, 80, 30);
        label4.setOpaque(false);
        label4.setForeground(new Color(215, 218, 211));
        //回答文本框
        textArea_paq.setFont(font1);
        textArea_paq.setBackground(new Color(64, 70, 72));
        textArea_paq.setForeground(new Color(192, 186, 178));
        textArea_paq.setLineWrap(true);
        textArea_paq.setEditable(false);
        //题目信息文本框
        title_aq.setFont(font1);
        title_aq.setBackground(new Color(64, 70, 72));
        title_aq.setForeground(new Color(192, 186, 178));
        title_aq.setLineWrap(true);
        title_aq.setEditable(false);
        //参考答案文本框
        textArea_referenceAnswer.setFont(font1);
        textArea_referenceAnswer.setBackground(new Color(64, 70, 72));
        textArea_referenceAnswer.setForeground(new Color(192, 186, 178));
        textArea_referenceAnswer.setLineWrap(true);
        textArea_referenceAnswer.setEditable(false);
        //滚动面板初始化
        scrollPane1.setBounds(100, 60, panel_answerQuestion.getWidth() - 105, (panel_answerQuestion.getHeight() - 95) / 3);
        scrollPane2.setBounds(100, (panel_answerQuestion.getHeight() - 95) / 3 + 70, panel_answerQuestion.getWidth() - 105, (panel_answerQuestion.getHeight() - 95) / 3);
        scrollPane3.setBounds(100, (panel_answerQuestion.getHeight() - 95) * 2 / 3 + 80, panel_answerQuestion.getWidth() - 105, (panel_answerQuestion.getHeight() - 95) / 3);
        //添加
        panel_answerQuestion.setLayout(null);
        panel_answerQuestion.setBackground(new Color(48, 52, 55));
        panel_answerQuestion.add(label1);
        panel_answerQuestion.add(label2);
        panel_answerQuestion.add(label3);
        panel_answerQuestion.add(label4);
        panel_answerQuestion.add(scrollPane1);
        panel_answerQuestion.add(scrollPane2);
        panel_answerQuestion.add(scrollPane3);
    }

    private void changeQuestion(int x) {
        questionPosition = x;
        buttonList[questionPosition].setBackground(new Color(27, 76, 135));
        if (answers[questionPosition] != null) {
            textArea_referenceAnswer.setText(questions[questionPosition].getAnswer());
            textArea_paq.setText(answers[questionPosition]);
        } else {
            JOptionPane.showMessageDialog(frame1, "错误！第" + questionPosition + "题答案为空");
            textArea_paq.setText("");
        }
        if (grades[studentPosition][questionPosition] != null) {
            textField_grade.setText(String.valueOf(grades[studentPosition][questionPosition]));
        }
        title_aq.setText(questions[x].getTitle());
        label1.setText(x + 1 + ". " + questions[x].getForm() + "(" + questions[x].getPoint() + "分)");
        label_1.setText(questions[x].getPoint() + "分");
        panel_answerQuestion.revalidate();
        panel_answerQuestion.repaint();
        frame1.revalidate();
        frame1.repaint();
    }

    private void changeStudent(int x) {
        if (saveGrade() && checkOver()) {
            studentPosition = x;
            label_student.setText("当前学生：" + studentNames[studentPosition]);
            for (int i = 0; i < questions.length; i++) {
                if (grades[studentPosition][i] != null) {
                    buttonList[i].setForeground(new Color(243, 58, 48));
                } else {
                    buttonList[i].setForeground(new Color(165, 158, 146));
                }
            }
            changeAnswers(studentPosition);
            changeQuestion(questionPosition);
        }
    }

    private void changeAnswers(int x) {
        changedbimpl changedbimpl = new changedbimpl();
        testDone testdone = changedbimpl.checktestdone(testPaper1.getId(), studentArray[studentPosition], classId);
        testDone1 = testdone;
        String[] answers = new String[testdone.getAnswerquestionDones().length];
        int i = 0;
        for (answerquestionDone temp : testdone.getAnswerquestionDones()) {
            answers[i] = temp.getStudentanswer();
            i++;
        }
        this.answers = answers;
    }

    private boolean saveGrade() {
        if (!textField_grade.getText().equals("")) {
            try {
                int grade = Integer.parseInt(textField_grade.getText());
                if (grade >= 0 && grade <= questions[questionPosition].getPoint()) {
                    grades[studentPosition][questionPosition] = grade;
                    buttonList[questionPosition].setForeground(new Color(243, 58, 48));
                    buttonList[questionPosition].setBackground(new Color(73, 81, 84));
                    return true;
                } else {
                    JOptionPane.showMessageDialog(frame1, "请输入正确的分数");
                    return false;
                }
            } catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(frame1, "请输入正确格式的分数");
                return false;
            }
        } else {
            grades[studentPosition][questionPosition] = null;
            buttonList[questionPosition].setForeground(new Color(165, 158, 146));
            buttonList[questionPosition].setBackground(new Color(73, 81, 84));
            return true;
        }
    }

    private boolean checkOver() {
        for (Integer d : grades[studentPosition]) {
            if (d == null) {
                JOptionPane.showMessageDialog(frame1, "该名学生试题还未全部批完!");
                return false;
            }
        }
        return true;
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("提 交")) {
                if (saveGrade() && checkOver()) {
                    for (int i = 0; i <= last; i++) {
                        studentPosition = i;
                        int q = 0,sumscore=0;
                        answerquestionDone[] temp = testDone1.getAnswerquestionDones();
                        for (int x : grades[studentPosition]) {
                            sumscore+=x;
                            temp[q].setScore(x);
                            q++;
                        }
                        for(int e1=0;e1<testDone1.getJudgequestionDones().length;e1++){
                            sumscore+=testDone1.getJudgequestionDones()[e1].getScore();
                        }
                        for(int e1=0;e1<testDone1.getChoicequestionDones().length;e1++){
                            sumscore+=testDone1.getChoicequestionDones()[e1].getScore();
                        }
                        testDone1.setAnswerquestionDones(temp);
                        students students = new students();
                        students.setId(studentArray[studentPosition]);
                        students.setName(studentNames[studentPosition]);
                        changedbimpl changedbimpl = new changedbimpl(students, testDone1);
                        changedbimpl.addtestdone(sumscore,0, classId, 3);
                        changedbimpl.addtestdonetostudent(students.getId(),testPaper1,sumscore);
                    }
                    JOptionPane.showMessageDialog(frame1, "提交成功，即将自动退出界面");
                    frame1.dispose();
                    teacherInterface.setVisible();
                }
            } else if (e.getActionCommand().equals("退 出")) {
                if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(frame1, "确认退出？请确保您已经提交了分数，", "提示：", JOptionPane.YES_NO_OPTION)) {
                    System.out.println("退出sb");
                    frame1.dispose();
                    teacherInterface.setVisible();
                }
            } else if (e.getActionCommand().equals("上一位学生")) {
                if (studentPosition == 0) {
                    if (saveGrade())
                        JOptionPane.showMessageDialog(frame1, "已经是第一个学生");
                } else {
                    if (saveGrade()) {
                        changeStudent(studentPosition - 1);
                    }
                }
            } else if (e.getActionCommand().equals("下一位学生")) {
                if (studentPosition == studentArray.length - 1) {
                    if (saveGrade())
                        JOptionPane.showMessageDialog(frame1, "已经是最后一个学生");
                } else {
                    if (saveGrade()) {
                        changeStudent(studentPosition + 1);
                        if (studentPosition > last) {
                            last = studentPosition;
                        }
                    }
                }
            } else if (e.getActionCommand().startsWith("buttonList")) {
                if (saveGrade()) {
                    int number = Integer.parseInt(e.getActionCommand().substring(10));
                    textField_grade.setText("");
                    changeQuestion(number);
                }
            }
        }
    }

}
