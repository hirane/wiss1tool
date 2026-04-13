package sqlKadai;

//SQLのパッケージをインポート
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class dataCreate {

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
			Class.forName(POSTGRES_DRIVER);

			// 接続先の情報。引数:「JDMC接続先情報」,「ユーザー名」,「パスワード」
			connection = DriverManager.getConnection(JDBC_CONNECTION, USER, PASS);

			Statement stmt = connection.createStatement();

			String sql = "";
			// テーブル作成
			// 初期化用
			//			String sql = "DROP TABLE id, name, address, age, birth;";
			//			stmt.executeUpdate(sql);
			//			sql = "CREATE TABLE id (  ID integer )";
			//			stmt.executeUpdate(sql);
			//			sql = "CREATE TABLE name (  ID integer, NAME varchar(10) )";
			//			stmt.executeUpdate(sql);
			//			sql = "CREATE TABLE address (  ID integer, ADDRESS varchar(10) )";
			//			stmt.executeUpdate(sql);
			//			sql = "CREATE TABLE age (  ID integer, AGE varchar(10) )";
			//			stmt.executeUpdate(sql);
			//			sql = "CREATE TABLE birth (  ID integer, BIRTH varchar(8) )";
			//			stmt.executeUpdate(sql);

			// データ挿入
			// ID
			sql = "DELETE FROM id";
			stmt.executeUpdate(sql);
			// name
			sql = "DELETE FROM name ";
			stmt.executeUpdate(sql);
			// address
			sql = "DELETE FROM address";
			stmt.executeUpdate(sql);
			// age
			sql = "DELETE FROM age ";
			stmt.executeUpdate(sql);
			// birth
			sql = "DELETE FROM birth ";
			stmt.executeUpdate(sql);
			int i = 0;
			while (i < 30000) {
				// ID
				sql = "INSERT INTO id VALUES (" + i + ")";
				stmt.executeUpdate(sql);
				// name
				sql = "INSERT INTO name VALUES (" + i + ", " + (i + 1) + ")";
				stmt.executeUpdate(sql);
				// address
				sql = "INSERT INTO address VALUES (" + i + ", " + (i + 2) + ")";
				stmt.executeUpdate(sql);
				// age
				sql = "INSERT INTO age VALUES (" + i + ", " + (i + 3) + ")";
				stmt.executeUpdate(sql);
				// birth
				sql = "INSERT INTO birth VALUES (" + i + ", " + (i + 4) + ")";
				stmt.executeUpdate(sql);

				i++;
			}

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
