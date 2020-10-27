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
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * W01ConvertFileTsvToCsvc
 *
 * tsvからcsvの変換を行う
 *
 * @author hara
 * @varsion
 *
 */
public class W01ConvertFileTsvToCsv {

    private static W01CommonUtil message = new W01CommonUtil();

    /**
     * main
     *
     * メイン処理
     *
     * @return 処理結果を返却する 0:正常終了 1:異常終了
     */
    @SuppressWarnings("resource")
    public String main() {

        System.out.println("ファイルの格納先（絶対パス）を入力ください：");
        Scanner scan = new Scanner(System.in);
        // ユーザー入力
        String readFile = scan.next();

        if (checkInputPath(readFile) == false) {
            // 異常終了
            return W01CommonConst.ERROR;
        }
        String createFile = readFile.replace(W01CommonConst.CONST_EXTENSION_TSV, W01CommonConst.CONST_EXTENSION_CSV);
        // tsvファイル読み込みメソッドを呼び出し
        List<String> list = ReadFile(readFile);
        if (list == null) {
            message.outMessage("I03", "タブ区切りとなっているTSVファイル");
            // 異常終了
            return W01CommonConst.ERROR;
        }

        // Csvファイル出力メソッドを呼び出し
        int result = CreateCsv(createFile, list);
        if (result == 0) {
            // 正常終了
            return W01CommonConst.SUCCESS;
        } else {
            // 異常終了
            return W01CommonConst.ERROR;
        }

    }

    /**
     * ReadFile
     *
     * tsvファイル読み込みメソッド
     *
     * @param readFileName 読み込み対象ファイル名
     * @return List<String[]> 読み込み対象ファイルの内容
     */
    @SuppressWarnings("resource")
    public static List<String> ReadFile(String readFileName) {
        List<String> list = new ArrayList<String>(0);
        try {
            File file = new File(readFileName);
            String str = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), W01CommonConst.CONST_CHAR_CODE_UTF8));

            // 読み込み対象ファイルの内容をリストに追加
            while ((str = br.readLine()) != null) {
                String data = str;
                if (data.contains(W01CommonConst.CONST_ST_COMMA)) {
                    return null;
                }
                String tmpArray = data.replace(W01CommonConst.CONST_ST_TAB, W01CommonConst.CONST_ST_COMMA);
                list.add(tmpArray);
            }
            br.close();
        } catch (Exception e) {
            // 読み込みにて異常終了
            return null;
        }
        // 読み込み正常終了
        return list;
    }

    /**
     * CreateCsv
     *
     * Csvファイル出力メソッド
     *
     * @param createFileName
     * @param list
     * @return
     */
    public static int CreateCsv(String createFileName, List<String> list) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(createFileName), W01CommonConst.CONST_CHAR_CODE_UTF8)));

            // ,を追加し、ファイル出力
            for (String tmpStringArray : list) {
                pw.println(String.join(W01CommonConst.CONST_ST_COMMA, tmpStringArray));
            }
            // ファイルを閉じる
            pw.close();
            return 0;
        } catch (Exception e) {
            // ファイル出力で異常終了
            return 1;
        }

    }

    public static boolean checkInputPath(String readFile) {

        // 拡張子チェック
        if (!readFile.endsWith(W01CommonConst.CONST_EXTENSION_TSV)) {
            message.outMessage("I03", "TSVファイル");
            return false;
        }
        // ファイルの存在確認
        File file = new File(readFile);
        if (!file.exists()) {
            message.outMessage("I03", "正しい格納先（絶対パス）");
            return false;
        }
        // ファイルサイズの確認
        if (file.length() == 0) {
            message.outMessage("E03", "ファイル内にデータ");
            return false;
        }
        return true;
    }
}