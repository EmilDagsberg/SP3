public abstract class Media {
    protected String mediaTitle;
    protected double rating;
    protected String genre;
    protected String releaseYear;
    protected String seasonsAndEpisodes;

    public Media(String mediaTitle, String releaseYear, String genre, double rating, String seasonsAndEpisodes){
        this.mediaTitle = mediaTitle;
        this.releaseYear = releaseYear;
        this.genre = genre;
        this.rating = rating;
        this.seasonsAndEpisodes = seasonsAndEpisodes;
    }

    public Media(String mediaTitle,String releaseYear, String genre, double rating){
        this.mediaTitle = mediaTitle;
        this.rating = rating;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    public String getMediaTitle() {
        return mediaTitle;
    }

    public double getRating() {
        return rating;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public String getSeasonsAndEpisodes() {
        return seasonsAndEpisodes;
    }
}
