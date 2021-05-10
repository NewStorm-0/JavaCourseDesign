package entity;

public class testpaper implements java.io.Serializable{
    private int id;
    private String name;
    private String creater;
    private int paperpoint;
    private int choicenumber;
    private int judgenumber;
    private int answernumber;
    private  int questionnumber;
    private question[] questionlist;
    private int temp=0;
    public testpaper(){this.id=0;}
    public testpaper(String name,String creater,int paperpoint,int questionnumber,int choicenumber,int answernumber,int judgenumber,question[] questionlist){
        super();
        this.name=name;
        this.creater=creater;
        this.paperpoint=paperpoint;
        this.questionnumber=questionnumber;
        this.choicenumber=choicenumber;
        this.answernumber=answernumber;
        this.judgenumber=judgenumber;
        this.questionlist=questionlist;
        this.id=0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setJudgenumber(int judgenumber) {
        this.judgenumber = judgenumber;
    }

    public void setChoicenumber(int choicenumber) {
        this.choicenumber = choicenumber;
    }

    public void setAnswernumber(int answernumber) {
        this.answernumber = answernumber;
    }

    public void setQuestionnumber(int questionnumber) {
        this.questionnumber = questionnumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public void setPaperpoint(int paperpoint) {
        this.paperpoint = paperpoint;
    }

    public void setQuestionlist(question[] questionlist) {
        this.questionlist = questionlist;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreater() {
        return creater;
    }

    public int getQuestionnumber() {
        return questionnumber;
    }

    public int getJudgenumber() {
        return judgenumber;
    }

    public int getChoicenumber() {
        return choicenumber;
    }

    public int getAnswernumber() {
        return answernumber;
    }

    public int getPaperpoint() {
        return paperpoint;
    }

    public question[] getQuestionlist() {
        return questionlist;
    }
}
