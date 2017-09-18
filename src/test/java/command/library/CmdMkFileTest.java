/*
 * DOSBox, Scrum.org, Professional Scrum Developer Training
 * Authors: Rainer Grau, Daniel Tobler, Zuehlke Technology Group
 * Copyright (c) 2013 All Right Reserved
 */ 

package command.library;

import filesystem.File;
import helpers.TestHelper;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CmdMkFileTest extends CmdTest {

	@Before
	public void setUp() {
        // Check this file structure in base class: crucial to understand the tests.
        this.createTestFileStructure();
		
		// Add all commands which are necessary to execute this unit test
		// Important: Other commands are not available unless added here.
		this.commandInvoker.addCommand(new CmdMkFile("mkfile", this.drive));
	}

    @Test
    public void CmdMkFile_CreatesNewFile()
    {
        // given
        final String newFileName = "testFile";

        // when
        executeCommand("mkfile " + newFileName);

        // then
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertOutputIsEmpty(testOutput);
        File createdFile = TestHelper.getFile(drive, drive.getCurrentDirectory().getPath(), newFileName);
        assertNotNull(createdFile);
    }

    @Test
    public void CmdMkFile_WithoutContent_CreatesEmptyFile()
    {
        // given
        final String newFileName = "testFile";

        // when
        executeCommand("mkfile " + newFileName);

        // then
        File createdFile = TestHelper.getFile(drive, drive.getCurrentDirectory().getPath(), newFileName);
        assertEquals("", createdFile.getFileContent());
    }

    @Test
    public void CmdMkFile_WithContent_CreatesFileWithContent()
    {
        // given
        final String newFileName = "testFile";
        final String newFileContent = "ThisIsTheContent";

        // when
        executeCommand("mkfile " + newFileName + " " + newFileContent);

        // then
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertOutputIsEmpty(testOutput);
        File createdFile = TestHelper.getFile(drive, drive.getCurrentDirectory().getPath(), newFileName);
        assertEquals(newFileContent, createdFile.getFileContent());
    }

    @Test
    public void CmdMkFile_NoParameters_ReportsError()
    {
        executeCommand("mkfile");
        assertEquals(numbersOfFilesBeforeTest, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertContains("syntax of the command is incorrect", testOutput.toString());
    }

    @Test
    public void CmdMkFile_Duplicate_File()
    {
        executeCommand("mkfile gugus");
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertOutputIsEmpty(testOutput);
        executeCommand("mkfile gugus");
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertContains("A subdirectory or file 'gugus' already exists", testOutput.toString());
    }


    @Test
    public void CmdMkFile_Duplicate_File_Case()
    {
        executeCommand("mkfile gugus");
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertOutputIsEmpty(testOutput);
        executeCommand("mkfile Gugus");
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertContains("A subdirectory or file 'Gugus' already exists", testOutput.toString());
    }

    @Test
    public void CmdMkFile_Duplicate_File_Directory()
    {
        this.commandInvoker.addCommand(new CmdMkDir("mkdir", this.drive));

        executeCommand("mkfile gugus");
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertOutputIsEmpty(testOutput);
        executeCommand("mkdir gugus");
        assertEquals(numbersOfFilesBeforeTest + 1, drive.getCurrentDirectory().getNumberOfContainedFiles());
        TestHelper.assertContains("A subdirectory or file 'gugus' already exists", testOutput.toString());
    }
}
