package gui;

import service.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ServerInterface {
    private JFrame frame1;
    private Font font1;
    private Server server;

    public ServerInterface(Server server) {
        this.server = server;
        font1 = new Font("Microsoft YaHei UI", Font.PLAIN, 15);
        frame1 = new JFrame("卷——服务端");
        frame1.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 5));
        Container panel = frame1.getContentPane();
        String iconPath = this.getClass().getClassLoader().getResource("").getPath() + "../../../../first soft/material/aa.png";
        ImageIcon icon = new ImageIcon(iconPath);
        //
        JLabel label1 = new JLabel("服务器状态：");
        JLabel label2 = new JLabel("关闭");
        JButton button1 = new JButton("打开服务器");
        //
        label1.setFont(font1);
        label2.setFont(font1);
        button1.setFont(font1);
        label1.setBounds(10, 10, 100, 30);
        label2.setBounds(100, 10, 80, 30);
        button1.setBounds(150, 10, 120, 30);
        button1.setActionCommand("打开服务器");
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("打开服务器")) {
                    server.start();
                    label2.setText("打开");
                    button1.setText("关闭服务器");
                    button1.setActionCommand("关闭服务器");
                } else if (e.getActionCommand().equals("关闭服务器")) {
                    try {
                        server.serverSocket.close();
                        label2.setText("关闭");
                        button1.setText("打开服务器");
                        button1.setActionCommand("打开服务器");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        //
        panel.setLayout(null);
        panel.add(label1);
        panel.add(label2);
        panel.add(button1);
        //
        frame1.setSize(310, 100);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) screenSize.getWidth() / 2 - frame1.getWidth() / 2;
        int y = (int) screenSize.getHeight() / 2 - frame1.getHeight() / 2;
        frame1.setLocation(x, y);
        frame1.setIconImage(icon.getImage());
        frame1.setResizable(false);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.setDefaultLookAndFeelDecorated(true);
        frame1.setVisible(true);
    }
}
