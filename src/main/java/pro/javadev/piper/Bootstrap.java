package pro.javadev.piper;

import java.util.List;

public class Bootstrap {

    public static void boot(ApplicationContext context, String... arguments) {
        context.getArguments().addAll(List.of(arguments));
    }

}
