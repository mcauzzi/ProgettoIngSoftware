package ModelPackage;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.swing.ImageIcon;


public class Article {
	
	/* Fields */
	private UUID UniCode;
	private ArticleType Type;
	
	private BigDecimal Price;
	public void setPrice(BigDecimal price) {
		Price = price;
	}
	private Date ProductionDate;
	
	/* Getters */
	public UUID getUniCode() {
		return UniCode;
	}
	public ArticleType getType() {
		return Type;
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public Date getProductionDate() {
		return ProductionDate;
	}
	
	/* Constructor */
	Article(String title, ArticleType type, Date date, BigDecimal price){
		UniCode=UniCode.randomUUID();	
		Type=type;
		ProductionDate=date;
		Price=price;
	}
	
	/* Functions */
}
