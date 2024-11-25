import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class StreamingService {
    private String name;
    private String movieDataPath;
    private String seriesDataPath;
    private String userDataPath;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Series> series = new ArrayList<Series>();
    private HashMap<String, String> userData;
    private User currentUser;
    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();
    private MediaManager manager;

    StreamingService(String name) {
        this.name = name;
        this.movieDataPath = "data/film.txt";
        this.seriesDataPath = "data/serier.txt";
        this.userDataPath = "data/userData.csv";
        this.userData = io.readUserData(this.userDataPath);
        //this.users = new ArrayList<User>();
        this.manager = new MediaManager(ui, io, this.currentUser, movieDataPath, seriesDataPath);
    }

    void startStreamingService() {
        ui.displayMsg("Welcome to " + this.name + "\n1. Make new user? \n2. Login on existing user?");

        int choice = ui.promptNumeric("Type 1 or 2.");

        if (choice == 1) { // Brugernes valg bliver gemt og tjekkes om hvilket nummer det er.
            createUser();
            homeMenu();
        } else if (choice == 2) {
            loadUserData();
        } else {
            ui.displayMsg("Sorry but that choice is invalid");
            // hvis ikke de skriver 1 eller 2, bliver dette her printet og funktionen bliver kaldt igen.
            startStreamingService();
        }
    }

    void createUser() {
        String username = ui.promptText("Type username:");
        while(userData.containsKey(username)) { // Loop der altid tjekker om det nye username allerede eksistere.
            ui.displayMsg("Username already exists. Please choose different username");
            username = ui.promptText("Type username:");
        }
        String password = ui.promptText("Type password:");
        currentUser = new User(username, password);
        this.addUser(currentUser);
    }

    public void addUser(User u) {
        FileIO.SaveUserData(u.toString(), this.userDataPath);
        // User (toString) vi lavet bliver sat i userData filen.
    }

    public void loadUserData() {
        if(!userData.isEmpty()) {
            String enteredUsername = ui.promptText("Type username:");
            String enteredPassword = ui.promptText("Type password:");
            if(userData.containsKey(enteredUsername) && userData.get(enteredUsername).equals(enteredPassword)){
                // Den if tjekker om det tastede username (key) eksistere. Herefter os tastede password stemmer overens med sin key.
                currentUser = new User(enteredUsername, enteredPassword);
                homeMenu();
            } else {
                ui.displayMsg("Username or password is wrong. Please try again");
                loadUserData();
            }
        } else {
            ui.displayMsg("Sorry, but we don't have any user yet. Please create on.");
            createUser();
        }
    }

    public void loadWatchlist() {
        ArrayList<Media> watchlist = io.readWatchlist(currentUser.getUsername());
        currentUser.watchlist = watchlist;
    }

    public void homeMenu() {
        int choice = ui.displayHomeMenu("Type number:");
        switch (choice) {
            case 1:
                // Call on method to search on a specific movie or series
                manager.searchByTitle();
                break;
            case 2:
                // call on method to search on a genre and get all movies and series in that genre.
                ui.displayMsg("Searching genre");
                break;
            case 3:
                // call on method to see a list of previously watched movies and series.
                ui.displayMsg("Seeing previously watched media");
                break;
            case 4:
                // call on method to see a list of saved movies that you want to watch.
                currentUser.displayWatchlist();
                break;
            case 5:
                // call on method to log out and close the program. Probably don't need a method. Just print that you are logging out.
                ui.displayMsg("Logging out...");
                break;
            default:
                ui.displayMsg("Choice invalid");
                homeMenu();
        }
    }
}