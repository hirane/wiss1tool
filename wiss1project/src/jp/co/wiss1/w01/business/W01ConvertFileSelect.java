package jp.co.wiss1.w01.business;

/**
 * クラス概要 ファイル名の入力と拡張子判定
 *
 * @author m-nishikawa
 * @since 2020/11/16
 * @version 1.0
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

public class W01ConvertFileSelect {

    /**
     * 処理を選択
     * @param なし
     * @return 処理結果を返却する
     */
    @SuppressWarnings("resource")
    public String W01ConvertFileSelect() {

        W01CommonUtil message = new W01CommonUtil();

        // 全件を対象にするか選択
        message.outMessage("I06", "フォルダ内の全て");
        message.outMessage("I00", W01CommonConst.ONE_YES);
        message.outMessage("I00", W01CommonConst.TWO_NO);
        Scanner scan = new Scanner(System.in);
        int num1 = scan.nextInt();

        switch (num1) {

        // フォルダ内のすべてを対象にする
        case W01CommonConst.NUM_ONE:

            // 対象にするファイルを選択させる
            message.outMessage("I04", "対象にするファイルを");
            message.outMessage("I00", W01CommonConst.ONE_TSV);
            message.outMessage("I00", W01CommonConst.TWO_CSV);
            int num2 = scan.nextInt();
            switch (num2) {
            // フォルダ内のtsvファイルを対象にする
            case W01CommonConst.NUM_ONE:
                AllFileSorting(num2);
                // 正常終了の場合は0を返す
                return W01CommonConst.SUCCESS;

                // フォルダ内のcsvファイルを対象にする
            case W01CommonConst.NUM_TWO:
                AllFileSorting(num2);
                // 正常終了の場合は0を返す
                return W01CommonConst.SUCCESS;

                // それ以外
            default:
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E04", "1~2で");
                // 異常終了の場合は1を返す
                return W01CommonConst.ERROR;
            }

        case W01CommonConst.NUM_TWO:

            String returnValue = FileSelect();
            if (W01CommonConst.ERROR.equals(returnValue)) {
                return W01CommonConst.ERROR;
            }
            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;

            // それ以外
        default:
            W01CommonUtil messege = new W01CommonUtil();
            messege.outMessage("E04", "1~2で");
            // 異常終了の場合は1を返す
            return W01CommonConst.ERROR;
        }

    }

    /**
     * ファイル名の拡張子を判断
     * @param なし
     * @return 処理結果を返却する
     */
    @SuppressWarnings("resource")
    public String FileSelect() {

        W01CommonUtil message = new W01CommonUtil();

        //ファイル名を入力させる
        message.outMessage("I03", "変換したいファイル名");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();

        //拡張子判断
        String extension = fileName.substring(fileName.lastIndexOf(W01CommonConst.CONST_ST_PERIOD));

        //TSVファイルを入力された場合
        if (extension.equals(W01CommonConst.CONST_EXTENSION_TSV)) {
            // ファイル変換（TSV⇒CSV）
            String returnValue1 = W01ConvertFileTsvToCsv.ConvertFileTsvToCsv(fileName);
            if (W01CommonConst.ERROR.equals(returnValue1)) {
                return W01CommonConst.ERROR;
            }

            //CSVファイルを入力された場合
        } else if (extension.equals(W01CommonConst.CONST_EXTENSION_CSV)) {
            // ファイル変換（CSV⇒TSV）
            String returnValue2 = W01ConvertFileCsvToTsv.ConvertFileCsvToTsv(fileName);
            if (W01CommonConst.ERROR.equals(returnValue2)) {
                return W01CommonConst.ERROR;
            }

            //拡張子が正しくない場合
        } else {
            message.outMessage("I03", "正しいファイル名");
            return W01CommonConst.ERROR;
        }
        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;
    }

    /**
     * フォルダ内一括処理
     * @param num2（ファイルの種類）
     * @return 処理結果を返却する
     */
    public String AllFileSorting(int num2) {

        String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH); // ログインパスワード

        File file1 = new File(path);
        // ファイルオブジェクトを配列で取得
        File fileArray1[] = file1.listFiles();
        // 判別した後のtsvファイル名を入れるリスト
        List<String> tsvList = new ArrayList<String>(0);
        // 判別した後のcsvファイル名を入れるリスト
        List<String> csvList = new ArrayList<String>(0);
        // ファイルの一覧を取得
        for (File f : fileArray1) {
            // isFileメソッドでファイルを判別
            if (f.isFile()) {
                // System.out.println(f.toString());//ファイルを表示
                String fileName = f.toString();

                //拡張子判断
                String extension =
                        fileName.substring(fileName.lastIndexOf(W01CommonConst.CONST_ST_PERIOD));
                //TSVファイルの場合
                if (extension.equals(W01CommonConst.CONST_EXTENSION_TSV)) {
                    tsvList.add(fileName);
                    //CSVファイルの場合
                } else if (extension.equals(W01CommonConst.CONST_EXTENSION_CSV)) {
                    csvList.add(fileName);
                }
            }
        }
        if (W01CommonConst.NUM_ONE == num2) {
            // tsvファイルを対象
            String returnValue1 = W01ConvertFileTsvToCsv.AllFileTsvToCsv(tsvList);
            if (W01CommonConst.ERROR.equals(returnValue1)) {
                return W01CommonConst.ERROR;
            }
        }else {
            // csvファイルを対象
            String returnValue2 = W01ConvertFileCsvToTsv.AllFileCsvToTsv(csvList);
            if (W01CommonConst.ERROR.equals(returnValue2)) {
                return W01CommonConst.ERROR;
            }
        }
        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;
    }

}
