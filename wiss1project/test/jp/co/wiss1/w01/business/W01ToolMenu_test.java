package jp.co.wiss1.w01.business;

import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import mockit.Mock;
import mockit.MockUp;

class W01ToolMenu_test {

    @Test
    public void 正常系_1_ファイル変換が選択されたとき_0() throws IOException {
         new MockUp<W01ConvertFileSelect>() {
             @Mock
             public String convertFileSelect() {
             return "0";
             }
        };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="1"
            public String next() {
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";
            }
        };
        W01ToolMenu.main(null);
    }


    @Test
    public void 異常系_1_ファイル変換が選択されたとき_1() throws IOException {
        new MockUp<W01ConvertFileSelect>() {
            @Mock
            public String convertFileSelect() {
            return "1";
            }
       };
       new MockUp<Scanner>() {
           int count = 1;
           @Mock
           //１回目入力値="1"
           public String next() {
               if (count == 1) {
                   count++;
                   return "1";
               }
               //2回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
               return "99";
           }
       };
       W01ToolMenu.main(null);
       }


    @Test
    public void 正常系_2_DB関連で1_テーブルヘッダー部取得が選択されたとき_0() throws IOException {
        new MockUp<W01SelectTableHeader>() {
            @Mock
            public String selectTableHeader() {
            return "0";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                } else if(count == 2) {
                    count++;
                    //2回目入力値＝”１”
                return "1";
                }
                //3回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";

            }
        };
        W01ToolMenu.main(null);
    }




    @Test
    public void 異常系_2_DB関連で1_テーブルヘッダー部取得が選択されたとき_1() throws IOException {
        new MockUp<W01SelectTableHeader>() {
            @Mock
            public String selectTableHeader() {
            return "1";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                } else if(count == 2) {
                    count++;
                    //2回目入力値＝”１”
                return "1";
                }
                //3回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";

            }
        };
        W01ToolMenu.main(null);
    }



    @Test
    public void 正常系_2_DB関連で2_データ取得が選択されたとき_0() throws IOException {
        new MockUp<W01SelectTableData>() {
            @Mock
            public String selectTableData(boolean flg) {
            return "0";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                } else if(count == 2) {
                    count++;
                    //2回目入力値＝”2”
                return "2";
                }
                //3回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";

            }
        };
        W01ToolMenu.main(null);
    }




    //未実装
    @Test
    public void 異常系_2_DB関連で2_データ取得が選択されたとき_1() throws IOException {
        new MockUp<W01SelectTableData>() {
            @Mock
            public String selectTableData(boolean flg) {
            return "1";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                } else if(count == 2) {
                    count++;
                    //2回目入力値＝”2”
                return "2";
                }
                //3回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";

            }
        };
        W01ToolMenu.main(null);
    }



    @Test
    public void 正常系_2_DB関連で3_メニュー画面に戻るが選択されたとき_0() throws IOException {
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                } else if(count == 2) {
                    count++;
                    //2回目入力値＝”3”
                return "3";
                }
                //3回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";

            }
        };
        W01ToolMenu.main(null);
    }



    @Test
    public void 異常系_2_DB関連で異常値が選択されたとき_1() throws IOException {
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="2"
            public String next() {
                if (count == 1) {
                    count++;
                    return "2";
                } else if(count == 2) {
                    count++;
                    //2回目入力値＝”9”
                return "9";
                }else if(count == 3) {
                    count++;
                //2回目入力値＝”3”
                return "3";
                }
                //4回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";
            }
        };
        W01ToolMenu.main(null);
    }



    @Test
    public void 正常系_3_エビデンス成型が選択されたとき_0() throws IOException {
        new MockUp<W01ShapeEvidence>() {
            @Mock
            public String shapeEvidence() {
            return "0";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="3"
            public String next() {
                if (count == 1) {
                    count++;
                    return "3";
                }
                //2回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";
            }
        };
        W01ToolMenu.main(null);
    }




    @Test
    public void 異常系_3_エビデンス成型が選択されたとき_1() throws IOException {
        new MockUp<W01ShapeEvidence>() {
            @Mock
            public String shapeEvidence() {
            return "1";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="3"
            public String next() {
                if (count == 1) {
                    count++;
                    return "3";
                }
                //2回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";
            }
        };
        W01ToolMenu.main(null);
    }




    @Test
    public void 正常系_9_連動機能が選択されたとき_0() throws IOException {
        new MockUp<W01SelectTableData>() {
            @Mock
            public String  selectTableData(boolean interLockingFlg) {
            return "0";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="9"
            public String next() {
                if (count == 1) {
                    count++;
                    return "9";
                }
                //2回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";
            }
        };
        W01ToolMenu.main(null);
    }



    @Test
    public void 異常系_9_連動機能が選択されたとき_1() throws IOException {
        new MockUp<W01SelectTableData>() {
            @Mock
            public String  selectTableData(boolean interLockingFlg) {
            return "1";
            }
       };
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            //１回目入力値="9"
            public String next() {
                if (count == 1) {
                    count++;
                    return "9";
                }
                //2回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";
            }
        };
        W01ToolMenu.main(null);
    }



    @Test
    public void 正常系_99_バッチを終了するが選択されたとき_期待値なし() throws IOException {
        new MockUp<Scanner>() {
            @Mock
            //１回目入力値="99"
            public String next() {
                    return "99";
            }
            };
        W01ToolMenu.main(null);
    }


    @Test
    public void 異常系_最初に異常値が入力されたとき_1() throws IOException {
        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            //１回目入力値="aa"
            public String next() {
                if (count == 1) {
                    count++;
                    return "aa";
                }
                //2回目にScannerが呼ばれた際に”９９”を渡して処理を終わらす
                return "99";
            }
        };
        W01ToolMenu.main(null);
    }

}
