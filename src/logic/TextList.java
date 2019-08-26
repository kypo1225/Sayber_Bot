package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TextList {
	Map<String, String> map = new HashMap<>();
	List<String> judgeTextList = new ArrayList<>();

	public Map<String, String> createMap() {
		map.put("おはよう", "おはYO");
		map.put("こんにちは", "こんにちは");
		map.put("こんばんは", "こんばんは草");
		map.put("おやすみなさい", "おやすみなさいBrother");

		return map;
	}

	public List<String> judgeList() {

		for (String key : map.keySet()) {
			judgeTextList.add(key);
			System.out.println(key + "aa");
		}
		return judgeTextList;
	}
}