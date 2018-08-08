package ModelPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class WarehouseInsOuts {
	/*Fields*/
	List<Ingress> Ins;
	List<Egress> Outs;
	ArticleList StoredArticles;
	
	public WarehouseInsOuts() {
		Ins=new ArrayList<Ingress>();
		Outs=new ArrayList<Egress>();
		StoredArticles=new ArticleList();
	}
	
	public void AddIn(ArticleList art, Date date, List<String> articlePos ) {
		Ins.add(new Ingress(UUID.randomUUID(), art, date, articlePos));
		for(int i=0;i<art.Size();i++) {
			StoredArticles.AddElement(art.GetByIndex(i));
		}
	}
	
	public void AddOut(ArticleList art, Date date, String note, Shop receiver, String shipper) {
		Outs.add(new Egress(date, note, art, receiver, shipper));
		
		for(int i=0;i<art.Size();i++) {
			StoredArticles.RemoveByCode(art.GetByIndex(i).getUniCode());
		}
	}
}
