import java.lang.reflect.Array;
import java.util.ArrayList;

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
        ArrayList<String> userData = io.readData(this.userDataPath); // Læser data i userData filen.

        ui.displayMsg("Make new user? Press 1");
        ui.displayMsg(("Login on existing user? Press 2"));
        int choice = ui.promptNumeric("Type 1 or 2.");

        if(choice == 1) { // Brugernes valg bliver gemt og tjekkes om hvilket nummer det er.
            createUser();
            homeMenu();
        } else if(choice == 2) {
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
        FileIO.SaveUserData(u.toString(), this.userDataPath, "username, password");
        // User (toString) vi lavet bliver sat i userData filen.
    }

    public void loadUserData() {
        ArrayList<String> userData = io.readData(this.userDataPath);
        ArrayList<String> usernames = new ArrayList<>(); // Filen af users blev læst og bliver fordelt. Username kommer i et array.
        ArrayList<String> passwords = new ArrayList<>(); // passwords kommer i et array.
        if(!userData.isEmpty()) {
            for (String s : userData) {
                String[] info = s.split(", "); // Indholdet i filen blev delt op, når der er et ", ".
                usernames.add(info[0]);
                passwords.add(info[1]);
            }
            if(usernames.contains((ui.promptText("Type username:"))) && passwords.contains(ui.promptText("Type password;"))) {
                // Kigger om det username, brugeren skriver er i arrayet. Det samme som password
                // Problemet er at man kan skrive et hvilket som helst password der er i arrayet. Selv hvis det er til et andet username.
                // HASHMAP KAN BRUGES HER!!!!!
                    homeMenu();
                } else {
                    ui.displayMsg("Username or password is wrong. Please try again.");
                    loadUserData();
                }
        } else {
            ui.displayMsg("Sorry, but we don't have any users yet. So please make one");
            // Hvis filen er tom, så siger vi at vi ikke har nogle brugere og kalder på metoden igen.
            createUser();
        }
    }

    public void homeMenu() {
        ui.displayMsg("Find movie or series. Press 1");
        ui.displayMsg("Search genre. Press 2");
        ui.displayMsg("See previously watched movies and series. Press 3");
        ui.displayMsg("See watchlist. Press 4");
        ui.displayMsg("Log out. Press 5");
        int choice = ui.promptNumeric("Type nummer:");
        switch(choice) {
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

    public void createMovies(){
        try {
            ArrayList<String> movieData = io.readData(movieDataPath);
            String title;
            int releaseYear;
            String genres;
            Double rating;
            for (String s : movieData) {
                String[] data = s.split(";"); //splits a line into 4 Strings containing Title/ReleaseYear/Genres/Rating"
                title = data[0];
                releaseYear = Integer.valueOf(data[1]);
                genres = (data[2]);
                rating = Double.valueOf(data[3]);
                Movie newMedia = new Movie(title, releaseYear, genres, rating);
                movies.add(newMedia);
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    public void createMovies(){
        try {
            ArrayList<String> movieData = io.readData(movieDataPath);
            String title;
            int releaseYear;
            String genres;
            Double rating;
            for (String s : movieData) {
                String[] data = s.split(";"); //splits a line into 4 Strings containing Title/ReleaseYear/Genres/Rating"
                title = data[0];
                releaseYear = Integer.valueOf(data[1]);
                genres = (data[2]);
                rating = Double.valueOf(data[3]);
                Movie newMedia = new Movie(title, releaseYear, genres, rating);
                movies.add(newMedia);
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found");
        }
    }
}
