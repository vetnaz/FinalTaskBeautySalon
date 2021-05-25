package epam.nazaruk.final_project.service.exception;

public class EntityNotFoundExceptionService extends ServiceException{
    public EntityNotFoundExceptionService() {
    }

    public EntityNotFoundExceptionService(String message) {
        super(message);
    }

    public EntityNotFoundExceptionService(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundExceptionService(Throwable cause) {
        super(cause);
    }
}
