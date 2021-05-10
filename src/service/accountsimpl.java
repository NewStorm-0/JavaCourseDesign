package service;
import dao.*;
import entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class accountsimpl implements accounts{
    private Connection connection;
    public accountsimpl() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb_pro?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", accounts.password);
 this.connection=connection;   }

    @Override
    public int studentregister(students students) throws Exception {
        accountdaoimpl accountdaoimpl=new accountdaoimpl(connection);
        accountdaoimpl.studentregister(students);
        return 0;
    }

    @Override
    public int teacherregister(teacher teacher) throws Exception {
        accountdaoimpl accountdaoimpl=new accountdaoimpl(connection);
        return accountdaoimpl.teacherregister(teacher);
    }

    @Override
    public students studentsign(students students){
        accountdaoimpl accountdaoimpl=new accountdaoimpl(connection);
        return accountdaoimpl.studentsign(students);
    }

    @Override
    public teacher teachersign(teacher teacher){
        accountdaoimpl accountdaoimpl=new accountdaoimpl(connection);
        return accountdaoimpl.teachersign(teacher);
    }

    @Override
    public int studentchangeaccount(students students) {
        return 0;
    }

    @Override
    public int teacherchangeaccount(teacher teacher) throws Exception {
        accountdaoimpl accountdaoimpl=new accountdaoimpl(connection);
        return accountdaoimpl.teacherchangeaccount(teacher);
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
