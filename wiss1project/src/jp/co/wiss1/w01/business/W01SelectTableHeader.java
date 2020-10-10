package jp.co.wiss1.w01.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonUtil;

public class W01SelectTableHeader {

	public static void main(String[] args) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		//接続文字列
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String user = "postgres";
		String password = "wanima123";

		try {
			// DBへのコネクションを作成する
			conn = DriverManager.getConnection(url, user, password);

			Scanner sc = new Scanner(System.in);

			System.out.println("01：社員情報");
			System.out.println("02：部署コード");
			System.out.println("03：役職コード");

			System.out.println("取得したいテーブルを選択してください：");

			int code = sc.nextInt();

			switch (code) {

			case 01:
				// 社員情報テーブルの値を取得するメソッドを呼び出す
				division_code(conn);
				break;

			case 02:
				// 部署コードテーブルの値を取得するメソッドを呼び出す
				post_code(conn);
				break;

			case 03:
				// 役職コードテーブルの値を取得するメソッドを呼び出す
				t_employee_datas(conn);
				break;

			// 1~3以外ならバッチに戻り値1を返す
			default:
				System.err.println(1);
				System.out.println("01~03で入力してください");

				//table呼び出し失敗メッセージ
				W01CommonUtil messege = new W01CommonUtil();
				messege.outMessage("E02", "TBL呼び出し");
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {

			//接続を切断する
			if (rset != null) {
				rset.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public static void division_code(Connection conn) throws SQLException {
		DatabaseMetaData dbmd = conn.getMetaData();
		String table = "division_code";

		String types[] = { "TABLE" };
		ResultSet rs = dbmd.getTables(null, null, table, types);

		exportCsv(table, rs, dbmd);
	}

	public static void post_code(Connection conn) throws SQLException {
		DatabaseMetaData dbmd = conn.getMetaData();
		String table = "post_code";

		String types[] = { "TABLE" };
		ResultSet rs = dbmd.getTables(null, null, table, types);

		exportCsv(table, rs, dbmd);
	}

	public static void t_employee_datas(Connection conn) throws SQLException {
		DatabaseMetaData dbmd = conn.getMetaData();
		String table = "t_employee_datas";

		String types[] = { "TABLE" };
		ResultSet rs = dbmd.getTables(null, null, table, types);

		exportCsv(table, rs, dbmd);
	}

	public static void exportCsv(String table, ResultSet rs, DatabaseMetaData dbmd) throws SQLException {

		//現在時刻の取得
		Date date = new Date();

		//フォーマットを設定
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

		//日時情報を指定フォーマットの文字列で取得
		String display = format.format(date);

		// 出力ファイルの作成
		//		FileWriter f;
		try {
			//指定のファイル名を生成
			File file = new File("C:\\wiss1workspeas\\" + table + "header" + display + ".csv");

			PrintWriter p = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "Shift-JIS")));

			while (rs.next()) {
				String tableName = rs.getString("TABLE_NAME");
				ResultSet rs2 = dbmd.getColumns(null, null, tableName, "%");

				while (rs2.next()) {
					//ファイルに書き込む
					p.write(rs2.getString("COLUMN_NAME") + ",");
				}
			}
			// ファイルに書き出し閉じる
			p.close();
		} catch (IOException e) {
			e.printStackTrace();
			//出力失敗メッセージ
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E02", "CSV出力");
		}
		//正常終了メッセージ
		W01CommonUtil messege = new W01CommonUtil();
		messege.outMessage("I01", "正常終了しました。");
	}

}
