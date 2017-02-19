package de.japkit.tutorial.valueobject;

import java.util.Date;
import java.util.List;
import java.util.Set;

import de.japkit.metaannotations.Matcher;

/**
 * Common functions for code templates for domain classes.
 *
 */
public class DomainLibrary {
	@Matcher(type = Date.class)
	public @interface isDate {
	}

	@Matcher(type = List.class)
	public @interface isList {
	}

	@Matcher(type = Set.class)
	public @interface isSet {
	}

}
