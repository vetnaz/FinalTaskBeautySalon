package epam.nazaruk.final_project.db.exception;

public class NoSuchRecordException  extends DAOException{
    public NoSuchRecordException() {
    }

    public NoSuchRecordException(String message) {
        super(message);
    }

    public NoSuchRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchRecordException(Throwable cause) {
        super(cause);
    }
}
