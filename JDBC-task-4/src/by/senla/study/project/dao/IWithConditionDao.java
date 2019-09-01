package by.senla.study.project.dao;

import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.jdbc.impl.entity.PC;

public interface IWithConditionDao {

	List<PC> findPCWithCost();

	Map<Integer, Integer> findAverageCostPc();

	List<String> findPrinterMakers();

	List<String> findPCAndLaptopMakers();

}
