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

    static W01CommonUtil message = new W01CommonUtil();

    /**
     * divisionCode、postCode、tEmployeeDatasメソッドを呼び出します
     * @param interLockingFlg（連動機能と判別させるフラグ）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     */
    @SuppressWarnings("resource")
    public String selectTableData(boolean interLockingFlg) {

        Scanner scan = new Scanner(System.in);
        while (true) {
            // 1：社員情報
            message.outMessage("I00", W01CommonConst.TBL_NM_ONE);
            // 2：部署コード
            message.outMessage("I00", W01CommonConst.TBL_NM_TWO);
            // 3：役職コード
            message.outMessage("I00", W01CommonConst.TBL_NM_THREE);

            message.outMessage("I04", "取得したいテーブル");

            String numTable = scan.next();
            //1から3以外を入力したら異常終了で1を返す
            if (!(W01CommonConst.TBL_CH_ONE.equals(numTable))
                    && !(W01CommonConst.TBL_CH_TWO.equals(numTable))
                    && !(W01CommonConst.TBL_CH_THREE.equals(numTable))) {
                message.outMessage("E04", "1から3");
                continue;

            }

            while (true) {
                String numData = "1";

                if (!interLockingFlg) {
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

                try {
                    if (W01CommonConst.OPE_CH_ONE.equals(numData)) {
                        return selectData(numTable, interLockingFlg);
                    } else if (W01CommonConst.OPE_CH_TWO.equals(numData)) {
                        return addData(numTable);
                    } else if (W01CommonConst.OPE_CH_THREE.equals(numData)) {
                        return deleteData(numTable);
                    } else {
                        message.outMessage("E04", "1から3");
                        continue;
                    }

                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                    message.outMessage("E04", "1から3");
                    // 異常終了の場合は1を返す
                    return W01CommonConst.ERROR;

                }

            }

        }
    }

    /**
     * テーブルのデータを取得するメソッド
     * @param numTable（入力された数値）
     * @param interLockingFlg（連動機能と判別させるフラグ）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     */
    private static String selectData(String numTable, boolean interLockingFlg) throws SQLException {

        switch (numTable) {
        // 社員情報
        case W01CommonConst.TBL_CH_ONE:

            // SQL文を定義する
            String employeeSql = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_EMPLOYEE;

            ResultSet resultEmployeeSet = resultSetData(employeeSql);
            if (null == resultEmployeeSet) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebEmployee = W01CommonConst.TBL_NM_EMPLOYEE;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnEmployeeValue =
                    outputFile(resultEmployeeSet, tablebEmployee, interLockingFlg);
            if (W01CommonConst.ERROR.equals(returnEmployeeValue)) {
                // 異常終了の場合は1を返す
                return W01CommonConst.ERROR;
            }

            // 部署コード
        case W01CommonConst.TBL_CH_TWO:

            // SQL文を定義する
            String divisionSql = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_DIVISION;

            ResultSet resultDivisionSet = resultSetData(divisionSql);
            if (null == resultDivisionSet) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebDivision = W01CommonConst.TBL_NM_DIVISION;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnDivisionValue =
                    outputFile(resultDivisionSet, tablebDivision, interLockingFlg);
            // 異常終了の場合は1を返す
            if (W01CommonConst.ERROR.equals(returnDivisionValue)) {
                return W01CommonConst.ERROR;
            }

            // 役職コード
        case W01CommonConst.TBL_CH_THREE:
            // SQL文を定義する
            String postSql = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_POST;

            ResultSet resultPostSet = resultSetData(postSql);
            if (null == resultPostSet) {
                return W01CommonConst.ERROR;
            }

            // CSVファイルにネームするテーブル名を取得
            String tablebPost = W01CommonConst.TBL_NM_POST;

            // 取得したいデータをファイル出力メソッドに渡す
            String returnPostValue = outputFile(resultPostSet, tablebPost, interLockingFlg);
            // 異常終了の場合は1を返す
            if (W01CommonConst.ERROR.equals(returnPostValue)) {
                return W01CommonConst.ERROR;
            }

        }
        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;

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
                message.outMessage("E03", "TBLデータ");
                // 異常終了の場合は1を返す
                return null;
            }
        } catch (Exception ex) {
            // 例外発生時の処理
            ex.printStackTrace(); // エラー内容をコンソールに出力する
            message.outMessage("E02", "TBLデータ取得");
            // 異常終了の場合は1を返す
            return null;

        } finally {
            // クローズ処理
            if (null != statement)
                statement.close();
            if (null != connection)
                connection.close();
        }
        return resultSet;

    }

    /**
     * 取得したデータをCSVファイルに出力するメソッド
     * @param resultSet (DB接続結果)
     * @param tablebName (テーブル名)
     * @param interLockingFlg（連動機能と判別させるフラグ）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     * @throws SQLException
     */
    private static String outputFile(ResultSet resultSet, String tablebName,
            boolean interLockingFlg) throws SQLException {

        try {

            // 現在時刻の取得
            Date now = new Date();

            // 最終更新日時表示書式定義
            SimpleDateFormat sdf = new SimpleDateFormat(W01CommonConst.DATE);

            String fileNow = sdf.format(now);

            // ファイル名を作成
            String fileName = tablebName + W01CommonConst.FILE_NM_DATA + fileNow;

            // 出力パス
            String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH);

            File fileWriter = new File(path + fileName + W01CommonConst.CONST_EXTENSION_CSV);

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileWriter), W01CommonConst.CONST_CHAR_CODE_UTF8)));

            // 項目名を格納する配列
            ArrayList<String> clmnAry = null;
            // データの数分繰り返してファイルに書き込む
            int columnCount = 0;
            boolean flg = false;
            while (resultSet.next()) {
                // 初回のみ項目名を取得
                if (null == clmnAry) {
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
                if (false == flg) {
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
                for (String clmn : clmnAry) {
                    // データ部のデータをcsvファイルに値を入れる
                    j++;
                    if (columnCount == j) {
                        printWriter.print(resultSet.getObject(clmn));
                    } else {
                        printWriter.print(resultSet.getObject(clmn));
                        // データの間にカンマを挿入
                        printWriter.print(W01CommonConst.CONST_ST_COMMA);
                    }
                }
            }

            printWriter.close();

            if (interLockingFlg) {
                return selectFileData(fileWriter, interLockingFlg);
            }
        } catch (IOException ex) {
            // 例外時処理
            ex.printStackTrace();
            message.outMessage("E01", "CSVファイル出力");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        } finally {
            // クローズ処理
            if (null != resultSet)
                resultSet.close();
        }

        message.outMessage("I01", "CSVファイル出力");
        // 正常終了なら0を返す
        return W01CommonConst.SUCCESS;
    }

    /**
     * ファイル名を連携するメソッド
     * @param file（CSVファイルパス）
     * @param interLockingFlg（連動機能と判別させるフラグ）
     * @return なし
     */
    @SuppressWarnings("resource")
    private static String selectFileData(File file, boolean interLockingFlg) {
        while (true) {
            message.outMessage("I00", "1：TSVファイル");
            message.outMessage("I00", "2：EXCELファイル");
            message.outMessage("I04", "出力先にしたいファイルの形");
            Scanner scan = new Scanner(System.in);
            String numSelect = scan.next();

            // ファイルパスをファイル型から文字列型へ変更
            String filePath = file.toString();

            // ファイル名からファイルパスを取得
            String fileName = new File(filePath).getName();

            switch (numSelect) {
            // TSVファイル変換を行う
            case W01CommonConst.SELECT_FILE_ONE:
                return W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interLockingFlg);
            //return W01CommonConst.SUCCESS;

            // エビデンス成型を行う
            case W01CommonConst.SELECT_FILE_TWO:
                return W01ShapeEvidence.evidenceOutput(filePath);
            //return W01CommonConst.SUCCESS;

            // それ以外
            default:
                message.outMessage("E04", "1または2で");
                continue;
            }
        }

    }

    /**
     * テーブルのデータを追加するメソッド
     * @param numTable（入力された数値）
     * @return String（SUCCESS:正常終了 ERROR:異常終了）
     * @throws IOException
     */
    @SuppressWarnings("resource")
    private static String addData(String numTable) throws SQLException, IOException {
        // CSVファイルのファイル名を取得
        message.outMessage("I03", "CSVファイル名");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();

        // CSVファイルの絶対パスを取得
        String filePath = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH) + fileName;

        String extension = W01CommonConst.CONST_EXTENSION_CSV;

        // インプットファイルの確認
        if (W01CommonConst.FCHECK_ERROR_EXT == W01CommonUtil.checkInputPath(filePath,
                W01CommonConst.CONST_EXTENSION_CSV)) {
            // CSVファイルでなかった場合、異常終了する
            message.outMessage("I03", extension + "ファイル");
            return W01CommonConst.ERROR;
        } else if (W01CommonConst.FCHECK_ERROR_EXS == W01CommonUtil.checkInputPath(filePath,
                W01CommonConst.CONST_EXTENSION_CSV)) {
            // 該当のファイルが存在しない場合、異常終了する
            message.outMessage("I03", "正しい格納先（絶対パス）");
            return W01CommonConst.ERROR;
        } else if (W01CommonConst.FCHECK_ERROR_EMP == W01CommonUtil.checkInputPath(filePath,
                W01CommonConst.CONST_EXTENSION_CSV)) {
            // 空ファイルの場合、異常終了する
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

        String insertSql = W01CommonConst.TBL_INSERT_ONE;

        // 1行ずつCSVファイルを読み込む
        while ((line = br.readLine()) != null) {
            String sql = W01CommonConst.TBL_INSERT_TWO + line + W01CommonConst.TBL_INSERT_THREE;

            switch (numTable) {
            // 社員情報
            case W01CommonConst.TBL_CH_ONE:

                String employeeSql = insertSql + W01CommonConst.TBL_NM_EMPLOYEE + sql;

                statement.executeUpdate(employeeSql);
                break;

            // 部署コード
            case W01CommonConst.TBL_CH_TWO:

                String devisionSql = insertSql + W01CommonConst.TBL_NM_DIVISION + sql;

                statement.executeUpdate(devisionSql);
                break;

            // 役職コード
            case W01CommonConst.TBL_CH_THREE:

                String postSql = insertSql + W01CommonConst.TBL_NM_POST + sql;

                statement.executeUpdate(postSql);
                break;

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

            e.printStackTrace();
            message.outMessage("E01", "TBL接続");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        }
        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        Statement statement = connection.createStatement();

        String deleteSql = W01CommonConst.TBL_DELETE_ALL;

        switch (numTable) {
        // 社員情報
        case W01CommonConst.TBL_CH_ONE:

            // SQL文を定義する
            String employeeSql = deleteSql + W01CommonConst.TBL_NM_EMPLOYEE;
            statement.executeUpdate(employeeSql);

            // 部署コード
        case W01CommonConst.TBL_CH_TWO:

            // SQL文を定義する
            String devisionSql = deleteSql + W01CommonConst.TBL_NM_DIVISION;
            statement.executeUpdate(devisionSql);

            // 役職コード
        case W01CommonConst.TBL_CH_THREE:
            // SQL文を定義する
            String postSql = deleteSql + W01CommonConst.TBL_NM_POST;
            statement.executeUpdate(postSql);

        }
        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;
    }

}
