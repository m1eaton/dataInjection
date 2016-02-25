package repositoryapp.data.model.requests;

public class VPLEXResponse
{

    public VPLEXContext[] context;

    public VPLEXResponse()
    {   
    }

    public VPLEXContext[] getConext()
    {
        return context;
    }

    public void setConext(VPLEXContext[] context)
    {
        this.context = context;
    }

}
