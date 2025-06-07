package com.codemonkey.vcs.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Utility class for reading the current working directory. This class computes SHA-1 hashes for all files in the working directory, excluding hidden files and version control
 * system files (e.g., `.vcs`, `.git`).
 */
public class WorkingDirectoryReader {

  /**
   * Reads the current working directory and returns a map of file paths to their SHA-1 hashes.
   *
   * @return a map where keys are file paths and values are their corresponding SHA-1 hashes.
   * @throws IOException if an I/O error occurs while reading files.
   */
  public static Map<String, String> read() throws IOException {
    Map<String, String> entries = new HashMap<>();
    try (Stream<Path> paths = Files.walk(Paths.get("."))) {
      paths.filter(path -> !Files.isDirectory(path))
          .filter(path -> !path.getFileName().toString().endsWith(".vcs") || !path.getFileName().toString().endsWith(".git"))
          .forEach(path -> {
            try {
              var hash = HashUtil.sha1(Files.readString(path));
              if (!path.toString().startsWith("./.")) {
                var trimmedPath = path.toString().substring(2); // to remove "./" from the beginning of the path
                entries.put(trimmedPath, hash);
              }
            } catch (IOException ignored) {
            }
          });
    }
    return entries;
  }
}