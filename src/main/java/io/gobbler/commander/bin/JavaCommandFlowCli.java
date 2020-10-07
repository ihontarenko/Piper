package io.gobbler.commander.bin;

import io.gobbler.commander.Context;
import io.gobbler.commander.convert.MapToObjectNodeConverter;
import io.gobbler.commander.io.dumper.SnakeYAMLDumper;
import io.gobbler.commander.io.loader.YamlLoader;
import io.gobbler.commander.io.parser.SnakeYAMLParser;
import io.gobbler.commander.lifecycle.WrapperMiddleware;
import io.gobbler.commander.parser.ObjectNode;
import io.gobbler.commander.parser.RootParser;
import io.gobbler.commander.proprties.BuildInfoProperties;
import io.gobbler.commander.proprties.CommandProperties;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

import static io.gobbler.commander.Constants.DEFAULT_CONFIGURATION_FILE_NAME;

public class JavaCommandFlowCli {

    public static void main(String... arguments) throws IOException {

        Context context = new Context();
        SnakeYAMLDumper dumper = new SnakeYAMLDumper();

        Map<String, Object> values = new SnakeYAMLParser().parse(
                new YamlLoader().load(DEFAULT_CONFIGURATION_FILE_NAME)
        );

        System.out.println(new MapToObjectNodeConverter().convert(values));

        System.exit(1);

        RootParser root = new RootParser();

        root.handle(new ObjectNode("root", values), null);


        context.setRawConfiguration(values);

        new WrapperMiddleware().next(context.getGlobal());

        BuildInfoProperties buildInfo = context.getGlobal().getBuildInfo();

        buildInfo.setOsName("Win");
        buildInfo.setTimestamp(String.valueOf(Instant.now().getEpochSecond()));
        buildInfo.setAuthor(String.valueOf(Instant.now().getEpochSecond()));

        System.out.println("================");
        System.out.println(
                dumper.dump(buildInfo)
        );
        System.out.println("================");


        context.getGlobal()
                .getCommands().forEach((s, stringMap) -> {

            System.out.println(
                    new CommandProperties(stringMap).getScripts()
            );
        });


        System.out.println(
                dumper.dump(
                        context.getGlobal()
                )
        );

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
//        ProcessBuilder builder = new ProcessBuilder();
//
////        if (isWindows) {
////            builder.command("cmd.exe", "/c", "dir");
////        } else {
////            builder.command("sh", "-c", "ls");
////        }
//
////        builder.command("ls -la");
////        C:\\\\Program Files\\\\Git\\\\git-bash.exe
//        builder.command("C:\\\\Program Files\\\\Git\\\\git-cmd.exe", "ls -la");
////        builder.command("pwd");
//
////        builder.directory(new File(System.getProperty("user.home")));
//
//        Process process = builder.start();
//
////        StreamGobbler streamGobbler =
////                new StreamGobbler(process.getInputStream(), s -> {
////                    String line = "LINE: " + s;
////                    System.out.println(line);
////                });
////
////        Executors.newSingleThreadExecutor().submit(streamGobbler);

//        System.exit(process.waitFor());
    }

}
