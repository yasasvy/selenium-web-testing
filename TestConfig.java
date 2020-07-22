package multiple_File_Handling;

public class TestConfig {

	private String testCaseName;
	private String testDataName;
	private int testDataIndex;

	public TestConfig(String configLine) {
		String[] parts = configLine.split("\\t");
		if(null != parts && parts.length > 0) {
			setTestCaseName(parts[0].trim());
			if(parts.length > 1) {
				setTestDataName(parts[1].trim());
			}
			if(parts.length > 2) {
				setTestDataIndex(Integer.parseInt(parts[2].trim()));
			}
		}
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public String getTestDataName() {
		return testDataName;
	}

	public void setTestDataName(String testDataName) {
		this.testDataName = testDataName;
	}

	public int getTestDataIndex() {
		return testDataIndex;
	}

	public void setTestDataIndex(int testDataIndex) {
		this.testDataIndex = testDataIndex;
	}
}
