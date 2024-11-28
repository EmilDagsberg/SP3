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
    public boolean equals(Object o) { // Denne method sammenligner 2 objekter og kigger på deres 3 fields(mediaTitle, releaseYear, genre):
        // Hvis det er ens, så er objekterne set som det samme. Ellers er de ikke.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return mediaTitle.equals(movie.mediaTitle) &&
                releaseYear.equals(movie.releaseYear) &&
                genre.equals(movie.genre);
    }


    @Override
    public int hashCode() { // Laver en hashcode(integer value tildelt hvert objekt).
        // Hvis 2 objekter er equal(returns true), så skal deres hashcode også være det samme. Det er dette metoden gør.
        return Objects.hash(mediaTitle, releaseYear, genre);
    }

}