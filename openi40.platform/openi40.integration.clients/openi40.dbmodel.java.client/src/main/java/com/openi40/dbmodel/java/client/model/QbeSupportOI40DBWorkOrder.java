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
import com.openi40.dbmodel.java.client.model.OI40DBWorkOrder;
import com.openi40.dbmodel.java.client.model.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * QbeSupportOI40DBWorkOrder
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-09T15:18:53.580+01:00")
public class QbeSupportOI40DBWorkOrder {
  @JsonProperty("page")
  private PageInfo page = null;

  @JsonProperty("qbe")
  private OI40DBWorkOrder qbe = null;

  public QbeSupportOI40DBWorkOrder page(PageInfo page) {
    this.page = page;
    return this;
  }

   /**
   * Get page
   * @return page
  **/
  @ApiModelProperty(value = "")
  public PageInfo getPage() {
    return page;
  }

  public void setPage(PageInfo page) {
    this.page = page;
  }

  public QbeSupportOI40DBWorkOrder qbe(OI40DBWorkOrder qbe) {
    this.qbe = qbe;
    return this;
  }

   /**
   * Get qbe
   * @return qbe
  **/
  @ApiModelProperty(value = "")
  public OI40DBWorkOrder getQbe() {
    return qbe;
  }

  public void setQbe(OI40DBWorkOrder qbe) {
    this.qbe = qbe;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QbeSupportOI40DBWorkOrder qbeSupportOI40DBWorkOrder = (QbeSupportOI40DBWorkOrder) o;
    return Objects.equals(this.page, qbeSupportOI40DBWorkOrder.page) &&
        Objects.equals(this.qbe, qbeSupportOI40DBWorkOrder.qbe);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, qbe);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QbeSupportOI40DBWorkOrder {\n");
    
    sb.append("    page: ").append(toIndentedString(page)).append("\n");
    sb.append("    qbe: ").append(toIndentedString(qbe)).append("\n");
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

