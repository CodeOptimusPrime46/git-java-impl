package com.codemonkey.vcs.core;

import static com.codemonkey.vcs.utils.CommonPath.HEAD;
import static com.codemonkey.vcs.utils.CommonPath.OBJECTS_DIR;

import com.codemonkey.vcs.model.Commit;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

public class LogCommand {
  /**
   * Displays the commit log of the current repository.
   * It reads the HEAD file to find the current commit hash and then traverses
   * through the commit history, printing each commit's details.
   *
   * @throws IOException if there is an error reading files
   */
  public static void showLog() throws IOException {
    if (!Files.exists(Paths.get(HEAD))) {
      System.out.println("Repository not initialized. Please run 'vcs init' first.");
      return;
    }
    String currentHash = Files.readString(Paths.get(HEAD));
    if (currentHash.startsWith("ref: ")) {
      System.out.println("No commits found. Please make a commit first.");
      return;
    }
    while (!currentHash.isEmpty() && !currentHash.equals("null")) {
      Path commitPath = Paths.get(OBJECTS_DIR, currentHash);
      if (!Files.exists(commitPath)) {
        System.out.println("No commit found with hash: " + currentHash);
        break;
      }
      var jsonString = Files.readString(commitPath);
      var commit = new ObjectMapper().readValue(jsonString, Commit.class);
      System.out.println("Commit: " + currentHash);
      System.out.println("Message: " + commit.getMessage());
      System.out.println("Timestamp: " + Instant.ofEpochMilli(commit.getTimestamp()));
      System.out.println("Parent: " + commit.getParentHash());
      System.out.println("--------------------");

      // Read the next commit hash from the current commit
      currentHash = commit.getParentHash();
    }
  }
}
