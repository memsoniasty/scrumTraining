package command.library;

import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;


public class CmdVolTest extends CmdTest{

    @Before
    public void setUp() {
        this.createTestFileStructure();
        // Add all commands which are necessary to execute this unit test
        // Important: Other commands are not available unless added here.
//        drive.setLabel("Hello World");
        commandInvoker.addCommand(new CmdVol("vol", drive));
    }

    @Test
    public void cmdVol_withoutParameters(){
        executeCommand("vol");
        TestHelper.assertContains("Hello World", testOutput.toString());
        TestHelper.assertContains("IE16-IE3", testOutput.toString());
    }


}