package by.senla.cvs.module.init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.processor.CsvReader;
import by.senla.cvs.module.processor.CsvWriter;
import by.senla.cvs.module.processor.Parsing;

public class Init {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, ClassNotFoundException {

		createFolder();

		ClassTwo classTwo = new ClassTwo();
		classTwo.setId(3);
		classTwo.setName("Good name");

		ClassOne classOne1 = new ClassOne();
		classOne1.setSomeField("Value1");
		classOne1.setSomeInt(51557);
		classOne1.setId(5);
		classOne1.setSomeObject(classTwo);

		ClassOne classOne2 = new ClassOne();
		classOne2.setSomeField("Value2");
		classOne2.setSomeInt(48456);
		classOne2.setId(88);
		classOne2.setSomeObject(classTwo);

		ClassThree classThree = new ClassThree();

		List<Object> list = new ArrayList<>();
		list.add(classOne1);
		list.add(classOne2);
		list.add(classTwo);
		list.add(classThree);

		List<Object> annObjects = new ArrayList<>();

		for (Object someObject : list) {
			if (someObject.getClass().isAnnotationPresent(CsvEntity.class)) {
				annObjects.add(someObject);
			}
		}

		File folder = new File("./data/");
		CsvWriter writer = new CsvWriter();
		CsvReader reader = new CsvReader();
		Parsing parsing = new Parsing();

		writer.writeToCsv(annObjects);
		List<Object> good = parsing.parseToEntity(reader.readFromCsv(folder));

		System.out.println(good.size());
		for (Object object : good) {
			String fieldName = null;
			Object fieldValue = null;

			Field[] fields = object.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				fieldName = field.getName();
				fieldValue = field.get(object);
				System.out.println(object.getClass().getSimpleName() + " " + fieldName + ";" + fieldValue);
			}
		}
	}

	private static void createFolder() {
		File folder = new File("./data");
		if (!folder.isDirectory()) {
			folder.mkdir();
		}
	}
}
