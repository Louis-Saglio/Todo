package saglio.louis.todo;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.LinkedList;
import java.util.List;

public class AddActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final Spinner spinner = findViewById(R.id.spinner);
        LinkedList<String> list = new LinkedList<>();
        for (Priority priority : Priority.values()) {
            list.add(priority.toString());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list);
        spinner.setAdapter(dataAdapter);

        findViewById(R.id.save_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddActivity.this, ListActivity.class);

                EditText text = findViewById(R.id.text);


                Bundle extras = new Bundle();
                extras.putString("text", text.getText().toString());
                extras.putString("priority", spinner.getSelectedItem().toString());
                intent.putExtras(extras);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

}
