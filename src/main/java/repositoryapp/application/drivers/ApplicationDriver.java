package repositoryapp.application.drivers;

import java.util.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import repositoryapp.data.model.BaseDBObject;
import repositoryapp.integration.injection.DataInjectionDriver;
import repositoryapp.integration.injection.MongoDataInjectionService;

public class ApplicationDriver
{

    public static void main(String[] args)
    {
        
        System.out.println("-------------- VPLEX Data Repository Transfer Application: Start  ---------------");
        ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");   
        // could make a drive an interface for now it is concrete class
        DataInjectionDriver driver = context.getBean("dataInjectionDriver", DataInjectionDriver.class);
        MongoDataInjectionService service = (MongoDataInjectionService)driver.getDataInjectionService();
        Collection<BaseDBObject> items = service.createManagedObjectsFromSource();
        service.addManagedObjectsToTarget(items);
        System.out.println("-------------- VPLEX Data Repository Transfer Application: Finish  ---------------");
    }
    
}
