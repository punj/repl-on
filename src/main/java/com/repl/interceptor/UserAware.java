/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.repl.interceptor;

import com.repl.bean.User;

/**
 *
 * @author Punj
 */



public interface UserAware {

	public void setUser(User user);
}