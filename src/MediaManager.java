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

    public void searchByTitle() {
        String searchInput = ui.promptText("Enter your searchword:");
        ArrayList<Movie> searchResults = new ArrayList<Movie>(); //list to contain results from search
        int counter = 1;
        for (Movie s : movies) {
            String title = s.getMediaTitle().toLowerCase();
            if (title.contains(searchInput)) {
                searchResults.add(s);
            }
        }
        ui.displayMsg("Results: \n");
        for (Movie s : searchResults) {
            System.out.println(counter + ". " + s.getMediaTitle());
            counter++;
        }
        int userChoice = ui.promptNumeric("Type a number to view details");

        displayMediaInformation(searchResults.get(userChoice - 1));
    }

        public void displayMediaInformation(Media media) {
            ui.displayMsg(media.toString());

            int choice = ui.promptNumeric("What do you want to do?\n1. Watch movie\n2. Add to watchlist\n3. Go back");

            switch (choice) {
                case 1:
                    // watch media method
                    break;
                case 2:
                    addToWatchlist(media);
                    break;
                case 3:
                    ui.displayMsg("Going back...");
                    break;
                default:
                    ui.displayMsg("Invalid choice. Going back...");
            }
        }

        private void addToWatchlist(Media media) {
        this.currentUser.addToWatchlist(media);
        ui.displayMsg("Movie added to your watchlist: " + media.getMediaTitle());
        io.saveWatchlist(currentUser.getUsername(), currentUser.getWatchlist());
    }

}
