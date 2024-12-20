import java.util.ArrayList;
import java.util.List;

public class MediaManager {
    private User currentUser;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Series> series = new ArrayList<Series>();
    private TextUI ui;
    private FileIO io;
    private StreamingService streamingService;


    MediaManager(TextUI ui, FileIO io, String movieDataPath, String seriesDataPath, StreamingService streamingService, User currentUser) {
        this.ui = ui;
        this.io = io;
        this.movies = io.readMediaData(movieDataPath, "Movie");
        this.series = io.readMediaData(seriesDataPath, "Series");
        this.streamingService = streamingService;
        this.currentUser = currentUser;
    }

    public void searchByTitle() {
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

        int choice = ui.promptNumeric("\nWhat do you want to do?\n1. ⏵ Watch now\n2. + Add to watchlist\n3. ⌂ Go back home");

        switch (choice) {
            case 1:
                PlayMedia(media);
                break;
            case 2:
                currentUser.addToWatchlist(media);
                ui.displayMsg("\nHome Menu:");
                streamingService.homeMenu();
                break;
            case 3:
                ui.displayMsg("\nHome Menu:");
                streamingService.homeMenu();
                break;
            default:
                ui.displayMsg("Invalid choice. Going back...");
                streamingService.homeMenu();
        }
    }

    public void PlayMedia(Media media) {
        ui.displayMsg("\n" + "You are now watching: " + media.getMediaTitle());
        currentUser.addToPrevWatchedList(media);

        int choice = ui.promptNumeric("What do you want to do?\n1. ⏸ Pause\n2. ⏹ Stop\n3. ⌂ Home Menu\n4. ℹ Info");

        switch (choice) {
            case 1:
                ui.displayMsg("You have paused " + media.getMediaTitle() + "\n");
                displayMediaInformation(media);
                break;
            case 2:
                ui.displayMsg("You have stopped watching " + media.getMediaTitle() + "\n\nHome Menu:");
                streamingService.homeMenu();
                break;
            case 3:
                ui.displayMsg("You have stopped watching " + media.getMediaTitle() + "\n\nHome Menu:");
                streamingService.homeMenu();
                break;
            case 4:
                displayMediaInformation(media);
                break;
            default:
                ui.displayMsg("Invalid choice. Going back...");
                streamingService.homeMenu();
        }

    }
    
    public void medialistInteraction(List<Media> mediaList, String listName) {
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
            medialistInteraction(mediaList, listName);  // Rekursiv kald, så brugeren må vælge igen.
        }
    }
}
