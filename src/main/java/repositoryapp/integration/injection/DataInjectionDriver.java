package repositoryapp.integration.injection;



public class DataInjectionDriver
{

    private DataInjectionService _service;
    
    
    public DataInjectionService getDataInjectionService()
    {
        return _service;
    }
    
    public void setDataInjectionService(DataInjectionService service)
    {
        this._service = service;
    }
    
    
}
