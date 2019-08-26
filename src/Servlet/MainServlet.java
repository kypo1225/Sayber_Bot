package Servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.HttpLogic;
import logic.TextList;
import logic.UserInfoSplit;

@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		PrintWriter out = response.getWriter();
		out.println("<html><meta charset=\"UTF-8\"><head></head><body><h1>SynologyChatから入力待ちでござ</h1></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		StringBuilder sb = new StringBuilder();
		try (BufferedReader reader = request.getReader()) {
			String line;
			while ((line = reader.readLine()) != null)
				sb.append(line);
		}

		UserInfoSplit split = new UserInfoSplit(sb.toString());
		String sendString = "テスト中。「おはよう」、「こんにちは」、「こんばんは」、「おやすみなさい」にしか対応できません。";

		TextList l = new TextList();
		Map<String, String> textMap = l.createMap();
		List<String> textList = l.judgeList();
		Iterator<String> iterator = textList.iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			if (split.getText().equals(key)) {
				sendString = textMap.get(key);
				//				System.out.println(sendString);
				break;
			} else
				System.out.println(textMap.get(key));
		}

		String url = "https://aoaccess.dscloud.biz:5551/webapi/entry.cgi?api=SYNO.Chat.External&method=chatbot&version=2&token=%22BYLJ6nTkIrTK4NVFc1REhGWkPWLZx9kBCDSqahOjQc6cggiUve0ZMoENtrN9lpRa%22";

		String payload = "payload={\"text\": \"" + sendString + "\", \"user_ids\": [" + split.getId() + "] }";
		HttpLogic httpLogic = new HttpLogic();
		httpLogic.callPost(url, "UTF-8", payload);

	}
}