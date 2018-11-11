package be.clowdy.obtorque.transfer;

public final class MessageDTO {
    private String message;

    public static Builder create() {
        return new Builder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public static class Builder {

        private final MessageDTO messageDTO;

        public Builder() {
            messageDTO = new MessageDTO();
        }

        public Builder message(final String message) {
            messageDTO.message = message;
            return this;
        }

        public MessageDTO build() {
            return messageDTO;
        }
    }
}
