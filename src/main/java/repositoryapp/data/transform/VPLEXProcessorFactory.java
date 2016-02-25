package repositoryapp.data.transform;

public class VPLEXProcessorFactory
{

    public final String VIRTUAL_VOLUMES_CONTEXT = "virtual-volume";
    
    public VPLEXContextProcessor getVPLEXContextProcessor(String contextType)
    {
        VPLEXContextProcessor retVal = null;
        if(contextType.equalsIgnoreCase(VIRTUAL_VOLUMES_CONTEXT))
        {
            retVal =  new VirtualVolumesProcessor();
        }
        else
        {
            System.out.println("There is no Factory instance to process "+contextType);
        }
        return retVal;
    }
    
}
