public abstract class Media {
    protected String mediaTitle;
    protected double rating;
    protected String genre;
    protected String releaseYear;
    protected String seasonsAndEpisodes;

    public Media(String mediaTitle,double rating,String genre,String releaseYear,String seasonsAndEpisodes){
        this.mediaTitle = mediaTitle;
        this.rating = rating;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.seasonsAndEpisodes = seasonsAndEpisodes;
    }

}
