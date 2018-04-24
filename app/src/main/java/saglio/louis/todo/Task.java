package saglio.louis.todo;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by louis on 29/03/18.
 */

@Table(name = "Tasks")
public class Task extends Model {

    public void setText(String text) {
        this.text = text;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Column(name = "text")
    private String text;

    @Column(name = "checked")
    private boolean checked;

    @Column(name = "priority")
    private Priority priority;

    public Task() {
        super();
    }

    public Task(String text, boolean checked, Priority priority) {
        super();
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

    public String getCheckedString() {
        return " " + this.checked + " ";
    }

    @Override
    public String toString() {
        return "Task{" +
                "text='" + text + '\'' +
                ", checked=" + checked +
                ", priority=" + priority +
                '}';
    }

    public static List<Task> getAll() {
        return new Select().from(Task.class).execute();
    }
}
