package in.nic.pes.lgd.common;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.owasp.esapi.ESAPI;

public class EsapiEncoder {
	private static final Logger log = Logger.getLogger(EsapiEncoder.class);
	public static Object encode(Object obj){
		

		//int numberOfFields=obj.getClass().getDeclaredFields().length;



		//Field[] field=obj.getClass().getDeclaredFields();

		Method[] method=obj.getClass().getMethods();

		for(int i=0;i<method.length;i++){

		if(method[i].getReturnType().toString().contains("void")){
			
			
			try {
			Class<?>[] cla=method[i].getParameterTypes();	
			boolean isString=false;
			
			
			
			for(int j=0;j<cla.length;j++){
				
				if(cla[0].toString().contains("String")){
					isString=true;
				}	
			}
			
			
				if(isString){
					
				Method[] mLocal=	method;
				
				for(Method m:mLocal){
				
					
					if((!m.getReturnType().toString().contains("void"))&&m.getName().replaceFirst("g","s").equals(method[i].getName())){
						if(m.invoke(obj)!=null){
							method[i].invoke(obj,ESAPI.encoder().encodeForHTMLAttribute( m.invoke(obj).toString()));
						}
					}	
					
				}
					
				
				
				
				
				}
				
				
			} catch (Exception e) {
				
				log.debug("Exception" + e);
			}
			
		}
			
			
		}
				return null;
			}
}
