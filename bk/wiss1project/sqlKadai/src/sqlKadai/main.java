package sqlKadai;

//SQLのパッケージをインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class main {

	// ドライバーのクラス名
	private static final String POSTGRES_DRIVER = "org.postgresql.Driver";
	// JDMC接続先情報
	private static final String JDBC_CONNECTION = "jdbc:postgresql://localhost:5433/postgres";
	// ユーザー名
	private static final String USER = "postgres";
	// パスワード
	private static final String PASS = "hirane";

	public static kadai01 Kadai01 = new kadai01();

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		Connection connection = null;

		try {
			// データベースに接続する準備。
			// Class.forName()メソッドにJDBCドライバ名を与えJDBCドライバをロード
			Class.forName(POSTGRES_DRIVER);

			// 接続先の情報。引数:「JDMC接続先情報」,「ユーザー名」,「パスワード」
			connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);

			Statement stmt = connection.createStatement();

			/** 課題１ **/
			Kadai01.befor(stmt);
			Kadai01.after(stmt);
			Kadai01.check();

			connection.close();

			// forName()で例外発生
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

			// getConnection()で例外発生
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (connection != null) {
					// データベースを切断
					connection.close();
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
