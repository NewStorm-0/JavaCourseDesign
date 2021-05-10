package entity;
import java.io.*;
public class choicequestion extends question implements java.io.Serializable{
    private choice[] choices;
    @Override
    public String toString() {
        return null;
    }
    public choicequestion(){
        super();
    }
    public choicequestion (double difficulty,String answer,String title,choice[] choices){
        super(difficulty,answer,title);
        this.choices=choices;
        setForm("选择题");
    }

    public choice[] getChoices() {
        return choices;
    }

    public void setChoices(choice[] choices) {
        this.choices = choices;
    }
}
