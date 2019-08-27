package by.senla.cvs.module.processor;

import java.util.Collection;

public class SomeType<T> {
	
	public static <E> void test(Collection<E> collection) {
		for (E element : collection) {
			System.out.println(element);
		}
	}
}