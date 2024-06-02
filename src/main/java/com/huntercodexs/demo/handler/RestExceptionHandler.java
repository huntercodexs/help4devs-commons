package com.huntercodexs.demo.handler;

import com.huntercodexs.demo.handler.dto.RestErrorResponseDto;
import com.huntercodexs.demo.handler.exception.Help4DevsSampleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.huntercodexs.demo.enumerator.TraceType.RESPONSE_ENTITY;
import static com.huntercodexs.demo.services.Help4DevsToolsService.trace;

/**
 * This class serve to protect and configure an HTTP ERROR RESPONSE to [INTERNAL SERVICES] inside that platform,
 * or applications that belong to the same company or deal, in another word this ControllerAdvice treat the responses
 * that should be sent to the internal business consumers integrated with this application, for example:
 * <ul>
 *     <li>When the app consumer is an official applicative, the HTTP RESPONSE can be not needed to inform</li>
 *     <li>When the application is running in the production the HTTP RESPONSE can be disabled to avoid attacks</li>
 * </ul>
 * 	It can be enabled in the development/homolog environment to offer more practicing on the development
 * 	<br />
 * 	<br />
 * 	[IMPORTANT] Don't make a mistake and don't confound this ControllerAdvice with the RestExceptionServicesHandler
 * */
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Value("${api.app.response-body.enabled:false}")
	boolean appResponseBodyEnabled;

	String replacementString = "@{data}";
	String missingDataMessage = "Missing Data ["+replacementString+"], please check the request";

	@ExceptionHandler(value = {
			Help4DevsSampleException.class
	})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {

		String tcn;
		RestErrorResponseDto restErrorResponseDto = new RestErrorResponseDto();

    	if (ex instanceof Help4DevsSampleException) {

			Help4DevsSampleException help4DevsSampleException = (Help4DevsSampleException) ex;

			restErrorResponseDto.setCodeError(help4DevsSampleException.getCodeError());
			restErrorResponseDto.setMessage(help4DevsSampleException.getMessage());

			if (help4DevsSampleException.getTcn() != null) {
				
				tcn = help4DevsSampleException.getTcn();
				trace(tcn, null, "Help4DevsSampleException: " + ex.getMessage(), RESPONSE_ENTITY, "error");
				restErrorResponseDto.setTcn(tcn);
				
			} else {
				trace(null, null, "Help4DevsSampleException: " + ex.getMessage(), RESPONSE_ENTITY, "error");
			}

		} else {
			trace(null, null, "Unknown error: " + ex.getMessage(), RESPONSE_ENTITY, "error");
			restErrorResponseDto = null; /*TODO: Fix it in the next devs*/
    	}

		/*When enabled, the handler allow the service to respond the request within BodyResponse*/
		if (appResponseBodyEnabled) {
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

	/**
	 * Handler the Wrong Requests using the incorrect HTTP METHOD
	 * */
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {
		RestErrorResponseDto restErrorResponseDto = new RestErrorResponseDto();
		restErrorResponseDto.setCodeError(405);
		restErrorResponseDto.setMessage(ex.getMessage());

		trace(
				null,
				null,
				"handleHttpRequestMethodNotSupported: " + ex.getMessage(),
				RESPONSE_ENTITY,
				"error");

		/*When enabled, the handler allow the service to respond the request within BodyResponse*/
		if (appResponseBodyEnabled) {
			return handleExceptionInternal(
					ex,
					restErrorResponseDto,
					new HttpHeaders(),
					HttpStatus.METHOD_NOT_ALLOWED,
					request);
		}

		return handleExceptionInternal(
				ex,
				null,
				new HttpHeaders(),
				HttpStatus.METHOD_NOT_ALLOWED,
				request);
	}

	/**
	 * Handler the Wrong Requests when the Arguments are missing
	 * */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex,
			HttpHeaders headers,
			HttpStatus status,
			WebRequest request
	) {

		RestErrorResponseDto restErrorResponseDto = new RestErrorResponseDto();
		restErrorResponseDto.setCodeError(400);
		restErrorResponseDto.setMessage(getCauseArgumentError(ex));

		trace(
				null,
				null,
				"handleMethodArgumentNotValid: " + ex.getMessage(),
				RESPONSE_ENTITY,
				"error");

		/*When enabled, the handler allow the service to respond the request within BodyResponse*/
		if (appResponseBodyEnabled) {
			return handleExceptionInternal(
					ex,
					restErrorResponseDto,
					new HttpHeaders(),
					HttpStatus.BAD_REQUEST,
					request);
		}

		return handleExceptionInternal(
				ex,
				null,
				new HttpHeaders(),
				HttpStatus.BAD_REQUEST,
				request
		);

	}

	private String getCauseArgumentError(MethodArgumentNotValidException ex) {
		return missingDataMessage.replace(replacementString,
				ex.getMessage()
						.split("; default message")[1]
						.replaceAll("]|\\[| ", ""));
	}

}
