package by.senla.cvs.module.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CsvEntity {

	public String fileName();

	public String valuesSeparator() default ";";

	public String entityId() default "id";

}
