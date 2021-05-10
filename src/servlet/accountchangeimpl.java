package servlet;
import bean.Request;
import bean.Response;
import entity.*;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class accountchangeimpl implements accountchange{
    private teacher teacher;
    private students students;
    public accountchangeimpl(){}
    public accountchangeimpl(teacher teacher){
        this.teacher=teacher;
    }

    public accountchangeimpl(students students){
        this.students=students;
    }
    public int teacheraccountchange() throws Exception {
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream out = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
        req.setClassName("service.accountsimpl");// 全类名
        req.setMethodName("teacherchangeaccount");//客户端发出指令
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
