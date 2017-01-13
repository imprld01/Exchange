package exchange.model.account;

import java.sql.SQLException;
import java.util.Date;
import java.util.Random;

import exchange.model.database.DataBaseAdmin;

public class Test {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		// Date recentLog = new Date();
		// java.sql.Date now = new java.sql.Date(recentLog.getTime());
		// System.out.println(now);
		 DataBaseAdmin db = new DataBaseAdmin();
		 db.changeDBAccount("root", "narutoap12");
		AccountManager am = new AccountManager();

		// getAccount method success
		// System.out.println(am.getAccount("10567026"));
		// System.out.println(am.getAccount("1234567"));
		// System.out.println(am.getAccount("jec88899"));

		// 測試失敗
//		String area[] = { "基隆", "台北", "桃園", "新竹", "苗栗", "台中", "彰化", "南投", "雲林", "嘉義", "台南", "高雄", "屏東", "宜蘭", "花蓮",
//				"台東" };
//
//		for (int i = 150; i < 5150; i++) {
//			am.addAccount("test" + i, "test" + i, "test" + i, "test" + i, true, "@test", "1111-11-11",
//					area[(int) (Math.random() * 16)]);
//		}

		// System.out.println();

		// setProfile(id, profile) success
		// Profile profile = new Profile("nick2", "email1515", "region882");
		// am.setProfile("10567026", profile);

		// setSecret(secret) success
		// Secret secret = new Secret("10567026", "kc567894");
		// am.setSecret(secret);

		// getAllUserId() success
		// System.out.println(am.getAllUserId());

		// isSkillFull(id) success
		// System.out.println(am.isSkillFull("0"));

		// getregion(id) success
		// System.out.println(am.getRegion("1234567"));

		// isValid() success
		// System.out.println(am.isValid("788845153"));
		// System.out.println(am.isValid("10567026"));
		// System.out.println(am.isValid(""));
		am.addAccount("16511", "15111", "16516161", "65151", false, "156131", "2016-01-11", "台北");
	}

}
