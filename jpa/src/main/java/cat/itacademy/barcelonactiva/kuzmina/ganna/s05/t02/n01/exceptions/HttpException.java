package cat.itacademy.barcelonactiva.kuzmina.ganna.s05.t02.n01.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class HttpException extends RuntimeException{
    private final HttpStatus status;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
