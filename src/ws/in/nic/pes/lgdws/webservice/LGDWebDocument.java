package ws.in.nic.pes.lgdws.webservice;

import java.io.File;
import java.util.Locale;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import in.nic.pes.lgd.resourcebundles.LGDResourceBundle;

@Path("")
public class LGDWebDocument {
	

	
	
	
	@GET
	@Path("/webserviceDocDownload")
	//@Produces("text/plain")
	public Response WebServiceDocument(){
		ResponseBuilder builderError=null;
		ResponseBuilder response=  null; 
		try{
		builderError= Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		String directoryLocation = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("webservice.directory.location").toString();
		String fileName = LGDResourceBundle.getBundle("upload_info", Locale.ENGLISH).getObject("supporting.doc.file.webServiceDoc").toString();
		String filePath = directoryLocation+File.separator+fileName;
		
		File file = new File(filePath);
		if(file.length()>0) {
			
			response=Response.ok((Object)file);
			response.header("Content-Disposition","attachment; filename="+file.getName());
			
			
		}else{
			response = builderError.entity("File has been deleted");
		}
		}catch(Exception e){
			response = builderError.entity(e);
		}
		
		return response.build();
		
	}
	
	
	
}
