package dao;
import java.awt.*;
import java.sql.*;
import entity.*;
public class accountdaoimpl implements accountdao{
    private Connection connection;

    public accountdaoimpl(){};
    public accountdaoimpl(Connection connection){
        this.connection=connection;}
    @Override
    public int studentregister(students students) throws Exception{
    int result = 0;
    PreparedStatement preparedStatement=null;
        try {
    preparedStatement=connection.prepareStatement("insert into studentlist(student_username,student_password,student_name) values(?,?,?)");
preparedStatement.setString(1,students.getUsername());
preparedStatement.setString(2,students.getPassword());
preparedStatement.setString(3,students.getName());
result=preparedStatement.executeUpdate();
Statement statement=connection.createStatement();
int n=0;
ResultSet resultSet=statement.executeQuery("Select student_id from studentlist");
    while(resultSet.next()){
        n=resultSet.getInt("student_id");
    }
statement.executeUpdate("create table classforstudent"+n +
        "(class_id int not null auto_increment,class_No int,primary key (class_id))Engine=innodb default charset =utf8;"
);
    statement.executeUpdate("create table testdoneforstudent"+n +
                    "(paper_id int not null auto_increment," +
                    "paper_name varchar(200) not null," +
                    "paper_paperpoint int," +
                    "paper_score int," +
                    "primary key(paper_id))" +
                    "Engine=innodb default charset=utf8;"
            );
        }catch (Exception exception){exception.printStackTrace();}
        return result;
    }

    public int teacherregister(teacher  teacher) throws Exception{
        int result = 0;
        PreparedStatement preparedStatement=null;
        try {
            preparedStatement=connection.prepareStatement("insert into teacherlist(teacher_username,teacher_password,teacher_name) values(?,?,?)");
            preparedStatement.setString(1,teacher.getUsername());
            preparedStatement.setString(2,teacher.getPassword());
            preparedStatement.setString(3,teacher.getName());
            result=preparedStatement.executeUpdate();
            Statement statement=connection.createStatement();
            int n=0;
            ResultSet resultSet=statement.executeQuery("Select teacher_id from teacherlist");
            while(resultSet.next()){
                n=resultSet.getInt("teacher_id");
            }
            statement.executeUpdate("create table classforteacher"+n +
                    "(class_id int not null auto_increment,class_No int,primary key (class_id))Engine=innodb default charset =utf8;"
            );
        }catch (Exception exception){exception.printStackTrace();}
        return result;
    }


    @Override
    public students studentsign(students students){
        Statement statement=null;
        String stu="'"+students.getUsername()+"'";
        String pass=null;
        try{statement=connection.createStatement();
            ResultSet resultSet =statement.executeQuery("Select count(1) AS  count from studentlist where student_username = "+stu);
            while(resultSet.next()){
                System.out.println(1);
                if(resultSet.getInt("count")!=1)
                {students = null;
                    System.out.println("null");
                    return null;}
            }
            ResultSet resultSet1=statement.executeQuery("select student_password from studentlist where student_username = "+stu);
            while (resultSet1.next())
                pass=resultSet1.getString("student_password");
            System.out.println(pass);
            if (pass.equals(students.getPassword())){
                String string1="select * from studentlist where student_username = "+stu;
                ResultSet resultSet2=statement.executeQuery(string1);
                while (resultSet2.next()){
                    students.setId(resultSet2.getInt("student_id"));
                    students.setName(resultSet2.getString("student_name"));
                }
            }else
                students=null;


        }catch (Exception exception){}
        finally {
            return students;
        }
    }

    public teacher teachersign(teacher teacher) {
        Statement statement=null;
        String tea="'"+teacher.getUsername()+"'";
        String pass=null;
        try{statement=connection.createStatement();
            ResultSet resultSet =statement.executeQuery("Select count(1) AS  count from teacherlist where teacher_username = "+tea);
            while(resultSet.next()){
                System.out.println(1);
                if(resultSet.getInt("count")!=1)
                {teacher = null;
                System.out.println("null");
                return null;}
            }
            ResultSet resultSet1=statement.executeQuery("select teacher_password from teacherlist where teacher_username = "+tea);
            while (resultSet1.next())
                pass=resultSet1.getString("teacher_password");
            System.out.println(pass);
            if (pass.equals(teacher.getPassword())){
                String string1="select * from teacherlist where teacher_username = "+tea;
                ResultSet resultSet2=statement.executeQuery(string1);
                while (resultSet2.next()){
                    teacher.setId(resultSet2.getInt("teacher_id"));
                    teacher.setName(resultSet2.getString("teacher_name"));
                }
            }else
            teacher=null;


        }catch (Exception exception){}
        finally {
            return teacher;
        }
    }

    @Override
    public int studentchangeaccount(students students) {
        return 0;
    }

    @Override
    public int teacherchangeaccount(teacher teacher) throws Exception{
        int result=0;
        PreparedStatement preparedStatement=null;
        String tea="'"+teacher.getUsername()+"'";
        String string="update teacherlist set teacher_password="+teacher.getPassword()+" where teacher_username = "+tea;
        try {
preparedStatement=connection.prepareStatement(string);
result=preparedStatement.executeUpdate();
        }catch (Exception exception){}
        finally {
            return result;
        }
    }

    @Override
    public int paststudy() {
        return 0;
    }

    @Override
    public int pastcorrect() {
        return 0;
    }
}
