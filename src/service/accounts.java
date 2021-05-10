package service;
import dao.*;
import entity.*;
import java.sql.Connection;

public interface accounts {
     String password = "Aa20110728";
     int studentregister(students students) throws Exception;
     int teacherregister(teacher teacher) throws Exception;
     students studentsign(students students) throws Exception;
     teacher teachersign(teacher teacher) throws Exception;
     int studentchangeaccount(students students);
     int teacherchangeaccount(teacher teacher) throws Exception;
     int paststudy();
     int pastcorrect();
}
