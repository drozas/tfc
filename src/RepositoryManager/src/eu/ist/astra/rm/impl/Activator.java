package eu.ist.astra.rm.impl;

import java.util.Properties;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import eu.ist.astra.persistency.PersistencyException;
import eu.ist.astra.rm.IRepositoryManager;


/**
 * 
 * Repository Manager activator
 * 
 * @author David Rozas & Alfredo Perez
 *
 */
public class Activator implements BundleActivator {
	
	RepositoryManagerImpl arm;

	public void start(BundleContext bc) throws PersistencyException {
		
		if (arm != null) return;
		
		try
		{
			arm = new RepositoryManagerImpl(bc);
			
			Properties props = new Properties();
			props.put("SOAP.service.name", RepositoryManagerImpl.SOAP_SERVICE_NAME);
			bc.registerService(IRepositoryManager.class.getName(), arm, props);
			
		}catch(PersistencyException e)
		{
			System.out.println("It was not possible to start " + RepositoryManagerImpl.SOAP_SERVICE_NAME + ": ");
			e.printStackTrace();
			throw e;
		}


	}

	public void stop(BundleContext bc) throws Exception {
		// TODO Auto-generated method stub

	}

}
