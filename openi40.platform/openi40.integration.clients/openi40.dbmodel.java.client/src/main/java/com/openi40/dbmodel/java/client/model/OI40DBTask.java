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
 * OI40DBTask
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2023-11-29T17:41:29.716+01:00")
public class OI40DBTask {
  @JsonProperty("askedDeliveryDateTime")
  private Date askedDeliveryDateTime = null;

  @JsonProperty("attributesMap")
  private Object attributesMap = null;

  @JsonProperty("code")
  private String code = null;

  @JsonProperty("customPriority")
  private Integer customPriority = null;

  @JsonProperty("cycleCode")
  private String cycleCode = null;

  @JsonProperty("description")
  private String description = null;

  @JsonProperty("endExecution")
  private Date endExecution = null;

  @JsonProperty("endPreparation")
  private Date endPreparation = null;

  @JsonProperty("equipmentSpecCode")
  private String equipmentSpecCode = null;

  @JsonProperty("forcedMachineCode")
  private String forcedMachineCode = null;

  @JsonProperty("integrationTs")
  private Date integrationTs = null;

  @JsonProperty("maxProductionDateConstraint")
  private Date maxProductionDateConstraint = null;

  @JsonProperty("minProductionDateConstraint")
  private Date minProductionDateConstraint = null;

  @JsonProperty("modifiedTimestamp")
  private Date modifiedTimestamp = null;

  @JsonProperty("operationCode")
  private String operationCode = null;

  @JsonProperty("predefinedMachineCode")
  private String predefinedMachineCode = null;

  @JsonProperty("qtyProduced")
  private Double qtyProduced = null;

  @JsonProperty("qtyTotal")
  private Double qtyTotal = null;

  @JsonProperty("removed")
  private Boolean removed = null;

  @JsonProperty("salesOrderLineCode")
  private String salesOrderLineCode = null;

  @JsonProperty("scheduledMachineCode")
  private String scheduledMachineCode = null;

  @JsonProperty("sequenceCode")
  private String sequenceCode = null;

  @JsonProperty("setupGroupCode")
  private String setupGroupCode = null;

  @JsonProperty("setupTime")
  private Double setupTime = null;

  @JsonProperty("startExecution")
  private Date startExecution = null;

  @JsonProperty("startPreparation")
  private Date startPreparation = null;

  @JsonProperty("successfullyScheduled")
  private Boolean successfullyScheduled = null;

  @JsonProperty("workCenterCode")
  private String workCenterCode = null;

  @JsonProperty("workOrderRootTask")
  private Boolean workOrderRootTask = null;

  @JsonProperty("workTime")
  private Double workTime = null;

  public OI40DBTask askedDeliveryDateTime(Date askedDeliveryDateTime) {
    this.askedDeliveryDateTime = askedDeliveryDateTime;
    return this;
  }

   /**
   * Get askedDeliveryDateTime
   * @return askedDeliveryDateTime
  **/
  @ApiModelProperty(value = "")
  public Date getAskedDeliveryDateTime() {
    return askedDeliveryDateTime;
  }

  public void setAskedDeliveryDateTime(Date askedDeliveryDateTime) {
    this.askedDeliveryDateTime = askedDeliveryDateTime;
  }

