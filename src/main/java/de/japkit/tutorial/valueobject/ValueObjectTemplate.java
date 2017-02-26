package de.japkit.tutorial.valueobject;

import java.util.Collections;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Id;
import javax.persistence.TemporalType;

import de.japkit.annotations.RuntimeMetadata;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.ElementCollection_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.Embeddable_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.Embedded_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.Entity_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.Enumerated_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.ManyToMany_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.ManyToOne_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.OneToMany_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.OneToOne_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.OrderColumn_;
import de.japkit.annotationtemplates.JpaAnnotationTemplatesGen.Temporal_;
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
import de.japkit.tutorial.valueobject.DomainLibrary.isEmbeddable;
import de.japkit.tutorial.valueobject.DomainLibrary.isEntity;
import de.japkit.tutorial.valueobject.DomainLibrary.isEnum;
import de.japkit.tutorial.valueobject.DomainLibrary.isJpaBasicOrEmbeddable;
import de.japkit.tutorial.valueobject.DomainLibrary.isList;
import de.japkit.tutorial.valueobject.DomainLibrary.isMultiValued;
import de.japkit.tutorial.valueobject.DomainLibrary.isSet;
import de.japkit.tutorial.valueobject.DomainLibrary.isSingleValued;
import de.japkit.tutorial.valueobject.DomainLibrary.isValueObjectAsJpaEntity;

@Clazz(namePrefixToRemove="I", nameSuffixToAppend="", libraries = DomainLibrary.class)
@RuntimeMetadata
@Embeddable_(_cond = "#{!asJpaEntity}")
@Entity_(_cond = "#{asJpaEntity}")
public class ValueObjectTemplate implements SrcInterface {
	
	@Field(cond = "#{asJpaEntity}")
	@Id
	private Long id;

	@Template(src = "#{properties}")
	private class Properties {
		@Field
		@Embedded_(_condFun = { isEmbeddable.class, isSingleValued.class })
		@ElementCollection_(_condFun = { isJpaBasicOrEmbeddable.class, isMultiValued.class })
		@OrderColumn_(_condFun = isList.class)
		@Temporal_(_condFun = isDate.class, value = TemporalType.TIMESTAMP)
		@Enumerated_(_condFun = isEnum.class, value = EnumType.STRING)
		@OneToOne_(_condFun = { isValueObjectAsJpaEntity.class, isSingleValued.class }, cascade = CascadeType.ALL)
		@OneToMany_(_condFun = { isValueObjectAsJpaEntity.class, isMultiValued.class }, cascade = CascadeType.ALL, orphanRemoval = true)
		@ManyToOne_(_condFun = { isEntity.class, isSingleValued.class })
		@ManyToMany_(_condFun = { isEntity.class, isMultiValued.class })
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
	
	/**
	 * <ul>
	 * <li>japkit.bodyBeforeIteratorCode
	 * 
	 * <pre>
	 * if (!(obj instanceof #{valueObjectClass.code})) {
	 * 	return false;
	 * }
	 * #{valueObjectClass.code} other = (#{valueObjectClass.code}) obj;
	 * return
	 * </pre>
	 *  
	 * <li>japkit.bodyCode Objects.equals(#{name}, other.#{name})
	 * <li>japkit.bodyAfterIteratorCode ;
	 * </ul>
	 */
	@Method(imports = Objects.class,
			bodyIterator = "#{properties}",
			bodySeparator = " && ",
			bodyIndentAfterLinebreak = true)
	@Override
	public boolean equals(Object obj) {
		return true;
	}

	@Method(imports = Objects.class,
			bodyIterator = "#{properties}",
			bodyBeforeIteratorCode = "return Objects.hash(",
			bodyCode = "#{name}",
			bodySeparator = ", ",
			bodyLinebreak = false,
			bodyAfterIteratorCode = ");")
	@Override
	public int hashCode() {
		return 0;
	}
	
	/**
	 * @japkit.bodyBeforeIteratorCode return "#{valueObjectInterface.simpleName} {"+
	 * @japkit.bodyCode "#{name}=" + #{name} +
	 * @japkit.bodySeparator ", " +
	 * @japkit.bodyAfterIteratorCode "}";
	 */
	@Method(bodyIterator = "#{properties}",
			bodyIndentAfterLinebreak = true)
	@Override
	public String toString() {
		return "";
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
