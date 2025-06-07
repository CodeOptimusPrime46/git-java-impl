package com.codemonkey.vcs.model;


import static com.codemonkey.vcs.utils.CommonPath.OBJECTS_DIR;

import com.codemonkey.vcs.utils.HashUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.Data;
import lombok.NoArgsConstructor;
/** Represents a commit in the version control system.
 * A commit contains a message, a parent commit hash, a tree hash, and a timestamp.
 * It can be saved to the objects directory as a JSON file with its hash as the filename.
 */
@Data
@NoArgsConstructor
public class Commit {

  private String message;
  private String parentHash;
  private String treeHash;
  private Long timestamp;

  public Commit(String message, String parentHash, String treeHash, long timestamp) {
    this.message = message;
    this.parentHash = parentHash;
    this.treeHash = treeHash;
    this.timestamp = timestamp;
  }
  /**
   * Saves the commit object to the objects' directory.
   * The commit is saved as a JSON file with its SHA-1 hash as the filename.
   *
   * @return The SHA-1 hash of the commit object.
   * @throws IOException If an I/O error occurs while writing the file.
   */
  public String save() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    var jsonString = mapper.writeValueAsString(this);
    var commitHash = HashUtil.sha1(jsonString);
    var objectPath = Paths.get(OBJECTS_DIR, commitHash);
    if (!Files.exists(objectPath)) {
      Files.writeString(objectPath, jsonString);
      System.out.println("Commit object created with hash: " + commitHash);
    } else {
      System.out.println("Commit object already exists with hash: " + commitHash);
    }
    return commitHash;
  }
}
