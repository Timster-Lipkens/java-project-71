package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.util.concurrent.Callable;

@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 1.0",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {

    @Parameters(index = "0", description = "path to first file")
    private String filepath1; //обязательный параметр, функция по-умолчанию не работает

    @Parameters(index = "1", description = "path to second file")
    private String filepath2;

    @Option(names = {"-f", "--format"}, description = "output format [default: stylish]")
    private String format = "stylish";

    //Вводные для запуска и тестирования: //более короткий make run-dist не понимает параметров..
    //./build/install/app/bin/app src/main/java/hexlet/code/files/file1.json src/main/java/hexlet/code/files/file2.json
    //./build/install/app/bin/app src/main/java/hexlet/code/files/file1.yaml src/main/java/hexlet/code/files/file2.yaml
//./build/install/app/bin/app src/main/java/hexlet/code/files/file11.json src/main/java/hexlet/code/files/file22.json
//./build/install/app/bin/app src/main/java/hexlet/code/files/file11.yaml src/main/java/hexlet/code/files/file22.yaml
    //./build/install/app/bin/app -f pain
    @Override
    public Integer call() throws Exception { //./build/install/app/bin/app filepath1.json filepath2.json
        try {
            String diff = Differ.generate(filepath1, filepath2, format).toString(); //по-умолчанию вроде итак работает
            System.out.println(diff);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return 1; //ошибка.
        }
        return 0; //без ошибок.
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args); //./build/install/app/bin/app -h
        System.exit(exitCode);
    }

    //public static Map getData(String content) throws Exception { //упоминался метод, но непонятно зачем он
        //return parse(content);
    //}

}
