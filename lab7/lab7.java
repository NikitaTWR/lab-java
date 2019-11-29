import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class lab7 {
	static WebDriver wd = null;

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver", "D:/geckodriver.exe");
		wd = new FirefoxDriver();
		wd.manage().window().maximize();
		long sum = 0;
		int iter = 0;
		for (int j = 0; j < 20; j++) {
			wd.get("https://hh.ru/search/vacancy?L_is_autosearch=false&area=1&clusters=true&enable_snippets=true&text=Java+developer&page="
					+ j);

			for (int i = 1; i < 24; ++i)
				try {
					String sal = wd.findElement(
							By.xpath("/html/body/div[6]/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/div["
									+ i + "]/div[1]/div[2]/div"))
							.getText();
					// /html/body/div[5]/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/div[3]/div[1]/div[2]/div
					// /html/body/div[5]/div/div/div[2]/div/div[3]/div/div[2]/div/div[2]/div/div/div[24]/div[1]/div[2]/div
					// sal = sal.replace("&nbsp;","");
					System.out.println(sal);
					int min;
					int max;
					int avr;
					String[] splt = sal.split(" ");
					String[] splt2;
					if (sal.contains("от"))
						avr = Integer.parseInt(splt[1] + splt[2]);
					else if (sal.contains("до"))
						avr = Integer.parseInt(splt[1] + splt[2]);
					else {
						splt2 = splt[1].split("-");
						min = Integer.parseInt(splt[0] + splt2[0]);
						max = Integer.parseInt(splt2[1] + splt[2]);
						avr = (int) (min + max) / 2;
					}
					if (sal.contains("USD"))
						avr *= 64;
					else if (sal.contains("EUR"))
						avr *= 70;
					sum += avr;
					iter++;
					System.out.println(avr + " рублей");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				} finally {
					// wd.close();
				}
		}
		System.out.println("Средняя зп программиста java = " + (sum / iter) + " рублей");
		wd.close();
	}

}
