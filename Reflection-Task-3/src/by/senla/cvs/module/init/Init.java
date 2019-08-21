package by.senla.cvs.module.init;

import java.io.IOException;

import by.senla.cvs.module.processor.AnnotationProcessor;

public class Init {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException {

		ClassOne classOne1 = new ClassOne();
		classOne1.setSomeField("Value number 1");
		classOne1.setSomeInt(515);
		classOne1.setId(5);

		ClassOne classOne2 = new ClassOne();
		classOne2.setSomeField("Value number 2");
		classOne2.setSomeInt(48);
		classOne2.setId(88);

		ClassTwo classTwo = new ClassTwo();
		ClassThree classThree = new ClassThree();

		AnnotationProcessor.inspectClass(classOne1);
		AnnotationProcessor.inspectClass(classOne2);
		AnnotationProcessor.inspectClass(classTwo);
		AnnotationProcessor.inspectClass(classThree);

	}
}
