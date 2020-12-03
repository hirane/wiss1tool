package jp.co.wiss1.w01.business;

import java.io.IOException;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

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

        W01CommonUtil message = new W01CommonUtil();

        Scanner scan = new Scanner(System.in);
        // 実行結果
        String result = W01CommonConst.SUCCESS;
        // メニューに戻るフラグ
        Boolean returnFlg = false;

        while (true) {
            // メニューに戻るフラグを初期化
            returnFlg = false;

            message.outMessage("I00", "************************");
            message.outMessage("I00", "1_ファイル変換");
            message.outMessage("I00", "2_DB関連");
            message.outMessage("I00", "3_エビデンス成型");
            message.outMessage("I00", "9_連動機能");
            message.outMessage("I00", "99_バッチを終了する");
            message.outMessage("I00", "該当する番号を選択してください :");
            message.outMessage("I00", "************************");

            newline(2);
            // ユーザーに入力させる
            String str = scan.next();
            newline(3);
            if ("1".equals(str)) {
                // テーブルデータ取得
                W01ConvertFileSelect W01ConvertFileSelect = new W01ConvertFileSelect();
                result = W01ConvertFileSelect.convertFileSelect();
            } else if ("2".equals(str)) {
                while (true) {

                    message.outMessage("I00", "************************");
                    message.outMessage("I00", "DB関連情報を取得します");
                    message.outMessage("I00", "1_テーブルヘッダー部取得");
                    message.outMessage("I00", "2_データ取得");
                    message.outMessage("I00", "3_メニュー画面に戻る");
                    message.outMessage("I00", "1～3を選択してください:");
                    message.outMessage("I00", "************************");

                    newline(2);
                    // ユーザーに入力させる
                    str = scan.next();
                    newline(2);

                    if ("1".equals(str)) {
                        // テーブルヘッダー部取得
                        W01SelectTableHeader w01SelectTableHeader = new W01SelectTableHeader();
                        result = w01SelectTableHeader.selectTableHeader();
                        break;
                    } else if ("2".equals(str)) {
                        // テーブルデータ取得
                        W01SelectTableData w01SelectTableData = new W01SelectTableData();
                        result = w01SelectTableData.selectTableData(false);
                        break;
                    } else if ("3".equals(str)) {
                        message.outMessage("I00", "メニュー画面に戻ります");
                        newline(2);
                        // メニューに戻るフラグを設定
                        returnFlg = true;
                        break;
                    } else {
                        // 入力値不正
                        message.outMessage("I03", "ERROE:1～3");
                        newline(2);
                    }
                }
            } else if ("3".equals(str)) {
                // エビデンス成型
                W01ShapeEvidence w01ShapeEvidence = new W01ShapeEvidence();
                result = w01ShapeEvidence.shapeEvidence();
            } else if ("9".equals(str)) {
                // テーブルデータ取得
                W01SelectTableData w01SelectTableData = new W01SelectTableData();
                result = w01SelectTableData.selectTableData(true);
            } else if ("99".equals(str)) {
                // バッチ終了
                System.exit(0);
                // 連動機能
            } else {
                // 入力値不正
                message.outMessage("I03", "該当する番号");
                newline(2);
                continue;
            }
            // メニューに戻る
            if (returnFlg) {
                continue;
            }

            // 各クラスの戻り値によりメッセージを表示する。
            if (W01CommonConst.ERROR.equals(result)) {
                message.outMessage("E02", "処理");
                newline(2);
            } else {
                message.outMessage("I00", "処理が正常に終了しました");
                newline(2);
            }
            message.outMessage("I00", "メニュー画面に戻ります");
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
