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
 * OI40DBTaskRelation
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBTaskRelation {
  @JsonProperty("alignmentType")
  private String alignmentType = null;

  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("bomItemCode")
  private String bomItemCode = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("consumerTaskCode")
  private String consumerTaskCode = null;

  @JsonProperty("consumptionBatchQty")
  private Double consumptionBatchQty = null;

  @JsonProperty("consumptionTransferType")
  private String consumptionTransferType = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("offsetMillisecs")
  private Long offsetMillisecs = null;

  @JsonProperty("peggingCode")
  private String peggingCode = null;

  @JsonProperty("peggingEdge")
  private Boolean peggingEdge = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("supplierTaskCode")
  private String supplierTaskCode = null;

  @JsonProperty("timeRangeType")
  private String timeRangeType = null;

  public OI40DBTaskRelation alignmentType(String alignmentType) {
    this.alignmentType = alignmentType;
    return this;
  }

   /**
   * Get alignmentType
   * @return alignmentType
  **/
  @ApiModelProperty(value = "")
  public String getAlignmentType() {
    return alignmentType;
  }

  public void setAlignmentType(String alignmentType) {
    this.alignmentType = alignmentType;
  }

  public OI40DBTaskRelation attributesMap(Object attributesMap) {
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

  public OI40DBTaskRelation bomItemCode(String bomItemCode) {
    this.bomItemCode = bomItemCode;
    return this;
  }

   /**
   * Get bomItemCode
   * @return bomItemCode
  **/
  @ApiModelProperty(value = "")
  public String getBomItemCode() {
    return bomItemCode;
  }

  public void setBomItemCode(String bomItemCode) {
    this.bomItemCode = bomItemCode;
  }

  public OI40DBTaskRelation code(String code) {
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

  public OI40DBTaskRelation consumerTaskCode(String consumerTaskCode) {
    this.consumerTaskCode = consumerTaskCode;
    return this;
  }

   /**
   * Get consumerTaskCode
   * @return consumerTaskCode
  **/
  @ApiModelProperty(value = "")
  public String getConsumerTaskCode() {
    return consumerTaskCode;
  }

  public void setConsumerTaskCode(String consumerTaskCode) {
    this.consumerTaskCode = consumerTaskCode;
  }

  public OI40DBTaskRelation consumptionBatchQty(Double consumptionBatchQty) {
    this.consumptionBatchQty = consumptionBatchQty;
    return this;
  }

   /**
   * Get consumptionBatchQty
   * @return consumptionBatchQty
  **/
  @ApiModelProperty(value = "")
  public Double getConsumptionBatchQty() {
    return consumptionBatchQty;
  }

  public void setConsumptionBatchQty(Double consumptionBatchQty) {
    this.consumptionBatchQty = consumptionBatchQty;
  }

  public OI40DBTaskRelation consumptionTransferType(String consumptionTransferType) {
    this.consumptionTransferType = consumptionTransferType;
    return this;
  }

   /**
   * Get consumptionTransferType
   * @return consumptionTransferType
  **/
  @ApiModelProperty(value = "")
  public String getConsumptionTransferType() {
    return consumptionTransferType;
  }

  public void setConsumptionTransferType(String consumptionTransferType) {
    this.consumptionTransferType = consumptionTransferType;
  }

  public OI40DBTaskRelation description(String description) {
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

  public OI40DBTaskRelation integrationTs(Date integrationTs) {
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

  public OI40DBTaskRelation modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBTaskRelation offsetMillisecs(Long offsetMillisecs) {
    this.offsetMillisecs = offsetMillisecs;
    return this;
  }

   /**
   * Get offsetMillisecs
   * @return offsetMillisecs
  **/
  @ApiModelProperty(value = "")
  public Long getOffsetMillisecs() {
    return offsetMillisecs;
  }

  public void setOffsetMillisecs(Long offsetMillisecs) {
    this.offsetMillisecs = offsetMillisecs;
  }

  public OI40DBTaskRelation peggingCode(String peggingCode) {
    this.peggingCode = peggingCode;
    return this;
  }

   /**
   * Get peggingCode
   * @return peggingCode
  **/
  @ApiModelProperty(value = "")
  public String getPeggingCode() {
    return peggingCode;
  }

  public void setPeggingCode(String peggingCode) {
    this.peggingCode = peggingCode;
  }

  public OI40DBTaskRelation peggingEdge(Boolean peggingEdge) {
    this.peggingEdge = peggingEdge;
    return this;
  }

   /**
   * Get peggingEdge
   * @return peggingEdge
  **/
  @ApiModelProperty(value = "")
  public Boolean isPeggingEdge() {
    return peggingEdge;
  }

  public void setPeggingEdge(Boolean peggingEdge) {
    this.peggingEdge = peggingEdge;
  }

  public OI40DBTaskRelation removed(Boolean removed) {
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

  public OI40DBTaskRelation supplierTaskCode(String supplierTaskCode) {
    this.supplierTaskCode = supplierTaskCode;
    return this;
  }

   /**
   * Get supplierTaskCode
   * @return supplierTaskCode
  **/
  @ApiModelProperty(value = "")
  public String getSupplierTaskCode() {
    return supplierTaskCode;
  }

  public void setSupplierTaskCode(String supplierTaskCode) {
    this.supplierTaskCode = supplierTaskCode;
  }

  public OI40DBTaskRelation timeRangeType(String timeRangeType) {
    this.timeRangeType = timeRangeType;
    return this;
  }

   /**
   * Get timeRangeType
   * @return timeRangeType
  **/
  @ApiModelProperty(value = "")
  public String getTimeRangeType() {
    return timeRangeType;
  }

  public void setTimeRangeType(String timeRangeType) {
    this.timeRangeType = timeRangeType;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBTaskRelation oi40DBTaskRelation = (OI40DBTaskRelation) o;
    return Objects.equals(this.alignmentType, oi40DBTaskRelation.alignmentType) &&
        Objects.equals(this.attributesMap, oi40DBTaskRelation.attributesMap) &&
        Objects.equals(this.bomItemCode, oi40DBTaskRelation.bomItemCode) &&
        Objects.equals(this.code, oi40DBTaskRelation.code) &&
        Objects.equals(this.consumerTaskCode, oi40DBTaskRelation.consumerTaskCode) &&
        Objects.equals(this.consumptionBatchQty, oi40DBTaskRelation.consumptionBatchQty) &&
        Objects.equals(this.consumptionTransferType, oi40DBTaskRelation.consumptionTransferType) &&
        Objects.equals(this.description, oi40DBTaskRelation.description) &&
        Objects.equals(this.integrationTs, oi40DBTaskRelation.integrationTs) &&
        Objects.equals(this.modifiedTimestamp, oi40DBTaskRelation.modifiedTimestamp) &&
        Objects.equals(this.offsetMillisecs, oi40DBTaskRelation.offsetMillisecs) &&
        Objects.equals(this.peggingCode, oi40DBTaskRelation.peggingCode) &&
        Objects.equals(this.peggingEdge, oi40DBTaskRelation.peggingEdge) &&
        Objects.equals(this.removed, oi40DBTaskRelation.removed) &&
        Objects.equals(this.supplierTaskCode, oi40DBTaskRelation.supplierTaskCode) &&
        Objects.equals(this.timeRangeType, oi40DBTaskRelation.timeRangeType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(alignmentType, attributesMap, bomItemCode, code, consumerTaskCode, consumptionBatchQty, consumptionTransferType, description, integrationTs, modifiedTimestamp, offsetMillisecs, peggingCode, peggingEdge, removed, supplierTaskCode, timeRangeType);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBTaskRelation {\n");
    
    sb.append("    alignmentType: ").append(toIndentedString(alignmentType)).append("\n");
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    bomItemCode: ").append(toIndentedString(bomItemCode)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    consumerTaskCode: ").append(toIndentedString(consumerTaskCode)).append("\n");
    sb.append("    consumptionBatchQty: ").append(toIndentedString(consumptionBatchQty)).append("\n");
    sb.append("    consumptionTransferType: ").append(toIndentedString(consumptionTransferType)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    offsetMillisecs: ").append(toIndentedString(offsetMillisecs)).append("\n");
    sb.append("    peggingCode: ").append(toIndentedString(peggingCode)).append("\n");
    sb.append("    peggingEdge: ").append(toIndentedString(peggingEdge)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    supplierTaskCode: ").append(toIndentedString(supplierTaskCode)).append("\n");
    sb.append("    timeRangeType: ").append(toIndentedString(timeRangeType)).append("\n");
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

