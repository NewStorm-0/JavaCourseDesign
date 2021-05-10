package entity;

public class teacher implements java.io.Serializable{
    private String username;
    private String password;
    private String name;
    private testDoneCorrected[] testDoneCorrecteds;
    private int id;

    public teacher() {

    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int sign(String password){
        if (this.password==password)
        {return 0;}
        else return 1;
    }

    public testDoneCorrected[] getTestDoneCorrecteds() {
        return testDoneCorrecteds;
    }

    public void setTestDoneCorrecteds(testDoneCorrected[] testDoneCorrecteds) {
        this.testDoneCorrecteds = testDoneCorrecteds;
    }
    public teacher(String username,String password,String name){
        this.username=username;
        this.password=password;
        this.name=name;
    }
    public teacher(String username,String password){
        this.username=username;
        this.password=password;
        this.name=null;
    }

}
