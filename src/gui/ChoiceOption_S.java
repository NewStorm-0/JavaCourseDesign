package gui;

import entity.choice;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class ChoiceOption_S {
    private final Font font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
    private final ArrayList<JRadioButton> radioButtons; //
    private final ArrayList<JTextArea> textAreas; //
    private final ArrayList<JScrollPane> scrollPanes; //
    private final choice[] choices;
    char symbol = 'A';

    ChoiceOption_S(choice[] choices) {
        this.choices = choices;
        radioButtons = new ArrayList<>();
        textAreas = new ArrayList<>();
        scrollPanes = new ArrayList<>();
        initializeChoices();
    }

    private void initializeChoices() {
        for (choice c : choices) {
            //选项按钮
            JRadioButton radioButton = new JRadioButton(String.valueOf(symbol));
            radioButton.setFont(font1);
            radioButton.setSize(40, 15);
            radioButton.setOpaque(false);
            radioButton.setForeground(new Color(255, 197, 26));
            int temp = symbol;
            temp++;
            symbol = (char) temp;
            radioButtons.add(radioButton);
            //选项内容文本框
            JTextArea textArea = new JTextArea();
            textArea.setFont(font1);
            textArea.setLineWrap(true);
            textArea.setBackground(new Color(64, 70, 72));
            textArea.setForeground(new Color(192, 186, 178));
            textArea.setText(c.getContent());
            textArea.setEditable(false);
            textAreas.add(textArea);
            //滚动面板
            JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            scrollPane.setViewportView(textArea);
            scrollPane.setSize(205, 90);
            scrollPanes.add(scrollPane);
        }
    }

    int getSize() {
        return radioButtons.size();
    }

    JRadioButton getRadioButton(int i) {
        return radioButtons.get(i);
    }

    JScrollPane getScrollPane(int i) {
        return scrollPanes.get(i);
    }

}
