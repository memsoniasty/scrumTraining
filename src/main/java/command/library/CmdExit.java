/*
 * DOSBox, Scrum.org, Professional Scrum Developer Training
 * Authors: Rainer Grau, Daniel Tobler, Zuehlke Technology Group
 * Copyright (c) 2013 All Right Reserved
 */

package command.library;

import command.framework.Command;
import interfaces.IDrive;
import interfaces.IOutputter;

/**
 * Command to change current directory.
 * Example for a command with optional parameters.
 */
class CmdExit extends Command {

	protected CmdExit(String name, IDrive drive) {
		super(name, drive);
	}

	@Override
	public boolean checkNumberOfParameters(int numberOfParametersEntered) {
		return numberOfParametersEntered == 0;
	}

	@Override
	public boolean checkParameterValues(IOutputter outputter) {
		return true;
	}

	@Override
	public void execute(IOutputter outputter) {
	}
}
