package com.illiapinchuk.testtask.common;

import java.util.Calendar;
import lombok.experimental.UtilityClass;

/** Contains various constants used in the moodle application. */
@UtilityClass
public class ApplicationConstants {

  /** Inner utility class for web-related constants. */
  @UtilityClass
  public class Web {
    /** Inner utility class for web-related paths. */
    @UtilityClass
    public class Path {
      public static final String LOGIN_PATH = "/auth/login";
      public static final String SIGN_UP_PATH = "/users";
    }

    /** Inner utility class for constants related to security part. */
    @UtilityClass
    public class Security {
      public static final String TOKEN_HEADER_NAME = "token";
      public static final String SERVER_TIMEZONE_ID =
          Calendar.getInstance().getTimeZone().toZoneId().toString();

      /** Inner utility class for constants related to security jwt claims part. */
      @UtilityClass
      public class JwtClaims {
        public static final String ROLES = "roles";
        public static final String TIME_ZONE_ID = "timeZoneId";
      }
    }
  }
}
