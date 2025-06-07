package com.codemonkey.vcs.model;

import static com.codemonkey.vcs.utils.CommonPath.OBJECTS_DIR;

import com.codemonkey.vcs.utils.HashUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import lombok.Data;

/**
 * Represents a tree object in the version control system. A tree contains a mapping of file names to their corresponding hashes. It can be saved to the objects directory as a JSON
 * file with its hash as the filename.
 */
@Data
public class Tree {

  private final Map<String, String> entries;

  public Tree(Map<String, String> entries) {
    this.entries = entries;
  }

  /**
   * Saves the tree object to the objects directory. The tree is saved as a JSON file with its SHA-1 hash as the filename.
   *
   * @return The SHA-1 hash of the tree object.
   * @throws IOException If an I/O error occurs while writing the file.
   */
  public String save() throws IOException {
    var mapper = new ObjectMapper();
    var jsonString = mapper.writeValueAsString(entries);
    var treeHash = HashUtil.sha1(jsonString);
    var objectPath = Paths.get(OBJECTS_DIR, treeHash);
    if (!Files.exists(objectPath)) {
      // Write with file name being hash and content being JSON representation of the tree
      Files.write(objectPath, jsonString.getBytes(), java.nio.file.StandardOpenOption.CREATE);
      System.out.println("Tree object created with hash: " + treeHash);
    } else {
      System.out.println("Tree object already exists with hash: " + treeHash);
    }
    return treeHash;
  }
}
