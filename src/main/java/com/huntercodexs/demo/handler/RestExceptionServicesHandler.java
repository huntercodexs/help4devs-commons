package com.huntercodexs.demo.handler;

import com.huntercodexs.demo.handler.dto.RestErrorResponseDto;
import com.huntercodexs.demo.handler.exception.Help4DevsServiceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.huntercodexs.demo.enumerator.TraceType.RESPONSE_ENTITY_SERVICE;
import static com.huntercodexs.demo.services.basic.Help4DevsToolsService.trace;

/**
 * This class serve to protect and configure an HTTP ERROR RESPONSE to external services
 * from the platform, in another word this ControllerAdvice treat the responses that should be
 * sent to the consumers from the services offering by this application, for example:
 * <ul>
 *     <li>When the consumer is a POS station or something like that, the HTTP RESPONSE can be not needed to inform</li>
 *     <li>When the application is running in the production the HTTP RESPONSE can be disabled to avoid attacks</li>
 * </ul>
 * */
@Slf4j
@ControllerAdvice
public class RestExceptionServicesHandler extends ResponseEntityExceptionHandler {

	@Value("${api.services.response-body.enabled:false}")
	boolean servicesResponseBodyEnabled;
 
    @ExceptionHandler(value = {
			Help4DevsServiceException.class
	})
    protected ResponseEntity<Object> handleServicesConflict(RuntimeException ex, WebRequest request) {

		String tcn;
		RestErrorResponseDto restErrorResponseDto = new RestErrorResponseDto();

    	if (ex instanceof Help4DevsServiceException) {

			Help4DevsServiceException help4DevsServiceException = (Help4DevsServiceException) ex;

			restErrorResponseDto.setCodeError(help4DevsServiceException.getCodeError());
			restErrorResponseDto.setMessage(help4DevsServiceException.getMessage());

			if (help4DevsServiceException.getTcn() != null) {

				tcn = help4DevsServiceException.getTcn();
				trace(tcn, null, "Help4DevsServiceException: " + ex.getMessage(), RESPONSE_ENTITY_SERVICE, "error");
				restErrorResponseDto.setTcn(tcn);

			} else {
				trace(null, null, "Help4DevsServiceException: " + ex.getMessage(), RESPONSE_ENTITY_SERVICE, "error");
			}

		} else {
			trace(null, null, "Unknown error: " + ex.getMessage(), RESPONSE_ENTITY_SERVICE, "error");
			restErrorResponseDto = null; /*TODO: Fix it in the next devs*/
    	}

		if (servicesResponseBodyEnabled) {
			return handleExceptionInternal(
					ex,
					restErrorResponseDto,
					new HttpHeaders(),
					HttpStatus.CONFLICT,
					request);
		}

		return handleExceptionInternal(
				ex,
				null,
                new HttpHeaders(),
				HttpStatus.CONFLICT,
				request);
    }

}
