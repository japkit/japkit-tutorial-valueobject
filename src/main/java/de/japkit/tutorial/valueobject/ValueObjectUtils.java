package de.japkit.tutorial.valueobject;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;
import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Utilities for value objects.
 */
public abstract class ValueObjectUtils {
	private ValueObjectUtils() {
	}

	/**
	 * Defensive copy for Date. Not necessary for newer Date-Time-API...
	 *
	 * @param date the Date
	 * @return the copy
	 */
	public static Date copyDate(Date date) {
		return date == null ? null : new Date(date.getTime());
	}

	/**
	 * Immutable copy of a list.
	 *
	 * @param the list
	 * @param the type of list elements
	 * @return the copy
	 */
	public static <T> List<T> immutableCopyList(List<T> list) {
		return (list.isEmpty() || list == null) ? emptyList() : unmodifiableList(new ArrayList<>(list));
	}

	/**
	 * Immutable copy of a set.
	 *
	 * @param the set
	 * @param the type of set elements
	 * @return the copy
	 */
	public static <T> Set<T> immutableCopySet(Set<T> set) {
		return (set.isEmpty() || set == null) ? emptySet() : unmodifiableSet(new HashSet<>(set));
	}

}
