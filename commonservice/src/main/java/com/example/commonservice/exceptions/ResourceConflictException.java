package com.example.commonservice.exceptions;

import lombok.Getter;

@Getter
public class ResourceConflictException extends RuntimeException {

  public ResourceConflictException(String message) {
    super(message);
  }

}
