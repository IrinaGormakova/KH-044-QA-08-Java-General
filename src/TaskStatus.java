public enum TaskStatus {
    NEW, CLOSED, DONE, IN_PROGRESS;

    @Override
    public String toString() {
        String priorityName = super.toString();
        return priorityName.charAt(0) + priorityName.substring(1).toLowerCase();
    }
}
