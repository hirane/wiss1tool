package jp.co.wiss1.w01.business;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * エビデンス整形（EXCEL出力）するマクロを呼び出すクラス
 *
 * @since 2020.10.04
 * @author Soma Nakamura
 * @version 1.0.0
 */
public class W01ShapeEvidence {

    @SuppressWarnings("resource")
    public String main() {

        W01CommonUtil message = new W01CommonUtil();

        message.outMessage("I03", "csvファイルもしくはtsvファイルの格納先（絶対パス）");

        Scanner sc = new Scanner(System.in);

        //ファイルパスの取得
        String str = sc.nextLine();

        //ファイルが存在するかのチェック
        File file = new File(str);
        if (file.exists()) {
        } else {
            message.outMessage("E03", "ファイル");
            return W01CommonConst.ERROR;
        }

        //ファイル名をパラメータとしてマクロに渡す
        try {
            Runtime rt = Runtime.getRuntime();
            Process process = rt.exec(W01CommonConst.COMMAND + " " + str);
            int ret = process.waitFor();
            if (ret == 0) {
                message.outMessage("I02", "EXCELファイルへのエビデンス成型");
                return W01CommonConst.SUCCESS;
            } else {
                message.outMessage("E02", "EXCELファイルへのエビデンス成型");
                return W01CommonConst.ERROR;
            }

        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
            message.outMessage("E02", "EXCELファイルへのエビデンス成型");
            return W01CommonConst.ERROR;
        }

    }

}
