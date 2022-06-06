package dev.itscosmas.microservices.springrabbit.schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "code", "message", "data"
})
@ToString
@Getter @Setter
public class BaseResponse {
  @JsonProperty("code")
  private int code;
  @JsonProperty("message")
  private String message;
  @JsonProperty("data")
  private Object Data;


  public BaseResponse withCode(int httpCode){
    this.code = httpCode;
    return this;
  }

  public BaseResponse withMessage(String responseMessage){
    this.message = responseMessage;
    return this;
  }

  public BaseResponse withData(Object responseData){
    this.Data = responseData;
    return this;
  }

}

