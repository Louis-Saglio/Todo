package saglio.louis.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class Adapter extends ArrayAdapter {

    private static int resourceLayout = R.layout.item;
    private List<Task> tasks;

    public Adapter(Context context, List<Task> tasks) {
        super(context, resourceLayout, tasks);
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(resourceLayout, null);
        }

        Task currentTask = tasks.get(position);

        TextView taskText = convertView.findViewById(R.id.todo_id);
        taskText.setText(currentTask.getText());

        CheckBox checkBox = convertView.findViewById(R.id.check_box);
        checkBox.setChecked(currentTask.isChecked());

        Helper helper = Helper.getInstance();

        LinearLayout itemLayout = convertView.findViewById(R.id.item_id);
        itemLayout.setBackgroundResource(helper.getTaskColor(currentTask.getPriority()));

        return convertView;
    }
}
