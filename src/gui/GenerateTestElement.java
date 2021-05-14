package gui;

import entity.question;
import entity.rules;
import entity.testpaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

class GenerateTestElement {
    private final Font font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
    private final ArrayList<JButton> buttons;
    private final ArrayList<JLabel> labels_form;
    private final ArrayList<JTextField> textFields;
    private final ArrayList<JLabel> labels_grade;
    private final ArrayList<JTextField> textFields_grade;
    private final ArrayList<JButton> buttons_look;
    private final rules rule;
    private final question[] questions;
    private int y = 5;
    private final JButtonListener buttonListener;
    private final JFrame parentFrame;

    GenerateTestElement(rules rules, JFrame frame) {
        this.rule = rules;
        buttons = new ArrayList<>();
        labels_form = new ArrayList<>();
        textFields = new ArrayList<>();
        labels_grade = new ArrayList<>();
        textFields_grade = new ArrayList<>();
        buttons_look = new ArrayList<>();
        buttonListener = new JButtonListener();
        questions = new question[rule.getQuestionnumber()];
        parentFrame = frame;
        initializeElement();
    }

    GenerateTestElement(testpaper tp, JFrame frame) {
        this.questions = tp.getQuestionlist();
        rules rules = new rules();
        rules.setPoint(tp.getPaperpoint());
        rules.setChoicenumber(tp.getChoicenumber());
        rules.setAnswernumber(tp.getAnswernumber());
        rules.setJudgenumber(tp.getJudgenumber());
        rules.setQuestionnumber(tp.getQuestionnumber());
        this.rule = rules;
        buttons = new ArrayList<>();
        labels_form = new ArrayList<>();
        textFields = new ArrayList<>();
        labels_grade = new ArrayList<>();
        textFields_grade = new ArrayList<>();
        buttons_look = new ArrayList<>();
        buttonListener = new JButtonListener();
        parentFrame = frame;
        initializeElement();
        setOnlyLook();
    }

    int getNumber() {
        return buttons.size();
    }

    JButton getButton(int i) {
        return buttons.get(i);
    }

    JLabel getLabel_form(int i) {
        return labels_form.get(i);
    }

    JTextField getTextField(int i) {
        return textFields.get(i);
    }

    JLabel getLabel_grade(int i) {
        return labels_grade.get(i);
    }

    JTextField getTextField_grade(int i) {
        return textFields_grade.get(i);
    }

    JButton getButton_look(int i) {
        return buttons_look.get(i);
    }

    question[] getAllQuestion() {
        question[] questionsArray = new question[getNumber()];
        int i = 0;
        for (question q : questions) {
            questionsArray[i] = q;
            i++;
        }
        return questionsArray;
    }

    private void initializeElement() {
        for (int i = 0; i < rule.getQuestionnumber(); i++) {
            //JButton add
            JButton button = new JButton();
            button.setActionCommand("add" + i);
            button.setBounds(5, y, 30, 30);
            String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/add.jpg";
            ImageIcon icon = new ImageIcon(iconPath);
            Image img = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(img));
            button.addActionListener(buttonListener);
            buttons.add(button);
            //JLabel_form add
            JLabel label_form;
            if (getQuestionForm(i + 1) == 0) {
                label_form = new JLabel("选择题");
            } else if (getQuestionForm(i + 1) == 1) {
                label_form = new JLabel("判断题");
            } else {
                label_form = new JLabel("简答题");
            }
            label_form.setFont(font1);
            label_form.setBounds(43, y, 97, 30);
            labels_form.add(label_form);
            //JTextField add
            JTextField textField = new JTextField();
            textField.setFont(font1);
            textField.setEditable(false);
            textField.setBounds(105, y, 270, 30);
            textFields.add(textField);
            //JLabel_grade add
            JLabel label_grade = new JLabel("分数:");
            label_grade.setFont(font1);
            label_grade.setBounds(380, y, 40, 30);
            labels_grade.add(label_grade);
            //JTextField_grade add
            JTextField textField_grade = new JTextField();
            textField_grade.setFont(font1);
            LimitedDocument ld = new LimitedDocument(3);
            ld.setAllowChar("0123456789");
            textField_grade.setDocument(ld);
            textField_grade.setBounds(425, y, 45, 30);
            textFields_grade.add(textField_grade);
            //JButton_look add
            JButton button_look = new JButton("查看");
            button_look.setFont(font1);
            button_look.setActionCommand("look" + i);
            button_look.setBounds(475, y, 65, 30);
            button_look.addActionListener(buttonListener);
            buttons_look.add(button_look);
            //change the y
            y += 46;
        }
    }

    private int getQuestionForm(int x) {
        if (x <= rule.getChoicenumber()) {
            return 0;  //选择题
        } else if (x > rule.getChoicenumber() && x <= rule.getChoicenumber() + rule.getJudgenumber()) {
            return 1;  //判断题
        } else if (x - rule.getChoicenumber() - rule.getJudgenumber() > 0 && x - rule.getChoicenumber() - rule.getJudgenumber() <= rule.getAnswernumber()) {
            return 2;  //简答题
        } else {
            System.out.println("获取试题类型错误，在generateTestElement类的getQuestionForm方法");
            return 3;
        }
    }

    private void setOnlyLook() {
        for (int i = 0; i < rule.getQuestionnumber(); i++) {
            textFields.get(i).setText(questions[i].getTitle());
            textFields_grade.get(i).setText(String.valueOf(questions[i].getPoint()));
        }
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().startsWith("add")) {
                int i = Integer.parseInt(e.getActionCommand().substring(3));
                ChooseQuestion chooseQuestion = new ChooseQuestion(questions, textFields.get(i), i, labels_form.get(i).getText(), parentFrame);
            } else if (e.getActionCommand().startsWith("look")) {
                int i = Integer.parseInt(e.getActionCommand().substring(4));
                if (questions[i] != null) {
                    OnlyLookQuestion onlyLookQuestion = new OnlyLookQuestion(questions[i]);
                } else {
                    JOptionPane.showMessageDialog(parentFrame, "请先选择题目");
                }
            }
        }
    }

}
