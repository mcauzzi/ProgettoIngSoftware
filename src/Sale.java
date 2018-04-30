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
	private Client Buyer;
	private MediaFormat[] Products;
	private BigDecimal TotalPrice;
	private Date SaleDate;
	private InetAddress ClientIP;
	private PaymentMethod PayMethod;
	private DeliveryMethod DelMethod;
	
	Sale(Client buyer, MediaFormat[] products, InetAddress ip, PaymentMethod payMethod, DeliveryMethod delMethod){
		Buyer=buyer;
		Products=products;
		ClientIP=ip;
		payMethod=payMethod;
		DelMethod=delMethod;
		SaleDate=new Date();
		SaleDate.setTime(Instant.now().toEpochMilli());
	}
}
