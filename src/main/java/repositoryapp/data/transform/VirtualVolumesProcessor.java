package repositoryapp.data.transform;

import java.util.ArrayList;
import java.util.Collection;

import repositoryapp.data.model.BaseDBObject;
import repositoryapp.data.model.VirtualVolume;
import repositoryapp.data.model.requests.VPLEXRESTAttributes;

public class VirtualVolumesProcessor implements VPLEXContextProcessor
{

    private ArrayList<BaseDBObject> virtualVolumesList = new ArrayList<BaseDBObject>();

    public Collection<BaseDBObject> getCollection()
    {
        return virtualVolumesList;
    }

    public void setVirtualVolumesList(ArrayList<BaseDBObject> virtualVolumesList)
    {
        this.virtualVolumesList = virtualVolumesList;
    }

    public BaseDBObject processAttributes(VPLEXRESTAttributes[] attributes, String parent)
    {
        VirtualVolume vv = new VirtualVolume(attributes, parent);
        //virtualVolumesList.add(vv);
        System.out.println("Virtual Volume"+vv.get_id()+" was Created from VPLEX Rest Feed");       
        return vv;
    }
    
    
    
}
