package game.engine.tools;

public enum HTTPStatus {

    CONTINUE(100, "Kontynuuj"),
    CONNECTION_REFUSED(111, "Serwer odrzucił połączenie"),
    OK(200, "OK"),
    CREATED(201, "Utworzono pomyślnie."),
    NOT_FOUND(404, "Nie znaleziono"),
    METHOD_NOT_ALLOWED(405, "Niedozwolona metoda"),
    CONFLICT(409, "Konflikt - żądanie nie może być zrealizowane, ponieważ występuje konflikt z " +
            "obecnym statusem zasobu. Prawdopodobnie zasób już istnieje."),
    NOT_IMPLEMENTED(501, "Nie zaimplementowano");

    private int code;
    private String message;

    HTTPStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static HTTPStatus getValue(int code) {

        for (HTTPStatus httpStatus : HTTPStatus.values()) {
            if (httpStatus.getCode() == code) {
                return httpStatus;
            }
        }
        throw new IllegalArgumentException("Kod " + code + " nie istnieje!");
    }

    @Override
    public String toString() {
        return "[ " + code + " ] [ " + message + " ]";
    }
}
