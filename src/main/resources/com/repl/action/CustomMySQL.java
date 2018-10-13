/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.action;

import org.hibernate.dialect.MySQL5InnoDBDialect;
import org.hibernate.dialect.function.StandardSQLFunction;

/**
 *
 * @author Punj
 */
public class CustomMySQL extends MySQL5InnoDBDialect {

    public CustomMySQL() {
        super();
          registerFunction("say_hello", new StandardSQLFunction("say_hello"));
    }

    
    
}
