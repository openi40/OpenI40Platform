package com.openi40.platform.iomessages.controllers;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.platform.iomessages.spooler.services.IMSGSpoolingService;
import com.openi40.platform.iomessages.spooler.services.MSGSpoolerException;
import com.openi40.scheduler.iomessages.AbortTaskIOMessage;
import com.openi40.scheduler.iomessages.AbstractBaseIOMessage;
import com.openi40.scheduler.iomessages.EndSetupIOMessage;
import com.openi40.scheduler.iomessages.EndWorkIOMessage;
import com.openi40.scheduler.iomessages.OutOfOrderMachineIOMessage;
import com.openi40.scheduler.iomessages.PauseTaskIOMessage;
import com.openi40.scheduler.iomessages.StartSetupIOMessage;
import com.openi40.scheduler.iomessages.StartWorkIOMessage;
import com.openi40.scheduler.iomessages.TaskProductionUpdateIOMessage;
import com.openi40.scheduler.iomessages.UnderMantainmentMachineIOMessage;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("ioMessages/IOMessagesSpoolingController")
@Api
public class IOMessagesSpoolingController {
	static Logger LOGGER = LoggerFactory.getLogger(IOMessagesSpoolingController.class);
	IMSGSpoolingService spoolingService;

	public IOMessagesSpoolingController(@Autowired IMSGSpoolingService spoolingService) {
		this.spoolingService = spoolingService;
	}

	private void spoolMessage(String dataSourceName, String dataSetName, String dataSetVariant,
			AbstractBaseIOMessage message) throws MSGSpoolerException {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Begin spoolMessage(" + dataSourceName + "," + dataSetName + "," + dataSetVariant + ","
					+ message + ")");
		}
		try {
			this.spoolingService.add(dataSourceName, dataSetName, dataSetVariant, message);
		} catch (Throwable th) {
			String errorMsg = "spoolMessage(" + dataSourceName + "," + dataSetName + "," + dataSetVariant + ","
					+ message + ")";
			LOGGER.error(errorMsg, th);
			throw new MSGSpoolerException(errorMsg, th);
		}
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("End spoolMessage(" + dataSourceName + "," + dataSetName + "," + dataSetVariant + "," + message
					+ ")");
		}
	}

	@PostMapping("spoolStartSetupMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolStartSetupMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody StartSetupIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolEndSetupMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolEndSetupMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody EndSetupIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolStartWorkMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolStartWorkMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody StartWorkIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolEndWorkMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolEndWorkMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody EndWorkIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolOutOfOrderMachineIOMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolOutOfOrderMachineIOMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody OutOfOrderMachineIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolPauseTaskIOMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolPauseTaskIOMessag(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody PauseTaskIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolTaskProductionUpdateIOMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolTaskProductionUpdateIOMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody TaskProductionUpdateIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolUnderMantainmentMachineIOMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolUnderMantainmentMachineIOMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody UnderMantainmentMachineIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

	@PostMapping("spoolAbortTaskIOMessage/{dataSourceName}/{dataSetName}/{dataSetVariant}/")
	public void spoolAbortTaskIOMessage(@NotNull @PathVariable("dataSourceName") String dataSourceName,
			@NotNull @PathVariable("dataSetName") String dataSetName,
			@NotNull @PathVariable("dataSetVariant") String dataSetVariant,
			@NotNull @RequestBody AbortTaskIOMessage message) throws MSGSpoolerException {
		this.spoolMessage(dataSourceName, dataSetName, dataSetVariant, message);
	}

}
