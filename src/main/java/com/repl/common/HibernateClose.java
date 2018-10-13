/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.common;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.internal.SessionFactoryImpl;

/**
 *
 * @author Punj
 */
public class HibernateClose {

     public static final ThreadLocal local = new ThreadLocal();
 
    // Creating Session Factory Object
  static  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();// buildSessionfactory();
 
    // To get the current Session object from Session Factory object
    public static Session currentSession() throws HibernateException
    {
       Session session = (Session) local.get();
 
       //open a new session if this thread has no active session
       if(session == null)
       {
          session = sessionFactory.openSession();
          local.set(session);
       }else{
       
           System.out.println("EXISTING CONNECTION IS ALIVE");}
 
       return session;
    }
    
    private static boolean closeSessionFactoryIfC3P0ConnectionProvider(SessionFactory factory) {

    boolean done = false;
    if(factory instanceof SessionFactoryImpl) {
        SessionFactoryImpl sf = (SessionFactoryImpl)factory;
        ConnectionProvider conn = sf.getConnectionProvider();
        if(conn instanceof C3P0ConnectionProvider) { 
            ((C3P0ConnectionProvider)conn).close(); 
            try {
                Thread.sleep(2000); //Let give it time...it is enough...probably
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            done = true;
        }
        factory.close();
    }
    return done;

}
}
