package multiple_File_Handling;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestCaseStep {
	private String action;
	private String target;
	private String value;

	private static WebDriver driver;

	static {
		driver = TestRunner.getDriver();
	}

	public TestCaseStep(String lineText) {
		String[] stepParts = lineText.split("\\t");
		this.action = stepParts[0];
		/*if (stepParts.length == 2) {
			this.value = stepParts[1];
		} else {*/
			this.target = stepParts[1];
			if(stepParts.length > 2) {
				this.value = stepParts[2];
			} else {
				this.value = null;
			}
		// }
	}

	public void execute() {
		if (action.equalsIgnoreCase("GOTO")) {
			doGOTO();
		} else if (action.equalsIgnoreCase("ENTER")) {
			doEnterData();
		} else if (action.equalsIgnoreCase("CLICK")) {
			doClickEvent();
		} else if (action.equalsIgnoreCase("LINKTEXT")) {
			doClickTextEvent();
		}
		else if(action.equalsIgnoreCase("CLICKXPATH")) {
			doClickXpath();
		} else if(action.equalsIgnoreCase("SCROLLCLICK")) {
			doScrollClick();
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	private void doScrollClick() {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		WebElement elem =  driver.findElement(By.cssSelector(target));

		 //this line will scroll down to make element visible
		js.executeScript("window.scrollTo(" + elem.getLocation().x + "," +(elem.getLocation().y- 100) + ");");


		
	}

	private void doClickXpath() {
		driver.findElement(By.xpath(target)).click();
		
	}

	private void doClickTextEvent() {
		driver.findElement(By.linkText(value)).click();

	}

	private void doClickEvent() {
		driver.findElement(By.id(target)).click();
	}

	private void doEnterData() {
		driver.findElement(By.id(target)).clear();
		driver.findElement(By.id(target)).sendKeys(value);

	}

	private void doGOTO() {
		driver.get(value);
		System.out.println(driver.getTitle());
	}
}
