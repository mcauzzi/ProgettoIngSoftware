package ModelPackage;

import java.util.Date;
import java.util.List;

public class Egress {
	
	/* Fields */
	private Date EgressDate;
	private String DeliveryNote;
	private ArticleList EgressArticles;
	private Shop Receiver;
	private String Shipper;
	
	public Egress(Date date, String note, ArticleList articles, Shop receiver, String shipper) {
		EgressDate=date;
		DeliveryNote=note;
		EgressArticles=articles;
		Receiver=receiver;
		Shipper=shipper;
	}
}
