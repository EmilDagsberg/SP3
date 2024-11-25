import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class FileIO {

    public static HashMap<String, String> readUserData(String path) {
        HashMap<String, String> data = new HashMap<>();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header

            while (scan.hasNextLine()) {
                String[] userDetails = scan.nextLine().split(",");

                if(userDetails.length == 2) {
                    String username = userDetails[0].trim();
                    String password = userDetails[1].trim();
                    data.put(username, password);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return data;
    }

    public static ArrayList<Movie> readMovieData(String path) {
        String title;
        String releaseYear;
        String genres;
        Double rating;
        ArrayList<Movie> movieData = new ArrayList();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header
            while (scan.hasNextLine()) {
                String line = scan.nextLine(); // "title; release year; etc."
                String[] data = line.split(";"); //splits a line into 4 Strings containing Title/ReleaseYear/Genres/Rating"
                title = data[0];
                releaseYear = data[1];
                genres = (data[2]);
                rating = Double.valueOf(data[3]);
                Movie newMovie = new Movie(title, releaseYear, genres, rating);
                movieData.add(newMovie);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return movieData;
    }

    public static void SaveUserData(String userAsText, String path) {
        try {
            FileWriter writer = new FileWriter(path, true);
                writer.write(userAsText + "\n"); //"username, password";
            writer.close();
        } catch (IOException e) {
            System.out.println("something went wrong when writing to file");
        }
    }

    public void saveWatchlist(String username, ArrayList<Media> watchlist) {
        try (FileWriter writer = new FileWriter("data/" + username + "_watchlist.txt")) {
            for (Media media : watchlist) {
                writer.write(media.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Media> readWatchlist(String username) {
        ArrayList<Media> watchlist = new ArrayList<>();
        File file = new File("data/" + username + "_watchlist.txt");

        try {
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine()) {
                String mediaInfo = scan.nextLine().trim();
                if (!mediaInfo.isEmpty()) {
                    // Assuming you have both Movie and Series in your Media hierarchy
                    // You need to parse the line and recreate the appropriate object
                    // Example for parsing Movie or Series objects:
                    if (mediaInfo.startsWith("Movie")) {
                        Movie movie = parseMovie(mediaInfo);  // Implement parseMovie
                        watchlist.add(movie);
                    } else if (mediaInfo.startsWith("Series")) {
                        Series series = parseSeries(mediaInfo);  // Implement parseSeries
                        watchlist.add(series);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Watchlist file not found for user " + username);
        }
        return watchlist;
    }

    // Parse a Movie from its string representation (assuming the format is consistent)
    private Movie parseMovie(String mediaInfo) {
        String[] parts = mediaInfo.split(", ");
        // Example: "Movie: Title (2024), Genre, Rating"
        String title = parts[1].trim();
        String releaseYear = parts[2].trim().replace("(", "").replace(")", "");
        String genre = parts[3].trim();
        double rating = Double.parseDouble(parts[4].trim());
        return new Movie(title, releaseYear, genre, rating);
    }

    // Parse a Series from its string representation (assuming the format is consistent)
    private Series parseSeries(String mediaInfo) {
        String[] parts = mediaInfo.split(", ");
        // Example: "Series: Title (2023), Genre, 1 Season, 10 Episodes"
        String title = parts[1].trim();
        String releaseYear = parts[2].trim().replace("(", "").replace(")", "");
        String genre = parts[3].trim();
        String seasonsAndEpisodes = parts[4].trim();
        double rating = Double.parseDouble(parts[5].trim());
        return new Series(title, releaseYear, genre, rating, seasonsAndEpisodes);
    }
}


