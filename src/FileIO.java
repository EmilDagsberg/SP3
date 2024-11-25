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
                try{
                    String newRating = data[3].trim().replace(',', '.');
                    rating = Double.valueOf(newRating);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                Movie newMovie = new Movie(title, releaseYear, genres, rating);
                movieData.add(newMovie);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return movieData;
    }

    public static ArrayList<Series> readSeriesData(String path){
        String title;
        String releaseYear;
        String genres;
        Double rating;
        String seasonsAndEpisodes;
        ArrayList<Series> seriesData = new ArrayList();
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
                try{
                    String newRating = data[3].trim().replace(',', '.');
                    rating = Double.valueOf(newRating);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                seasonsAndEpisodes = data[4];
                Series newSeries = new Series(title, releaseYear, genres, rating, seasonsAndEpisodes);
                seriesData.add(newSeries);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return seriesData;
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
        try (FileWriter writer = new FileWriter("data/" + username + "_watchlist.txt", true)) {
            for (Media media : watchlist) {
                writer.write(media.getMediaTitle() +  media.getReleaseYear() + media.getGenre() + media.getRating() + "\n");
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
            while(scan.hasNextLine()) {

            }


    }


}


