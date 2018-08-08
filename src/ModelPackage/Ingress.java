package ModelPackage;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Ingress {
	
	/* Fields */
	private UUID UniCode;
	private Date Date;
	private ArticleList IngressArticles;
	private List<String> ArticlePos;
	
	public Ingress(UUID uniCode, ArticleList ingressArticles, Date date, List<String> articlePos) {
		UniCode=uniCode;
		Date=date;
		IngressArticles=ingressArticles;
		ArticlePos=articlePos;
	}
}
