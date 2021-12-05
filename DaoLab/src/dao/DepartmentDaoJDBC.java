package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import connection.ConnectionFactory;
import exceptions.DatabaseException;
import models.Department;


public class DepartmentDaoJDBC implements DepartmentDao {
	private Connection connection;
	
	public DepartmentDaoJDBC(Connection connection) {
		this.connection = connection;
	
	}
	
	@SuppressWarnings("null")
	@Override
	public  void insert(Department department) {
		ResultSet result = null;
		PreparedStatement st = null;
		
		try {
			
			st = connection.prepareStatement(""
					+ "INSERT INTO department (Name)"
					+ "VALUES(?)", Statement.RETURN_GENERATED_KEYS);
			
		    st.setString(1, department.getName());
			
			st.executeUpdate();
			
		}catch(SQLException e){
			
			throw new DatabaseException(e.getMessage());
			
		}finally {
			
			ConnectionFactory.closeStatement(st);
			
		    ConnectionFactory.closeResultSet(result);
		}
		
	}

	@Override
	public void update(Department department) {
		ResultSet result = null;
		PreparedStatement st = null;
		try {
			
			String sql ="UPDATE department "
						+ "SET Name = ?  "
					 	+ "WHERE Id = ?";
			
			st = connection.prepareStatement(sql);
			
			st.setInt(2, department.getId());
			st.setString(1, department.getName());
			
			st.executeUpdate();
		
			
		
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
			
			
			}finally {
				
				ConnectionFactory.closeStatement(st);
				
			    ConnectionFactory.closeResultSet(result);
			}
		
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement statement = null;
		ResultSet result = null;

		try {
			
			statement = connection.prepareStatement(""
					+ "DELETE FROM department WHERE Id = ?");
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
		
		
		} catch (SQLException e) {
			
			throw new DatabaseException(e.getMessage());
			
		}finally {
			
			ConnectionFactory.closeStatement(statement);
			
		    ConnectionFactory.closeResultSet(result);
		}
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement statement = null;
		ResultSet result = null;
		
	
		try {
			
			String sql = "SELECT Name, Id "
					+ "FROM department "
					+ "WHERE Id = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			result = statement.executeQuery();
			
			if(result.next()) {
				
				Department department= new Department();
				
				department.setId(result.getInt("Id"));
				
				department.setName(result.getString("name"));
				
				return department;
			}
			
		} catch (SQLException e) {
		
			throw new DatabaseException(e.getMessage());
		
		} finally {
			ConnectionFactory.closeStatement(statement);
			ConnectionFactory.closeResultSet(result);
		}
		
		return null;
	}

	@Override
	public ArrayList<Department> findAll() {
		PreparedStatement statement = null;
		ResultSet result = null;
		ArrayList<Department> resultados = new ArrayList<Department>();
		try{
			
			statement = connection.prepareStatement("select * from department order by Id;");
			
			result = statement.executeQuery();
			
			while(result.next()) {
				Department department= new Department();
				department.setId(result.getInt("Id"));
				department.setName(result.getString("Name"));
				
				resultados.add(department);
			}
			
		} catch (SQLException e) {
		
			throw new DatabaseException(e.getMessage());
		
		} finally {
			ConnectionFactory.closeStatement(statement);
			ConnectionFactory.closeResultSet(result);
		}
		
		return resultados;
	}

}
