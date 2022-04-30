public enum TaskPriority {
    PLANNED, URGENT, DELEGATED, TRIVIAL;

    @Override
    public String toString() {
        String priorityName = super.toString();
        return priorityName.charAt(0) + priorityName.substring(1).toLowerCase();
    }
}
