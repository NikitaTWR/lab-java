import java.util.InputMismatchException;
import java.util.Scanner;

public class lab3 {

	public static void main(String[] args) {
		int a, b, c;
		var in = new Scanner(System.in);
		System.out.println("Введи a,b,c ");
		try {
			a = in.nextInt();
			b = in.nextInt();
			c = in.nextInt();
			QuadUr(a, b, c);
		} catch (InputMismatchException e) {
			System.out.println("Введены не целые числа.");
			return;
		}
	}

	public static void QuadUr(int a, int b, int c) {
		double res, imz;
		if (a == 0) {
			if (b == 0) {
				if (c == 0)
					System.out.println("Любое число");
				else
					System.out.println("Нет корней");
				return;
			}
			res = (double) -c / b;
			System.out.println("x = " + res);
			return;
		}
		if (b * b - 4 * a * c >= 0) {
			res = (double) (-b - Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			System.out.println("x1 = " + res);
			res = (double) (-b + Math.sqrt(b * b - 4 * a * c)) / (2 * a);
			System.out.println("x2 = " + res);
			return;
		}
		res = (double) (-b) / (2 * a);
		imz = Math.abs(Math.sqrt(Math.abs((b * b - 4 * a * c))) / (2 * a));
		System.out.println("x1 = " + res + " + " + imz + "i");
		System.out.println("x2 = " + res + " - " + imz + "i");
	}
}
