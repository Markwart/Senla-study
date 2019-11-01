package by.senla.study.board.service.utils;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import by.senla.study.board.dao.utils.PersistenceConfig;

@Configuration
@Import(PersistenceConfig.class)
@ComponentScan(basePackages = { "by.senla.study.board.service" })
@EnableTransactionManagement
public class ServiceContext {

}
