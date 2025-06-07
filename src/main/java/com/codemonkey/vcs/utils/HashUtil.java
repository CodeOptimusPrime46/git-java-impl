package com.codemonkey.vcs.utils;

import java.security.MessageDigest;

/**
 * Utility class for hashing operations.
 * Provides methods to compute SHA-1 hashes of strings.
 */
public class HashUtil {

  /**
   * Computes the SHA-1 hash of the given input string.
   *
   * @param input the input string to hash
   * @return the SHA-1 hash as a hexadecimal string
   */
  public static String sha1(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("SHA-1");
      byte[] result = md.digest(input.getBytes());
      StringBuilder sb = new StringBuilder();
      for (byte b : result) {
        // formats each byte (b) as a two-character hexadecimal value.
        // The %02x ensures that single-digit hex values are zero-padded (e.g., 0a instead of a).
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (Exception e) {
      throw new RuntimeException("SHA-1 hashing failed", e);
    }
  }
}

