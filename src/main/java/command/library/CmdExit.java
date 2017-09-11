/*
 * DOSBox, Scrum.org, Professional Scrum Developer Training
 * Authors: Rainer Grau, Daniel Tobler, Zuehlke Technology Group
 * Copyright (c) 2013 All Right Reserved
 */

package command.library;

import interfaces.IDrive;
import interfaces.IOutputter;
import command.framework.Command;
import filesystem.Directory;
import filesystem.FileSystemItem;

/**Command to change current directory.
 * Example for a command with optional parameters.
 */
class CmdExit extends Command {

    protected CmdExit(String name, IDrive drive) {
        super(name, drive);
    }

    @Override
    public boolean checkNumberOfParameters(int numberOfParametersEntered) {
        return (numberOfParametersEntered == 0 || numberOfParametersEntered == 1);
    }

    @Override
    public boolean checkParameterValues(IOutputter outputter) {
        return true;
    }

    @Override
    public void execute(IOutputter outputter) {
    }
}
