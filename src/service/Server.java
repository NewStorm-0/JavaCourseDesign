package service;
import java.io.*;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import bean.Request;
import bean.Response;
import entity.*;
import gui.ServerInterface;

public class Server extends Thread{

    public ServerSocket serverSocket;

    public Server(int port) throws  IOException {
        serverSocket = new ServerSocket(port);
    }
    public void run() {

        try {
            while(true) {
                Socket s = serverSocket.accept();
                System.out.println(0);
                InputStream ins = s.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(ins);
                Request readObject = (Request) ois.readObject();
                /*
                 * 反射获取 Request 中的信息
                 */
                //获取类名
                String className = readObject.getClassName();
                //获取方法名
                String methodName = readObject.getMethodName();
                //获取参数类型
                Class[] parameterTypes = readObject.getParameterTypes();
                //获取 参数（实参）
                Object[] parameterValues = readObject.getParameterValues();
                //调用服务器中本地方法
                Class<?> request = Class.forName(className);
                //方法调用， 创建反射Class的对象  newInstance
                OutputStream out = s.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out);
                oos.writeObject(request.getMethod(methodName,parameterTypes).invoke(request.getDeclaredConstructor().newInstance(),parameterValues));
                oos.flush();
                s.close();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String [] args)
    {
        int port = 9999;
        try
        {
            Thread t = new Server(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}


