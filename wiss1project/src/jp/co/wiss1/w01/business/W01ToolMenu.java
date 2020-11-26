package jp.co.wiss1.w01.business;

import java.io.IOException;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonConst;

/**
 * メインメニュー用クラス
 *
 * @since 2020/10/18
 * @author s-miyazaki
 * @version 1.0
 */
public class W01ToolMenu {

    /**
     * mainメソッド
     * ユーザーに入力を促し、選択された値によってクラスを呼び分ける。
     * クラスの結果を受け取り、メッセージを表示する
     * @param args
     *@return なし
     */
    @SuppressWarnings("resource")
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
            System.out.println("1_ファイル変換");
            System.out.println("2_DB関連");
            System.out.println("3_エビデンス成型");
            System.out.println("99_バッチを終了する");
            System.out.println("9_連動機能");
            System.out.println("1～4を選択してください:");
            System.out.println("************************");
            newline(2);
            // ユーザーに入力させる
            String str = scan.next();
            System.out.println("");
            newline(2);
            if ("1".equals(str)) {
                // テーブルデータ取得
                W01ConvertFileSelect W01ConvertFileSelect = new W01ConvertFileSelect();
                result = W01ConvertFileSelect.W01ConvertFileSelect();
            } else if ("2".equals(str)) {
                while (true) {
                    System.out.println("************************");
                    System.out.println("DB関連情報を取得します");
                    System.out.println("1_テーブルヘッダー部取得");
                    System.out.println("2_データ取得");
                    System.out.println("3_メニュー画面に戻る");
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
                        result = w01SelectTableData.SelectTableData("");
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
            } else if ("99".equals(str)) {
                // バッチ終了
                System.exit(0);
                // 連動機能
            } else if ("9".equals(str)) {
                // テーブルデータ取得
                W01SelectTableData w01SelectTableData = new W01SelectTableData();
                result = w01SelectTableData.SelectTableData(str);
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
    * 改行出力メソッド
    * 引数に指定された回数分改行を出力する。
    *
    * @param int（改行を行う回数）
    * @return なし
    */
    public static void newline(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            System.out.println("");
        }
    }

}
