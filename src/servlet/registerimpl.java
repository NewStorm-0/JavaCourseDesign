package servlet;
import bean.Request;
import entity.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class registerimpl implements register{
    private students students;
    private teacher teacher;

    public registerimpl(students students ){
        this.students=students;
    }
    public registerimpl(teacher teacher){
    this.teacher=teacher;
    }
    @Override
    public int student_register() throws Exception {
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream out = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
        req.setClassName("service.accountsimpl");// 全类名
        req.setMethodName("studentregister");//客户端发出指令
        req.setParameterTypes(new Class[]{students.class});
        req.setParameterValues(new Object[]{students});
        oos.writeObject(req);//使用Object输出流发送 Request
        InputStream in = client.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        Integer integer = (Integer) ois.readObject();
        oos.close();
        out.close();
        client.close();
        return integer;
    }
    @Override
    public int teacher_register() throws Exception {
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream out = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
        req.setClassName("service.accountsimpl");// 全类名
        req.setMethodName("teacherregister");//客户端发出指令
        req.setParameterTypes(new Class[]{teacher.class});
        req.setParameterValues(new Object[]{teacher});
        oos.writeObject(req);//使用Object输出流发送 Request
        InputStream in = client.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        Integer integer = (Integer) ois.readObject();
        oos.close();
        out.close();
        client.close();
        return integer;
    }

}
