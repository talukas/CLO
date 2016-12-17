package core;

//BEGIN
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HW_34 {
	public static void main(String[] args) {

		if (args.length == 0) {
			System.err.println("No arguments!");
			System.exit(1);
		}

		WebDriver driver = new FirefoxDriver();
		
		for (int i = 0; i < args.length; i++) {

			String text_case_id = "TC-001.0" + (i + 1);
			String param[] = args[i].split("\\|");

			String url = param[0];
			String title_expected = param[1];
			
			driver.get(url);
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			String title_actual = driver.getTitle();
				System.out.println("");
			if (title_expected.equals(title_actual)) {
				System.out.println("Test Case ID: \t\t" + text_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + title_expected);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "PASSED");
			} else {
				System.out.println("Test Case ID: \t\t" + text_case_id);
				System.out.println("URL: \t\t\t" + url);
				System.out.println("Title Expected: \t" + title_expected);
				System.out.println("Title Actual: \t\t" + title_actual);
				System.out.println("Test Case Result: \t" + "FAILED");
			}
			
		}
	
		driver.quit();
	}
}
// END
