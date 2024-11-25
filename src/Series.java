public class Series extends Media {
    public Series(String mediaTitle, String releaseYear, String genre, double rating, String seasonsAndEpisodes){
        super(mediaTitle,releaseYear,genre,rating,seasonsAndEpisodes);
    }

    @Override
    public String toString() {
        return "Title: " + this.mediaTitle + " ReleaseYear: " + this.releaseYear + " Genre: " + this.genre + " Rating: " + this.rating + " SeasonsAndEpisodes: " + this.seasonsAndEpisodes;
    }
}
