import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    ArrayList<Media> watchlist

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return this.username + ", " + this.password;
    }
}
