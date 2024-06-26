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
 * OI40DBPegging
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBPegging {
  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("consumerTaskCode")
  private String consumerTaskCode = null;

  @JsonProperty("consumerWorkOrderCode")
  private String consumerWorkOrderCode = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("peggingQty")
  private Double peggingQty = null;

  @JsonProperty("producerTaskCode")
  private String producerTaskCode = null;

  @JsonProperty("producerWorkOrderCode")
  private String producerWorkOrderCode = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  public OI40DBPegging attributesMap(Object attributesMap) {
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

  public OI40DBPegging code(String code) {
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

  public OI40DBPegging consumerTaskCode(String consumerTaskCode) {
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

  public OI40DBPegging consumerWorkOrderCode(String consumerWorkOrderCode) {
    this.consumerWorkOrderCode = consumerWorkOrderCode;
    return this;
  }

   /**
   * Get consumerWorkOrderCode
   * @return consumerWorkOrderCode
  **/
  @ApiModelProperty(value = "")
  public String getConsumerWorkOrderCode() {
    return consumerWorkOrderCode;
  }

  public void setConsumerWorkOrderCode(String consumerWorkOrderCode) {
    this.consumerWorkOrderCode = consumerWorkOrderCode;
  }

  public OI40DBPegging description(String description) {
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

  public OI40DBPegging integrationTs(Date integrationTs) {
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

  public OI40DBPegging modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBPegging peggingQty(Double peggingQty) {
    this.peggingQty = peggingQty;
    return this;
  }

   /**
   * Get peggingQty
   * @return peggingQty
  **/
  @ApiModelProperty(value = "")
  public Double getPeggingQty() {
    return peggingQty;
  }

  public void setPeggingQty(Double peggingQty) {
    this.peggingQty = peggingQty;
  }

  public OI40DBPegging producerTaskCode(String producerTaskCode) {
    this.producerTaskCode = producerTaskCode;
    return this;
  }

   /**
   * Get producerTaskCode
   * @return producerTaskCode
  **/
  @ApiModelProperty(value = "")
  public String getProducerTaskCode() {
    return producerTaskCode;
  }

  public void setProducerTaskCode(String producerTaskCode) {
    this.producerTaskCode = producerTaskCode;
  }

  public OI40DBPegging producerWorkOrderCode(String producerWorkOrderCode) {
    this.producerWorkOrderCode = producerWorkOrderCode;
    return this;
  }

   /**
   * Get producerWorkOrderCode
   * @return producerWorkOrderCode
  **/
  @ApiModelProperty(value = "")
  public String getProducerWorkOrderCode() {
    return producerWorkOrderCode;
  }

  public void setProducerWorkOrderCode(String producerWorkOrderCode) {
    this.producerWorkOrderCode = producerWorkOrderCode;
  }

  public OI40DBPegging removed(Boolean removed) {
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
    OI40DBPegging oi40DBPegging = (OI40DBPegging) o;
    return Objects.equals(this.attributesMap, oi40DBPegging.attributesMap) &&
        Objects.equals(this.code, oi40DBPegging.code) &&
        Objects.equals(this.consumerTaskCode, oi40DBPegging.consumerTaskCode) &&
        Objects.equals(this.consumerWorkOrderCode, oi40DBPegging.consumerWorkOrderCode) &&
        Objects.equals(this.description, oi40DBPegging.description) &&
        Objects.equals(this.integrationTs, oi40DBPegging.integrationTs) &&
        Objects.equals(this.modifiedTimestamp, oi40DBPegging.modifiedTimestamp) &&
        Objects.equals(this.peggingQty, oi40DBPegging.peggingQty) &&
        Objects.equals(this.producerTaskCode, oi40DBPegging.producerTaskCode) &&
        Objects.equals(this.producerWorkOrderCode, oi40DBPegging.producerWorkOrderCode) &&
        Objects.equals(this.removed, oi40DBPegging.removed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, code, consumerTaskCode, consumerWorkOrderCode, description, integrationTs, modifiedTimestamp, peggingQty, producerTaskCode, producerWorkOrderCode, removed);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBPegging {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    consumerTaskCode: ").append(toIndentedString(consumerTaskCode)).append("\n");
    sb.append("    consumerWorkOrderCode: ").append(toIndentedString(consumerWorkOrderCode)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    peggingQty: ").append(toIndentedString(peggingQty)).append("\n");
    sb.append("    producerTaskCode: ").append(toIndentedString(producerTaskCode)).append("\n");
    sb.append("    producerWorkOrderCode: ").append(toIndentedString(producerWorkOrderCode)).append("\n");
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

