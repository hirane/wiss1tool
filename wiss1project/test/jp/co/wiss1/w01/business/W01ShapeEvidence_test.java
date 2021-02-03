package jp.co.wiss1.w01.business;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jp.co.wiss1.common.WISS1CommonUtil;
import mockit.Mock;
import mockit.MockUp;

class W01ShapeEvidence_test {

    public static void moveToWiss1workspeas() {
        File filePath = new File("C:\\wiss1workspeas\\PT");
        File[] files = filePath.listFiles();
        for (File file: files) {
            if (file.isFile()) {
                String wiss1workspeas = file.toString().replace("wiss1workspeas\\PT", "wiss1workspeas");
                file.renameTo(new File(wiss1workspeas));
            }
        }
    }

    public static void moveToSubDirectory() {
        File wiss1workspeas = new File("C:\\wiss1workspeas");
        File[] files = wiss1workspeas.listFiles();
        for (File file: files) {
            if (file.isFile()) {
                String filePath = file.toString().replace("wiss1workspeas", "wiss1workspeas\\PT");
                file.renameTo(new File(filePath));
            }
        }
    }

    public static void deleteAftermolding(String path) {
        File[] files = new File(path).listFiles();
        for (File file: files) {
            if (file.isDirectory()) {
                deleteAftermolding(path);
            } else {
                file.delete();
            }
        }
        new File(path).delete();
    }

    public static void cleaningAftermolding() {
        File aftermolding = new File("C:\\wiss1workspeas\\aftermolding");
        aftermolding.mkdir();
        File[] files = aftermolding.listFiles();
        for (File file: files) {
            if (file.isFile()) {
                file.delete();
            }
        }
    }

    @BeforeAll
    public static void makeSubDirectory() {
        new File("C:\\wiss1workspeas\\PT").mkdirs();
        moveToSubDirectory();
        new File("C:\\wiss1workspeas\\aftermolding").mkdir();
        deleteAftermolding("C:\\wiss1workspeas\\aftermolding");
    }

    @AfterAll
    public static void resetDirectoryStatus() {
        moveToWiss1workspeas();
        new File("C:\\wiss1workspeas\\PT").delete();
    }



    @Test
    public void 正常系_全てのtsvファイルのエビデンス成型に成功したとき_0() {
        cleaningAftermolding();
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="1"
                return "1";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


    @Test
    public void 正常系_全てのcsvファイルのエビデンス成型に成功したとき_0() {
        cleaningAftermolding();
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            public String next() {
                //１回目入力値="1"
                //2回目="2"
                if (count == 1) {
                    count++;
                    return "1";
                }
                return "2";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


    @Test
    public void 正常系_フォルダ内のtsvファイルを1つ選択したとき_0() {
        cleaningAftermolding();
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="2"
                return "2";
            }

            @Mock
            public String nextLine() {
                //TSVファイル名
                return "division_code_data_20201109175855.tsv";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


    @Test
    public void 準正常系_フォルダ内にtsvファイルが存在しないとき_0() {
        cleaningAftermolding();
        moveToSubDirectory();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="1"
                return "1";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


    @Test
    public void 準正常系_フォルダ内にcsvファイルが存在しないとき_0() {
        cleaningAftermolding();
        moveToSubDirectory();
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            public String next() {
                //１回目入力値="1"
                //2回目="2"
                if (count == 1) {
                    count++;
                    return "1";
                }
                return "2";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("0", actual);
    }


    @Test
    public void 異常系_tsv一括選択時に出力先フォルダがないとき_1() {
        deleteAftermolding("C:\\wiss1workspeas\\aftermolding");
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="1"
                return "1";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
        new File("C:\\wiss1workspeas\\aftermolding").mkdir();
    }


    @Test
    public void 異常系_csv一括選択時に出力先フォルダがないとき_1() {
        deleteAftermolding("C:\\wiss1workspeas\\aftermolding");
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            public String next() {
                //１回目入力値="1"
                //2回目="2"
                if (count == 1) {
                    count++;
                    return "1";
                }
                return "2";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
        new File("C:\\wiss1workspeas\\aftermolding").mkdir();
    }


    @Test
    public void 異常系_tsvファイル選択時に出力先フォルダがないとき_1() {
        deleteAftermolding("C:\\wiss1workspeas\\aftermolding");
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="2"
                return "2";
            }

            @Mock
            public String nextLine() {
                //TSVファイル名
                return "division_code_data_20201109175855.tsv";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
        new File("C:\\wiss1workspeas\\aftermolding").mkdir();
    }


    @Test
    public void 異常系_tsv一括選択時に参照元フォルダがないとき_1() {
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="1"
                return "1";
            }
        };
        new MockUp<WISS1CommonUtil>() {
            @Mock
            public String getProperty(String key) {
                return "C:\\wiss1workspeas_空\\";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    //中身tsvファイルのcsvファイル
    @Test
    public void 異常系_ファイルのエビデンス成型に失敗したとき_1() {
        cleaningAftermolding();
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="2"
                return "2";
            }

            @Mock
            public String nextLine() {
                //CSVファイル名
                return "division_code_data_20201126184007.csv";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    @Test
    public void 異常系_1_フォルダ内のファイル1つを選択したがtsvでもcsvでないとき_1() {
        cleaningAftermolding();
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="2"
                return "2";
            }

            @Mock
            public String nextLine() {
                //Excelファイル名
                return "division_code_data_20201008174724.xlsx";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    @Test
    public void 異常系_1_フォルダ内のファイル1つを選択したがファイルが存在しないとき_1() {
        cleaningAftermolding();
        moveToSubDirectory();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="2"
                return "2";
            }

            @Mock
            public String nextLine() {
                //CSVファイル名
                return "aaa.csv";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    @Test
    public void 異常系_1_フォルダ内のファイル1つを選択したがファイルの中身が空のとき_1() {
        cleaningAftermolding();
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="2"
                return "2";
            }

            @Mock
            public String nextLine() {
                //CSVファイル名
                return "t_employee_datasheader20201118162526.csv";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    @Test
    public void 異常系_1_マクロ実行時に例外が発生したとき_1() {
        cleaningAftermolding();
        moveToWiss1workspeas();
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="2"
                return "2";
            }

            @Mock
            public String nextLine() {
                //TSVファイル名
                return "division_code_data_20201109175855.tsv";
            }
        };

        new MockUp<Runtime>() {
            @Mock
            public Process exec(String command) throws IOException {
                throw new IOException();
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    @Test
    public void 異常系_フォルダ内を全て対象の選択肢で異常値を入力されたとき_1() {
        new MockUp<Scanner>() {
            @Mock
            public String next() {
                //入力値="9"
                return "9";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


    @Test
    public void 異常系_1_フォルダ内ファイル全選択後に異常値が入力されたとき_1() {
        new MockUp<Scanner>() {
            int count = 1;
            @Mock
            public String next() {
                //１回目入力値="1"
                //2回目="9"
                if (count == 1) {
                    count++;
                    return "1";
                }
                return "9";
            }
        };
        W01ShapeEvidence shapeEvidence = new W01ShapeEvidence();
        String actual = shapeEvidence.shapeEvidence();
        assertEquals("1", actual);
    }


}
