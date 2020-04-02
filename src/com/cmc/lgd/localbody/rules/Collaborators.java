package com.cmc.lgd.localbody.rules;

import java.util.Collections;
import java.util.Map;

public class Collaborators { // NO_UCD (use default)

	private final Map<String, Object> collaborators;

	public Collaborators(Map<String, Object> collaborators) {
		super();
		this.collaborators = collaborators;
	}

	public Map<String, Object> getCollaborators() {
		return Collections.unmodifiableMap(collaborators);
	}
}
