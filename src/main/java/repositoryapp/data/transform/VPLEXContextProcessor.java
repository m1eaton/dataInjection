package repositoryapp.data.transform;

import java.util.Collection;

import repositoryapp.data.model.BaseDBObject;
import repositoryapp.data.model.requests.VPLEXRESTAttributes;

public interface VPLEXContextProcessor
{

    public BaseDBObject processAttributes(VPLEXRESTAttributes[] attributes, String parent);
    public Collection <BaseDBObject> getCollection(); 
    
}
