/*
 * Marketplace
 * This is a place where sellers and buyers meat each other
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openapitools.client.model.Error;
import org.openapitools.client.model.ResponseResult;

/**
 * Базовый интерфейс для всех ответов
 */
@ApiModel(description = "Базовый интерфейс для всех ответов")
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-02-17T22:47:20.266576200+03:00[Europe/Moscow]")

public class IResponse {
  public static final String SERIALIZED_NAME_RESPONSE_TYPE = "responseType";
  @SerializedName(SERIALIZED_NAME_RESPONSE_TYPE)
  protected String responseType;

  public static final String SERIALIZED_NAME_REQUEST_ID = "requestId";
  @SerializedName(SERIALIZED_NAME_REQUEST_ID)
  private String requestId;

  public static final String SERIALIZED_NAME_RESULT = "result";
  @SerializedName(SERIALIZED_NAME_RESULT)
  private ResponseResult result;

  public static final String SERIALIZED_NAME_ERRORS = "errors";
  @SerializedName(SERIALIZED_NAME_ERRORS)
  private List<Error> errors = null;

  public IResponse() {
    this.responseType = this.getClass().getSimpleName();
  }

  public IResponse responseType(String responseType) {
    
    this.responseType = responseType;
    return this;
  }

   /**
   * Поле-дескриминатор для вычисления типа запроса
   * @return responseType
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(example = "create", value = "Поле-дескриминатор для вычисления типа запроса")

  public String getResponseType() {
    return responseType;
  }


  public void setResponseType(String responseType) {
    this.responseType = responseType;
  }


  public IResponse requestId(String requestId) {
    
    this.requestId = requestId;
    return this;
  }

   /**
   * Идентификатор запроса для отладки
   * @return requestId
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "Идентификатор запроса для отладки")

  public String getRequestId() {
    return requestId;
  }


  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }


  public IResponse result(ResponseResult result) {
    
    this.result = result;
    return this;
  }

   /**
   * Get result
   * @return result
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public ResponseResult getResult() {
    return result;
  }


  public void setResult(ResponseResult result) {
    this.result = result;
  }


  public IResponse errors(List<Error> errors) {
    
    this.errors = errors;
    return this;
  }

  public IResponse addErrorsItem(Error errorsItem) {
    if (this.errors == null) {
      this.errors = new ArrayList<Error>();
    }
    this.errors.add(errorsItem);
    return this;
  }

   /**
   * Get errors
   * @return errors
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public List<Error> getErrors() {
    return errors;
  }


  public void setErrors(List<Error> errors) {
    this.errors = errors;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    IResponse iresponse = (IResponse) o;
    return Objects.equals(this.responseType, iresponse.responseType) &&
        Objects.equals(this.requestId, iresponse.requestId) &&
        Objects.equals(this.result, iresponse.result) &&
        Objects.equals(this.errors, iresponse.errors);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseType, requestId, result, errors);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class IResponse {\n");
    sb.append("    responseType: ").append(toIndentedString(responseType)).append("\n");
    sb.append("    requestId: ").append(toIndentedString(requestId)).append("\n");
    sb.append("    result: ").append(toIndentedString(result)).append("\n");
    sb.append("    errors: ").append(toIndentedString(errors)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

