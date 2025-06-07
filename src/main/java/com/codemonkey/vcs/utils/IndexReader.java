package com.codemonkey.vcs.utils;

import static com.codemonkey.vcs.utils.CommonPath.INDEX_FILE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading the index file in the version control system. The index file maps file names to their corresponding SHA-1 hashes. This class provides a method to parse
 * the index file and return its contents as a map.
 */
public class IndexReader {

  /**
   * Reads the index file and returns a map of file names to their corresponding SHA-1 hashes.
   *
   * @return a map where keys are file names and values are their corresponding SHA-1 hashes.
   * @throws IOException if an I/O error occurs while reading the index file.
   */
  public static Map<String, String> read() throws IOException {
    Map<String, String> entries = new HashMap<>();
    Path path = Paths.get(INDEX_FILE);
    if (!Files.exists(path)) {
      return entries;
    }
    List<String> lines = Files.readAllLines(path);
    for (String line : lines) {
      String[] parts = line.split(" ");

      if (parts.length == 2) {
        entries.put(parts[0], parts[1]);
      }
    }
    return entries;
  }
}
