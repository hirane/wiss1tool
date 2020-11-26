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

	/**
	 * エビデンス整形（EXCEL出力）するマクロの呼出しの主処理
	 *
	 * @param なし
	 * @return String（0:正常終了 1:異常終了）
	 */
	@SuppressWarnings("resource")
	public String main() {
		W01CommonUtil message = new W01CommonUtil();

		// 全件を対象にするか選択
		message.outMessage("I06", "フォルダ内の全て");
		message.outMessage("I00", W01CommonConst.ONE_YES);
		message.outMessage("I00", W01CommonConst.TWO_NO);

		Scanner scan = new Scanner(System.in);
		int num1 = scan.nextInt();

		switch (num1) {

		// フォルダ内のすべてを対象にする
		case W01CommonConst.NUM_ONE:
			// 対象にするファイルを選択させる
			message.outMessage("I04", "対象にするファイルを");
			message.outMessage("I00", W01CommonConst.ONE_TSV);
			message.outMessage("I00", W01CommonConst.TWO_CSV);
			int num2 = scan.nextInt();
			// 対象をtsvにするかcsvにするか判断
			switch (num2) {

			// フォルダ内のtsvファイルを対象にする
			case W01CommonConst.NUM_ONE:
				AllFileSorting(num2);
				// フォルダ内のcsvファイルを対象にする
			case W01CommonConst.NUM_TWO:
				AllFileSorting(num2);
			default:
				W01CommonUtil messege = new W01CommonUtil();
				messege.outMessage("E04", "1~2で");
				// 異常終了の場合は1を返す
				return W01CommonConst.ERROR;
			}

			// フォルダ内のすべてを対象にしない
		case W01CommonConst.NUM_TWO:
			//ファイル名を入力させる
			message.outMessage("I03", "変換したいファイル名");
			Scanner sc = new Scanner(System.in);
			String fileName = sc.next();

			// プロパティからファイルパスを読み込む
			String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH);

			// ファイルパスとファイル名の結合
			String fileNamePath = (path + fileName);

			EvidenceOutput(fileNamePath);
		default:
			W01CommonUtil messege = new W01CommonUtil();
			messege.outMessage("E04", "1~2で");
			// 異常終了の場合は1を返す
			return W01CommonConst.ERROR;

		}

	}

	/**
	 * ファイルの拡張子・存在・サイズをチェックし、エビデンスを整形
	 * @param str 絶対パス
	 * @return 処理結果を返却する
	 */
	@SuppressWarnings("unused")
	public static String EvidenceOutput(String str) {
		// TODO 自動生成されたメソッド・スタブ

		W01CommonUtil message = new W01CommonUtil();
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
			// 処理を走らせる 実行する為のメソッド
			Runtime rt = Runtime.getRuntime();
			//
			Process process = rt.exec(W01CommonConst.COMMAND + " " + str);
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
	 * @param num2（ファイルの種類）
	 * @return 処理結果を返却する
	 */
	public String AllFileSorting(int num2) {

		String path = WISS1CommonUtil.getProperty(W01CommonConst.PRO_OUT_PATH); // ログインパスワード

		File file1 = new File(path);
		// ファイルオブジェクトを配列で取得
		File fileArray1[] = file1.listFiles();
		// 判別した後のtsvファイル名を入れるリスト
		List<String> tsvList = new ArrayList<String>(0);
		// 判別した後のcsvファイル名を入れるリスト
		List<String> csvList = new ArrayList<String>(0);
		// ファイルの一覧を取得
		for (File f : fileArray1) {
			// isFileメソッドでファイルを判別
			if (f.isFile()) {
				// System.out.println(f.toString());//ファイルを表示
				String fileName = f.toString();

				//拡張子判断
				String extension = fileName.substring(fileName.lastIndexOf(W01CommonConst.CONST_ST_PERIOD));
				//TSVファイルの場合
				if (extension.equals(W01CommonConst.CONST_EXTENSION_TSV)) {
					tsvList.add(fileName);
					//CSVファイルの場合
				} else if (extension.equals(W01CommonConst.CONST_EXTENSION_CSV)) {
					csvList.add(fileName);
				}
			}
		}
		if (W01CommonConst.NUM_ONE == num2) {
			// フォルダ内のtsvファイル分繰り返す
			for (String tsvFile : tsvList) {
				EvidenceOutput(tsvFile);
			}
		} else {
			// フォルダ内のcsvファイル分繰り返す
			for (String csvFile : csvList) {
				EvidenceOutput(csvFile);
			}
		}
		// 正常終了の場合は0を返す
		return W01CommonConst.SUCCESS;
	}

}
