package pro.javadev.piper.bin;

import org.apache.commons.cli.*;
import org.apache.commons.text.StringSubstitutor;
import pro.javadev.piper.Constants;
import pro.javadev.piper.command.CommandName;
import pro.javadev.piper.command.CommandLineOptionPresets;
import pro.javadev.piper.common.Holder;
import pro.javadev.piper.common.KeyValues;
import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.configurator.*;
import pro.javadev.piper.converter.CommandLineSplitterConverter;
import pro.javadev.piper.converter.FlattenMapConverter;
import pro.javadev.piper.converter.KeyAbbreviationConverter;
import pro.javadev.piper.converter.WrapIntoHolderConverter;
import pro.javadev.piper.converter.text.AnsiTextConverter;
import pro.javadev.piper.io.dumper.SnakeYAMLDumper;
import pro.javadev.piper.io.loader.YamlLoader;
import pro.javadev.piper.io.parser.SnakeYAMLParser;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.BiConsumer;

import static java.lang.String.format;

public class Sandbox {


    public static void main(String... arguments) throws ParseException, FileNotFoundException {

        BiConsumer<String, Object> printer   = (k, v) -> System.out.println(format("%s=%s", k, v));
        AnsiTextConverter          converter = new AnsiTextConverter();

        System.out.println();
        System.out.println();

        for (AnsiColors value : AnsiColors.values()) {
            System.out.println(converter.convert(format("${ansi:%s}%s", value.getName(), value.getName())));
        }

        System.out.println();
        System.out.println();


        Options options = new Options();

        options.addOption(new Option("f", "config-file", true, "PASS TARGET CONFIG FILE '.piper.yaml'"));

        HelpFormatter formatter = new HelpFormatter();

        formatter.setArgName("ARGUMENT");
        formatter.setSyntaxPrefix(converter.convert("${ansi:BLUE_BOLD_BRIGHT}COMMAND USAGE:${ansi:RESET} "));
        formatter.setWidth(128);
        formatter.setLongOptPrefix(" --");
        formatter.setLongOptSeparator("=");
        formatter.setOptPrefix(" -");

        String separator = converter.convert(format("${ansi:RED_BOLD_BRIGHT}%s", "-".repeat(32)));
//
        for (CommandName value : CommandName.values()) {
            formatter.printHelp(converter.convert(format("${ansi:YELLOW_BOLD_BRIGHT}%s", value.getName())), separator, CommandLineOptionPresets.toOptions(value), separator);
        }


        CommandLineParser parser = new DefaultParser();
        CommandLine       line   = parser.parse(options, new String[]{"-f", "./.gobbler.yaml"});

        System.out.println(
                line.getOptionValue("config-file")
        );

        System.out.println(
                line.getOptionValue('f')
        );

//        System.exit(1);

        SnakeYAMLDumper dumper = new SnakeYAMLDumper();

        Map<String, Object> values = new SnakeYAMLParser().parse(
                new YamlLoader().load(Constants.DEFAULT_CONFIGURATION_FILE_NAME)
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

        System.out.println("rootData");
        new FlattenMapConverter("env", ".").convert(rootData).forEach(printer);

        new FlattenMapConverter("env", ".").convert(System.getenv());

        String template = "test vars(${CMD.start-main.scripts[0]}): JAVA_HOME: ${env:JAVA_HOME} | gobbler yaml version = ${version} | get script [[ ${PPTY.BASH_CMD_WINDOWS} ]] finish";

        String result = StringSubstitutor.replace(template, new FlattenMapConverter(null, ".").convert(
                new KeyAbbreviationConverter(new HashMap<>() {{
                    put("programs", "BIN");
                    put("properties", "PPTY");
                    put("commands", "CMD");
                }}).convert(values)
        ));

        System.out.println(
                "FLATTEN_MAP"
        );

        new FlattenMapConverter("@", ".")
                .convert(values).forEach(printer);

        new FlattenMapConverter("env:", ".")
                .convert(System.getenv()).forEach(printer);

        result = StringSubstitutor.replace(result, new FlattenMapConverter("env:", ".").convert(System.getenv()));

        FlattenMapConverter flattenMapConverter = new FlattenMapConverter(null, ".");
        Map<String, Object> flattenMap = flattenMapConverter.convert(
                values
        );

        System.out.println("flattenMap");
        flattenMap.forEach(printer);

        BaseConfigurator crawler = new RootConfigurator();

//        crawler.add(new AnyTypeConfigurator());


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
        System.out.println("StringSubstitutor: " + result);


//        SnakeYAMLDumper dumper = new SnakeYAMLDumper();
//
//        Map<String, Object> values = new SnakeYAMLParser().parse(
//                new YamlLoader().load(DEFAULT_CONFIGURATION_FILE_NAME)
//        );
//
        Holder node = new WrapIntoHolderConverter().convert(values);

        System.out.println(
                "node -> " + node
        );

        RootConfigurator root = new RootConfigurator();

        System.out.println(
                "children all: " + root.children()
        );

        System.out.println(
                "children typed: " + root.find(KeyValuesConfigurator.class)
        );

        System.out.println(
                "exist typed: " + root.exist(KeyValuesConfigurator.class)
        );

        root.handle(node, Holder.of(new KeyValues<>()));

        System.out.println("new EnvParser() >>>");

        KeyValues<String> envs = new KeyValues<>();

        new KeyValuesConfigurator().handle(
                new WrapIntoHolderConverter().convert((Map<String, Object>) values.get("properties")), Holder.of(envs));

        System.out.println("new EnvParser() <<<");


        System.out.println(
                "CommandLineSplitterConverter"
        );

        Arrays.asList(new CommandLineSplitterConverter()
                .convert("docker-compose up -d dcp-couchbase-server")).forEach(System.out::println);

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

        System.out.println(
                ">>>>>>>>>>>>>>>>>>>"
        );

        System.out.println(
                values
        );

        values.forEach((s, o) -> {

            if (o instanceof Map) {
                Map<?, ?> data = (Map) o;
                data.forEach((o1, o2) -> {
                    System.out.println("key: " + o1);
                    System.out.println("value: " + o2);

                    if (((String) o1).equalsIgnoreCase("commands")) {
                        ((Map) o2).forEach((o3, o21) -> {
                            System.out.println("cmd: " + o3);
                        });
                    }
                });
            }

        });


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
////
//        ProcessBuilder builder = new ProcessBuilder();
//
////        builder.command("C:\\\\Program Files\\\\Git\\\\git-cmd.exe", "ls -la");
//        builder.command("C:/Program Files/Git/git-cmd.exe", "-c", "ls");
//        builder.command(parseCommand("C:\\\\Program Files\\\\Git\\\\git-cmd.exe ls -la | grep 'sh' && echo %PATH%"));

//        builder.command("C:\\\\Program Files\\\\Git\\\\git-cmd.exe ls -la | grep sh && echo test wqe");
//
//        builder.inheritIO();

//        for (String s : parseCommand("C:\\\\Program Files\\\\Git\\\\git-cmd.exe ls -la | grep 'sh' && echo $PATH")) {
//            System.out.println(s);
//        }


//        try {
//            ProcessBuilder builder = new ProcessBuilder();
//
////        builder.command("C:\\\\Program Files\\\\Git\\\\git-cmd.exe", "ls -la");
////            builder.command("C:\\Program Files\\Git\\git-bash.exe", "-c", "cd ~ && ls && sleep 10000");
//            builder.command("C:\\Program Files\\Git\\git-bash.exe", "-c",
//                    "cd ~ && ls && sleep 10000");
//            builder.redirectErrorStream(true);
////            builder.inheritIO();
//
//            System.out.println("before");
//            Process process = builder.start();
//
//            boolean exitVal = process.waitFor(2L, TimeUnit.SECONDS);
//
//            if (exitVal) {
//                System.out.println(" --- Command run successfully");
//            } else {
//                System.out.println(" --- Command run unsuccessfully");
//            }
//
//            new BufferedReader(new InputStreamReader(process.getInputStream()))
//                    .lines().forEach(System.out::println);
//
//            System.out.println("after");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


//        ProcessBuilder builder = new ProcessBuilder();
//        builder.command("C:\\Program Files\\Git\\git-cmd.exe", "ls -la");
//        builder.redirectErrorStream(false);
//        Process process = builder.start();
//
//        new BufferedReader(new InputStreamReader(process.getInputStream()))
//                .lines().forEach(System.out::println);

        System.out.println("arguments >>>");
        for (String argument : arguments) {
            System.out.println("argument: " + argument);
        }
    }

}
