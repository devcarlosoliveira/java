package br.com.carlos_oliveira.gestao_vagas.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * ExceptionHandlerController
 */
@ControllerAdvice
public class ExceptionHandlerController {

	private MessageSource messageSource;

	public ExceptionHandlerController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<ErrorMessageDTO>> handlerMethodArgumentNotValidExceptionEntity(
			MethodArgumentNotValidException e) {

		List<ErrorMessageDTO> errorsMessageDTO = new ArrayList<>();

		e.getBindingResult().getFieldErrors().forEach(err -> {

			// Objects.requireNonNull(err, "FieldError must not be null");

			if (err instanceof MessageSourceResolvable) {
				var message = messageSource.getMessage(err, LocaleContextHolder.getLocale());

				var errorMessageDTO = new ErrorMessageDTO(message, err.getField());

				errorsMessageDTO.add(errorMessageDTO);
			}

		});

		return new ResponseEntity<>(errorsMessageDTO, HttpStatus.BAD_REQUEST);

	}

}