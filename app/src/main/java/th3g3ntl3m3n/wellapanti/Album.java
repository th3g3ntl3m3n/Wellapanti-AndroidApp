package th3g3ntl3m3n.wellapanti;

/**
 * Created by th3g3ntl3m3n on 8/9/17.
 */

public class Album {
    private String title;
    private int userId, id;

    public Album(String title, int userId, int id) {
        this.title = title;
        this.userId = userId;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
