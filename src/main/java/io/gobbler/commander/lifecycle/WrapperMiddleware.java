package io.gobbler.commander.lifecycle;

import io.gobbler.commander.SingleMiddleware;
import io.gobbler.commander.proprties.GlobalProperties;
import io.gobbler.commons.Middleware;

import java.util.function.Consumer;

public class WrapperMiddleware extends SingleMiddleware<GlobalProperties> {

    private static final Consumer<String> LOGGER = System.out::println;

    private final Middleware<GlobalProperties> middleware;

    public WrapperMiddleware() {
        middleware = new StartMiddleware();
        middleware.next(new PreValidationMiddleware().next(new ReplacePlaceholderMiddleware()).next(new GenerateBuildInfoMiddleware())
                        .next(new GenerateGobblerCacheFileMiddleware())
                        .next(new PostValidationMiddleware())
                        .next(new FinishMiddleware()));
    }

    @Override
    public boolean proceed(GlobalProperties target) {
        boolean success;

        LOGGER.accept("start...");
        success = middleware.next(target);
        LOGGER.accept("finish...");

        return success;
    }

}
