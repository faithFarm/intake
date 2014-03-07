package org.faithfarm.sms.hibernate;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.SessionImplementor;

	public class HibernateUtil {
	   
	    private static Log log = LogFactory.getLog(HibernateUtil.class);
	    
	    private static final SessionFactory sessionFactory;
	    
	    static {
	        try {
	            // Create the SessionFactory
	        	 sessionFactory = new Configuration().configure().buildSessionFactory();
	            
	            
	        } catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            log.error("Initial SessionFactory creation failed.", ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	    
	    public static final ThreadLocal session = new ThreadLocal();
	    
	    public static Session currentSession() throws HibernateException {
	        Session s = (Session) session.get();
	        //s.setFlushMode(FlushMode.ALWAYS);
	        // Open a new Session, if this Thread has none yet
	        if (s == null) {
	            s = sessionFactory.openSession();
	             session.set(s);
	        }
	        return s;
	    }
	    
	    public static void closeSession() throws HibernateException {
	        Session s = (Session) session.get();
	        session.set(null);
	        if (s != null)
	            s.close();
	    }    
	    
	    
	 
	}

	 
	
	
	/*
    private static final SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }
    
    public static SessionFactory getSessionFactory() {
    	return sessionFactory;
    }
}*/