import java.lang.reflect.Array;
import java.util.ArrayList;

public class StreamingService {
    String name;
    String movieDataPath;
    String seriesDataPath;
    String userDataPath;
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
        ArrayList<String> userData = io.readData(this.userDataPath);

        ui.displayMsg("Make new user? Press 1");
        ui.displayMsg(("Login on existing user? Press 2"));
        int choice = ui.promptNumeric("Type 1 or 2.");

        if(choice == 1) {
            createUser();
            homeMenu();
        } else if(choice == 2) {
            loadUser();
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
    }

    public void loadUser() {
        ArrayList<String> userData = io.readData(this.userDataPath);
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<String> passwords = new ArrayList<>();
        String password = "";
        if(!userData.isEmpty()) {
            for (String s : userData) {
                String[] info = s.split(", ");
                usernames.add(info[0]);
                passwords.add(info[1]);
            }
            if(usernames.contains((ui.promptText("Type username:"))) && passwords.contains(ui.promptText("Type password;"))) {
                    homeMenu();
                } else {
                    ui.displayMsg("Username or password is wrong. Please try again.");
                    loadUser();
                }
        } else {
            ui.displayMsg("Sorry, but we don't have any users yet. So please make one");
            createUser();
        }
    }

    public void homeMenu() {

    }
}
