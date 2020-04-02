package in.nic.pes.lgd.ws;

import javax.ws.rs.WebApplicationException;

import com.sun.jersey.api.Responses;

public class NotFoundException extends WebApplicationException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3355978809370615376L;

	/**
     * Create a HTTP 404 (Not Found) exception.
     */
    public NotFoundException() {
        super(Responses.notFound().build());
    }

// TODO Remove unused code found by UCDetector
//     /**
//      * Create a HTTP 404 (Not Found) exception.
//      * @param message the String that is the entity of the 404 response.
//      */
//     public NotFoundException(String message) {
//         super(Response.status(Responses.NOT_FOUND).
//                 entity(message).type("application/xml").build());
//     }
    
// TODO Remove unused code found by UCDetector
//     public NotFoundException(LgdFault fault) {
//         super(Response.status(Responses.NOT_FOUND).
//                 entity(fault).type("application/xml").build());
//     }

}