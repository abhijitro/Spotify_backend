package com.cts.spotify.auth.service;


public interface UserService 
{

	public boolean loginUser(String username, String password);// login


	public String getRoleByUserAndPass(String username, String password);


}
