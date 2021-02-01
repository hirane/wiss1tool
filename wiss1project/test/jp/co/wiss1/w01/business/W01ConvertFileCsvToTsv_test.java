package jp.co.wiss1.w01.business;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mockit.Mock;
import mockit.MockUp;

class W01ConvertFileCsvToTsv_test {

    //convertFileCsvToTsv
    //TODO
    @SuppressWarnings("static-access")
    @Test
    public void 正常系_CSVファイル名を入力したとき_0() {
        //input fileName=CSVファイル名
        String fileName = "division_code_data_20201109175855.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="2"
            public String next() {
                return "2";
            }
        };
        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("0", actual);
    }

    //checkFile
    @SuppressWarnings("static-access")
    @Test
    public void 異常系_ファイルチェック異常拡張子エラー_1() {

        //input fileName=xlsxファイル名
        String fileName = "division_code_data_20201008174724.xlsx";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    //絶対にキャッチのほうに入る
    public void 異常系_ファイルチェック異常ファイルなしエラー_1() {

        //input fileName=ｃｓｖファイル名
        String fileName = "t_employee_datas_data_20201109141418..csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    public void 異常系_ファイルチェック異常ファイル0バイトエラー_1() {

        //input fileName=ｃｓｖファイル名
        String fileName = "t_employee_datasheader20201118162526.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    //TODO
    @SuppressWarnings("static-access")
    @Test
    public void 正常系_EXCELファイルに出力したとき_0() {
        new MockUp<W01ShapeEvidence>() {
            @Mock
            public String evidenceOutput(String createFile) {
            return "0";
            }
       };
        //input fileName=CSVファイル名
        String fileName = "division_code_data_20201109175855.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="1"
            public String next() {
                return "1";
            }
        };

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("0", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    public void 正常系_EXCELファイルに出力しないとき_0() {

        //input fileName=CSVファイル名
        String fileName = "division_code_data_20201109175855.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="2"
            public String next() {
                return "2";
            }
        };

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("0", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    public void 異常系_EXCELファイルに出力しますかで異常値を入力したとき_1() {

        //input fileName=CSVファイル名
        String fileName = "division_code_data_20201118162243.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="9"
            public String next() {
                return "9";
            }
        };

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    @SuppressWarnings("static-access")
    @Test
    public void 異常系_異常系_CSVファイルの読み込みに失敗したとき_1() {

        //input fileName=xlsxファイル名
        String fileName = "aaa.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    //allFileCsvToTsv
    @SuppressWarnings("static-access")
    @Test
    public void 正常系_フォルダ内一括処理のとき_0() {

        List<String> csvList = new ArrayList<String>(0);
        csvList.add("C:\\wiss1workspeas\\division_code_data_20201109175855.csv");
        csvList.add("C:\\wiss1workspeas\\t_employee_datasheader20201118162526.csv");
        csvList.add("C:\\wiss1workspeas\\t_employee_datas_data_20201119144237.csv");

        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.allFileCsvToTsv(csvList);
        assertEquals("0", actual);
    }


    @SuppressWarnings("static-access")
    @Test
    public void 異常系_アクセス権限がないCSVファイル名を入力したとき_0() {

        //input fileName=CSVファイル名
        String fileName = "division_code_data_20210121074809.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = false;
        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }


    @SuppressWarnings("static-access")
    @Test
    public void 異常系_コンマ区切りのCSVファイル名を入力したとき_1() {
        String fileName = "from_csv_to_tsv_2_NG_tsvtype.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = false;
        W01ConvertFileCsvToTsv convertFileCsvToTsv = new W01ConvertFileCsvToTsv();
        String actual = convertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }


}
