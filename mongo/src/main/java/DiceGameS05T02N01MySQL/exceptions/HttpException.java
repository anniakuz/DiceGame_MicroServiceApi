package DiceGameS05T02N01MySQL.exceptions;

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
