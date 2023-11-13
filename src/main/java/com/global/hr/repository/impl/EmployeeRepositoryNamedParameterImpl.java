package com.global.hr.repository.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.global.hr.mapper.EmployeeMapper;
import com.global.hr.model.Employee;
import com.global.hr.repository.EmployeeRepository;

//@Component
@Repository
@Qualifier("EmployeeRepositoryNamedParameterImpl")
public class EmployeeRepositoryNamedParameterImpl implements EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public int count() {
		return namedParameterJdbcTemplate.queryForObject("SELECT COUNT(*) FROM employees", new MapSqlParameterSource(),
				Integer.class);
	}

	@Override
	public Employee findById(Long id) {

		return namedParameterJdbcTemplate.queryForObject("SELECT * FROM employees where id=:id",
				new MapSqlParameterSource("id", id), new EmployeeMapper());
	}

	@Override
	public List<Employee> findByNameAndSalary(String name, Double salary) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("name", name);
		mapSqlParameterSource.addValue("salary", salary);
		return namedParameterJdbcTemplate.query("SELECT * FROM employees where name=:name and salary = :salary",
				mapSqlParameterSource, new EmployeeMapper());
	}

	@Override
	public List<Employee> findAll() {
		return namedParameterJdbcTemplate.query("SELECT * FROM employees", new EmployeeMapper());
	}

	@Override
	public int insert(Employee employee) {

		return namedParameterJdbcTemplate.update("insert into employees (id,name,salary) values(:id,:name,:salary)",
				new BeanPropertySqlParameterSource(employee));
	}

	@Override
	public int update(Employee employee) {
		return namedParameterJdbcTemplate.update("update employees set name= :name,salary= :salary",
				new BeanPropertySqlParameterSource(employee));
	}

	@Override
	public int delete(Long id) {
		return namedParameterJdbcTemplate.update("DELETE FROM employees WHERE id = :id",
				new MapSqlParameterSource("id", id));
	}

}
