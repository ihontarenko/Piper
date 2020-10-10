package io.gobbler.commander.command;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class CommandTask {

    private String              name;
    private String              description;
    private List<CommandScript> scripts;

    public CommandTask(String name, String description) {
        this.name = name;
        this.description = description;
        this.scripts = new ArrayList<>();
    }

    public CommandTask(String name) {
        this(name, format("COMMAND NAME: '%s'", name));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommandScript> getScripts() {
        return scripts;
    }

    public void setScripts(List<CommandScript> scripts) {
        this.scripts = scripts;
    }

    public void addScript(String script) {
        addScript(new CommandScript(script));
    }

    public void addScript(CommandScript script) {
        this.scripts.add(script);
    }

    @Override
    public String toString() {
        return format("CMD_TASK: [%s <%s>, %s]", name, description, scripts);
    }

}
