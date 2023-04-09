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
import io.swagger.annotations.ApiModel;
import com.google.gson.annotations.SerializedName;

import java.io.IOException;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Тип видимости объявления. Возможные значения: видит только владелец, только зарегистрированный в системе пользователь, видимо всем
 */
@JsonAdapter(AdVisibility.Adapter.class)
public enum AdVisibility {
  
  OWNERONLY("ownerOnly"),
  
  REGISTEREDONLY("registeredOnly"),
  
  PUBLIC("public");

  private String value;

  AdVisibility(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return String.valueOf(value);
  }

  public static AdVisibility fromValue(String value) {
    for (AdVisibility b : AdVisibility.values()) {
      if (b.value.equals(value)) {
        return b;
      }
    }
    throw new IllegalArgumentException("Unexpected value '" + value + "'");
  }

  public static class Adapter extends TypeAdapter<AdVisibility> {
    @Override
    public void write(final JsonWriter jsonWriter, final AdVisibility enumeration) throws IOException {
      jsonWriter.value(enumeration.getValue());
    }

    @Override
    public AdVisibility read(final JsonReader jsonReader) throws IOException {
      String value = jsonReader.nextString();
      return AdVisibility.fromValue(value);
    }
  }
}

