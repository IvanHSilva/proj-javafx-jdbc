package model.services;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentService {
	
	public List<Department> findAll() {
		List<Department> list = new ArrayList<>();
		list.add(new Department(1, "Livros"));
		list.add(new Department(2, "Eletr�nicos"));
		list.add(new Department(3, "Computadores"));
		list.add(new Department(4, "Filmes"));
		list.add(new Department(5, "Vestu�rio"));
		return list;
	}
}
