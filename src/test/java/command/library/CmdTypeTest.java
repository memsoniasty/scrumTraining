package command.library;

import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * @author dominik.schmitz@traveltainment.de
 */
public class CmdTypeTest extends CmdTest {

	@Before
	public void setUp() {
		// Check this file structure in base class: crucial to understand the tests.
		this.createTestFileStructure();

		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		commandInvoker.addCommand(new CmdType("type", drive));
	}

	@Test
	public void cmdType_WithExistingFile () {
		executeCommand("type FileInRoot1");
		TestHelper.assertContains("an entry", testOutput.toString());
	}

	@Test
	public void cmdType_WithNoParameter_ReportsError() {
		executeCommand("type");
		TestHelper.assertContains("syntax of the command is incorrect", testOutput.toString());
	}

	@Test
	public void cmdType_NotExistingFile_ReportsError() {
		executeCommand("type NotExistingFile");
		TestHelper.assertContains("File does not exist in current directory", testOutput.toString());
	}

	@Test
	public void cmdType_WithDirName_ReportsError() {
		executeCommand("type subDir1");
		TestHelper.assertContains("Access denied!", testOutput.toString());
	}
}
