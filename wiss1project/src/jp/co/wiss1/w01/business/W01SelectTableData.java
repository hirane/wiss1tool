package jp.co.wiss1.w01.business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * テーブルからデータを取得、CSVファイル出力に出力するクラス
 *
 * @author nishio
 *
 */

public class W01SelectTableData {

	/**
	 * mainメソッド
	 * divisionCode、postCode、tEmployeeDatasメソッドを呼び出します
	 */
	public static int main(String[] args) throws Exception {

		System.out.println("01：社員情報");
		System.out.println("02：部署コード");
		System.out.println("03：役職コード");

		System.out.println("取得したいテーブルを選択してください：");
		Scanner scan = new Scanner(System.in);
		String num = scan.next();

		switch (num) {
		case "01":
			// 社員情報テーブルの値を取得するメソッドを呼び出す
			int returnValue1 = tEmployeeDatas();
			// 異常終了の場合は1を返す
			if (returnValue1 == 1) {
				return 1;
			}
			break;

		case "02":
			// 部署コードテーブルの値を取得するメソッドを呼び出す
			int returnValue2 = divisionCode();
			// 異常終了の場合は1を返す
			if (returnValue2 == 1) {
				return 1;
			}
			break;

		case "03":
			// 役職コードテーブルの値を取得するメソッドを呼び出す
			int returnValue3 = postCode();
			// 異常終了の場合は1を返す
			if (returnValue3 == 1) {
				return 1;
			}
			break;

		// 1~3以外ならバッチに戻り値1を返す
		default:
			System.err.println(1);
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E02", "TBL呼び出し");
			// 異常終了の場合は1を返す
			return 1;
		}
		// 正常終了の場合は0を返す
		return 0;
	}

	/**
	 * divisionCodeメソッド
	 * 部署コードテーブルのデータを取得するメソッド
	 *
	 */
	public static int divisionCode() throws SQLException {

		// 変数定義
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// DB接続情報を設定する
		String path = "jdbc:postgresql://localhost:5434/postgres"; // 接続パス
		String id = "postgres"; // ログインID
		String pw = "postgres"; // ログインパスワード

		// SQL文を定義する
		String sql = "SELECT * FROM division_code";
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E01", "TBL接続");
			// 異常終了の場合は1を返す
			return 1;
		}
		try {
			// DBへのコネクションを作成する
			conn = DriverManager.getConnection(path, id, pw);

			// 実行するSQL文とパラメータを指定する
			ps = conn.prepareStatement(sql);

			// SELECTを実行する
			rs = ps.executeQuery();

			// CSVファイルにネームするテーブル名を取得
			String tbName = "division_code";

			System.out.println(tbName);

			// 取得したいデータをファイル出力メソッドに渡す
			int returnValue = outputFile(rs, tbName);
			// 異常終了の場合は1を返す
			if (returnValue == 1) {
				return 1;
			}

		} catch (Exception ex) {
			// 例外発生時の処理
			ex.printStackTrace(); // エラー内容をコンソールに出力する
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E02", "DB接続");
			// 異常終了の場合は1を返す
			return 1;

		} finally {
			// クローズ処理
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		// 正常終了の場合は0を返す
		return 0;
	}

	/**
	 * postCodeメソッド
	 * 役職コードテーブルのデータを取得するメソッド
	 *
	 */
	public static int postCode() throws SQLException {

		// 変数定義
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// DB接続情報を設定する
		String path = "jdbc:postgresql://localhost:5434/postgres"; // 接続パス
		String id = "postgres"; // ログインID
		String pw = "postgres"; // ログインパスワード

		// SQL文を定義する
		String sql = "SELECT * FROM post_code";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E01", "TBL接続");
			// 異常終了の場合は1を返す
			return 1;
		}

		try {
			// DBへのコネクションを作成する
			conn = DriverManager.getConnection(path, id, pw);

			// 実行するSQL文とパラメータを指定する
			ps = conn.prepareStatement(sql);

			// SELECTを実行する
			rs = ps.executeQuery();

			// CSVファイルにネームするテーブル名を取得
			String tbName = "post_code";

			System.out.println(tbName);

			// 取得したいデータをファイル出力メソッドに渡す
			int returnValue = outputFile(rs, tbName);
			// 異常終了の場合は1を返す
			if (returnValue == 1) {
				return 1;
			}
		} catch (Exception ex) {
			// 例外発生時の処理
			ex.printStackTrace(); // エラー内容をコンソールに出力する
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E02", "TBL呼び出し");
			// 異常終了の場合は1を返す
			return 1;

		} finally {
			// クローズ処理
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		// 正常終了の場合は0を返す
		return 0;
	}

