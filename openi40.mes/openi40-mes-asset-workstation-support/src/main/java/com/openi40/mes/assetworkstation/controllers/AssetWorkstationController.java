package com.openi40.mes.assetworkstation.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openi40.mes.assetworkstation.model.AssetContextObject;
import com.openi40.mes.assetworkstation.model.AssetContextObjectTree;
import com.openi40.mes.assetworkstation.model.AssetContextType;
import com.openi40.mes.assetworkstation.model.AssetWorkstationIdentifier;
import com.openi40.mes.assetworkstation.model.ContextPosition;
import com.openi40.mes.assetworkstation.services.IAssetContextTreeFactory;
import com.openi40.mes.assetworkstation.services.IAssetWorkstationIdentifierService;

import io.swagger.annotations.Api;
import lombok.Data;

@Api
@RestController
@RequestMapping(value = "workstations/AssetWorkstationController")
public class AssetWorkstationController {
	@Autowired
	IAssetWorkstationIdentifierService assetWorkstationIdentifierService;
	@Autowired
	IAssetContextTreeFactory assetContextTreeFactory;

	public AssetWorkstationController() {

	}

	@GetMapping(path = "getContextRoots")
	public List<AssetContextObjectTree> getContextRoots() {
		return assetContextTreeFactory.getContextRoots();
	}

	@PostMapping(path = "getContextPositionByContextObject")
	public ContextPosition getContextPositionByContextObject(@RequestBody AssetContextObject contextObject) {
		return this.assetContextTreeFactory.findPosition(contextObject);
	}

	@PostMapping(path = "getContextPositionByWorkstationIdentifier")
	public ContextPosition getContextPositionByWorkstationIdentifier(HttpServletRequest request,
			@RequestBody AssetWorkstationIdentifier workStationId) {
		AssetContextObject contextObject = new AssetContextObject(
				AssetContextType.valueOf(workStationId.getParentObjectType()), workStationId.getParentObjectCode());
		return this.assetContextTreeFactory.findPosition(contextObject);
	}

	@Data
	public static class WorkstationContext {
		ContextPosition position = null;
		AssetWorkstationIdentifier identifier = null;
	}

	@GetMapping(path = "recogniseWorkstationContext")
	public WorkstationContext recogniseWorkstationContext(HttpServletRequest request) {
		AssetWorkstationIdentifier identifier = null;
		String ip = request.getRemoteAddr();
		String host = request.getRemoteHost();
		identifier = assetWorkstationIdentifierService.getWorkstationIdentifier(ip);
		WorkstationContext context = null;
		if (identifier != null) {
			context = new WorkstationContext();
			context.setIdentifier(identifier);
			AssetContextObject contextObject = new AssetContextObject(
					AssetContextType.valueOf(identifier.getParentObjectType()), identifier.getParentObjectCode());
			context.setPosition(this.assetContextTreeFactory.findPosition(contextObject));
		}

		return context;

	}

	@GetMapping(path = "recogniseWorkstation")
	public AssetWorkstationIdentifier recogniseWorkstation(HttpServletRequest request) {
		AssetWorkstationIdentifier identifier = null;
		String ip = request.getRemoteAddr();
		String host = request.getRemoteHost();
		return assetWorkstationIdentifierService.getWorkstationIdentifier(ip);
	}

	@Data
	public static class AssetWorkstationIdentifierCreationOption extends AssetWorkstationIdentifier {
		boolean newAssetGroup = false;
		boolean defaultAssetGroup = false;
	}

	@Data
	public static class AssetWorkstationIdentifierCreationSuggestion {
		private boolean alreadyExists = false;
		private boolean cannotCreate = false;
		private AssetWorkstationIdentifier existing = null;
		private List<AssetWorkstationIdentifierCreationOption> creationOptions = new ArrayList<AssetWorkstationIdentifierCreationOption>();
	}

	@GetMapping(path = "suggestNewWorkstation/{contextObjectType}/{contextObjectCode}")
	public AssetWorkstationIdentifierCreationSuggestion suggestNewWorkstation(HttpServletRequest request,
			@PathVariable("contextObjectType") String contextObjectType,
			@PathVariable("contextObjectCode") String contextObjectCode) {
		AssetWorkstationIdentifierCreationSuggestion suggestion = new AssetWorkstationIdentifierCreationSuggestion();

		String ip = request.getRemoteAddr();
		String host = request.getRemoteHost();
		AssetWorkstationIdentifier existing = assetWorkstationIdentifierService.getWorkstationIdentifier(ip);
		if (existing != null) {
			suggestion.setExisting(existing);
			suggestion.setAlreadyExists(true);
			suggestion.setCannotCreate(true);
		} else {

		}
		return suggestion;
	}

	@PostMapping(path = "createNewWorkstation")
	public AssetWorkstationIdentifier createNewWorkstation(
			@RequestBody AssetWorkstationIdentifierCreationOption suggestion) {
		return assetWorkstationIdentifierService.save(suggestion);
	}
}
