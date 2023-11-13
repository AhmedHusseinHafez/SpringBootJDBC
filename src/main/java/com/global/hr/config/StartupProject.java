package com.global.hr.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.global.hr.model.Employee;
import com.global.hr.repository.EmployeeRepository;

@Component
public class StartupProject implements CommandLineRunner {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	@Qualifier("EmployeeRepositoryNamedParameterImpl")
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("DROP TABLE IF EXISTS employees");
		jdbcTemplate.execute("CREATE TABLE employees(id SERIAL,name VARCHAR(255),salary NUMERIC(15,2))");

		if (employeeRepository.count() == 0) {
			employeeRepository.insert(new Employee(20L, "Mohamed", 223213.0));
			employeeRepository.insert(new Employee(30L, "Nour", 23243.0));
			employeeRepository.insert(new Employee(40L, "Ali", 43243.0));
		}
	}

}
