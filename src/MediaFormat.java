import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.swing.ImageIcon;

final class MediaType{
	protected String Type;
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
	
	/*Fields*/
	private UUID UniCode;
	private MediaType Type;
	private String Title;
	private String[] SongTitles;
	
	private BigDecimal Price;
	public void setPrice(BigDecimal price) {
		Price = price;
	}

	private Date ArrivalDate;
	private String Artist;
	private String Description;
	private String Genre;
	private Musician[] MusList;
	private String[] Instruments;
	private ImageIcon AlbumCover;
	
	/*Getters*/
	public UUID getUniCode() {
		return UniCode;
	}
	public MediaType getType() {
		return Type;
	}
	public String getTitle() {
		return Title;
	}
	public String getSongTitle(int i) {
		return SongTitles[i];
	}
	public BigDecimal getPrice() {
		return Price;
	}
	public Date getArrivalDate() {
		return ArrivalDate;
	}
	public String getArtist() {
		return Artist;
	}
	public String getDescription() {
		return Description;
	}
	public String getGenre() {
		return Genre;
	}
	public Musician getMusList(int i) {
		return MusList[i];
	}
	public String[] getInstruments() {
		return Instruments;
	}
	public ImageIcon getAlbumCover() {
		return AlbumCover;
	}
	
	/*Constructor*/
	MediaFormat(){
		UniCode=UniCode.randomUUID();	
	}
	
	MediaFormat(String title, String mediaType, String[] songTitles, Date date, 
				String artist, String description, String genre, 
				Musician[] musList, ImageIcon albumCover, String[] instruments){
		UniCode=UniCode.randomUUID();	
		Title=title;
		Type=new MediaType(mediaType);
		SongTitles=songTitles;
		ArrivalDate=date;
		Artist=artist;
		Description=description;
		Genre=genre;
		MusList=musList;
		Instruments=instruments;
		AlbumCover=albumCover;
	}
	
	
	
}
