package command.library;

import command.framework.Command;
import interfaces.IDrive;
import interfaces.IOutputter;

/**
 * @author dominik.schmitz@traveltainment.de
 */
public class CmdVer extends Command {


	protected CmdVer(String commandName, IDrive drive) {
		super(commandName, drive);
	}

	@Override
	protected boolean checkNumberOfParameters(int numberOfParametersEntered) {
		return numberOfParametersEntered == 0 || numberOfParametersEntered == 1;
	}

	@Override
	protected boolean checkParameterValues(IOutputter outputter) {
		if (this.getParameterCount() == 1) {
			return this.getParameterAt(0).equals("/w");
		}
		return true;
	}

	@Override
	public void execute(IOutputter outputter) {
		if (this.getParameterCount() == 0) {
			outputter.printLine("Microsoft Windows [Version 6.1.7601]");
		} else if (this.getParameterCount() == 1) {
			outputter.printLine("Lars Frauenrath");
			outputter.printLine("Willi Man");
			outputter.printLine("Przemyslaw Galera");
			outputter.printLine("Dominik Schmitz");
		}
	}
}
