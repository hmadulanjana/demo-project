package net.codinghermit.api.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RuntimeUtils {

  public static String getMethodName() {
    return Thread.currentThread().getStackTrace()[2].getMethodName();
  }

  public static String getMethodName(int depth) {
    return Thread.currentThread().getStackTrace()[depth].getMethodName();
  }
}
