//Выигрышная стратегия
//Лучше не играйте в лотерею, проиграете
//Если выиграли то бегите пока не поздно.
//В лотереях не разбираюсь :D
//Программа симулирует моё понимание лотереи

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Lottery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		var in = new Scanner(System.in);

		// начальные параметры
		String input;
		int budget = 100000;
		int hiddenBudget = 0;
		int winTickets = 0;
		Random random = new Random();
		HashMap<Integer, Boolean> tickets = new HashMap<>();
		int ticketCount = 10000;
		// генерируем 10% выигрышных билетов
		for (int i = 0; i < ticketCount; i++) {
			if (winTickets < ticketCount / 10) {
				tickets.put(i, random.nextInt() % 100 < 10);
				if (tickets.get(i))
					winTickets++;
			} else {
				tickets.put(i, false);
			}
		}
		// если сгенерировалось меньше 10% выигрышных билетов то случайно делаем
		// проигравшие
		// билеты выигрышными до 10%
		if (winTickets < ticketCount / 10) {
			for (int i = 0; i < ticketCount; i++) {
				if (winTickets < ticketCount / 10) {
					if (!tickets.get(i)) {
						tickets.put(i, random.nextInt() % 100 < 10);
						if (tickets.get(i))
							winTickets++;
					}
				}
			}
		}

		System.out.println("Генерация билетов завершена:\n");
		// Ну и сама игра
		int clientBudget = 10000;
		System.out.println("Кол-во билетов:" + ticketCount + " Выигрышных: " + winTickets);
		while (true) {
			System.out.println("Купить случайный билет? y/n");
			input = in.next();
			if (input.toLowerCase().charAt(0) == 'y') {
				System.out.println("Покупка случайного билета");
				int index;
				do {
					index = random.nextInt() % ticketCount;
				} while (!tickets.containsKey(index));
				if (tickets.get(index)) {
					System.out.println("Вы выиграли 300 рублей!");
					budget -= 300;
					clientBudget += 300;
				} else {
					System.out.println("Вы Проиграли 100 рублей!");
					budget += 50;
					clientBudget -= 100;
					hiddenBudget += 50;
				}
				tickets.remove(index);
				if (tickets.size() == 0) {
					System.out.println("Билеты закончились, приходите в след. месяце :)");
					return;
				}
				System.out.println(
						"Баланс:" + clientBudget + " Джекпот:" + budget + " Скрытый баланс казино:" + hiddenBudget);
				if (clientBudget < 100) {
					System.out.println("Вы бедняк, пока.");
					return;
				}
			} else {
				System.out.println("Вы ушли!\nБаланс:" + clientBudget + " Джекпот:" + budget + " Скрытый баланс казино:"
						+ hiddenBudget);
				return;
			}
		}

	}

}
