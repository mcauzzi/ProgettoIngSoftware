package ModelPackage;

import java.util.List;

public class ArticleType {
	
	/* Fields */
	String Name;
	String Description;
	String Sport;
	List<String> Materials;
	
	ArticleType(String name, String description, String sport, List<String> materials){
		Name=name;
		Description=description;
		Sport=sport;
		Materials=materials;
	}
	
}
