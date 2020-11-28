package th.ac.ku.ku_community.Activity.Model;

public class Post {
//    private String id;
    private String detail;
    private String img;
    private String created_at;

    public Post(){}

    public Post(String detail, String img, String created_at) {
        this.detail = detail;
        this.img = img;
        this.created_at = created_at;
    }

    public String getDetail() {
        return detail;
    }

    public String getImg() {
        return img;
    }

    public String getCreated_at() {
        return created_at;
    }
}
