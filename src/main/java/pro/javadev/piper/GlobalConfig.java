package pro.javadev.piper;

import pro.javadev.piper.common.KeyValues;

public interface GlobalConfig {

    String getSubScriptPrefix();

    String getOS();

    class Configuration extends KeyValues<String> implements GlobalConfig  {

        public static final String SUB_SCRIPT_PREFIX = "sub_script_prefix";
        public static final String OS = "os";

        @Override
        public String getSubScriptPrefix() {
            return get(SUB_SCRIPT_PREFIX);
        }

        @Override
        public String getOS() {
            return get(OS);
        }

    }

}


