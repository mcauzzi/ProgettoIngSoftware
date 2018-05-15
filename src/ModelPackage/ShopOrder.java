package ModelPackage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ShopOrder {
	
	/* Fields */
	private Shop Receiver;
	private UUID OrderCode;
	private Date date;
	private List<Article> Articles;
	private List<Integer> Quantity;
	private BigDecimal TotalPrice;
	
}
