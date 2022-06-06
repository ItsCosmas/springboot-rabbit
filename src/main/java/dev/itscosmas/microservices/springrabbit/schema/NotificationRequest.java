package dev.itscosmas.microservices.springrabbit.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class NotificationRequest {
  @JsonProperty("fullName")
  private String fullName;
  @JsonProperty("phone")
  private String phone;
  @JsonProperty("email")
  private String email;

}
