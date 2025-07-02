package com.ecommerce.exception;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionResponse {
  private String message;
  private int status;
  private LocalDateTime dateTime;
}
