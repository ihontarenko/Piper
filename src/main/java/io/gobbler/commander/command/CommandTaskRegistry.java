package io.gobbler.commander.command;

import java.util.HashMap;

public class CommandTaskRegistry extends HashMap<String, CommandTask> {

    public void register(CommandTask task) {
        put(task.getName(), task);
    }

    public void register(String name) {
        register(new CommandTask(name));
    }

    public void unregister(String name) {
        remove(name);
    }

    public void unregister(CommandTask task) {
        unregister(task.getName());
    }

    public boolean has(String name) {
        return containsKey(name);
    }

    public boolean has(CommandTask task) {
        return has(task.getName());
    }

}
