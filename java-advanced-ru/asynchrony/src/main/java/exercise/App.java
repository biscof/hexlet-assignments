package exercise;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.concurrent.atomic.AtomicLong;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(
            String pathToFile1,
            String pathToFile2,
            String pathToResultFile
    ) {

        CompletableFuture<String> future1 = getSupplyAsync(pathToFile1);
        CompletableFuture<String> future2 = getSupplyAsync(pathToFile2);

        return future1
                .thenCombine(future2, (fileContents1, fileContents2) -> {
                    String newContent = fileContents1 + fileContents2;
                    Path newFilePath = Paths.get(pathToResultFile).toAbsolutePath();

                    try {
                        if (!Files.exists(newFilePath)) {
                            Files.createFile(newFilePath);
                        }
                        Files.writeString(newFilePath, newContent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return newContent;
                })
                .exceptionally(ex -> {
                    System.out.println("An exception occurred: " + ex.getMessage());
                    return null;
                });
    }

    public static CompletableFuture<Long> getDirectorySize(String directory) throws IOException {

        Path dirPath = Paths.get(directory);
        return CompletableFuture.supplyAsync(() -> {
            try {
                AtomicLong size = new AtomicLong(0L);
                Files.newDirectoryStream(dirPath)
                        .iterator()
                        .forEachRemaining(itemPath -> {
                            if (Files.isRegularFile(itemPath)) {
                                try {
                                    size.addAndGet(Files.size(itemPath));
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                return size.get();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static CompletableFuture<String> getSupplyAsync(String pathToFile) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path path1 = Paths.get(pathToFile).toAbsolutePath();
                return Files.readString(path1);
            } catch (InvalidPathException | IOException e) {
                System.out.println("File doesn't exist or invalid file path provided.");
                throw new RuntimeException(e);
            }
        });
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        CompletableFuture<String> united = unionFiles(
                "src/main/resources/file1.txt",
                "src/main/resources/file2.txt",
                "src/main/resources/file3.txt");

        CompletableFuture<Long> size = getDirectorySize("src/main/resources");

        System.out.println(united.get());
        System.out.println(size.get());
        // END
    }
}

