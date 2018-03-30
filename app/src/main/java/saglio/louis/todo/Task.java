package saglio.louis.todo;

/**
 * Created by louis on 29/03/18.
 */

public class Task {

    private String text;

    private boolean checked;
    private Priority priority;

    public Task(String text, boolean checked, Priority priority) {
        this.text = text;
        this.checked = checked;
        this.priority = priority;
    }

    public String getText() {
        return text;
    }

    public boolean isChecked() {
        return checked;
    }

    public Priority getPriority() {
        return priority;
    }

    public void inverseChecked() {
        this.checked = !this.checked;
    }

    @Override
    public String toString() {
        return "Task{" +
                "text='" + text + '\'' +
                ", checked=" + checked +
                ", priority=" + priority +
                '}';
    }
}
