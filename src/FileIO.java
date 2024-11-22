import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileIO {

    public static ArrayList<String> readData(String path) {
        ArrayList<String> data = new ArrayList();
        File file = new File(path);
        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();//skip header

            while (scan.hasNextLine()) {
                String line = scan.nextLine(); // "title; release year; etc."
                data.add(line);
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

    public static void SaveUserData(String userAsText, String path, String header) {
        try {
            FileWriter writer = new FileWriter(path, true);
                writer.write(userAsText + "\n"); //"username, password";
            writer.close();
        } catch (IOException e) {
            System.out.println("something went wrong when writing to file");
        }
    }

    /* public String[] readWatchListData(String path, int length) {
        String[] data = new String[length];
        File file = new File(path);
        int counter = 0;

        try {
            Scanner scan = new Scanner(file);
            scan.nextLine();

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                data[counter] = line;
                counter++;
            }

        } catch (FileNotFoundException e) {
            System.out.println("File was not found");
        }
        return data;

    } */
}