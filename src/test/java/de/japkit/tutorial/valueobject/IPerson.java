package de.japkit.tutorial.valueobject;

import java.util.Date;
import java.util.List;

@ValueObject
public interface IPerson {
	/** Returns this person's full (English) name. */
	String getName();

	/** Returns this person's age in years, rounded down. */
	int getAge();
	
	/** Returns this person's birthday. */
	Date getBirthday();

	/** Returns this person's telephone numbers. */
	List<String> getTelephoneNumbers();
	
	/** Builder of {@link IPerson} instances. */
	public class Builder extends Person.Builder {
	}
}