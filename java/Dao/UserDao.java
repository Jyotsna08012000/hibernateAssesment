package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Connection.DBConnection;

import Model.User;

public class UserDao {
	Session session = null;
	Transaction tx = null;
	List<User> list = null;
	
	public  List<User> checkEmail(String email) {
		session = new DBConnection().getSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("from User u where u.email=:email");
		q.setParameter("email", email);
		list = q.list();
		System.out.println("list:"+list);
		tx.commit();

		session.close();
		return list;
	}
	public void insertUser(User u) {
		
		session = new DBConnection().getSession();
		tx = session.beginTransaction();
		session.save(u);
		tx.commit();
		session.close();
		System.out.println("data inserted");
	}
	public User UserLogin(User u) {
		User u1 = null;
		session = new DBConnection().getSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("from User u where u.email=:email and u.password=:password");
		q.setParameter("email", u.getEmail());
		q.setParameter("password", u.getPassword());
		list = q.list();
		u1 = list.get(0);
		tx.commit();
		session.close();
		return u1;
	}
	
	public boolean checkOldPassword(int userid,String op) {
		boolean flag = false;
		session = new DBConnection().getSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("from User u where u.userid=:userid and u.password=:password");
		q.setParameter("userid", userid);
		q.setParameter("password", op);
		list = q.list();
		flag = true;
		tx.commit();
		session.close();
		return flag;
	}
	public void changePasswrod(int userid,String np) {
		session = new DBConnection().getSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("update User u set u.password=?1 where u.id=?2");
		q.setParameter(1, np);
		q.setParameter(2, userid);
		q.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("password changed");
		
	}
	public boolean checkEmailfromDB(String email) {
		boolean flag = false;
		session = new DBConnection().getSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("from User u where u.email=:email");
		q.setParameter("email", email);
		list = q.list();
		flag = true;
		tx.commit();
		session.close();
		return flag;
	}
	public void changeNewPassword(String email, String newp) {
		session = new DBConnection().getSession();
		tx = session.beginTransaction();
		Query q = session.createQuery("update User u set u.password=?1 where u.email=?2");
		q.setParameter(1, newp);
		q.setParameter(2, email);
		q.executeUpdate();
		tx.commit();
		session.close();
		System.out.println("password changed");
		
	}
}






	
