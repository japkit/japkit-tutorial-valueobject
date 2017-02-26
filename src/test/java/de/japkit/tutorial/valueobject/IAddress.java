package de.japkit.tutorial.valueobject;

@ValueObject
public interface IAddress {
	String getStreet();

	City getCity();
}
