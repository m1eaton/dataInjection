package repositoryapp.integration.injection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;

import repositoryapp.data.connection.vplex.VPlexCommunicationException;
import repositoryapp.data.connection.vplex.VplexClient;
import repositoryapp.data.model.BaseDBObject;
import repositoryapp.data.model.requests.VPLEXRESTResponse;
import repositoryapp.data.transform.RequestTransofrmation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.client.GenericType;

public class MongoDataInjectionService implements DataInjectionService
{

    Logger logger = Logger.getLogger(MongoDataInjectionService.class);
    private String _collectionName = "";
    private VplexClient _vplexClient; 
    private MongoOperations _operations;
    private String _resourcePath = "";
    
    @Override
    public Collection<BaseDBObject> createManagedObjectsFromSource()
    {
        
        Collection<BaseDBObject> retVal = null;
        List<String> path = new ArrayList<String>();
        path.add(_resourcePath); 
        String clusters;
        Gson gson = new GsonBuilder().create();
        try
        {
            clusters = _vplexClient.accept(path , new GenericType<String>(){});
            VPLEXRESTResponse pres = gson.fromJson(clusters, VPLEXRESTResponse.class);
            RequestTransofrmation requestTransform = new RequestTransofrmation();
            requestTransform.reqestToTransform(pres);
            retVal =  requestTransform.getProcessedRequetCollection();
        }
        catch (VPlexCommunicationException e)
        {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public void addManagedObjectsToTarget(Collection<BaseDBObject> items)
    {
         //operations.collectionExists(_collectionName);
        if(checkCollectionValue(_operations))
        {
            if(_operations.collectionExists(_collectionName))
            {
                _operations.dropCollection(_collectionName);
            }
            _operations.createCollection(_collectionName);
        }
        else
        {
            _operations.createCollection(_collectionName);
        }
        _operations.insert(items, _collectionName);
    }
    
    public void setCollectionName(String collectionName)
    {
        this._collectionName = collectionName;
    }

    /**
     * Validate that if the collection exists in the Mongo DataBase
     * @param operations
     * @return
     */
    private boolean checkCollectionValue(MongoOperations operations)
    {
        boolean retVal = true;
    
        if (_collectionName.equals(""))
        {
            retVal = false;
        }
        else
        {
            retVal = operations.collectionExists(_collectionName);
        }
        
        return retVal;
    }
        

    @Override
    public void setVPLEXClient(VplexClient client)
    {
        this._vplexClient = client;
        
    }

    @Override
    public void setMongoServices(MongoOperations operations)
    {
        this._operations = operations;
        
    }

    @Override
    public void setResourcePath(String resourcePath)
    {
       this._resourcePath = resourcePath;
        
    }

   
    
}
