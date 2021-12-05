package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionFactory;
import exceptions.DatabaseException;
import models.Department;
import models.Seller;

public class SellerDaoJDBC implements SellerDao {
	
	private Connection connection;
	
	public SellerDaoJDBC(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void insert(Seller seller) {
		ResultSet result = null;
		PreparedStatement st = null;
		try {
			
			st = connection.prepareStatement(""
					+ "INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)"
					+ "VALUES(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, (Date) seller.getBirthdate());
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			
			st.executeUpdate();
			
		}catch(SQLException e){
			
			throw new DatabaseException(e.getMessage());
			
		}finally {
			
			ConnectionFactory.closeStatement(st);
			
		    ConnectionFactory.closeResultSet(result);
		}
		
	}

	@Override
	public void update(Seller seller) {
		ResultSet result = null;
		PreparedStatement st = null;
		try {
			
			String sql ="UPDATE seller "
						+ "SET Name = ?, "
						+ "Email = ?, "
						+ "BirthDate = ?, "
						+ "BaseSalary = ?, "
						+ "DepartmentId = ? "
					 	+ "WHERE Id = ?";
			
			st = connection.prepareStatement(sql);
			st.setString(1, seller.getName());
			st.setString(2, seller.getEmail());
			st.setDate(3, (Date) seller.getBirthdate());
			st.setDouble(4, seller.getBaseSalary());
			st.setInt(5, seller.getDepartment().getId());
			st.setInt(6, seller.getId());
			
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
					+ "DELETE FROM seller WHERE Id = ?");
			
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
	public Seller findById(Integer id) {
		
		PreparedStatement statement = null;
		ResultSet result = null;
		
	
		try {
			
			String sql = "SELECT seller.*, department.Name as DepName "
					+ "FROM seller "
					+ "INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE seller.Id = ?";
			
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			
			result = statement.executeQuery();
			
			if(result.next()) {
				
				Department department= new Department();
				
				department.setId(result.getInt("DepartmentId"));
				
				department.setName(result.getString("DepName"));
				
				
				Seller seller = new Seller();
				
				seller.setId(result.getInt("Id"));
				
				seller.setName(result.getString("Name"));
				
				seller.setEmail(result.getString("Email"));
				
				seller.setBirthdate(result.getDate("BirthDate"));
				
				seller.setBaseSalary(result.getDouble("BaseSalary"));
				
				seller.setDepartment(department);
				
				return seller;
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
	public List<Seller> findAll() {
		PreparedStatement statement = null;
		PreparedStatement statement1 = null;
		ResultSet result = null;
		ArrayList<Seller> resultados = new ArrayList<Seller>();
		try{
			
			statement = connection.prepareStatement("SELECT seller.Id, seller.Name, seller.Email, "
													+ "seller.BirthDate as Date, seller.BaseSalary, seller.DepartmentId, "
													+ "department.Name "
													+ "FROM seller JOIN department "
													+ "ON seller.DepartmentId = department.Id;");
					
			result = statement.executeQuery();
			
			while(result.next()) {		
				Department department= new Department();
				
				department.setId(result.getInt("Id"));
				
				department.setName(result.getString("department.Name"));
				
				
				Seller s = new Seller();
				s.setId(result.getInt("Id"));
				s.setName(result.getString("Name"));
				s.setEmail(result.getString("Email"));
				s.setBirthdate(result.getDate("Date"));
				s.setBaseSalary(result.getDouble("BaseSalary"));
				s.setDepartment(department);
				
				resultados.add(s);
			}
			
		} catch (SQLException e) {
		
			throw new DatabaseException(e.getMessage());
		
		} finally {
			ConnectionFactory.closeStatement(statement);
			ConnectionFactory.closeResultSet(result);
		}
		
		return resultados;

	}

	@Override
	public List<Seller> findByDepartment(Department dep) {
		
		
		PreparedStatement statement = null;
		ResultSet result = null;
	    List<Seller> sellers = new ArrayList<Seller>();
		
	
		try {
			
			String sql = "SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER "
					+ "JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? ORDER BY Name";
			
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, dep.getId());
			
			result = statement.executeQuery();
			
			
			while(result.next()) {
				
				Department department = createDepartment(result);
				
				Seller seller = createSeller(result, department);
				
				sellers.add(seller);
			}
			
		} catch (SQLException e) {
			throw new DatabaseException(e.getMessage());
		}
		
		return sellers;
	}
	
	private Seller createSeller(ResultSet result, Department department) throws SQLException {
		
		Seller seller = new Seller();
		
		seller.setId(result.getInt("Id"));
		
		seller.setName(result.getString("Name"));
		
		seller.setEmail(result.getString("Email"));
		
		seller.setBirthdate(result.getDate("BirthDate"));
		
		seller.setBaseSalary(result.getDouble("BaseSalary"));
		
		seller.setDepartment(department);
		
		return seller;
	}
	
	private Department createDepartment(ResultSet result) throws SQLException {
		
	    Department department= new Department();
		
		department.setId(result.getInt("DepartmentId"));
		
		department.setName(result.getString("DepName"));
		
		return department;
	}

}
