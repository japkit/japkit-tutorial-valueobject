package de.japkit.tutorial.valueobject;

import java.util.Collections;

import de.japkit.functions.SrcInterface;
import de.japkit.functions.SrcType;
import de.japkit.metaannotations.Clazz;
import de.japkit.metaannotations.CodeFragment;
import de.japkit.metaannotations.Constructor;
import de.japkit.metaannotations.DefaultCase;
import de.japkit.metaannotations.Field;
import de.japkit.metaannotations.Getter;
import de.japkit.metaannotations.InnerClass;
import de.japkit.metaannotations.Method;
import de.japkit.metaannotations.Setter;
import de.japkit.metaannotations.Switch;
import de.japkit.metaannotations.Template;
import de.japkit.metaannotations.Var;
import de.japkit.metaannotations.classselectors.GeneratedClass;
import de.japkit.tutorial.valueobject.DomainLibrary.isDate;
import de.japkit.tutorial.valueobject.DomainLibrary.isList;
import de.japkit.tutorial.valueobject.DomainLibrary.isSet;

@Clazz(namePrefixToRemove="I", nameSuffixToAppend="")
public class ValueObjectTemplate implements SrcInterface {

	@Template(src = "#{properties}")
	private class Properties {
		@Field
		private SrcType $name$;

		@Method(bodyCode = "return #{getterRhs()};")
		public SrcType $getterName$() {
			return null;
		}

		@Method(bodyCode = "this.#{name} = #{setterRhs()};")
		private void $setterName$(SrcType $name$) {
		}

		@Switch
		class getterRhs {
			// Date is mutable. Create a defensive copy.
			@isDate
			@CodeFragment(imports = ValueObjectUtils.class, code = "ValueObjectUtils.copyDate(#{name})")
			String copyDate;

			// Persistent collections in Hibernate are mutable. Make them
			// unmodifiable.
			// Do not copy them, since we do not want to trigger lazy loading
			// here.
			@isList
			@CodeFragment(imports = Collections.class, code = "Collections.unmodifiableList(#{name})")
			String copyList;

			// Persistent collections in Hibernate are mutable. Make them
			// unmodifiable.
			// Do not copy them, since we do not want to trigger lazy loading
			// here.
			@isSet
			@CodeFragment(imports = Collections.class, code = "Collections.unmodifiableSet(#{name})")
			String copySet;

			@DefaultCase
			@CodeFragment(code = "#{name}")
			String deflt;
		}

		@Switch
		class setterRhs {
			@isDate
			@CodeFragment(imports = ValueObjectUtils.class, code = "ValueObjectUtils.copyDate(#{name})")
			String copyDate;

			@isList
			@CodeFragment(imports = ValueObjectUtils.class, code = "ValueObjectUtils.immutableCopyList(#{name})")
			String copyList;

			@isSet
			@CodeFragment(imports = ValueObjectUtils.class, code = "ValueObjectUtils.immutableCopySet(#{name})")
			String copySet;

			@DefaultCase
			@CodeFragment(code = "#{name}")
			String deflt;

		}
	}

	@Var(fun = GeneratedClass.class)
	class ValueObjectClass {
	}

	@Var(fun = SrcType.class)
	class ValueObjectInterface {
	}

	@InnerClass
	static class Builder {
		@Field(src = "#{src.properties}",
				getter = @Getter,
				setter = @Setter(chain=true))
		private SrcType $name$;

		@Constructor
		public Builder() {
		}

		@Constructor(bodyIterator = "#{properties}", bodyCode = "#{setterName}(vo.#{getterName}());", bodyLinebreak = true)
		public Builder(ValueObjectInterface vo) {
		}

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
