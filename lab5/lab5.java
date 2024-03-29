import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class lab5 {

	public static void main(String[] args) {
		try {
			FileInputStream FIS = new FileInputStream("D:\\Война и мир.txt");
			BufferedReader BR = new BufferedReader(new InputStreamReader(FIS, "UTF-8"));
			String str;
			int j = 0;
			char chars[];
			ArrayList<List<Map.Entry<Character, Integer>>> List = new ArrayList<List<Map.Entry<Character, Integer>>>();
			Map<Character, Integer> charToCount = new HashMap<>();
			while ((str = BR.readLine()) != null) {
				str = str.replaceAll("[^а-яА-Яa-zA-Z+[ёЁ]]", "");
				chars = str.toCharArray();
				for (int i = 0; i < chars.length; i++) {
					for (char ch : chars) {

						if (!charToCount.containsKey(Character.toLowerCase(ch))) {
							charToCount.put(Character.toLowerCase(ch), 0);
						}
						charToCount.put(Character.toLowerCase(ch), charToCount.get(Character.toLowerCase(ch)) + 1);
						j++;
						if (j > 10000) {
							List<Map.Entry<Character, Integer>> a = new ArrayList<>(charToCount.entrySet());
							sort(a);
							List.add(a);
							j = 0;
							charToCount = new HashMap<>();
						}
					}
				}

			}

			for (int l = 0; l < 10; l++) {
				for (int k = 0; k < 10; k++)
					System.out.print(List.get(k).get(l).getKey() + " " + List.get(k).get(l).getValue() + " ");
				System.out.print("\n");
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private static void sort(List<Map.Entry<Character, Integer>> a) {
		Collections.sort(a, new Comparator() {
			public int compare(Object o1, Object o2) {
				Map.Entry e1 = (Map.Entry) o1;
				Map.Entry e2 = (Map.Entry) o2;
				return ((Comparable) e2.getValue()).compareTo(e1.getValue());
			}
		});
	}
}
