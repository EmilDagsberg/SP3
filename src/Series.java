import java.util.Objects;

public class Series extends Media {
    public Series(String mediaTitle, String releaseYear, String genre, double rating, String seasonsAndEpisodes){
        super(mediaTitle,releaseYear,genre,rating,seasonsAndEpisodes);
    }

    @Override
    public String toString() {
        return "Title: " + this.mediaTitle + "\n" +
            "ReleaseYear: " + this.releaseYear + "\n" +
            "Genre: " + this.genre + "\n" +
            "Rating: " + this.rating + "\n" +
            "SeasonsAndEpisodes: " + this.seasonsAndEpisodes;
    }

    @Override
    public boolean equals(Object o) { // Denne method sammenligner 2 objekter og kigger på deres 3 fields(mediaTitle, releaseYear, genre):
        // Hvis det er ens, så er objekterne set som det samme. Ellers er de ikke.
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return mediaTitle.equals(series.mediaTitle) &&
                releaseYear.equals(series.releaseYear) &&
                genre.equals(series.genre);
    }

    @Override
    public int hashCode() {// Laver en hashcode(integer value tildelt hvert objekt).
        // Hvis 2 objekter er equal(returns true), så skal deres hashcode også være det samme. Det er dette metoden gør.
        return Objects.hash(mediaTitle, releaseYear, genre);
    }
}
