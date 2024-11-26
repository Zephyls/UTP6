package zad1;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.charset.Charset;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        Path startPath = Paths.get(dirName);
        Path resultFilePath = Paths.get(resultFileName);


        Charset outputCharset = Charset.forName("UTF-8");

        try (BufferedWriter writer = Files.newBufferedWriter(resultFilePath, outputCharset)) {
            Files.walkFileTree(startPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    if (file.toString().endsWith(".txt")) {
                        Charset inputCharset = Charset.forName("Cp1250");
                        try (BufferedReader reader = Files.newBufferedReader(file, inputCharset)) {
                            String line;
                            while ((line = reader.readLine()) != null) {
                                writer.write(line);
                                writer.newLine();
                            }
                        }
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Error processing directory: " + e.getMessage());
        }
    }
}
