/*
 * DOSBox, Scrum.org, Professional Scrum Developer Training
 * Authors: Rainer Grau, Daniel Tobler, Zuehlke Technology Group
 * Copyright (c) 2013 All Right Reserved
 */

package command.library;

import interfaces.IDrive;
import interfaces.IOutputter;
import command.framework.Command;
import filesystem.File;

class CmdMkFile extends Command {



    public CmdMkFile(String cmdName, IDrive drive) {
        super(cmdName, drive);
    }

    @Override
    protected boolean checkParameterValues(IOutputter outputter) {
        for (int i = 0; i < getParameterCount(); i++) {
            if (fileOrDirectoryExists(getParameterAt(i), outputter)) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected boolean checkNumberOfParameters(int numberOfParametersEntered) {
        return numberOfParametersEntered > 0 && numberOfParametersEntered <= 2;
    }

    @Override
    public void execute(IOutputter outputter) {
        String fileName;
        String fileContent;
        File newFile;

        switch (this.getParameterCount()) {
            case 1:
                fileName = this.getParameterAt(0);
                newFile = new File(fileName, "");
                break;
            case 2:
                fileName = this.getParameterAt(0);
                fileContent = this.getParameterAt(1);
                newFile = new File(fileName, fileContent);
                break;
            default:
                return;
        }

        this.getDrive().getCurrentDirectory().add(newFile);
    }
}
