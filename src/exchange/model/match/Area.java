package exchange.model.match;

public class Area{ //依照表格排序地區  (魔術數字)
	
	private int distance;
	private String placeName;
	
	public Area(int distance, String placeName) {
		
		this.distance=distance;
		this.placeName=placeName;
	}
	
	public int getDistance() {
		
		return distance;
	}

	public String getPlaceName() {
		
		return placeName;
	}
}