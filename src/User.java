import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    ArrayList<Media> watchlist;
    TextUI ui = new TextUI();

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.watchlist = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.username + ", " + this.password;
    }


    public void addToWatchlist(Media media) {
        if(!watchlist.contains(media)) {
            watchlist.add(media);
        } else {
            ui.displayMsg("This media is already in the watchlist");
        }
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Media> getWatchlist() {
        return watchlist;
    }
}
