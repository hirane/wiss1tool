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

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;


/**
 * テーブルからデータを取得、CSVファイル出力に出力するクラス
 *
 * @author nishio
 * @version version1.0
 */
public class W01SelectTableData {

    // 共通関数クラス
    private static WISS1CommonUtil commUtil = new WISS1CommonUtil();

    /**
     * divisionCode、postCode、tEmployeeDatasメソッドを呼び出します
     * @param num
     * @return "1"or"0"
     */
    public  String main() {
        // 01：社員情報
        System.out.println(W01CommonConst.TBL_NM_ONE);
        // 02：部署コード
        System.out.println(W01CommonConst.TBL_NM_TWO);
        // 03：役職コード
        System.out.println(W01CommonConst.TBL_NM_THREE);

        System.out.println("取得したいテーブルを選択してください：");
        Scanner scan = new Scanner(System.in);
        String num = scan.next();

        int returnNum;
        try {
            returnNum = selectData(num);
            if (returnNum == 1) {
                return "1";
            }
        } catch (SQLException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
            return "1";
        }

        // 正常終了の場合は0を返す
        return "0";
    }

    /**
     * テーブルのデータを取得するメソッド
     * @param resultSet, tablebName
     * @return 1 or 0
     */
    private static int selectData(String num) throws SQLException {
        // 変数定義
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // DB接続情報を設定する
        String url = commUtil.getProperty("DBURL"); // 接続パス
        String id = commUtil.getProperty("DBUSER"); // ログインID
        String pw = commUtil.getProperty("DBPASS"); // ログインパスワード

        switch (num) {
        // 社員情報
        case "01":

            // SQL文を定義する
            String sql1 = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_EMPLOYEE;

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
                connection = DriverManager.getConnection(url, id, pw);

                // 実行するSQL文とパラメータを指定する
                preparedStatement = connection.prepareStatement(sql1);

                // SELECTを実行する
                resultSet = preparedStatement.executeQuery();

                // CSVファイルにネームするテーブル名を取得
                String tablebName = W01CommonConst.TBL_NM_EMPLOYEE;

                System.out.println(tablebName);

                // 取得したいデータをファイル出力メソッドに渡す
                int returnValue = outputFile(resultSet, tablebName);
                if (returnValue == 1) {
                    // 異常終了の場合は1を返す
                    return 1;
                }
            } catch (Exception ex) {
                // 例外発生時の処理
                ex.printStackTrace(); // エラー内容をコンソールに出力する
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E02", "TBLデータ取得");
                // 異常終了の場合は1を返す
                return 1;

            } finally {
                // クローズ処理
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            }
            // 正常終了の場合は0を返す
            return 0;

            // 部署コード
        case "02":

            // SQL文を定義する
            String sql2 = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_DIVISION;
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
                connection = DriverManager.getConnection(url, id, pw);

                // 実行するSQL文とパラメータを指定する
                preparedStatement = connection.prepareStatement(sql2);

                // SELECTを実行する
                resultSet = preparedStatement.executeQuery();

                // CSVファイルにネームするテーブル名を取得
                String tablebName = W01CommonConst.TBL_NM_DIVISION;

                System.out.println(tablebName);

                // 取得したいデータをファイル出力メソッドに渡す
                int returnValue = outputFile(resultSet, tablebName);
                // 異常終了の場合は1を返す
                if (returnValue == 1) {
                    return 1;
                }

            } catch (Exception ex) {
                // 例外発生時の処理
                ex.printStackTrace(); // エラー内容をコンソールに出力する
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E02", "TBLデータ取得");
                // 異常終了の場合は1を返す
                return 1;

            } finally {
                // クローズ処理
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            }
            // 正常終了の場合は0を返す
            return 0;

            // 役職コード
        case "03":
            // SQL文を定義する
            String sql = W01CommonConst.TBL_SELECT_ALL + W01CommonConst.TBL_NM_POST;

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
                connection = DriverManager.getConnection(url, id, pw);

                // 実行するSQL文とパラメータを指定する
                preparedStatement = connection.prepareStatement(sql);

                // SELECTを実行する
                resultSet = preparedStatement.executeQuery();

                // CSVファイルにネームするテーブル名を取得
                String tablebName = W01CommonConst.TBL_NM_POST;

                System.out.println(tablebName);

                // 取得したいデータをファイル出力メソッドに渡す
                int returnValue = outputFile(resultSet, tablebName);
                // 異常終了の場合は1を返す
                if (returnValue == 1) {
                    return 1;
                }
            } catch (Exception ex) {
                // 例外発生時の処理
                ex.printStackTrace(); // エラー内容をコンソールに出力する
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E02", "TBLデータ取得");
                // 異常終了の場合は1を返す
                return 1;

            } finally {
                // クローズ処理
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            }
            // 正常終了の場合は0を返す
            return 0;

            // 1~3以外ならバッチに戻り値1を返す
        default:
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E02", "TBL呼び出し");
            // 異常終了の場合は1を返す
            return 1;
        }

    }

    /**
     * 取得したデータをCSVファイルに出力するメソッド
     * @param
     * @return 1 or 0
     */
    private static int outputFile(ResultSet resultSet, String tablebName) {

        try {

            // 現在時刻の取得
            Date now = new Date();

            // 最終更新日時表示書式定義
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

            String fileNow = sdf.format(now);

            // テーブル名と現在時刻を合わせる
            String fileName = tablebName + "_data_" + fileNow;

            String path = commUtil.getProperty("OUTPUTPATH"); // ログインパスワード

            FileWriter fileWriter = new FileWriter(path + fileName + ".csv");

            PrintWriter printWriter = new PrintWriter(new BufferedWriter(fileWriter));

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
                                printWriter.print(",");
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
                            printWriter.print(",");
                        }
                    }
                }
            } catch (SQLException e) {
                // 例外発生時の処理
                e.printStackTrace(); // エラー内容をコンソールに出力する
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E02", "CSVファイル出力");
                // 異常終了の場合は1を返す
                return 1;
            }
            printWriter.close();
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
