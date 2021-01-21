package jp.co.wiss1.w01.business;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mockit.Mock;
import mockit.MockUp;

class W01ShapeEvidence_test {

    //shapeEvidence
    @Test
    public void 正常系_フォルダ内のtsvファイル全てを選択したとき_0() {
        new MockUp<W01ShapeEvidence>() {
            @Mock
            public String allFileSorting(String tsvOrCsv) {
                return "0";
            }
        };
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="1"
            //2回目="1"
            public String next() {
                return "1";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        //return Moc="0"allFileSorting
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }

    @Test
    public void 正常系_フォルダ内のcsvファイル全てを選択したとき_0() {
        new MockUp<W01ShapeEvidence>() {
            @Mock
            public String allFileSorting(String tsvOrCsv) {
                return "0";
            }
        };

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
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        //return Moc="0"allFileSorting
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }

    @Test
    public void 異常系_1_フォルダ内ファイル全選択後に異常値が入力されたとき_1() {
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
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        //return Moc="0"allFileSorting
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }

    @Test
    public void 正常系_フォルダ内のtsvファイルを１つ選択したとき_0() {

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
                return null;
            }
        };
        new MockUp<Scanner>() {
            int count = 2;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String nextLine() {
                if (count == 2) {
                    count++;
                    return "division_code_data_20201109175855.tsv";
                }
                return null;
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
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
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }

    //evidenceOutputメソッド
    @Test
    public void 異常系_1_フォルダ内のファイル1つを選択したがtsvでもcsvでないとき_1() {
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
                return null;
            }
        };
        new MockUp<Scanner>() {
            int count = 2;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String nextLine() {
                if (count == 2) {
                    count++;
                    return "division_code_data_20201008174724.xlsx";
                }
                return null;
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }

    @Test
    public void 異常系_1_フォルダ内のファイル１つを選択したがファイルが存在しないとき_1() {
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
                return null;
            }
        };
        new MockUp<Scanner>() {
            int count = 2;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String nextLine() {
                if (count == 2) {
                    count++;
                    return "aaa.csv";
                }
                return null;
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }

    @Test
    public void 異常系_1_フォルダ内のファイル１つを選択したがファイルの中身が空のとき_1() {
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
                return null;
            }
        };
        new MockUp<Scanner>() {
            int count = 2;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String nextLine() {
                if (count == 2) {
                    count++;
                    return "t_employee_datasheader20201118162526.csv";
                }
                return null;
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }

//正常パターンは作成ファイルが被ってしまうため同時に実行できない
/*
    @Test
    public void 正常系_ファイルのエビデンス成型に成功したとき_0() {
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
                return null;
            }
        };
        new MockUp<Scanner>() {
            int count = 2;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String nextLine() {
                if (count == 2) {
                    count++;
                    return "division_code_data_20201109175855.csv";
                }
                return null;
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }
*/


    //中身tsvファイル
    //137行
    @Test
    public void 異常系_ファイルのエビデンス成型に失敗したとき_1() {
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
                return "division_code_data_20201126184007.csv";
            }
        };
        new MockUp<Scanner>() {
            int count = 2;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String nextLine() {
                if (count == 2) {
                    count++;
                    return "division_code_data_20201126184007.csv";
                }
                return null;
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }

    /*
    //TODO
    //絶対落とせない
    @Test
    public void 異常系_マクロの呼び出しに失敗したとき_1() {
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

                return null;

            }
        };
                new MockUp<Scanner>() {
            int count = 2;

            @Mock
            //１回目入力値="2"
            //2回目="TSVファイル名"
            public String nextLine() {
                if (count == 2) {
                    count++;
                    return "division_code_data_20201109175855.csv";
                }
                return null;
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }
    */

    @Test
    public void 正常系_tsvファイルのエビデンス成型に成功したとき_0() {
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
                return "1";
            }

        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        //return Moc="0"allFileSorting
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


  //正常パターンは作成ファイルが被ってしまうため同時に実行できない
    /*
    @Test
    public void 正常系_csvファイルのエビデンス成型に成功したとき_0() {
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
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        //return Moc="0"allFileSorting
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }
*/

// フォルダ内にcsvtsvファイルが存在しない要にする
    @Test
    public void 異常系_フォルダ内にcsvtsvファイルが存在しないとき_0() {
        new MockUp<Scanner>() {

            @Mock
            //１回目入力値="1"
            //2回目="1"
            public String next() {
                return "1";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        //return Moc="0"allFileSorting
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


}
