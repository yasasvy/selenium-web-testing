package multiple_File_Handling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestRunner {

	public static void main(String[] config) {
		TestRunner testExecutor = new TestRunner(config[0]);
		testExecutor.execute();
	}

	private static WebDriver driver = getFireFoxDriver();
	private String configFileName;
	private List<TestConfig> lstConfigs;

	public TestRunner(String configFileName) {
		this.configFileName = configFileName;
		initConfig();
	}

	public void execute() {
		for (TestConfig testConfig : lstConfigs) {
			TestCase tCase = new TestCase(testConfig.getTestCaseName());
			if (null != testConfig.getTestDataName()) {
				tCase.setTestData(getTestData(testConfig.getTestDataName(), testConfig.getTestDataIndex()));
			}
			tCase.execute();
		}

		driver.close();
	}

	private Map<String, String> getTestData(String testDataFileName, int index) {
		// String inputJson ="C:\\Users\\guntu\\Desktop\\TestData1.json";
		// String[] str2 = new String[2];
		ObjectMapper mapper = new ObjectMapper();
		try {
			TypeReference<List<Map<String, String>>> ref = new TypeReference<List<Map<String, String>>>() {
			};
			List<Map<String, String>> testData = mapper.readValue(new File(testDataFileName), ref);
			return testData.get(index - 1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private void initConfig() {
		// Instantiating FileReader to read text file passed as parameter
		lstConfigs = new ArrayList<TestConfig>();
		FileReader fileReader1 = null;
		try {
			fileReader1 = new FileReader(configFileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader1);
			String lineText = null;
			// ArrayList<String> args = new ArrayList<String>();
			while ((lineText = bufferedReader.readLine()) != null) {
				if (!lineText.trim().isEmpty()) {
					lstConfigs.add(new TestConfig(lineText));
				}
			}

			bufferedReader.close();
			fileReader1.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}

	private static WebDriver getFireFoxDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\guntu\\Desktop\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		return driver;
	}
}
