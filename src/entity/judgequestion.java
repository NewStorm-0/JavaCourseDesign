package entity;

public class judgequestion extends question implements java.io.Serializable{
    @Override
    public String toString() {
        return null;
    }
    public judgequestion(){
        super();
    }
    public judgequestion (double difficulty,String answer,String title){
        super(difficulty,answer,title);
        setForm("判断题");
    }
}
