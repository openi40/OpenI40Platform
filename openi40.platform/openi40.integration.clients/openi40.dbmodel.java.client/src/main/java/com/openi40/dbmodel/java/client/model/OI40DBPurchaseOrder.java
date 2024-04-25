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
 * OI40DBPurchaseOrder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBPurchaseOrder {
  @JsonProperty("askedDeliveryDate")
  private Date askedDeliveryDate = null;

  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("customPriority")
  private Integer customPriority = null;

  @JsonProperty("departmentCode")
  private String departmentCode = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("orderStatus")
  private String orderStatus = null;

  @JsonProperty("orderType")
  private String orderType = null;

  @JsonProperty("partner")
  private String partner = null;

  @JsonProperty("plannedDeliveryDate")
  private Date plannedDeliveryDate = null;

  @JsonProperty("plantCode")
  private String plantCode = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  public OI40DBPurchaseOrder askedDeliveryDate(Date askedDeliveryDate) {
    this.askedDeliveryDate = askedDeliveryDate;
    return this;
  }

   /**
   * Get askedDeliveryDate
   * @return askedDeliveryDate
  **/
  @ApiModelProperty(value = "")
  public Date getAskedDeliveryDate() {
    return askedDeliveryDate;
  }

  public void setAskedDeliveryDate(Date askedDeliveryDate) {
    this.askedDeliveryDate = askedDeliveryDate;
  }

  public OI40DBPurchaseOrder attributesMap(Object attributesMap) {
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

  public OI40DBPurchaseOrder code(String code) {
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

  public OI40DBPurchaseOrder customPriority(Integer customPriority) {
    this.customPriority = customPriority;
    return this;
  }

   /**
   * Get customPriority
   * @return customPriority
  **/
  @ApiModelProperty(value = "")
  public Integer getCustomPriority() {
    return customPriority;
  }

  public void setCustomPriority(Integer customPriority) {
    this.customPriority = customPriority;
  }

  public OI40DBPurchaseOrder departmentCode(String departmentCode) {
    this.departmentCode = departmentCode;
    return this;
  }

   /**
   * Get departmentCode
   * @return departmentCode
  **/
  @ApiModelProperty(value = "")
  public String getDepartmentCode() {
    return departmentCode;
  }

  public void setDepartmentCode(String departmentCode) {
    this.departmentCode = departmentCode;
  }

  public OI40DBPurchaseOrder description(String description) {
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

  public OI40DBPurchaseOrder integrationTs(Date integrationTs) {
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

  public OI40DBPurchaseOrder modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBPurchaseOrder orderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
    return this;
  }

   /**
   * Get orderStatus
   * @return orderStatus
  **/
  @ApiModelProperty(value = "")
  public String getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(String orderStatus) {
    this.orderStatus = orderStatus;
  }

  public OI40DBPurchaseOrder orderType(String orderType) {
    this.orderType = orderType;
    return this;
  }

   /**
   * Get orderType
   * @return orderType
  **/
  @ApiModelProperty(value = "")
  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public OI40DBPurchaseOrder partner(String partner) {
    this.partner = partner;
    return this;
  }

   /**
   * Get partner
   * @return partner
  **/
  @ApiModelProperty(value = "")
  public String getPartner() {
    return partner;
  }

  public void setPartner(String partner) {
    this.partner = partner;
  }

  public OI40DBPurchaseOrder plannedDeliveryDate(Date plannedDeliveryDate) {
    this.plannedDeliveryDate = plannedDeliveryDate;
    return this;
  }

   /**
   * Get plannedDeliveryDate
   * @return plannedDeliveryDate
  **/
  @ApiModelProperty(value = "")
  public Date getPlannedDeliveryDate() {
    return plannedDeliveryDate;
  }

  public void setPlannedDeliveryDate(Date plannedDeliveryDate) {
    this.plannedDeliveryDate = plannedDeliveryDate;
  }

  public OI40DBPurchaseOrder plantCode(String plantCode) {
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

  public OI40DBPurchaseOrder removed(Boolean removed) {
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
    OI40DBPurchaseOrder oi40DBPurchaseOrder = (OI40DBPurchaseOrder) o;
    return Objects.equals(this.askedDeliveryDate, oi40DBPurchaseOrder.askedDeliveryDate) &&
        Objects.equals(this.attributesMap, oi40DBPurchaseOrder.attributesMap) &&
        Objects.equals(this.code, oi40DBPurchaseOrder.code) &&
        Objects.equals(this.customPriority, oi40DBPurchaseOrder.customPriority) &&
        Objects.equals(this.departmentCode, oi40DBPurchaseOrder.departmentCode) &&
        Objects.equals(this.description, oi40DBPurchaseOrder.description) &&
        Objects.equals(this.integrationTs, oi40DBPurchaseOrder.integrationTs) &&
        Objects.equals(this.modifiedTimestamp, oi40DBPurchaseOrder.modifiedTimestamp) &&
        Objects.equals(this.orderStatus, oi40DBPurchaseOrder.orderStatus) &&
        Objects.equals(this.orderType, oi40DBPurchaseOrder.orderType) &&
        Objects.equals(this.partner, oi40DBPurchaseOrder.partner) &&
        Objects.equals(this.plannedDeliveryDate, oi40DBPurchaseOrder.plannedDeliveryDate) &&
        Objects.equals(this.plantCode, oi40DBPurchaseOrder.plantCode) &&
        Objects.equals(this.removed, oi40DBPurchaseOrder.removed);
  }

  @Override
  public int hashCode() {
    return Objects.hash(askedDeliveryDate, attributesMap, code, customPriority, departmentCode, description, integrationTs, modifiedTimestamp, orderStatus, orderType, partner, plannedDeliveryDate, plantCode, removed);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBPurchaseOrder {\n");
    
    sb.append("    askedDeliveryDate: ").append(toIndentedString(askedDeliveryDate)).append("\n");
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    customPriority: ").append(toIndentedString(customPriority)).append("\n");
    sb.append("    departmentCode: ").append(toIndentedString(departmentCode)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    orderStatus: ").append(toIndentedString(orderStatus)).append("\n");
    sb.append("    orderType: ").append(toIndentedString(orderType)).append("\n");
    sb.append("    partner: ").append(toIndentedString(partner)).append("\n");
    sb.append("    plannedDeliveryDate: ").append(toIndentedString(plannedDeliveryDate)).append("\n");
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

