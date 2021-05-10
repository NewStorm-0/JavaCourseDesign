package entity;

import java.util.Objects;

public abstract class question implements java.io.Serializable{
    private int id;
    private String form;
    private double difficulty;
    private int point;
    private String answer;
    private String title;
    abstract public String toString();

    public void setForm(String form) {
        this.form = form;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public int getPoint() {
        return point;
    }

    public String getAnswer() {
        return answer;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public question(){}
    public question(double difficulty,String answer,String title){
        super();
        this.difficulty=difficulty;
        this.answer=answer;
        this.title=title;
        this.point=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        question question = (question) o;
        return difficulty == question.difficulty &&
                point == question.point &&
                Objects.equals(form, question.form) &&
                Objects.equals(answer, question.answer) &&
                Objects.equals(title, question.title);
    }

}
