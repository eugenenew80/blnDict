package kz.kegoc.bln.webapi.exception.mapper;

import javax.ejb.EJBException;
import javax.transaction.RollbackException;
import javax.validation.ValidationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import kz.kegoc.bln.exception.ApplicationException;
import kz.kegoc.bln.webapi.exception.entity.ErrorMessage;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.SQLGrammarException;


@Provider
public class EjbExceptionMapperImpl implements ExceptionMapper<EJBException> { 
	
    @Override
    public Response toResponse(EJBException exc) {
    	if (exc.getCause()!=null) {
			if (exc.getCause() instanceof RollbackException) {
				Throwable appExc =  exc.getCause();

				if (appExc.getCause()!=null)
					appExc = appExc.getCause();

				if (appExc.getCause()!=null)
					appExc = appExc.getCause();

				if (appExc.getCause()!=null)
					appExc = appExc.getCause();

				return Response.status(500)
					.type(MediaType.APPLICATION_JSON)
					.entity(new ErrorMessage("tx-exception", appExc.getMessage()))
					.build();
			}

			if (exc.getCause() instanceof SQLGrammarException) {
				Throwable appExc =  exc.getCause().getCause();
				return Response.status(500)
					.type(MediaType.APPLICATION_JSON)
					.entity(new ErrorMessage("sql-exception", appExc.getMessage()))
					.build();
			}

    		if (exc.getCause() instanceof ValidationException) { 
    			ValidationException appExc = (ValidationException) exc.getCause();
    	    	return Response.status(500)
    	    		.type(MediaType.APPLICATION_JSON)	
	    	        .entity(new ErrorMessage("validation-exception", appExc.getMessage()))
	    	        .build();    			
    		}

    		if (exc.getCause() instanceof ApplicationException) { 
    			ApplicationException appExc = (ApplicationException) exc.getCause();
    			
    			return Response.status(appExc.getStatusCode())
    				.type(MediaType.APPLICATION_JSON)	
	    	        .entity(new ErrorMessage(appExc.getCode(), appExc.getMessage()))
	    	        .build();    			
    		}

			String message = StringUtils.isNotEmpty(exc.getCause().getMessage()) ? exc.getCause().getMessage() : exc.getCause().getClass().getName();
        	return Response.status(500)
        		.type(MediaType.APPLICATION_JSON)	
    	        .entity(new ErrorMessage("ejb_exception", message))
    	        .build();
    	}
    		
    	String message = StringUtils.isNotEmpty(exc.getMessage()) ? exc.getMessage() : exc.getClass().getName();
    	return Response.status(500)
	        .entity(new ErrorMessage("ejb_exception", message))
	        .build();
    }
}
