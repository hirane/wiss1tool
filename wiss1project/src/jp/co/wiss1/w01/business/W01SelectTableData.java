package jp.co.wiss1.w01.business;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * テーブルデータ出力クラス
 *
 * @since 20201001
 * @author k-nishio
 * @version 1.0
 */
public class W01SelectTableData {

    /**
     * divisionCode、postCode、tEmployeeDatasメソッドを呼び出します
     * @param なし
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     */
    @SuppressWarnings("resource")
    public String main() {
        // 1：社員情報
        W01CommonUtil messege1 = new W01CommonUtil();
        messege1.outMessage("I00", W01CommonConst.TBL_NM_ONE);
        // 2：部署コード
        W01CommonUtil messege2 = new W01CommonUtil();
        messege2.outMessage("I00", W01CommonConst.TBL_NM_TWO);
        // 3：役職コード
        W01CommonUtil messege3 = new W01CommonUtil();
        messege3.outMessage("I00", W01CommonConst.TBL_NM_THREE);

        W01CommonUtil messege4 = new W01CommonUtil();
        messege4.outMessage("I00", "取得したいテーブルを選択してください：");
        Scanner scan = new Scanner(System.in);
        String num = scan.next();

        try {
            String returnValue = selectData(num);
            if (W01CommonConst.ERROR.equals(returnValue)) {
                return W01CommonConst.ERROR;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E04", "1~3");
            return W01CommonConst.ERROR;
        }

        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;
    }

    /**
     * テーブルのデータを取得するメソッド
     * @param num（入力された数値）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     */
    private static String selectData(String num) throws SQLException {

        switch (num) {
        // 社員情報
        case W01CommonConst.TBL_CH_ONE:

            // SQL文を定義する
            String sql1 = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_EMPLOYEE;

            ResultSet resultSet = resultSetData(sql1);
            if (resultSet.equals(null)) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebName = W01CommonConst.TBL_NM_EMPLOYEE;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnValue = outputFile(resultSet, tablebName);
            if (W01CommonConst.ERROR.equals(returnValue)) {
                // 異常終了の場合は1を返す
                return W01CommonConst.ERROR;
            }
            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

            // 部署コード
        case W01CommonConst.TBL_CH_TWO:

            // SQL文を定義する
            String sql2 = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_DIVISION;

            ResultSet resultSet2 = resultSetData(sql2);
            if (resultSet2.equals(null)) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebName2 = W01CommonConst.TBL_NM_DIVISION;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnValue2 = outputFile(resultSet2, tablebName2);
            // 異常終了の場合は1を返す
            if (W01CommonConst.ERROR.equals(returnValue2)) {
                return W01CommonConst.ERROR;
            }

            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

            // 役職コード
        case W01CommonConst.TBL_CH_THREE:
            // SQL文を定義する
            String sql3 = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_POST;

            ResultSet resultSet3 = resultSetData(sql3);
            if (resultSet3.equals(null)) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebName3 = W01CommonConst.TBL_NM_POST;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnValue3 = outputFile(resultSet3, tablebName3);
            // 異常終了の場合は1を返す
            if (W01CommonConst.ERROR.equals(returnValue3)) {
                return W01CommonConst.ERROR;
            }
            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

            // 1~3以外ならバッチに戻り値1を返す
        default:
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E02", "TBL呼び出し");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        }

    }

    /**
     * テーブルデータを取得する処理
     * @param sql (SELECT文)
     * @return resultSet（resultSet:正常終了 null:異常終了）
     * @throws SQLException
     */
    private static ResultSet resultSetData(String sql) throws SQLException {

        // 変数定義
        ResultSet resultSet = null;
        java.sql.Statement statement = null;

        try {
            Class.forName(W01CommonConst.PRO_DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E01", "TBL接続");
            // 異常終了の場合は1を返す
            return null;
        }
        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        try {
            // resultSetのカーソルのタイプを指定する。
            statement =
                    connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_READ_ONLY);

            // SELECTの実行結果を移す
            resultSet = statement.executeQuery(sql);

            // カーソルを最後まで移動する
            resultSet.last();
            // データの数をカウントする
            int dateNumber = resultSet.getRow();
            // カーソルを一つ前に移動する
            resultSet.beforeFirst();
            // データが無い場合
            if (1 > dateNumber) {
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E03", "TBLデータ");
                // 異常終了の場合は1を返す
                return null;
            }
        } catch (Exception ex) {
            // 例外発生時の処理
            ex.printStackTrace(); // エラー内容をコンソールに出力する
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E02", "TBLデータ取得");
            // 異常終了の場合は1を返す
            return null;

        } finally {
            // クローズ処理
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        }
        return resultSet;

    }

    /**
     * 取得したデータをCSVファイルに出力するメソッド
     * @param resultSet (DB接続結果)
     * @param tablebName (テーブル名)
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     * @throws SQLException
     */
    private static String outputFile(ResultSet resultSet, String tablebName) throws SQLException {

        try {

            // 現在時刻の取得
            Date now = new Date();

            // 最終更新日時表示書式定義
            SimpleDateFormat sdf = new SimpleDateFormat(W01CommonConst.DATE);

            String fileNow = sdf.format(now);

            // ファイル名を作成
            String fileName = tablebName + W01CommonConst.FILE_NM_DATA + fileNow;

            String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH); // ログインパスワード

            File fileWriter =
                    new File(path + fileName + W01CommonConst.CONST_EXTENSION_CSV);

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileWriter),W01CommonConst.CONST_CHAR_CODE_UTF8)));

            // 項目名を格納する配列
            ArrayList<String> clmnAry = null;
            try {
                // データの数分繰り返してファイルに書き込む
                int columnCount = 0;
                boolean flg = false;
                while (resultSet.next()) {
                    // 初回のみ項目名を取得
                    if (clmnAry == null) {
                        clmnAry = new ArrayList<>();
                        // カラム数とカラム名を取得を取得
                        ResultSetMetaData metaData = resultSet.getMetaData();
                        columnCount = metaData.getColumnCount();
                        // カラム数分繰り返す
                        for (int i = 0; i < columnCount; i++) {
                            // カラム名を設定
                            clmnAry.add(metaData.getColumnName(i + 1));
                        }
                    }
                    if (flg == false) {
                        flg = true;
                        int i = 0;
                        // カラム数分繰り返す
                        for (String clmn : clmnAry) {
                            // ヘッダ部のデータをcsvファイルに値を入れる
                            i++;
                            if (columnCount == i) {
                                printWriter.print(clmn);
                            } else {
                                printWriter.print(clmn);
                                // データの間にカンマを挿入
                                printWriter.print(W01CommonConst.CONST_ST_COMMA);
                            }

                        }
                    }
                    printWriter.println();
                    // カラム数分繰り返す
                    int j = 0;
                    for (String clmn2 : clmnAry) {
                        // データ部のデータをcsvファイルに値を入れる
                        j++;
                        if (columnCount == j) {
                            printWriter.print(resultSet.getObject(clmn2));
                        } else {
                            printWriter.print(resultSet.getObject(clmn2));
                            // データの間にカンマを挿入
                            printWriter.print(W01CommonConst.CONST_ST_COMMA);
                        }
                    }
                }
            } catch (SQLException e) {
                // 例外発生時の処理
                e.printStackTrace(); // エラー内容をコンソールに出力する
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E02", "CSVファイル出力");
                // 異常終了の場合は1を返す
                return W01CommonConst.ERROR;
            }
            printWriter.close();
        } catch (IOException ex) {
            // 例外時処理
            ex.printStackTrace();
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E01", "CSVファイル出力");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        } finally {
            // クローズ処理
            if (resultSet != null)
                resultSet.close();
        }

        W01CommonUtil messege = new W01CommonUtil();
        messege.outMessage("I01", "CSVファイル出力");
        // 正常終了なら0を返す
        return W01CommonConst.SUCCESS;
    }

}
