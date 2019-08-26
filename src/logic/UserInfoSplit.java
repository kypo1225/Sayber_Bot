package logic;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class UserInfoSplit {
	String userInfo;

	String token = "token=";
	String id = "user_id=";
	String name = "username=";
	String text = "text=";

	private String completeToken;
	private int completeId;
	private String completeName;
	private String completeText;

	public UserInfoSplit(String json) {

		this.userInfo = json;
	}

	//	public String getToken() {
	//		int tokenStart = userInfo.indexOf(token) + token.length();
	//		int tokenEnd = userInfo.indexOf("&");
	//		this.completeToken =  userInfo.substring(tokenStart, tokenEnd);
	//
	//		return completeToken;
	//	}

	public int getId() {
		int idStart = userInfo.indexOf(id) + id.length();
		String s = userInfo.substring(idStart);
		int idEnd = s.indexOf("&");
		String strId = userInfo.substring(idStart, idStart + idEnd);
		this.completeId = Integer.parseInt(strId);

		return completeId;

	}
	//
	//	public String getName() {
	//		int nameStart = userInfo.indexOf(name) + name.length();
	//		this.completeName = userInfo.substring(nameStart,userInfo.length());
	//
	//		return completeName;
	//	}

	public String getText() {
		int textStart = userInfo.indexOf(text) + text.length();
		completeText = userInfo.substring(textStart, userInfo.length());
		try {
			String decodeText = URLDecoder.decode(completeText, "UTF-8");
			return decodeText;
		} catch (UnsupportedEncodingException e) {
			System.out.println("デコード失敗");
			e.printStackTrace();
		}
		return "デコード失敗です";
	}
}