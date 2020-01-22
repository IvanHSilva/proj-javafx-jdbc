package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {
	
	private SellerDao dao = DaoFactory.createSellerDao();
	
	public List<Seller> findAll() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Seller sel) {
		if (sel.getId() == null) {
			dao.insert(sel);
		} else {
			dao.update(sel);
		}
	}
	
	public void remove(Seller sel) {
		dao.deleteById(sel.getId());
	}
}
