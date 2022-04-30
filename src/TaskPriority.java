public enum TaskPriority {
    PLANNED, URGENT, DELEGATED, TRIVIAL;

    @Override
    public String toString() {
        String priorityName = super.toString();
        return priorityName.substring(0, 1) + priorityName.substring(1).toLowerCase();
    }
}
