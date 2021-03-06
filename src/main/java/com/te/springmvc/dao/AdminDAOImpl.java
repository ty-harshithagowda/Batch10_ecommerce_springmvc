package com.te.springmvc.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import com.te.springmvc.beans.AdminBean;
import com.te.springmvc.beans.ItemBean;


@Repository
public class AdminDAOImpl implements AdminDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	public AdminBean authenticate(Integer aid, String password) {
		AdminBean Bean = null;
		try {
			EntityManager manager = factory.createEntityManager();

			String getData = "from AdminBean where aid=:aid and password=:password";

			Query query = manager.createQuery(getData);
			query.setParameter("aid", aid);
			query.setParameter("password", password);

			Bean = (AdminBean) query.getSingleResult();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return Bean;
	}// end of authenticate }

	public ItemBean getEmpData(Integer aid) {

		EntityManager manager = factory.createEntityManager();
		ItemBean bean = manager.find(ItemBean.class, aid);
		return bean;
	}

	public boolean addItem(ItemBean Bean) {
		try {

			EntityManager manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(Bean);
			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean updateItem(ItemBean infoBean) {
		try {
//			EntityManagerFactory factory = Persistence.createEntityManagerFactory("springdb");
			EntityManager manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			ItemBean orgData = manager.find(ItemBean.class, infoBean.getId());

			if (infoBean.getName() != null && infoBean.getName() != "") {
				orgData.setName(infoBean.getName());
			}

			if (infoBean.getQty() != null) {
				orgData.setQty(infoBean.getQty());
			}

			if (infoBean.getPrice() != null) {
				orgData.setPrice(infoBean.getPrice());
			}

			transaction.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean deleteItem(Integer id) {
		try {
//			EntityManagerFactory factory = Persistence.createEntityManagerFactory("springdb");
			EntityManager manager = factory.createEntityManager();
			EntityTransaction transaction = manager.getTransaction();
			transaction.begin();
			ItemBean infoBean = manager.find(ItemBean.class, id);
			manager.remove(infoBean);
			transaction.commit();
			return true;
		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
	}

	@Override
	public List<ItemBean> getAllEmployeeDetails() {
		List<ItemBean> Beans = null;

		try {
//			EntityManagerFactory factory = Persistence.createEntityManagerFactory("springdb");
			EntityManager manager = factory.createEntityManager();

			Query query = manager.createQuery("from item");

			Beans = query.getResultList();
			
			
		} catch (Exception e) {
			Beans = null;
			e.printStackTrace();
		}

		return Beans;
	
	}

	
}
