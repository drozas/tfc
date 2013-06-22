package eu.ist.astra.am;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import eu.ist.astra.am.controller.ApplicationManagerController;


/**
 * 
 * Bundle activator.
 * 
 * @author David Rozas 
 *
 */
public class Activator implements BundleActivator {

	
	private ApplicationManagerController controller;
	@Override
	public void start(BundleContext bc) throws Exception {
		this.controller = ApplicationManagerController.getInstance();
		this.controller.setBundleContext(bc);
		this.controller.start();	
	}

	@Override
	public void stop(BundleContext arg0) throws Exception {
		//If the bundle is stopped, dispose everything
		this.controller.stop();
	}
	
	
}
