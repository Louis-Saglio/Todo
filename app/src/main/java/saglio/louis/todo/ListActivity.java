package saglio.louis.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.LinkedList;

public class ListActivity extends Activity {

    private LinkedList<Task> tasks;
    private ListView listView;
    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        tasks = Helper.getInstance().genarateTasks();

        listView = findViewById(R.id.list_cards);
        adapter = new Adapter(this, tasks);
        listView.setAdapter(adapter);

        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, AddActivity.class);
                startActivityForResult(intent, 0);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("Evenement non capturé pour une raison inconnue");
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle extras = data.getExtras();
        System.out.println(Priority.valueOf(extras.getString("priority")));
        Task newTask = new Task(extras.getString("text"), false, Priority.valueOf(extras.getString("priority")));
        tasks.add(newTask);
        adapter.notifyDataSetChanged();
    }
}
