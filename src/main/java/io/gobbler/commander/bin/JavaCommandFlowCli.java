package io.gobbler.commander.bin;

import io.gobbler.commander.converter.KeyAbbreviationConverter;
import io.gobbler.commander.converter.MapToObjectNodeConverter;
import io.gobbler.commander.converter.NestedMapToFlatMapConverter;
import io.gobbler.commander.executor.StreamLinesExecutor;
import io.gobbler.commander.io.dumper.SnakeYAMLDumper;
import io.gobbler.commander.io.loader.YamlLoader;
import io.gobbler.commander.io.parser.SnakeYAMLParser;
import io.gobbler.commander.parser.*;
import org.apache.commons.text.StringSubstitutor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import static io.gobbler.commander.Constants.DEFAULT_CONFIGURATION_FILE_NAME;

public class JavaCommandFlowCli {

    public static void main(String... arguments) throws IOException, InterruptedException {

        SnakeYAMLDumper dumper = new SnakeYAMLDumper();

        Map<String, Object> values = new SnakeYAMLParser().parse(
                new YamlLoader().load(DEFAULT_CONFIGURATION_FILE_NAME)
        );

        List<Object> strings = new ArrayList<>();

        strings.add("123qwe");
        strings.add("qwe123");
        strings.add("123123");
        strings.add(new HashMap<>() {{
            put("v1", "123");
            put("keys", "aaaaa");
        }});

        Map<String, Object> rootData = new HashMap<>() {{
            put("str", getClass().getName());
            put("sub", new HashMap<>() {{
                put("v1", "[Version: 4.0]");
                put("keys", strings);
            }});
        }};

        new NestedMapToFlatMapConverter("env", ".").convert(System.getenv());

        String template = "test vars: JAVA_HOME: ${env:JAVA_HOME} | gobbler yaml version = ${version} | get script [[ ${PPTY/BASH_CMD_WINDOWS} ]] finish";

        String result = StringSubstitutor.replace(template, new NestedMapToFlatMapConverter(null, "/").convert(
                new KeyAbbreviationConverter(new HashMap<>() {{
                    put("programs", "BIN");
                    put("properties", "PPTY");
                    put("commands", "CMD");
                }}).convert(values)
        ));

        result = StringSubstitutor.replace(result, new NestedMapToFlatMapConverter("env:", ".").convert(System.getenv()));

//        new NestedMapToFlatMapConverter(null, ".").convert(
//                new KeyAbbreviationConverter(new HashMap<>(){{
//                    put("programs", "BIN");
//                    put("properties", "PPTY");
//                    put("commands", "CMD");
//                }}).convert(values)
//        ).forEach((s, o) -> {
//            System.out.println(s);
//        });
//
        System.out.println(result);


//        SnakeYAMLDumper dumper = new SnakeYAMLDumper();
//
//        Map<String, Object> values = new SnakeYAMLParser().parse(
//                new YamlLoader().load(DEFAULT_CONFIGURATION_FILE_NAME)
//        );
//
        ObjectNode node = new MapToObjectNodeConverter().convert(values);
        RootParser root = new RootParser();

        root.addChild(new VersionParser());
        root.addChild(new EnvParser());
        root.addChild(new BinsParser());
        root.addChild(new CommandListParser()
                .addChild(new CommandScriptsParser())
                .addChild(new CommandDescriptionParser())
                .addChild(new CommandEnvParser()));
        root.addChild(new BuildInfoParser());

        root.handle(node, new ParserContext());

//        context.setRawConfiguration(values);
//
//        context.getGlobal()
//                .getCommands().forEach((s, stringMap) -> {
//
//            System.out.println(
//                    new CommandProperties(stringMap).getScripts()
//            );
//        });
//
//
//        System.out.println(
//                dumper.dump(
//                        context.getGlobal()
//                )
//        );

//        System.out.println(
//                context.getRawConfiguration().getCommands()
//        );
//
//        System.out.println(
//                context.getRawConfiguration().getVersion()
//        );
//
//        new BeansPathAccessor().getProperty(values, "gobbler.commands", Map.class).forEach((o, o2) -> {
//            System.out.println("CMD: " + o);
//            System.out.println(o2);
//        });
//
//        System.out.println(
//                yaml.dump(context)
//        );
//
//        System.out.println(
//                (String) new PropertyAccessor().read(context, "env.JAVA_HOME")
//        );
//        System.out.println(
//                (String) new PropertyAccessor().read(context, "env.M2_HOME")
//        );


//        values.forEach((s, o) -> {
//
//            if (s.equalsIgnoreCase("version")) {
//                System.out.println(new Version((String) o));
//            }
//
//            if (s.equalsIgnoreCase("gobbler")) {
//                if (o instanceof Map) {
//                    Map<?, ?> data = (Map)o;
//
//                    data.forEach((o1, o2) -> {
//                        System.out.println("key: " + o1);
//                        System.out.println("value: " + o2);
//
//                        if (((String)o1).equalsIgnoreCase("commands")) {
//                            ((Map)o2).forEach((o3, o21) -> {
//                                System.out.println("cmd: " + o3);
//                            });
//                        }
//                    });
//
//
//                }
//            }
//        });


//        Map<String, Object> data = new HashMap<>();
//        Map<String, Object> data2 = new HashMap<>();
//
//        data2.put("val1", "Value One - 1");
//        data2.put("val-a", "Value Alpha");
//
//        data.put("env", System.getenv());
//        data.put("test", data2);
//
//        System.out.println(data);
//
//        System.out.println(
//                "Ola! ${test.val-a} ${test.val1} =)"
//        );
//
//        TemplateParserContext template = new TemplateParserContext("${", "}");
//        ExpressionParser root = new SpelExpressionParser();
//        Expression expression = root.parseExpression("Ola! ${test.vala} =)", template);
//
////        StandardEvaluationContext context = new StandardEvaluationContext(data);
//        SimpleEvaluationContext context = new SimpleEvaluationContext.Builder(new BeansPropertyAccessor())
//                .withRootObject(data)
//                .build();
//
//        System.out.println("result: " + expression.getValue(context, String.class));
//
//        System.exit(1);
//
//        boolean isWindows = System.getProperty("os.name")
//                .toLowerCase().startsWith("windows");
//
        ProcessBuilder builder = new ProcessBuilder();

//        builder.command("C:\\\\Program Files\\\\Git\\\\git-cmd.exe", "ls -la");
        builder.command("C:\\\\Program Files\\\\Git\\\\git-cmd.exe", "ls -la");

        System.out.println("before");
        Process process = builder.start();
        System.out.println("after");

        Executors.newFixedThreadPool(5).submit(
                new StreamLinesExecutor(process.getInputStream(), System.out::println)
        );

        System.exit(process.waitFor());
    }

}
