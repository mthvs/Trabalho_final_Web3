package dao;

import java.util.ArrayList;


import models.Department;

public interface DepartmentDao {
	
	public void insert(Department department);
	
	public void update(Department department);
	
	public void deleteById(Integer id);
	
	public Department findById(Integer id);
	
	public ArrayList<Department> findAll();
	
}
