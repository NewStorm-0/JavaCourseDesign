package dao;

import java.sql.*;

import entity.*;

public class daoimpl implements dao {
    final private Connection connection;
    private question question;
    private rules rules;
    private choice choice;
    private testpaper testpaper;
    private classes classes;

    public daoimpl(Connection connection) {
        this.connection = connection;
    }
    public daoimpl(Connection connection,classes classes) {

        this.connection = connection;
        this.classes=classes;
    }

    public daoimpl(Connection connection, question question) {
        this.connection = connection;
        this.question = question;
    }

    public daoimpl(Connection connection, rules rules) {
        this.connection = connection;
        this.rules = rules;
    }
    public daoimpl(Connection connection, choice choice) {
        this.connection = connection;
        this.choice=choice;
    }
    public daoimpl(Connection connection, testpaper testpaper) {
        this.connection = connection;
       this.testpaper=testpaper;
    }
    @Override
    public int correcting() {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {

        } catch (Exception exception) {

        } finally {

        }
        return result;
    }
    public int checkclassnumber(String people,int peopleid){
        Statement statement = null;
        int n = 0;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from classfor"+people+peopleid);
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checkrulesnumber() {
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from rules");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checkquestionnumber() {
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checkpaperquestionnumber(int t){
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from paper"+t);
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public testpaper checkpointedpaper(int id){
        Statement statement = null;
        testpaper testpaper= new testpaper();
        try {
            statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from paperlist where paper_id="+id);
            while (resultSet.next()) {
                testpaper.setId(resultSet.getInt("paper_id"));
                testpaper.setName(resultSet.getString("paper_name"));
                testpaper.setCreater(resultSet.getString("paper_creater"));
                testpaper.setPaperpoint(resultSet.getInt("paper_paperpoint"));
                testpaper.setQuestionnumber(resultSet.getInt("paper_questionnumber"));
                testpaper.setChoicenumber(resultSet.getInt("paper_choicenumber"));
                testpaper.setAnswernumber(resultSet.getInt("paper_answernumber"));
                testpaper.setJudgenumber(resultSet.getInt("paper_judgenumber"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return testpaper;
        }}
    public String checkclassstudent(int classid){
        String s="";
        try{
        Statement statement=connection.createStatement();
        int n=0;
        ResultSet resultSet1=statement.executeQuery("select * from class"+classid);
        while (resultSet1.next()){
            s+= resultSet1.getString("people_name");
            s+=" ";
        }}catch(Exception e){}finally {
            return s;
        }

    }
    public classes[] checkclass(String people,int peopleid){
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from classfor"+people+peopleid);
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            int i1=0;
            classes[] classess=new classes[n];
                ResultSet resultSet1=statement.executeQuery("select * from classfor"+people+peopleid);
                while (resultSet1.next()){
                    classess[i1]=new classes();
                    classess[i1].setId(resultSet1.getInt("class_No"));
                    i1++;
                }
                for (int i2=0;i2<n;i2++)
            {
                ResultSet resultSet2=statement.executeQuery("select class_name from classlist where class_id="+classess[i2].getId());
                while (resultSet2.next()){
                    classess[i2].setName(resultSet2.getString("class_name"));
                }
            }


            return classess;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public question[] checkpaperquestion(int t){
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from paper"+t);
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from paper"+t);
            while (resultSet.next()) {
                if (resultSet.getString("question_form").equals("简答题")) {
                    questions[i] = new answerquestion();
                } else if (resultSet.getString("question_form").equals("判断题")
                ) {
                    questions[i] = new judgequestion();
                } else if (resultSet.getString("question_form").equals("选择题")) {
                    choicequestion choicequestion=new choicequestion();
                    choicequestion.setChoices(null);
                    questions[i]=choicequestion;
                }
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(resultSet.getInt("question_point"));
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }
    }
    public int checkchoicequestionnumber() {
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_form = '选择题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checkanswerquestionnumber() {
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_form = '简答题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checkjudgequestionnumber() {
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_form = '判断题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checksearchedquestionnumber(String searchstring){
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checksearchedrulesnumber(String searchstring){
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from rules where rules_name REGEXP '"+searchstring+"'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checksearchedchoicequestionnumber(String searchstring){
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '选择题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checksearchedanswerquestionnumber(String searchstring){
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '简答题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checksearchedjudgequestionnumber(String searchstring){
        Statement statement = null;
        int n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '判断题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return n;
        }
    }
    public int checkpapernumber(){
        Statement statement = null;
        int  n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from paperlist");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }}catch (Exception e){e.printStackTrace();}
        finally {
            return
                    n;
        }
    }
    public String checkstudentclass(int studentid){
        String s="";
        int n=0;
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select count(1) AS  count from classforstudent"+studentid);
            while (resultSet.next()){
                n=resultSet.getInt("count");
            }
            if(n==0)
                return s;
            else {
            resultSet=statement.executeQuery("Select * from classforstudent"+studentid);
            while (resultSet.next()){
                s+=resultSet.getString("class_No");
                s+=" ";
            }
            return s;}
        }catch (Exception e){
            return s;
        }
    }
    public String checkclassname(int classid){
        String s="";
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select * from classlist where class_id="+classid);
            while (resultSet.next()){
                s=resultSet.getString("class_name");
                s+=" ";
            }
            return s;
        }catch (Exception e){
            return s;
        }
    }
    public String checkclasstask(int classid){
        String s="";
        int n=0;
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select count(1) AS  count from taskfromclass"+classid);
            while (resultSet.next()){
              n=resultSet.getInt("count");
            }
            if(n==0)
                return s;
            else {
                int[] ints=new int[n];
                n=0;
                resultSet = statement.executeQuery("select * from taskfromclass"+classid);
                while (resultSet.next()) {
                    ints[n]=resultSet.getInt("task_paperid");
                    n++;
                }
                for(int i=0;i<n;i++){
                    resultSet=statement.executeQuery("select paper_name from paperlist where paper_id="+ints[i]);
                    while (resultSet.next()){
                        s+=resultSet.getString("paper_name");
                        s+=" ";
                    }
                }
                return s;
            }
        }catch (Exception e){
return s;
        }
    }
    public String checkdonestudent(int classid ,int taskid){
        String s="";
        int n=0;
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select count(1) AS  count from task"+taskid+"fromclass"+classid+" where paper_state=2");
            while (resultSet.next()){
                n=resultSet.getInt("count");
            }
            if(n==0)
                return s;
            else {
                resultSet = statement.executeQuery("select * from task"+taskid+"fromclass"+classid+" where paper_state=2");
                while (resultSet.next()) {
                    s+=resultSet.getInt("student_id");
                    s+=" ";}
                return s;
            }
        }catch (Exception e){
            return s;
        }
    }
    public String checkcorrectedstudent(int classid ,int taskid){
        String s="";
        int n=0;
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select count(1) AS  count from task"+taskid+"fromclass"+classid+" where paper_state=3");
            while (resultSet.next()){
                n=resultSet.getInt("count");
            }
            if(n==0)
                return s;
            else {
                resultSet = statement.executeQuery("select * from task"+taskid+"fromclass"+classid+" where paper_state=3");
                while (resultSet.next()) {
                    s+=resultSet.getInt("student_id");
                    s+=" ";}
                return s;
            }
        }catch (Exception e){
            return s;
        }
    }
    public String checkclasstaskNo(int classid){
        String s="";
        int n=0;
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select count(1) AS  count from taskfromclass"+classid);
            while (resultSet.next()){
                n=resultSet.getInt("count");
            }
            if(n==0)
                return s;
            else {
                resultSet = statement.executeQuery("select * from taskfromclass"+classid);
                while (resultSet.next()) {
                    s+=resultSet.getInt("task_id");
                    s+=" ";                }
                return s;
            }
        }catch (Exception e){
            return s;
        }
    }
    public String checkstudentname(int studentid){
        try{Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from studentlist where student_id="+studentid);
            while (resultSet.next()){
                return resultSet.getString("student_name");
            }
            return "null";
        }
        catch (Exception e){
            return "null";
        }
    }
    public testpaper checktaskpaper(int classid,int taskid){
        int n=1;
        try{Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from taskfromclass"+classid+" where task_id="+taskid);
while (resultSet.next()){
    n=resultSet.getInt("task_paperid");
}
return checkpointedpaper(n);
        }
        catch (Exception e){
return null;
        }
        }
    public String checkstudenttask(int studentid){
        String s="";
        try{Statement statement=connection.createStatement();
            int n=0;
            ResultSet resultSet=statement.executeQuery("Select count(1) AS  count from classforstudent"+studentid);
            while (resultSet.next()){
                n=resultSet.getInt("count");
            }
            if(n==0)
                return s;
            else{
                int[] ints=new int[n];
                ResultSet resultSet1=statement.executeQuery("select * from classforstudent"+studentid);
                int i=0;
                while (resultSet1.next()){
                    ints[i]=resultSet1.getInt("class_No");
                    i++;
                }
                for(int i1=0;i1<n;i1++){
                resultSet1=statement.executeQuery("Select count(1) AS  count from taskfromclass"+ints[i1]);
                int n2=0;
                while (resultSet1.next()){
                    n2=resultSet1.getInt("count");
                }
                if(n2==0)
                    continue;
                else {int[] ints1=new int[n2];
                      int[] ints2=new int[n2];
                      int[] ints3=new int[n2];
                int i2=0;
                resultSet1=statement.executeQuery("select * from taskfromclass"+ints[i1]);
                while (resultSet1.next()){
                    ints1[i2]=resultSet1.getInt("task_id");
                    ints2[i2]=resultSet1.getInt("task_paperid");
                    ints3[i2]=resultSet1.getInt("task_time");
                    i2++;
                }
                for(int i3=0;i3<n2;i3++){
                    resultSet1=statement.executeQuery("Select count(1) AS  count from task"+ints1[i3] +
                            "fromclass"+ints[i1]+" where student_id ="+studentid);
                    while (resultSet1.next()){
                    if(resultSet1.getInt("count")==0){
                        s+=Integer.toString(ints2[i3]);
                        s+=" ";
                        s+="0";
                        s+=" ";
                        s+=ints3[i3];
                        s+=" ";
                        s+=ints[i1];
                        s+=" ";
                    }
                    else {
                        Statement statement1=connection.createStatement();
                        ResultSet resultSet2=statement1.executeQuery("Select * from task"+ints1[i3] +
                                "fromclass"+ints[i1]+" where student_id ="+studentid);
                        while (resultSet2.next()){
                            int temp=resultSet2.getInt("paper_state");
                            if(temp==1){
                            s+=Integer.toString(ints2[i3]);
                            s+=" ";
                            s+=resultSet2.getInt("paper_time");
                            s+=" ";
                            s+=ints3[i3];
                            s+=" ";
                            s+=ints[i1];
                            s+=" ";}
                        }
                    }
                    }
                }
                }
                }
            }

        }catch (Exception e){

        }finally {

            return s;
        }
    }
    public int checktestdonetaskno(int paper_id ,int classid){
        Statement statement = null;
        int  n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from taskfromclass"+classid+" where task_paperid="+paper_id);
            while (resultSet.next()) {
            n=resultSet.getInt("task_id");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return n;
        }}
        public choicequestionDone[] checkchoicedone(int taskid,int studentid,int classid){
            Statement statement = null;
            choicequestionDone[] choicequestionDones = null;
            int i = 0, n = 1;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from task"+taskid+"fromclass"+classid+"student"+studentid+" where question_form='选择题'");
                while (resultSet.next()) {
                    n = resultSet.getInt("count");
                }
                System.out.println(n);
                choicequestionDones = new choicequestionDone[n];
                resultSet = statement.executeQuery("select * from task"+taskid+"fromclass"+classid+"student"+studentid+" where question_form='选择题'");
                while (resultSet.next()) {
                        choicequestionDone choicequestiondone=new choicequestionDone();
                        choicequestiondone.setChoices(null);
                        choicequestionDones[i]=choicequestiondone;
                    choicequestionDones[i].setId(resultSet.getInt("question_id"));
                    choicequestionDones[i].setForm(resultSet.getString("question_form"));
                    choicequestionDones[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                    choicequestionDones[i].setPoint(resultSet.getInt("question_point"));
                    choicequestionDones[i].setScore(resultSet.getInt("question_studentpoint"));
                    choicequestionDones[i].setAnswer(resultSet.getString("question_answer"));
                    choicequestionDones[i].setStudentanswer(resultSet.getString("question_studentanswer"));
                    choicequestionDones[i].setTitle(resultSet.getString("question_title"));
                    i++;
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                return choicequestionDones;
            }
        }
    public answerquestionDone[] checkanswerdone(int taskid,int studentid,int classid){
        Statement statement = null;
        answerquestionDone[] answerquestionDones = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from task"+taskid+"fromclass"+classid+"student"+studentid+" where question_form='简答题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            answerquestionDones = new answerquestionDone[n];
            resultSet = statement.executeQuery("select * from task"+taskid+"fromclass"+classid+"student"+studentid+" where question_form='简答题'");
            while (resultSet.next()) {
                answerquestionDone answerquestionDone=new answerquestionDone();
                answerquestionDones[i]=answerquestionDone;
                answerquestionDones[i].setId(resultSet.getInt("question_id"));
                answerquestionDones[i].setForm(resultSet.getString("question_form"));
                answerquestionDones[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                answerquestionDones[i].setPoint(resultSet.getInt("question_point"));
                answerquestionDones[i].setScore(resultSet.getInt("question_studentpoint"));
                answerquestionDones[i].setAnswer(resultSet.getString("question_answer"));
                answerquestionDones[i].setStudentanswer(resultSet.getString("question_studentanswer"));
                answerquestionDones[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return answerquestionDones;
        }
    }
    public judgequestionDone[] checkjudgedone(int taskid,int studentid,int classid){
        Statement statement = null;
        judgequestionDone[] judgequestionDones = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from task"+taskid+"fromclass"+classid+"student"+studentid+" where question_form='判断题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            judgequestionDones = new judgequestionDone[n];
            resultSet = statement.executeQuery("select * from task"+taskid+"fromclass"+classid+"student"+studentid+" where question_form='判断题'");
            while (resultSet.next()) {
                judgequestionDone judgequestionDone=new judgequestionDone();
                judgequestionDones[i]=judgequestionDone;
                judgequestionDones[i].setId(resultSet.getInt("question_id"));
                judgequestionDones[i].setForm(resultSet.getString("question_form"));
                judgequestionDones[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                judgequestionDones[i].setPoint(resultSet.getInt("question_point"));
                judgequestionDones[i].setScore(resultSet.getInt("question_studentpoint"));
                judgequestionDones[i].setAnswer(resultSet.getString("question_answer"));
                judgequestionDones[i].setStudentanswer(resultSet.getString("question_studentanswer"));
                judgequestionDones[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return judgequestionDones;
        }
    }
    public String checkchoicedonechoices(question question,int taskid,int studentid,int classid){
        try{
            Statement statement1 = connection.createStatement();
            String s="";
            int t = question.getId();
            ResultSet resultSet1 = statement1.executeQuery("select * from  task"+taskid+"fromclass"+classid+"student"+studentid+"choices where choice_from='" + t + "'");
            while (resultSet1.next()) {
                s+=(resultSet1.getString("choice_content"));
                s+=" ";
            }
            return s;
        }
        catch (Exception e){return null;}
    }
    public testpaper[] checktestpapers(){
        Statement statement = null;
        testpaper[] testpapers= null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from paperlist");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            testpapers =new testpaper[n];
            resultSet = statement.executeQuery("select * from paperlist");
            while (resultSet.next()) {
                testpapers[i]=new testpaper();
                testpapers[i].setId(resultSet.getInt("paper_id"));
                testpapers[i].setName(resultSet.getString("paper_name"));
                testpapers[i].setCreater(resultSet.getString("paper_creater"));
                testpapers[i].setPaperpoint(resultSet.getInt("paper_paperpoint"));
                testpapers[i].setQuestionnumber(resultSet.getInt("paper_questionnumber"));
                testpapers[i].setChoicenumber(resultSet.getInt("paper_choicenumber"));
                testpapers[i].setAnswernumber(resultSet.getInt("paper_answernumber"));
                testpapers[i].setJudgenumber(resultSet.getInt("paper_judgenumber"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return testpapers;
        }
    }
    public rules[] checkrules() throws Exception{
        Statement statement = null;
        rules[] rules= null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from rules");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            rules= new rules[n];
            resultSet = statement.executeQuery("select * from rules");
            while (resultSet.next()) {
            rules[i]=new rules();
            rules[i].setId(resultSet.getInt("rules_id"));
            rules[i].setName(resultSet.getString("rules_name"));
            rules[i].setQuestionnumber(resultSet.getInt("rules_questionnumber"));
            rules[i].setPoint(resultSet.getInt("rules_point"));
            rules[i].setChoicenumber(resultSet.getInt("rules_choicenumber"));
            rules[i].setJudgenumber(resultSet.getInt("rules_judgenumber"));
            rules[i].setAnswernumber(resultSet.getInt("rules_answernumber"));
            i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return rules;
        }
    }
    public rules[] searchrules(String searchstring){
        Statement statement = null;
        rules[] rules= null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from rules where rules_name REGEXP '"+searchstring+"'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            rules= new rules[n];
            resultSet = statement.executeQuery("select * from rules where rules_name REGEXP '"+searchstring+"'");
            while (resultSet.next()) {
                rules[i]=new rules();
                rules[i].setId(resultSet.getInt("rules_id"));
                rules[i].setName(resultSet.getString("rules_name"));
                rules[i].setQuestionnumber(resultSet.getInt("rules_questionnumber"));
                rules[i].setPoint(resultSet.getInt("rules_point"));
                rules[i].setChoicenumber(resultSet.getInt("rules_choicenumber"));
                rules[i].setJudgenumber(resultSet.getInt("rules_judgenumber"));
                rules[i].setAnswernumber(resultSet.getInt("rules_answernumber"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return rules;
        }
    }
    @Override
    public question[] checkquestion() throws Exception {
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist");
            while (resultSet.next()) {
                if (resultSet.getString("question_form").equals("简答题")) {
                    questions[i] = new answerquestion();
                } else if (resultSet.getString("question_form").equals("判断题")
                ) {
                    questions[i] = new judgequestion();
                } else if (resultSet.getString("question_form").equals("选择题")) {
                    choicequestion choicequestion=new choicequestion();
                    choicequestion.setChoices(null);
                    questions[i]=choicequestion;
                }
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }

    }
    public question[] checkchoicequestion(){
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_form = '选择题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist where question_form = '选择题'");
            while (resultSet.next()) {
                    choicequestion choicequestion=new choicequestion();
                    choicequestion.setChoices(null);
                    questions[i]=choicequestion;
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }
    }
    public question[] checkanswerquestion(){
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_form = '简答题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist where question_form = '简答题'");
            while (resultSet.next()) {
                answerquestion answerquestion=new answerquestion();
                questions[i]=answerquestion;
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }
    }
    public question[] checkjudgequestion(){
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_form = '判断题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist where question_form = '判断题'");
            while (resultSet.next()) {
                judgequestion judgequestion=new judgequestion();
                questions[i]=judgequestion;
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }
    }
    public question[] searchquestion(String searchstring) throws Exception {
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist where question_title REGEXP '"+searchstring+"'");
            while (resultSet.next()) {
                if (resultSet.getString("question_form").equals("简答题")) {
                    questions[i] = new answerquestion();
                } else if (resultSet.getString("question_form").equals("判断题")
                ) {
                    questions[i] = new judgequestion();
                } else if (resultSet.getString("question_form").equals("选择题")) {
                    choicequestion choicequestion=new choicequestion();
                    choicequestion.setChoices(null);
                    questions[i]=choicequestion;
                }
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }

    }
    public question[] searchchoicequestion(String searchstring) throws Exception {
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '选择题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '选择题'");
            while (resultSet.next()) {
                choicequestion choicequestion=new choicequestion();
                    choicequestion.setChoices(null);
                    questions[i]=choicequestion;
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }

    }
    public question[] searchanswerquestion(String searchstring) throws Exception {
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '简答题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '简答题'");
            while (resultSet.next()) {
                answerquestion answerquestion=new answerquestion();
                questions[i]=answerquestion;
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }

    }
    public question[] searchjudgequestion(String searchstring) throws Exception {
        Statement statement = null;
        question[] questions = null;
        int i = 0, n = 1;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select count(1) AS  count from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '判断题'");
            while (resultSet.next()) {
                n = resultSet.getInt("count");
            }
            System.out.println(n);
            questions = new question[n];
            resultSet = statement.executeQuery("select * from questionlist where question_title REGEXP '"+searchstring+"' and question_form = '判断题'");
            while (resultSet.next()) {
                judgequestion judgequestion=new judgequestion();
                questions[i]=judgequestion;
                questions[i].setId(resultSet.getInt("question_id"));
                questions[i].setForm(resultSet.getString("question_form"));
                questions[i].setDifficulty(resultSet.getDouble("question_difficulty"));
                questions[i].setPoint(0);
                questions[i].setAnswer(resultSet.getString("question_answer"));
                questions[i].setTitle(resultSet.getString("question_title"));
                i++;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return questions;
        }

    }
    public String checkchoice(question question)  {
        try{
        Statement statement1 = connection.createStatement();
        String s="";
            int t = question.getId();
                    int w = 0;
                    ResultSet resultSet1 = statement1.executeQuery("select * from choices where choice_from='" + t + "'");
                    while (resultSet1.next()) {
                        s+=(resultSet1.getString("choice_content"));
                        s+=" ";
                        }
                    return s;
                    }
        catch (Exception e){return null;}
    }
    public String checkpaperquestionchoice(int q,question question){
        try{
            Statement statement1 = connection.createStatement();
            String s="";
            int t = question.getId();
            ResultSet resultSet1 = statement1.executeQuery("select * from choices"+q+" where choice_from='" + t + "'");
            while (resultSet1.next()) {
                s+=(resultSet1.getString("choice_content"));
                s+=" ";
            }
            return s;
        }
        catch (Exception e){return null;}
    }
    public int publishtask(int classNo,int paperid,int time){

        int result = 0;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("insert into taskfromclass"+classNo+"(task_paperid,task_time)" +
                    " values(?,?)");
            preparedStatement.setInt(1,paperid);
            preparedStatement.setInt(2,time);
            preparedStatement.executeUpdate();
            Statement statement=connection.createStatement();
            int n=1;
            ResultSet resultSet = statement.executeQuery("select * from taskfromclass"+classNo);
            while (resultSet.next()){
                n=resultSet.getInt("task_id");
            }
            statement.executeUpdate("create table task"+n+"fromclass"+classNo+"(" +
                    "student_No int not null auto_increment," +
                    "student_id int," +
                    "student_name varchar(50)," +
                    "paper_point int," +
                    "paper_time int," +
                    "paper_state int," +
                    "primary key (student_No)" +
                    ")engine=innodb default charset=utf8");
            return  0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return result;
        }
    }
    public int addteacherintoclass(String teachername,int teacherid){
        int result = 0;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select class_id from classlist");
            int i = 0;
            while (resultSet.next()) {
                i = resultSet.getInt("class_id");}
            PreparedStatement preparedStatement=connection.prepareStatement("insert into class"+i+"(people_name,people_id,people_form)" +
                    " values(?,?,?)");
            preparedStatement.setString(1,teachername);
            preparedStatement.setInt(2,teacherid);
            preparedStatement.setString(3,"teacher");
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1=connection.prepareStatement("insert into classforteacher"+teacherid+"(class_No)" +
                    " values(?)");
            preparedStatement1.setInt(1,i);
            preparedStatement1.executeUpdate();
            return  0;
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return result;
        }
    }
    public int addstudentintoclass(String classname,String password,String studentname,int studentid){
        try{Statement statement=connection.createStatement();
            ResultSet resultSet =statement.executeQuery("Select count(1) AS  count from classlist where class_name = '"+classname+"'");
            while(resultSet.next()){
                if(resultSet.getInt("count")!=1)
                return 1;
            }
            ResultSet resultSet1=statement.executeQuery("select * from classlist where class_name = '"+classname+"'");
            while (resultSet1.next()){
            if(resultSet1.getString("class_password").equals(password)) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into class" + resultSet1.getInt("class_id") + "(people_name,people_id,people_form)" +
                        " values(?,?,?)");
                preparedStatement.setString(1, studentname);
                preparedStatement.setInt(2, studentid);
                preparedStatement.setString(3, "student");
                preparedStatement.executeUpdate();
                PreparedStatement preparedStatement1=connection.prepareStatement("insert into classforstudent"+studentid+"(class_No)" +
                        " values(?)");
                preparedStatement1.setInt(1,resultSet1.getInt("class_id"));
                preparedStatement1.executeUpdate();
                return 0;}
            }
            return 1;
    }catch (Exception e){e.printStackTrace();
        return 1;}
    }
    public int addclass(){
        int result = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("insert into classlist(class_name,class_password)" +
                    " values(?,?)");
            preparedStatement.setString(1, classes.getName());
            preparedStatement.setString(2, classes.getPassword());
            preparedStatement.executeUpdate();
            Statement statement=connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select class_id from classlist");
            int i = 0;
            while (resultSet.next()) {
                i = resultSet.getInt("class_id");}
            Statement statement1=connection.createStatement();
            statement1.executeUpdate("create table class"+i+"(" +
                    "people_classid int not null auto_increment," +
                    "people_name varchar(40)," +
                    "people_id int," +
                    "people_form varchar(40)," +
                    "primary key (people_classid)" +
                    ")engine=innodb default charset=utf8;");
            statement1.executeUpdate("create table taskfromclass"+i+"(" +
                    "task_id int not null auto_increment," +
                    "task_paperid int," +
                    "task_time int,"+
                    "primary key (task_id)" +
                    ")engine=innodb default charset=utf8;");
        } catch (Exception exception) {
            exception.printStackTrace();
            result=1;
        } finally {
            return result;
        }

    }
    public int addchoices(){
        int result = 0;
        try{
        PreparedStatement preparedStatement;
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select question_id from questionlist");
                int i = 0;
                while (resultSet.next()) {
                    i = resultSet.getInt("question_id");}
                    preparedStatement = connection.prepareStatement("insert into choices(choice_content,choice_from)" + "values(?,?)");
                    preparedStatement.setString(1, choice.getContent());
                    preparedStatement.setInt(2, i);
                    result = preparedStatement.executeUpdate();
        }catch (Exception e){}finally {
            return result;
        }
    }
    @Override
    public int addquestion() {
        int result = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("insert into questionlist(question_form,question_difficulty,question_point,question_answer,question_title)" +
                    " values(?,?,?,?,?)");
            preparedStatement.setString(1, question.getForm());
            preparedStatement.setDouble(2, question.getDifficulty());
            preparedStatement.setInt(3, question.getPoint());
            preparedStatement.setString(4, question.getAnswer());
            preparedStatement.setString(5, question.getTitle());
            result = preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return result;
        }

    }

    @Override
    public int addrules() {
        int result = 0;
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement("insert into rules(rules_name,rules_questionnumber,rules_point ,rules_choicenumber,rules_judgenumber,rules_answernumber)" +
                    " values(?,?,?,?,?,?)");
            preparedStatement.setString(1, rules.getName());
            preparedStatement.setInt(2, rules.getQuestionnumber());
            preparedStatement.setInt(3, rules.getPoint());
            preparedStatement.setInt(4, rules.getChoicenumber());
            preparedStatement.setInt(5, rules.getJudgenumber());
            preparedStatement.setInt(6, rules.getAnswernumber());
            result = preparedStatement.executeUpdate();


        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return result;
        }
    }
    public String checktestdoneforstudent(int studentid){
        String s="";
        int n=0;
        try{
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("Select count(1) AS  count from testdoneforstudent"+studentid);
            while (resultSet.next()){
                n=resultSet.getInt("count");
            }
            if(n==0)
                return s;
            else {
                resultSet=statement.executeQuery("Select * from testdoneforstudent"+studentid);
                while (resultSet.next()){
                    s+=resultSet.getString("paper_name");
                    s+=" ";
                    s+=resultSet.getInt("paper_paperpoint");
                    s+=" ";
                    s+=resultSet.getInt("paper_score");
                    s+=" ";
                }
                return s;}
        }catch (Exception e){
            return s;
        }}

    public int addtestdonetostudent(int studentid, String papername, int paperpoint, int score) {
        try {
            Statement statement1 = connection.createStatement();
            statement1.executeUpdate("insert into testdoneforstudent"+studentid+"(" +
                    "paper_name,paper_paperpoint,paper_score) values(\""+papername+"\","+paperpoint+","+score+")");

            return 0;
        } catch (
                Exception exception) {
            exception.printStackTrace();
           return  1;
        }
    }

    public int addtestdone(int score,int testpaperid,int studentno,String studentname,int time,int classNo,int state){
        int result = 0,taskno=1;
        boolean ifhasdone=false;
        PreparedStatement preparedStatement;
        try {

            Statement statement1= connection.createStatement();
            ResultSet resultSet=statement1.executeQuery("select * from taskfromclass"+classNo+" where task_paperid = "+testpaperid);
            while (resultSet.next()){
                taskno=resultSet.getInt("task_id");
            }
            Statement statement=connection.createStatement();
            ResultSet resultSet1=statement.executeQuery("Select count(1) AS  count from task"+taskno+"fromclass"+classNo+" where student_id="+studentno);
            while (resultSet1.next()){
                if(resultSet1.getInt("count")!=0){
                    Statement  statement2=connection.createStatement();
                    statement2.executeUpdate("update task"+taskno+"fromclass"+classNo+" set paper_time="+time+", paper_state="+state+", paper_point="+score+" where student_id="+studentno);
                    statement2.executeUpdate("drop table task"+taskno+"fromclass"+classNo+"student"+ studentno);
                    statement2.executeUpdate("drop table task"+taskno+"fromclass"+classNo+"student"+ studentno+"choices");

                }else{
                    statement1.executeUpdate("insert into task"+taskno+"fromclass"+classNo+"(student_id,student_name,paper_point," +
                            "paper_time,paper_state)" +
                            " values("+studentno+",\""+studentname+"\",0,"+time+","+state+")");
                }
            }
            statement1.executeUpdate("create table task"+taskno+"fromclass"+classNo+"student"+ studentno+
                    "(question_id int not null auto_increment," +
                    "question_form varchar(20) not null,"+
                    "question_difficulty double," +
                    "question_point int not null," +
                    "question_studentpoint int," +
                    "question_answer varchar(400)," +
                    "question_studentanswer varchar(400)," +
                    "question_title varchar(400) not null," +
                    "primary key (question_id)" +
                    ")engine = innodb default charset = utf8;");
            statement1.executeUpdate("create table task"+taskno+"fromclass"+classNo+"student"+ studentno+"choices"+
                    "(choice_id int not null auto_increment," +
                    "choice_content varchar(200)," +
                    "choice_from int not null," +
                    "primary key (choice_id)" +
                    ")engine=innodb default charset=utf8;");
        } catch (Exception exception) {

        } finally {
            return taskno;
        }

    }
    public int addtestdonequestion(int score,int taskno,int studentno,int classNo,String studentanswer){

        try {
            Statement statement=connection.createStatement();
            statement.executeUpdate("insert into task"+taskno+"fromclass"+classNo+"student"+ studentno+"(question_form,question_difficulty,question_point,question_studentpoint,question_answer,question_studentanswer,question_title)" +
                    " values(\""+question.getForm()+"\","+question.getDifficulty()+","+question.getPoint()+","+score+",\""+question.getAnswer()+"\",\""+studentanswer+"\",\""+question.getTitle()+"\")");
            return 0;
        } catch (Exception exception) {
            exception.printStackTrace();
            return 1;
        }
    }
    public int addtestdonechoice(int taskno,int studentno,int classNo){
        int result=0;
        int n=0;
        try{
            PreparedStatement preparedStatement;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select question_id from task"+taskno+"fromclass"+classNo+"student"+ studentno);
            int i = 0;
            while (resultSet.next()) {
                i = resultSet.getInt("question_id");}
            preparedStatement = connection.prepareStatement("insert into task"+taskno+"fromclass"+classNo+"student"+ studentno+"choices"+"(choice_content,choice_from)" + "values(?,?)");
            preparedStatement.setString(1, choice.getContent());
            preparedStatement.setInt(2, i);
            result = preparedStatement.executeUpdate();
        }catch (Exception e){}finally {
            return result;
        }
    }

    @Override
    public int addtestpaper() {
        int result = 0,result1=0,n=1;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("insert into paperlist(paper_name,paper_creater,paper_paperpoint,paper_questionnumber,paper_choicenumber,paper_answernumber,paper_judgenumber)" +
                    " values(?,?,?,?,?,?,?)");
            preparedStatement.setString(1,testpaper.getName());
            preparedStatement.setString(2,testpaper.getCreater());
            preparedStatement.setInt(3, testpaper.getPaperpoint());
            preparedStatement.setInt(4, testpaper.getQuestionnumber());
            preparedStatement.setInt(5, testpaper.getChoicenumber());
            preparedStatement.setInt(6, testpaper.getAnswernumber());
            preparedStatement.setInt(7, testpaper.getJudgenumber());
            result = preparedStatement.executeUpdate();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select * from paperlist");
            while(resultSet.next()){
                n=resultSet.getInt("paper_id");
            }
            resultSet.close();
            statement.close();
            Statement statement1= connection.createStatement();
            statement1.executeUpdate("create table paper"+n +
                    "(question_id int not null auto_increment," +
                    "question_form varchar(20) not null,"+
                    "question_difficulty double," +
                    "question_point int not null," +
                    "question_answer varchar(400)," +
                    "question_title varchar(400) not null," +
                    "primary key (question_id)" +
                    ")engine=innodb default charset=utf8;");
            statement1.executeUpdate("create table choices"+n+
                    "(choice_id int not null auto_increment," +
                    "choice_content varchar(200)," +
                    "choice_from int not null," +
                    "primary key (choice_id)" +
                    ")engine=innodb default charset=utf8;");

        } catch (Exception exception) {

        } finally {

        }
        return result;
    }
    public int addtestquestion(){
        int result=0;
        int n=0;
        PreparedStatement preparedStatement1;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from paperlist");
            while(resultSet.next()){
                n=resultSet.getInt("paper_id");
            }
            preparedStatement1 = connection.prepareStatement("insert into paper"+n+"(question_form,question_difficulty,question_point,question_answer,question_title)" +
                        " values(?,?,?,?,?)");
            preparedStatement1.setString(1, question.getForm());
            preparedStatement1.setDouble(2, question.getDifficulty());
            preparedStatement1.setInt(3, question.getPoint());
            preparedStatement1.setString(4, question.getAnswer());
            preparedStatement1.setString(5, question.getTitle());
            result += preparedStatement1.executeUpdate();
            } catch (Exception exception) {
                exception.printStackTrace();
            } finally {
                return result;
            }
    }
    public int addtestchoice(){
        int result=0;
        int n=0;
        try{
            Statement statement1=connection.createStatement();
            ResultSet resultSet1=statement1.executeQuery("select * from paperlist");
            while(resultSet1.next()){
                n=resultSet1.getInt("paper_id");
            }
            PreparedStatement preparedStatement;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select question_id from paper"+n);
            int i = 0;
            while (resultSet.next()) {
                i = resultSet.getInt("question_id");}
            preparedStatement = connection.prepareStatement("insert into choices"+n+"(choice_content,choice_from)" + "values(?,?)");
            preparedStatement.setString(1, choice.getContent());
            preparedStatement.setInt(2, i);
            result = preparedStatement.executeUpdate();
        }catch (Exception e){}finally {
            return result;
        }
    }

    @Override
    public int delquestion() {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            if (question.getForm().equals("选择题")) {
                int i = question.getId();
                PreparedStatement preparedStatement1 = connection.prepareStatement("delete from choices where choice_From=" + question.getId());
                result = preparedStatement1.executeUpdate();

            }
            PreparedStatement preparedStatement2 = connection.prepareStatement("delete from questionlist where question_id=" + question.getId());
            result = preparedStatement2.executeUpdate();

        } catch (Exception exception) {

        } finally {

        }
        return result;
    }

    @Override
    public int delrules() {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            PreparedStatement preparedStatement2 = connection.prepareStatement("delete from rules where rules_id=" + rules.getId());
            result = preparedStatement2.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return result;
        }

    }

