import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import jp.co.wiss1.w01.business.W01SelectTableHeader;
import jp.co.wiss1.w01.common.W01CommonConst;
import mockit.Mock;
import mockit.MockUp;

class W01SelectTableHeader_test {

    @Test
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

    //getTableData
    //個別で実施
    //W01CommonConst.javaの135行目をコメントアウトし、136行目を使用
    @Test
    void 異常系_テーブル名が正しくないとき_1() {
        new MockUp<Connection>() {
            @Mock
            DatabaseMetaData getMetaData() throws SQLException {
                //1回目入力値="1"
                return null;
            }
        };
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
    //exportCsv
    //個別で実施
    //wiss1Common.propertiesの８行目をコメントアウトし、14行目を使用
    @Test
    void 異常系_出力ファイルパスが正しくないとき_1() {
        new MockUp<Scanner>() {
            @Mock
            public String nextLine() {
                //1回目入力値="1"
                return "1";
            }
        };
        new MockUp<Properties>() {
            @Mock
            public String getProperty() {
                //1回目入力値="1"
                return "E:\\";
            }
        };

        W01SelectTableHeader select = new W01SelectTableHeader();
        String actual = select.selectTableHeader();
        assertEquals(W01CommonConst.ERROR, actual);
    }
}
