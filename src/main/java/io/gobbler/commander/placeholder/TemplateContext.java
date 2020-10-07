package io.gobbler.commander.placeholder;

public interface TemplateContext {

    TemplateContext DEFAULT_TEMPLATE_CONTEXT = new TemplateContext() {
        @Override
        public String getPrefix() { return "${"; }

        @Override
        public String getSuffix() { return "}"; }
    };

    String getPrefix();

    String getSuffix();

}
