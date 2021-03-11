package movie.details.projet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;





public class MovieService {
	//Inner class to implement to do the comparison on basis of business done by the Movies
	class Compare implements Comparator<Movie>{

		@Override
		public int compare(Movie o1, Movie o2) {
			double d1=o1.getTotalBusinessDone();
			double d2=o2.getTotalBusinessDone();
			
			if(d1<d2) {
				return -1;
			}
			else if(d1==d2) {
				return 0;
			}
			else {
				
				return 1;
			}
		}
	}
	
		
		
		

	public  List<Movie> populateMovies(File file) throws IOException{
		
		List<Movie> list=new ArrayList<Movie>();
		
		FileReader fr=new FileReader(file);
		BufferedReader br=new BufferedReader(fr);   
		
		String line =br.readLine();
		
		while(line!=null){
			String[] temp=line.split(",");
			int len=temp.length;
			if(len==1) {
				break;
			}
			List<String> cast=new ArrayList<>();
			Category c=new Category(temp[2]);
			Language l=new Language(temp[3]);
			for(int i=7;i<len;i++) {
				cast.add(temp[i]);
			}
			double rating=Double.parseDouble(temp[5]);
			double eraning=Double.parseDouble(temp[6]);
			int movideId=Integer.parseInt(temp[0]);
			Date d=Date.valueOf(temp[4]);
			Movie m=new Movie(movideId,temp[1],c,l,d,cast,rating,eraning);
			list.add(m);
			
			line=br.readLine();
			
			
		}
		
		br.close();
		
		
		return list;
	}
	
	public  boolean  allAllMoviesInDb(List<Movie> movies) throws SQLException {
		
		Connection cn=DBConnectionUtil.getConnection();
		Statement stmt = cn.createStatement();
		
	    int n=movies.size();
	    boolean status = true;
		//text file
	    //4,Student of the Year,Comedy,Hindi,2012-10-12,6.7,50.7,Varun Dhavan ,Alia Bhatt
	    
	    
		for(Movie m:movies) {
			int movieId=m.getMovieId();
			String name=m.getMovieName();
			Category c=m.getMovieType();
			String mtype=c.getCategory();
			Language l=m.getLanguage();
			String lang=l.getLanguage();
			Date d=m.getReleaseDate();
			
			String query="insert into movielist VALUES ( " + movieId + ",'"+name+"','"+mtype+"','"+lang+"','"+d+"')";
			status = status  & stmt.execute(query);
		
		}
		
		return status;
		
	}
	
	
	
	void addMovie(Movie movie,List<Movie> mList){   //add new movie to list
	mList.add(movie);		
	}
	
	
	
public  void  serializeMovies(List<Movie> movies, String fileName) {  //Serialize the movie list to a file
	
	try
    {
        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(movies);
        oos.close();
        fos.close();
    } 
    catch (IOException ioe) 
    {
        ioe.printStackTrace();
    }
	
	
	
	
}

public  List<Movie> deserializeMovie(String fileName){   //deserialize the movie list from a file
	
	List<Movie> mList;
	try
    {
        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        mList = (ArrayList) ois.readObject();

        ois.close();
        fis.close();
    } 
    catch (IOException ioe) 
    {
        ioe.printStackTrace();
        return null;
    } 
    catch (ClassNotFoundException c) 
    {
        System.out.println("Class not found");
       c.printStackTrace();
        return null;
    }
	
	
	//System.out.println(mList);
     
	
	return mList;
	
	
}
	

public  List<Movie> getMoviesRealeasedInYear(List<Movie> list,int year){
	
	List<Movie> mList=new ArrayList<>();
	
	for(Movie m:list) {
		Date d=m.getReleaseDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d);
		int y = cal.get(Calendar.YEAR);
		
		System.out.println(y);
		if(y==year) {
			mList.add(m);
		}
		
	}
	
	
	
	return mList;
}
	

public  List<Movie> getMoviesByActor(List<Movie> list,String... actorNames){
	
	List<Movie> mList=new ArrayList<Movie>();
	
	if(actorNames.length==1) {
		//System.out.println(actorNames[0]);
		for(Movie m:list) {
			
			List<String> aclist=m.getCasting();
			
			for(String s:aclist) {
				if(s.equals(actorNames[0])) {
					
					mList.add(m);
					
				}
			}
			
			
			
		}
		
	}
	else {
		
		int size=actorNames.length;
		
		Set<Movie> s=new HashSet<>();
		
		for(Movie m:list) {
			List<String> temp=m.getCasting();
			
			for(String name:actorNames) {
				for(String cast:temp) {
					if(name.equals(cast)) {
						s.add(m);
					}
				}
				
			}
			
		}
		
	
		mList.addAll(s);
	}

	
	return mList;
}





public   void  updateRatings(Movie movie, double rating ,List<Movie> movies) {
	
	for(Movie m:movies) {
		if(m.getMovieId()==movie.getMovieId()) {
			
			m.setRating(rating);
			
			
		}
		
	}
	
}


public  void updateBusiness(Movie movie, double amount,List<Movie> movies) {
	
	for(Movie m:movies) {
		if(m.getMovieId()==movie.getMovieId()) {
			
			m.setTotalBusinessDone(amount);
			
			
		}
		
		
		
	}
	
	
	
}



public  HashMap<Language,TreeSet<Movie>> businessDone(List<Movie> mList,double amount){
	HashMap<Language, TreeSet<Movie>> list = new HashMap<>();
	
	for (Movie m:mList) {
		if (m.getTotalBusinessDone()>amount) {
			if (list.containsKey(m.getLanguage()))
				list.get(m.getLanguage()).add(m);
			else {
				TreeSet<Movie> treeArr = new TreeSet<>(new Compare());
				treeArr.add(m);
				list.put(m.getLanguage(),treeArr);
			}
		}
	}
	return list;
}



	
	
	
	
	
	
}
