package se.quiz;



    public class SQLRepositoryException extends RuntimeException {

        public SQLRepositoryException() {
        }

        public SQLRepositoryException(String message) {
            super(message);
        }

        public SQLRepositoryException(String message, Throwable cause) {
            super(message, cause);
        }

        public SQLRepositoryException(Throwable cause) {
            super(cause);
        }

        public SQLRepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

