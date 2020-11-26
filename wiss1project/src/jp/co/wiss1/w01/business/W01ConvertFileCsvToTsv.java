package jp.co.wiss1.w01.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * クラス概要 CSVファイルからTSVファイルへの書き換え
 *
 * @author m-nishikawa
 * @since 2020/10/02
 * @version 1.0
 */
public class W01ConvertFileCsvToTsv {

    /**
     * CSVファイルからTSVファイルへの書き換え
     * @param なし
     * @return 処理結果を返却する
     */
    public static String ConvertFileCsvToTsv(String fileName) {
        //StringBuffer inputFile = new StringBuffer();
        W01CommonUtil message = new W01CommonUtil();

        // プロパティからファイルパスを読み込む
        String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH);
        // ファイルパスとファイル名の結合
        String inputFile = (path + fileName);
        // selectNumは連動機能と判別させる数値
        String selectNum = null;
        String returnValue = FileCheck(inputFile, selectNum);
        if (W01CommonConst.ERROR.equals(returnValue)) {
            message.outMessage("E02", "CSVからTSVへのファイル変換");
            return W01CommonConst.ERROR;
        }

        return W01CommonConst.SUCCESS;
    }

    @SuppressWarnings("resource")
    public static String FileCheck(String csvFile, String selectNum) {
        W01CommonUtil message = new W01CommonUtil();
        // 新ファイルの拡張子書き換え
        String createFile =
                csvFile.replace(W01CommonConst.CONST_EXTENSION_CSV,
                        W01CommonConst.CONST_EXTENSION_TSV);

        // CSVファイルの読み込み
        File fInputCsv = new File(csvFile);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fInputCsv));

            //ファイルのチェックメソッド （0：正常、1：拡張子エラー、2:ファイルなしエラー、3:ファイル0バイトエラー）
            String extension = W01CommonConst.CONST_EXTENSION_CSV;

            if (W01CommonUtil.checkInputPath(csvFile, extension) == W01CommonConst.FCHECK_ERROR_EXT) {
                message.outMessage("I03", extension + "ファイル");
                return W01CommonConst.ERROR;
            } else if (W01CommonUtil.checkInputPath(csvFile, extension) == W01CommonConst.FCHECK_ERROR_EXS) {
                message.outMessage("I03", "正しい格納先（絶対パス）");
                return W01CommonConst.ERROR;
            } else if (W01CommonUtil.checkInputPath(csvFile, extension) == W01CommonConst.FCHECK_ERROR_EMP) {
                message.outMessage("E03", "ファイル内にデータ");
                return W01CommonConst.ERROR;
            }
            // TSVファイルを出力
            FileOutputStream fos = new FileOutputStream(createFile);
            OutputStreamWriter osw =
                    new OutputStreamWriter(fos, W01CommonConst.CONST_CHAR_CODE_UTF8);
            PrintWriter pOutputTsv = new PrintWriter(new BufferedWriter(osw));

            // ファイル書き換え
            String lineBefore;
            // 1行ずつCSVファイルを読み込む
            while ((lineBefore = br.readLine()) != null) {

                // カンマ区切りからタブ区切りへ
                String lineAfter =
                        lineBefore.replace(W01CommonConst.CONST_ST_COMMA,
                                W01CommonConst.CONST_ST_TAB);

                // タブ区切りにしたものを書き込み
                pOutputTsv.print(lineAfter);
                pOutputTsv.println(); // 改行

            }
            pOutputTsv.close();
            br.close();

            message.outMessage("I01", "CSVからTSVへのファイル変換");

            if (W01CommonConst.SELECT_NINE == selectNum) {
                message.outMessage("I00", "EXCELファイルに出力しますか：");
                message.outMessage("I00", W01CommonConst.ONE_YES);
                message.outMessage("I00", W01CommonConst.TWO_NO);
                Scanner scan = new Scanner(System.in);
                int num = scan.nextInt();

                switch (num) {

                // エビデンス成型処理を行う
                case W01CommonConst.NUM_ONE:
                    W01ShapeEvidence.EvidenceOutput(createFile);

                case W01CommonConst.NUM_TWO:
                    return W01CommonConst.SUCCESS;

                    // それ以外
                default:
                    W01CommonUtil messege = new W01CommonUtil();
                    messege.outMessage("E04", "1~2で");
                    // 異常終了の場合は1を返す
                    return W01CommonConst.ERROR;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            message.outMessage("E02", "CSVからTSVへのファイル変換");
            return W01CommonConst.ERROR;

        }
        return W01CommonConst.SUCCESS;

    }

    /**
     * フォルダ内一括処理
     * @param csvList（フォルダ内のファイル名(絶対パス)）
     * @return 処理結果を返却する
     */
    public static String AllFileCsvToTsv(List<String> csvList) {
        W01CommonUtil message = new W01CommonUtil();
        // selectNumは連動機能と判別させる数値
        String selectNum = null;
        for (String csvFile : csvList) {
            String returnValue = FileCheck(csvFile, selectNum);
            if (W01CommonConst.ERROR.equals(returnValue)) {
                return W01CommonConst.ERROR;
            }
        }
        message.outMessage("I01", "CSVからTSVへのファイル変換");
        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;

    }

}
