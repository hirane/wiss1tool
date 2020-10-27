package jp.co.wiss1.w01.business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import jp.co.wiss1.w01.common.W01CommonConst;
import jp.co.wiss1.w01.common.W01CommonUtil;

/**
 * W01ConvertFileTsvToCsvc
 *
 * tsv����csv�̕ϊ����s��
 *
 * @author hara
 * @varsion
 *
 */
public class W01ConvertFileTsvToCsv {

    private static W01CommonUtil message = new W01CommonUtil();

    /**
     * main
     *
     * ���C������
     *
     * @return �������ʂ�ԋp���� 0:����I�� 1:�ُ�I��
     */
    @SuppressWarnings("resource")
    public String main() {

        System.out.println("�t�@�C���̊i�[��i��΃p�X�j����͂��������F");
        Scanner scan = new Scanner(System.in);
        // ���[�U�[����
        String readFile = scan.next();

        if (checkInputPath(readFile) == false) {
            // �ُ�I��
            return W01CommonConst.ERROR;
        }
        String createFile = readFile.replace(W01CommonConst.CONST_EXTENSION_TSV, W01CommonConst.CONST_EXTENSION_CSV);
        // tsv�t�@�C���ǂݍ��݃��\�b�h���Ăяo��
        List<String> list = ReadFile(readFile);
        if (list == null) {
            message.outMessage("I03", "�^�u��؂�ƂȂ��Ă���TSV�t�@�C��");
            // �ُ�I��
            return W01CommonConst.ERROR;
        }

        // Csv�t�@�C���o�̓��\�b�h���Ăяo��
        int result = CreateCsv(createFile, list);
        if (result == 0) {
            // ����I��
            return W01CommonConst.SUCCESS;
        } else {
            // �ُ�I��
            return W01CommonConst.ERROR;
        }

    }

    /**
     * ReadFile
     *
     * tsv�t�@�C���ǂݍ��݃��\�b�h
     *
     * @param readFileName �ǂݍ��ݑΏۃt�@�C����
     * @return List<String[]> �ǂݍ��ݑΏۃt�@�C���̓��e
     */
    @SuppressWarnings("resource")
    public static List<String> ReadFile(String readFileName) {
        List<String> list = new ArrayList<String>(0);
        try {
            File file = new File(readFileName);
            String str = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), W01CommonConst.CONST_CHAR_CODE_UTF8));

            // �ǂݍ��ݑΏۃt�@�C���̓��e�����X�g�ɒǉ�
            while ((str = br.readLine()) != null) {
                String data = str;
                if (data.contains(W01CommonConst.CONST_ST_COMMA)) {
                    return null;
                }
                String tmpArray = data.replace(W01CommonConst.CONST_ST_TAB, W01CommonConst.CONST_ST_COMMA);
                list.add(tmpArray);
            }
            br.close();
        } catch (Exception e) {
            // �ǂݍ��݂ɂĈُ�I��
            return null;
        }
        // �ǂݍ��ݐ���I��
        return list;
    }

    /**
     * CreateCsv
     *
     * Csv�t�@�C���o�̓��\�b�h
     *
     * @param createFileName
     * @param list
     * @return
     */
    public static int CreateCsv(String createFileName, List<String> list) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(createFileName), W01CommonConst.CONST_CHAR_CODE_UTF8)));

            // ,��ǉ����A�t�@�C���o��
            for (String tmpStringArray : list) {
                pw.println(String.join(W01CommonConst.CONST_ST_COMMA, tmpStringArray));
            }
            // �t�@�C�������
            pw.close();
            return 0;
        } catch (Exception e) {
            // �t�@�C���o�͂ňُ�I��
            return 1;
        }

    }

    public static boolean checkInputPath(String readFile) {

        // �g���q�`�F�b�N
        if (!readFile.endsWith(W01CommonConst.CONST_EXTENSION_TSV)) {
            message.outMessage("I03", "TSV�t�@�C��");
            return false;
        }
        // �t�@�C���̑��݊m�F
        File file = new File(readFile);
        if (!file.exists()) {
            message.outMessage("I03", "�������i�[��i��΃p�X�j");
            return false;
        }
        // �t�@�C���T�C�Y�̊m�F
        if (file.length() == 0) {
            message.outMessage("E03", "�t�@�C�����Ƀf�[�^");
            return false;
        }
        return true;
    }
}