import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class lab5_v2 {

	public static void main(String[] args) {
		try {
			FileInputStream FIS = new FileInputStream("D:\\Война и мир.txt"); // открываем файл с книгой
			BufferedReader BR = new BufferedReader(new InputStreamReader(FIS, "UTF-8")); // Объявляем откуда и с какой
																							// кодировкой будем
																							// считывать
			String str;
			Character chars[] = {};
			while ((str = BR.readLine()) != null) { // пока файл не закончился
				str = str.replaceAll("[^а-яА-Яa-zA-Z+[ёЁ]]", "").toLowerCase(); // считываем строку, убираем лишнее и
																				// уменьшаем буквы
				Character[] charObjectArray = str.chars().mapToObj(c -> (char) c).toArray(Character[]::new); // переводим
																												// строку
																												// в
																												// массив
																												// типа
																												// Caracters
				chars = concat(chars, charObjectArray); // добавляем в массив новый массив букв

			}
			for (int l = 0; l < 10; l++) {
				Character x[] = majority(chars, 10000 * l, 10000 * (l + 1), 10); // считаем 10 частовстречаемых каждую
																					// 10000 символов 10 раз
				for (int i = 0; i < 10 && i < x.length; i++)
					System.out.print(x[i].charValue() + " ");// выводим на печать наши буквы
				System.out.println();
			}
			BR.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Алгоритм выжигания
	private static Character[] majority(Character[] array, int B, int N, int k) {
		// словарь стоящих букв изначально пуст
		LinkedHashMap<Character, Integer> candidates = new LinkedHashMap<Character, Integer>();

		// проходим по массиву и усаживаем группы по k
		for (int i = B; i < N; i++) {

			// у следующего такой же элемент, как у стоящих? тогда встанет вместе с ними
			if (candidates.containsKey(array[i]))
				candidates.put(array[i], candidates.get(array[i]).intValue() + 1);
			else // может, стоят менее чем с k-1 элементами?
			if (candidates.size() < k - 1)
				candidates.put(array[i], 1);
			else // стоят с k-1 другими элементами, так что уменьшаем всю группу
			{
				Character x[] = (Character[]) candidates.keySet().toArray(new Character[candidates.size()]); // перевод
																												// ключей
																												// в
																												// массив
																												// букв
				for (Character l : x)
					if (candidates.get(l).intValue() == 0) // если пустой
						candidates.remove(l); // удаляем
					else // иначе уменьшаем счетчик хитпоинтов :D
						candidates.put(l, candidates.get(l).intValue() - 1);
			}
		}

		return (Character[]) candidates.keySet().toArray(new Character[candidates.size()]); // выдаем наш результат в
																							// виде нового массива из
																							// нужных букв
	}

	// Функция для складывания массивов. Например нам надо получить массив С в
	// котором будут элементы массива А, а затем элементы массива В
	static Character[] concat(Character[] A, Character[] B) {
		int aLen = A.length;
		int bLen = B.length;
		Character[] C = new Character[aLen + bLen];
		System.arraycopy(A, 0, C, 0, aLen);
		System.arraycopy(B, 0, C, aLen, bLen);
		return C;
	}

}
