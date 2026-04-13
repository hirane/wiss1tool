package sqlKadai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class kadai01 {

	private long beforTime = 0;
	private long afterTime = 0;

	public void befor(Statement stmt) throws SQLException {

		System.out.println("---------------" + new Object() {
		}.getClass().getEnclosingClass().getName() + "---------------");
		System.out.println("---------------" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "---------------");
		System.out.println("---------------開始---------------");

		// 処理前の時刻を取得
		long startTime = System.currentTimeMillis();

		String sql = "SELECT id FROM weather";
		ResultSet rset = stmt.executeQuery(sql);
		String sql02 = "SELECT name FROM name order by id";
		ResultSet rset02 = stmt.executeQuery(sql);
		String sql03 = "SELECT id FROM weathe order by idr";
		ResultSet rset03 = stmt.executeQuery(sql);
		String sql04 = "SELECT id FROM weather order by id";
		ResultSet rset04 = stmt.executeQuery(sql);
		String sql05 = "SELECT id FROM weather order by id";
		ResultSet rset05 = stmt.executeQuery(sql);
		while (rset.next()) {
			rset02.next();
			rset03.next();
			rset04.next();
			rset05.next();

			System.out.println(rset.getString(1) + " name:"+  rset02.getString(2) + " name:"+  rset03.getString(2) + rset04.getString(2)
					+ rset05.getString(2));
		}
		rset.close();

		// 処理後の時刻を取得
		long endTime = System.currentTimeMillis();

		System.out.println("開始時刻：" + startTime + " ms");
		System.out.println("終了時刻：" + endTime + " ms");
		System.out.println("処理時間：" + (endTime - startTime) + " ms");
		beforTime = endTime - startTime;
		System.out.println("---------------終了---------------");
	}

	public void after(Statement stmt) throws SQLException {

		System.out.println("---------------" + new Object() {
		}.getClass().getEnclosingClass().getName() + "---------------");
		System.out.println("---------------" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "---------------");
		System.out.println("---------------開始---------------");

		// 処理前の時刻を取得
		long startTime = System.currentTimeMillis();

		String sql = "SELECT name FROM weather";
		ResultSet rset = stmt.executeQuery(sql);
		while (rset.next()) {
			System.out.println(rset.getString(1));
		}
		rset.close();

		// 処理後の時刻を取得
		long endTime = System.currentTimeMillis();

		System.out.println("開始時刻：" + startTime + " ms");
		System.out.println("終了時刻：" + endTime + " ms");
		System.out.println("処理時間：" + (endTime - startTime) + " ms");
		afterTime = endTime - startTime;
		System.out.println("---------------終了---------------");

	}

	public void check() {
		if (beforTime > afterTime) {
			System.out.println("性能改善成功");
		} else {
			System.out.println("性能改善失敗");
		}
	}

}
