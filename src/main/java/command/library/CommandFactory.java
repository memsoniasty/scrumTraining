/*
 * DOSBox, Scrum.org, Professional Scrum Developer Training
 * Authors: Rainer Grau, Daniel Tobler, Zuehlke Technology Group
 * Copyright (c) 2013 All Right Reserved
 */ 

package command.library;

import command.framework.Command;
import interfaces.IDrive;

import java.util.ArrayList;

/**The factory is responsible to create an object of every command supported
 * and to add it to the list of known commands.
 * New commands must be added to the list of known commands here.
 */
public class CommandFactory {
	
	private ArrayList<Command> commands;

	public CommandFactory(IDrive drive) {
		this.commands = new ArrayList<Command>();
		
		this.commands.add(new CmdDir("dir", drive));
		this.commands.add(new CmdCd("cd", drive));
		this.commands.add(new CmdCd("chdir", drive));
		this.commands.add(new CmdMkDir("mkdir", drive));
		this.commands.add(new CmdMkDir("md", drive));
		this.commands.add(new CmdMkFile("mf", drive));
		this.commands.add(new CmdMkFile("mkfile", drive));
		// Add your commands here
		this.commands.add(new CmdExit("exit", drive));
		this.commands.add(new CmdVer("ver", drive));
        this.commands.add(new CmdVol("vol", drive));
		this.commands.add(new CmdType("type", drive));

	}
	
	public ArrayList<Command> getCommandList() {
		return commands;
	}

}
