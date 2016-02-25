package repositoryapp.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;


public class LoggingAspect
{

    private Logger logger;
    
    
    public void beforeMethodLogging(JoinPoint jp)   
    {
        //Object result = null;
        //System.out.println(pjp.getTarget().getClass());
       
       // Object foo = pjp.getClass();
        
        //try
       // {
        //    result = pjp.proceed();
            
        //}
        //catch (Throwable e)
        //{
        //    e.printStackTrace();
        //}
        //return result;
        System.out.println("Before");
    }
    
}
