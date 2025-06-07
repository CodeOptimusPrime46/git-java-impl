package com.codemonkey.vcs.core;

import com.codemonkey.vcs.utils.IndexReader;
import com.codemonkey.vcs.utils.LastCommitReader;
import com.codemonkey.vcs.utils.WorkingDirectoryReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatusCommand {
  /**
   * Displays the status of the version control system, including staged, modified, and untracked files.
   *
   * @throws IOException if an I/O error occurs while reading the index, last commit, or working directory.
   */
  public static void showStatus() throws IOException {
    Map<String, String> index = IndexReader.read();
    Map<String, String> lastCommit = LastCommitReader.read();
    Map<String, String> workingDir = WorkingDirectoryReader.read();

    List<String> stagedFiles = new ArrayList<>();
    List<String> modifiedFiles = new ArrayList<>();
    List<String> untrackedFiles = new ArrayList<>();

    for (var file : workingDir.keySet()) {
      String workingDirHash = workingDir.get(file);
      String indexHash = index.get(file);
      String lastCommitHash = lastCommit.get(file);

      if (null != indexHash && !indexHash.equals(lastCommitHash)) {
        // File is staged but has been modified since last commit
        stagedFiles.add(file);
      } else if (null == indexHash && null == lastCommitHash) {
        // since file is not in index and not in last commit, it is untracked
        untrackedFiles.add(file);
      } else if (!workingDirHash.equals(indexHash)) {
        // File is modified in working directory but not staged
        modifiedFiles.add(file);
      }
    }

    System.out.println("Changes to be committed:");
    stagedFiles.forEach(f -> System.out.println("  staged: " + f));
    System.out.println("\nChanges not staged for commit:");
    modifiedFiles.forEach(f -> System.out.println("  modified: " + f));
    System.out.println("\nUntracked files:");
    untrackedFiles.forEach(f -> System.out.println("  " + f));

  }
}
