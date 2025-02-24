package hacka.mangue.contests.service.exceptions;

import java.io.Serial;

public class NotFoundException extends BusinessException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super("Resource not found.");
    }

}