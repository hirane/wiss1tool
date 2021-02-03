package jp.co.wiss1.w01.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    static W01CommonUtil message = new W01CommonUtil();

    /**
     * CSVファイルからTSVファイルへの書き換え
     * @param なし
     * @return 処理結果を返却する
     */
    public static String convertFileCsvToTsv(String fileName, boolean interlockingFlg) {
        //StringBuffer inputFile = new StringBuffer();

        // プロパティからファイルパスを読み込む
        String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH);
        // ファイルパスとファイル名の結合
        String inputFile = (path + fileName);

        return checkFile(inputFile, interlockingFlg);

    }

    /**
     * ファイルチェックを行い、CSVファイルからTSVファイルへの書き換え出力
     * @param csvFile(ファイル名(絶対パス)),連動機能と判別させるフラグ
     * @return 処理結果を返却する
     */
    @SuppressWarnings("resource")
    public static String checkFile(String csvFile, boolean interlockingFlg) {
        // 新ファイルの拡張子書き換え
        String createFile = csvFile.replace(W01CommonConst.CONST_EXTENSION_CSV,
                W01CommonConst.CONST_EXTENSION_TSV);

        // CSVファイルの読み込み
        File fInputCsv = new File(csvFile);
        try {

            //ファイルのチェックメソッド （0：正常、1：拡張子エラー、2:ファイルなしエラー、3:ファイル0バイトエラー）
            String extension = W01CommonConst.CONST_EXTENSION_CSV;

            if (W01CommonUtil.checkInputPath(csvFile,
                    extension) == W01CommonConst.FCHECK_ERROR_EXT) {
                message.outMessage("I03", extension + "ファイル");
                return W01CommonConst.ERROR;
            } else if (W01CommonUtil.checkInputPath(csvFile,
                    extension) == W01CommonConst.FCHECK_ERROR_EXS) {
                message.outMessage("E03", "フォルダまたはファイル");
                return W01CommonConst.ERROR;
            } else if (W01CommonUtil.checkInputPath(csvFile,
                    extension) == W01CommonConst.FCHECK_ERROR_EMP) {
                message.outMessage("E03", "ファイル内にデータ");
                return W01CommonConst.ERROR;
            }

            BufferedReader br = new BufferedReader(new FileReader(fInputCsv));
            // ファイル書き換え
            String lineBefore = null;
            String lineAfter = null;
            // ファイル内容を格納するリスト
            List<String> contentsList = new ArrayList<String>(0);
            // 1行ずつCSVファイルを読み込む
            while ((lineBefore = br.readLine()) != null) {
                if (lineBefore.contains(W01CommonConst.CONST_ST_TAB)) {
                    br.close();
                    message.outMessage("I03", "コンマ区切りとなっているCSVファイル");
                    return W01CommonConst.ERROR;
                }
                // カンマ区切りからタブ区切りへ
                lineAfter = lineBefore.replace(W01CommonConst.CONST_ST_COMMA,
                        W01CommonConst.CONST_ST_TAB);
                contentsList.add(lineAfter);
            }
            // TSVファイルを出力
            FileOutputStream newFile = new FileOutputStream(createFile);
            OutputStreamWriter osw =
                    new OutputStreamWriter(newFile, W01CommonConst.CONST_CHAR_CODE_UTF8);
            PrintWriter pOutputTsv = new PrintWriter(new BufferedWriter(osw));

            // タブ区切りにしたものを書き込み
            for(String line: contentsList) {
                pOutputTsv.println(line);
            }
            pOutputTsv.close();
            br.close();

            message.outMessage("I01", "CSVからTSVへのファイル変換");
            while (true) {
                if (interlockingFlg) {
                    message.outMessage("I00", "EXCELファイルに出力しますか：");
                    message.outMessage("I00", W01CommonConst.ONE_YES);
                    message.outMessage("I00", W01CommonConst.TWO_NO);
                    Scanner scan = new Scanner(System.in);
                    String interlockingnum = scan.next();

                    switch (interlockingnum) {

                    // エビデンス成型処理を行う
                    case W01CommonConst.OPE_CH_ONE:
                        // TSVファイル名を渡す(絶対パス)
                        return W01ShapeEvidence.evidenceOutput(createFile);

                    case W01CommonConst.OPE_CH_TWO:
                        return W01CommonConst.SUCCESS;

                        // それ以外
                    default:

                        message.outMessage("E04", W01CommonConst.SELECT_ONE_AND_TWO);
                        return W01CommonConst.ERROR;
                    }
                } else {
                    // 連動機能以外の場合
                    return W01CommonConst.SUCCESS;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            message.outMessage("E02", "CSVからTSVへのファイル変換");
            return W01CommonConst.ERROR;

        }

    }

    /**
     * フォルダ内一括処理
     * @param csvList（フォルダ内のファイル名(絶対パス)）
     * @return 処理結果を返却する
     */
    public static String allFileCsvToTsv(List<String> csvList) {
        // selectNumは連動機能と判別させる数値
        boolean interlockingFlg = false;
        int successCount = W01CommonConst.NUM_ZERO;
        for (String csvFile : csvList) {
            //対象のファイルを表示
            message.outMessage("I00", csvFile.replace("\\", "\\\\"));
            // 対象のファイルが異常の時はそのファイルを飛ばす
            String returnNum = checkFile(csvFile, interlockingFlg);
            if (returnNum.equals(W01CommonConst.ERROR)) {
                continue;
            }
            //正常に処理した件数のカウント
            successCount++;
        }
        message.outMessage("I01", successCount + "/" + csvList.size() + "件のCSVからTSVへのファイル変換");
        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;

    }

}
