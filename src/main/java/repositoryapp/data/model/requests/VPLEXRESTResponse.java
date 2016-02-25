package repositoryapp.data.model.requests;

public class VPLEXRESTResponse
{
    private VPLEXResponse response;

    public VPLEXRESTResponse()
    {
        System.out.println("Generating VPLEXRestReponse");
    }

    public VPLEXResponse getResponse()
    {
        return response;
    }

    public void setResponse(VPLEXResponse response)
    {
        this.response = response;
    }

}
