package movie.details.projet;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeSet;

public class MovieTester {
	
	
	public static void main(String args[]) {
		
		MovieService ms=new MovieService();
		File f=new File("C:\\Users\\hp\\eclipse-workspace\\Assignment9\\movieDetails.txt");
		
		
		List<Movie> list = null;
		try {
			list = ms.populateMovies(f);
			System.out.println(list.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/**
		 try {
			boolean ans=ms.allAllMoviesInDb(list);
		} catch (SQLException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
	}
		
		
		
		String filename="C:\\Users\\hp\\eclipse-workspace\\Assignment9\\S.txt";
		ms.serializeMovies(list, filename);
		
		ms.deserializeMovie(filename);
		
		*/
		List<Movie> cast=ms.getMoviesByActor(list,"Ranbir Kapoor");
		System.out.println(cast);
		
		HashMap<Language,TreeSet<Movie>> hm=ms.businessDone(list, 50.0);
		
		System.out.println(hm);
		
		
		
	}
	
	
	
}
