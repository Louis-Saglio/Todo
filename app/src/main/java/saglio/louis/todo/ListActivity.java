package saglio.louis.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.LinkedList;

public class ListActivity extends Activity {

    private LinkedList<Task> tasks = new LinkedList<>();
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        tasks.addAll(Task.getAll());
//        tasks = Helper.getInstance().generateTasks();

        listView = findViewById(R.id.list_cards);
        adapter = new Adapter(this, tasks);
        listView.setAdapter(adapter);

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Evenement non capturé pour une raison inconnue");
                Task currentTask = tasks.get(i);
                currentTask.inverseChecked();
                currentTask.save();
                adapter.notifyDataSetChanged();
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle extras = new Bundle();
                extras.putLong("task_id", tasks.get(i).getId());
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                intent.putExtras(extras);
                startActivityForResult(intent, 0);
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();

        if (requestCode == 0) {
            Task newTask = new Task(extras.getString("text"), false, Priority.valueOf(extras.getString("priority")));
            newTask.save();
            tasks.add(newTask);
            adapter.notifyDataSetChanged();
        }

        if (requestCode == 1) {
            if (resultCode == RESULT_OK && extras != null) {
                final Long task_id = extras.getLong("task_id");
                Task modifiedTask = Task.load(Task.class, task_id);
                for (Task task : tasks) {
                    if (task.getId().equals(task_id)) {
                        if (modifiedTask == null) {
                            tasks.remove(task);
                            break;
                        } else {
                            tasks.set(tasks.indexOf(task), modifiedTask);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
