package repositoryapp.integration.injection;

import java.util.Collection;

import org.springframework.data.mongodb.core.MongoOperations;

import repositoryapp.data.connection.vplex.VplexClient;
import repositoryapp.data.model.BaseDBObject;


public interface DataInjectionService
{
    
    
    public Collection<BaseDBObject> createManagedObjectsFromSource();
    //ToDo
    // Create custom exceptions to bubble up for now throw the Exception
    public void addManagedObjectsToTarget(Collection<BaseDBObject> items);
    public void setCollectionName(String collectionName);
    public void setVPLEXClient(VplexClient client);
    public void setMongoServices(MongoOperations operations);
    public void setResourcePath(String resourcePath);
    
}
