package command.library;

import command.framework.Command;
import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

/**
 * @author dominik.schmitz@traveltainment.de
 */
public class CmdHelpTest extends CmdTest {

    @Before
    public void setUp() {
        // Check this file structure in base class: crucial to understand the tests.
        this.createTestFileStructure();

        // Add all commands which are necessary to execute this unit test
        // Important: Other commands are not available unless added here.
        commandInvoker.addCommand(new CmdHelp("help", drive));
    }

    @Test
    public void cmdVer_WithoutParameter_Static() {
        executeCommand("help");
        TestHelper.assertContains("dir", testOutput.toString());
        TestHelper.assertContains("exit", testOutput.toString());
        TestHelper.assertContains("type", testOutput.toString());
    }


    @Test
    public void cmdVer_WithoutParameter_CommandFactory() {
        executeCommand("help");
        ArrayList<Command> commandList = new CommandFactory(drive).getCommandList();
        for (Command command : commandList) {
            if (command.getClass() != CmdHelp.class) {
                TestHelper.assertContains(command.toString(), testOutput.toString());
            }
        }
    }

    @Test
    public void cmdVer_WithParameter() {
        executeCommand("help /w");
        TestHelper.assertContains("syntax of the command is incorrect", testOutput.toString());
    }
}
