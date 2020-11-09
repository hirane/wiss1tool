package jp.co.wiss1.w01.business;

import java.io.IOException;
import java.util.Scanner;

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

    /**
     * エビデンス整形（EXCEL出力）するマクロの呼出しの主処理
     *
     * @param なし
     * @return String（0:正常終了 1:異常終了）
     */
    @SuppressWarnings("resource")
    public String main() {

        W01CommonUtil message = new W01CommonUtil();

        message.outMessage("I03", "csvファイルもしくはtsvファイルの格納先（絶対パス）");

        Scanner sc = new Scanner(System.in);

        //ファイルパスの取得
        String str = sc.nextLine();

        //インプットファイルの確認
        int result = W01CommonUtil.checkInputPath(str, W01CommonConst.CONST_EXTENSION_CSV);
        if (result == W01CommonConst.FCHECK_ERROR_EXT) {
            result = W01CommonUtil.checkInputPath(str, W01CommonConst.CONST_EXTENSION_TSV);
            if (result == W01CommonConst.FCHECK_ERROR_EXT) {
                message.outMessage("E04", "csvファイルもしくはtsvファイルの格納先（絶対パス）");
                //異常終了
                return W01CommonConst.ERROR;
            }
        } else if (result == W01CommonConst.FCHECK_ERROR_EXS) {
            message.outMessage("E03", "ファイル");
            //異常終了
            return W01CommonConst.ERROR;

        } else if (result == W01CommonConst.FCHECK_ERROR_EMP) {
            message.outMessage("E03", "ファイル内にデータ");
            //異常終了
            return W01CommonConst.ERROR;

        }

        //ファイル名をパラメータとしてマクロに渡す
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(W01CommonConst.COMMAND + " " + str);
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

}
