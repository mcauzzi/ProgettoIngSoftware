package ModelPackage;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class ArticleList {

	/* Fields */
	List<Article> ArtList;
	
	/* Constructor */
	ArticleList(){
		ArtList=new ArrayList<Article>();
	}
	
	/* Functions */
	void AddElement(String title, ArticleType type, Date date, BigDecimal price) {
		ArtList.add(new Article(title, type, date, price));
	}
	
	void AddElement(Article art) {
		ArtList.add(art);
	}
	
	void RemoveByCode(UUID code) {
		ArtList.remove(GetIndexOfUUID(code));
	}
	
	int GetIndexOfUUID(UUID code) {
		for(int i=0;i<ArtList.size();i++) {
			if(ArtList.get(i).getUniCode()==code) {
				return i;
			}
		}
		
		return -1;
	}
	
	public Article GetByIndex(int index) {
		return ArtList.get(index);
	}
	
	public int Size() {
		return ArtList.size();
	}
}
