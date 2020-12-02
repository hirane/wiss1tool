package jp.co.wiss1.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jp.co.wiss1.w01.common.W01CommonConst;

public class WISS1CommonUtil {

		// プロパティーファイル格納先
	    private static final String INIT_FILE_PATH = "conf/wiss1Common.properties";
	    // プロパティファイル
	    private static final Properties properties;

	    // プロパティファイル読み込み
	    static {
	        properties = new Properties();
	        try {
	            properties.load(Files.newBufferedReader(Paths.get(INIT_FILE_PATH), StandardCharsets.UTF_8));
	        } catch (IOException e) {
	            // ファイル読み込みに失敗
	            System.out.println(String.format("ファイルの読み込みに失敗しました。ファイル名:%s", INIT_FILE_PATH));
	        }
	    }

	    /**
	     * プロパティ値を取得する
	     *
	     * @param key キー
	     * @return 値
	     */
	    public static String getProperty(final String key) {
	        return getProperty(key, "");
	    }

	    /**
	     * プロパティ値を取得する
	     * キーが存在しない場合、デフォルト値を返却する
	     *
	     * @param key キー
	     * @param defaultValue デフォルト値
	     * @return キーが存在しない場合、デフォルト値
	     *          存在する場合、値
	     */
	    public static String getProperty(final String key, final String defaultValue) {
	        return properties.getProperty(key, defaultValue);
	    }

	    /**
	     * DB接続する
	     *
	     * @return connection
	     */
	     public static Connection getConnection() {
	         // DB接続
	         Connection connection = null;
	         // DB接続情報を設定する
	         String url = WISS1CommonUtil.getProperty(W01CommonConst.PRO_DB_URL); // 接続パス
	         String id = WISS1CommonUtil.getProperty(W01CommonConst.PRO_DB_USER); // ログインID
	         String pw = WISS1CommonUtil.getProperty(W01CommonConst.PRO_DB_PASS); // ログインパスワード

	         try {
	             return connection = DriverManager.getConnection(url, id, pw);
	         } catch (SQLException e) {
	             // TODO 自動生成された catch ブロック
	             e.printStackTrace();
	         }
	         return connection;

	     }

	      /**
	         * ファイル名の拡張子判断
	         *
	         * @param fileName(ファイル名)
	         * @return 拡張子
	         */
	         public static String judgmentExtension(String fileName) {
	                 // 拡張子判断
                    return fileName.substring(fileName.lastIndexOf(W01CommonConst.CONST_ST_PERIOD));
	         }
}
