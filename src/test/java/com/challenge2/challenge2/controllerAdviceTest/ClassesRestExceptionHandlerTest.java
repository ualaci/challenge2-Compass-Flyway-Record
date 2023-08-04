package com.challenge2.challenge2.controllerAdviceTest;

import com.challenge2.challenge2.entities.ErrorResponse;
import com.challenge2.challenge2.restControllers.ClassController;
import com.challenge2.challenge2.restControllers.controllersAdvices.ClassesRestExceptionHandler;
import com.challenge2.challenge2.services.impl.ClassServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ClassController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClassesRestExceptionHandlerTest {

    @MockBean
    private ClassServiceImpl classService;

    @Test
    public void testHandleIllegalArgumentException() {
        ClassesRestExceptionHandler classesRestExceptionHandler = new ClassesRestExceptionHandler();

        IllegalArgumentException exception = new IllegalArgumentException("Teste de exceção");

        WebRequest webRequest = mock(WebRequest.class);

        ResponseEntity<Object> responseEntity = classesRestExceptionHandler.handleIllegalArgumentException(exception, webRequest);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorResponse errorResponse = (ErrorResponse) responseEntity.getBody();
        assertEquals("Illegal Argument Exception: Teste de exceção", errorResponse.getMessage());

        assertEquals(HttpStatus.BAD_REQUEST.name(), errorResponse.getHttpStatus());
    }


}
