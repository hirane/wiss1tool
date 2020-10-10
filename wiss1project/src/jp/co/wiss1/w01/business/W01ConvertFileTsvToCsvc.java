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


/**
 * W01ConvertFileTsvToCsvc
 * 
 * tsvからcsvの変換を行う
 * 
 * @author hara
 *
 */
public class W01ConvertFileTsvToCsvc {


	
	/**
	 * main
	 * 
	 * 動作確認用メソッド。
	 * メインメニューからは実行されない
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		main();
	}
	
	
	/**
	 * main
	 * 
	 * メイン処理
	 * 
	 * @return 処理結果を返却する 0:正常終了 1:異常終了
	 */
	public static int main() {
		System.out.println("ファイルの格納先（絶対パス）を入力ください：");
		Scanner scan = new Scanner(System.in);
		// ユーザー入力
		String readFile = scan.next();
		String createFile = readFile.replace(".tsv", ".csv");
		
		// tsvファイル読み込みメソッドを呼び出し
		List<String[]> list = ReadFile(readFile);
		
		// Csvファイル出力メソッドを呼び出し
		int result = CreateCsv(createFile, list);
		if(result == 0 ){
			// 正常終了
			return 0;
		} else {
			// 異常終了
			return 1;
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
	public static List<String[]> ReadFile(String readFileName) {
		List<String[]> list = new ArrayList<String[]>(0);
		try {
			int i = 0;
			File f = new File(readFileName);
			String str = null;
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f), "UTF-8"));

			// 読み込み対象ファイルの内容をリストに追加
			while ((str = br.readLine()) != null) {
				String data = str;
				String[] tmpArray = data.split("\t");
				for (i = 0; i < tmpArray.length; i++) {
					tmpArray[i] = tmpArray[i];
				}
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
	public static int CreateCsv(String createFileName, List<String[]> list) {
		try {
			PrintWriter pw = new PrintWriter(
					new BufferedWriter(new OutputStreamWriter(new FileOutputStream(createFileName), "UTF-8")));
			
			// ,を追加し、ファイル出力
			for (String[] tmpStringArray : list) {
				pw.println(String.join(",", tmpStringArray));
			}
			
			// ファイルを閉じる
			pw.close();
			
			return 0;
		} catch (Exception e) {
			
			// ファイル出力で異常終了
			System.out.println(e);
			return 1;
		}
		
}
}