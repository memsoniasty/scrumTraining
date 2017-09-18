/*
 * DOSBox, Scrum.org, Professional Scrum Developer Training
 * Authors: Rainer Grau, Daniel Tobler, Zuehlke Technology Group
 * Copyright (c) 2013 All Right Reserved
 */

package command.framework;

import interfaces.IDrive;
import interfaces.IOutputter;

import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.io.IOException;
import java.util.logging.SimpleFormatter;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * Implements the abstract base class for commands.
 */
public abstract class Command {
	final static Logger logger = Logger.getLogger("Command");

    private static final String INCORRECT_SYNTAX = "The syntax of the command is incorrect.";
    private static final String DEFAULT_ERROR_MESSAGE_WRONG_PARAMETER = "Wrong parameter entered";
    private static final String FILE_ALREADY_EXISTS = "A subdirectory or file '%s' already exists";
    private String commandName;
    private ArrayList<String> parameters = new ArrayList<String>();
    private IDrive drive;

    protected IDrive getDrive() {
        return this.drive;
    }

    protected Command(String commandName, IDrive drive) {
        this.commandName = commandName.toLowerCase();
        this.drive = drive;
    }

    /**
     * Checks the passed parameters. This consists of two steps which are overrideable by concrete commands.
     * 1.) Check the number of parameters.
     * 2.) Check the values of all passed parameters
     * Template Method Pattern: Template Method.
     *
     * @param outputter The outputter must be used to printout any error description.
     * @return - true  if number and values of the parameters are correct. Execute() may use the parameters afterwards unchecked.
     * - false if the number is below or above excepted range or if any value is incorrect. An explaining error
     * message must be given by the concrete command.
     * @throws Exception
     */
    public final boolean checkParameters(IOutputter outputter) throws Exception {
        if (!checkNumberOfParameters(this.parameters.size())) {
            outputter.printLine(INCORRECT_SYNTAX);
            return false;
        }

        if (!checkParameterValues(outputter)) {
            if (!outputter.hasCharactersPrinted())
                outputter.printLine(DEFAULT_ERROR_MESSAGE_WRONG_PARAMETER);
            return false;
        }

        return true;
    }

    /**
     * <b>Can be overwritten</b> by the concrete commands if something must be checked.
     * Checks if the number of parameters is in range.
     * Do not output anything, an explaining error message is output by the abstract command.
     * Template Method Pattern: Hook.
     *
     * @param name="numberOfParametersEntered">Number of parameters passed by the caller.
     * @return - true if number of parameters is within expected range
     * - false otherwise
     */
    protected boolean checkNumberOfParameters(int numberOfParametersEntered) {
        return true;
    }

    /**
     * <b>Can be overwritten</b> by the concrete commands if at least the value of one parameter must be checked.
     * Checks all values of all passed parameters.
     * An explaining error message must be output by the concrete command.<br>
     *
     * @param outputter The IOutputter must be used to output error messages.
     * @return - true if all values of all parameters passed are correct.
     * - false if at least one value of one parameter in incorrect.
     */
    protected boolean checkParameterValues(IOutputter outputter) {
        if (outputter == null) throw new IllegalArgumentException("outputter is null");
        return true;
    }

    /**
     * <b>Must be overwritten</b> by the concrete commands to implement the execution of the command.
     *
     * @param outputter Must be used to output any text.
     */
    public abstract void execute(IOutputter outputter);

	/**Prints the command execution details to the common logfile
	 */
	public void trace(){
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("DosBox.log");
		} catch (IOException  e){
			e.printStackTrace();
		}
		fileHandler.setFormatter(new SimpleFormatter());
		logger.addHandler(fileHandler);
		logger.setUseParentHandlers(false);
		logger.setLevel(Level.FINEST);
		logger.info(
				"Executed command: " + this.commandName + " with params: " + this.parameters.toString());
	}

    /**Returns true if the passed name and the command name fit.
     * Used to obtain the concrete command from the list of commands.
     * @param name="commandNameToCompare">name with which the command name shall be compared.
     * @return
     * - true if names fit
     * - false otherwise
     */
    public final boolean compareCmdName(String cmdName) {
        return (this.commandName.compareTo(cmdName) == 0);
    }

    /**
     * Sets the list of parameters. This operation shall be called by the invoker only.
     */
    public final void setParameters(ArrayList<String> newParameters) {
        this.parameters.clear();
        for (String parameter : newParameters) {
            this.parameters.add(parameter);
        }
    }

    protected int getParameterCount() {
        return this.parameters.size();
    }

    protected String getParameterAt(int parameterIndex) {
        if (parameterIndex < 0 || parameterIndex >= this.parameters.size())
            throw new IllegalArgumentException("Index out of range");
        return this.parameters.get(parameterIndex);
    }

    protected boolean fileOrDirectoryExists(String parameter, IOutputter outputter) {
        if (getDrive().getCurrentDirectory().getContent().stream()
                .filter(fileSystemItem -> fileSystemItem.getName().compareToIgnoreCase(parameter) == 0)
                .count() == 0) {
            return false;
        } else {
            outputter.printLine(String.format(FILE_ALREADY_EXISTS, parameter));
            return true;
        }
    }

    @Override
    public final String toString() {
        return this.commandName;
    }
}
