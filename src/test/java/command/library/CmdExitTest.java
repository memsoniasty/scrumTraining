package command.library;

import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * @author dominik.schmitz@traveltainment.de
 */
public class CmdExitTest extends CmdTest {

	@Before
	public void setUp() {
		// Check this file structure in base class: crucial to understand the tests.
		this.createTestFileStructure();

		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		commandInvoker.addCommand(new CmdExit("exit", drive));
	}

	@Test
	public void cmdExit_WithoutParameter() {
		executeCommand("exit");
		TestHelper.assertOutputIsEmpty(testOutput);
	}

	@Test
	public void cmdExit_WithParameter() {
		executeCommand("exit /w");
		TestHelper.assertContains("syntax of the command is incorrect", testOutput.toString());
	}

}
