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

    static W01CommonUtil message = new W01CommonUtil();

    /**
     * 処理を選択
     * @param なし
     * @return 処理結果を返却する
     */
    @SuppressWarnings("resource")
    public String convertFileSelect() {

        while (true) {
            // 全件を対象にするか選択
            message.outMessage("I06", "フォルダ内の全て");
            message.outMessage("I00", W01CommonConst.ONE_YES);
            message.outMessage("I00", W01CommonConst.TWO_NO);
            Scanner scan = new Scanner(System.in);
            String allNum = scan.next();

            switch (allNum) {

            // フォルダ内のすべてを対象にする
            case W01CommonConst.OPE_CH_ONE:

                while (true) {

                    // 対象にするファイルを選択させる
                    message.outMessage("I04", "対象にするファイルを");
                    message.outMessage("I00", W01CommonConst.ONE_TSV);
                    message.outMessage("I00", W01CommonConst.TWO_CSV);
                    String selectNum = scan.next();
                    switch (selectNum) {

                    // フォルダ内のtsvファイルを対象にする
                    case W01CommonConst.OPE_CH_ONE:
                        // 返却値を返す
                        return allFileSorting(selectNum);

                    // フォルダ内のcsvファイルを対象にする
                    case W01CommonConst.OPE_CH_TWO:
                        // 返却値を返す
                        return allFileSorting(selectNum);

                    // それ以外
                    default:
                        message.outMessage("E04", W01CommonConst.SELECT_ONE_AND_TWO);
                        // 異常値の場合はループさせる
                        continue;
                    }
                }

            case W01CommonConst.OPE_CH_TWO:
                // 返却値を返す
                return selectFile();

            // それ以外
            default:
                message.outMessage("E04", W01CommonConst.SELECT_ONE_AND_TWO);
                // 異常値の場合はループさせる
                continue;
            }
        }
    }

    /**
     * ファイル名の拡張子を判断
     * @param なし
     * @return 処理結果を返却する
     */
    @SuppressWarnings("resource")
    public String selectFile() {

        //ファイル名を入力させる
        message.outMessage("I03", "変換したいファイル名");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.next();

        //拡張子判断
        String extension = WISS1CommonUtil.judgmentExtension(fileName);

        //TSVファイルを入力された場合
        if (W01CommonConst.CONST_EXTENSION_TSV.equals(extension)) {
            // ファイル変換（TSV⇒CSV）
            return W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);

            //CSVファイルを入力された場合
        } else if (W01CommonConst.CONST_EXTENSION_CSV.equals(extension)) {
            // selectNumは連動機能と判別させる数値
            boolean interlockingFlg = false;
            // ファイル変換（CSV⇒TSV）
            return W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);

            //拡張子が正しくない場合
        } else {
            message.outMessage("I03", "正しいファイル名");
            return W01CommonConst.ERROR;
        }
    }

    /**
     * フォルダ内一括処理
     * @param num2（ファイルの種類）
     * @return 処理結果を返却する
     */
    public String allFileSorting(String selectNum) {

        String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH); // ログインパスワード

        File file = new File(path);
        // ファイルオブジェクトを配列で取得
        File[] fileArray = file.listFiles();
        // 判別した後のtsvファイル名を入れるリスト
        List<String> tsvList = new ArrayList<String>(0);
        // 判別した後のcsvファイル名を入れるリスト
        List<String> csvList = new ArrayList<String>(0);
        // ファイルの一覧を取得
        for (File allFile : fileArray) {
            // isFileメソッドでファイルを判別
            if (allFile.isFile()) {
                message.outMessage("I00", allFile.toString());//ファイルを表示
                String fileName = allFile.toString();

                //拡張子判断
                String extension = WISS1CommonUtil.judgmentExtension(fileName);
                //TSVファイルの場合
                if (W01CommonConst.CONST_EXTENSION_TSV.equals(extension)) {
                    tsvList.add(fileName);
                    //CSVファイルの場合
                } else if (W01CommonConst.CONST_EXTENSION_CSV.equals(extension)) {
                    csvList.add(fileName);
                }
            }
        }
        if (W01CommonConst.OPE_CH_ONE.equals(selectNum)) {
            // tsvファイルを対象
            return W01ConvertFileTsvToCsv.allFileTsvToCsv(tsvList);

        } else {
            // csvファイルを対象
            return W01ConvertFileCsvToTsv.allFileCsvToTsv(csvList);
        }
    }
}
