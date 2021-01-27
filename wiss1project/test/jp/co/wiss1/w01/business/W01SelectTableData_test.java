package jp.co.wiss1.w01.business;
import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import jp.co.wiss1.common.WISS1CommonUtil;
import jp.co.wiss1.w01.common.W01CommonConst;
import mockit.Mock;
import mockit.MockUp;

@TestMethodOrder(OrderAnnotation.class)
class W01SelectTableData_test {

    @Test
    @Order(1)
    void 正常系_社員情報テーブルのデータ取得を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);

        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(2)
    void 正常系_部署コードテーブルのデータ取得を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="2"
                if (count == 1) {
                    count++;
                    return "2";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(3)
    void 正常系_役職コードテーブルのデータ取得を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="3"
                if (count == 1) {
                    count++;
                    return "3";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(4)
    void 異常系_テーブル選択で1から３以外を選択したとき_1() {

        new MockUp<Scanner>() {
            //int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="4"
                return "4";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(5)
    void 異常系_実行操作で1から３以外を選択したとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";

                }
                //2回目入力値="4"
                return "4";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(6)
    void 正常系_TSVファイルを選択したとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        new MockUp<W01ConvertFileCsvToTsv>() {

            @Mock
            public String convertFileCsvToTsv(String fileName, boolean interlockingFlg) {
                return W01CommonConst.SUCCESS;
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(true);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(7)
    void 正常系_EXCELファイルを選択したとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "2";
            }
        };

        new MockUp<W01ShapeEvidence>() {

            @Mock
            public String evidenceOutput(String filePath) {
                return W01CommonConst.SUCCESS;
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(true);
        assertEquals(W01CommonConst.SUCCESS, actual);
    }

    @Test
    @Order(8)
    void 異常系_1または2以外を選択したとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="3"
                return "3";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(true);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(9)
    void 正常系_社員情報テーブルのデータ削除を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="3"
                return "3";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(10)
    void 正常系_部署コードテーブルのデータ削除を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="2"
                if (count == 1) {
                    count++;
                    return "2";
                }
                //2回目入力値="3"
                return "3";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(11)
    void 正常系_役職コードテーブルのデータ削除を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="3"
                if (count == 1) {
                    count++;
                    return "3";
                }
                //2回目入力値="3"
                return "3";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(12)
    void 異常系_社員情報テーブルのテーブルデータが空のとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(13)
    void 異常系_部署コードテーブルのテーブルデータが空のとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "2";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(14)
    void 異常系_役職コードテーブルのテーブルデータが空のとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="3"
                if (count == 1) {
                    count++;
                    return "3";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(15)
    void 正常系_社員情報テーブルのデータ追加を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas.csv"
                return "t_employee_datas.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(16)
    void 正常系_部署コードテーブルのデータ追加を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="2"
                if (count == 1) {
                    count++;
                    return "2";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="division_code.csv"
                return "division_code.csv";
            }
        };
        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(17)
    void 正常系_役職コードテーブルのデータ追加を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="3"
                if (count == 1) {
                    count++;
                    return "3";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="post_code.csv"
                return "post_code.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    @Order(18)
    void 異常系_社員情報テーブルにすでにあるデータを追加するとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas.csv"
                return "t_employee_datas.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(19)
    void 異常系_部署コードテーブルにすでにあるデータを追加するとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="2"
                if (count == 1) {
                    count++;
                    return "2";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="division_code.csv"
                return "division_code.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(20)
    void 異常系_役職コードテーブルにすでにあるデータを追加するとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="3"
                if (count == 1) {
                    count++;
                    return "3";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="post_code.csv"
                return "post_code.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(21)
    void 異常系_社員情報テーブルのデータ追加でファイルの拡張子がCSV以外のとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas.tsv"
                return "t_employee_datas.tsv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(22)
    void 異常系_部署コードテーブルのデータ追加でファイルの拡張子がCSV以外のとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="2"
                if (count == 1) {
                    count++;
                    return "2";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas.tsv"
                return "t_employee_datas.tsv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(23)
    void 異常系_役職コードテーブルのデータ追加でファイルの拡張子がCSV以外のとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "3";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas.tsv"
                return "t_employee_datas.tsv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(24)
    void 異常系_社員情報テーブルのデータ追加でファイルが存在しないとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas_null.csv"
                return "t_employee_datas_null.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(25)
    void 異常系_部署コードテーブルのデータ追加でファイルが存在しないとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="2"
                if (count == 1) {
                    count++;
                    return "2";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas_null.csv"
                return "t_employee_datas_null.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(26)
    void 異常系_役職コードテーブルのデータ追加でファイルが存在しないとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="3"
                if (count == 1) {
                    count++;
                    return "3";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="t_employee_datas_null.csv"
                return "t_employee_datas_null.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(27)
    void 異常系_社員情報テーブルのデータ追加で空ファイルのとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="nodata.csv"
                return "nodata.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(28)
    void 異常系_部署コードテーブルのデータ追加で空ファイルのとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="2"
                if (count == 1) {
                    count++;
                    return "2";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="nodata.csv"
                return "nodata.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(29)
    void 異常系_役職コードテーブルのデータ追加で空ファイルのとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="3"
                if (count == 1) {
                    count++;
                    return "3";
                    //2回目入力値="2"
                } else if (count == 2) {
                    count++;
                    return "2";
                }
                //3回目入力値="nodata.csv"
                return "nodata.csv";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(30)
    void 異常系_社員情報テーブルのデータ取得を選択してDBの接続に失敗した時_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        new MockUp<WISS1CommonUtil>() {
            int count = 1;

            @Mock
            public String getProperty(String key) {
                if (count == 1) {
                    count++;
                    return "jdbc:postgresql://localhost:5432/postgres";
                } else if (count == 2) {
                    count++;
                    return "postgres";
                }
                return "root";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(31)
    void 異常系_部署コードテーブルのデータ取得を選択してDBの接続に失敗した時_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "2";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        new MockUp<WISS1CommonUtil>() {
            int count = 1;

            @Mock
            public String getProperty(String key) {
                if (count == 1) {
                    count++;
                    return "jdbc:postgresql://localhost:5432/postgres";
                } else if (count == 2) {
                    count++;
                    return "postgres";
                }
                return "root";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(32)
    void 異常系_役職コードテーブルのデータ取得を選択してDBの接続に失敗した時_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "3";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        new MockUp<WISS1CommonUtil>() {
            int count = 1;

            @Mock
            public String getProperty(String key) {
                if (count == 1) {
                    count++;
                    return "jdbc:postgresql://localhost:5432/postgres";
                } else if (count == 2) {
                    count++;
                    return "postgres";
                }
                return "root";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(33)
    void 異常系_社員情報テーブルのデータ取得で出力ファイルパスが誤っているとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        new MockUp<WISS1CommonUtil>() {
            int count = 1;

            @Mock
            public String getProperty(String key) {
                if (count == 1) {
                    count++;
                    return "jdbc:postgresql://localhost:5433/postgres";
                } else if (count == 2) {
                    count++;
                    return "postgres";
                } else if (count == 3) {
                    count++;
                    return "root";
                }
                return "C:\\<";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(34)
    void 異常系_部署コードテーブルのデータ取得で出力ファイルパスが誤っているとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "2";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        new MockUp<WISS1CommonUtil>() {
            int count = 1;

            @Mock
            public String getProperty(String key) {
                if (count == 1) {
                    count++;
                    return "jdbc:postgresql://localhost:5433/postgres";
                } else if (count == 2) {
                    count++;
                    return "postgres";
                } else if (count == 3) {
                    count++;
                    return "root";
                }
                return "C:\\<";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

    @Test
    @Order(35)
    void 異常系_役職コードテーブルのデータ取得で出力ファイルパスが誤っているとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String nextLine() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "3";
                }
                //2回目入力値="1"
                return "1";
            }
        };

        new MockUp<WISS1CommonUtil>() {
            int count = 1;

            @Mock
            public String getProperty(String key) {
                if (count == 1) {
                    count++;
                    return "jdbc:postgresql://localhost:5433/postgres";
                } else if (count == 2) {
                    count++;
                    return "postgres";
                } else if (count == 3) {
                    count++;
                    return "root";
                }
                return "C:\\<";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }

}