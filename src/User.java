import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    ArrayList<Media> watchlist;
    TextUI ui = new TextUI;

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
        watchlist.add(media);
    }

    public void displayWatchlist() {
        if(watchlist.isEmpty()) {
            ui.displayMsg("Your watchlist is empty");
        } else {
            ui.displayMsg("Your watchlist:");
            for(Media media : watchlist) {
                ui.displayMsg(media.getMediaTitle());
            }
        }
    }
}
