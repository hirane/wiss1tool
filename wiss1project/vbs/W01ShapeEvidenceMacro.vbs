' 変数の宣言を強制します
Option Explicit

' 変数を宣言します
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


'対象のファイルのパスを指定
strReadFilePath  = WScript.Arguments(0)

' objFsoにExcelアプリケーションのオブジェクトをセットします
Set objFso = WScript.CreateObject("Scripting.FileSystemObject")

'ファイルを開き、objReadStream オブジェクトを取得
Set objReadStream = CreateObject("ADODB.Stream")
   objReadStream.Open
   objReadStream.Type = 2
   objReadStream.Charset = "UTF-8" 
   objReadStream.LineSeparator = 10
   objReadStream.LoadFromFile strReadFilePath

' objXlsにExcelアプリケーションのオブジェクトをセットします
Set objXls = WScript.CreateObject("Excel.Application")

' Excelの表示
objXls.Visible = False


' TEST.XLSXを開き、そのオブジェクトをobjBookにセットします
Set objBook = objXls.Workbooks.add()

' 変数objSheetを宣言します
Dim objSheet

' objSheetにTEST.XLSXのSheet1のオブジェクトをセットします。
Set objSheet = objBook.Worksheets("Sheet1")


iRow = 1

Do Until objReadStream.EOS

    iCol = 1

    '1 行読み込み
    line = objReadStream.ReadText(-2)
    
    'ダブルクォーテーションを削除
    line = Replace(line, """", "") 

    
    
    If InStr(strReadFilePath, ".csv") > 0 then
        If InStr(line, Chr(9)) <> 0 then
            objXls.DisplayAlerts = False
            objReadStream.Close
            objBook.close
            objXls.Quit()
            Wscript.Quit(1)
        Else
            ' カンマで文字列を区切る
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
            ' タブで文字列を区切る
            strLine = Split(line, Chr(9))
        End If
    End If
  
    for i = 0 to Ubound(strLine)
        ' 書式を文字列に設定
        objSheet.Cells(iRow, iCol).NumberFormatLocal = "@"
        ' データをセルに書き込み
        objSheet.Cells(iRow, iCol).Value = strLine(i)
        ' セルを格子で囲む
        objSheet.Cells(iRow, iCol).Borders.LineStyle = 1
        ' カラムの背景色を変える
        If iRow < 2 then
            objSheet.Cells(iRow, iCol).Interior.ColorIndex = 20
        End If

        ' 列幅の自動調整
        objSheet.columns(iCol).AutoFit()
        iCol = iCol + 1
    Next

    iRow = iRow + 1

Loop

' パスの設定
saveFilePath  = "C:\wiss1workspeas\aftermolding\"

' ファイル名の取得
fileName = objFso.GetBaseName(strReadFilePath)

If objFso.FileExists(saveFilePath & fileName & ".xlsx") then
    objXls.DisplayAlerts = False
    objReadStream.Close
    objBook.close
    objXls.Quit()
    Wscript.Quit(1)
End If

objXls.DisplayAlerts = False

' 名前を付けて保存
objBook.SaveAs(saveFilePath & fileName & ".xlsx")


objReadStream.Close
objBook.close
objXls.Quit()
Wscript.Quit(0)
