package command.library;

import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;

/**
 * @author dominik.schmitz@traveltainment.de
 */
public class CmdVerTest extends CmdTest {

	@Before
	public void setUp()
	{
		// Check this file structure in base class: crucial to understand the tests.
		this.createTestFileStructure();

		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		commandInvoker.addCommand(new CmdVer("ver", drive));
	}

	@Test
	public void cmdVer_WithoutParameter () {
		executeCommand("ver");
		TestHelper.assertContains("Microsoft Windows [Version", testOutput.toString());
	}

	@Test
	public void cmdVer_WithParameter () {
		executeCommand("ver /w");
		TestHelper.assertContains("Microsoft Windows [Version", testOutput.toString());
		TestHelper.assertContains("Dominik Schmitz", testOutput.toString());
	}

	@Test
	public void cmdVer_WithTwoParameter_ReportsError()
	{
		executeCommand("ver /w wrongParameter");
		TestHelper.assertContains("syntax of the command is incorrect", testOutput.toString());
	}
}
