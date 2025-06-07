package com.codemonkey.vcs.model;

import static com.codemonkey.vcs.utils.CommonPath.OBJECTS_DIR;

import com.codemonkey.vcs.utils.HashUtil;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Blob represents a file that is tracked in the version control system. It computes the SHA-1 hash of the file content and saves it in the .vcs/objects directory. The file is
 * saved only if it does not already exist in the objects directory. The hash is used as the filename in the objects directory.
 */
public class Blob {

  private final String filePath;

  public Blob(String filePath) {
    this.filePath = filePath;
  }

  /**
   * Saves the blob to the .vcs/objects directory.
   *
   * @return The SHA-1 hash of the file content.
   * @throws IOException If an I/O error occurs while reading the file or writing to the objects directory.
   */
  public String save() throws IOException {
    // 1. Read file contents
    String content = Files.readString(Paths.get(filePath));

    // 2. Compute SHA-1 hash
    String hash = HashUtil.sha1(content);

    // 3. Save to .vcs/objects/<hash> if not already saved
    Path objectPath = Paths.get(OBJECTS_DIR, hash);
    if (!Files.exists(objectPath)) {
      Files.write(objectPath, content.getBytes(), StandardOpenOption.CREATE);
    }
    // 4. Return the hash
    return hash;
  }

}
