package de.japkit.tutorial.valueobject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import de.japkit.metaannotations.Trigger;

@Trigger(template = ValueObjectTemplate.class)
@Target(ElementType.TYPE)
public @interface ValueObject {
	boolean shadow() default false;
	
	/**
	 * JPA Embeddables have some limitations. Thus, it is sometimes necessary to
	 * implement a Value Object as JPA Entity.
	 *
	 * @return true, if the Value Object is implemented as JPA Entity.
	 */
	boolean asJpaEntity() default false;
}
