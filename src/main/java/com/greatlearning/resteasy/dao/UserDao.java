package com.greatlearning.resteasy.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import com.greatlearning.resteasy.entities.User;
import com.greatlearning.resteasy.utils.Role;

public class UserDao extends AbstractDao{

	public UserDao() {
	}
    
    public void save(User entity) throws Exception {
        getCurrentSession().save(entity);
    }
    
    public User findById(String id) throws Exception {
    	openCurrentSession();
    	User user = (User) getCurrentSession().get(User.class, id);
    	closeCurrentSession();
        return user; 
    }
    
    public User login(String userName, String password, Role role) throws HibernateException {
    	openCurrentSession();
    	Criteria criteria = getCurrentSession().createCriteria(User.class);
    	criteria.add(Restrictions.eq("username", userName));
    	criteria.add(Restrictions.eq("password", password));
    	criteria.add(Restrictions.eq("role", role.toString()));
    	List<User> users = criteria.list();
    	User user = users.size()>0?(User)users.get(0):null;
    	closeCurrentSession();
        return user; 
    }
}
