import java.util.ArrayList;

public class MediaManager {

    private String movieDataPath;
    private String seriesDataPath;
    private User currentUser;
    private ArrayList<Movie> movies = new ArrayList<Movie>();
    private ArrayList<Series> series = new ArrayList<Series>();
    private TextUI ui = new TextUI();
    private FileIO io = new FileIO();

    MediaManager(TextUI ui, FileIO io, User currentUser, String movieDataPath, String seriesDataPath){
        this.ui = ui;
        this.io = io;
        this.currentUser = currentUser;
        this.movies = io.readMovieData(movieDataPath);
        this.series = io.readSeriesData(seriesDataPath);
    }

    public void searchByTitle(){
        String searchInput = ui.promptText("Enter your searchword:");
        ArrayList<Media> searchResults = new ArrayList<Media>(); //list to contain results from search
        int counter = 1;
        for(Movie s : movies){
            String title = s.getMediaTitle().toLowerCase();
            if(title.contains(searchInput)){
                searchResults.add(s);
            }
        }
        for(Series s : series){
            String title = s.getMediaTitle().toLowerCase();
            if(title.contains(searchInput)){
                searchResults.add(s);
            }
        }
        ui.displayMsg("Results: \n");
        for(Media s :searchResults){
            System.out.println(counter +". " + s.getMediaTitle());
            counter++;
        }

        int userChoice = ui.promptNumeric("Type a number to view details");

        displayMediaInformation(searchResults.get(userChoice-1));
    }

    public void displayMediaInformation(Media media){

        //Display the information of the media object. Use media.getMediaTitle()

        //Display a numbered menu that gives the user choices of what to do with the given media. Switch case etc.

        //Add the relevant methods to the cases in the switch. 1 = AddToWatchlist(media) 2 = PlayMedia(media) etc.
        //Remember to use the class diagram for method names
    }
}
