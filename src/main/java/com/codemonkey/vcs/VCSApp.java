package com.codemonkey.vcs;

import com.codemonkey.vcs.core.Add;
import com.codemonkey.vcs.core.CommitCommand;
import com.codemonkey.vcs.core.Initializer;
import com.codemonkey.vcs.core.LogCommand;
import com.codemonkey.vcs.core.StatusCommand;
import com.codemonkey.vcs.utils.IndexReader;
import java.io.IOException;

/**
 * VCSApp is the main entry point for the version control system (VCS) application. It provides a command-line interface to interact with the VCS functionalities such as
 * initializing a repository, adding files, committing changes, viewing commit logs, checking status, and debugging the index.
 */
public class VCSApp {

  /**
   * Main method to run the VCS application. Usage: vcs <command> [options] Available commands:
   *
   * <li>init: Initialize a new version control repository.</li>
   * <li>add file: Add a file to the staging area.</li>
   * <li>commit message: Commit changes with a message.</li>
   * <li>log: Show the commit log.</li>
   * <li>status: Show the status of the repository.</li>
   * <li>push: Push changes to a remote repository (not implemented).</li>
   * <li>pull: Pull changes from a remote repository (not implemented).</li>
   * <li>show-index: Show the contents of the index file for debugging.</li>
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 0) {
      System.out.println("Usage: vcs <command> [options]");
      System.out.println("Available commands: init, commit, push, pull, status");
      return;
    }
    String command = args[0];

    switch (command) {
      case "init":
        Initializer.init();
        break;
      case "add":
        System.out.println("Adding files to staging area...");
        Add.addFile(args[1]);
        break;
      case "commit":
        System.out.println("Committing changes...");
        if (args.length < 2) {
          System.out.println("Usage: vcs commit <message>");
          return;
        }
        String message = args[1];
        CommitCommand.commit(message);
        break;
      case "log":
        System.out.println("Displaying commit log...");
        LogCommand.showLog();
        break;
      case "status":
        System.out.println("Checking status of the repository...");
        StatusCommand.showStatus();
        break;
      case "diff":
        System.err.println("Pending implementation");
        // TODO : Add diff logic here
        break;
      case "push":
        System.err.println("Pending implementation");
        // TODO : Add push logic here
        break;
      case "pull":
        System.err.println("Pending implementation");
        // TODO : Add pull logic here
        break;
      case "show-index":
        System.out.println("Showing index contents...");
        try {
          IndexReader.read().forEach((file, hash) -> System.out.println(file + " -> " + hash));
        } catch (IOException e) {
          System.err.println("Error reading index: " + e.getMessage());
        }
        break;
      default:
        System.out.println("Unknown command: " + command);
    }
  }
}
