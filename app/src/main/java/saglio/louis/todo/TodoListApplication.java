package saglio.louis.todo;

import com.activeandroid.ActiveAndroid;

public class TodoListApplication extends com.activeandroid.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
