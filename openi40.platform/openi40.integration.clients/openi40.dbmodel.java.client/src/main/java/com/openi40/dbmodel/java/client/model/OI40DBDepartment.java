/*
 * Openi40
 * The open source industy 4.0 production scheduler & MES platform
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.openi40.dbmodel.java.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * OI40DBDepartment
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBDepartment {
  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("plantCode")
  private String plantCode = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  public OI40DBDepartment attributesMap(Object attributesMap) {
    this.attributesMap = attributesMap;
    return this;
  }

   /**
   * Get attributesMap
   * @return attributesMap
  **/
  @ApiModelProperty(value = "")
  public Object getAttributesMap() {
    return attributesMap;
  }

  public void setAttributesMap(Object attributesMap) {
    this.attributesMap = attributesMap;
  }

  public OI40DBDepartment code(String code) {
    this.code = code;
    return this;
  }

   /**
   * Get code
   * @return code
  **/
  @ApiModelProperty(value = "")
  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public OI40DBDepartment description(String description) {
    this.description = description;
    return this;
  }

   /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public OI40DBDepartment integrationTs(Date integrationTs) {
    this.integrationTs = integrationTs;
    return this;
  }

   /**
   * Get integrationTs
   * @return integrationTs
  **/
  @ApiModelProperty(value = "")
  public Date getIntegrationTs() {
    return integrationTs;
  }

  public void setIntegrationTs(Date integrationTs) {
    this.integrationTs = integrationTs;
  }

  public OI40DBDepartment modifiedTimestamp(Date modifiedTimestamp) {
    this.modifiedTimestamp = modifiedTimestamp;
    return this;
  }

   /**
   * Get modifiedTimestamp
   * @return modifiedTimestamp
  **/
  @ApiModelProperty(value = "")
  public Date getModifiedTimestamp() {
    return modifiedTimestamp;
  }

  public void setModifiedTimestamp(Date modifiedTimestamp) {
    this.modifiedTimestamp = modifiedTimestamp;
  }

  public OI40DBDepartment plantCode(String plantCode) {
    this.plantCode = plantCode;
    return this;
  }

   /**
   * Get plantCode
   * @return plantCode
  **/
  @ApiModelProperty(value = "")
  public String getPlantCode() {
    return plantCode;
  }

  public void setPlantCode(String plantCode) {
    this.plantCode = plantCode;
  }

  public OI40DBDepartment removed(Boolean removed) {
    this.removed = removed;
    return this;
  }

   /**
   * Get removed
   * @return removed
  **/
  @ApiModelProperty(value = "")
  public Boolean isRemoved() {
    return removed;
  }

  public void setRemoved(Boolean removed) {
    this.removed = removed;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBDepartment oi40DBDepartment = (OI40DBDepartment) o;
    return Objects.equals(this.attributesMap, oi40DBDepartment.attributesMap) &&
        Objects.equals(this.code, oi40DBDepartment.code) &&
        Objects.equals(this.description, oi40DBDepartment.description) &&
        Objects.equals(this.integrationTs, oi40DBDepartment.integrationTs) &&
        Objects.equals(this.modifiedTimestamp, oi40DBDepartment.modifiedTimestamp) &&
        Objects.equals(this.plantCode, oi40DBDepartment.plantCode) &&
        Objects.equals(this.removed, oi40DBDepartment.removed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, code, description, integrationTs, modifiedTimestamp, plantCode, removed);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBDepartment {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    plantCode: ").append(toIndentedString(plantCode)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
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

