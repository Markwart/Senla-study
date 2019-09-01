package by.senla.study.project.init;

import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.jdbc.impl.entity.PC;
import by.senla.study.project.service.IWithConditionService;
import by.senla.study.project.service.impl.WithConditionService;

public class WithConditionOperation {

	private static IWithConditionService service = WithConditionService.getInstance();

	public static void findPCWithCost() {
		List<PC> pcList = service.getPCListWithCost();
		for (PC entity : pcList) {
			StringBuilder pc = new StringBuilder().append(entity.getModel().getModel()).append(" ")
					.append(entity.getHd()).append(" ").append(entity.getRam()).append(" ").append(entity.getSpeed())
					.append(" ").append(entity.getCd()).append(" ").append(entity.getPrice());
			System.out.println(pc);
		}
	}

	public static void findPCAndLaptopMakers() {
		List<String> pcList = service.getPCAndLaptopMakers();
		for (String maker : pcList) {
			System.out.println("maker=" + maker);
		}
	}

	public static void findAverageCostPc() {
		Map<Integer, Integer> pcMap = service.getAverageCostPc();
		pcMap.forEach((speed, price) -> {
			StringBuilder values = new StringBuilder().append("speed=").append(speed).append(" ")
					.append("average cost=").append(price);
			System.out.println(values);
		});
	}

	public static void findPrinterMakers() {
		List<String> pcList = service.getPrinterMakers();
		for (String maker : pcList) {
			System.out.println("maker=" + maker);
		}
	}
}
