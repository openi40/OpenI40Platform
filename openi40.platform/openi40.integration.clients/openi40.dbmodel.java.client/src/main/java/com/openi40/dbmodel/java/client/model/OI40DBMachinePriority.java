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
 * OI40DBMachinePriority
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBMachinePriority {
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

  @JsonProperty("operationEquipmentSpecCode")
  private String operationEquipmentSpecCode = null;

  @JsonProperty("priority")
  private Integer priority = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  public OI40DBMachinePriority attributesMap(Object attributesMap) {
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

  public OI40DBMachinePriority code(String code) {
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

  public OI40DBMachinePriority description(String description) {
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

  public OI40DBMachinePriority integrationTs(Date integrationTs) {
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

  public OI40DBMachinePriority machineCode(String machineCode) {
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

  public OI40DBMachinePriority modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBMachinePriority operationEquipmentSpecCode(String operationEquipmentSpecCode) {
    this.operationEquipmentSpecCode = operationEquipmentSpecCode;
    return this;
  }

   /**
   * Get operationEquipmentSpecCode
   * @return operationEquipmentSpecCode
  **/
  @ApiModelProperty(value = "")
  public String getOperationEquipmentSpecCode() {
    return operationEquipmentSpecCode;
  }

  public void setOperationEquipmentSpecCode(String operationEquipmentSpecCode) {
    this.operationEquipmentSpecCode = operationEquipmentSpecCode;
  }

  public OI40DBMachinePriority priority(Integer priority) {
    this.priority = priority;
    return this;
  }

   /**
   * Get priority
   * @return priority
  **/
  @ApiModelProperty(value = "")
  public Integer getPriority() {
    return priority;
  }

  public void setPriority(Integer priority) {
    this.priority = priority;
  }

  public OI40DBMachinePriority removed(Boolean removed) {
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
    OI40DBMachinePriority oi40DBMachinePriority = (OI40DBMachinePriority) o;
    return Objects.equals(this.attributesMap, oi40DBMachinePriority.attributesMap) &&
        Objects.equals(this.code, oi40DBMachinePriority.code) &&
        Objects.equals(this.description, oi40DBMachinePriority.description) &&
        Objects.equals(this.integrationTs, oi40DBMachinePriority.integrationTs) &&
        Objects.equals(this.machineCode, oi40DBMachinePriority.machineCode) &&
        Objects.equals(this.modifiedTimestamp, oi40DBMachinePriority.modifiedTimestamp) &&
        Objects.equals(this.operationEquipmentSpecCode, oi40DBMachinePriority.operationEquipmentSpecCode) &&
        Objects.equals(this.priority, oi40DBMachinePriority.priority) &&
        Objects.equals(this.removed, oi40DBMachinePriority.removed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, code, description, integrationTs, machineCode, modifiedTimestamp, operationEquipmentSpecCode, priority, removed);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBMachinePriority {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    machineCode: ").append(toIndentedString(machineCode)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    operationEquipmentSpecCode: ").append(toIndentedString(operationEquipmentSpecCode)).append("\n");
    sb.append("    priority: ").append(toIndentedString(priority)).append("\n");
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

