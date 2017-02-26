package de.japkit.tutorial.valueobject;

import static de.japkit.metaannotations.TypeCategory.ENUM;
import static de.japkit.metaannotations.TypeCategory.MATH;
import static de.japkit.metaannotations.TypeCategory.PRIMITIVE;
import static de.japkit.metaannotations.TypeCategory.STRING;
import static de.japkit.metaannotations.TypeCategory.TEMPORAL;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Embeddable;
import javax.persistence.Entity;

import de.japkit.metaannotations.And;
import de.japkit.metaannotations.Library;
import de.japkit.metaannotations.Matcher;
import de.japkit.metaannotations.Not;
import de.japkit.metaannotations.Or;

/**
 * Common functions for code templates for domain classes.
 *
 */
@Library(annotationImports = ValueObject.class)
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
	
	@Matcher(singleValueTypeAnnotations = Entity.class)
	public @interface isJpaEntity {
	}

	@Matcher(singleValueTypeAnnotations = Embeddable.class)
	public @interface isJpaEmbeddable {
	}

	@Matcher(singleValueTypeAnnotations = ValueObject.class)
	public @interface isValueObject {
	}

	@Matcher(singleValueTypeAnnotations = ValueObject.class, condition = "#{singleValueType.asElement().ValueObject.asJpaEntity == true}")
	public @interface isValueObjectAsJpaEntity {
	}

	@Matcher(singleValueTypeAnnotations = ValueObject.class, condition = "#{singleValueType.asElement().ValueObject.asJpaEntity != true}")
	public @interface isValueObjectAsEmbeddable {
	}

	/**
	 * JPA Basic type
	 */
	@Matcher(singleValueTypeCategory = { PRIMITIVE, MATH, STRING, TEMPORAL, ENUM })
	public @interface isJpaBasic {
	}

	@Matcher(singleValueTypeCategory = { ENUM })
	public @interface isEnum {
	}

	@Or({ isJpaBasic.class, isJpaEmbeddable.class, isValueObjectAsEmbeddable.class })
	public @interface isJpaBasicOrEmbeddable {
	}

	@Or({ isJpaEmbeddable.class, isValueObjectAsEmbeddable.class })
	public @interface isEmbeddable {
	}

	/**
	 * @ValueObject or hand-written @Embeddable.
	 */
	@Or({ isValueObject.class, isJpaEmbeddable.class })
	public @interface isValueObjectOrJpaEmbeddable {
	}

	@Not(isValueObject.class)
	public @interface isNotValueObject {
	}

	@And({ isJpaEntity.class, isNotValueObject.class })
	public @interface isEntity {
	}

	@Or({ isList.class, isSet.class })
	public @interface isMultiValued {
	}

	@Not(isMultiValued.class)
	public @interface isSingleValued {
	}

}
