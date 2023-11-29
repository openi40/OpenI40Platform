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
import com.openi40.dbmodel.java.client.model.OI40DBPartner;
import com.openi40.dbmodel.java.client.model.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * QbeSupportOI40DBPartner
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class QbeSupportOI40DBPartner {
  @JsonProperty("page")
  private PageInfo page = null;

  @JsonProperty("qbe")
  private OI40DBPartner qbe = null;

  public QbeSupportOI40DBPartner page(PageInfo page) {
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

  public QbeSupportOI40DBPartner qbe(OI40DBPartner qbe) {
    this.qbe = qbe;
    return this;
  }

   /**
   * Get qbe
   * @return qbe
  **/
  @ApiModelProperty(value = "")
  public OI40DBPartner getQbe() {
    return qbe;
  }

  public void setQbe(OI40DBPartner qbe) {
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
    QbeSupportOI40DBPartner qbeSupportOI40DBPartner = (QbeSupportOI40DBPartner) o;
    return Objects.equals(this.page, qbeSupportOI40DBPartner.page) &&
        Objects.equals(this.qbe, qbeSupportOI40DBPartner.qbe);
  }

  @Override
  public int hashCode() {
    return Objects.hash(page, qbe);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QbeSupportOI40DBPartner {\n");
    
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

