package epam.nazaruk.final_project.db.exception;

public class AlreadyExistRecordException extends DAOException{
    public AlreadyExistRecordException() {
    }

    public AlreadyExistRecordException(String message) {
        super(message);
    }

    public AlreadyExistRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistRecordException(Throwable cause) {
        super(cause);
    }
}
