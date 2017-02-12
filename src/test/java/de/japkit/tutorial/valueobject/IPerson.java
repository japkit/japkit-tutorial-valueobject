package de.japkit.tutorial.valueobject;

//@ValueObject
public interface IPerson {
	/** Returns this person's full (English) name. */
	String getName();

	/** Returns this person's age in years, rounded down. */
	int getAge();

	/** Builder of {@link IPerson} instances. */
	//class Builder extends Person.Builder {
	//}
}