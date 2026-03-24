package seedu.address.model.employee;

/**
 * A class to represent a Task.
 */
public class Task {
    public static final String MESSAGE_CONSTRAINTS_TASK_NAME = "Enter a valid task name.";
    public static final String MESSAGE_CONSTRAINTS_TASK_DESCRIPTION = "Enter a valid task description.";
    private String taskName;
    private String taskDescription;
    private boolean isCompleted;
    public static int taskIndex = 1;
    private int currentTaskIndex;

    /**
     * Constructor for Task.
     * @param taskName the name of the task.
     * @param taskDescription the description of the task.
     */
    public Task(String taskName, String taskDescription, int currentTaskIndex) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.currentTaskIndex = currentTaskIndex;

    }


    public static boolean isValidTaskName(String taskName) {
        return taskName != null && !taskName.trim().isEmpty();
    }

    public static boolean isValidTaskDescription(String taskDescription) {
        return taskDescription != null && !taskDescription.trim().isEmpty();
    }

    /**
     * Returns the name of the task.
     *
     * @return The task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getTaskDescription() {
        return taskDescription;
    }

    /**
     * Returns true if the task is completed.
     *
     * @return True if completed, false otherwise.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        isCompleted = true;
    }

    public int getCurrentTaskIndex() {
        return currentTaskIndex;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return A string in the format "taskName: taskDescription".
     */
    @Override
    public String toString() {
        return "#" + currentTaskIndex + " " + taskName + ": " + taskDescription;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return taskName.equals(otherTask.taskName)
                && taskDescription.equals(otherTask.taskDescription);
    }


    public void incrementTaskIndex() {
        taskIndex++;
    }

    public static int getOverallIndex() {
        return taskIndex;
    }


}
