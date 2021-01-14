package jp.co.wiss1.w01.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * テーブルヘッダー出力クラス
 *
 * @since 2020/10/04
 * @author h-shima
 * @version 1.0
 */

public class W01SelectTableHeader {

    static W01CommonUtil message = new W01CommonUtil();

    /**
     * テーブルヘッダー出力の主処理
     *
     * @param なし
     * @return String（0:正常終了 1:異常終了）
     */
    @SuppressWarnings("resource")
    public String selectTableHeader() {
        Connection conn = null;
        ResultSet rset = null;

        // DBへのコネクションを作成する
        conn = WISS1CommonUtil.getConnection();

        //DB接続失敗時に異常終了のメッセージを表示
        if (null == conn) {
            message.outMessage("E02", "DB接続");
            return W01CommonConst.ERROR;
        }

        Scanner sc = new Scanner(System.in);

        //3つのヘッダのどれかを選択させる
        // 1：社員情報
        message.outMessage("I00", W01CommonConst.TBL_NM_ONE);
        // 2：部署コード
        message.outMessage("I00", W01CommonConst.TBL_NM_TWO);
        // 3：役職コード
        message.outMessage("I00", W01CommonConst.TBL_NM_THREE);

        message.outMessage("I00", "取得したいテーブルを選択してください：");

        String code = sc.nextLine();

        switch (code) {

        case W01CommonConst.TBL_CH_ONE:
            return getTableData(conn, W01CommonConst.TBL_NM_EMPLOYEE, rset);

        case W01CommonConst.TBL_CH_TWO:
            return getTableData(conn, W01CommonConst.TBL_NM_DIVISION, rset);

        case W01CommonConst.TBL_CH_THREE:
            return getTableData(conn, W01CommonConst.TBL_NM_POST, rset);

        // 1から3以外ならバッチに戻り値1を返す
        default:
            message.outMessage("I04", "01から03");

            //table呼び出し失敗メッセージ
            message.outMessage("E02", "TBL呼び出し");
            return W01CommonConst.ERROR;
        }

    }

    /**
     * Tableの情報を取得しCsvファイルを出力すむメソッドを呼び出す
     * @param
     * @return
     */
    private static String getTableData(Connection conn, String table, ResultSet rset) {
        DatabaseMetaData dbmd;
        try {
            dbmd = conn.getMetaData();

            String types[] = { "TABLE" };
            //渡されたカタログ、スキーマ、テーブル名のパターンで使用可能なテーブルの記述を取得します。
            rset = dbmd.getTables(null, null, table, types);

            String num = exportCsv(rset, dbmd);
            rset.close();
            conn.close();
            if (W01CommonConst.TBL_CH_ONE.equals(num)) {
                return W01CommonConst.ERROR;
            }

        } catch (IOException | SQLException e) {
            message.outMessage("E02", "CSVファイル出力");
            return W01CommonConst.ERROR;
        }

        return W01CommonConst.SUCCESS;
    }

    /**
     * テーブルヘッダーをCsvファイルへ出力するメソッド
     * @param
     * @return
     * @throws IOException ,SQLException
     */
    private static String exportCsv(ResultSet rs, DatabaseMetaData dbmd)
            throws IOException, SQLException {
        int count = 1;
        //現在時刻の取得
        Date date = new Date();

        //フォーマットを設定
        SimpleDateFormat format = new SimpleDateFormat(W01CommonConst.DATE);

        //日時情報を指定フォーマットの文字列で取得
        String display = format.format(date);

        // 出力ファイルの作成
        //try {

        while (rs.next()) {
            //テーブル名を取得
            String tableName = rs.getString(W01CommonConst.TABLE_NAME);

            //取得したColumnsを格納していく変数
            StringBuffer sb = new StringBuffer();

            //ファイル格納先
            String filePlace = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH);

            //指定のファイル名を生成
            File fInputCsv = new File(filePlace + tableName + W01CommonConst.FILE_NM_HEADER
                    + display + W01CommonConst.CONST_EXTENSION_CSV);

            //指定された名前のファイルに書き込むためのファイル出力ストリームを作成します。
            PrintWriter pWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fInputCsv), W01CommonConst.CONST_CHAR_CODE_UTF8)));

            //tableのヘッダ内容を取得
            ResultSet rsColumns = dbmd.getColumns(null, null, tableName, W01CommonConst.CONST_ST_);

            //一件ずつループでヘッダーを取得
            while (rsColumns.next()) {

                //指定されたヘッダーと、区切りのカンマを格納
                sb.append(rsColumns.getString(W01CommonConst.COLUMN_NAME));
                sb.append(W01CommonConst.CONST_ST_COMMA);
                count++;

            }

            String columName = sb.toString();
            //最後のカンマを除く処理
            columName = columName.substring(0, columName.length() - 1);

            //ファイルへ書き出し
            pWriter.write(columName);

            // ファイルに書き出し閉じる
            pWriter.close();
        }
        if (count == 1) {
            //出力失敗メッセージ
            message.outMessage("E02", "CSVファイル出力");
            return W01CommonConst.ERROR;
        }

        //正常終了メッセージ
        message.outMessage("I01", "ファイルへの出力");
        return W01CommonConst.SUCCESS;
    }

}