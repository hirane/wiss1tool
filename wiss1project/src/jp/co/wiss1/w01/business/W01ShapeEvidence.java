package jp.co.wiss1.w01.business;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * エビデンス整形（EXCEL出力）するマクロの呼出しクラス
 *
 * @since 2020/10/04
 * @author s-nakamura
 * @version 1.0
 */
public class W01ShapeEvidence {

    static W01CommonUtil message = new W01CommonUtil();

    /**
     * エビデンス整形（EXCEL出力）するマクロの呼出しの主処理
     *
     * @param なし
     * @return String（0:正常終了 1:異常終了）
     */
    @SuppressWarnings("resource")
    public String shapeEvidence() {

        while (true) {

            // 全件を対象にするか選択
            message.outMessage("I06", "フォルダ内の全て");
            message.outMessage("I00", W01CommonConst.ONE_YES);
            message.outMessage("I00", W01CommonConst.TWO_NO);

            Scanner scan = new Scanner(System.in);
            String yesOrNo = scan.next();

            switch (yesOrNo) {

            // フォルダ内のすべてを対象にする（W01CommonConst.ONE_YES）
            case W01CommonConst.OPE_CH_ONE:
                // 対象にするファイルを選択させる
                message.outMessage("I04", "対象にするファイル");
                message.outMessage("I00", W01CommonConst.ONE_TSV);
                message.outMessage("I00", W01CommonConst.TWO_CSV);
                String tsvOrCsv = scan.next();
                // 対象をtsvにするかcsvにするか判断
                switch (tsvOrCsv) {

                // フォルダ内のtsvファイルを対象にする （W01CommonConst.ONE_TSV）
                case W01CommonConst.OPE_CH_ONE:
                    return allFileSorting(tsvOrCsv);
                // フォルダ内のcsvファイルを対象にする（W01CommonConst.TWO_CSV）
                case W01CommonConst.OPE_CH_TWO:
                    return allFileSorting(tsvOrCsv);
                default:
                    W01CommonUtil messege = new W01CommonUtil();
                    messege.outMessage("E04", "1または2");
                    return W01CommonConst.ERROR;
                }

                // フォルダ内のすべてを対象にしない（W01CommonConst.TWO_NO）
            case W01CommonConst.OPE_CH_TWO:
                //ファイル名を入力させる
                message.outMessage("I03", "変換したいファイル名");
                Scanner sc = new Scanner(System.in);
                String fileName = sc.nextLine();
                fileName = fileName.replaceAll("^[ |　|\\n|\\t]+|[ |　|\\n|\\t]+$", "");

                // プロパティからファイルパスを読み込む
                String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH);

                // ファイルパスとファイル名の結合
                String fileNamePath = (path + fileName);

                return evidenceOutput(fileNamePath);
            default:
                W01CommonUtil messege = new W01CommonUtil();
                messege.outMessage("E04", "1または2");
                return W01CommonConst.ERROR;

            }
        }
    }

    /**
     * ファイルの拡張子・存在・サイズをチェックし、エビデンスを整形
     * @param filePath 絶対パス
     * @return 処理結果を返却する
     */
    public static String evidenceOutput(String filePath) {

        //インプットファイルのチェック
        int result = W01CommonUtil.checkInputPath(filePath, W01CommonConst.CONST_EXTENSION_CSV);
        // 異なる拡張子を入力された場合
        if (result == W01CommonConst.FCHECK_ERROR_EXT) {
            result = W01CommonUtil.checkInputPath(filePath, W01CommonConst.CONST_EXTENSION_TSV);
            if (result == W01CommonConst.FCHECK_ERROR_EXT) {
                message.outMessage("E04", "csvファイルもしくはtsvファイルの格納先（絶対パス）");
                //異常終了
                return W01CommonConst.ERROR;
            }
            // ファイルが存在しない場合
        } else if (result == W01CommonConst.FCHECK_ERROR_EXS) {
            message.outMessage("E03", "ファイル");
            //異常終了
            return W01CommonConst.ERROR;

            // ファイルが空の場合
        } else if (result == W01CommonConst.FCHECK_ERROR_EMP) {
            message.outMessage("E03", "ファイル内にデータ");
            //異常終了
            return W01CommonConst.ERROR;

        }

        //ファイル名をパラメータとしてマクロに渡す
        try {
            // 処理を走らせる 実行する為のメソッド
            Runtime rt = Runtime.getRuntime();
            // マクロの実行
            Process process = rt.exec(W01CommonConst.COMMAND + " " + filePath);
            // waitFor 処理が終わったら0を返す 1だったら異常終了
            int ret = process.waitFor();
            if (ret == W01CommonConst.NUM_ZERO) {
                message.outMessage("I02", "EXCELファイルへのエビデンス成型");
                //正常終了
                return W01CommonConst.SUCCESS;
            } else {
                message.outMessage("E02", "EXCELファイルへのエビデンス成型");
                //異常終了
                return W01CommonConst.ERROR;
            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            message.outMessage("E02", "マクロの呼び出し");
            //異常終了
            return W01CommonConst.ERROR;
        }
    }

    /**
     * フォルダ内一括処理
     * @param tsvOrCsv（ファイルの種類）
     * @return 処理結果を返却する
     */
    public String allFileSorting(String tsvOrCsv) {

        String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH); // ログインパスワード

        File file1 = new File(path);
        // ファイルオブジェクトを配列で取得
        File[] fileArray = file1.listFiles();
        // 判別した後のtsvファイル名を入れるリスト
        List<String> tsvList = new ArrayList<String>(0);
        // 判別した後のcsvファイル名を入れるリスト
        List<String> csvList = new ArrayList<String>(0);
        int successCount = W01CommonConst.NUM_ZERO;
        // ファイルの一覧を取得
        for (File f : fileArray) {
            // isFileメソッドでファイルを判別
            if (f.isFile()) {

                String fileName = f.toString();

                //拡張子判断
                String extension =
                        fileName.substring(fileName.lastIndexOf(W01CommonConst.CONST_ST_PERIOD));
                //TSVファイルの場合
                if (W01CommonConst.CONST_EXTENSION_TSV.equals(extension)) {
                    tsvList.add(fileName);
                    //CSVファイルの場合
                } else if (W01CommonConst.CONST_EXTENSION_CSV.equals(extension)) {
                    csvList.add(fileName);
                }
            }
        }
        //入力値が”1”かつ、tsvファイルが0ではないとき
        if (W01CommonConst.OPE_CH_ONE.equals(tsvOrCsv)
                && tsvList.size() != W01CommonConst.NUM_ZERO) {
            // フォルダ内のtsvファイル分繰り返す
            for (String tsvFile : tsvList) {
                successCount++;
                //ファイルを表示
                message.outMessage("I00", tsvFile.toString());
                evidenceOutput(tsvFile);
            }
            message.outMessage("I01",
                    tsvList.size() + "/" + successCount + "件" + "TSVファイルのエビデンス成型");
            //入力値が”2”かつ、ｃsvファイルが0ではないとき
        } else if (W01CommonConst.OPE_CH_TWO.equals(tsvOrCsv)
                && csvList.size() != W01CommonConst.NUM_ZERO) {
            // フォルダ内のcsvファイル分繰り返す
            for (String csvFile : csvList) {
                successCount++;
                //ファイルを表示
                message.outMessage("I00", csvFile.toString());
                evidenceOutput(csvFile);
            }
            message.outMessage("I01",
                    csvList.size() + "/" + successCount + "件" + "CSVファイルのエビデンス成型");
        } else {
            message.outMessage("I00", "対象のファイルがありません。");
        }

        return W01CommonConst.SUCCESS;
    }
}
