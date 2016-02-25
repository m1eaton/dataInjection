package repositoryapp.data.transform;

import java.util.ArrayList;
import java.util.Collection;

import repositoryapp.data.model.BaseDBObject;
import repositoryapp.data.model.VirtualVolume;
import repositoryapp.data.model.requests.VPLEXContext;
import repositoryapp.data.model.requests.VPLEXRESTResponse;
import repositoryapp.data.model.requests.VPLEXResponse;

public class RequestTransofrmation
{
    
    
    private Collection<BaseDBObject> transforedObjects;
    
    /**
     * Takes a VPLEXResponse and process it to create the correct objects to inject into the 
     * Mongo DB.
     * @param initialRequest
     */
    public void reqestToTransform(VPLEXRESTResponse initialRequest)
    {
        transforedObjects = new ArrayList<>();
        BaseDBObject dbObject = null;
        VPLEXProcessorFactory factory = new VPLEXProcessorFactory();
        VPLEXResponse response = initialRequest.getResponse();
        VPLEXContext[] response_contexts = response.getConext();
        VPLEXContextProcessor processor = null;
        for(int i = 0; i<response_contexts.length;i++)
        {
            VPLEXContext context = response_contexts[i];
            String type = context.type;
            processor = factory.getVPLEXContextProcessor(type);
            dbObject = processor.processAttributes(context.attributes, context.parent);
            transforedObjects.add(dbObject);
        }
        //transforedObjects.add(dbObject);    
        
    }
    
   
    public Collection<BaseDBObject> getProcessedRequetCollection()
    {
        return transforedObjects;
    }
    
    public static void main(String[] args)
    {
        
    }
    
    
}
