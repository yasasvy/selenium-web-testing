package multiple_File_Handling;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestCase {
	private String fileName; // = "C:\\Users\\guntu\\Desktop\\TestCase.txt"
	// Instantiating FileReader to read text file passed as parameter
	private List<TestCaseStep> tcSteps;
	private Map<String, String> testData;

	
	
	public TestCase(String fileName) {
		this.fileName = fileName;
	}
	
	public void execute(){
		createTestSteps();
		if(null == tcSteps || tcSteps.isEmpty()) {
			return;
		}
		for (TestCaseStep step : tcSteps) {
			step.execute();
		}
	}
	
	
	private void createTestSteps() {
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String lineText = null;

		try {
			tcSteps = new ArrayList<TestCaseStep>();
			while ((lineText = bufferedReader.readLine()) != null) {
				// String[] str = MapFormat.format(lineText, testData).split("\\t");
				
				//TODO Replace Parameters with Values from data file
				String step = lineText;
				if(null != testData){
					step = MapFormat.format(lineText, testData);
				}
				TestCaseStep tcstep = new TestCaseStep(step);
				tcSteps.add(tcstep);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Close BufferedReader
		try {
			bufferedReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			fileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setTestData(Map<String, String> testData) {
		this.testData = testData;
	}
}