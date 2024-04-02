package com.demovalidation.handler;

import com.demovalidation.model.vo.ResponseVO;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
@RestControllerAdvice
// 将优先级调整为最高
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler {
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseVO validExceptionHandler(BindException e) {
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            message.append(error.getField()).append(error.getDefaultMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ResponseVO.builder().code(400).message(message.toString()).timestamp(Instant.now().toEpochMilli()).build();
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("{}", e.getMessage(), e);
        List<String> defaultMsg = e.getBindingResult().getFieldErrors()
                .stream()
                .map(fieldError -> "【" + fieldError.getField() + "】" + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return ResponseVO.builder().code(400).message(defaultMsg.get(0)).timestamp(Instant.now().toEpochMilli()).build();
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseVO handleConstraintViolationException(ConstraintViolationException e) {
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            Path path = violation.getPropertyPath();
            String[] pathArr = splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(pathArr[1]).append(violation.getMessage()).append(",");
        }
        message = new StringBuilder(message.substring(0, message.length() - 1));
        return ResponseVO.builder().code(400).message(message.toString()).timestamp(Instant.now().toEpochMilli()).build();
    }

    public static String[] splitByWholeSeparatorPreserveAllTokens(String str, String separator) {
        if (str == null || str.isEmpty()) {
            return new String[0];
        }

        if (separator == null || separator.isEmpty()) {
            return new String[] { str };
        }

        List<String> tokens = new ArrayList<>();
        int currentIndex = 0;
        int separatorLength = separator.length();

        while (true) {
            int indexOfSeparator = str.indexOf(separator, currentIndex);
            if (indexOfSeparator == -1) {
                tokens.add(str.substring(currentIndex));
                break;
            }

            tokens.add(str.substring(currentIndex, indexOfSeparator));
            currentIndex = indexOfSeparator + separatorLength;
        }

        return tokens.toArray(new String[0]);
    }
}
