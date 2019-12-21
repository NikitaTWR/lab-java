package calc;

import java.util.regex.Pattern;

public class Calculator {
	public String Calc(String example) {
		String[] operation = null;
		if (Pattern.matches("^\\d{1,10}\\s[+-/*]\\s\\d{1,10}$", example)) {
			operation = example.split(" ");
			return example + Solve(Integer.parseInt(operation[0]), Integer.parseInt(operation[2]), operation[1]);
		}
		return "Error in your example";

	}

	public String Solve(int a, int b, String op){
		switch(op) {
		case "+":
			return " = " + (a + b) + "\n";
		case "-":
			return " = " + (a - b) + "\n";
		case "*":
			return " = " + (a * b) + "\n";
		case "/":
			return " =  " + (a / b) + "\n";
		default:
			return "Error";
		}
	}

}
