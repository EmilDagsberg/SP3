import java.util.ArrayList;

public class MediaManager {

    private String movieDataPath;
    private String seriesDataPath;
    private User currentUser;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Series> series = new ArrayList<Series>();
    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();

MediaManager(TextUI ui, FileIO io, User currentUser, String movieDataPath, String seriesDataPath) {
        this.ui = ui;
        this.io = io;
        this.currentUser = currentUser;
        this.movies = io.readMovieData(movieDataPath);
        // this.series = io.readSeries
    }

    public void searchByTitle(){
        String searchInput = ui.promptText("Enter your searchword:");
        ArrayList<Movie> searchResults = new ArrayList<Movie>(); //list to contain results from search
        int counter = 1;
        for(Movie s : movies){
            String title = s.getMediaTitle().toLowerCase();
            if(title.contains(searchInput)){
                searchResults.add(s);
            }
        }
        ui.displayMsg("Results: \n");
        for(Movie s :searchResults){
            System.out.println(counter +". " + s.getMediaTitle());
            counter++;
        }
    private void addToWatchlist(Media media, User currentUser) {
        currentUser.addToWatchlist(media);
        ui.displayMsg("Movie added to your watchlist: " + media.getMediaTitle());
        io.saveWatchlist(currentUser.getUsername(), currentUser.getWatchlist());
    }

        int userChoice = ui.promptNumeric("Type a number to view details");


    }
}
