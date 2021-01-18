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
    //実装はうまくいってるが、障害を改善しないと実行結果が返ってこない
    @Test
    public void 正常系_CSVファイル名を入力したとき_0() {

        //input fileName=CSVファイル名
        String fileName = "division_code_data_20201109175855.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("0", actual);
    }

    //checkFile
    @Test
    public void 異常系_ファイルチェック異常拡張子エラー_1() {

        //input fileName=xlsxファイル名
        String fileName = "division_code_data_20201008174724.xlsx";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    @Test
    //絶対にキャッチのほうに入る
    public void 異常系_ファイルチェック異常ファイルなしエラー_1() {

        //input fileName=ｃｓｖファイル名
        String fileName = "t_employee_datas_data_20201109141418..csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    @Test
    public void 異常系_ファイルチェック異常ファイル0バイトエラー_1() {

        //input fileName=ｃｓｖファイル名
        String fileName = "t_employee_datasheader20201118162526.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    //実装はうまくいってるが、障害を改善しないと実行結果が不一致になる
    @Test
    public void 正常系_EXCELファイルに出力したとき_0() {

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

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("0", actual);
    }

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

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("0", actual);
    }

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

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    @Test
    public void 異常系_異常系_CSVファイルの読み込みに失敗したとき_1() {

        //input fileName=xlsxファイル名
        String fileName = "aaa.csv";
        //input interlockingFlg=true
        boolean interlockingFlg = true;

        String actual = W01ConvertFileCsvToTsv.convertFileCsvToTsv(fileName, interlockingFlg);
        assertEquals("1", actual);
    }

    //allFileCsvToTsv
    //実装はうまくいってるが、障害を改善しないと実行結果が返ってこない
    @Test
    public void 正常系_フォルダ内一括処理のとき_0() {

        List<String> csvList = new ArrayList<String>(0);
        csvList.add("C:\\wiss1workspeas\\division_code_data_20201109175855.csv");
        csvList.add("C:\\wiss1workspeas\\division_code_data_20201126184007.csv");
        csvList.add("C:\\wiss1workspeas\\t_employee_datas_data_20201119144237.csv");

        String actual = W01ConvertFileCsvToTsv.allFileCsvToTsv(csvList);
        assertEquals("0", actual);
    }

}
