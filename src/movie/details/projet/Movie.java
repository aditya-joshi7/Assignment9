package movie.details.projet;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;




public class Movie implements Serializable,Comparable<Movie>{
	
	
	private int movieId;
	private String movieName;
	private Category movieType;
	private Language  language;
	private Date releaseDate; 
	private List<String> casting;
	private double rating;
	private double  totalBusinessDone;
	public Movie() {
		
		
		
	}
	
	
	public int getMovieId() {
		return movieId;
	}
	public void setMovieI(int movieId) {
		this.movieId = movieId;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public Category getMovieType() {
		return movieType;
	}
	public void setMovieType(Category movieType) {
		this.movieType = movieType;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<String> getCasting() {
		return casting;
	}
	public void setCasting(List<String> casting) {
		this.casting = casting;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public double getTotalBusinessDone() {
		return totalBusinessDone;
	}
	public void setTotalBusinessDone(double totalBusinessDone) {
		this.totalBusinessDone = totalBusinessDone;
	}
	public Movie(int movieId, String movieName, Category movieType, Language language, Date releaseDate,
			List<String> casting, double rating, double totalBusinessDone) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieType = movieType;
		this.language = language;
		this.releaseDate = releaseDate;
		this.casting = casting;
		this.rating = rating;
		this.totalBusinessDone = totalBusinessDone;
	}
	@Override
	public String toString() {
		return "Movie [movieI=" + movieId + ", movieName=" + movieName + ", movieType=" + movieType + ", language="
				+ language + ", releaseDate=" + releaseDate + ", casting=" + casting + ", rating=" + rating
				+ ", totalBusinessDone=" + totalBusinessDone + "]";
	}


	@Override
	public int compareTo(Movie o) {
		if(this.movieId<o.getMovieId()) {
			return 1;
		}
		return 0;
	}

	
	
	
	
}
