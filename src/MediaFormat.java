import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.swing.ImageIcon;

final class MediaType{
	private String Type;
	private final String DVD="DVD";
	private final String CD="CD";
	
	MediaType(String s){
		if(!s.equalsIgnoreCase(DVD) || !s.equalsIgnoreCase(CD) ) {
			throw new IllegalArgumentException();
		}
		Type=s;
	}
}

public class MediaFormat {
	private UUID UniCode;
	private MediaType Type;
	private String Title;
	private BigDecimal Price;
	private Date ArrivalDate;
	private String Artist;
	private String Description;
	private String Genre;
	private Musician MusList;
	private String[] Instruments;
	private ImageIcon AlbumCover;
	
	
}
