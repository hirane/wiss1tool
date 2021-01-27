package jp.co.wiss1.w01.business;
import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
class W01SelectTableHeader_test {

    @Test
    @Order(1)
    void 正常系_社員情報を選択したとき_0() {

        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="1"
                return "1";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.SUCCESS, actual);
    }

    @Test
    @Order(2)
    void 正常系_部署コードを選択したとき_0() {

        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="2"
                return "2";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.SUCCESS, actual);
    }

    @Test
    @Order(3)
    void 正常系_役職コードを選択したとき_0() {

        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="3"
                return "3";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.SUCCESS, actual);
    }

    @Test
    @Order(4)
    void 異常系_1から3以外を選択したとき_1() {

        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="4"
                return "A";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }

    @Test
    @Order(5)
    void 異常系_DBに接続できないとき_1() {

        new MockUp<WISS1CommonUtil>() {
            int count = 1;

            @Mock
            public String getProperty(String key) {
                if (count == 1) {
                    count++;
                    return "jdbc:postgresql://localhost:5430/postgres";
                } else if (count == 2) {
                    count++;
                    return "postgres";
                }
                return "root";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }

    @Test
    @Order(6)
    void 異常系_社員情報テーブルを選択して出力ファイルパスが正しくないとき_1() {
        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="1"
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

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }

    @Test
    @Order(7)
    void 異常系_部署コードテーブルを選択して出力ファイルパスが正しくないとき_1() {
        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="2"
                return "2";
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

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }

    @Test
    @Order(8)
    void 異常系_役職コードテーブルを選択して出力ファイルパスが正しくないとき_1() {
        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="3"
                return "3";
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

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }


    @Test
    @Order(9)
    void 異常系_社員情報テーブルが存在しないとき_1() throws SQLException {

        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        Statement statement = connection.createStatement();

        String deleteSql = "DROP TABLE ";

        // SQL文を定義する
        String employeeSql = deleteSql + W01CommonConst.TBL_NM_EMPLOYEE;
        statement.executeUpdate(employeeSql);

        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="1"
                return "1";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }

    @Test
    @Order(10)
    void 異常系_部署コードテーブルが存在しないとき_1() throws SQLException {

        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        Statement statement = connection.createStatement();

        String deleteSql = "DROP TABLE ";

        // SQL文を定義する
        String employeeSql = deleteSql + W01CommonConst.TBL_NM_DIVISION;
        statement.executeUpdate(employeeSql);

        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="1"
                return "1";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }

    @Test
    @Order(11)
    void 異常系_役職コードテーブルが存在しないとき_1() throws SQLException {

        // DB接続する
        Connection connection = WISS1CommonUtil.getConnection();
        Statement statement = connection.createStatement();

        String deleteSql = "DROP TABLE ";

        // SQL文を定義する
        String employeeSql = deleteSql + W01CommonConst.TBL_NM_POST;
        statement.executeUpdate(employeeSql);

        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="1"
                return "1";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }

}
