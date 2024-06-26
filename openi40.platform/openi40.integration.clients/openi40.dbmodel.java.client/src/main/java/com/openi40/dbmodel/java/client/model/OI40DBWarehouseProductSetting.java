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
 * OI40DBWarehouseProductSetting
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBWarehouseProductSetting {
  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("averageMinPurchaseQty")
  private Double averageMinPurchaseQty = null;

  @JsonProperty("averageProductionDays")
  private Integer averageProductionDays = null;

  @JsonProperty("averageleadTimeDays")
  private Integer averageleadTimeDays = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("produceAccordingToReorderLevel")
  private Boolean produceAccordingToReorderLevel = null;

  @JsonProperty("produced")
  private Boolean produced = null;

  @JsonProperty("productCode")
  private String productCode = null;

  @JsonProperty("productionBySchedulerEnabled")
  private Boolean productionBySchedulerEnabled = null;

  @JsonProperty("purchaseAccordingToReorderLevel")
  private Boolean purchaseAccordingToReorderLevel = null;

  @JsonProperty("purchaseBySchedulerEnabled")
  private Boolean purchaseBySchedulerEnabled = null;

  @JsonProperty("purchased")
  private Boolean purchased = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("securityStock")
  private Double securityStock = null;

  @JsonProperty("warehouseCode")
  private String warehouseCode = null;

  public OI40DBWarehouseProductSetting attributesMap(Object attributesMap) {
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

  public OI40DBWarehouseProductSetting averageMinPurchaseQty(Double averageMinPurchaseQty) {
    this.averageMinPurchaseQty = averageMinPurchaseQty;
    return this;
  }

   /**
   * Get averageMinPurchaseQty
   * @return averageMinPurchaseQty
  **/
  @ApiModelProperty(value = "")
  public Double getAverageMinPurchaseQty() {
    return averageMinPurchaseQty;
  }

  public void setAverageMinPurchaseQty(Double averageMinPurchaseQty) {
    this.averageMinPurchaseQty = averageMinPurchaseQty;
  }

  public OI40DBWarehouseProductSetting averageProductionDays(Integer averageProductionDays) {
    this.averageProductionDays = averageProductionDays;
    return this;
  }

   /**
   * Get averageProductionDays
   * @return averageProductionDays
  **/
  @ApiModelProperty(value = "")
  public Integer getAverageProductionDays() {
    return averageProductionDays;
  }

  public void setAverageProductionDays(Integer averageProductionDays) {
    this.averageProductionDays = averageProductionDays;
  }

  public OI40DBWarehouseProductSetting averageleadTimeDays(Integer averageleadTimeDays) {
    this.averageleadTimeDays = averageleadTimeDays;
    return this;
  }

   /**
   * Get averageleadTimeDays
   * @return averageleadTimeDays
  **/
  @ApiModelProperty(value = "")
  public Integer getAverageleadTimeDays() {
    return averageleadTimeDays;
  }

  public void setAverageleadTimeDays(Integer averageleadTimeDays) {
    this.averageleadTimeDays = averageleadTimeDays;
  }

  public OI40DBWarehouseProductSetting code(String code) {
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

  public OI40DBWarehouseProductSetting description(String description) {
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

  public OI40DBWarehouseProductSetting integrationTs(Date integrationTs) {
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

  public OI40DBWarehouseProductSetting modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBWarehouseProductSetting produceAccordingToReorderLevel(Boolean produceAccordingToReorderLevel) {
    this.produceAccordingToReorderLevel = produceAccordingToReorderLevel;
    return this;
  }

   /**
   * Get produceAccordingToReorderLevel
   * @return produceAccordingToReorderLevel
  **/
  @ApiModelProperty(value = "")
  public Boolean isProduceAccordingToReorderLevel() {
    return produceAccordingToReorderLevel;
  }

  public void setProduceAccordingToReorderLevel(Boolean produceAccordingToReorderLevel) {
    this.produceAccordingToReorderLevel = produceAccordingToReorderLevel;
  }

  public OI40DBWarehouseProductSetting produced(Boolean produced) {
    this.produced = produced;
    return this;
  }

   /**
   * Get produced
   * @return produced
  **/
  @ApiModelProperty(value = "")
  public Boolean isProduced() {
    return produced;
  }

  public void setProduced(Boolean produced) {
    this.produced = produced;
  }

  public OI40DBWarehouseProductSetting productCode(String productCode) {
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

  public OI40DBWarehouseProductSetting productionBySchedulerEnabled(Boolean productionBySchedulerEnabled) {
    this.productionBySchedulerEnabled = productionBySchedulerEnabled;
    return this;
  }

   /**
   * Get productionBySchedulerEnabled
   * @return productionBySchedulerEnabled
  **/
  @ApiModelProperty(value = "")
  public Boolean isProductionBySchedulerEnabled() {
    return productionBySchedulerEnabled;
  }

  public void setProductionBySchedulerEnabled(Boolean productionBySchedulerEnabled) {
    this.productionBySchedulerEnabled = productionBySchedulerEnabled;
  }

  public OI40DBWarehouseProductSetting purchaseAccordingToReorderLevel(Boolean purchaseAccordingToReorderLevel) {
    this.purchaseAccordingToReorderLevel = purchaseAccordingToReorderLevel;
    return this;
  }

   /**
   * Get purchaseAccordingToReorderLevel
   * @return purchaseAccordingToReorderLevel
  **/
  @ApiModelProperty(value = "")
  public Boolean isPurchaseAccordingToReorderLevel() {
    return purchaseAccordingToReorderLevel;
  }

  public void setPurchaseAccordingToReorderLevel(Boolean purchaseAccordingToReorderLevel) {
    this.purchaseAccordingToReorderLevel = purchaseAccordingToReorderLevel;
  }

  public OI40DBWarehouseProductSetting purchaseBySchedulerEnabled(Boolean purchaseBySchedulerEnabled) {
    this.purchaseBySchedulerEnabled = purchaseBySchedulerEnabled;
    return this;
  }

   /**
   * Get purchaseBySchedulerEnabled
   * @return purchaseBySchedulerEnabled
  **/
  @ApiModelProperty(value = "")
  public Boolean isPurchaseBySchedulerEnabled() {
    return purchaseBySchedulerEnabled;
  }

  public void setPurchaseBySchedulerEnabled(Boolean purchaseBySchedulerEnabled) {
    this.purchaseBySchedulerEnabled = purchaseBySchedulerEnabled;
  }

  public OI40DBWarehouseProductSetting purchased(Boolean purchased) {
    this.purchased = purchased;
    return this;
  }

   /**
   * Get purchased
   * @return purchased
  **/
  @ApiModelProperty(value = "")
  public Boolean isPurchased() {
    return purchased;
  }

  public void setPurchased(Boolean purchased) {
    this.purchased = purchased;
  }

  public OI40DBWarehouseProductSetting removed(Boolean removed) {
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

  public OI40DBWarehouseProductSetting securityStock(Double securityStock) {
    this.securityStock = securityStock;
    return this;
  }

   /**
   * Get securityStock
   * @return securityStock
  **/
  @ApiModelProperty(value = "")
  public Double getSecurityStock() {
    return securityStock;
  }

  public void setSecurityStock(Double securityStock) {
    this.securityStock = securityStock;
  }

  public OI40DBWarehouseProductSetting warehouseCode(String warehouseCode) {
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
    OI40DBWarehouseProductSetting oi40DBWarehouseProductSetting = (OI40DBWarehouseProductSetting) o;
    return Objects.equals(this.attributesMap, oi40DBWarehouseProductSetting.attributesMap) &&
        Objects.equals(this.averageMinPurchaseQty, oi40DBWarehouseProductSetting.averageMinPurchaseQty) &&
        Objects.equals(this.averageProductionDays, oi40DBWarehouseProductSetting.averageProductionDays) &&
        Objects.equals(this.averageleadTimeDays, oi40DBWarehouseProductSetting.averageleadTimeDays) &&
        Objects.equals(this.code, oi40DBWarehouseProductSetting.code) &&
        Objects.equals(this.description, oi40DBWarehouseProductSetting.description) &&
        Objects.equals(this.integrationTs, oi40DBWarehouseProductSetting.integrationTs) &&
        Objects.equals(this.modifiedTimestamp, oi40DBWarehouseProductSetting.modifiedTimestamp) &&
        Objects.equals(this.produceAccordingToReorderLevel, oi40DBWarehouseProductSetting.produceAccordingToReorderLevel) &&
        Objects.equals(this.produced, oi40DBWarehouseProductSetting.produced) &&
        Objects.equals(this.productCode, oi40DBWarehouseProductSetting.productCode) &&
        Objects.equals(this.productionBySchedulerEnabled, oi40DBWarehouseProductSetting.productionBySchedulerEnabled) &&
        Objects.equals(this.purchaseAccordingToReorderLevel, oi40DBWarehouseProductSetting.purchaseAccordingToReorderLevel) &&
        Objects.equals(this.purchaseBySchedulerEnabled, oi40DBWarehouseProductSetting.purchaseBySchedulerEnabled) &&
        Objects.equals(this.purchased, oi40DBWarehouseProductSetting.purchased) &&
        Objects.equals(this.removed, oi40DBWarehouseProductSetting.removed) &&
        Objects.equals(this.securityStock, oi40DBWarehouseProductSetting.securityStock) &&
        Objects.equals(this.warehouseCode, oi40DBWarehouseProductSetting.warehouseCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, averageMinPurchaseQty, averageProductionDays, averageleadTimeDays, code, description, integrationTs, modifiedTimestamp, produceAccordingToReorderLevel, produced, productCode, productionBySchedulerEnabled, purchaseAccordingToReorderLevel, purchaseBySchedulerEnabled, purchased, removed, securityStock, warehouseCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBWarehouseProductSetting {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    averageMinPurchaseQty: ").append(toIndentedString(averageMinPurchaseQty)).append("\n");
    sb.append("    averageProductionDays: ").append(toIndentedString(averageProductionDays)).append("\n");
    sb.append("    averageleadTimeDays: ").append(toIndentedString(averageleadTimeDays)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    produceAccordingToReorderLevel: ").append(toIndentedString(produceAccordingToReorderLevel)).append("\n");
    sb.append("    produced: ").append(toIndentedString(produced)).append("\n");
    sb.append("    productCode: ").append(toIndentedString(productCode)).append("\n");
    sb.append("    productionBySchedulerEnabled: ").append(toIndentedString(productionBySchedulerEnabled)).append("\n");
    sb.append("    purchaseAccordingToReorderLevel: ").append(toIndentedString(purchaseAccordingToReorderLevel)).append("\n");
    sb.append("    purchaseBySchedulerEnabled: ").append(toIndentedString(purchaseBySchedulerEnabled)).append("\n");
    sb.append("    purchased: ").append(toIndentedString(purchased)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    securityStock: ").append(toIndentedString(securityStock)).append("\n");
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

