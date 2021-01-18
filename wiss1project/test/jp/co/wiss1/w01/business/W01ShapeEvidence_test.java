package jp.co.wiss1.w01.business;

import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mockit.Mock;
import mockit.MockUp;

class W01ShapeEvidence_test {
    /*
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
        new MockUp<W01ShapeEvidence>() {
            @Mock
            public String evidenceOutput(String filePath) {
            return "0";
            }
       };
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

                return "division_code_data_20201008174724.xlsx";

            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    //tsvパターンだと通らない。tsvに対応してない？
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

                return "aaa.csv";

            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    //tsvパターンだと通らない。tsvに対応してない？
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

                return "t_employee_datasheader20201118162526.csv";

            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }



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

                return "division_code_data_20201109175855.csv";

            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }



  //中身tsvファイル
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
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


//TODO
//中身sjis 落とせない
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

                return "division_code_data_20201109175855.csv";

            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }
*/


//allFileSorting
    //fileArrayの中身はパスが入っているためif文に入らない
    @Test
    public void 異常系_1_フォルダ内にファイルが存在しないとき_1() {
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
        assertEquals("1", actual);
    }


/*
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

}
