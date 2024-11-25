public class Movie extends Media {
    public Movie(String mediaTitle, String releaseYear, String genre, double rating){
        super(mediaTitle,releaseYear,genre,rating);
    }


    @Override
    public String toString() {
        return "Title: " + this.mediaTitle + "\n" +
                "ReleaseYear: " + this.releaseYear + "\n" +
                "Genre: " + this.genre + "\n" +
                "Rating: " + this.rating;
    }

}