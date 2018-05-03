import java.math.BigDecimal;
import java.net.InetAddress;
import java.time.Instant;
import java.util.Date;

enum PaymentMethod {
BANK_TRANSFER, CREDIT_CARD, PAYPAL
}

enum DeliveryMethod{
	COURIER, POSTAL_SERVICE
}

public class Sale {
	
	/* Fields */
	private Client Buyer;
	private MediaFormat[] Products;
	private BigDecimal TotalPrice;
	private Date SaleDate;
	private InetAddress ClientIP;
	private PaymentMethod PayMethod;
	private DeliveryMethod DelMethod;
	
	/* Getters */
	public Client getBuyer() {
		return Buyer;
	}

	public MediaFormat[] getProducts() {
		return Products;
	}

	public BigDecimal getTotalPrice() {
		return TotalPrice;
	}

	public Date getSaleDate() {
		return SaleDate;
	}

	public InetAddress getClientIP() {
		return ClientIP;
	}

	public PaymentMethod getPayMethod() {
		return PayMethod;
	}

	public DeliveryMethod getDelMethod() {
		return DelMethod;
	}

	/* Constructor */
	Sale(Client buyer, MediaFormat[] products, InetAddress ip, PaymentMethod payMethod, DeliveryMethod delMethod){
		Buyer=buyer;
		Products=products;
		ClientIP=ip;
		PayMethod=payMethod;
		DelMethod=delMethod;
		SaleDate=new Date();
		SaleDate.setTime(Instant.now().toEpochMilli());
	}
	
	/* Functions */
	void GetFromDB() {
		
	}
	
	void SaveToDB() {
		
	}
}
