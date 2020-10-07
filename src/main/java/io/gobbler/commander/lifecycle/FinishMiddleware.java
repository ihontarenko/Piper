package io.gobbler.commander.lifecycle;

import io.gobbler.commander.SingleMiddleware;
import io.gobbler.commander.proprties.GlobalProperties;

public class FinishMiddleware extends SingleMiddleware<GlobalProperties> {

    @Override
    public boolean proceed(GlobalProperties target) {
        System.out.println("FinishMiddleware");
        return true;
    }
    
}
