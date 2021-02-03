package jp.co.wiss1.w01.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class W01ConvertFileTsvToCsv_test {
    //convertFileTsvToCsv

    @SuppressWarnings("static-access")
    @Test
    public void 正常系_TSVファイル名を入力したとき_0() {
        //input fileName=TSVファイル名
        String fileName = "division_code_data_20201109175855.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("0", actual);
    }

    //checkFile
    @SuppressWarnings("static-access")
    @Test
    public void 異常系_ファイルチェック異常拡張子エラー_1() {

        //input fileName=xlsxファイル名
        String fileName = "division_code_data_20201008174724.xlsx";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    public void 異常系_ファイルチェック異常ファイルなしエラー_1() {

        //input fileName=tｓｖファイル名
        String fileName = "aaaaaa.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    public void 異常系_ファイルチェック異常ファイル0バイトエラー_1() {

        //input fileName=tｓｖファイル名
        String fileName = "t_employee_datasheader20201118162526.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    //TODO
    @SuppressWarnings("static-access")
    @Test
    public void 異常系_中身がCSVのTSVファイルを読み込んだとき_1() {
        //input fileName=tｓｖファイル名
        String fileName = "t_employee_datas_data_20201118191647.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    //readFile
    @SuppressWarnings("static-access")
    @Test
    public void 正常系_TSVファイルを読み込んだとき_null() {

        //input fileName=tｓｖファイル名、中身がｃｓｖ
        String fileName = "t_employee_datas_data_20201118191647.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    public void 正常系_CSVファイル名を入力したとき_contentsListファイルの中身がCSV変換されている() {
        //input fileName=TSVファイル名
        String fileName = "division_code_data_20201126184007.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("0", actual);
    }

    //この処理の前でファイルチェックを行っているため、112行目には入らない
    @SuppressWarnings("static-access")
    @Test
    public void 異常系_ファイルが無いとき_1() {

        //input fileName=tｓｖファイル名
        String fileName = "";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    //createCsv
    @SuppressWarnings("static-access")
    @Test
    public void 正常系_tsvファイル名を入力したとき_0() {
        //input fileName=TSVファイル名
        String fileName = "division_code_data_20201109175855.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("0", actual);
    }

    //この処理の前でファイルチェックを行っているため、146行目には入らない
    @SuppressWarnings("static-access")
    @Test
    public void 異常系_ファイルの中身が異常のとき_1() {
        //input fileName=tｓｖファイル名、中身がｃｓｖ
        String fileName = "t_employee_datas_data_20201118191647.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    //allFileTsvToCsv
    @SuppressWarnings("static-access")
    @Test
    public void 正常系_フォルダ内一括処理でTSVファイルを読み込んだとき_0() {
        List<String> tsvList = new ArrayList<String>(0);
        tsvList.add("C:\\wiss1workspeas\\division_code_data_20201109175855.tsv");
        tsvList.add("C:\\wiss1workspeas\\t_employee_datas_data_20201118191647.tsv");
        tsvList.add("C:\\wiss1workspeas\\t_employee_datasheader20201118162526.tsv");

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.allFileTsvToCsv(tsvList);
        assertEquals("0", actual);
    }

    //ファイルの権限をいじる
    @SuppressWarnings("static-access")
    @Test
    public void 異常系_readFileメソッドでcatchに入る処理_1() {
        //アクセス権のないファイルを指定した場合
        String fileName = "yomitoriNG.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }


    //ファイルの権限をいじる
    @SuppressWarnings("static-access")
    @Test
    public void 異常系_createCsvメソッドでcatchに入る処理_1() {
        //書き込み先のcsvファイルが既に存在し、かつ変更が許可されていないファイルである場合
        String fileName = "writeNG.tsv";

        W01ConvertFileTsvToCsv convertFileTsvToCsv = new W01ConvertFileTsvToCsv();
        String actual = convertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

}
