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
import com.openi40.dbmodel.java.client.model.Time;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;

/**
 * OI40DBTimesheetMetaInfoWorkingTimeRule
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBTimesheetMetaInfoWorkingTimeRule {
  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("dayOfWeek")
  private Integer dayOfWeek = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("endTime")
  private Time endTime = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("startTime")
  private Time startTime = null;

  @JsonProperty("timesheetMetaCode")
  private String timesheetMetaCode = null;

  public OI40DBTimesheetMetaInfoWorkingTimeRule attributesMap(Object attributesMap) {
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

  public OI40DBTimesheetMetaInfoWorkingTimeRule code(String code) {
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

  public OI40DBTimesheetMetaInfoWorkingTimeRule dayOfWeek(Integer dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
    return this;
  }

   /**
   * Get dayOfWeek
   * @return dayOfWeek
  **/
  @ApiModelProperty(value = "")
  public Integer getDayOfWeek() {
    return dayOfWeek;
  }

  public void setDayOfWeek(Integer dayOfWeek) {
    this.dayOfWeek = dayOfWeek;
  }

  public OI40DBTimesheetMetaInfoWorkingTimeRule description(String description) {
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

  public OI40DBTimesheetMetaInfoWorkingTimeRule endTime(Time endTime) {
    this.endTime = endTime;
    return this;
  }

   /**
   * Get endTime
   * @return endTime
  **/
  @ApiModelProperty(value = "")
  public Time getEndTime() {
    return endTime;
  }

  public void setEndTime(Time endTime) {
    this.endTime = endTime;
  }

  public OI40DBTimesheetMetaInfoWorkingTimeRule integrationTs(Date integrationTs) {
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

  public OI40DBTimesheetMetaInfoWorkingTimeRule modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBTimesheetMetaInfoWorkingTimeRule removed(Boolean removed) {
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

  public OI40DBTimesheetMetaInfoWorkingTimeRule startTime(Time startTime) {
    this.startTime = startTime;
    return this;
  }

   /**
   * Get startTime
   * @return startTime
  **/
  @ApiModelProperty(value = "")
  public Time getStartTime() {
    return startTime;
  }

  public void setStartTime(Time startTime) {
    this.startTime = startTime;
  }

  public OI40DBTimesheetMetaInfoWorkingTimeRule timesheetMetaCode(String timesheetMetaCode) {
    this.timesheetMetaCode = timesheetMetaCode;
    return this;
  }

   /**
   * Get timesheetMetaCode
   * @return timesheetMetaCode
  **/
  @ApiModelProperty(value = "")
  public String getTimesheetMetaCode() {
    return timesheetMetaCode;
  }

  public void setTimesheetMetaCode(String timesheetMetaCode) {
    this.timesheetMetaCode = timesheetMetaCode;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBTimesheetMetaInfoWorkingTimeRule oi40DBTimesheetMetaInfoWorkingTimeRule = (OI40DBTimesheetMetaInfoWorkingTimeRule) o;
    return Objects.equals(this.attributesMap, oi40DBTimesheetMetaInfoWorkingTimeRule.attributesMap) &&
        Objects.equals(this.code, oi40DBTimesheetMetaInfoWorkingTimeRule.code) &&
        Objects.equals(this.dayOfWeek, oi40DBTimesheetMetaInfoWorkingTimeRule.dayOfWeek) &&
        Objects.equals(this.description, oi40DBTimesheetMetaInfoWorkingTimeRule.description) &&
        Objects.equals(this.endTime, oi40DBTimesheetMetaInfoWorkingTimeRule.endTime) &&
        Objects.equals(this.integrationTs, oi40DBTimesheetMetaInfoWorkingTimeRule.integrationTs) &&
        Objects.equals(this.modifiedTimestamp, oi40DBTimesheetMetaInfoWorkingTimeRule.modifiedTimestamp) &&
        Objects.equals(this.removed, oi40DBTimesheetMetaInfoWorkingTimeRule.removed) &&
        Objects.equals(this.startTime, oi40DBTimesheetMetaInfoWorkingTimeRule.startTime) &&
        Objects.equals(this.timesheetMetaCode, oi40DBTimesheetMetaInfoWorkingTimeRule.timesheetMetaCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(attributesMap, code, dayOfWeek, description, endTime, integrationTs, modifiedTimestamp, removed, startTime, timesheetMetaCode);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBTimesheetMetaInfoWorkingTimeRule {\n");
    
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    dayOfWeek: ").append(toIndentedString(dayOfWeek)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    endTime: ").append(toIndentedString(endTime)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    startTime: ").append(toIndentedString(startTime)).append("\n");
    sb.append("    timesheetMetaCode: ").append(toIndentedString(timesheetMetaCode)).append("\n");
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

