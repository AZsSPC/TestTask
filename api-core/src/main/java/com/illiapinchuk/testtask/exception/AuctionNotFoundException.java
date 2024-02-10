package com.illiapinchuk.testtask.exception;

import jakarta.persistence.EntityNotFoundException;
import java.io.Serial;

/**
 * An exception class that indicates that a requested auction was not found. This class extends from
 * the {@link EntityNotFoundException} class.
 */
public class AuctionNotFoundException extends EntityNotFoundException {
  @Serial private static final long serialVersionUID = 2861593456573631247L;

  public AuctionNotFoundException(String message) {
    super(message);
  }
}
