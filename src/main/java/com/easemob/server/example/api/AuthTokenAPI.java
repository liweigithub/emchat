package com.easemob.server.example.api;

/**
 * @author wstv
 */
public interface AuthTokenAPI{
	Object getAuthToken(String clientId, String clientSecret);
}
