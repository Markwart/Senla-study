package by.senla.study.project.service.impl;

import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.IWithConditionDao;
import by.senla.study.project.dao.jdbc.impl.WithConditionDao;
import by.senla.study.project.dao.jdbc.impl.entity.PC;
import by.senla.study.project.service.IWithConditionService;

public class WithConditionService implements IWithConditionService {

	private IWithConditionDao dao = WithConditionDao.getInstance();

	private static WithConditionService instance;

	private WithConditionService() {
	}

	public static WithConditionService getInstance() {
		if (instance == null) {
			instance = new WithConditionService();
		}
		return instance;
	}

	@Override
	public List<PC> getPCListWithCost() {
		List<PC> pcList = dao.findPCWithCost();
		return pcList;
	}
	
	@Override
	public Map<Integer, Integer> getAverageCostPc() {
		Map<Integer, Integer> pcMap = dao.findAverageCostPc();
		return pcMap;
	}
	
	@Override
	public List<String> getPrinterMakers() {
		List<String> pcList = dao.findPrinterMakers();
		return pcList;
	}
	
	@Override
	public List<String> getPCAndLaptopMakers() {
		List<String> pcList = dao.findPCAndLaptopMakers();
		return pcList;
	}
}
