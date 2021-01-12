import static org.junit.Assert.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

import jp.co.wiss1.w01.business.W01SelectTableData;
import jp.co.wiss1.w01.common.W01CommonConst;
import mockit.Mock;
import mockit.MockUp;

class W01SelectTableData_test {

    //個別に実施しないと結果が不一致になる
    @Test
    void 正常系_社員情報テーブルのデータ取得を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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

    //個別に実施しないと結果が不一致になる
    @Test
    void 正常系_部署コードテーブルのデータ取得を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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

    //個別に実施しないと結果が不一致になる
    @Test
    void 正常系_役職コードテーブルのデータ取得を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
/*
    //個別に実施しないと結果が不一致になる
    @Test
    void 正常系_テーブル選択で1から３以外を選択したときループ_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="4"
                if (count == 1) {
                    count++;
                    return "4";
                    //2回目入力値="1"
                } else if (count == 2) {
                    count++;
                    return "1";
                }
                //3回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    //個別に実施しないと結果が不一致になる
    @Test
    void 正常系_実行操作で1から３以外を選択したときループ_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                    //2回目入力値="4"
                } else if (count == 2) {
                    count++;
                    return "4";
                }
                //3回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    //selectFileData（個別で実施）
    @Test
    void 正常系_TSVファイルを選択したとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
        String actual = select.selectTableData(true);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

    @Test
    void 正常系_EXCELファイルを選択したとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "2";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(true);
        assertEquals(W01CommonConst.SUCCESS, actual);
    }

    @Test
    void 正常系_1または2以外を選択したときループ_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                    //2回目入力値="3"
                } else if (count == 2) {
                    count++;
                    return "3";
                }
                //3回目入力値="1"
                return "1";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(true);
        assertEquals(W01CommonConst.SUCCESS, actual);

    }

*/

    //deleteData
    @Test
    void 正常系_社員情報テーブルのデータ削除を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 正常系_部署コードテーブルのデータ削除を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 正常系_役職コードテーブルのデータ削除を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
/*
    //resultSetData
    @Test
    void 異常系_テーブルデータが空のとき_1() throws ClassNotFoundException, SQLException {
        Class.forName(W01CommonConst.PRO_DB_DRIVER);

        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        Statement statement = connection.createStatement();

        String deleteSql = W01CommonConst.TBL_DELETE_ALL;

        // SQL文を定義する
        String employeeSql = deleteSql + W01CommonConst.TBL_NM_EMPLOYEE;
        statement.executeUpdate(employeeSql);

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
*/
    /*
    @Test
    void 異常系_resultsetDataで3を受け取ったとき_null() throws NoSuchMethodException, SecurityException,
            IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        W01SelectTableData select = new W01SelectTableData();
        Method method = W01SelectTableData.class.getDeclaredMethod("resultSetData", String.class);
        method.setAccessible(true);
        String actual = (String) method.invoke(select, "3");

        assertEquals(null, actual);
    }
*/
    //addData
    @Test
    void 正常系_社員情報テーブルのデータ追加を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 正常系_部署コードテーブルのデータ追加を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 正常系_役職コードテーブルのデータ追加を選択するとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 異常系_ファイルの拡張子がCSV以外のとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 異常系_ファイルが存在しないとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 異常系_空ファイルのとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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


    /*
    //個別で実施
    //W01CommonConst.javaの187行目をコメントアウトし、188行目を使用
    @Test
    void 正常系_社員情報テーブルのデータ取得を選択してDB接続できなかったとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 正常系_部署コードテーブルのデータ取得を選択してDB接続できなかったとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 正常系_役職コードテーブルのデータ取得を選択してDB接続できなかったとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="1"
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
    void 正常系_社員情報テーブルのデータ追加を選択してDB接続できなかったとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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
    void 正常系_社員情報テーブルのデータ削除を選択してDB接続できなかったとき_0() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "3";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }
    */

    /*
    //個別で実施
    //wiss1Common.propertiesの８行目をコメントアウトし、14行目を使用
    @Test
    void 正常系_社員情報テーブルのデータ取得で出力ファイルパスが誤っているとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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

    //wiss1Common.propertiesの８行目をコメントアウトし、14行目を使用
    @Test
    void 正常系_部署コードテーブルのデータ取得で出力ファイルパスが誤っているとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
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

    //wiss1Common.propertiesの８行目をコメントアウトし、14行目を使用
    @Test
    void 正常系_役職コードテーブルのデータ取得で出力ファイルパスが誤っているとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="1"
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

    //W01CommonConst.javaの行目をコメントアウトし、86行目を使用
    @Test
    void 正常系_データ削除のSQL文が誤っているとき_1() {

        new MockUp<Scanner>() {
            int count = 1;

            @Mock
            public String next() {
                //1回目入力値="1"
                if (count == 1) {
                    count++;
                    return "1";
                }
                //2回目入力値="1"
                return "3";
            }
        };

        W01SelectTableData select = new W01SelectTableData();
        String actual = select.selectTableData(false);
        assertEquals(W01CommonConst.ERROR, actual);

    }
*/
}