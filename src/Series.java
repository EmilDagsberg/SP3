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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Series series = (Series) o;
        return mediaTitle.equals(series.mediaTitle) &&
                releaseYear.equals(series.releaseYear) &&
                genre.equals(series.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mediaTitle, releaseYear, genre);
    }
}
