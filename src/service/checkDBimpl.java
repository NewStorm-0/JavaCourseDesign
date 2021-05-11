package service;

import entity.*;
import dao.daoimpl;

import java.sql.*;

public class checkDBimpl implements checkDB{
    private Connection connection;
    public checkDBimpl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_pro?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", accounts.password);
        this.connection=connection;
    }
    public int checkclassnumber(String people,int peopleid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkclassnumber(people,peopleid);
    }
    public int checkpapernumber() throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkpapernumber();}
        public int checkpaperquestionnumber(int t) {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkpaperquestionnumber(t);
    }
    public int checkquestionnumber() throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkquestionnumber();
    }public int checkrulesnumber() throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkrulesnumber();
    }
    public int checkchoicequestionnumber() throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkchoicequestionnumber();
    }
    public int checkanswerquestionnumber() throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkanswerquestionnumber();
    }
    public int checkjudgequestionnumber() throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkjudgequestionnumber();
    }
    public int checksearchedquestionnumber(String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checksearchedquestionnumber(searchstring);
    }
    public int checksearchedrulesnumber(String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checksearchedrulesnumber(searchstring);
    }
    public int checksearchedchoicequestionnumber(String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checksearchedchoicequestionnumber(searchstring);
    }
    public int checksearchedanswerquestionnumber(String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checksearchedanswerquestionnumber(searchstring);
    }
    public int checksearchedjudgequestionnumber(String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checksearchedjudgequestionnumber(searchstring);
    }
    public question searchquestion(int i,String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.searchquestion(searchstring);
        return questions[i];
    }
    public question searchchoicequestion(int i,String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.searchchoicequestion(searchstring);
        return questions[i];
    }
    public question searchanswerquestion(int i,String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.searchanswerquestion(searchstring);
        return questions[i];
    }
    public question searchjudgequestion(int i,String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.searchjudgequestion(searchstring);
        return questions[i];
    }
    public rules searchrules(int i,String searchstring) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        rules[] rules=daoimpl.searchrules(searchstring);
        return rules[i];
    }
    public String checktestdoneforstudent(int studentid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checktestdoneforstudent(studentid);
        }
    public testpaper checktaskpaper(int classid,int taskid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checktaskpaper(classid,taskid);
    }
    public String checkstudentname(int studentid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkstudentname(studentid);
    }

    public String checkdonestudent(int classid ,int taskid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkdonestudent(classid,taskid);
    }
    public String checkcorrectedstudent(int classid ,int taskid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkcorrectedstudent(classid,taskid);
    }
    public String checkclasstaskNo(int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkclasstaskNo(classid);}
    public String checkstudentclass(int studentid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkstudentclass(studentid);
    }
    public String checkclassname(int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkclassname(classid);
    }
    public String checkclasstask(int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkclasstask(classid);
    }
    public String checkstudenttask(int studentid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkstudenttask(studentid);
    }
    public String checkclassstudent(int id){daoimpl daoimpl=new daoimpl(connection);
    return daoimpl.checkclassstudent(id);}
    public classes checkclass(int i,String people,int peopleid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkclass(people,peopleid)[i];
    }
    public testpaper checkpointedpaper(int id){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkpointedpaper(id);
    }
    public testpaper checkpaper(int t)throws Exception{
        daoimpl daoimpl=new daoimpl(connection);
        testpaper[] testpapers=daoimpl.checktestpapers();
        return testpapers[t];
    }
    public int checktestdonetaskno(int paper_id ,int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checktestdonetaskno(paper_id,classid);
    }
    public question checkpaperquestion(int i,int t){
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.checkpaperquestion(t);
        return questions[i];
    }
    @Override
    public question checkquestion(int i) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.checkquestion();
        return questions[i];
    }
    public choicequestionDone checkchoicedone(int i,int taskid,int studentid,int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkchoicedone(taskid, studentid, classid)[i];
    }
    public answerquestionDone checkanswerdone(int i,int taskid,int studentid,int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkanswerdone(taskid, studentid, classid)[i];
    }
    public judgequestionDone checkjudgedone(int i,int taskid,int studentid,int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkjudgedone(taskid, studentid, classid)[i];
    }
    public String checkchoicedonechoices(question question,int taskid,int studentid,int classid){
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkchoicedonechoices(question, taskid, studentid, classid);
    }
    public question checkchoicequestion(int i) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.checkchoicequestion();
        return questions[i];
    }
    public question checkanswerquestion(int i) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.checkanswerquestion();
        return questions[i];
    }
    public question checkjudgequestion(int i) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        question[] questions=daoimpl.checkjudgequestion();
        return questions[i];
    }
    public String checkchoice(question question)  {
        daoimpl daoimpl=new daoimpl(connection);
        String s =daoimpl.checkchoice(question);
        return s;
    }
    public String checkpaperquestionchoice(int q,question question){
        daoimpl daoimpl=new daoimpl(connection);
        String s =daoimpl.checkpaperquestionchoice(q,question);
        return s;
    }
    public rules checkrules(int i) throws Exception {
        daoimpl daoimpl=new daoimpl(connection);
        return daoimpl.checkrules()[i];
    }
}
