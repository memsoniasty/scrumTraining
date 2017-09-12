package command.library;

import command.framework.Command;
import filesystem.File;
import filesystem.FileSystemItem;
import interfaces.IDrive;
import interfaces.IOutputter;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author dominik.schmitz@traveltainment.de
 */
public class CmdType extends Command {

	protected CmdType(String commandName, IDrive drive) {
		super(commandName, drive);
	}

	@Override
	protected boolean checkNumberOfParameters(int numberOfParametersEntered) {
		return numberOfParametersEntered == 1;
	}

	@Override
	protected boolean checkParameterValues(IOutputter outputter) {
		String fileName = this.getParameterAt(0);

		ArrayList<FileSystemItem> contentOfDir = this.getDrive().getCurrentDirectory().getContent();
		Optional<FileSystemItem> file = contentOfDir.stream().filter(f -> f.getName().equals(fileName)).findFirst();
		if (file.isPresent()) {
			FileSystemItem fileSystemItem = file.get();
			if (fileSystemItem.isDirectory()) {
				outputter.printLine("Access denied!");
				return false;
			}
		} else {
			outputter.printLine("File does not exist in current directory");
			return false;
		}
		return true;
	}

	@Override
	public void execute(IOutputter outputter) {
		outputter.printLine(
				((File) this.getDrive().getCurrentDirectory().getContent().stream()
						.filter(file -> !file.isDirectory() && file.getName().equals(this.getParameterAt(0)))
						.findFirst().get()).getFileContent());
	}
}
