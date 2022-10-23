public enum TaskStatus {
    NEW, CLOSED, DONE, IN_PROGRESS;

    @Override
    public String toString() {
        String priorityName = super.toString();
        return priorityName.charAt(0) + priorityName.substring(1).toLowerCase();
    }

    public static TaskStatus getStatus(String status) {
        return Arrays.stream(TaskStatus.values())
                .filter(st -> st.toString().equalsIgnoreCase(status)).findFirst().orElseThrow();
    }

}
