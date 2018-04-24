package saglio.louis.todo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.LinkedList;

public class DetailActivity extends Activity {

    private EditText nameView;
    private Spinner priorityView;
    private Button deleteView, updateView;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        nameView = findViewById(R.id.nameView);
        priorityView = findViewById(R.id.priorityView);
        deleteView = findViewById(R.id.deleteView);
        updateView = findViewById(R.id.updateView);

        LinkedList<String> list = new LinkedList<>();
        for (Priority priority : Priority.values()) {
            list.add(priority.toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        priorityView.setAdapter(dataAdapter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Long todoId = extras.getLong("task_id");
            task = Task.load(Task.class, todoId);
        }

        if (task == null) {
            setResult(RESULT_CANCELED);
            finish();
            return;
        }

        nameView.setText(task.getText());

        priorityView.setSelection(list.indexOf(task.getPriority().toString()));

        deleteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.delete();
                setResult(RESULT_OK);
                finish();

                finishOk();
            }
        });

        updateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.setText(nameView.getText().toString());
                String priorityString = priorityView.getSelectedItem().toString();
                task.setPriority(Priority.valueOf(priorityString));
                task.save();
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void finishOk() {
        Intent intent = new Intent();
        Bundle extras = new Bundle();
        intent.putExtras(extras);
        extras.putLong("task_id", task.getId());
        setResult(RESULT_OK, intent);
        finish();
    }
}
