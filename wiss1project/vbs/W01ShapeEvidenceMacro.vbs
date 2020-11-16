' �ϐ��̐錾���������܂�
Option Explicit

' �ϐ���錾���܂�
Dim objFso
Dim strReadFilePath
Dim objReadStream
Dim strLine
Dim objXls
Dim objBook
Dim iRow
Dim iCol
Dim line
Dim i
Dim fileName
Dim saveFilePath


'�Ώۂ̃t�@�C���̃p�X���w��
strReadFilePath  = WScript.Arguments(0)

' objFso��Excel�A�v���P�[�V�����̃I�u�W�F�N�g���Z�b�g���܂�
Set objFso = WScript.CreateObject("Scripting.FileSystemObject")

'�t�@�C�����J���AobjReadStream �I�u�W�F�N�g���擾
Set objReadStream = CreateObject("ADODB.Stream")
   objReadStream.Open
   objReadStream.Type = 2
   objReadStream.Charset = "UTF-8" 
   objReadStream.LineSeparator = 10
   objReadStream.LoadFromFile strReadFilePath

' objXls��Excel�A�v���P�[�V�����̃I�u�W�F�N�g���Z�b�g���܂�
Set objXls = WScript.CreateObject("Excel.Application")

' Excel�̕\��
objXls.Visible = False


' TEST.XLSX���J���A���̃I�u�W�F�N�g��objBook�ɃZ�b�g���܂�
Set objBook = objXls.Workbooks.add()

' �ϐ�objSheet��錾���܂�
Dim objSheet

' objSheet��TEST.XLSX��Sheet1�̃I�u�W�F�N�g���Z�b�g���܂��B
Set objSheet = objBook.Worksheets("Sheet1")


iRow = 1

Do Until objReadStream.EOS

    iCol = 1

    '1 �s�ǂݍ���
    line = objReadStream.ReadText(-2)
    
    '�_�u���N�H�[�e�[�V�������폜
    line = Replace(line, """", "") 

    
    
    If InStr(strReadFilePath, ".csv") > 0 then
        If InStr(line, Chr(9)) <> 0 then
            objXls.DisplayAlerts = False
            objReadStream.Close
            objBook.close
            objXls.Quit()
            Wscript.Quit(1)
        Else
            ' �J���}�ŕ��������؂�
            strLine = Split(line, ",")
        End If
    ElseIf InStr(strReadFilePath, ".tsv") > 0 then
        If InStr(line, ",")  <> 0 then
            objXls.DisplayAlerts = False
            objReadStream.Close
            objBook.close
            objXls.Quit()
            Wscript.Quit(1)
        Else
            ' �^�u�ŕ��������؂�
            strLine = Split(line, Chr(9))
        End If
    End If
  
    for i = 0 to Ubound(strLine)
        ' �����𕶎���ɐݒ�
        objSheet.Cells(iRow, iCol).NumberFormatLocal = "@"
        ' �f�[�^���Z���ɏ�������
        objSheet.Cells(iRow, iCol).Value = strLine(i)
        ' �Z�����i�q�ň͂�
        objSheet.Cells(iRow, iCol).Borders.LineStyle = 1
        ' �J�����̔w�i�F��ς���
        If iRow < 2 then
            objSheet.Cells(iRow, iCol).Interior.ColorIndex = 20
        End If

        ' �񕝂̎�������
        objSheet.columns(iCol).AutoFit()
        iCol = iCol + 1
    Next

    iRow = iRow + 1

Loop

' �p�X�̐ݒ�
saveFilePath  = "C:\wiss1workspeas\aftermolding\"

' �t�@�C�����̎擾
fileName = objFso.GetBaseName(strReadFilePath)

If objFso.FileExists(saveFilePath & fileName & ".xlsx") then
    objXls.DisplayAlerts = False
    objReadStream.Close
    objBook.close
    objXls.Quit()
    Wscript.Quit(1)
End If

objXls.DisplayAlerts = False

' ���O��t���ĕۑ�
objBook.SaveAs(saveFilePath & fileName & ".xlsx")


objReadStream.Close
objBook.close
objXls.Quit()
Wscript.Quit(0)
