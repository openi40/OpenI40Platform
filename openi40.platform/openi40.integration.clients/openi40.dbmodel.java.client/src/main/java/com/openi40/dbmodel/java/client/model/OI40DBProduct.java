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
 * OI40DBProduct
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-10T15:05:07.942+01:00")
public class OI40DBProduct {
  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("averageMinPurchaseQty")
  private Double averageMinPurchaseQty = null;

  @JsonProperty("canBeProducedByScheduler")
  private Boolean canBeProducedByScheduler = null;

  @JsonProperty("canBePurchasedByScheduler")
  private Boolean canBePurchasedByScheduler = null;

  @JsonProperty("class1fam1")
  private String class1fam1 = null;

  @JsonProperty("class1fam2")
  private String class1fam2 = null;

  @JsonProperty("class1fam3")
  private String class1fam3 = null;

  @JsonProperty("class2fam1")
  private String class2fam1 = null;

  @JsonProperty("class2fam2")
  private String class2fam2 = null;

  @JsonProperty("class2fam3")
  private String class2fam3 = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("leadTimeDays")
  private Integer leadTimeDays = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("mov2purchCoeff")
  private Double mov2purchCoeff = null;

  @JsonProperty("movUnity")
  private String movUnity = null;

  @JsonProperty("netWeight")
  private Double netWeight = null;

  @JsonProperty("purchUnity")
  private String purchUnity = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("reorderQty")
  private Double reorderQty = null;

