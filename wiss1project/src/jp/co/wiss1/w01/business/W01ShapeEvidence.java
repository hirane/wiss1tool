package jp.co.wiss1.w01.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.co.wiss1.w01.common.W01CommonUtil;

//

public class W01ShapeEvidence {

	public static void main(String[] args) throws InterruptedException {

		W01CommonUtil message = new W01CommonUtil();

		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);

		System.out.println("csvファイルもしくはtsvファイルの格納先（絶対パス）を入力してください：");

		//ファイルパスの取得
		String str = null;
		try {
			str = br.readLine();
			br.close();
		} catch (IOException e) {
			message.outMessage("E02", "ファイルの取得");
			System.exit(1);
		}

		//ファイルが存在するかのチェック
		File file = new File(str);
		if (file.exists()) {
		} else {
			System.out.println("ファイルが存在しません");
			System.exit(1);
		}

		//ファイル名をパラメータとしてマクロに渡す
		try {
			Runtime rt = Runtime.getRuntime();
			Process process = rt.exec("cmd /c cscript C:\\wiss1workspeas\\W01ShapeEvidenceMacro.vbs " + str);
			int ret = process.waitFor();
			message.outMessage("I02", "エビデンス成型");
			System.exit(ret);
		} catch (IOException ex) {
			message.outMessage("E02", "エビデンス成型");
			System.exit(1);
		}

	}

}
