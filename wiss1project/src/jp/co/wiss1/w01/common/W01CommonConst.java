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
    public static final String CONST_EXTENSION_CSV = "csv";

    /**
     * TSVファイル拡張子
     */
    public static final String CONST_EXTENSION_TSV = "tsv";

    /**
     * UTF-8
     */
    public static final String CONST_CHAR_CODE_UTF8 = "UTF-8";

    /**
     * カンマ
     */
    public static final String CONST_ST_COMMA = ",";

    /**
     * タブ
     */
    public static final String CONST_ST_TAB = "\t";

    /**
     * テーブル選択肢_01
     */
    public static final String TBL_NM_ONE = "01：社員情報";

    /**
     * テーブル選択肢_02
     */
    public static final String TBL_NM_TWO = "02：部署コード";

    /**
     * テーブル選択肢_03
     */
    public static final String TBL_NM_THREE = "03：役職コード";

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
     * コマンド
     */
    public static final String COMMAND = "cmd /c cscript vbs\\W01ShapeEvidenceMacro.vbs";
}
