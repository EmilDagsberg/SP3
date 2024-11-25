import java.util.ArrayList;

public class MediaManager {

    private String movieDataPath;
    private String seriesDataPath;
    private User currentUser;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Series> series = new ArrayList<Series>();
    private TextUI ui;
    private FileIO io;
    private StreamingService streamingService;


    MediaManager(TextUI ui, FileIO io, String movieDataPath, String seriesDataPath, StreamingService streamingService) {
        this.ui = ui;
        this.io = io;
        this.movies = io.readMovieData(movieDataPath);
        this.streamingService = streamingService;
        // this.series = io.readSeries
    }

    public void searchByTitle(User currentUser) {
        this.currentUser = currentUser;
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
                ui.displayMsg("Going back...");
                streamingService.homeMenu();
                break;
            case 3:
                ui.displayMsg("Going back...");
                searchByTitle(currentUser);
                break;
            default:
                ui.displayMsg("Invalid choice. Going back...");
                streamingService.homeMenu();
            }
        }

    private void addToWatchlist(Media media) {
        currentUser.addToWatchlist(media);
        ui.displayMsg("Movie added to your watchlist: " + media.getMediaTitle());
        io.saveWatchlist(currentUser.getUsername(), currentUser.getWatchlist());
    }

    public void watchlistInteraction(User currentUser) {
        this.currentUser = currentUser;
        if (currentUser.getWatchlist().isEmpty()) {
            ui.displayMsg("Your watchlist is empty.");
            streamingService.homeMenu();  // Go back to the home menu if the watchlist is empty
            return;
        }

        // Display watchlist and allow user to select a movie
        ui.displayMsg("Your Watchlist:");
        int counter = 1;
        for (Media media : currentUser.getWatchlist()) {
            ui.displayMsg(counter + ". " + media.getMediaTitle());
            counter++;
        }

        int userChoice = ui.promptNumeric("Choose a movie number to start watching, or 0 to go back");

        if (userChoice == 0) {
            ui.displayMsg("Going back...");
            streamingService.homeMenu();  // Go back to home menu
        } else if (userChoice > 0 && userChoice <= currentUser.getWatchlist().size()) {
            Media selectedMedia = currentUser.getWatchlist().get(userChoice - 1);
            ui.displayMsg("You are now watching: " + selectedMedia.getMediaTitle());
            // You could add functionality to actually "watch" the media here, if needed
        } else {
            ui.displayMsg("Invalid choice, going back...");
            watchlistInteraction(currentUser);  // Prompt the user again if the choice is invalid
        }
    }
}
