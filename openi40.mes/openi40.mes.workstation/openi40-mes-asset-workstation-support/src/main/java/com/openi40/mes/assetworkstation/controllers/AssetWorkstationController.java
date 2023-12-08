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

import com.openi40.mes.assetworkstation.model.AssetWorkstationIdentifier;
import com.openi40.mes.assetworkstation.services.IAssetWorkstationIdentifierService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(value = "workstations/AssetWorkstationController")
public class AssetWorkstationController {
	@Autowired
	IAssetWorkstationIdentifierService assetWorkstationIdentifierService;
	

	@GetMapping(path = "recogniseWorkstation")
	public AssetWorkstationIdentifier recogniseWorkstation(HttpServletRequest request) {
		AssetWorkstationIdentifier identifier = null;
		String ip = request.getRemoteAddr();
		String host = request.getRemoteHost();
		return assetWorkstationIdentifierService.getWorkstationIdentifier(ip);
	}

	
	public static class AssetWorkstationIdentifierCreationOption extends AssetWorkstationIdentifier {
		boolean newAssetGroup = false;
		boolean defaultAssetGroup = false;
		public boolean isNewAssetGroup() {
			return newAssetGroup;
		}
		public void setNewAssetGroup(boolean newAssetGroup) {
			this.newAssetGroup = newAssetGroup;
		}
		public boolean isDefaultAssetGroup() {
			return defaultAssetGroup;
		}
		public void setDefaultAssetGroup(boolean defaultAssetGroup) {
			this.defaultAssetGroup = defaultAssetGroup;
		}
	}

	
	public static class AssetWorkstationIdentifierCreationSuggestion {
		private boolean alreadyExists = false;
		private boolean cannotCreate = false;
		private AssetWorkstationIdentifier existing = null;
		private List<AssetWorkstationIdentifierCreationOption> creationOptions = new ArrayList<AssetWorkstationIdentifierCreationOption>();
		public boolean isAlreadyExists() {
			return alreadyExists;
		}
		public void setAlreadyExists(boolean alreadyExists) {
			this.alreadyExists = alreadyExists;
		}
		public boolean isCannotCreate() {
			return cannotCreate;
		}
		public void setCannotCreate(boolean cannotCreate) {
			this.cannotCreate = cannotCreate;
		}
		public AssetWorkstationIdentifier getExisting() {
			return existing;
		}
		public void setExisting(AssetWorkstationIdentifier existing) {
			this.existing = existing;
		}
		public List<AssetWorkstationIdentifierCreationOption> getCreationOptions() {
			return creationOptions;
		}
		public void setCreationOptions(List<AssetWorkstationIdentifierCreationOption> creationOptions) {
			this.creationOptions = creationOptions;
		}
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