  public OI40DBTask attributesMap(Object attributesMap) {
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

  public OI40DBTask code(String code) {
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

  public OI40DBTask customPriority(Integer customPriority) {
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

  public OI40DBTask cycleCode(String cycleCode) {
    this.cycleCode = cycleCode;
    return this;
  }

   /**
   * Get cycleCode
   * @return cycleCode
  **/
  @ApiModelProperty(value = "")
  public String getCycleCode() {
    return cycleCode;
  }

  public void setCycleCode(String cycleCode) {
    this.cycleCode = cycleCode;
  }

  public OI40DBTask description(String description) {
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

  public OI40DBTask endExecution(Date endExecution) {
    this.endExecution = endExecution;
    return this;
  }

   /**
   * Get endExecution
   * @return endExecution
  **/
  @ApiModelProperty(value = "")
  public Date getEndExecution() {
    return endExecution;
  }

  public void setEndExecution(Date endExecution) {
    this.endExecution = endExecution;
  }

  public OI40DBTask endPreparation(Date endPreparation) {
    this.endPreparation = endPreparation;
    return this;
  }

   /**
   * Get endPreparation
   * @return endPreparation
  **/
  @ApiModelProperty(value = "")
  public Date getEndPreparation() {
    return endPreparation;
  }

  public void setEndPreparation(Date endPreparation) {
    this.endPreparation = endPreparation;
  }

  public OI40DBTask equipmentSpecCode(String equipmentSpecCode) {
    this.equipmentSpecCode = equipmentSpecCode;
    return this;
  }

   /**
   * Get equipmentSpecCode
   * @return equipmentSpecCode
  **/
  @ApiModelProperty(value = "")
  public String getEquipmentSpecCode() {
    return equipmentSpecCode;
  }

  public void setEquipmentSpecCode(String equipmentSpecCode) {
    this.equipmentSpecCode = equipmentSpecCode;
  }

  public OI40DBTask forcedMachineCode(String forcedMachineCode) {
    this.forcedMachineCode = forcedMachineCode;
    return this;
  }

   /**
   * Get forcedMachineCode
   * @return forcedMachineCode
  **/
  @ApiModelProperty(value = "")
  public String getForcedMachineCode() {
    return forcedMachineCode;
  }

  public void setForcedMachineCode(String forcedMachineCode) {
    this.forcedMachineCode = forcedMachineCode;
  }

  public OI40DBTask integrationTs(Date integrationTs) {
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

  public OI40DBTask maxProductionDateConstraint(Date maxProductionDateConstraint) {
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

  public OI40DBTask minProductionDateConstraint(Date minProductionDateConstraint) {
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

  public OI40DBTask modifiedTimestamp(Date modifiedTimestamp) {
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

  public OI40DBTask operationCode(String operationCode) {
    this.operationCode = operationCode;
    return this;
  }

   /**
   * Get operationCode
   * @return operationCode
  **/
  @ApiModelProperty(value = "")
  public String getOperationCode() {
    return operationCode;
  }

  public void setOperationCode(String operationCode) {
    this.operationCode = operationCode;
  }

  public OI40DBTask predefinedMachineCode(String predefinedMachineCode) {
    this.predefinedMachineCode = predefinedMachineCode;
    return this;
  }

   /**
   * Get predefinedMachineCode
   * @return predefinedMachineCode
  **/
  @ApiModelProperty(value = "")
  public String getPredefinedMachineCode() {
    return predefinedMachineCode;
  }

  public void setPredefinedMachineCode(String predefinedMachineCode) {
    this.predefinedMachineCode = predefinedMachineCode;
  }

  public OI40DBTask qtyProduced(Double qtyProduced) {
    this.qtyProduced = qtyProduced;
    return this;
  }

   /**
   * Get qtyProduced
   * @return qtyProduced
  **/
  @ApiModelProperty(value = "")
  public Double getQtyProduced() {
    return qtyProduced;
  }

  public void setQtyProduced(Double qtyProduced) {
    this.qtyProduced = qtyProduced;
  }

  public OI40DBTask qtyTotal(Double qtyTotal) {
    this.qtyTotal = qtyTotal;
    return this;
  }

   /**
   * Get qtyTotal
   * @return qtyTotal
  **/
  @ApiModelProperty(value = "")
  public Double getQtyTotal() {
    return qtyTotal;
  }

  public void setQtyTotal(Double qtyTotal) {
    this.qtyTotal = qtyTotal;
  }

  public OI40DBTask removed(Boolean removed) {
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

  public OI40DBTask salesOrderLineCode(String salesOrderLineCode) {
    this.salesOrderLineCode = salesOrderLineCode;
    return this;
  }

   /**
   * Get salesOrderLineCode
   * @return salesOrderLineCode
  **/
  @ApiModelProperty(value = "")
  public String getSalesOrderLineCode() {
    return salesOrderLineCode;
  }

  public void setSalesOrderLineCode(String salesOrderLineCode) {
    this.salesOrderLineCode = salesOrderLineCode;
  }

  public OI40DBTask scheduledMachineCode(String scheduledMachineCode) {
    this.scheduledMachineCode = scheduledMachineCode;
    return this;
  }

   /**
   * Get scheduledMachineCode
   * @return scheduledMachineCode
  **/
  @ApiModelProperty(value = "")
  public String getScheduledMachineCode() {
    return scheduledMachineCode;
  }

  public void setScheduledMachineCode(String scheduledMachineCode) {
    this.scheduledMachineCode = scheduledMachineCode;
  }

  public OI40DBTask sequenceCode(String sequenceCode) {
    this.sequenceCode = sequenceCode;
    return this;
  }

   /**
   * Get sequenceCode
   * @return sequenceCode
  **/
  @ApiModelProperty(value = "")
  public String getSequenceCode() {
    return sequenceCode;
  }

  public void setSequenceCode(String sequenceCode) {
    this.sequenceCode = sequenceCode;
  }

  public OI40DBTask setupGroupCode(String setupGroupCode) {
    this.setupGroupCode = setupGroupCode;
    return this;
  }

   /**
   * Get setupGroupCode
   * @return setupGroupCode
  **/
  @ApiModelProperty(value = "")
  public String getSetupGroupCode() {
    return setupGroupCode;
  }

  public void setSetupGroupCode(String setupGroupCode) {
    this.setupGroupCode = setupGroupCode;
  }

  public OI40DBTask setupTime(Double setupTime) {
    this.setupTime = setupTime;
    return this;
  }

   /**
   * Get setupTime
   * @return setupTime
  **/
  @ApiModelProperty(value = "")
  public Double getSetupTime() {
    return setupTime;
  }

  public void setSetupTime(Double setupTime) {
    this.setupTime = setupTime;
  }

  public OI40DBTask startExecution(Date startExecution) {
    this.startExecution = startExecution;
    return this;
  }

   /**
   * Get startExecution
   * @return startExecution
  **/
  @ApiModelProperty(value = "")
  public Date getStartExecution() {
    return startExecution;
  }

  public void setStartExecution(Date startExecution) {
    this.startExecution = startExecution;
  }

  public OI40DBTask startPreparation(Date startPreparation) {
    this.startPreparation = startPreparation;
    return this;
  }

   /**
   * Get startPreparation
   * @return startPreparation
  **/
  @ApiModelProperty(value = "")
  public Date getStartPreparation() {
    return startPreparation;
  }

  public void setStartPreparation(Date startPreparation) {
    this.startPreparation = startPreparation;
  }

  public OI40DBTask successfullyScheduled(Boolean successfullyScheduled) {
    this.successfullyScheduled = successfullyScheduled;
    return this;
  }

   /**
   * Get successfullyScheduled
   * @return successfullyScheduled
  **/
  @ApiModelProperty(value = "")
  public Boolean isSuccessfullyScheduled() {
    return successfullyScheduled;
  }

  public void setSuccessfullyScheduled(Boolean successfullyScheduled) {
    this.successfullyScheduled = successfullyScheduled;
  }

  public OI40DBTask workCenterCode(String workCenterCode) {
    this.workCenterCode = workCenterCode;
    return this;
  }

   /**
   * Get workCenterCode
   * @return workCenterCode
  **/
  @ApiModelProperty(value = "")
  public String getWorkCenterCode() {
    return workCenterCode;
  }

  public void setWorkCenterCode(String workCenterCode) {
    this.workCenterCode = workCenterCode;
  }

  public OI40DBTask workOrderRootTask(Boolean workOrderRootTask) {
    this.workOrderRootTask = workOrderRootTask;
    return this;
  }

   /**
   * Get workOrderRootTask
   * @return workOrderRootTask
  **/
  @ApiModelProperty(value = "")
  public Boolean isWorkOrderRootTask() {
    return workOrderRootTask;
  }

  public void setWorkOrderRootTask(Boolean workOrderRootTask) {
    this.workOrderRootTask = workOrderRootTask;
  }

  public OI40DBTask workTime(Double workTime) {
    this.workTime = workTime;
    return this;
  }

   /**
   * Get workTime
   * @return workTime
  **/
  @ApiModelProperty(value = "")
  public Double getWorkTime() {
    return workTime;
  }

  public void setWorkTime(Double workTime) {
    this.workTime = workTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OI40DBTask oi40DBTask = (OI40DBTask) o;
    return Objects.equals(this.askedDeliveryDateTime, oi40DBTask.askedDeliveryDateTime) &&
        Objects.equals(this.attributesMap, oi40DBTask.attributesMap) &&
        Objects.equals(this.code, oi40DBTask.code) &&
        Objects.equals(this.customPriority, oi40DBTask.customPriority) &&
        Objects.equals(this.cycleCode, oi40DBTask.cycleCode) &&
        Objects.equals(this.description, oi40DBTask.description) &&
        Objects.equals(this.endExecution, oi40DBTask.endExecution) &&
        Objects.equals(this.endPreparation, oi40DBTask.endPreparation) &&
        Objects.equals(this.equipmentSpecCode, oi40DBTask.equipmentSpecCode) &&
        Objects.equals(this.forcedMachineCode, oi40DBTask.forcedMachineCode) &&
        Objects.equals(this.integrationTs, oi40DBTask.integrationTs) &&
        Objects.equals(this.maxProductionDateConstraint, oi40DBTask.maxProductionDateConstraint) &&
        Objects.equals(this.minProductionDateConstraint, oi40DBTask.minProductionDateConstraint) &&
        Objects.equals(this.modifiedTimestamp, oi40DBTask.modifiedTimestamp) &&
        Objects.equals(this.operationCode, oi40DBTask.operationCode) &&
        Objects.equals(this.predefinedMachineCode, oi40DBTask.predefinedMachineCode) &&
        Objects.equals(this.qtyProduced, oi40DBTask.qtyProduced) &&
        Objects.equals(this.qtyTotal, oi40DBTask.qtyTotal) &&
        Objects.equals(this.removed, oi40DBTask.removed) &&
        Objects.equals(this.salesOrderLineCode, oi40DBTask.salesOrderLineCode) &&
        Objects.equals(this.scheduledMachineCode, oi40DBTask.scheduledMachineCode) &&
        Objects.equals(this.sequenceCode, oi40DBTask.sequenceCode) &&
        Objects.equals(this.setupGroupCode, oi40DBTask.setupGroupCode) &&
        Objects.equals(this.setupTime, oi40DBTask.setupTime) &&
        Objects.equals(this.startExecution, oi40DBTask.startExecution) &&
        Objects.equals(this.startPreparation, oi40DBTask.startPreparation) &&
        Objects.equals(this.successfullyScheduled, oi40DBTask.successfullyScheduled) &&
        Objects.equals(this.workCenterCode, oi40DBTask.workCenterCode) &&
        Objects.equals(this.workOrderRootTask, oi40DBTask.workOrderRootTask) &&
        Objects.equals(this.workTime, oi40DBTask.workTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(askedDeliveryDateTime, attributesMap, code, customPriority, cycleCode, description, endExecution, endPreparation, equipmentSpecCode, forcedMachineCode, integrationTs, maxProductionDateConstraint, minProductionDateConstraint, modifiedTimestamp, operationCode, predefinedMachineCode, qtyProduced, qtyTotal, removed, salesOrderLineCode, scheduledMachineCode, sequenceCode, setupGroupCode, setupTime, startExecution, startPreparation, successfullyScheduled, workCenterCode, workOrderRootTask, workTime);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OI40DBTask {\n");
    
    sb.append("    askedDeliveryDateTime: ").append(toIndentedString(askedDeliveryDateTime)).append("\n");
    sb.append("    attributesMap: ").append(toIndentedString(attributesMap)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    customPriority: ").append(toIndentedString(customPriority)).append("\n");
    sb.append("    cycleCode: ").append(toIndentedString(cycleCode)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    endExecution: ").append(toIndentedString(endExecution)).append("\n");
    sb.append("    endPreparation: ").append(toIndentedString(endPreparation)).append("\n");
    sb.append("    equipmentSpecCode: ").append(toIndentedString(equipmentSpecCode)).append("\n");
    sb.append("    forcedMachineCode: ").append(toIndentedString(forcedMachineCode)).append("\n");
    sb.append("    integrationTs: ").append(toIndentedString(integrationTs)).append("\n");
    sb.append("    maxProductionDateConstraint: ").append(toIndentedString(maxProductionDateConstraint)).append("\n");
    sb.append("    minProductionDateConstraint: ").append(toIndentedString(minProductionDateConstraint)).append("\n");
    sb.append("    modifiedTimestamp: ").append(toIndentedString(modifiedTimestamp)).append("\n");
    sb.append("    operationCode: ").append(toIndentedString(operationCode)).append("\n");
    sb.append("    predefinedMachineCode: ").append(toIndentedString(predefinedMachineCode)).append("\n");
    sb.append("    qtyProduced: ").append(toIndentedString(qtyProduced)).append("\n");
    sb.append("    qtyTotal: ").append(toIndentedString(qtyTotal)).append("\n");
    sb.append("    removed: ").append(toIndentedString(removed)).append("\n");
    sb.append("    salesOrderLineCode: ").append(toIndentedString(salesOrderLineCode)).append("\n");
    sb.append("    scheduledMachineCode: ").append(toIndentedString(scheduledMachineCode)).append("\n");
    sb.append("    sequenceCode: ").append(toIndentedString(sequenceCode)).append("\n");
    sb.append("    setupGroupCode: ").append(toIndentedString(setupGroupCode)).append("\n");
    sb.append("    setupTime: ").append(toIndentedString(setupTime)).append("\n");
    sb.append("    startExecution: ").append(toIndentedString(startExecution)).append("\n");
    sb.append("    startPreparation: ").append(toIndentedString(startPreparation)).append("\n");
    sb.append("    successfullyScheduled: ").append(toIndentedString(successfullyScheduled)).append("\n");
    sb.append("    workCenterCode: ").append(toIndentedString(workCenterCode)).append("\n");
    sb.append("    workOrderRootTask: ").append(toIndentedString(workOrderRootTask)).append("\n");
    sb.append("    workTime: ").append(toIndentedString(workTime)).append("\n");
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

