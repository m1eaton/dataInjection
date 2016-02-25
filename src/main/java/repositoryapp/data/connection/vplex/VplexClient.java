package repositoryapp.data.connection.vplex;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.representation.Form;

/**
 * REST communication with the vplex.
 * @author dagano
 *
 */
public class VplexClient {

	private static final String USERNAME = "username"; 
	private static final String PASSWORD = "password"; 
	private static final String PORT = "443"; 

	public static final String VPLEX_GET_ALL_VIRTUAL_VOLUMES = "vplex/clusters/*/virtual-volumes/*";
	//public static final String VPLEX_GAT_ALL_CLUSTERS = "vplex/clusters/";
	
	private VplexConnection host; 
	
	/**
	 * Created a new Vplex REST client
	 * @param vplex credentials for connecting the REST API.
	 */
	public VplexClient(VplexConnection vplex) {
		host = vplex;
	}

	/**
	 * All calls to the vplex uri's starts with this prefix. 
	 * @return https://<hostname>:<port>
	 */
	private String baseUri() {
		return "https://" + host.getHostname() + ":" + PORT;
	}
	
	/**
	 * Retrieves the actual data of the resource from the vplex.
	 * @param vplexResource the vplex resource
	 * @param returnType a response class to be created from the server response
	 * @return the deserialized data about the server resource.s
	 * @throws VplexCouldNotIgnoreCertException
	 */
	public <T> T accept(List<String> path, GenericType<T> returnType) throws VPlexCommunicationException {
		System.out.println("Connecting to VPLEX to retrieve the data");
	    WebResource resource;
		resource = buildResource(path);
		T returnedResource = resource.header(USERNAME, host.getUsername()).header(PASSWORD, host.getPassword())
				.accept(MediaType.APPLICATION_JSON).get(returnType);
		return returnedResource;
	}

        public <T> T acceptPost(List<String> path, GenericType<T> returnType, String formData) throws VPlexCommunicationException {
            WebResource resource;
            resource = buildResource(path);
            T returnedResource = resource.header(USERNAME, host.getUsername()).header(PASSWORD, host.getPassword()).accept(MediaType.APPLICATION_JSON).post(returnType,formData);
        return returnedResource;
	}
        
        
        
	/**
	 * Builds the Jersey web-resource object for the specified path.
	 * @param path the uri of the resource, without the base uri prefix.
	 * @return The Jersey resource object that can be used to get data about the actual resource on the server.
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	private WebResource buildResource(List<String> path) throws VPlexCommunicationException {
		try {
			Certs.ignoreCertifications();
		} catch (Exception e) {
			throw new VPlexCommunicationException("could not ignore certifications", e);
		} 
		ClientConfig config = new DefaultClientConfig();
		config.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
		Client client = Client.create(config);
		client.addFilter(new HTTPBasicAuthFilter(host.getUsername(), host.getPassword()));
		WebResource resource = client.resource(baseUri());
		// getting the resource to the right path
		for (String pathElement : path) {
			resource = resource.path(pathElement);
		}
		return resource;
	}
	
	public static VplexClient getClient(String IP) throws VPlexCommunicationException {
		VplexConnection vplexConnection = new VplexConnection(IP, "service", "Mi@Dim7T");
		VplexClient vplexClient = new VplexClient(vplexConnection);
		return vplexClient;
	}

	public static void main(String[] args)
	{	   
	    try
        {
            VplexClient client = VplexClient.getClient("10.110.19.140");
            List<String> path = new ArrayList<String>();
            path.add("vplex/clusters/*/virtual-volumes/*"); 
            String response = client.accept(path , new GenericType<String>(){});
            System.out.println(response);
        }
        catch (VPlexCommunicationException e)
        {
            e.printStackTrace();
        }
	}

}
