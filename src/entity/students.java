package entity;

public class students implements java.io.Serializable{
    private String username;
    private String password;
    private String name;
    private testDone[] testDones;
    private int classNo;
    private int id;
    public int sign(String password){
        if(this.password==password)
        {return 0;}
        else return 1;
    }
    public void Trychangepassword(String password){
        if (this.password.equals(password));
        Changepassword(password);
    }
    private void Changepassword(String password1){
        this.password=password1;
    }
    public testDone[] pasttest(testDone[] testDones){
        return testDones;
    }
    public  students(){name=null;};
    public students(String username,String password,String name){
        this.username=username;
        this.password=password;
        this.name=name;
        testDones=null;
        classNo=0;

    }
    public students(String username,String password){
        this.username=username;
        this.password=password;
        this.name=null;
        testDones=null;
        classNo=0;

    }
    public int changeclass(){return 1;};

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getClassNo() {
        return classNo;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
}
