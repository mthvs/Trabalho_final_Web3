package app;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import dao.DaoFactory;
import dao.DepartmentDao;
import dao.DepartmentDaoJDBC;
import dao.SellerDao;
import models.Department;
import models.Seller;

public class TesteConexaoDao {
	
	public static void main(String[] args) throws ParseException {
		
		Department d = new Department();
		DepartmentDao dao = DaoFactory.creatDepartmentDao();
		
	    //d.setName("Teste");
		
		//dao.insert(d);//passa o objeto para a camada model
		
		//Department department = dao.findById(5);
		//System.out.println(department.getId() +" "+ department.getName());
		
		//d.setId(6);
		/*d = dao.findById(5);
		d.setName("Moto");
		dao.update(d);*/
		
		//dao.deleteById(7);
		
		/*List<Department> department = dao.findAll();
		
		for(Department d1 : department) {
			System.out.println("id: " + d1.getId() + " nome:" + d1.getName());
		}*/
		
		
		/*System.out.println("======= TESTE 1 (findById)  ============");
		SellerDao dao = DaoFactory.createSellerDao();
		
		Seller s = new Seller();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		/*s.setName("Matheus Henrique");
		s.setEmail("matheusfoz_11@hotmail.com");
		s.setBirthdate(new java.sql.Date(dateFormat.parse("08/02/2001").getTime()));
		s.setBaseSalary(2000.00);
		d.setId(5);
		d.getId();
		s.setDepartment(d);
		
		dao.insert(s);*/
		
		
		/*s = dao.findById(7);
		s.setEmail("matheusfoz_11@hotmail.com");
		d.setId(3);
		s.setDepartment(d);
		dao.update(s);*/
		
		/*List<Seller> sellers = dao.findAll();
		
		for (Seller s1 : sellers) {
			System.out.println(s1);
		}*/
		
		//System.out.println(seller);
		
		//dao.deleteById(6);
		
		
		/*System.out.println("======= TESTE 2 (findByDepartment) ============");
		
		List<Seller> sellers = dao.findByDepartment(new Department(1, null));
		
		for (Seller s : sellers) {
			System.out.println(s);
		}*/
		
		
	}
	

}
