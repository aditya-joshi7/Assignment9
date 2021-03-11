package movie.details.projet;

import java.io.Serializable;

public class Category implements Serializable {
    private String category;

    public Category() {
    	
    	
    }
	public Category(String category) {
		super();
		this.category = category;
	}
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
    
}
