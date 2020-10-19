package jp.co.wiss1.w01.business;

import java.io.IOException;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonConst;

/**
 * メインメニュー用クラス
 *
 * @since 2020.10.18
 * @author wiss1
 * @version 1.0.0
 */
public class W01ToolMenu {

    /**
     * mainメソッド
     * ユーザーに入力を促し、選択された値によってクラスを呼び分ける。
     * クラスの結果を受け取り、メッセージを表示する
     * @param args
     */
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);
        // 実行結果
        String result = W01CommonConst.SUCCESS;
        // メニューに戻るフラグ
        Boolean returnFlg = false;

        while (true) {
            // メニューに戻るフラグを初期化
            returnFlg = false;

            System.out.println("************************");
            System.out.println("01_ファイル変換");
            System.out.println("02_DB関連");
            System.out.println("03_エビデンス成型");
            System.out.println("1～3を選択してください:");
            System.out.println("************************");
            newline(2);
            // ユーザーに入力させる
            String str = scan.next();
            System.out.println("");
            newline(2);
            if ("1".equals(str)) {
                while (true) {
                    System.out.println("************************");
                    System.out.println("ファイル変換を行います");
                    System.out.println("01_TSV⇔CSV");
                    System.out.println("02_CSV⇔TSV");
                    System.out.println("03_ニュー画面に戻る");
                    System.out.println("1～3を選択してください:");
                    System.out.println("************************");
                    newline(2);
                    // ユーザーに入力させる
                    str = scan.next();
                    newline(2);

                    if ("1".equals(str)) {
                        // ファイル変換（TSV⇒CSV）
                        W01ConvertFileTsvToCsvc w01ConvertFileTsvToCsvc =
                                new W01ConvertFileTsvToCsvc();
                        result = w01ConvertFileTsvToCsvc.main();
                        break;
                    } else if ("2".equals(str)) {
                        // ファイル変換（CSV⇒TSV）
                        W01ConvertFileCsvToTsv w01ConvertFileCsvToTsv =
                                new W01ConvertFileCsvToTsv();
                        result = w01ConvertFileCsvToTsv.main();
                        break;
                    } else if ("3".equals(str)) {
                        System.out.println("メニュー画面に戻ります");
                        newline(2);
                        // メニューに戻るフラグを設定
                        returnFlg = true;
                        break;
                    } else {
                        // 入力値不正
                        System.out.println("ERROE:1～3を入力してください");
                        newline(2);
                    }
                }
            } else if ("2".equals(str)) {
                while (true) {
                    System.out.println("************************");
                    System.out.println("DB関連情報を取得します");
                    System.out.println("01_テーブルヘッダー部取得");
                    System.out.println("02_データ取得");
                    System.out.println("03_ニュー画面に戻る");
                    System.out.println("1～3を選択してください:");
                    System.out.println("************************");
                    newline(2);
                    // ユーザーに入力させる
                    str = scan.next();
                    newline(2);

                    if ("1".equals(str)) {
                        // テーブルヘッダー部取得
                        W01SelectTableHeader w01SelectTableHeader = new W01SelectTableHeader();
                        result = w01SelectTableHeader.main();
                        break;
                    } else if ("2".equals(str)) {
                        // テーブルデータ取得
                        W01SelectTableData w01SelectTableData = new W01SelectTableData();
                        result = w01SelectTableData.main();
                        break;
                    } else if ("3".equals(str)) {
                        System.out.println("メニュー画面に戻ります");
                        newline(2);
                        // メニューに戻るフラグを設定
                        returnFlg = true;
                        break;
                    } else {
                        // 入力値不正
                        System.out.println("ERROE:1～3を入力してください");
                        newline(2);
                    }
                }
            } else if ("3".equals(str)) {
                // テーブルデータ取得
                W01ShapeEvidence w01ShapeEvidence = new W01ShapeEvidence();
                result = w01ShapeEvidence.main();
            } else {
                // 入力値不正
                System.out.println("ERROE:1～3を入力してください");
                newline(2);
                continue;
            }
            // メニューに戻る
            if (returnFlg) {
                continue;
            }

            // 各クラスの戻り値によりメッセージを表示する。
            if (W01CommonConst.ERROR.equals(result)) {
                System.out.println("処理に失敗しました");
                newline(2);
            } else {
                System.out.println("処理が正常に終了しました");
                newline(2);
            }
            System.out.println("メニュー画面に戻ります");
            newline(2);
        }
    }

    /**
     * 改行用メソッド
     * 引数に指定された回数分改行を表示する。
     * @param count 改行を行う回数
    */
    public static void newline(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            System.out.println("");
        }
    }

}
