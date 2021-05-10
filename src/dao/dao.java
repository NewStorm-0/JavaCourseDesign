package dao;
import entity.*;
import java.sql.*;
public interface dao {
    int addquestion();
    int addrules();
    int addtestpaper();
    int delquestion();
    int delrules();
    int deltestpaper();
    int changequestion();
    int changetestpaper();
    int correcting();
    question[] checkquestion() throws Exception;
}