    @Override
    public int deltestpaper() {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {

        } catch (Exception exception) {

        } finally {

        }
        return result;
    }

    @Override
    public int changequestion() {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {
            if (question.getForm().equals("选择题")) {
                choicequestion choicequestion = (entity.choicequestion) question;
                choice[] choices = choicequestion.getChoices();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select question_id from questionlist");
                int i = question.getId();
                PreparedStatement preparedStatement1 = connection.prepareStatement("delete from choices where choice_From=" + question.getId());
                result = preparedStatement1.executeUpdate();
                for (int j = 0; j < choices.length; j++) {
                    preparedStatement = connection.prepareStatement("insert into choices(choice_content,choice_from)" + "values(?,?)");
                    preparedStatement.setString(1, choices[j].getContent());
                    preparedStatement.setInt(2, i);
                    result = preparedStatement.executeUpdate();
                }

            }
            preparedStatement = connection.prepareStatement("update questionlist set question_difficulty='" + question.getDifficulty() + "' where question_id=" + question.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("update questionlist set question_answer='" + question.getAnswer() + "' where question_id=" + question.getId());
            result = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement("update questionlist set question_title='" + question.getTitle() + "' where question_id=" + question.getId());
            result = preparedStatement.executeUpdate();

        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            return result;
        }

    }

    @Override
    public int changetestpaper() {
        int result = 0;
        PreparedStatement preparedStatement = null;
        try {

        } catch (Exception exception) {

        } finally {

        }
        return result;
    }


}
