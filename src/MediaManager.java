import java.util.ArrayList;

public class MediaManager {
    private TextUI ui;
    private FileIO io;
    private User currentUser;
    private ArrayList<Movie> movies = new ArrayList<>();
    private ArrayList<Series> series = new ArrayList<>();

    MediaManager(TextUI ui, FileIO io, User currentUser, String movieDataPath, String SeriesDataPath) {
        this.ui = ui;
        this.io = io;
        this.currentUser = currentUser;
        this.movies = io.readMovieData(movieDataPath);
        // this.series = io.readSeries
    }

    private void addToWatchlist(Movie movie, User currentUser) {
        currentUser.addToWatchlist(movie);
        ui.displayMsg("Movie added to your watchlist: " + movie.getMediaTitle());
        io.saveWatchlist(currentUser.getUsername(), currentUser.getWatchlist());
    }

}
