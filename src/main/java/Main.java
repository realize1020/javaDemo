import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        System.out.println("Hello World!");

        try {
            /*Files.list(Paths.get("D:\\"))
                    .filter(Files::isRegularFile)
                    .forEach(System.out::println);*/
            List<Path> collect = Files.list(Paths.get("D:\\"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());


            List<File> collect1 = Files.list(Paths.get("D:\\"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.collectingAndThen(Collectors.toList(),
                            path -> path.stream().map(Path::toFile).collect(Collectors.toList())));


            for(File file:collect1){
                //System.out.println(file.getCanonicalPath());
                System.out.println(file.getName());
            }



            for(Path path:collect){
                //System.out.println(path.toAbsolutePath());
                //System.err.println(path.toUri());
                //System.out.println(path.toString());
                //System.out.println(path.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
