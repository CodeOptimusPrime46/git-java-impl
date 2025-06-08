package com.codemonkey.vcs.core;

import static com.codemonkey.vcs.utils.CommonPath.INDEX_FILE;

import com.codemonkey.vcs.model.Blob;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;

public class AddCommand {

  /**
   * Adds a file to the staging area.
   *
   * @param filePath the path to the file to be added
   * @throws IOException if an I/O error occurs
   */
  public static void addFile(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    if (!path.toFile().exists()) {
      System.err.println("File does not exist: " + filePath);
      return;
    }
    Blob blob = new Blob(filePath);
    var hash = blob.save();

    // Update index
    String entry = filePath + " " + hash + "\n";
    List<String> indexLines = Files.exists(Paths.get(INDEX_FILE))
        ? Files.readAllLines(Paths.get(INDEX_FILE))
        : List.of();

    // Here we add filepath + " " to avoid removing incorrect entries
    List<String> newIndexLines = indexLines.stream().filter(line -> !line.startsWith(filePath + " ")).collect(Collectors.toList());
    newIndexLines.add(entry);
    Files.write(Paths.get(INDEX_FILE), String.join("\n", newIndexLines).getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
    System.out.println("Staged: " + filePath);
  }
}
