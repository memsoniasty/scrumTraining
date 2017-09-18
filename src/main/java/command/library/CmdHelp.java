package command.library;

import command.framework.Command;
import interfaces.IDrive;
import interfaces.IOutputter;

import java.util.Comparator;

/**
 * @author dominik.schmitz@traveltainment.de
 */
public class CmdHelp extends Command {

    protected CmdHelp(String commandName, IDrive drive) {
        super(commandName, drive);
    }

    @Override
    protected boolean checkNumberOfParameters(int numberOfParametersEntered) {
        return numberOfParametersEntered == 0;
    }

    @Override
    protected boolean checkParameterValues(IOutputter outputter) {
        return super.checkParameterValues(outputter);
    }

    @Override
    public void execute(IOutputter outputter) {
        new CommandFactory(getDrive()).getCommandList().stream()
                .filter(command -> command.getClass() != this.getClass())
                .sorted(Comparator.comparing(Command::toString))
                .forEach(filteredCommand -> outputter.printLine(filteredCommand.toString()));
    }
}
