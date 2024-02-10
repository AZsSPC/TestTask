package com.illiapinchuk.testtask.exception;

import java.io.Serial;

/** Exception for case when bid amount is less than current price. */
public class BidShouldBeMoreThenPreviousOne extends RuntimeException {
  @Serial private static final long serialVersionUID = -8880150136170878350L;

  public BidShouldBeMoreThenPreviousOne(String message) {
    super(message);
  }
}
