package movie.details.projet;

import java.io.Serializable;

public class Language implements Serializable {
private String language;

public Language() {
	
	
}

public Language(String language) {
	super();
	this.language = language;
}

public String getLanguage() {
	return language;
}

public void setLanguage(String language) {
	this.language = language;
}
}
