package jp.co.wiss1.w01.common;

public class W01CommonUtil {

	static final String BF_STR = "%1";
	static final String DLMTR = ":";

	protected enum MsgStr {
		I01("I01", "%1は正常終了しました。"),
		I02("I02", "%1が完了しました。"),
		E01("E01", "%1は異常終了しました。"),
		E02("E02", "%1に失敗しました。");

		// フィールド定義
		private String id;
		private String msgStr;

		// コンストラクタ
		private MsgStr(String id, String msgStr) {
			this.id = id;
			this.msgStr = msgStr;
		}

		// ID取得
		public String getId() {
			return id;
		}
		// メッセージ取得
		public String getMsgStr() {
			return msgStr;
		}
	}

	// 指定したIDよりメッセージを作成し出力するメソッド
	// 第一引数：指定ID
	// 第二引数以降：任意文字列（%1の箇所を置き換える、複数指定可）
	// 正常完了時：true、異常時：falseを返却
	public boolean outMessage (String msgId, final String ...strs) {
		MsgStr msg = MsgStr.valueOf(msgId);
		String id = msg.getId();
		String message = msg.getMsgStr();

		for (String str : strs) {
			if (str == null) {
				// 任意文字列がNULLの場合、パラメータエラー
				System.out.println("メッセージ出力パラメータエラー");
				return false;
			}
			// 「ID：メッセージ」を生成
			message = id + DLMTR + message.replaceFirst(BF_STR, str);
		}
		// メッセージ出力
		System.out.println(message);
		return true;
	}
}
