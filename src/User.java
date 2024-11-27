import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    ArrayList<Media> watchlist;
    ArrayList<Media> prevWatched;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    User(String username, String password) {
        this.username = username;
        this.password = password;
        this.watchlist = new ArrayList<>();
        this.prevWatched = new ArrayList<>();
    }

    @Override
    public String toString() {
        return this.username + ", " + this.password;
    }


    public void addToWatchlist(Media media) {
        if(!watchlist.contains(media)) {
            watchlist.add(media);
            ui.displayMsg("Movie/Series added to your watchlist: " + media.getMediaTitle());
            io.saveMediaList(this.username, this.watchlist, "watchlist"); // gemmer så mediet i users unikke watchlistfil
        } else {
            ui.displayMsg("This media is already in the watchlist");
        }
    }

    public void addToPrevWatchedList(Media media) {
        prevWatched.add(media);
        io.saveMediaList(this.username, this.prevWatched, "prevWatchedlist");
    }

    public String getUsername() {
        return username;
    }

    public ArrayList<Media> getWatchlist() {
        return watchlist;
    }

    public ArrayList<Media> getPrevWatched() {
        return prevWatched;
    }
}
