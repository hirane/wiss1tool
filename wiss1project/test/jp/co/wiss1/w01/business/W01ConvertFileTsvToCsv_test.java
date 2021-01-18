package jp.co.wiss1.w01.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class W01ConvertFileTsvToCsv_test {
    //convertFileTsvToCsv

    @Test
    public void 正常系_TSVファイル名を入力したとき_0() {
        //input fileName=TSVファイル名
        String fileName = "division_code_data_20201109175855.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("0", actual);
    }

    //checkFile
    @Test
    public void 異常系_ファイルチェック異常拡張子エラー_1() {

        //input fileName=xlsxファイル名
        String fileName = "division_code_data_20201008174724.xlsx";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    @Test
    public void 異常系_ファイルチェック異常ファイルなしエラー_1() {

        //input fileName=tｓｖファイル名
        String fileName = "aaaaaa.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    @Test
    public void 異常系_ファイルチェック異常ファイル0バイトエラー_1() {

        //input fileName=tｓｖファイル名
        String fileName = "t_employee_datasheader20201118162526.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    @Test
    public void 異常系_TSVファイルを読み込んだとき_1() {
        //input fileName=tｓｖファイル名
        String fileName = "t_employee_datas_data_20201119144237.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    //readFile
    @Test
    public void 正常系_TSVファイルを読み込んだとき_null() {

        //input fileName=tｓｖファイル名、中身がｃｓｖ
        String fileName = "t_employee_datas_data_20201118191647.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    @Test
    public void 正常系_CSVファイル名を入力したとき_contentsListファイルの中身がCSV変換されている() {
        //input fileName=TSVファイル名
        String fileName = "division_code_data_20201126184007.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("0", actual);
    }

    //この処理の前でファイルチェックを行っているため、112行目には入らない
    @Test
    public void 異常系_ファイルが無いとき_1() {

        //input fileName=tｓｖファイル名
        String fileName = "";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    //createCsv
    @Test
    public void 正常系_tsvファイル名を入力したとき_0() {
        //input fileName=TSVファイル名
        String fileName = "division_code_data_20201109175855.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("0", actual);
    }

    //この処理の前でファイルチェックを行っているため、146行目には入らない
    @Test
    public void 異常系_ファイルの中身が異常のとき_1() {
        //input fileName=tｓｖファイル名、中身がｃｓｖ
        String fileName = "t_employee_datas_data_20201118191647.tsv";

        String actual = W01ConvertFileTsvToCsv.convertFileTsvToCsv(fileName);
        assertEquals("1", actual);
    }

    //allFileTsvToCsv
    @Test
    public void 正常系_フォルダ内一括処理でTSVファイルを読み込んだとき_0() {
        List<String> tsvList = new ArrayList<String>(0);
        tsvList.add("C:\\wiss1workspeas\\division_code_data_20201109175855.tsv");
        tsvList.add("C:\\wiss1workspeas\\division_code_data_20201118162243.tsv");
        tsvList.add("C:\\wiss1workspeas\\division_code_data_20201126184007.tsv");

        String actual = W01ConvertFileTsvToCsv.allFileTsvToCsv(tsvList);
        assertEquals("0", actual);
    }

}
