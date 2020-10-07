package io.gobbler.commander.command;

import com.github.rvesse.airline.HelpOption;
import com.github.rvesse.airline.annotations.Command;
import com.github.rvesse.airline.annotations.Option;

import javax.inject.Inject;

@Command(name = "setup-log", description = "Setup our log")
public class RunCommand implements Runnable {

    @Inject
    private HelpOption<RunCommand> help;

    @Option(name = { "-v", "--verbose" }, description = "Set log verbosity on/off")
    private boolean verbose = false;

    @Override
    public void run() {
        System.out.println("RunCommand: called...");
    }

}
