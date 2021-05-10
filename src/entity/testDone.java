package entity;

public class testDone extends testpaper implements java.io.Serializable{
    private answerquestionDone[] answerquestionDones;
    private choicequestionDone[] choicequestionDones;
    private judgequestionDone[] judgequestionDones;
    private String student;
    private int point=0;
    public answerquestionDone[] getAnswerquestionDones() {
        return answerquestionDones;
    }

    public choicequestionDone[] getChoicequestionDones() {
        return choicequestionDones;
    }

    public judgequestionDone[] getJudgequestionDones() {
        return judgequestionDones;
    }

    public int getPoint() {
        return point;
    }

    public void setAnswerquestionDones(answerquestionDone[] answerquestionDones) {
        this.answerquestionDones = answerquestionDones;
    }

    public void setChoicequestionDones(choicequestionDone[] choicequestionDones) {
        this.choicequestionDones = choicequestionDones;
    }

    public void setJudgequestionDones(judgequestionDone[] judgequestionDones) {
        this.judgequestionDones = judgequestionDones;
    }
    public void setPoint(int point) {
        this.point = point;
    }


}
