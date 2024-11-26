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
}
