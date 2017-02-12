package de.japkit.tutorial.valueobject;

import javax.lang.model.element.Modifier;

import de.japkit.functions.SrcInterface;
import de.japkit.functions.SrcType;
import de.japkit.metaannotations.Clazz;
import de.japkit.metaannotations.Field;
import de.japkit.metaannotations.Getter;
import de.japkit.metaannotations.Setter;

@Clazz(namePrefixToRemove="I", nameSuffixToAppend="")
public class ValueObjectTemplate implements SrcInterface {
	@Field(src = "#{src.properties}",
			getter = @Getter,
			setter = @Setter(modifiers=Modifier.PRIVATE))
	private SrcType $name$;
}
