package entity;

public class choicequestionDone extends choicequestion implements java.io.Serializable{
    private int score;
    private String studentanswer;
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getStudentanswer() {
        return studentanswer;
    }

    public void setStudentanswer(String studentanswer) {
        this.studentanswer = studentanswer;
    }
}
