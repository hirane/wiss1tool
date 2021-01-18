package jp.co.wiss1.w01.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * tsvからcsvの変換を行う
 *
 * @author a-hara
 * @since 2020/10/04
 * @varsion 1.0
 *
 */
public class W01ConvertFileTsvToCsv {

    private static W01CommonUtil message = new W01CommonUtil();

    /**
     * メイン処理
     *
     * @return 処理結果を返却する 0:正常終了 1:異常終了
     */
    public static String convertFileTsvToCsv(String fileName) {
        // プロパティからファイルパスを読み込む
        String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH);
        // ファイルパスとファイル名の結合
        String inputFile = (path + fileName);
        return checkFile(inputFile);
    }

    /**
    * ファイルチェックメソッド
    *
    * @param readFileName
    *            読み込み対象ファイル名
    * @return List<String[]> 読み込み対象ファイルの内容
    */
    public static String checkFile(String inputFile) {
        // ファイルのチェック
        int inFile = W01CommonUtil.checkInputPath(inputFile, W01CommonConst.CONST_EXTENSION_TSV);
        // 拡張子チェック
        if (inFile == W01CommonConst.FCHECK_ERROR_EXT) {
            message.outMessage("I03", "TSVファイル");
            return W01CommonConst.ERROR;
            // ファイルの存在チェック
        } else if (inFile == W01CommonConst.FCHECK_ERROR_EXS) {
            message.outMessage("I03", "正しい格納先（絶対パス）");
            return W01CommonConst.ERROR;
            // ファイルサイズチェック
        } else if (inFile == W01CommonConst.FCHECK_ERROR_EMP) {
            message.outMessage("E03", "ファイル内にデータ");
            return W01CommonConst.ERROR;
        }
        // ファイル名をTSVをCSVに変換
        String createFile = inputFile.replace(W01CommonConst.CONST_EXTENSION_TSV,
                W01CommonConst.CONST_EXTENSION_CSV);
        // tsvファイル読み込みメソッドを呼び出し
        List<String> contentsList = readFile(inputFile);
        if (contentsList == null) {
            message.outMessage("I03", "タブ区切りとなっているTSVファイル");
            // 異常終了
            return W01CommonConst.ERROR;
        }

        // Csvファイル出力メソッドを呼び出し
        return createCsv(createFile, contentsList);

    }

    /**
     * tsvファイル読み込みメソッド
     *
     * @param readFileName
     *            読み込み対象ファイル名
     * @return List<String[]> 読み込み対象ファイルの内容
     */
    @SuppressWarnings("resource")
    public static List<String> readFile(String readFileName) {
        List<String> contentsList = new ArrayList<String>(0);
        try {
            File file = new File(readFileName);
            String str = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),
                    W01CommonConst.CONST_CHAR_CODE_UTF8));

            // 読み込み対象ファイルの内容をリストに追加
            while ((str = br.readLine()) != null) {
                String data = str;
                if (data.contains(W01CommonConst.CONST_ST_COMMA)) {
                    return null;
                }
                // ファイルの中身をTSVをCSVに変換
                String tmpArray =
                        data.replace(W01CommonConst.CONST_ST_TAB, W01CommonConst.CONST_ST_COMMA);
                contentsList.add(tmpArray);
            }
            br.close();
        } catch (Exception e) {
            // 読み込みにて異常終了
            return null;
        }
        // 読み込み正常終了
        return contentsList;
    }

    /**
     * Csvファイル出力メソッド
     *
     * @param createFileName
     * @param list
     * @return 0:正常終了 1:異常終了
     */
    public static String createCsv(String createFileName, List<String> contentsList) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(createFileName), W01CommonConst.CONST_CHAR_CODE_UTF8)));

            // ,を追加し、ファイル出力
            for (String tmpStringArray : contentsList) {
                pw.println(String.join(W01CommonConst.CONST_ST_COMMA, tmpStringArray));
            }
            // ファイルを閉じる
            pw.close();
            // 正常終了
            message.outMessage("I01", "TSVファイルからCSVファイルへの変換");
            // 正常終了の場合は0を返す
            return W01CommonConst.SUCCESS;
        } catch (Exception e) {
            // 異常終了
            message.outMessage("E02", "TSVファイルからCSVファイルへの変換");
            ;
            // ファイル出力で異常終了
            return W01CommonConst.ERROR;
        }
    }

    /**
     * フォルダ内一括処理
     * @param tsvList（フォルダ内のファイル名(絶対パス)）
     * @return 処理結果を返却する
     */
    public static String allFileTsvToCsv(List<String> tsvList) {
        for (String tsvFile : tsvList) {
            // 対象のファイルが異常の時はそのファイルを飛ばす
            String returnNum = checkFile(tsvFile);
            if (returnNum.equals(W01CommonConst.ERROR)) {
                continue;
            }
        }
        message.outMessage("I01", "CSVからTSVへのファイル変換");
        // 正常終了の場合は0を返す
        return W01CommonConst.SUCCESS;

    }
}