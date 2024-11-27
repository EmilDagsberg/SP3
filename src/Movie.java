import java.util.Objects;

public class Movie extends Media {
    public Movie(String mediaTitle, String releaseYear, String genre, double rating){
        super(mediaTitle,releaseYear,genre,rating);
    }


    @Override
    public String toString() {
        return "Title: " + this.mediaTitle + "\nReleaseYear: " + this.releaseYear + "\nGenre: " + this.genre + "\nRating: " + this.rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return mediaTitle.equals(movie.mediaTitle) &&
                releaseYear.equals(movie.releaseYear) &&
                genre.equals(movie.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaTitle, releaseYear, genre);
    }

}