package jp.co.wiss1.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
}
