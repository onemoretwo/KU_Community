package th.ac.ku.ku_community.Activity.Model;

public class Comment {
//    private String id;
    private String detail;
    private String created_at;

    public Comment() {
    }

    public Comment(String detail, String created_at) {
        this.detail = detail;
        this.created_at = created_at;
    }

    public String getDetail() {
        return detail;
    }

    public String getCreated_at() {
        return created_at;
    }
}
