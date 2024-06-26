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
 * OI40DBChangeOverMatrixItem
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBChangeOverMatrixItem {
  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("machineCode")
  private String machineCode = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("setupGroupCodeFrom")
  private String setupGroupCodeFrom = null;

  @JsonProperty("setupGroupCodeTo")
  private String setupGroupCodeTo = null;

  @JsonProperty("setupTime")
  private Double setupTime = null;

  @JsonProperty("workCenterCode")
  private String workCenterCode = null;

  public OI40DBChangeOverMatrixItem attributesMap(Object attributesMap) {
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

  public OI40DBChangeOverMatrixItem code(String code) {
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

  public OI40DBChangeOverMatrixItem description(String description) {
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

  public OI40DBChangeOverMatrixItem integrationTs(Date integrationTs) {
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

  public OI40DBChangeOverMatrixItem machineCode(String machineCode) {
    this.machineCode = machineCode;
    return this;
  }

   /**
   * Get machineCode
   * @return machineCode
  **/
  @ApiModelProperty(value = "")
  public String getMachineCode() {
    return machineCode;
  }

  public void setMachineCode(String machineCode) {
    this.machineCode = machineCode;
  }

  public OI40DBChangeOverMatrixItem modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBChangeOverMatrixItem removed(Boolean removed) {
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

  public OI40DBChangeOverMatrixItem setupGroupCodeFrom(String setupGroupCodeFrom) {
    this.setupGroupCodeFrom = setupGroupCodeFrom;
    return this;
  }

   /**
   * Get setupGroupCodeFrom
   * @return setupGroupCodeFrom
  **/
  @ApiModelProperty(value = "")
  public String getSetupGroupCodeFrom() {
    return setupGroupCodeFrom;
  }

  public void setSetupGroupCodeFrom(String setupGroupCodeFrom) {
    this.setupGroupCodeFrom = setupGroupCodeFrom;
  }

  public OI40DBChangeOverMatrixItem setupGroupCodeTo(String setupGroupCodeTo) {
    this.setupGroupCodeTo = setupGroupCodeTo;
    return this;
  }

   /**
   * Get setupGroupCodeTo
   * @return setupGroupCodeTo
  **/
  @ApiModelProperty(value = "")
  public String getSetupGroupCodeTo() {
    return setupGroupCodeTo;
  }

  public void setSetupGroupCodeTo(String setupGroupCodeTo) {
    this.setupGroupCodeTo = setupGroupCodeTo;
  }

  public OI40DBChangeOverMatrixItem setupTime(Double setupTime) {
    this.setupTime = setupTime;
    return this;
  }

   /**
   * Get setupTime
   * @return setupTime
  **/
  @ApiModelProperty(value = "")
  public Double getSetupTime() {
    return setupTime;
  }

  public void setSetupTime(Double setupTime) {
    this.setupTime = setupTime;
  }

  public OI40DBChangeOverMatrixItem workCenterCode(String workCenterCode) {
    this.workCenterCode = workCenterCode;
    return this;
  }

   /**
   * Get workCenterCode
   * @return workCenterCode
  **/
  @ApiModelProperty(value = "")
  public String getWorkCenterCode() {
    return workCenterCode;
  }

  public void setWorkCenterCode(String workCenterCode) {
    this.workCenterCode = workCenterCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBChangeOverMatrixItem oi40DBChangeOverMatrixItem = (OI40DBChangeOverMatrixItem) o;
    return Objects.equals(this.attributesMap, oi40DBChangeOverMatrixItem.attributesMap) &&
        Objects.equals(this.code, oi40DBChangeOverMatrixItem.code) &&
        Objects.equals(this.description, oi40DBChangeOverMatrixItem.description) &&
        Objects.equals(this.integrationTs, oi40DBChangeOverMatrixItem.integrationTs) &&
        Objects.equals(this.machineCode, oi40DBChangeOverMatrixItem.machineCode) &&
        Objects.equals(this.modifiedTimestamp, oi40DBChangeOverMatrixItem.modifiedTimestamp) &&
        Objects.equals(this.removed, oi40DBChangeOverMatrixItem.removed) &&
        Objects.equals(this.setupGroupCodeFrom, oi40DBChangeOverMatrixItem.setupGroupCodeFrom) &&
        Objects.equals(this.setupGroupCodeTo, oi40DBChangeOverMatrixItem.setupGroupCodeTo) &&
        Objects.equals(this.setupTime, oi40DBChangeOverMatrixItem.setupTime) &&
        Objects.equals(this.workCenterCode, oi40DBChangeOverMatrixItem.workCenterCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, code, description, integrationTs, machineCode, modifiedTimestamp, removed, setupGroupCodeFrom, setupGroupCodeTo, setupTime, workCenterCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBChangeOverMatrixItem {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    machineCode: ").append(toIndentedString(machineCode)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    setupGroupCodeFrom: ").append(toIndentedString(setupGroupCodeFrom)).append("\n");
    sb.append("    setupGroupCodeTo: ").append(toIndentedString(setupGroupCodeTo)).append("\n");
    sb.append("    setupTime: ").append(toIndentedString(setupTime)).append("\n");
    sb.append("    workCenterCode: ").append(toIndentedString(workCenterCode)).append("\n");
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

