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
 * OI40DBPlant
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-09T15:18:53.580+01:00")
public class OI40DBPlant {
  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("infiniteCapacity")
  private Boolean infiniteCapacity = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("productiveCompanyCode")
  private String productiveCompanyCode = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("timesheetMetaInfoCode")
  private String timesheetMetaInfoCode = null;

  public OI40DBPlant attributesMap(Object attributesMap) {
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

  public OI40DBPlant code(String code) {
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

  public OI40DBPlant description(String description) {
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

  public OI40DBPlant infiniteCapacity(Boolean infiniteCapacity) {
    this.infiniteCapacity = infiniteCapacity;
    return this;
  }

   /**
   * Get infiniteCapacity
   * @return infiniteCapacity
  **/
  @ApiModelProperty(value = "")
  public Boolean isInfiniteCapacity() {
    return infiniteCapacity;
  }

  public void setInfiniteCapacity(Boolean infiniteCapacity) {
    this.infiniteCapacity = infiniteCapacity;
  }

  public OI40DBPlant integrationTs(Date integrationTs) {
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

  public OI40DBPlant modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBPlant productiveCompanyCode(String productiveCompanyCode) {
    this.productiveCompanyCode = productiveCompanyCode;
    return this;
  }

   /**
   * Get productiveCompanyCode
   * @return productiveCompanyCode
  **/
  @ApiModelProperty(value = "")
  public String getProductiveCompanyCode() {
    return productiveCompanyCode;
  }

  public void setProductiveCompanyCode(String productiveCompanyCode) {
    this.productiveCompanyCode = productiveCompanyCode;
  }

  public OI40DBPlant removed(Boolean removed) {
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

  public OI40DBPlant timesheetMetaInfoCode(String timesheetMetaInfoCode) {
    this.timesheetMetaInfoCode = timesheetMetaInfoCode;
    return this;
  }

   /**
   * Get timesheetMetaInfoCode
   * @return timesheetMetaInfoCode
  **/
  @ApiModelProperty(value = "")
  public String getTimesheetMetaInfoCode() {
    return timesheetMetaInfoCode;
  }

  public void setTimesheetMetaInfoCode(String timesheetMetaInfoCode) {
    this.timesheetMetaInfoCode = timesheetMetaInfoCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBPlant oi40DBPlant = (OI40DBPlant) o;
    return Objects.equals(this.attributesMap, oi40DBPlant.attributesMap) &&
        Objects.equals(this.code, oi40DBPlant.code) &&
        Objects.equals(this.description, oi40DBPlant.description) &&
        Objects.equals(this.infiniteCapacity, oi40DBPlant.infiniteCapacity) &&
        Objects.equals(this.integrationTs, oi40DBPlant.integrationTs) &&
        Objects.equals(this.modifiedTimestamp, oi40DBPlant.modifiedTimestamp) &&
        Objects.equals(this.productiveCompanyCode, oi40DBPlant.productiveCompanyCode) &&
        Objects.equals(this.removed, oi40DBPlant.removed) &&
        Objects.equals(this.timesheetMetaInfoCode, oi40DBPlant.timesheetMetaInfoCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, code, description, infiniteCapacity, integrationTs, modifiedTimestamp, productiveCompanyCode, removed, timesheetMetaInfoCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBPlant {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    infiniteCapacity: ").append(toIndentedString(infiniteCapacity)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    productiveCompanyCode: ").append(toIndentedString(productiveCompanyCode)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    timesheetMetaInfoCode: ").append(toIndentedString(timesheetMetaInfoCode)).append("\n");
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

