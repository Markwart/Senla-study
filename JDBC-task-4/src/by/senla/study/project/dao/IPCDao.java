package by.senla.study.project.dao;

import java.util.List;
import java.util.Map;

import by.senla.study.project.entity.PC;

public interface IPCDao extends IDao<PC, Integer>{

	List<PC> findPCWithCost(Integer price);

	Map<Integer, Integer> findAverageCostPc();

}
