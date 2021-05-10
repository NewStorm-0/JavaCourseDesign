package entity;

public class answerquestion extends question implements java.io.Serializable{

    @Override
    public String toString() {
        return null;
    }
    public answerquestion(){
        super();
    }
    public answerquestion(double difficulty,String answer,String title){
        super(difficulty,answer,title);
        setForm("简答题");
    }

}
