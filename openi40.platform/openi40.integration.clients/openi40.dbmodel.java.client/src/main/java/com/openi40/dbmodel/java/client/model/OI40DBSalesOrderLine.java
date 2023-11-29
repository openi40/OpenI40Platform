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
 * OI40DBSalesOrderLine
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBSalesOrderLine {
  @JsonProperty("askedDeliveryDate")
  private Date askedDeliveryDate = null;

  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("color")
  private String color = null;

  @JsonProperty("completedQty")
  private Double completedQty = null;

  @JsonProperty("customPriority")
  private Integer customPriority = null;

  @JsonProperty("departmentCode")
  private String departmentCode = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("lineStatus")
  private String lineStatus = null;

  @JsonProperty("maxProductionDateConstraint")
  private Date maxProductionDateConstraint = null;

  @JsonProperty("minProductionDateConstraint")
  private Date minProductionDateConstraint = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("orderCode")
  private String orderCode = null;

  @JsonProperty("orderType")
  private String orderType = null;

  @JsonProperty("plannedDeliveryDate")
  private Date plannedDeliveryDate = null;

  @JsonProperty("plantCode")
  private String plantCode = null;

  @JsonProperty("productCode")
  private String productCode = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("residualQty")
  private Double residualQty = null;

  @JsonProperty("totalQty")
  private Double totalQty = null;

  @JsonProperty("warehouseCode")
  private String warehouseCode = null;

  public OI40DBSalesOrderLine askedDeliveryDate(Date askedDeliveryDate) {
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

  public OI40DBSalesOrderLine attributesMap(Object attributesMap) {
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

  public OI40DBSalesOrderLine code(String code) {
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

  public OI40DBSalesOrderLine color(String color) {
    this.color = color;
    return this;
  }

   /**
   * Get color
   * @return color
  **/
  @ApiModelProperty(value = "")
  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public OI40DBSalesOrderLine completedQty(Double completedQty) {
    this.completedQty = completedQty;
    return this;
  }

   /**
   * Get completedQty
   * @return completedQty
  **/
  @ApiModelProperty(value = "")
  public Double getCompletedQty() {
    return completedQty;
  }

  public void setCompletedQty(Double completedQty) {
    this.completedQty = completedQty;
  }

  public OI40DBSalesOrderLine customPriority(Integer customPriority) {
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

  public OI40DBSalesOrderLine departmentCode(String departmentCode) {
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

  public OI40DBSalesOrderLine description(String description) {
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

  public OI40DBSalesOrderLine integrationTs(Date integrationTs) {
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

  public OI40DBSalesOrderLine lineStatus(String lineStatus) {
    this.lineStatus = lineStatus;
    return this;
  }

   /**
   * Get lineStatus
   * @return lineStatus
  **/
  @ApiModelProperty(value = "")
  public String getLineStatus() {
    return lineStatus;
  }

  public void setLineStatus(String lineStatus) {
    this.lineStatus = lineStatus;
  }

  public OI40DBSalesOrderLine maxProductionDateConstraint(Date maxProductionDateConstraint) {
    this.maxProductionDateConstraint = maxProductionDateConstraint;
    return this;
  }

   /**
   * Get maxProductionDateConstraint
   * @return maxProductionDateConstraint
  **/
  @ApiModelProperty(value = "")
  public Date getMaxProductionDateConstraint() {
    return maxProductionDateConstraint;
  }

  public void setMaxProductionDateConstraint(Date maxProductionDateConstraint) {
    this.maxProductionDateConstraint = maxProductionDateConstraint;
  }

  public OI40DBSalesOrderLine minProductionDateConstraint(Date minProductionDateConstraint) {
    this.minProductionDateConstraint = minProductionDateConstraint;
    return this;
  }

   /**
   * Get minProductionDateConstraint
   * @return minProductionDateConstraint
  **/
  @ApiModelProperty(value = "")
  public Date getMinProductionDateConstraint() {
    return minProductionDateConstraint;
  }

  public void setMinProductionDateConstraint(Date minProductionDateConstraint) {
    this.minProductionDateConstraint = minProductionDateConstraint;
  }

  public OI40DBSalesOrderLine modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBSalesOrderLine orderCode(String orderCode) {
    this.orderCode = orderCode;
    return this;
  }

   /**
   * Get orderCode
   * @return orderCode
  **/
  @ApiModelProperty(value = "")
  public String getOrderCode() {
    return orderCode;
  }

  public void setOrderCode(String orderCode) {
    this.orderCode = orderCode;
  }

  public OI40DBSalesOrderLine orderType(String orderType) {
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

  public OI40DBSalesOrderLine plannedDeliveryDate(Date plannedDeliveryDate) {
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

  public OI40DBSalesOrderLine plantCode(String plantCode) {
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

  public OI40DBSalesOrderLine productCode(String productCode) {
    this.productCode = productCode;
    return this;
  }

   /**
   * Get productCode
   * @return productCode
  **/
  @ApiModelProperty(value = "")
  public String getProductCode() {
    return productCode;
  }

  public void setProductCode(String productCode) {
    this.productCode = productCode;
  }

  public OI40DBSalesOrderLine removed(Boolean removed) {
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

  public OI40DBSalesOrderLine residualQty(Double residualQty) {
    this.residualQty = residualQty;
    return this;
  }

   /**
   * Get residualQty
   * @return residualQty
  **/
  @ApiModelProperty(value = "")
  public Double getResidualQty() {
    return residualQty;
  }

  public void setResidualQty(Double residualQty) {
    this.residualQty = residualQty;
  }

  public OI40DBSalesOrderLine totalQty(Double totalQty) {
    this.totalQty = totalQty;
    return this;
  }

   /**
   * Get totalQty
   * @return totalQty
  **/
  @ApiModelProperty(value = "")
  public Double getTotalQty() {
    return totalQty;
  }

  public void setTotalQty(Double totalQty) {
    this.totalQty = totalQty;
  }

  public OI40DBSalesOrderLine warehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
    return this;
  }

   /**
   * Get warehouseCode
   * @return warehouseCode
  **/
  @ApiModelProperty(value = "")
  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBSalesOrderLine oi40DBSalesOrderLine = (OI40DBSalesOrderLine) o;
    return Objects.equals(this.askedDeliveryDate, oi40DBSalesOrderLine.askedDeliveryDate) &&
        Objects.equals(this.attributesMap, oi40DBSalesOrderLine.attributesMap) &&
        Objects.equals(this.code, oi40DBSalesOrderLine.code) &&
        Objects.equals(this.color, oi40DBSalesOrderLine.color) &&
        Objects.equals(this.completedQty, oi40DBSalesOrderLine.completedQty) &&
        Objects.equals(this.customPriority, oi40DBSalesOrderLine.customPriority) &&
        Objects.equals(this.departmentCode, oi40DBSalesOrderLine.departmentCode) &&
        Objects.equals(this.description, oi40DBSalesOrderLine.description) &&
        Objects.equals(this.integrationTs, oi40DBSalesOrderLine.integrationTs) &&
        Objects.equals(this.lineStatus, oi40DBSalesOrderLine.lineStatus) &&
        Objects.equals(this.maxProductionDateConstraint, oi40DBSalesOrderLine.maxProductionDateConstraint) &&
        Objects.equals(this.minProductionDateConstraint, oi40DBSalesOrderLine.minProductionDateConstraint) &&
        Objects.equals(this.modifiedTimestamp, oi40DBSalesOrderLine.modifiedTimestamp) &&
        Objects.equals(this.orderCode, oi40DBSalesOrderLine.orderCode) &&
        Objects.equals(this.orderType, oi40DBSalesOrderLine.orderType) &&
        Objects.equals(this.plannedDeliveryDate, oi40DBSalesOrderLine.plannedDeliveryDate) &&
        Objects.equals(this.plantCode, oi40DBSalesOrderLine.plantCode) &&
        Objects.equals(this.productCode, oi40DBSalesOrderLine.productCode) &&
        Objects.equals(this.removed, oi40DBSalesOrderLine.removed) &&
        Objects.equals(this.residualQty, oi40DBSalesOrderLine.residualQty) &&
        Objects.equals(this.totalQty, oi40DBSalesOrderLine.totalQty) &&
        Objects.equals(this.warehouseCode, oi40DBSalesOrderLine.warehouseCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(askedDeliveryDate, attributesMap, code, color, completedQty, customPriority, departmentCode, description, integrationTs, lineStatus, maxProductionDateConstraint, minProductionDateConstraint, modifiedTimestamp, orderCode, orderType, plannedDeliveryDate, plantCode, productCode, removed, residualQty, totalQty, warehouseCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBSalesOrderLine {\n");
    
    sb.append("    askedDeliveryDate: ").append(toIndentedString(askedDeliveryDate)).append("\n");
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    color: ").append(toIndentedString(color)).append("\n");
    sb.append("    completedQty: ").append(toIndentedString(completedQty)).append("\n");
    sb.append("    customPriority: ").append(toIndentedString(customPriority)).append("\n");
    sb.append("    departmentCode: ").append(toIndentedString(departmentCode)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    lineStatus: ").append(toIndentedString(lineStatus)).append("\n");
    sb.append("    maxProductionDateConstraint: ").append(toIndentedString(maxProductionDateConstraint)).append("\n");
    sb.append("    minProductionDateConstraint: ").append(toIndentedString(minProductionDateConstraint)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    orderCode: ").append(toIndentedString(orderCode)).append("\n");
    sb.append("    orderType: ").append(toIndentedString(orderType)).append("\n");
    sb.append("    plannedDeliveryDate: ").append(toIndentedString(plannedDeliveryDate)).append("\n");
    sb.append("    plantCode: ").append(toIndentedString(plantCode)).append("\n");
    sb.append("    productCode: ").append(toIndentedString(productCode)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    residualQty: ").append(toIndentedString(residualQty)).append("\n");
    sb.append("    totalQty: ").append(toIndentedString(totalQty)).append("\n");
    sb.append("    warehouseCode: ").append(toIndentedString(warehouseCode)).append("\n");
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

