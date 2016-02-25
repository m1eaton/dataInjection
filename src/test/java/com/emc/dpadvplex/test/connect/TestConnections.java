package com.emc.dpadvplex.test.connect;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sun.jersey.api.client.GenericType;

import repositoryapp.data.connection.vplex.VPlexCommunicationException;
import repositoryapp.data.connection.vplex.VplexClient;




public class TestConnections extends TestCase
{
    ApplicationContext context;
    
    protected void setUp()
    {
        context = new ClassPathXmlApplicationContext("ApplicationContext.xml"); 
    }
    
    
    public void testWhippet()
    {
        VplexClient client = context.getBean("vplex_client_whippet", VplexClient.class);
        List<String> path = new ArrayList<String>();
        path.add("vplex/clusters/*"); 
        String response = "";
        try
        {
            response = client.accept(path , new GenericType<String>(){});
        }
        catch (VPlexCommunicationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(response);
        
    }
    
    protected void tearDown()
    {
        
    }
}
