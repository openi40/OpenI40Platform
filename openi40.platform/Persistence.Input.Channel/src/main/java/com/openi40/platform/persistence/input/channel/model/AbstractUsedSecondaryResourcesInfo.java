package com.openi40.platform.persistence.input.channel.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.openi40.scheduler.input.model.tasks.UsedSecondaryResourcesInfoInputDto;

@MappedSuperclass
public abstract class AbstractUsedSecondaryResourcesInfo extends UsedSecondaryResourcesInfoInputDto {
	static final String CODES_SEPARATOR = ";";
	String rc_codes = null;

	@Transient
	@Override
	public List<String> getUsedResourcesCodes() {
		return super.getUsedResourcesCodes();
	}

	@Transient
	@Override
	public void setUsedResourcesCodes(List<String> usedResourcesCodes) {
		this.rc_codes = concatenate(usedResourcesCodes);
		super.setUsedResourcesCodes(usedResourcesCodes);
	}

	public String getRc_codes() {
		return rc_codes;
	}

	public void setRc_codes(String rc_codes) {
		List<String> mappeds = parse(rc_codes);
		this.rc_codes = rc_codes;
		this.setUsedResourcesCodes(mappeds);
	}

	private String concatenate(List<String> usedResourcesCodes) {
		StringBuffer concatenation = new StringBuffer();
		if (usedResourcesCodes != null) {
			Iterator<String> iterator = usedResourcesCodes.iterator();
			while (iterator.hasNext()) {
				concatenation.append(iterator.next());
				if (iterator.hasNext()) {
					concatenation.append(CODES_SEPARATOR);
				}
			}
		}
		return concatenation.toString();
	}

	private List<String> parse(String rc_codes2) {
		List<String> outvector = new ArrayList<>();
		if (rc_codes2 != null) {
			StringTokenizer tokenizer = new StringTokenizer(rc_codes2, CODES_SEPARATOR);
			while (tokenizer.hasMoreTokens()) {
				outvector.add(tokenizer.nextToken());
			}
		}
		return outvector;
	}
}
