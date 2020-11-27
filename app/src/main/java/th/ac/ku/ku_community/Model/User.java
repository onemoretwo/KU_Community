package th.ac.ku.ku_community.Model;

public class User {
    private String id;
    private String name;
    private String email;
    private String password;
    private String faculty;
    private String major;
    private String img;
    private String created_at;

    public User(){}

    public User(String id, String name, String email, String password, String faculty, String major, String img, String created_at) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.faculty = faculty;
        this.major = major;
        this.img = img;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getMajor() {
        return major;
    }

    public String getImg() {
        return img;
    }

    public String getCreated_at() {
        return created_at;
    }
}
