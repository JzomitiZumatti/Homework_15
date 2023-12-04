public enum Messages {
    GREETINGS("Hello!"),
    FAREWELL("See you next time!"),
    QUESTION("what is palianytsia?");
    private final String message;

    Messages(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
