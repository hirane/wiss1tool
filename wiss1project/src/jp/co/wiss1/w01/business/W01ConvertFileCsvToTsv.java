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
	@SuppressWarnings("resource")
	public String main() {

		W01CommonUtil message = new W01CommonUtil();

		try {
			// CSVファイルのパスを取得
			message.outMessage("I03", "ファイルの格納先（絶対パス）");
			Scanner scan = new Scanner(System.in);
			String inputPath = scan.next();
			String createFile = inputPath.replace(W01CommonConst.CONST_EXTENSION_CSV,
					W01CommonConst.CONST_EXTENSION_TSV);

			// CSVファイルの読み込み
			File fInputCsv = new File(inputPath);
			BufferedReader br = new BufferedReader(new FileReader(fInputCsv));

			// ファイルがあるかの確認：無い場合⇒エラーを返す
			//File file = new File(inputPath);
			//if (file.exists()) {
			//} else {
			//message.outMessage("I03", "正しい格納先（絶対パス）");
			//return W01CommonConst.ERROR;
			//}

			//読み込んだファイルの拡張子の判断：TSVファイルを入力された場合⇒エラーを返す
			//String name = fInputCsv.getName();
			//String extension = name.substring(name.lastIndexOf(W01CommonConst.CONST_ST_PERIOD));

			//if (extension.equals(W01CommonConst.CONST_EXTENSION_TSV)) {
			//br.close();
			//message.outMessage("I03", "CSVファイル");
			//return W01CommonConst.ERROR;
			//}

			//ファイルのチェックメソッド （0：正常、1：拡張子エラー、2:ファイルなしエラー、3:ファイル0バイトエラー）
			String extension = W01CommonConst.CONST_EXTENSION_CSV;

			if (W01CommonUtil.checkInputPath(inputPath, extension) == W01CommonConst.FCHECK_ERROR_EXT) {
				message.outMessage("I03", extension + "ファイル");
				return W01CommonConst.ERROR;
			} else if (W01CommonUtil.checkInputPath(inputPath, extension) == W01CommonConst.FCHECK_ERROR_EXS) {
				message.outMessage("I03", "正しい格納先（絶対パス）");
				return W01CommonConst.ERROR;
			} else if (W01CommonUtil.checkInputPath(inputPath, extension) == W01CommonConst.FCHECK_ERROR_EMP) {
				message.outMessage("E03", "ファイル内にデータ");
				return W01CommonConst.ERROR;
			}

			// TSVファイルを出力
			FileOutputStream fos = new FileOutputStream(createFile);
			OutputStreamWriter osw = new OutputStreamWriter(fos, W01CommonConst.CONST_CHAR_CODE_UTF8);
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
