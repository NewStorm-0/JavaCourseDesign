package service;
import dao.*;
import entity.*;
import java.sql.Connection;

public interface checkDB {
    question checkquestion(int i) throws Exception;
}
