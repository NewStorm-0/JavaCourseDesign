package dao;
import java.sql.*;
import entity.*;
public interface accountdao {
    int studentregister(students students) throws Exception;
    int teacherregister(teacher teacher) throws Exception;
    students studentsign(students students) throws Exception;
    teacher teachersign(teacher teacher);
    int studentchangeaccount(students students);
    int teacherchangeaccount(teacher teacher) throws Exception;
    int paststudy();
    int pastcorrect();
}
