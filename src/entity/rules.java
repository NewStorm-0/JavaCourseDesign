package entity;

public class rules implements java.io.Serializable{
    private int id;
    private String name;
    private int questionnumber;
    private int point;
    private int answernumber;
    private int choicenumber;
    private int judgenumber;
    ;
    public rules(){}
    public rules(String name,int questionnumber,int point,int answernumber,int choicenumber,int judgenumber){
        this.name=name;
        this.questionnumber=questionnumber;
        this.point=point;
        this.answernumber=answernumber;
        this.choicenumber=choicenumber;
        this.judgenumber=judgenumber;
    }

    public int getId() { return id; }

    public int getAnswernumber() {
        return answernumber;
    }

    public int getChoicenumber() {
        return choicenumber;
    }

    public int getJudgenumber() {
        return judgenumber;
    }

    public int getQuestionnumber() {
        return questionnumber;
    }

    public int getPoint() {
        return point;
    }

    public String getName(){
        return name;
    }

    public void setId(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setQuestionnumber(int questionnumber) {
        this.questionnumber = questionnumber;
    }

    public void setAnswernumber(int answernumber) {
        this.answernumber = answernumber;
    }

    public void setChoicenumber(int choicenumber) {
        this.choicenumber = choicenumber;
    }

    public void setJudgenumber(int judgenumber) {
        this.judgenumber = judgenumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        rules rules = (rules) o;
        return questionnumber == rules.questionnumber &&
                point == rules.point &&
                answernumber == rules.answernumber &&
                choicenumber == rules.choicenumber &&
                judgenumber == rules.judgenumber ;
    }

}
