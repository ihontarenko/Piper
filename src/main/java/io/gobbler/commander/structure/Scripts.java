package io.gobbler.commander.structure;

import java.util.ArrayList;
import java.util.List;

public class Scripts {

    protected List<String> scripts;

    public Scripts() {
        scripts = new ArrayList<>();
    }

    public void add(String script) {
        scripts.add(script);
    }

    public List<String> getScripts() {
        return scripts;
    }

}
