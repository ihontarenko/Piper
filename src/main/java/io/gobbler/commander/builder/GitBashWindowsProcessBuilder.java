package io.gobbler.commander.builder;

public class GitBashWindowsProcessBuilder extends AbstractProcessBuilder<GitBashWindowsProcessBuilder> {

    public static final String DEFAULT_GIT_BASH_CMD_LOCATION = "C:\\\\Program Files\\\\Git\\\\git-cmd.exe";

    public GitBashWindowsProcessBuilder() {
        predefined(DEFAULT_GIT_BASH_CMD_LOCATION);
    }



}
