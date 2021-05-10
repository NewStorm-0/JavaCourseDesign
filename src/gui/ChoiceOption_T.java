package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ChoiceOption_T {
    private final Font font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
    ArrayList<JRadioButton> radioButtons; //
    ArrayList<JTextArea> textAreas; //
    ArrayList<JScrollPane> scrollPanes; //
    int position = 1;
    char symbol = 'B';
    int y1 = 45, y2 = 5;

    ChoiceOption_T() {
        radioButtons = new ArrayList<>();
        textAreas = new ArrayList<>();
        scrollPanes = new ArrayList<>();
        //单选选项arraylist
        radioButtons.add(new JRadioButton("A"));
        radioButtons.add(new JRadioButton("B"));
        radioButtons.get(0).setFont(font1);
        radioButtons.get(1).setFont(font1);
        radioButtons.get(0).setBounds(5, 45, 40, 15);
        radioButtons.get(1).setBounds(250, 45, 40, 15);
        radioButtons.get(0).setOpaque(false);
        radioButtons.get(1).setOpaque(false);
        //单选文本框arraylist
        textAreas.add(new JTextArea());
        textAreas.add(new JTextArea());
        textAreas.get(0).setFont(font1);
        textAreas.get(1).setFont(font1);
        textAreas.get(0).setLineWrap(true);
        textAreas.get(1).setLineWrap(true);
        //
        scrollPanes.add(new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        scrollPanes.add(new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        scrollPanes.get(0).setViewportView(textAreas.get(0));
        scrollPanes.get(1).setViewportView(textAreas.get(1));
        scrollPanes.get(0).setBounds(45, 5, 195, 90);
        scrollPanes.get(1).setBounds(290, 5, 195, 90);
    }

    int getSize() {
        return radioButtons.size();
    }

    private void changePosition() {
        if (position == 1) {
            position = 0;
        } else if (position == 0) {
            position = 1;
        }
    }

    void addAChoice() {
        int temp = symbol;
        temp++;
        symbol = (char) temp;
        radioButtons.add(new JRadioButton(String.valueOf(symbol)));
        textAreas.add(new JTextArea());
        scrollPanes.add(new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED));
        initializeAChoice();
    }

    void clearOption() {
        for (int i = getSize(); i > 2; i--) {
            radioButtons.remove(i - 1);
            textAreas.remove(i - 1);
            scrollPanes.remove(i - 1);
        }
        position = 1;
        y1 = 45;
        y2 = 5;
        symbol = 'B';
    }

    private void initializeAChoice() {
        radioButtons.get(radioButtons.size() - 1).setFont(font1);
        radioButtons.get(radioButtons.size() - 1).setOpaque(false);
        textAreas.get(textAreas.size() - 1).setFont(font1);
        textAreas.get(textAreas.size() - 1).setLineWrap(true);
        scrollPanes.get(scrollPanes.size() - 1).setViewportView(textAreas.get(textAreas.size() - 1));
        if (position == 1) {
            y1 += 95;
            y2 += 95;
            radioButtons.get(radioButtons.size() - 1).setBounds(5, y1, 40, 15);
            scrollPanes.get(scrollPanes.size() - 1).setBounds(45, y2, 195, 90);
        } else if (position == 0) {
            radioButtons.get(radioButtons.size() - 1).setBounds(250, y1, 40, 15);
            scrollPanes.get(scrollPanes.size() - 1).setBounds(290, y2, 195, 90);
        }
        changePosition();
    }

    String[] getChoiceContent() {
        String[] choiceContent = new String[getSize()];
        for (int i = 0; i < choiceContent.length; i++) {
            choiceContent[i] = textAreas.get(i).getText();
        }
        return choiceContent;
    }

    String[] getChoiceNumber() {
        String[] choiceNumber = new String[getSize()];
        for (int i = 0; i < choiceNumber.length; i++) {
            choiceNumber[i] = radioButtons.get(i).getText();
        }
        return choiceNumber;
    }

    String getChoiceAnswer() {
        StringBuilder choiceAnswer = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            if (radioButtons.get(i).isSelected()) {
                choiceAnswer.append("1");
            } else if (!radioButtons.get(i).isSelected()) {
                choiceAnswer.append("0");
            } else {
                JOptionPane.showMessageDialog(null, "获取选择题选择情况出现错误");
            }
        }
        return choiceAnswer.toString();
    }

}
