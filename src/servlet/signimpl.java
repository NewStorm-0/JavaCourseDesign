package servlet;

import bean.Request;
import entity.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class signimpl implements sign {
    private students students;
    private teacher teacher;

    public signimpl(students students) {
        this.students = students;

    }

    public signimpl(teacher teacher) {
        this.teacher = teacher;
    }

    @Override
    public students studentsign() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.accountsimpl");// 全类名
            req.setMethodName("studentsign");//客户端发出指令
            req.setParameterTypes(new Class[]{students.class});
            req.setParameterValues(new Object[]{students});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            students students = (students) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return students;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public teacher teachersign() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.accountsimpl");// 全类名
            req.setMethodName("teachersign");//客户端发出注册指令
            req.setParameterTypes(new Class[]{teacher.class});
            req.setParameterValues(new Object[]{teacher});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            teacher teacher = (teacher) ois.readObject();
            oos.close();
            out.close();
            client.close();

            return teacher;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
