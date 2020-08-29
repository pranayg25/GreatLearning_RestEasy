package com.greatlearning.resteasy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.greatlearning.resteasy.entities.Dish;
import com.greatlearning.resteasy.entities.Order;
import com.greatlearning.resteasy.entities.Orderline;
import com.greatlearning.resteasy.entities.User;
import com.greatlearning.resteasy.entities.Vendor;
import com.greatlearning.resteasy.entities.VendorDish;

public abstract class AbstractDao {

	private Session currentSession;
    
    private Transaction currentTransaction;
    
    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
     
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(Dish.class);
        configuration.addAnnotatedClass(Order.class);
        configuration.addAnnotatedClass(Orderline.class);
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Vendor.class);
        configuration.addAnnotatedClass(VendorDish.class);
        
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }
}
