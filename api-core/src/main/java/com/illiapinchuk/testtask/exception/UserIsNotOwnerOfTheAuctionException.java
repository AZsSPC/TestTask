package com.illiapinchuk.testtask.exception;

import java.io.Serial;

/** The exception is thrown when the user is not the owner of the auction */
public class UserIsNotOwnerOfTheAuctionException extends RuntimeException {
  @Serial private static final long serialVersionUID = -5132945494489165120L;

  public UserIsNotOwnerOfTheAuctionException(String message) {
    super(message);
  }
}
