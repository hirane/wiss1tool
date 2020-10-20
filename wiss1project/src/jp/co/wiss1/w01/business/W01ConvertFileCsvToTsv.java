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

/*
 * @author nishikawa
 * 2020/10/02
 * 1.0
 */

public class W01ConvertFileCsvToTsv {

	/**
	 * CSVファイルからTSVファイルへの書き換え
	 * @param
	 * @return 処理結果を返却する
	 */
	public String main() {

		try {
			// CSVファイルのパスを取得
			System.out.println("ファイルの格納先（絶対パス）を入力ください：");
			Scanner scan = new Scanner(System.in);
			String readFile = scan.next();
			String createFile = readFile.replace(W01CommonConst.CONST_EXTENSION_CSV,
					W01CommonConst.CONST_EXTENSION_TSV);

			// CSVファイルの読み込み
			File fInputCsv = new File(readFile);
			BufferedReader br = new BufferedReader(new FileReader(fInputCsv));

			// 読み込んだファイルの拡張子の判断 （TSVファイルを入力された場合はエラーを返す）
			String name = fInputCsv.getName();
			String extension = name.substring(name.lastIndexOf("."));

			if (".tsv".equals(extension)) {
				br.close();
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

			return W01CommonConst.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return W01CommonConst.ERROR;

		}
	}

}
