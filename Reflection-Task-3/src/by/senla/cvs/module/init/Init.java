package by.senla.cvs.module.init;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.processor.CsvReader;
import by.senla.cvs.module.processor.CsvWriter;

public class Init {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException,
			NoSuchFieldException, InstantiationException, ClassNotFoundException {

		createFolder();

		ClassTwo classTwo1 = new ClassTwo();
		classTwo1.setId(3);
		classTwo1.setName("Good name");
		classTwo1.setNumber(34);
		classTwo1.setCharacter('g');
		classTwo1.setFl(11.6F);

		ClassTwo classTwo2 = new ClassTwo();
		classTwo2.setId(5);
		classTwo2.setName("Bad name");
		classTwo2.setNumber(6578);
		classTwo2.setCharacter('u');
		classTwo2.setFl(15.3F);

		ClassOne classOne1 = new ClassOne();
		classOne1.setSomeField("Value1");
		classOne1.setSomeInt(51557);
		classOne1.setId(5);
		classOne1.setSomeObject(classTwo1);

		ClassOne classOne2 = new ClassOne();
		classOne2.setSomeField("Value2");
		classOne2.setSomeInt(48456);
		classOne2.setId(88);
		classOne2.setSomeObject(classTwo2);

		ClassThree classThree = new ClassThree();

		List<Object> list = new ArrayList<>();
		list.add(classOne1);
		list.add(classOne2);

		List<Object> annObjects = new ArrayList<>();

		for (Object someObject : list) {
			if (someObject.getClass().isAnnotationPresent(CsvEntity.class)) {
				annObjects.add(someObject);
			}
		}

		File folder = new File("./data/");
		String stringFolder = "./data/";
		CsvWriter writer = new CsvWriter();
		CsvReader reader = new CsvReader();

		writer.writeToCsv(annObjects, stringFolder);
		List<Object> classList = reader.readFromCsv(folder, ClassOne.class);

		System.out.println(classList.size());
		for (Object object : classList) {
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
		File folder = new File("./data/");
		if (!folder.isDirectory()) {
			folder.mkdir();
		}
	}
}
