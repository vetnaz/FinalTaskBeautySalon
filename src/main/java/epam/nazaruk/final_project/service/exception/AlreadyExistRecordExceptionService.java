package epam.nazaruk.final_project.service.exception;

public class AlreadyExistRecordExceptionService extends ServiceException{
    public AlreadyExistRecordExceptionService() {
    }

    public AlreadyExistRecordExceptionService(String message) {
        super(message);
    }

    public AlreadyExistRecordExceptionService(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistRecordExceptionService(Throwable cause) {
        super(cause);
    }
}
