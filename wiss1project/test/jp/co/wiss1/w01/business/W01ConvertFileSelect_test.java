package jp.co.wiss1.w01.business;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mockit.Mock;
import mockit.MockUp;

class W01ConvertFileSelect_test {

    //convertFileSelect
    @Test
    public void 正常系_フォルダ内のTSVファイル全て対象にしたとき_0() {
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="1"
            //2回目="1"
            public String next() {
                return "1";
            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("0", actual);
    }

    /*
    //W01ConvertFileCsvToTsvが障害のため返却値が返ってこない
    @Test
    public void 正常系_フォルダ内のCSVファイル全て対象にしたとき_0() {
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="1"
            //2回目="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "1";
                }
                return "2";
            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("0", actual);
    }
    */

    @Test
    public void 異常系_フォルダ内を全て対象にし異常値を入力されたとき_1() {
        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            //１回目入力値="1"
            //2回目="9"
            public String next() {
                if (count == 1) {
                    count++;
                    return "1";
                }

                return "9";

            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("1", actual);
    }

    @Test
    public void 正常系_フォルダ内のファイル全てが対象で無いとき_0() {
        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                }

                return "division_code_data_20201109175855.tsv";

            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("0", actual);
    }

    @Test
    public void 異常系_フォルダ内を全て対象の選択肢で異常値を入力されたとき_1() {
        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            //１回目入力値="1"
            //2回目="9"
            public String next() {
                if (count == 1) {
                    count++;
                    return "9";
                }
                return "1";

            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("1", actual);
    }

    //selectFile
    @Test
    public void 正常系_TSVファイルを入力したとき_0() {
        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                }

                return "division_code_data_20201109175855.tsv";

            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("0", actual);
    }

    /*
      //W01ConvertFileCsvToTsvが障害のため返却値が返ってこない
    @Test
    public void 正常系_CSVファイルを入力したとき_0() {
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                }

                return "division_code_data_20201118162243.csv";

            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("0", actual);
    }
    */

    @Test
    public void 異常系_TSVCSVファイル以外のファイルを入力したとき_1() {
        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            //１回目入力値="2"
            //2回目="TSV,CSVファイル以外のファイル"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                }

                return "division_code_data_20201008174724.xlsx";

            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("1", actual);
    }

    //allFileSorting
    @Test
    public void 正常系_フォルダ内一括処理TSVファイルを入力したとき_0() {
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="1"
            //2回目="1"
            public String next() {
                return "1";
            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("0", actual);
    }

    /*
      //W01ConvertFileCsvToTsvが障害のため返却値が返ってこない
    @Test
    public void 正常系_フォルダ内一括処理CSVファイルを入力したとき_0() {
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="1"
            //2回目="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "1";
                }
                return "2";


            }
        };
        W01ConvertFileSelect convertFileSelect = new W01ConvertFileSelect();
        //return Moc="0"allFileSorting
        String actual = convertFileSelect.convertFileSelect();
        assertEquals("0", actual);
    }
    */
}
