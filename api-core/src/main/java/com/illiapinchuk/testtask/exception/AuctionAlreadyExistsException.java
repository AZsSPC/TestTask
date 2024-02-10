package com.illiapinchuk.testtask.exception;

import jakarta.persistence.EntityExistsException;
import java.io.Serial;

/**
 * An exception class that indicates that a requested auction already exists. This class extends
 * from the {@link EntityExistsException} class.
 */
public class AuctionAlreadyExistsException extends EntityExistsException {
  @Serial private static final long serialVersionUID = 391276374798037515L;

  public AuctionAlreadyExistsException(String message) {
    super(message);
  }
}
