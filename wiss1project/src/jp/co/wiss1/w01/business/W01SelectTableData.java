package jp.co.wiss1.w01.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
     * @param num（連動機能番号）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     */
    @SuppressWarnings("resource")
    public String SelectTableData(String num) {
        W01CommonUtil message = new W01CommonUtil();
        // 1：社員情報
        message.outMessage("I00", W01CommonConst.TBL_NM_ONE);
        // 2：部署コード
        message.outMessage("I00", W01CommonConst.TBL_NM_TWO);
        // 3：役職コード
        message.outMessage("I00", W01CommonConst.TBL_NM_THREE);

        message.outMessage("I04", "取得したいテーブル");
        Scanner scan = new Scanner(System.in);
        String numTable = scan.next();

        //1～3以外を入力したら異常終了で1を返す
        if (!(numTable.equals(W01CommonConst.TBL_CH_ONE))
                && !(numTable.equals(W01CommonConst.TBL_CH_TWO))
                && !(numTable.equals(W01CommonConst.TBL_CH_THREE))) {
            message.outMessage("E04", "1～3");
            return W01CommonConst.ERROR;

        }

        String numData = "1";
        if (!W01CommonConst.SELECT_NINE.equals(num)) {
            // 1：データ取得
            message.outMessage("I00", W01CommonConst.OPE_CON_ONE);
            // 2：データ追加
            message.outMessage("I00", W01CommonConst.OPE_CON_TWO);
            // 3：データ削除
            message.outMessage("I00", W01CommonConst.OPE_CON_THREE);

            //実行したい操作の番号を入力
            message.outMessage("I04", "対象にしたい処理");
            numData = scan.next();
        }

        String returnValue = null;
        try {
            if (numData.equals(W01CommonConst.OPE_CH_ONE)) {
                returnValue = selectData(numTable, num);
            } else if (numData.equals(W01CommonConst.OPE_CH_TWO)) {
                returnValue = addData(numTable);
            } else if (numData.equals(W01CommonConst.OPE_CH_THREE)) {
                returnValue = deleteData(numTable);
            }
            if (W01CommonConst.ERROR.equals(returnValue)) {
                return W01CommonConst.ERROR;
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            message.outMessage("E04", "1～3");
            return W01CommonConst.ERROR;
        }

        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;

    }

    /**
     * テーブルのデータを取得するメソッド
     * @param numTable（入力された数値）
     * @param num（連動機能番号）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     */
    private static String selectData(String numTable, String num) throws SQLException {

        switch (numTable) {
        // 社員情報
        case W01CommonConst.TBL_CH_ONE:

            // SQL文を定義する
            String sql1 = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_EMPLOYEE;

            ResultSet resultSet = resultSetData(sql1);
            if (resultSet == null) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebName = W01CommonConst.TBL_NM_EMPLOYEE;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnValue = outputFile(resultSet, tablebName, num);
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
            if (resultSet2 == null) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebName2 = W01CommonConst.TBL_NM_DIVISION;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnValue2 = outputFile(resultSet2, tablebName2, num);
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
            if (resultSet3 == null) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebName3 = W01CommonConst.TBL_NM_POST;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnValue3 = outputFile(resultSet3, tablebName3, num);
            // 異常終了の場合は1を返す
            if (W01CommonConst.ERROR.equals(returnValue3)) {
                return W01CommonConst.ERROR;
            }
            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

        // 1～3以外ならバッチに戻り値1を返す
        default:
            W01CommonUtil message = new W01CommonUtil();
            message.outMessage("E02", "TBL呼び出し");
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
            W01CommonUtil message = new W01CommonUtil();
            message.outMessage("E01", "TBL接続");
            // 異常終了の場合は1を返す
            return null;
        }
        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        try {
            // resultSetのカーソルのタイプを指定する。
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
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
                W01CommonUtil message = new W01CommonUtil();
                message.outMessage("E03", "TBLデータ");
                // 異常終了の場合は1を返す
                return null;
            }
        } catch (Exception ex) {
            // 例外発生時の処理
            ex.printStackTrace(); // エラー内容をコンソールに出力する
            W01CommonUtil message = new W01CommonUtil();
            message.outMessage("E02", "TBLデータ取得");
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
     * @param num（連動機能番号）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     * @throws SQLException
     */
    @SuppressWarnings("resource")
    private static String outputFile(ResultSet resultSet, String tablebName, String num)
            throws SQLException {

        try {

            // 現在時刻の取得
            Date now = new Date();

            // 最終更新日時表示書式定義
            SimpleDateFormat sdf = new SimpleDateFormat(W01CommonConst.DATE);

            String fileNow = sdf.format(now);

            // ファイル名を作成
            String fileName = tablebName + W01CommonConst.FILE_NM_DATA + fileNow;

            String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH); // ログインパスワード

            File fileWriter = new File(path + fileName + W01CommonConst.CONST_EXTENSION_CSV);

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileWriter), W01CommonConst.CONST_CHAR_CODE_UTF8)));

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

                W01CommonUtil message = new W01CommonUtil();
                if (W01CommonConst.SELECT_NINE.equals(num)) {
                    message.outMessage("I00", "1：TSVファイル");
                    message.outMessage("I00", "2：EXCELファイル");
                    message.outMessage("I04", "出力先にしたいファイルの形");
                    Scanner scan = new Scanner(System.in);
                    int numSelect = scan.nextInt();

                    // ファイルパスをファイル型から文字列型へ変更
                    String filePath = fileWriter.toString();

                    switch (numSelect) {

                    // TSVファイル変換を行う
                    case W01CommonConst.NUM_ONE:
                        W01ConvertFileCsvToTsv.FileCheck(filePath, num);

                        // エビデンス成型を行う
                    case W01CommonConst.NUM_TWO:
                        W01ShapeEvidence.EvidenceOutput(filePath);

                        // それ以外
                    default:
                        message.outMessage("E04", "1～2で");
                        // 異常終了の場合は1を返す
                        return W01CommonConst.ERROR;
                    }

                }

            } catch (SQLException e) {
                // 例外発生時の処理
                e.printStackTrace(); // エラー内容をコンソールに出力する
                W01CommonUtil message = new W01CommonUtil();
                message.outMessage("E02", "CSVファイル出力");
                // 異常終了の場合は1を返す
                return W01CommonConst.ERROR;
            }
            printWriter.close();
        } catch (IOException ex) {
            // 例外時処理
            ex.printStackTrace();
            W01CommonUtil message = new W01CommonUtil();
            message.outMessage("E01", "CSVファイル出力");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        } finally {
            // クローズ処理
            if (resultSet != null)
                resultSet.close();
        }

        W01CommonUtil message = new W01CommonUtil();
        message.outMessage("I01", "CSVファイル出力");
        // 正常終了なら0を返す
        return W01CommonConst.SUCCESS;
    }

    /**
     * テーブルのデータを追加するメソッド
     * @param numTable（入力された数値）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     * @throws IOException
     */
    @SuppressWarnings("resource")
    private static String addData(String numTable) throws SQLException, IOException {
        W01CommonUtil message = new W01CommonUtil();
        // CSVファイルのファイル名を取得
        message.outMessage("I03", "CSVファイル名");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();

        // CSVファイルの絶対パスを取得
        String filePath = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH) + fileName;

        String extension = W01CommonConst.CONST_EXTENSION_CSV;

        // インプットファイルの確認
        if (W01CommonUtil.checkInputPath(filePath,
                W01CommonConst.CONST_EXTENSION_CSV) == W01CommonConst.FCHECK_ERROR_EXT) {
            message.outMessage("I03", extension + "ファイル");
            return W01CommonConst.ERROR;
        } else if (W01CommonUtil.checkInputPath(filePath,
                W01CommonConst.CONST_EXTENSION_CSV) == W01CommonConst.FCHECK_ERROR_EXS) {
            message.outMessage("I03", "正しい格納先（絶対パス）");
            return W01CommonConst.ERROR;
        } else if (W01CommonUtil.checkInputPath(filePath,
                W01CommonConst.CONST_EXTENSION_CSV) == W01CommonConst.FCHECK_ERROR_EMP) {
            message.outMessage("E03", "ファイル内にデータ");
            return W01CommonConst.ERROR;
        }

        // CSVファイルの読み込み
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);

        try {
            Class.forName(W01CommonConst.PRO_DB_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            message.outMessage("E01", "TBL接続");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        }
        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        Statement statement = connection.createStatement();

        //読み込み行
        String line;

        // 1行ずつCSVファイルを読み込む
        while ((line = br.readLine()) != null) {
            switch (numTable) {
            // 社員情報
            case W01CommonConst.TBL_CH_ONE:

                String sql1 = W01CommonConst.TBL_INSERT_ONE + W01CommonConst.TBL_NM_EMPLOYEE
                        + W01CommonConst.TBL_INSERT_TWO + line + W01CommonConst.TBL_INSERT_THREE;
                statement.executeUpdate(sql1);

                // 部署コード
            case W01CommonConst.TBL_CH_TWO:

                String sql2 = W01CommonConst.TBL_INSERT_ONE + W01CommonConst.TBL_NM_DIVISION
                        + W01CommonConst.TBL_INSERT_TWO + line + W01CommonConst.TBL_INSERT_THREE;
                statement.executeUpdate(sql2);

                // 役職コード
            case W01CommonConst.TBL_CH_THREE:

                String sql3 = W01CommonConst.TBL_INSERT_ONE + W01CommonConst.TBL_NM_POST
                        + W01CommonConst.TBL_INSERT_TWO + line + W01CommonConst.TBL_INSERT_THREE;
                statement.executeUpdate(sql3);

            default:
                message.outMessage("E02", "TBL呼び出し");
                // 異常終了の場合は1を返す
                return W01CommonConst.ERROR;
            }

        }

        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;
    }

    /**
     * テーブルのデータを削除するメソッド
     * @param numTable（入力された数値）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     */
    private static String deleteData(String numTable) throws SQLException {
        try {
            Class.forName(W01CommonConst.PRO_DB_DRIVER);
        } catch (ClassNotFoundException e) {
            W01CommonUtil message = new W01CommonUtil();
            e.printStackTrace();
            message.outMessage("E01", "TBL接続");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        }
        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        Statement statement = connection.createStatement();

        switch (numTable) {
        // 社員情報
        case W01CommonConst.TBL_CH_ONE:

            // SQL文を定義する
            String sql1 = W01CommonConst.TBL_DELETE_ALL + W01CommonConst.TBL_NM_EMPLOYEE;
            statement.executeUpdate(sql1);

            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

        // 部署コード
        case W01CommonConst.TBL_CH_TWO:

            // SQL文を定義する
            String sql2 = W01CommonConst.TBL_DELETE_ALL + W01CommonConst.TBL_NM_DIVISION;
            statement.executeUpdate(sql2);
            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

        // 役職コード
        case W01CommonConst.TBL_CH_THREE:
            // SQL文を定義する
            String sql3 = W01CommonConst.TBL_DELETE_ALL + W01CommonConst.TBL_NM_POST;

            statement.executeUpdate(sql3);

            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

        // 1～3以外ならバッチに戻り値1を返す
        default:
            W01CommonUtil message = new W01CommonUtil();
            message.outMessage("E02", "TBL呼び出し");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;

        }
    }

}
