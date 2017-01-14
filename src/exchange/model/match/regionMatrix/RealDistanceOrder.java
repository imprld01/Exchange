package exchange.model.match.regionMatrix;

import exchange.model.match.Region;

public class RealDistanceOrder implements RegionMatrixSet {

	// prepare the matrix with the order below(switch).
	private int matrix[][] = {
								{}, {}, {}, {},
								{}, {}, {}, {},
								{}, {}, {}, {},
								{}, {}, {}, {}
							};
	
	public int [] getRegionMatrix(Region region){
		
		return matrix[region.ordinal()];
		
		/*
		switch(region){
		case KEELUNG: return matrix[0];
		case TAIPEI: return matrix[1];
		case TAOYUAN: return matrix[2];
		case HSINCHU: return matrix[3];
		case MIAOLI: return matrix[4];
		case TAICHUNG: return matrix[5];
		case CHANGHUA: return matrix[6];
		case NANTOU: return matrix[7];
		case YUNLIN: return matrix[8];
		case CHIAYI: return matrix[9];
		case TAINAN: return matrix[10];
		case KAOHSIUNG: return matrix[11];
		case PINGTUNG: return matrix[12];
		case ILAN: return matrix[13];
		case HUALIEN: return matrix[14];
		case TAITUNG: return matrix[15];
		}
		
		return null;
		*/
	}
}