  public OI40DBProduct attributesMap(Object attributesMap) {
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

  public OI40DBProduct averageMinPurchaseQty(Double averageMinPurchaseQty) {
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

  public OI40DBProduct canBeProducedByScheduler(Boolean canBeProducedByScheduler) {
    this.canBeProducedByScheduler = canBeProducedByScheduler;
    return this;
  }

   /**
   * Get canBeProducedByScheduler
   * @return canBeProducedByScheduler
  **/
  @ApiModelProperty(value = "")
  public Boolean isCanBeProducedByScheduler() {
    return canBeProducedByScheduler;
  }

  public void setCanBeProducedByScheduler(Boolean canBeProducedByScheduler) {
    this.canBeProducedByScheduler = canBeProducedByScheduler;
  }

  public OI40DBProduct canBePurchasedByScheduler(Boolean canBePurchasedByScheduler) {
    this.canBePurchasedByScheduler = canBePurchasedByScheduler;
    return this;
  }

   /**
   * Get canBePurchasedByScheduler
   * @return canBePurchasedByScheduler
  **/
  @ApiModelProperty(value = "")
  public Boolean isCanBePurchasedByScheduler() {
    return canBePurchasedByScheduler;
  }

  public void setCanBePurchasedByScheduler(Boolean canBePurchasedByScheduler) {
    this.canBePurchasedByScheduler = canBePurchasedByScheduler;
  }

  public OI40DBProduct class1fam1(String class1fam1) {
    this.class1fam1 = class1fam1;
    return this;
  }

   /**
   * Get class1fam1
   * @return class1fam1
  **/
  @ApiModelProperty(value = "")
  public String getClass1fam1() {
    return class1fam1;
  }

  public void setClass1fam1(String class1fam1) {
    this.class1fam1 = class1fam1;
  }

  public OI40DBProduct class1fam2(String class1fam2) {
    this.class1fam2 = class1fam2;
    return this;
  }

   /**
   * Get class1fam2
   * @return class1fam2
  **/
  @ApiModelProperty(value = "")
  public String getClass1fam2() {
    return class1fam2;
  }

  public void setClass1fam2(String class1fam2) {
    this.class1fam2 = class1fam2;
  }

  public OI40DBProduct class1fam3(String class1fam3) {
    this.class1fam3 = class1fam3;
    return this;
  }

   /**
   * Get class1fam3
   * @return class1fam3
  **/
  @ApiModelProperty(value = "")
  public String getClass1fam3() {
    return class1fam3;
  }

  public void setClass1fam3(String class1fam3) {
    this.class1fam3 = class1fam3;
  }

  public OI40DBProduct class2fam1(String class2fam1) {
    this.class2fam1 = class2fam1;
    return this;
  }

   /**
   * Get class2fam1
   * @return class2fam1
  **/
  @ApiModelProperty(value = "")
  public String getClass2fam1() {
    return class2fam1;
  }

  public void setClass2fam1(String class2fam1) {
    this.class2fam1 = class2fam1;
  }

  public OI40DBProduct class2fam2(String class2fam2) {
    this.class2fam2 = class2fam2;
    return this;
  }

   /**
   * Get class2fam2
   * @return class2fam2
  **/
  @ApiModelProperty(value = "")
  public String getClass2fam2() {
    return class2fam2;
  }

  public void setClass2fam2(String class2fam2) {
    this.class2fam2 = class2fam2;
  }

  public OI40DBProduct class2fam3(String class2fam3) {
    this.class2fam3 = class2fam3;
    return this;
  }

   /**
   * Get class2fam3
   * @return class2fam3
  **/
  @ApiModelProperty(value = "")
  public String getClass2fam3() {
    return class2fam3;
  }

  public void setClass2fam3(String class2fam3) {
    this.class2fam3 = class2fam3;
  }

  public OI40DBProduct code(String code) {
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

  public OI40DBProduct description(String description) {
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

  public OI40DBProduct integrationTs(Date integrationTs) {
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

  public OI40DBProduct leadTimeDays(Integer leadTimeDays) {
    this.leadTimeDays = leadTimeDays;
    return this;
  }

   /**
   * Get leadTimeDays
   * @return leadTimeDays
  **/
  @ApiModelProperty(value = "")
  public Integer getLeadTimeDays() {
    return leadTimeDays;
  }

  public void setLeadTimeDays(Integer leadTimeDays) {
    this.leadTimeDays = leadTimeDays;
  }

  public OI40DBProduct modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBProduct mov2purchCoeff(Double mov2purchCoeff) {
    this.mov2purchCoeff = mov2purchCoeff;
    return this;
  }

   /**
   * Get mov2purchCoeff
   * @return mov2purchCoeff
  **/
  @ApiModelProperty(value = "")
  public Double getMov2purchCoeff() {
    return mov2purchCoeff;
  }

  public void setMov2purchCoeff(Double mov2purchCoeff) {
    this.mov2purchCoeff = mov2purchCoeff;
  }

  public OI40DBProduct movUnity(String movUnity) {
    this.movUnity = movUnity;
    return this;
  }

   /**
   * Get movUnity
   * @return movUnity
  **/
  @ApiModelProperty(value = "")
  public String getMovUnity() {
    return movUnity;
  }

  public void setMovUnity(String movUnity) {
    this.movUnity = movUnity;
  }

  public OI40DBProduct netWeight(Double netWeight) {
    this.netWeight = netWeight;
    return this;
  }

   /**
   * Get netWeight
   * @return netWeight
  **/
  @ApiModelProperty(value = "")
  public Double getNetWeight() {
    return netWeight;
  }

  public void setNetWeight(Double netWeight) {
    this.netWeight = netWeight;
  }

  public OI40DBProduct purchUnity(String purchUnity) {
    this.purchUnity = purchUnity;
    return this;
  }

   /**
   * Get purchUnity
   * @return purchUnity
  **/
  @ApiModelProperty(value = "")
  public String getPurchUnity() {
    return purchUnity;
  }

  public void setPurchUnity(String purchUnity) {
    this.purchUnity = purchUnity;
  }

  public OI40DBProduct removed(Boolean removed) {
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

  public OI40DBProduct reorderQty(Double reorderQty) {
    this.reorderQty = reorderQty;
    return this;
  }

   /**
   * Get reorderQty
   * @return reorderQty
  **/
  @ApiModelProperty(value = "")
  public Double getReorderQty() {
    return reorderQty;
  }

  public void setReorderQty(Double reorderQty) {
    this.reorderQty = reorderQty;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBProduct oi40DBProduct = (OI40DBProduct) o;
    return Objects.equals(this.attributesMap, oi40DBProduct.attributesMap) &&
        Objects.equals(this.averageMinPurchaseQty, oi40DBProduct.averageMinPurchaseQty) &&
        Objects.equals(this.canBeProducedByScheduler, oi40DBProduct.canBeProducedByScheduler) &&
        Objects.equals(this.canBePurchasedByScheduler, oi40DBProduct.canBePurchasedByScheduler) &&
        Objects.equals(this.class1fam1, oi40DBProduct.class1fam1) &&
        Objects.equals(this.class1fam2, oi40DBProduct.class1fam2) &&
        Objects.equals(this.class1fam3, oi40DBProduct.class1fam3) &&
        Objects.equals(this.class2fam1, oi40DBProduct.class2fam1) &&
        Objects.equals(this.class2fam2, oi40DBProduct.class2fam2) &&
        Objects.equals(this.class2fam3, oi40DBProduct.class2fam3) &&
        Objects.equals(this.code, oi40DBProduct.code) &&
        Objects.equals(this.description, oi40DBProduct.description) &&
        Objects.equals(this.integrationTs, oi40DBProduct.integrationTs) &&
        Objects.equals(this.leadTimeDays, oi40DBProduct.leadTimeDays) &&
        Objects.equals(this.modifiedTimestamp, oi40DBProduct.modifiedTimestamp) &&
        Objects.equals(this.mov2purchCoeff, oi40DBProduct.mov2purchCoeff) &&
        Objects.equals(this.movUnity, oi40DBProduct.movUnity) &&
        Objects.equals(this.netWeight, oi40DBProduct.netWeight) &&
        Objects.equals(this.purchUnity, oi40DBProduct.purchUnity) &&
        Objects.equals(this.removed, oi40DBProduct.removed) &&
        Objects.equals(this.reorderQty, oi40DBProduct.reorderQty);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, averageMinPurchaseQty, canBeProducedByScheduler, canBePurchasedByScheduler, class1fam1, class1fam2, class1fam3, class2fam1, class2fam2, class2fam3, code, description, integrationTs, leadTimeDays, modifiedTimestamp, mov2purchCoeff, movUnity, netWeight, purchUnity, removed, reorderQty);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBProduct {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    averageMinPurchaseQty: ").append(toIndentedString(averageMinPurchaseQty)).append("\n");
    sb.append("    canBeProducedByScheduler: ").append(toIndentedString(canBeProducedByScheduler)).append("\n");
    sb.append("    canBePurchasedByScheduler: ").append(toIndentedString(canBePurchasedByScheduler)).append("\n");
    sb.append("    class1fam1: ").append(toIndentedString(class1fam1)).append("\n");
    sb.append("    class1fam2: ").append(toIndentedString(class1fam2)).append("\n");
    sb.append("    class1fam3: ").append(toIndentedString(class1fam3)).append("\n");
    sb.append("    class2fam1: ").append(toIndentedString(class2fam1)).append("\n");
    sb.append("    class2fam2: ").append(toIndentedString(class2fam2)).append("\n");
    sb.append("    class2fam3: ").append(toIndentedString(class2fam3)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    leadTimeDays: ").append(toIndentedString(leadTimeDays)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    mov2purchCoeff: ").append(toIndentedString(mov2purchCoeff)).append("\n");
    sb.append("    movUnity: ").append(toIndentedString(movUnity)).append("\n");
    sb.append("    netWeight: ").append(toIndentedString(netWeight)).append("\n");
    sb.append("    purchUnity: ").append(toIndentedString(purchUnity)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    reorderQty: ").append(toIndentedString(reorderQty)).append("\n");
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

