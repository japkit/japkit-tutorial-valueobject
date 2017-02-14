package de.japkit.tutorial.valueobject;

import javax.lang.model.element.Modifier;

import de.japkit.functions.SrcInterface;
import de.japkit.functions.SrcType;
import de.japkit.metaannotations.Clazz;
import de.japkit.metaannotations.Field;
import de.japkit.metaannotations.Getter;
import de.japkit.metaannotations.InnerClass;
import de.japkit.metaannotations.Method;
import de.japkit.metaannotations.Setter;
import de.japkit.metaannotations.Var;
import de.japkit.metaannotations.classselectors.GeneratedClass;

@Clazz(namePrefixToRemove="I", nameSuffixToAppend="")
public class ValueObjectTemplate implements SrcInterface {
	@Field(src = "#{src.properties}",
			getter = @Getter,
			setter = @Setter(modifiers=Modifier.PRIVATE))
	private SrcType $name$;

	@Var(fun = GeneratedClass.class)
	class ValueObjectClass {
	}

	@InnerClass
	static class Builder {
		@Field(src = "#{src.properties}",
				getter = @Getter,
				setter = @Setter(chain=true))
		private SrcType $name$;

		@Method(
				bodyIterator = "#{properties}",
				bodyBeforeIteratorCode = "#{valueObjectClass.code} vo = new #{valueObjectClass.code}();",
				bodyCode = "vo.#{setterName}(#{name});",
				bodyAfterIteratorCode = "return vo;",
				bodyLinebreak = true)
		public ValueObjectClass build() {
			return null;
		}

	}
}
