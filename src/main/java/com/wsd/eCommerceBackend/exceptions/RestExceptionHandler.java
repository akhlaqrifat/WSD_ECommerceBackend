package com.wsd.eCommerceBackend.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler({
            CloudServiceException.class
    })
    protected ResponseEntity<Object> handleCustomExceptions(Exception ex) {
        HttpStatus status = getStatus(ex);
        ApiError apiError = new ApiError(status);
        apiError.setMessage(ex.getMessage());
        if (ex instanceof DatabaseException || ex instanceof CloudServiceException) {
            apiError.setDebugMessage(ex.getMessage());
        }
        return buildResponseEntity(apiError);
    }
//    @ExceptionHandler(EntityNotFoundException.class)
//    protected ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
//        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
//        apiError.setMessage(ex.getMessage());
//        return buildResponseEntity(apiError);
//    }

    // Other overridden methods...

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    private HttpStatus getStatus(Exception ex) {
        if (ex instanceof DatabaseException || ex instanceof CloudServiceException) {
            return HttpStatus.CONFLICT;
        } else {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";
        return buildResponseEntity(new ApiError(BAD_REQUEST, error, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String exceptionMethodName = ex.getMethod().toLowerCase() + " method is not supported" ;
        return buildResponseEntity(new ApiError(HttpStatus.METHOD_NOT_ALLOWED, exceptionMethodName, ex));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String exceptionMethodName = ex.getContentType() + " media type is not supported" ;
        return buildResponseEntity(new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, exceptionMethodName, ex));
    }

    @ExceptionHandler(CustomMessage404Exceptions.class)
    protected ResponseEntity<Object> handleCustomMessage400 (CustomMessage404Exceptions ex) {
        ApiError apiError = new ApiError(HttpStatus.SERVICE_UNAVAILABLE);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }
    @ExceptionHandler(CustomMessageException.class)
    protected ResponseEntity<Object> handleCustomMessage(CustomMessageException ex) {
        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(DatabaseException.class)
    protected ResponseEntity<Object> handleDatabase(DatabaseException ex) {

        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(value = CustomException.class)
    protected ResponseEntity<Object> handleCustomException(CustomException ex) {

        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(ex.getMessage());
        return buildResponseEntity(apiError);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(Exception e) {

        String errMessage;
        if(e.getCause() != null && e.getCause().getCause() instanceof Exception ex) {
            errMessage =ex.getMessage();
        } else {
            errMessage = e.getMessage();
        }

        ApiError apiError = new ApiError(BAD_REQUEST);
        apiError.setMessage(errMessage);
        return buildResponseEntity(apiError);
    }


}

