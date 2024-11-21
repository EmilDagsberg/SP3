import java.lang.reflect.Array;
import java.util.ArrayList;

public class StreamingService {
    String name;
    String movieDataPath;
    String seriesDataPath;
    String userDataPath;
    ArrayList<User> users;
    TextUI ui = new TextUI();
    FileIO io = new FileIO();

    StreamingService(String name) {
        this.name = name;
        this.movieDataPath = "data/film.txt";
        this.seriesDataPath = "data/serier.txt";
        this.userDataPath = "data/userData.csv";
        this.users = new ArrayList<User>();
    }

    void startStreamingService() {
        ui.displayMsg("Welcome to " + this.name);
        ArrayList<String> userData = io.readData(this.userDataPath);

        ui.displayMsg("Make new user? Press 1");
        ui.displayMsg(("Login on existing user? Press 2"));
        int choice = ui.promptNumeric("Type 1 or 2.");

        if(choice == 1) {
            createUser();
        }

    }

    void createUser() {
        String username = ui.promptText("Type username:");
        String password = ui.promptText("Type password:");
        User u = new User(username, password);
        this.addUser(u);
    }

    public void addUser(User u) {
        this.users.add(u);
    }
}
