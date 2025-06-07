package com.codemonkey.vcs.utils;

import static com.codemonkey.vcs.utils.CommonPath.HEAD;
import static com.codemonkey.vcs.utils.CommonPath.OBJECTS_DIR;

import com.codemonkey.vcs.model.Commit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class to read the last commit from the HEAD file and retrieve the tree entries. It reads the commit hash from the HEAD file, retrieves the commit object, and then
 * retrieves the tree object associated with that commit.
 */
public class LastCommitReader {

  /**
   * Reads the last commit from the HEAD file and retrieves the tree entries.
   *
   * @return A map containing the file names and their corresponding hashes from the last commit's tree.
   * @throws IOException If an I/O error occurs while reading files.
   */
  public static Map<String, String> read() throws IOException {
    Map<String, String> entries = new HashMap<>();
    Path path = Paths.get(HEAD);
    if (!Files.exists(path) || Files.readString(path).startsWith("ref:")) {
      return entries;
    }
    String commitHash = Files.readString(path).trim();
    Path commitHashPath = Paths.get(OBJECTS_DIR, commitHash);
    if (!Files.exists(commitHashPath)) {
      return entries;
    }
    String commitData = Files.readString(commitHashPath);
    Commit commit = new ObjectMapper().readValue(commitData, Commit.class);
    String treeHash = commit.getTreeHash();
    Path treeHashPath = Paths.get(OBJECTS_DIR, treeHash);
    if (!Files.exists(treeHashPath)) {
      return entries;
    }
    String treeData = Files.readString(treeHashPath);
    Map<String, String> tree = new ObjectMapper().readValue(treeData, new TypeReference<Map<String, String>>() {
    });
    entries.putAll(tree);
    return entries;
  }
}
