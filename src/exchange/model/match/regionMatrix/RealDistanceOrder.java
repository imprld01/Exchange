package exchange.model.match.regionMatrix;

import exchange.model.match.Area;
import exchange.model.match.Region;

public class RealDistanceOrder implements RegionMatrixSet {
	
	private Area matrix[][] = {
			// 基隆
			{	new Area(0, "基隆"), new Area(1, "台北"), new Area(2, "桃園"), new Area(2, "宜蘭"),
				new Area(3, "新竹"), new Area(4, "苗栗"), new Area(4, "花蓮"), new Area(5, "台中"),
				new Area(6, "彰化"), new Area(6, "南投"), new Area(7, "雲林"), new Area(7, "台東"),
				new Area(8, "嘉義"), new Area(9, "台南"), new Area(10, "高雄"), new Area(11, "屏東") },
			// 台北
			{	new Area(0, "台北"), new Area(1, "基隆"), new Area(1, "桃園"), new Area(1, "宜蘭"),
				new Area(2, "新竹"), new Area(3, "苗栗"), new Area(3, "花蓮"), new Area(4, "台中"),
				new Area(5, "彰化"), new Area(5, "南投"), new Area(6, "雲林"), new Area(6, "台東"),
				new Area(7, "嘉義"), new Area(8, "台南"), new Area(9, "高雄"), new Area(10, "屏東") },
			// 桃園
			{	new Area(0, "桃園"), new Area(1, "台北"), new Area(1, "新竹"), new Area(2, "基隆"),
				new Area(2, "宜蘭"), new Area(2, "苗栗"), new Area(3, "台中"), new Area(4, "彰化"),
				new Area(4, "南投"), new Area(4, "花蓮"), new Area(5, "雲林"), new Area(6, "嘉義"),
				new Area(7, "台南"), new Area(7, "台東"), new Area(8, "高雄"), new Area(9, "屏東") },
			// 新竹
			{	new Area(0, "新竹"), new Area(1, "桃園"), new Area(1, "苗栗"), new Area(2, "台北"),
				new Area(2, "台中"), new Area(3, "基隆"), new Area(3, "彰化"), new Area(3, "南投"),
				new Area(3, "宜蘭"), new Area(4, "雲林"), new Area(5, "嘉義"), new Area(5, "花蓮"),
				new Area(6, "台南"), new Area(7, "高雄"), new Area(7, "台東"), new Area(8, "屏東") },
			// 苗栗
			{	new Area(0, "苗栗"), new Area(1, "新竹"), new Area(1, "台中"), new Area(2, "桃園"),
				new Area(2, "彰化"), new Area(2, "南投"), new Area(3, "台北"), new Area(3, "雲林"),
				new Area(4, "基隆"), new Area(4, "嘉義"), new Area(4, "宜蘭"), new Area(5, "台南"),
				new Area(6, "高雄"), new Area(6, "花蓮"), new Area(7, "屏東"), new Area(9, "台東") },
			// 台中
			{	new Area(0, "台中"), new Area(1, "苗栗"), new Area(1, "彰化"), new Area(1, "南投"),
				new Area(2, "新竹"), new Area(2, "雲林"), new Area(3, "桃園"), new Area(3, "嘉義"),
				new Area(4, "台北"), new Area(4, "台南"), new Area(5, "基隆"), new Area(5, "高雄"),
				new Area(5, "宜蘭"), new Area(5, "花蓮"), new Area(6, "屏東"), new Area(8, "台東") },
			// 彰化
			{	new Area(0, "彰化"), new Area(1, "台中"), new Area(1, "南投"), new Area(1, "雲林"),
				new Area(2, "苗栗"), new Area(2, "嘉義"), new Area(3, "新竹"), new Area(3, "台南"),
				new Area(4, "桃園"), new Area(4, "高雄"), new Area(5, "台北"), new Area(5, "屏東"),
				new Area(5, "花蓮"), new Area(6, "基隆"), new Area(6, "宜蘭"), new Area(8, "台東") },
			// 南投
			{	new Area(0, "南投"), new Area(1, "台中"), new Area(1, "彰化"), new Area(2, "苗栗"),
				new Area(2, "雲林"), new Area(3, "新竹"), new Area(3, "嘉義"), new Area(4, "桃園"),
				new Area(5, "台南"), new Area(4, "花蓮"), new Area(5, "台北"), new Area(5, "高雄"),
				new Area(6, "基隆"), new Area(6, "屏東"), new Area(6, "宜蘭"), new Area(7, "台東") },
			// 雲林
			{	new Area(0, "雲林"), new Area(1, "彰化"), new Area(1, "嘉義"), new Area(2, "台中"),
				new Area(2, "南投"), new Area(2, "台南"), new Area(3, "苗栗"), new Area(3, "高雄"),
				new Area(4, "新竹"), new Area(4, "屏東"), new Area(5, "桃園"), new Area(6, "台北"),
				new Area(6, "花蓮"), new Area(7, "基隆"), new Area(7, "宜蘭"), new Area(7, "台東") },
			// 嘉義
			{	new Area(0, "嘉義"), new Area(1, "雲林"), new Area(1, "台南"), new Area(2, "彰化"),
				new Area(2, "高雄"), new Area(3, "台中"), new Area(3, "南投"), new Area(3, "屏東"),
				new Area(4, "苗栗"), new Area(5, "新竹"), new Area(6, "桃園"), new Area(6, "台東"),
				new Area(7, "台北"), new Area(7, "花蓮"), new Area(8, "基隆"), new Area(8, "宜蘭") },
			// 台南
			{	new Area(0, "台南"), new Area(1, "嘉義"), new Area(1, "高雄"), new Area(2, "雲林"),
				new Area(2, "屏東"), new Area(3, "彰化"), new Area(4, "台中"), new Area(4, "南投"),
				new Area(5, "苗栗"), new Area(5, "台東"), new Area(6, "新竹"), new Area(7, "桃園"),
				new Area(8, "台北"), new Area(8, "花蓮"), new Area(9, "基隆"), new Area(9, "宜蘭") },
			// 高雄
			{	new Area(0, "高雄"), new Area(1, "台南"), new Area(1, "屏東"), new Area(2, "嘉義"),
				new Area(3, "雲林"), new Area(4, "彰化"), new Area(4, "台東"), new Area(5, "台中"),
				new Area(5, "南投"), new Area(6, "苗栗"), new Area(7, "新竹"), new Area(7, "花蓮"),
				new Area(8, "桃園"), new Area(9, "台北"), new Area(9, "宜蘭"), new Area(10, "基隆") },
			// 屏東
			{	new Area(0, "屏東"), new Area(1, "高雄"), new Area(1, "台南"), new Area(3, "嘉義"),
				new Area(3, "台東"), new Area(4, "雲林"), new Area(5, "彰化"), new Area(6, "台中"),
				new Area(6, "南投"), new Area(6, "花蓮"), new Area(7, "苗栗"), new Area(8, "新竹"),
				new Area(8, "宜蘭"), new Area(9, "桃園"), new Area(10, "台北"), new Area(11, "基隆") },
			// 宜蘭
			{	new Area(0, "宜蘭"), new Area(1, "台北"), new Area(2, "基隆"), new Area(2, "桃園"),
				new Area(2, "花蓮"), new Area(3, "新竹"), new Area(4, "苗栗"), new Area(5, "台中"),
				new Area(5, "台東"), new Area(6, "彰化"), new Area(6, "南投"), new Area(7, "雲林"),
				new Area(8, "嘉義"), new Area(8, "屏東"), new Area(9, "台南"), new Area(9, "高雄") },
			// 花蓮
			{	new Area(0, "花蓮"), new Area(2, "宜蘭"), new Area(3, "台北"), new Area(3, "台東"),
				new Area(4, "基隆"), new Area(4, "桃園"), new Area(4, "南投"), new Area(5, "桃園"),
				new Area(5, "台中"), new Area(5, "彰化"), new Area(6, "苗栗"), new Area(6, "雲林"),
				new Area(6, "屏東"), new Area(7, "嘉義"), new Area(7, "高雄"), new Area(8, "台南") },
			// 台東
			{	new Area(0, "台東"), new Area(3, "屏東"), new Area(3, "花蓮"), new Area(4, "高雄"),
				new Area(5, "台南"), new Area(5, "宜蘭"), new Area(6, "台北"), new Area(6, "嘉義"),
				new Area(7, "基隆"), new Area(7, "桃園"), new Area(7, "南投"), new Area(7, "雲林"),
				new Area(8, "新竹"), new Area(8, "台中"), new Area(8, "彰化"), new Area(9, "苗栗") } };
	
	public Area[] getRegionMatrix(Region region){
		
		return matrix[region.ordinal()];
	}
}