package de.japkit.tutorial.valueobject;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import de.japkit.metaannotations.Trigger;

@Trigger(template = ValueObjectTemplate.class)
@Target(ElementType.TYPE)
public @interface ValueObject {
	boolean shadow() default false;
}
