package io.gobbler.commander.structure;

import static io.gobbler.commander.structure.Command.Strategy.valueOf;

public class Command {

    protected String description;
    protected String name;
    protected Strategy strategy = Strategy.CONSISTENT;
    protected String target;
    protected Scripts scripts = new Scripts();

    public Command(String name) {
        this.name = name;
    }

    public enum Strategy {
        CONSISTENT, PARALLEL
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(String strategy) {
        setStrategy(valueOf(strategy.toUpperCase()));
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Scripts getScripts() {
        return scripts;
    }

    public void setScripts(Scripts scripts) {
        this.scripts = scripts;
    }

}
