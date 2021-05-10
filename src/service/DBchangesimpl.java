package service;
import entity.*;
import dao.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBchangesimpl implements DBchanges{
    private Connection connection;
    public int publishtask(int classNo,int paperid,int time){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.publishtask(classNo,paperid,time);
    }
    public int addteacherintoclass(String teachername,int teacherid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.addteacherintoclass(teachername,teacherid);
    }
    public int addstudentintoclass(String classname,String password,String studentname,int studentid){
daoimpl daoimpl=new daoimpl(connection);
return daoimpl.addstudentintoclass(classname,password,studentname,studentid);
    }
    public int addclass(classes classes){
        daoimpl daoimpl=new daoimpl(connection,classes);
        return daoimpl.addclass();
    }
    @Override
    public int addquestion(question question) {
        daoimpl daoimpl=new daoimpl(connection,question);
        return daoimpl.addquestion();
    }
    public int addchoice(choice choice) {
        daoimpl daoimpl=new daoimpl(connection,choice);
        return daoimpl.addchoices();
    }
    @Override
    public int addrules(rules rules) {
        daoimpl daoimpl=new daoimpl(connection,rules);
        return daoimpl.addrules();
    }
    public int addtestdonetostudent(int studentid, String papername, int paperpoint, int score) {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.addtestdonetostudent(studentid, papername, paperpoint, score);
    }
    public int addtestdone(int score,int testpaperid,int studentno,String studentname,int time,int classNo,int state){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.addtestdone(score,testpaperid,studentno, studentname, time, classNo, state);
    }
    public int addtestdonequestion(question question,int score,int taskno,int studentno,int classNo,String studentanswer){
        daoimpl daoimpl=new daoimpl(connection,question);
        return daoimpl.addtestdonequestion(score,taskno,studentno,classNo,studentanswer);
    }
    public int addtestdonechoice(choice choice,int taskno,int studentno,int classNo){
        daoimpl daoimpl=new daoimpl(connection,choice);
        return daoimpl.addtestdonechoice(taskno, studentno, classNo);
    }

    @Override
    public int addtestpaper(testpaper testpaper) {
        daoimpl daoimpl= new daoimpl(connection,testpaper);
        return daoimpl.addtestpaper();
    }
    public int addtestquestion(question question) {
        daoimpl daoimpl= new daoimpl(connection,question);
        return daoimpl.addtestquestion();
    }
    public int addtestchoice(choice choice) {
        daoimpl daoimpl= new daoimpl(connection,choice);
        return daoimpl.addtestchoice();
    }
    @Override
    public int delquestion(question question) {
        daoimpl daoimpl=new daoimpl(connection,question);
        return daoimpl.delquestion();
    }


    @Override
    public int delrules(rules rules) {
        daoimpl daoimpl=new daoimpl(connection,rules);
        return daoimpl.delrules();
    }

    @Override
    public int deltestpaper() {
        return 0;
    }

    @Override
    public int changequestion(question question) {
        daoimpl daoimpl=new daoimpl(connection,question);
        return daoimpl.changequestion();
    }

    @Override
    public int changetestpaper() {
        return 0;
    }
    public DBchangesimpl () throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_pro?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", accounts.password);
        this.connection=connection;
    }

}
