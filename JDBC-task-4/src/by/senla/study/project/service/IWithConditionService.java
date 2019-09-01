package by.senla.study.project.service;

import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.jdbc.impl.entity.PC;

public interface IWithConditionService {

	List<PC> getPCListWithCost();

	Map<Integer, Integer> getAverageCostPc();

	List<String> getPrinterMakers();

	List<String> getPCAndLaptopMakers();

}
