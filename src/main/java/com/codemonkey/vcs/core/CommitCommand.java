package com.codemonkey.vcs.core;

import static com.codemonkey.vcs.utils.CommonPath.HEAD;
import static com.codemonkey.vcs.utils.CommonPath.INDEX_FILE;

import com.codemonkey.vcs.model.Commit;
import com.codemonkey.vcs.model.Tree;
import com.codemonkey.vcs.utils.IndexReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CommitCommand {

  /**
   * Commits the changes in the index to a new commit object.
   *
   * @param message The commit message.
   * @throws IOException If an I/O error occurs while reading or writing files.
   */
  public static void commit(String message) throws IOException {
    // Read the index file
    var indexEntries = IndexReader.read();
    if (indexEntries.isEmpty()) {
      System.out.println("No changes to commit.");
      return;
    }
    // Create a tree object from the index entries
    Tree tree = new Tree(indexEntries);
    var treeHash = tree.save();

    // Read the current HEAD to get the parent commit hash
    Path currentPath = Paths.get(HEAD);
    var parentHash = Files.exists(currentPath)
        ? Files.readString(currentPath).trim()
        : null;

    // Create a new commit object with the message, parent hash, and tree hash
    Commit commit = new Commit(message, parentHash, treeHash, System.currentTimeMillis());
    var commitHash = commit.save();

    // Update HEAD to point to the new commit
    Files.writeString(currentPath, commitHash, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    // clean up the index file
    Files.write(Paths.get(INDEX_FILE), "".getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
  }
}
