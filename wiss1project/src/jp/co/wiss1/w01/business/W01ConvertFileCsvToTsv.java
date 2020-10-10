package project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class W01ConvertFileCsvToTsv {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

		try {
			File f = new File("C:\\Users\\WISS1_0073\\Desktop\\Java\\sample.csv");
			PrintWriter p = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("C:\\Users\\WISS1_0073\\Desktop\\Java\\test.tsv"), "Shift-JIS")));

			BufferedReader br = new BufferedReader(new FileReader(f));

			String line;
			// 1行ずつCSVファイルを読み込む
			while ((line = br.readLine()) != null) {
				String[] data = line.split(",", 0); // 行をカンマ区切りで配列に変換
				// 内容をセットする
				for (int i = 0; i < data.length; i++) {
					p.print(data[i]);
					if (i != data.length - 1) {
						p.print('\t');
					}
				}
				p.println(); // 改行

			}
			p.close();
			System.out.println("ファイル出力完了！");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
