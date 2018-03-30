package saglio.louis.todo;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by louis on 29/03/18.
 */

public class Helper {
    private static final Helper ourInstance = new Helper();
    public HashMap<Priority, Integer> colorMap;

    public static Helper getInstance() {
        return ourInstance;
    }

    private Helper() {
        colorMap = new HashMap<>();
        colorMap.put(Priority.High, R.color.colorAccent);
        colorMap.put(Priority.Normal, R.color.colorPrimary);
    }

    public int getTaskColor(Priority priority) {
        return colorMap.get(priority);
    }

    public LinkedList<Task> genarateTasks() {
        LinkedList<Task> tasks = new LinkedList<>();
        tasks.add(new Task("tache 1", false, Priority.High));
        tasks.add(new Task("tache 2", false, Priority.Normal));
        return tasks;
    }
}
