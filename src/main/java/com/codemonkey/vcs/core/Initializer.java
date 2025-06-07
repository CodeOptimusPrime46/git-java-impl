package com.codemonkey.vcs.core;

import static com.codemonkey.vcs.utils.CommonPath.HEAD;
import static com.codemonkey.vcs.utils.CommonPath.INDEX_FILE;
import static com.codemonkey.vcs.utils.CommonPath.OBJECTS_DIR;
import static com.codemonkey.vcs.utils.CommonPath.VCS_DIR;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Initializer {

  /**
   * Initializes the .vcs directory structure. Creates the necessary directories and files for version control. This includes: .vcs directory, .vcs/objects directory, vcs/index
   * file, .vcs/HEAD file pointing to the master branch. If the .vcs directory already exists, it will not reinitialize.
   *
   * @throws IOException if an I/O error occurs while creating directories or files.
   */
  public static void init() throws IOException {
    Path vcsDir = Paths.get(VCS_DIR);
    if (Files.exists(vcsDir)) {
      System.out.println("Initializer already initialized.");
      return;
    }
    Files.createDirectories(vcsDir);
    Files.createDirectories(Paths.get(OBJECTS_DIR));
    Files.write(Paths.get(INDEX_FILE), "".getBytes());
    Files.write(Paths.get(HEAD), "ref: refs/heads/master".getBytes());
    System.out.println("Initialized empty repository in " + vcsDir.toAbsolutePath());

  }
}
