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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/*
 * TableHeaderの取得
 * @author shima
 * 2020/10/02
 * 1.1
 */

// 共通関数クラス
public class W01SelectTableHeader {
    /**
     * クラス概要
     *
     * @author shima
     * @version 1.0.0
     */
    public static WISS1CommonUtil commUtil = new WISS1CommonUtil();


    public String main() {
        Connection conn = null;
        ResultSet rset = null;

        //接続文字列
        String url = commUtil.getProperty("DBURL"); // 接続パス
        String user = commUtil.getProperty("DBUSER"); // ログインID
        String password = commUtil.getProperty("DBPASS"); // ログインパスワード

        try {
            // DBへのコネクションを作成する
            conn = DriverManager.getConnection(url, user, password);

            Scanner sc = new Scanner(System.in);

            //3つのヘッダのどれかを選択させる
            System.out.println(W01CommonConst.TBL_NM_ONE);
            System.out.println(W01CommonConst.TBL_NM_TWO);
            System.out.println(W01CommonConst.TBL_NM_THREE);

            System.out.println("取得したいテーブルを選択してください：");

            int code = sc.nextInt();

            switch (code) {

            case 01:
                // 社員情報テーブルの値を取得するメソッドを呼び出す
                String num01 = getTableData(conn, W01CommonConst.TBL_NM_EMPLOYEE, rset);
                if (num01.equals(1)) {
                    return "1";
                }
                return "0";

            case 02:
                // 部署コードテーブルの値を取得するメソッドを呼び出す
                String num02 = getTableData(conn, W01CommonConst.TBL_NM_DIVISION, rset);
                if (num02.equals(1)) {
                    return "1";
                }
                return "0";

            case 03:
                //役職コードテーブルの値を取得するメソッドを呼び出す
                String num03 = getTableData(conn, W01CommonConst.TBL_NM_POST, rset);
                if (num03.equals(1)) {
                    return "1";
                }
                return "0";

            // 1~3以外ならバッチに戻り値1を返す
            default:
                System.out.println("01~03で入力してください");

                //table呼び出し失敗メッセージ
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E02", "TBL呼び出し");
                return "1";
            }

            //DB接続失敗時に異常終了のメッセージを表示「
        } catch (SQLException e) {
            e.printStackTrace();
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E02", "DB接続");
            return "1";

        } finally {

            //接続を切断する
            if (rset != null) {
                try {
                    rset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    W01CommonUtil messege = new W01CommonUtil();
                    messege.outMessage("E02", "DB接続");
                    return "1";
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    W01CommonUtil messege = new W01CommonUtil();
                    messege.outMessage("E02", "DB接続");
                    return "1";
                }
            }
        }
    }

    /**
     * Tableの情報を取得しCsvファイルを出力すむメソッドを呼び出す
     * @param
     * @return
     */
    private static String getTableData(Connection conn, String table, ResultSet rset)
            throws SQLException {
        DatabaseMetaData dbmd = conn.getMetaData();

        String types[] = { "TABLE" };
        //渡されたカタログ、スキーマ、テーブル名のパターンで使用可能なテーブルの記述を取得します。
        rset = dbmd.getTables(null, null, table, types);

        String num = exportCsv(rset, dbmd);
        if (num.equals(1)) {
            return "1";
        }
        return "0";
    }

    /**
     * テーブルヘッダーをCsvファイルへ出力するメソッド
     * @param
     * @return
     */
    private static String exportCsv(ResultSet rs, DatabaseMetaData dbmd) {

        //現在時刻の取得
        Date date = new Date();

        //フォーマットを設定
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");

        //日時情報を指定フォーマットの文字列で取得
        String display = format.format(date);

        // 出力ファイルの作成
        //		FileWriter f;
        try {

            while (rs.next()) {
                //テーブル名を取得
                String tableName = rs.getString("TABLE_NAME");

                //取得したColumnsを格納していく変数
                StringBuffer sb = new StringBuffer();

                //ファイル格納先
                String FilePlace = commUtil.getProperty("PATH");

                //指定のファイル名を生成
                File fInputCsv = new File(FilePlace + tableName + "header" + display + ".csv");

                //指定された名前のファイルに書き込むためのファイル出力ストリームを作成します。
                FileOutputStream FileOutput = new FileOutputStream(fInputCsv);
                PrintWriter pWriter =
                        new PrintWriter(new BufferedWriter(new OutputStreamWriter(FileOutput)));

                //tableのヘッダ内容を取得
                ResultSet rsColumns = dbmd.getColumns(null, null, tableName, "%");

                //一件ずつループでヘッダーを取得
                while (rsColumns.next()) {

                    //指定されたヘッダーと、区切りのカンマを格納
                    sb.append(rsColumns.getString("COLUMN_NAME"));
                    sb.append(",");

                }

                String columName = sb.toString();
                //最後のカンマを除く処理
                columName = columName.substring(0, columName.length() - 1);

                //ファイルへ書き出し
                pWriter.write(columName);

                // ファイルに書き出し閉じる
                pWriter.close();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();

            //出力失敗メッセージ
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E02", "CSVファイル出力");
            return "1";

        }
        //正常終了メッセージ
        W01CommonUtil messege = new W01CommonUtil();
        messege.outMessage("I01", "ファイルへの出力");
        return "0";
    }

}