	/**
	 * tEmployeeDatasメソッド
	 *  社員情報テーブルのデータを取得するメソッド
	 *
	 */
	public static int tEmployeeDatas() throws SQLException {

		// 変数定義
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// DB接続情報を設定する
		String path = "jdbc:postgresql://localhost:5434/postgres"; // 接続パス
		String id = "postgres"; // ログインID
		String pw = "postgres"; // ログインパスワード

		// SQL文を定義する
		String sql = "SELECT * FROM t_employee_datas";

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E01", "TBL接続");
			// 異常終了の場合は1を返す
			return 1;
		}

		try {
			// DBへのコネクションを作成する
			conn = DriverManager.getConnection(path, id, pw);

			// 実行するSQL文とパラメータを指定する
			ps = conn.prepareStatement(sql);

			// SELECTを実行する
			rs = ps.executeQuery();

			// CSVファイルにネームするテーブル名を取得
			String tbName = "t_employee_datas";

			System.out.println(tbName);

			// 取得したいデータをファイル出力メソッドに渡す
			int returnValue = outputFile(rs, tbName);
			if (returnValue == 1) {
				// 異常終了の場合は1を返す
				return 1;
			}
		} catch (Exception ex) {
			// 例外発生時の処理
			ex.printStackTrace(); // エラー内容をコンソールに出力する
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E02", "TBL呼び出し");
			// 異常終了の場合は1を返す
			return 1;

		} finally {
			// クローズ処理
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
			if (conn != null)
				conn.close();
		}
		// 正常終了の場合は0を返す
		return 0;
	}

	/**
	 * outputFileメソッド
	 * 取得したデータをCSVファイルに出力するメソッド
	 *
	 */
	public static int outputFile(ResultSet rs, String tbName) {

		try {

			// 現在時刻の取得
			Date now = new Date();

			// 最終更新日時表示書式定義
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

			String fileNow = sdf.format(now);

			// テーブル名と現在時刻を合わせる
			String fileName = tbName + "_data_" + fileNow;

			FileWriter fw = new FileWriter("C:\\wiss1workspeas\\" + fileName
					+ ".csv");

			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

			// 項目名を格納する配列
			ArrayList<String> clmnAry = null;
			try {
				// データの数分繰り返してファイルに書き込む
				while (rs.next()) {
					// 初回のみ項目名を取得
					if (clmnAry == null) {
						clmnAry = new ArrayList<>();
						// カラム数とカラム名を取得を取得
						ResultSetMetaData metaData = rs.getMetaData();
						int columnCount = metaData.getColumnCount();
						// カラム数分繰り返す
						for (int i = 0; i < columnCount; i++) {
							// カラム名を設定
							clmnAry.add(metaData.getColumnName(i + 1));
						}
					}
					// カラム数分繰り返す
					for (String clmn : clmnAry) {
						// ヘッダ部のデータをcsvファイルに値を入れる
						pw.print(clmn);
						pw.print(",");
					}
					pw.println();
					// カラム数分繰り返す
					for (String clmn2 : clmnAry) {
						// データ部のデータをcsvファイルに値を入れる
						pw.print(rs.getObject(clmn2 + ","));
						pw.print(",");
					}
				}
			} catch (SQLException e) {
				// 例外発生時の処理
				e.printStackTrace(); // エラー内容をコンソールに出力する
				W01CommonUtil messege = new W01CommonUtil();
				messege.outMessage("E01", "CSVファイル出力");
				// 異常終了の場合は1を返す
				return 1;
			}
			pw.close();
		} catch (IOException ex) {
			// 例外時処理
			ex.printStackTrace();
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E01", "CSVファイル出力");
			// 異常終了の場合は1を返す
			return 1;
		}

		W01CommonUtil messege = new W01CommonUtil();
		messege.outMessage("I01", "CSVファイル出力");
		// 正常終了なら0を返す
		return 0;
	}

}
