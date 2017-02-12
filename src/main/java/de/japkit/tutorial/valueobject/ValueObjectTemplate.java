package de.japkit.tutorial.valueobject;

import de.japkit.functions.SrcType;
import de.japkit.metaannotations.Clazz;
import de.japkit.metaannotations.Field;

@Clazz(namePrefixToRemove="I", nameSuffixToAppend="")
public class ValueObjectTemplate {
	@Field(src = "#{src.properties}")
	private SrcType $name$;
}
