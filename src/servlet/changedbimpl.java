package servlet;
import bean.Request;
import dao.daoimpl;
import entity.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
public class changedbimpl implements changedb{
    private String people;
    private classes classes;
    private question question;
    private rules rules;
    private String searchstring;
    private testpaper testpaper;
    private int id;
    private teacher teacher;
    private students students;
    private choicequestionDone[] choicequestionDone;
    private answerquestionDone[] answerquestionDone;
    private judgequestionDone[] judgequestionDone;
    public String checkstudentname(int studentid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkstudentname");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class});
            req.setParameterValues(new Object[]{studentid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return s;}catch(Exception e){e.printStackTrace();
            return null;}
    }
    public testpaper checktaskpaper(int classid,int taskid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checktaskpaper");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class,int.class});
            req.setParameterValues(new Object[]{classid,taskid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            testpaper testpaper = (testpaper) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return testpaper;
        }catch(Exception e){e.printStackTrace();
            return null;}
    }
    public String[] checkstudentclass(){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkstudentclass");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class});
            req.setParameterValues(new Object[]{id});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            oos.close();
            out.close();
            client.close();
            String[] strings=s.split(" ");
            String s1="";
            for(int i=0;i<strings.length;i++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkclassname");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class});
                req1.setParameterValues(new Object[]{Integer.parseInt(strings[i])});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                s1 += (String) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();

            }
            String temp=s+s1;
            String[] strings1=temp.split(" ");
            return strings1;}catch(Exception e){e.printStackTrace();
            return null;}
    }
    public String[] checkclasstask(int classno){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkclasstask");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class});
            req.setParameterValues(new Object[]{classno});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            System.out.println(s);
            oos.close();
            out.close();
            client.close();
            String[] strings=s.split(" ");
            return strings;}catch(Exception e){e.printStackTrace();
            return null;}
    }
    public String[] checkstudenttask(){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkstudenttask");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class});
            req.setParameterValues(new Object[]{id});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            System.out.println(s);
            oos.close();
            out.close();
            client.close();
            String[] strings=s.split(" ");
            return strings;}catch(Exception e){e.printStackTrace();
            return null;}
    }
    public int publishtask(int classNo,int paperid,int time){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("publishtask");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class,int.class,int.class});
            req.setParameterValues(new Object[]{classNo,paperid,time});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return integer;}catch(Exception e){e.printStackTrace();
            return 1;}
    }
    public int addteacherintoclass(String teachername,int teacherid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addteacherintoclass");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class,int.class});
            req.setParameterValues(new Object[]{teachername,teacherid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return integer;}catch(Exception e){e.printStackTrace();
            return 1;}
    }
    public int addstudentintoclass(String classname,String password,String studentname,int studentid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addstudentintoclass");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class,String.class,String.class,int.class});
            req.setParameterValues(new Object[]{classname,password,studentname,studentid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return integer;}catch(Exception e){e.printStackTrace();
            return 1;}
    }
    public int addclass(){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addclass");//客户端发出指令
            req.setParameterTypes(new Class[]{classes.class});
            req.setParameterValues(new Object[]{classes});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            int i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return i;}catch(Exception e){e.printStackTrace();
            return 1;}
    }

    public int addtestpaper(){try {
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream out = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
        req.setClassName("service.DBchangesimpl");// 全类名
        req.setMethodName("addtestpaper");//客户端发出指令
        req.setParameterTypes(new Class[]{testpaper.class});
        req.setParameterValues(new Object[]{testpaper});
        oos.writeObject(req);//使用Object输出流发送 Request
        InputStream in = client.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        Integer integer = (Integer) ois.readObject();
        oos.close();
        out.close();
        client.close();
    return integer;}catch(Exception e){e.printStackTrace();
    return 1;}
    }
    public int addtestdonetostudent(int studentid,testpaper testpaper,int score){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addtestdonetostudent");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class,String.class,int.class,int.class});
            req.setParameterValues(new Object[]{studentid,testpaper.getName(),testpaper.getPaperpoint(),score});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return 0;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int addtestdone(int score,int time,int classNo,int state){
        Integer integer=0;
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addtestdone");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class,int.class,int.class,String.class,int.class,int.class,int.class});
            System.out.println(""+testpaper.getId()+students.getId()+students.getName()+time+classNo+state);
            req.setParameterValues(new Object[]{score,testpaper.getId(),students.getId(),students.getName(),time,classNo,state});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            int taskno=(int)ois.readObject();
            System.out.println("(  "+classNo+" "+taskno);
            oos.close();
            out.close();
            client.close();
            System.out.println(choicequestionDone.length);
            for(int i=0;i<choicequestionDone.length;i++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.DBchangesimpl");// 全类名
                req1.setMethodName("addtestdonequestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{question.class,int.class,int.class,int.class,int.class,String.class});
                req1.setParameterValues(new Object[]{choicequestionDone[i],choicequestionDone[i].getScore(),taskno,students.getId(),classNo,choicequestionDone[i].getStudentanswer()});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                integer+=(Integer)ois1.readObject();
                System.out.println(integer+")))");
                oos1.close();
                out1.close();
                client1.close();
                for(int k=0;k<choicequestionDone[i].getChoices().length;k++){
                    Socket client2 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out2 = client2.getOutputStream();
                    ObjectOutputStream oos2 = new ObjectOutputStream(out2);
                    Request req2 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req2.setClassName("service.DBchangesimpl");// 全类名
                    req2.setMethodName("addtestdonechoice");//客户端发出指令
                    req2.setParameterTypes(new Class[]{choice.class,int.class,int.class,int.class});
                    req2.setParameterValues(new Object[]{choicequestionDone[i].getChoices()[k],taskno,students.getId(),classNo});
                    oos2.writeObject(req2);//使用Object输出流发送 Request
                    InputStream in2 = client2.getInputStream();
                    ObjectInputStream ois2 = new ObjectInputStream(in2);
                    integer += (Integer) ois2.readObject();
                    oos2.close();
                    out2.close();
                    client2.close();}
            }
            for(int i=0;i<answerquestionDone.length;i++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.DBchangesimpl");// 全类名
                req1.setMethodName("addtestdonequestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{question.class,int.class,int.class,int.class,int.class,String.class});
                req1.setParameterValues(new Object[]{answerquestionDone[i],answerquestionDone[i].getScore(),taskno,students.getId(),classNo,answerquestionDone[i].getStudentanswer()});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                integer+=(Integer)ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
            }
            for(int i=0;i<judgequestionDone.length;i++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.DBchangesimpl");// 全类名
                req1.setMethodName("addtestdonequestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{question.class,int.class,int.class,int.class,int.class,String.class});
                req1.setParameterValues(new Object[]{judgequestionDone[i],judgequestionDone[i].getScore(),taskno,students.getId(),classNo,judgequestionDone[i].getStudentanswer()});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                integer+=(Integer)ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
            }
            return integer;
        }catch(Exception e){e.printStackTrace();
            return 1;}

    }
    public int addtestquestion() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addtestquestion");//客户端发出指令
            req.setParameterTypes(new Class[]{question.class});
            req.setParameterValues(new Object[]{question});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            Integer integer1=0;
            if(question.getForm().equals("选择题")){
                choicequestion choicequestion =(choicequestion) question;
                for(int k=0;k<choicequestion.getChoices().length;k++){
                    Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out1 = client1.getOutputStream();
                    ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                    Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req1.setClassName("service.DBchangesimpl");// 全类名
                    req1.setMethodName("addtestchoice");//客户端发出指令
                    req1.setParameterTypes(new Class[]{choice.class});
                    req1.setParameterValues(new Object[]{choicequestion.getChoices()[k]});
                    oos1.writeObject(req1);//使用Object输出流发送 Request
                    InputStream in1 = client1.getInputStream();
                    ObjectInputStream ois1 = new ObjectInputStream(in1);
                    integer1 = (Integer) ois1.readObject();
                    oos.close();
                    out.close();
                    client.close();}
            }
            return integer+integer1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }
    @Override
    public int addquestion() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addquestion");//客户端发出指令
            req.setParameterTypes(new Class[]{question.class});
            req.setParameterValues(new Object[]{question});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            Integer integer1=0;
            if(question.getForm().equals("选择题")){
                choicequestion choicequestion =(choicequestion) question;
                for(int k=0;k<choicequestion.getChoices().length;k++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.DBchangesimpl");// 全类名
                req1.setMethodName("addchoice");//客户端发出指令
                req1.setParameterTypes(new Class[]{choice.class});
                req1.setParameterValues(new Object[]{choicequestion.getChoices()[k]});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                integer1 = (Integer) ois1.readObject();
                oos.close();
                out.close();
                client.close();}
            }
            return integer+integer1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int addrules() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("addrules");//客户端发出指令
            req.setParameterTypes(new Class[]{rules.class});
            req.setParameterValues(new Object[]{rules});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return integer;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }
    public int deleterules(){
        try {
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream out = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
        req.setClassName("service.DBchangesimpl");// 全类名
        req.setMethodName("delrules");//客户端发出指令
        req.setParameterTypes(new Class[]{rules.class});
        req.setParameterValues(new Object[]{rules});
        oos.writeObject(req);//使用Object输出流发送 Request
        InputStream in = client.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        Integer integer = (Integer) ois.readObject();
        oos.close();
        out.close();
        client.close();
        return integer; }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public int changequestion() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("changequestion");//客户端发出指令
            req.setParameterTypes(new Class[]{question.class});
            req.setParameterValues(new Object[]{question});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return integer;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public int delquestion() {
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.DBchangesimpl");// 全类名
            req.setMethodName("delquestion");//客户端发出指令
            req.setParameterTypes(new Class[]{question.class});
            req.setParameterValues(new Object[]{question});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            Integer integer = (Integer) ois.readObject();
            oos.close();
            out.close();
            client.close();
            return integer;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }
    public rules[] searchrules(){
        try {
            int i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checksearchedrulesnumber");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class});
            req.setParameterValues(new Object[]{searchstring});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            rules[] rules1=new rules[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("searchrules");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class,String.class});
                req1.setParameterValues(new Object[]{t,searchstring});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                rules rules = (rules) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                rules1[t]=rules;
            }
            return rules1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String[] checkdonestudent(int classid ,int taskid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkdonestudent");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class,int.class});
            System.out.println("beizhi:"+classid+taskid);
            req.setParameterValues(new Object[]{classid,taskid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            oos.close();
            out.close();
            client.close();
            System.out.println("warning:"+s+")");
            String[] s1=s.split(" ");
            return s1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String[] checkcorrectedstudent(int classid ,int taskid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkcorrectedstudent");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class,int.class});
            req.setParameterValues(new Object[]{classid,taskid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            oos.close();
            out.close();
            client.close();
            String[] s1=s.split(" ");
            return s1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String[] checktaskno(int classid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkclasstaskNo");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class});
            req.setParameterValues(new Object[]{classid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            oos.close();
            out.close();
            client.close();
            String[] s1=s.split(" ");
            return s1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String[] checkclasstudent(int classid){
        try {
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkclassstudent");//客户端发出指令
            req.setParameterTypes(new Class[]{int.class});
            req.setParameterValues(new Object[]{classid});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            String s = (String) ois.readObject();
            oos.close();
            out.close();
            client.close();
            String[] s1=s.split(" ");
            return s1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public classes[] checkclass(){
        try {
            int i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkclassnumber");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class,int.class});
            req.setParameterValues(new Object[]{people,id});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            System.out.println(i);
            oos.close();
            out.close();
            client.close();
            classes[] classes=new classes[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkclass");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class,String.class,int.class});
                req1.setParameterValues(new Object[]{t,people,id});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                classes classes1=(classes)ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                classes[t]=classes1;
            }
            return classes;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public rules[] checkrule(){
        try {
            int i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkrulesnumber");//客户端发出指令
            req.setParameterTypes(null);
            req.setParameterValues(null);
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            rules[] rules1=new rules[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkrules");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class});
                req1.setParameterValues(new Object[]{t});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                rules rules = (rules) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                rules1[t]=rules;
            }
            return rules1;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public question[] searchquestion(){
        try {
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checksearchedquestionnumber");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class});
            req.setParameterValues(new Object[]{searchstring});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            question[] questions=new question[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("searchquestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class,String.class});
                req1.setParameterValues(new Object[]{t,searchstring});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                question question1 = (question) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                questions[t]=question1;
                //System.out.println(question1.getId());
                if(question1.getForm().equals("选择题")){
                    choicequestion choicequestion=(entity.choicequestion)question1;
                    choicequestion.setChoices(null);
                    int e=question1.getAnswer().length();
                    choice[] choices=new choice[e];
                    Socket client2 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out2 = client2.getOutputStream();
                    ObjectOutputStream oos2 = new ObjectOutputStream(out2);
                    Request req2 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req2.setClassName("service.checkDBimpl");// 全类名
                    req2.setMethodName("checkchoice");//客户端发出指令
                    req2.setParameterTypes(new Class[]{question.class});
                    req2.setParameterValues(new Object[]{question1});
                    oos2.writeObject(req2);//使用Object输出流发送 Request
                    InputStream in2 = client2.getInputStream();
                    ObjectInputStream ois2 = new ObjectInputStream(in2);
                    String s = (String) ois2.readObject();
                    oos2.close();
                    out2.close();
                    client2.close();
                    //System.out.println(s);
                    String[] strings=s.split(" ");
                    int w=0;
                    for(String s1:strings){
                        choices[w]=new choice();
                        choices[w].setContent(s1);
                        w++;
                    }
                    choicequestion.setChoices(choices);
                    questions[t]=choicequestion;
                }
            }
            return questions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public question[] searchchoicequestion(){
        try {
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checksearchedchoicequestionnumber");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class});
            req.setParameterValues(new Object[]{searchstring});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            question[] questions=new question[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("searchchoicequestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class,String.class});
                req1.setParameterValues(new Object[]{t,searchstring});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                question question1 = (question) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                questions[t]=question1;
                    choicequestion choicequestion=(entity.choicequestion)question1;
                    choicequestion.setChoices(null);
                    int e=question1.getAnswer().length();
                    choice[] choices=new choice[e];
                    Socket client2 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out2 = client2.getOutputStream();
                    ObjectOutputStream oos2 = new ObjectOutputStream(out2);
                    Request req2 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req2.setClassName("service.checkDBimpl");// 全类名
                    req2.setMethodName("checkchoice");//客户端发出指令
                    req2.setParameterTypes(new Class[]{question.class});
                    req2.setParameterValues(new Object[]{question1});
                    oos2.writeObject(req2);//使用Object输出流发送 Request
                    InputStream in2 = client2.getInputStream();
                    ObjectInputStream ois2 = new ObjectInputStream(in2);
                    String s = (String) ois2.readObject();
                    oos2.close();
                    out2.close();
                    client2.close();
                    //System.out.println(s);
                    String[] strings=s.split(" ");
                    int w=0;
                    for(String s1:strings){
                        choices[w]=new choice();
                        choices[w].setContent(s1);
                        w++;
                    }
                    choicequestion.setChoices(choices);
                    questions[t]=choicequestion;

            }
            return questions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public question[] searchanswerquestion(){
        try {
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checksearchedanswerquestionnumber");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class});
            req.setParameterValues(new Object[]{searchstring});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            question[] questions=new question[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("searchanswerquestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class,String.class});
                req1.setParameterValues(new Object[]{t,searchstring});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                question question1 = (question) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                questions[t]=question1;
            }
            return questions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public question[] searchjudgequestion(){
        try {
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checksearchedjudgequestionnumber");//客户端发出指令
            req.setParameterTypes(new Class[]{String.class});
            req.setParameterValues(new Object[]{searchstring});
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            question[] questions=new question[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("searchjudgequestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class,String.class});
                req1.setParameterValues(new Object[]{t,searchstring});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                question question1 = (question) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                questions[t]=question1;
            }
            return questions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public question[] checkchoicequestion(){
        try {
        Integer i=0;
        Socket client = new Socket(InetAddress.getLocalHost(), 9999);
        OutputStream out = client.getOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(out);
        Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
        req.setClassName("service.checkDBimpl");// 全类名
        req.setMethodName("checkchoicequestionnumber");//客户端发出指令
        req.setParameterTypes(null);
        req.setParameterValues(null);
        oos.writeObject(req);//使用Object输出流发送 Request
        InputStream in = client.getInputStream();
        ObjectInputStream ois = new ObjectInputStream(in);
        i = (int) ois.readObject();
        oos.close();
        out.close();
        client.close();
        //System.out.println(i);
        question[] questions=new question[i];
        for(int t=0;t<i;t++){
            Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out1 = client1.getOutputStream();
            ObjectOutputStream oos1 = new ObjectOutputStream(out1);
            Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req1.setClassName("service.checkDBimpl");// 全类名
            req1.setMethodName("checkchoicequestion");//客户端发出指令
            req1.setParameterTypes(new Class[]{int.class});
            req1.setParameterValues(new Object[]{t});
            oos1.writeObject(req1);//使用Object输出流发送 Request
            InputStream in1 = client1.getInputStream();
            ObjectInputStream ois1 = new ObjectInputStream(in1);
            question question1 = (question) ois1.readObject();
            oos1.close();
            out1.close();
            client1.close();
            questions[t]=question1;
            choicequestion choicequestion=(entity.choicequestion)question1;
            choicequestion.setChoices(null);
            int e=question1.getAnswer().length();
            choice[] choices=new choice[e];
            Socket client2 = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out2 = client2.getOutputStream();
            ObjectOutputStream oos2 = new ObjectOutputStream(out2);
            Request req2 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req2.setClassName("service.checkDBimpl");// 全类名
            req2.setMethodName("checkchoice");//客户端发出指令
            req2.setParameterTypes(new Class[]{question.class});
            req2.setParameterValues(new Object[]{question1});
            oos2.writeObject(req2);//使用Object输出流发送 Request
            InputStream in2 = client2.getInputStream();
            ObjectInputStream ois2 = new ObjectInputStream(in2);
            String s = (String) ois2.readObject();
            oos2.close();
            out2.close();
            client2.close();
            String[] strings=s.split(" ");
            int w=0;
            for(String s1:strings){
                choices[w]=new choice();
                choices[w].setContent(s1);
                w++;
            }
            choicequestion.setChoices(choices);
            questions[t]=choicequestion;

        }
        return questions;
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return null;
    }}
    public question[] checkanswerquestion(){
        try {
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkanswerquestionnumber");//客户端发出指令
            req.setParameterTypes(null);
            req.setParameterValues(null);
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            question[] questions=new question[i];
            for(int t=0;t<i;t++) {
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkanswerquestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class});
                req1.setParameterValues(new Object[]{t});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                question question1 = (question) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                questions[t] = question1;
            }
            return questions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }}
    public question[] checkjudgequestion(){
        try {
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkjudgequestionnumber");//客户端发出指令
            req.setParameterTypes(null);
            req.setParameterValues(null);
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            question[] questions=new question[i];
            for(int t=0;t<i;t++) {
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkjudgequestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class});
                req1.setParameterValues(new Object[]{t});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                question question1 = (question) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                questions[t] = question1;
            }
            return questions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }}
        public testpaper checkpointedpaper(int paperid){
            try{
                    Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out1 = client1.getOutputStream();
                    ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                    Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req1.setClassName("service.checkDBimpl");// 全类名
                    req1.setMethodName("checkpointedpaper");//客户端发出指令
                    req1.setParameterTypes(new Class[]{int.class});
                    req1.setParameterValues(new Object[]{paperid});
                    oos1.writeObject(req1);//使用Object输出流发送 Request
                    InputStream in1 = client1.getInputStream();
                    ObjectInputStream ois1 = new ObjectInputStream(in1);
                    testpaper testpaper1 = (testpaper) ois1.readObject();
                    oos1.close();
                    out1.close();
                    client1.close();
                    Socket client2= new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out2 = client2.getOutputStream();
                    ObjectOutputStream oos2 = new ObjectOutputStream(out2);
                    Request req2 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req2.setClassName("service.checkDBimpl");// 全类名
                    req2.setMethodName("checkpaperquestionnumber");//客户端发出指令
                    req2.setParameterTypes(new Class[]{int.class});
                    req2.setParameterValues(new Object[]{testpaper1.getId()});
                    oos2.writeObject(req2);//使用Object输出流发送 Request
                    InputStream in2 = client2.getInputStream();
                    ObjectInputStream ois2 = new ObjectInputStream(in2);
                    int qunum=(int)ois2.readObject();
                    oos2.close();
                    out2.close();
                    client2.close();
                    question[] questionlist=new question[qunum];
                    for(int i1=0;i1<qunum;i1++){
                        Socket client3 = new Socket(InetAddress.getLocalHost(), 9999);
                        OutputStream out3 = client3.getOutputStream();
                        ObjectOutputStream oos3 = new ObjectOutputStream(out3);
                        Request req3 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                        req3.setClassName("service.checkDBimpl");// 全类名
                        req3.setMethodName("checkpaperquestion");//客户端发出指令
                        req3.setParameterTypes(new Class[]{int.class,int.class});
                        req3.setParameterValues(new Object[]{i1,paperid});
                        oos3.writeObject(req3);//使用Object输出流发送 Request
                        InputStream in3 = client3.getInputStream();
                        ObjectInputStream ois3 = new ObjectInputStream(in3);
                        question question1 = (question) ois3.readObject();
                        oos3.close();
                        out3.close();
                        client3.close();
                        questionlist[i1]=question1;
                        //System.out.println(question1.getId());
                        if(question1.getForm().equals("选择题")){
                            choicequestion choicequestion=(entity.choicequestion)question1;
                            choicequestion.setChoices(null);
                            int e=question1.getAnswer().length();
                            choice[] choices=new choice[e];
                            Socket client4 = new Socket(InetAddress.getLocalHost(), 9999);
                            OutputStream out4 = client4.getOutputStream();
                            ObjectOutputStream oos4 = new ObjectOutputStream(out4);
                            Request req4 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                            req4.setClassName("service.checkDBimpl");// 全类名
                            req4.setMethodName("checkpaperquestionchoice");//客户端发出指令
                            req4.setParameterTypes(new Class[]{int.class,question.class});
                            req4.setParameterValues(new Object[]{testpaper1.getId(),question1});
                            oos4.writeObject(req4);//使用Object输出流发送 Request
                            InputStream in4 = client4.getInputStream();
                            ObjectInputStream ois4 = new ObjectInputStream(in4);
                            String s = (String) ois4.readObject();
                            oos4.close();
                            out4.close();
                            client4.close();
                            //System.out.println(s);
                            String[] strings=s.split(" ");
                            int w=0;
                            for(String s1:strings){
                                choices[w]=new choice();
                                choices[w].setContent(s1);
                                w++;
                            }
                            choicequestion.setChoices(choices);
                            questionlist[i1]=choicequestion;
                        }
                    }
                    testpaper1.setQuestionlist(questionlist);

                return testpaper1;


            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        public testDone checktestdone(int paperid,int studentid,int classid){
            try{
                int taskid=1;
                Socket client = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out = client.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out);
                Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req.setClassName("service.checkDBimpl");// 全类名
                req.setMethodName("checktestdonetaskno");//客户端发出指令
                req.setParameterTypes(new Class[]{int.class,int.class});
                req.setParameterValues(new Object[]{paperid,classid});
                oos.writeObject(req);//使用Object输出流发送 Request
                InputStream in = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in);
                taskid = (int) ois.readObject();
                System.out.println(taskid+"feds");
                oos.close();
                out.close();
                client.close();
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkpointedpaper");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class});
                req1.setParameterValues(new Object[]{paperid});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                testpaper testpaper1 = (testpaper) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                choicequestionDone[] choicequestionDones=new choicequestionDone[testpaper1.getChoicenumber()];
                answerquestionDone[] answerquestionDones=new answerquestionDone[testpaper1.getAnswernumber()];
                judgequestionDone[] judgequestionDones=new judgequestionDone[testpaper1.getJudgenumber()];
                for(int i1=0;i1<choicequestionDones.length;i1++){
                    Socket client3 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out3 = client3.getOutputStream();
                    ObjectOutputStream oos3 = new ObjectOutputStream(out3);
                    Request req3 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req3.setClassName("service.checkDBimpl");// 全类名
                    req3.setMethodName("checkchoicedone");//客户端发出指令
                    req3.setParameterTypes(new Class[]{int.class,int.class,int.class,int.class});
                    req3.setParameterValues(new Object[]{i1,taskid,studentid,classid});
                    oos3.writeObject(req3);//使用Object输出流发送 Request
                    InputStream in3 = client3.getInputStream();
                    ObjectInputStream ois3 = new ObjectInputStream(in3);
                    choicequestionDone choicequestionDone = (choicequestionDone) ois3.readObject();
                    oos3.close();
                    out3.close();
                    client3.close();
                    choicequestionDones[i1]=choicequestionDone;
                    choicequestionDone.setChoices(null);
                    int e=choicequestionDone.getAnswer().length();
                    choice[] choices=new choice[e];
                    Socket client4 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out4 = client4.getOutputStream();
                    ObjectOutputStream oos4 = new ObjectOutputStream(out4);
                    Request req4 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req4.setClassName("service.checkDBimpl");// 全类名
                    req4.setMethodName("checkchoicedonechoices");//客户端发出指令
                    req4.setParameterTypes(new Class[]{question.class,int.class,int.class,int.class});
                    req4.setParameterValues(new Object[]{choicequestionDone,taskid,studentid,classid});
                    oos4.writeObject(req4);//使用Object输出流发送 Request
                    InputStream in4 = client4.getInputStream();
                    ObjectInputStream ois4 = new ObjectInputStream(in4);
                    String s = (String) ois4.readObject();
                    oos4.close();
                    out4.close();
                    client4.close();
                    //System.out.println(s);
                    String[] strings=s.split(" ");
                    int w=0;
                    for(String s1:strings){
                        choices[w]=new choice();
                        choices[w].setContent(s1);
                        w++;
                    }
                    choicequestionDone.setChoices(choices);
                    choicequestionDones[i1]=choicequestionDone;

                }
                for(int i1=0;i1<answerquestionDones.length;i1++){
                    Socket client3 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out3 = client3.getOutputStream();
                    ObjectOutputStream oos3 = new ObjectOutputStream(out3);
                    Request req3 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req3.setClassName("service.checkDBimpl");// 全类名
                    req3.setMethodName("checkanswerdone");//客户端发出指令
                    req3.setParameterTypes(new Class[]{int.class,int.class,int.class,int.class});
                    req3.setParameterValues(new Object[]{i1,taskid,studentid,classid});
                    oos3.writeObject(req3);//使用Object输出流发送 Request
                    InputStream in3 = client3.getInputStream();
                    ObjectInputStream ois3 = new ObjectInputStream(in3);
                    answerquestionDone answerquestionDone = (answerquestionDone) ois3.readObject();
                    oos3.close();
                    out3.close();
                    client3.close();
                    answerquestionDones[i1]=answerquestionDone;
                }
                for(int i1=0;i1<judgequestionDones.length;i1++){
                    Socket client3 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out3 = client3.getOutputStream();
                    ObjectOutputStream oos3 = new ObjectOutputStream(out3);
                    Request req3 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req3.setClassName("service.checkDBimpl");// 全类名
                    req3.setMethodName("checkjudgedone");//客户端发出指令
                    req3.setParameterTypes(new Class[]{int.class,int.class,int.class,int.class});
                    req3.setParameterValues(new Object[]{i1,taskid,studentid,classid});
                    oos3.writeObject(req3);//使用Object输出流发送 Request
                    InputStream in3 = client3.getInputStream();
                    ObjectInputStream ois3 = new ObjectInputStream(in3);
                    judgequestionDone judgequestionDone = (judgequestionDone) ois3.readObject();
                    oos3.close();
                    out3.close();
                    client3.close();
                    judgequestionDones[i1]=judgequestionDone;
                }
                testDone testDone=new testDone();
                testDone.setId(testpaper1.getId());
                testDone.setQuestionnumber(testpaper1.getQuestionnumber());
                testDone.setPaperpoint(testpaper1.getPaperpoint());
                testDone.setName(testpaper1.getName());
                testDone.setCreater(testpaper1.getCreater());
                testDone.setChoicenumber(testpaper1.getChoicenumber());
                testDone.setJudgenumber(testpaper1.getJudgenumber());
                testDone.setAnswernumber(testpaper1.getAnswernumber());
                testDone.setChoicequestionDones(choicequestionDones);
                testDone.setAnswerquestionDones(answerquestionDones);
                testDone.setJudgequestionDones(judgequestionDones);
                return testDone;


            }catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        public testpaper[] checkpaper(){
        try{
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkpapernumber");//客户端发出指令
            req.setParameterTypes(null);
            req.setParameterValues(null);
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            testpaper[] testpapers=new testpaper[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkpaper");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class});
                req1.setParameterValues(new Object[]{t});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                testpaper testpaper1 = (testpaper) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                Socket client2= new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out2 = client2.getOutputStream();
                ObjectOutputStream oos2 = new ObjectOutputStream(out2);
                Request req2 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req2.setClassName("service.checkDBimpl");// 全类名
                req2.setMethodName("checkpaperquestionnumber");//客户端发出指令
                req2.setParameterTypes(new Class[]{int.class});
                req2.setParameterValues(new Object[]{testpaper1.getId()});
                oos2.writeObject(req2);//使用Object输出流发送 Request
                InputStream in2 = client2.getInputStream();
                ObjectInputStream ois2 = new ObjectInputStream(in2);
                int qunum=(int)ois2.readObject();
                oos2.close();
                out2.close();
                client2.close();
                question[] questionlist=new question[qunum];
                for(int i1=0;i1<qunum;i1++){
                    Socket client3 = new Socket(InetAddress.getLocalHost(), 9999);
                    OutputStream out3 = client3.getOutputStream();
                    ObjectOutputStream oos3 = new ObjectOutputStream(out3);
                    Request req3 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                    req3.setClassName("service.checkDBimpl");// 全类名
                    req3.setMethodName("checkpaperquestion");//客户端发出指令
                    req3.setParameterTypes(new Class[]{int.class,int.class});
                    req3.setParameterValues(new Object[]{i1,testpaper1.getId()});
                    oos3.writeObject(req3);//使用Object输出流发送 Request
                    InputStream in3 = client3.getInputStream();
                    ObjectInputStream ois3 = new ObjectInputStream(in3);
                    question question1 = (question) ois3.readObject();
                    oos3.close();
                    out3.close();
                    client3.close();
                    questionlist[i1]=question1;
                    //System.out.println(question1.getId());
                    if(question1.getForm().equals("选择题")){
                        choicequestion choicequestion=(entity.choicequestion)question1;
                        choicequestion.setChoices(null);
                        int e=question1.getAnswer().length();
                        choice[] choices=new choice[e];
                        Socket client4 = new Socket(InetAddress.getLocalHost(), 9999);
                        OutputStream out4 = client4.getOutputStream();
                        ObjectOutputStream oos4 = new ObjectOutputStream(out4);
                        Request req4 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                        req4.setClassName("service.checkDBimpl");// 全类名
                        req4.setMethodName("checkpaperquestionchoice");//客户端发出指令
                        req4.setParameterTypes(new Class[]{int.class,question.class});
                        req4.setParameterValues(new Object[]{testpaper1.getId(),question1});
                        oos4.writeObject(req4);//使用Object输出流发送 Request
                        InputStream in4 = client4.getInputStream();
                        ObjectInputStream ois4 = new ObjectInputStream(in4);
                        String s = (String) ois4.readObject();
                        oos4.close();
                        out4.close();
                        client4.close();
                        //System.out.println(s);
                        String[] strings=s.split(" ");
                        int w=0;
                        for(String s1:strings){
                            choices[w]=new choice();
                            choices[w].setContent(s1);
                            w++;
                        }
                        choicequestion.setChoices(choices);
                        questionlist[i1]=choicequestion;
                    }
                }
                testpaper1.setQuestionlist(questionlist);
                testpapers[t]=testpaper1;
            }
            return testpapers;


        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        }
        public String[] checktestdoneforstudent(int studentid){
            try {
                Socket client = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out = client.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(out);
                Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req.setClassName("service.checkDBimpl");// 全类名
                req.setMethodName("checktestdoneforstudent");//客户端发出指令
                req.setParameterTypes(new Class[]{int.class});
                req.setParameterValues(new Object[]{studentid});
                oos.writeObject(req);//使用Object输出流发送 Request
                InputStream in = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(in);
                String s = (String) ois.readObject();
                oos.close();
                out.close();
                client.close();
                String[] strings=s.split(" ");
                return strings;

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }
    @Override
    public question[] checkquestion() {
        try {
            Integer i=0;
            Socket client = new Socket(InetAddress.getLocalHost(), 9999);
            OutputStream out = client.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(out);
            Request req = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
            req.setClassName("service.checkDBimpl");// 全类名
            req.setMethodName("checkquestionnumber");//客户端发出指令
            req.setParameterTypes(null);
            req.setParameterValues(null);
            oos.writeObject(req);//使用Object输出流发送 Request
            InputStream in = client.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(in);
            i = (int) ois.readObject();
            oos.close();
            out.close();
            client.close();
            //System.out.println(i);
            question[] questions=new question[i];
            for(int t=0;t<i;t++){
                Socket client1 = new Socket(InetAddress.getLocalHost(), 9999);
                OutputStream out1 = client1.getOutputStream();
                ObjectOutputStream oos1 = new ObjectOutputStream(out1);
                Request req1 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                req1.setClassName("service.checkDBimpl");// 全类名
                req1.setMethodName("checkquestion");//客户端发出指令
                req1.setParameterTypes(new Class[]{int.class});
                req1.setParameterValues(new Object[]{t});
                oos1.writeObject(req1);//使用Object输出流发送 Request
                InputStream in1 = client1.getInputStream();
                ObjectInputStream ois1 = new ObjectInputStream(in1);
                question question1 = (question) ois1.readObject();
                oos1.close();
                out1.close();
                client1.close();
                questions[t]=question1;
                //System.out.println(question1.getId());
                if(question1.getForm().equals("选择题")){
                    choicequestion choicequestion=(entity.choicequestion)question1;
                    choicequestion.setChoices(null);
                    int e=question1.getAnswer().length();
                    choice[] choices=new choice[e];
                        Socket client2 = new Socket(InetAddress.getLocalHost(), 9999);
                        OutputStream out2 = client2.getOutputStream();
                        ObjectOutputStream oos2 = new ObjectOutputStream(out2);
                        Request req2 = new Request();//需要建立Request类，包含方法类型和参数等，序列化发送
                        req2.setClassName("service.checkDBimpl");// 全类名
                        req2.setMethodName("checkchoice");//客户端发出指令
                        req2.setParameterTypes(new Class[]{question.class});
                        req2.setParameterValues(new Object[]{question1});
                        oos2.writeObject(req2);//使用Object输出流发送 Request
                        InputStream in2 = client2.getInputStream();
                        ObjectInputStream ois2 = new ObjectInputStream(in2);
                        String s = (String) ois2.readObject();
                        oos2.close();
                        out2.close();
                        client2.close();
                        //System.out.println(s);
                        String[] strings=s.split(" ");
                        int w=0;
                        for(String s1:strings){
                            choices[w]=new choice();
                            choices[w].setContent(s1);
                            w++;
                        }
                    choicequestion.setChoices(choices);
                    questions[t]=choicequestion;
                }
            }
            return questions;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    public changedbimpl(classes classes){
        this.classes=classes;
    }
    public changedbimpl(question question){
        this.question=question;
    }
    public changedbimpl(students students,question question){

        this.students=students;
        this.question=question;
    }
    public changedbimpl(rules rules ){
        this.rules=rules;
    }
    public changedbimpl(testpaper testpaper){
        this.testpaper=testpaper;
    }
    public changedbimpl(students students,testpaper testpaper){
        this.students=students;
        this.testpaper=testpaper;
    }
    public changedbimpl(students students,testDone testDone){
        this.students=students;
        testpaper testpaper1=new testpaper();
        testpaper1.setId(testDone.getId());
        this.testpaper=testpaper1;
        this.answerquestionDone=testDone.getAnswerquestionDones();
        this.judgequestionDone=testDone.getJudgequestionDones();
        this.choicequestionDone=testDone.getChoicequestionDones();
    }
    public changedbimpl(String searchstring){this.searchstring=searchstring;}
    public changedbimpl(){}
    public changedbimpl(students students){
        this.people="student";
        this.id=students.getId();
    }
    public changedbimpl(teacher teacher){
    this.people="teacher";
    this.id=teacher.getId();
    }
}