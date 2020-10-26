package jp.co.wiss1.w01.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * クラス概要 CSVファイルからTSVファイルへの書き換え
 *
 * @since 2020/10/02
 * @author Nishikawa Miyuko
 * @version 1.0.0
 */
public class W01ConvertFileCsvToTsv {

	/**
	 * CSVファイルからTSVファイルへの書き換え
	 * @param
	 * @return 処理結果を返却する
	 */
	public String main() {

		W01CommonUtil message = new W01CommonUtil();

		try {
			// CSVファイルのパスを取得
			System.out.println("ファイルの格納先（絶対パス）を入力ください：");
			Scanner scan = new Scanner(System.in);
			String readFile = scan.next();
			String createFile = readFile.replace(W01CommonConst.CONST_EXTENSION_CSV,
					W01CommonConst.CONST_EXTENSION_TSV);

			// ファイルがあるかの確認：無い場合⇒エラーを返す
			File file = new File(readFile);
			if (file.exists()) {
			} else {
				message.outMessage("I03", "正しい格納先（絶対パス）");
				return W01CommonConst.ERROR;
			}

			// CSVファイルの読み込み
			File fInputCsv = new File(readFile);
			BufferedReader br = new BufferedReader(new FileReader(fInputCsv));

			// 読み込んだファイルの拡張子の判断：TSVファイルを入力された場合⇒エラーを返す
			String name = fInputCsv.getName();
			String extension = name.substring(name.lastIndexOf("."));

			if (".tsv".equals(extension)) {
				br.close();
				message.outMessage("I03", "CSVファイルを");
				return W01CommonConst.ERROR;
			}

			// ファイル内にデータがあるかの確認：データが空の場合⇒エラーを返す
			File branchFile = new File(readFile);
			if (branchFile.length() == 0) {
				message.outMessage("E03", "ファイル内にデータ");
				return W01CommonConst.ERROR;
			}

			// TSVファイルを出力
			FileOutputStream fos = new FileOutputStream(createFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
			PrintWriter pOutputTsv = new PrintWriter(new BufferedWriter(osw));

			// ファイル書き換え
			String lineBefore;
			// 1行ずつCSVファイルを読み込む
			while ((lineBefore = br.readLine()) != null) {

				// カンマ区切りからタブ区切りへ
				String lineAfter = lineBefore.replace(W01CommonConst.CONST_ST_COMMA,
						W01CommonConst.CONST_ST_TAB);

				// タブ区切りにしたものを書き込み
				pOutputTsv.print(lineAfter);
				pOutputTsv.println(); // 改行

			}
			pOutputTsv.close();
			br.close();

			message.outMessage("I01", "CSVからTSVへのファイル変換");

			return W01CommonConst.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			message.outMessage("E02", "CSVからTSVへのファイル変換");
			return W01CommonConst.ERROR;

		}
	}

}
