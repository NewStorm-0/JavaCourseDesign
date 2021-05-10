package servlet;
import entity.*;

public interface changedb {
    int addquestion();
    int changequestion();
    int delquestion();
    question[] checkquestion();
}
