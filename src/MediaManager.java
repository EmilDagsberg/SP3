import java.util.ArrayList;
import java.util.List;

public class MediaManager {
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
        this.series = io.readSeriesData(seriesDataPath);
        this.streamingService = streamingService;
    }

    public void searchByTitle(User currentUser) {
        this.currentUser = currentUser;
        String searchInput = ui.promptText("Searching by title. Enter your searchword:");
        ArrayList<Media> searchResults = new ArrayList<Media>(); //list to contain results from search
        int counter = 1;
        for (Movie s : movies) {
            String title = s.getMediaTitle().toLowerCase();
            if (title.contains(searchInput)) {
                searchResults.add(s);
            }
        }
        for (Series s : series) {
            String title = s.getMediaTitle().toLowerCase();
            if (title.contains(searchInput)) {
                searchResults.add(s);
            }
        }
        if (searchResults.isEmpty()) {
            ui.displayMsg("No results.");
            streamingService.homeMenu();
        }
        ui.displayMsg("Results: \n");
        for (Media s : searchResults) {
            ui.displayMsg(counter + ". " + s.getMediaTitle());
            counter++;
        }

        int userChoice = ui.promptNumeric("Type a number to view details");

        displayMediaInformation(searchResults.get(userChoice - 1));
    }

    public void searchByGenre() {
        String searchInput = ui.promptText("Searching by genre. Enter your searchword:");
        ArrayList<Media> searchResults = new ArrayList<Media>(); //list to contain results from search
        int counter = 1;
        for (Movie s : movies) {
            String title = s.getGenre().toLowerCase();
            if (title.contains(searchInput)) {
                searchResults.add(s);
            }
        }
        for (Series s : series) {
            String title = s.getGenre().toLowerCase();
            if (title.contains(searchInput)) {
                searchResults.add(s);
            }
        }
        if (searchResults.isEmpty()) {
            ui.displayMsg("No results.");
            streamingService.homeMenu();
        }
        ui.displayMsg("Results: \n");
        for (Media s : searchResults) {
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
                PlayMedia(media);
                break;
            case 2:
                addToWatchlist(media);
                ui.displayMsg("Going back...");
                streamingService.homeMenu();
                break;
            case 3:
                ui.displayMsg("Going back to Home menu");
                streamingService.homeMenu();
            default:
                ui.displayMsg("Invalid choice. Going back...");
                streamingService.homeMenu();
        }
    }

    public void PlayMedia(Media media) {
        ui.displayMsg("You are now watching: " + media.getMediaTitle());
        currentUser.addToPrevWatchedList(media);
        ui.displayMsg("You have finished watching");
        streamingService.homeMenu();

    }

    private void addToWatchlist(Media media) {
        currentUser.addToWatchlist(media); // add mediet til currentUsers watchlist
        ui.displayMsg("Movie/Series added to your watchlist: " + media.getMediaTitle());
        io.saveWatchlist(currentUser.getUsername(), currentUser.getWatchlist()); // gemmer så mediet i users unikke watchlistfil
    }

    public void medialistInteraction(User currentUser, List<Media> mediaList, String listName) {
        if (mediaList.isEmpty()) {
            ui.displayMsg("Your " + listName + " is empty.");
            streamingService.homeMenu();  // Go back to the home menu if the watchlist is empty
            return;
        }
        ui.displayMsg("Your" + listName); // Viser ens watchlist med et nummer foran.
        int counter = 1;
        for (Media media : mediaList) {
            ui.displayMsg(counter + ". " + media.getMediaTitle() + " (" + media.getReleaseYear() + ")");
            counter++;
        }

        int userChoice = ui.promptNumeric("Choose a movie/series number to start watching, or 0 to go back");

        if (userChoice == 0) {
            ui.displayMsg("Going back...");
            streamingService.homeMenu();
        } else if (userChoice > 0 && userChoice <= currentUser.getWatchlist().size()) {
            Media chosenMedia = mediaList.get(userChoice - 1); // Det nummer som brugeren skrev (-1) er det index i arraylisten.
            PlayMedia(chosenMedia);
        } else {
            ui.displayMsg("Invalid choice, going back...");
            medialistInteraction(currentUser, mediaList, listName);  // Rekursiv kald, så brugeren må vælge igen.
        }
    }

    public void watchlistInteraction(User currentUser) {
        this.currentUser = currentUser;
        medialistInteraction(currentUser, currentUser.getWatchlist(), "watchlist");
    }

    public void prevWatchedlistInteraction(User currentUser) {
        this.currentUser = currentUser;
        medialistInteraction(currentUser, currentUser.getPrevWatched(), "previously watched list");
    }
}
