package command.library;

import command.framework.Command;
import interfaces.IDrive;
import interfaces.IOutputter;


class CmdVol extends Command {
    public CmdVol(String vol, IDrive drive) {
        super(vol, drive);
    }

    @Override
    public void execute(IOutputter outputter) {
        outputter.printLine("Volume in drive C is: " + getDrive().getLabel());
        outputter.newLine();
        outputter.printLine("Volume serial number is: IE16-IE3");
    }
}
