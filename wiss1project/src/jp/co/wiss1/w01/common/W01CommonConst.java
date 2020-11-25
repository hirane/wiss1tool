package jp.co.wiss1.w01.common;

/**
 * WISS1ツール定数クラス
 *
 * @since 2020.10.18
 * @author wiss1
 * @version 1.0.0
 */
public class W01CommonConst {

    /**
     * 戻り値（処理が正常完了した場合）
     */
    public static final String SUCCESS = "0";

    /**
     * 戻り値（処理が異常終了した場合）
     */
    public static final String ERROR = "1";

    /**
     * CSVファイル拡張子
     */
    public static final String CONST_EXTENSION_CSV = ".csv";

    /**
     * TSVファイル拡張子
     */
    public static final String CONST_EXTENSION_TSV = ".tsv";

    /**
     * UTF-8
     */
    public static final String CONST_CHAR_CODE_UTF8 = "UTF-8";

    /**
     * カンマ
     */
    public static final String CONST_ST_COMMA = ",";

    /**
     * ピリオド
     */
    public static final String CONST_ST_PERIOD = ".";

    /**
     * タブ
     */
    public static final String CONST_ST_TAB = "\t";

    /**
     * テーブル選択肢_1
     */
    public static final String TBL_NM_ONE = "1：社員情報";

    /**
     * テーブル選択肢_2
     */
    public static final String TBL_NM_TWO = "2：部署コード";

    /**
     * テーブル選択肢_3
     */
    public static final String TBL_NM_THREE = "3：役職コード";

    /**
     * テーブル選択肢_1（ユーザが指定した選択肢）
     */
    public static final String TBL_CH_ONE = "1";

    /**
     * テーブル選択肢_2（ユーザが指定した選択肢）
     */
    public static final String TBL_CH_TWO = "2";

    /**
     * テーブル選択肢_3（ユーザが指定した選択肢）
     */
    public static final String TBL_CH_THREE = "3";

    /**
     * テーブル名_社員情報
     */
    public static final String TBL_NM_EMPLOYEE = "t_employee_datas";

    /**
     * テーブル名_部署コード
     */
    public static final String TBL_NM_DIVISION = "division_code";

    /**
     * テーブル名_役職コード
     */
    public static final String TBL_NM_POST = "post_code";

    /**
     * SELECT文
     */
    public static final String TBL_SELECT_ALL = "SELECT * FROM ";

    /**
     * INSERT文
     */
    public static final String TBL_INSERT_ONE = "INSERT INTO ";

    /**
     * INSERT文
     */
    public static final String TBL_INSERT_TWO = " VALUES (";

    /**
     * INSERT文
     */
    public static final String TBL_INSERT_THREE = ");";

    /**
     * DELETE文
     */
    public static final String TBL_DELETE_ALL = "DELETE FROM ";

    /**
     * コマンド
     */
    public static final String COMMAND = "cmd /c cscript vbs\\W01ShapeEvidenceMacro.vbs";

    /**
     * 日付
     */
    public static final String DATE = "yyyyMMddHHmmss";

    /**
     * テーブル名
     */
    public static final String TABLE_NAME = "TABLE_NAME";

    /**
     * パス
     */
    public static final String PATH = "PATH";

    /**
     * ヘッダー
     */
    public static final String FILE_NM_HEADER = "header";

    /**
     * データ
     */
    public static final String FILE_NM_DATA = "_data_";

    /**
     * パーセント
     */
    public static final String CONST_ST_ = "%";

    /**
     * カラム名
     */
    public static final String COLUMN_NAME = "COLUMN_NAME";

    /**
     * プロパティファイルのDBのURL
     */
    public static final String PRO_DB_URL = "DBURL";

    /**
     * プロパティファイルのDBのユーザ名
     */
    public static final String PRO_DB_USER = "DBUSER";

    /**
     * プロパティファイルのDBパス
     */
    public static final String PRO_DB_PASS = "DBPASS";

    /**
     * プロパティファイルのDBテーブル名
     */
    public static final String PRO_DB_TABLE = "TABLE";

    /**
     * プロパティファイルのDBドライバー
     */
    public static final String PRO_DB_DRIVER = "org.postgresql.Driver";

    /**
     * プロパティファイルのアウトプットパス
     */
    public static final String PRO_OUT_PATH = "OUTPUTPATH";

    /**
     * 数値0
     */
    public static final int NUM_ZERO = 0;

    /**
     * 数値1
     */
    public static final int NUM_ONE = 1;

    /**
     * 数値2
     */
    public static final int NUM_TWO = 2;

    /**
     * 数値3
     */
    public static final int NUM_THREE = 3;

    /**
     * 戻り値（ファイルチェック正常）
     */
    public static final int FCHECK_SUCCESS = 0;

    /**
     * 戻り値（ファイルチェック異常（拡張子エラー））
     */
    public static final int FCHECK_ERROR_EXT = 1;

    /**
     * 戻り値（ファイルチェック異常（ファイルなしエラー））
     */
    public static final int FCHECK_ERROR_EXS = 2;

    /**
     * 戻り値（ファイルチェック異常（ファイル0バイトエラー））
     */
    public static final int FCHECK_ERROR_EMP = 3;

    /**
     * 選択肢_はい
     */
    public static final String ONE_YES = "1：はい";

    /**
     * 選択肢_いいえ
     */
    public static final String TWO_NO = "2：いいえ";

    /**
     * 選択肢_tsv
     */
    public static final String ONE_TSV = "1：tsv";

    /**
     * 選択肢_csv
     */
    public static final String TWO_CSV = "2：csv";

    /**
     * 操作内容選択肢_1
     */
    public static final String OPE_CON_ONE = "1：データ取得";

    /**
     * 操作内容選択肢_2
     */
    public static final String OPE_CON_TWO = "2：データ追加";

    /**
     * 操作内容選択肢_3
     */
    public static final String OPE_CON_THREE = "3：データ削除";

    /**
     * 操作内容選択肢_1（ユーザが指定した選択肢）
     */
    public static final String OPE_CH_ONE = "1";

    /**
     * 操作内容選択肢_2（ユーザが指定した選択肢）
     */
    public static final String OPE_CH_TWO = "2";

    /**
     * 操作内容選択肢_3（ユーザが指定した選択肢）
     */
    public static final String OPE_CH_THREE = "3";

}
