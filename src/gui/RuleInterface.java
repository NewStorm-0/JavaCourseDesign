package gui;

import entity.rules;
import servlet.changedbimpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

class RuleInterface {
    private JFrame frame1; //主界面
    private JTextField textField1; //总分输入框
    private JTextField textField2; //选择题个数输入框
    private JTextField textField3; //判断题个数输入框
    private JTextField textField4; //简答题个数输入框
    private JTextField textField5; //规则名称输入框
    private JButton button1;
    private final JList<String> list;
    private rules rule;
    private final JRadioButton is; //检查试题库面板，显示试题单选框
    private final TeacherInterface teacherInterface;

    RuleInterface(JList<String> jList, TeacherInterface ti, JRadioButton is) {
        list = jList;
        this.is = is;
        teacherInterface = ti;
        initializeInterface();
    }

    RuleInterface(JList<String> jList, TeacherInterface ti, JRadioButton is, rules rules) {
        this(jList, ti, is);
        rule = rules;
        changeToRead();
    }

    private void initializeInterface() {
        //主题风格设置
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");//windows风格
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        frame1 = new JFrame("卷——添加试题出题规则");
        Font font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        textField1 = new JTextField("100");
        textField2 = new JTextField();
        textField3 = new JTextField();
        textField4 = new JTextField();
        textField5 = new JTextField();
        JLabel label1 = new JLabel("试卷总分：");
        JLabel label2 = new JLabel("选择题数目：");
        JLabel label3 = new JLabel("判断题数目：");
        JLabel label4 = new JLabel("简答题数目：");
        JLabel label5 = new JLabel("规则名称：");
        button1 = new JButton("提 交");
        Container panel1 = frame1.getContentPane();
        //输入框初始化
        LimitedDocument ld1 = new LimitedDocument(6);
        ld1.setAllowChar("0123456789");
        LimitedDocument ld2 = new LimitedDocument(6);
        ld2.setAllowChar("0123456789");
        LimitedDocument ld3 = new LimitedDocument(6);
        ld3.setAllowChar("0123456789");
        LimitedDocument ld4 = new LimitedDocument(6);
        ld4.setAllowChar("0123456789");
        textField1.setFont(font1);
        textField2.setFont(font1);
        textField3.setFont(font1);
        textField4.setFont(font1);
        textField5.setFont(font1);
        textField1.setBounds(120, 45, 100, 25);
        textField2.setBounds(120, 80, 100, 25);
        textField3.setBounds(120, 115, 100, 25);
        textField4.setBounds(120, 150, 100, 25);
        textField5.setBounds(120, 8, 100, 28);
        textField1.setDocument(ld1);
        textField2.setDocument(ld2);
        textField3.setDocument(ld3);
        textField4.setDocument(ld4);
        //标签初始化
        label1.setFont(font1);
        label2.setFont(font1);
        label3.setFont(font1);
        label4.setFont(font1);
        label5.setFont(font1);
        label1.setBounds(10, 45, 100, 25);
        label2.setBounds(10, 80, 100, 25);
        label3.setBounds(10, 115, 100, 25);
        label4.setBounds(10, 150, 100, 25);
        label5.setBounds(10, 10, 100, 25);
        //按钮初始化
        button1.setActionCommand("提 交");
        button1.setFont(font1);
        button1.setBounds(50, 180, 120, 30);
        button1.addActionListener(new JButtonListener());
        //panel1面板初始化
        panel1.setLayout(null);
        panel1.add(textField1);
        panel1.add(textField2);
        panel1.add(textField3);
        panel1.add(textField4);
        panel1.add(textField5);
        panel1.add(label1);
        panel1.add(label2);
        panel1.add(label3);
        panel1.add(label4);
        panel1.add(label5);
        panel1.add(button1);
        //frame1主界面初始化
        String iconPath = Objects.requireNonNull(this.getClass().getClassLoader().getResource("")).getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        frame1.setSize(240, 260);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        frame1.setResizable(false);
        frame1.setLayout(null);
        frame1.setLocation(x, y);
        frame1.setIconImage(icon.getImage());
        frame1.setVisible(true);
    }

    private void changeToRead() {
        frame1.setTitle("卷——查看试题出题规则");
        textField1.setText(String.valueOf(rule.getPoint()));
        textField2.setText(String.valueOf(rule.getChoicenumber()));
        textField3.setText(String.valueOf(rule.getJudgenumber()));
        textField4.setText(String.valueOf(rule.getAnswernumber()));
        textField5.setText(String.valueOf(rule.getName()));
        textField1.setEditable(false);
        textField2.setEditable(false);
        textField3.setEditable(false);
        textField4.setEditable(false);
        textField5.setEditable(false);
        button1.setText("删 除");
        button1.setActionCommand("删 除");
    }

    private void refreshRules() {
        teacherInterface.getRules();
        changedbimpl changedbimpl = new changedbimpl();
        rules[] rules1 = changedbimpl.checkrule();
        String[] strings = new String[rules1.length];
        int t = 0;
        for (rules x : rules1) {
            strings[t] = x.getName() + "  总分：" + x.getPoint();
            t++;
        }
        try {
            if (!is.isSelected()) {
                list.setListData(strings);
                list.repaint();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("刷新规则错误");
        }
    }

    void setLook() {
        frame1.setVisible(true);
    }

    private class JButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("提 交")) {
                try {
                    String name = textField5.getText();
                    int grade = Integer.parseInt(textField1.getText());
                    int choiceNumber = Integer.parseInt(textField2.getText());
                    int judgeNumber = Integer.parseInt(textField3.getText());
                    int answerNumber = Integer.parseInt(textField4.getText());
                    rules rules = new rules();
                    rules.setAnswernumber(answerNumber);
                    rules.setChoicenumber(choiceNumber);
                    rules.setJudgenumber(judgeNumber);
                    rules.setQuestionnumber(answerNumber + choiceNumber + judgeNumber);
                    rules.setName(name);
                    rules.setPoint(grade);
                    changedbimpl changedbimpl = new changedbimpl(rules);
                    changedbimpl.addrules();
                    JOptionPane.showMessageDialog(null, "提交成功");
                    refreshRules();
                    frame1.dispose();
                    System.out.println("名称：" + name + " 总分：" + grade + " 选择题数目：" + choiceNumber + " 判断题数目：" + judgeNumber + " 简答题数目：" + answerNumber);
                } catch (NumberFormatException numberFormatException) {
                    JOptionPane.showMessageDialog(null, "请输入正确的数目");
                }
            } else if (e.getActionCommand().equals("删 除")) {
                rules rules = rule;
                changedbimpl changedbimpl = new changedbimpl(rules);
                changedbimpl.deleterules();
                refreshRules();
                JOptionPane.showMessageDialog(frame1, "删除规则成功");
                frame1.dispose();
            }
        }
    }
}
