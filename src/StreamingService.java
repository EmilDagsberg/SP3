import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class StreamingService {
    String name;
    String movieDataPath;
    String seriesDataPath;
    String userDataPath;
    ArrayList<Movie> movies = new ArrayList<Movie>();
    //ArrayList<User> users;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    StreamingService(String name) {
        this.name = name;
        this.movieDataPath = "data/film.txt";
        this.seriesDataPath = "data/serier.txt";
        this.userDataPath = "data/userData.csv";
        //this.users = new ArrayList<User>();
    }

    void startStreamingService() {
        ui.displayMsg("Welcome to " + this.name);

        ui.displayMsg("Make new user? Press 1");
        ui.displayMsg(("Login on existing user? Press 2"));
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
        String password = ui.promptText("Type password:");
        User u = new User(username, password);
        this.addUser(u);
    }

    public void addUser(User u) {
        FileIO.SaveUserData(u.toString(), this.userDataPath);
        // User (toString) vi lavet bliver sat i userData filen.
    }

    public void loadUserData() {
        HashMap<String, String> userData = io.readUserData(this.userDataPath);
        if(!userData.isEmpty()) {
            String enteredUsername = ui.promptText("Type username:");
            String enteredPassword = ui.promptText("Type password:");
            if(userData.containsKey(enteredUsername) && userData.get(enteredUsername).equals(enteredPassword)){
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

    public void homeMenu() {
        int choice = ui.displayHomeMenu("Type number:");
        switch (choice) {
            case 1:
                // Call on method to search on a specific movie or series
                ui.displayMsg("Searching movies");
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
                ui.displayMsg("Seeing watchlist");
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