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
import org.openapitools.client.model.AdDebug;
import org.openapitools.client.model.AdRequestDebug;
import org.openapitools.client.model.AdUpdateObject;
import org.openapitools.client.model.AdUpdateRequestAllOf;
import org.openapitools.client.model.IRequest;

/**
 * AdUpdateRequest
 */
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen", date = "2023-02-17T22:47:20.266576200+03:00[Europe/Moscow]")
public class AdUpdateRequest extends IRequest {
  public static final String SERIALIZED_NAME_DEBUG = "debug";
  @SerializedName(SERIALIZED_NAME_DEBUG)
  private AdDebug debug;

  public static final String SERIALIZED_NAME_AD = "ad";
  @SerializedName(SERIALIZED_NAME_AD)
  private AdUpdateObject ad = null;


  public AdUpdateRequest debug(AdDebug debug) {
    
    this.debug = debug;
    return this;
  }

   /**
   * Get debug
   * @return debug
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public AdDebug getDebug() {
    return debug;
  }


  public void setDebug(AdDebug debug) {
    this.debug = debug;
  }


  public AdUpdateRequest ad(AdUpdateObject ad) {
    
    this.ad = ad;
    return this;
  }

   /**
   * Get ad
   * @return ad
  **/
  @javax.annotation.Nullable
  @ApiModelProperty(value = "")

  public AdUpdateObject getAd() {
    return ad;
  }


  public void setAd(AdUpdateObject ad) {
    this.ad = ad;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdUpdateRequest adUpdateRequest = (AdUpdateRequest) o;
    return Objects.equals(this.debug, adUpdateRequest.debug) &&
        Objects.equals(this.ad, adUpdateRequest.ad) &&
        super.equals(o);
  }

  @Override
  public int hashCode() {
    return Objects.hash(debug, ad, super.hashCode());
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdUpdateRequest {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    debug: ").append(toIndentedString(debug)).append("\n");
    sb.append("    ad: ").append(toIndentedString(ad)).append("\n");
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

