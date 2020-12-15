package com.example.ex112;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * The type Main activity.
 *
 * @author Itey Weintraub <av5350@bs.amalnet.k12.il>
 * @version 1
 * @since 5 /12/2020 short description:      This activity let the user to use SharedPreferences counter data
 */
public class MainActivity extends AppCompatActivity {
    int counter;
    TextView countView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countView = (TextView) findViewById(R.id.countView);

        getData();
    }

    /**
     * Gets the last app run counter data (or 0 if its the first run)
     */
    public void getData()
    {
        SharedPreferences data = getSharedPreferences("COUNTER_DATA",MODE_PRIVATE);
        counter = data.getInt("counter", 0);
        countView.setText(String.valueOf(counter));
    }

    /**
     * Inc the counter value and show it on the textView.
     *
     * @param view the view
     */
    public void incCounter(View view) {
        counter++;
        countView.setText(String.valueOf(counter));
    }

    /**
     * Rest counter value(to 0) and show it on the textView.
     *
     * @param view the view
     */
    public void restCounter(View view) {
        counter = 0;
        countView.setText(String.valueOf(counter));
    }

    /**
     * Exit the app and save the counter value in file.
     *
     * @param view the view
     */
    public void exitApp(View view) {
        SharedPreferences data = getSharedPreferences("COUNTER_DATA",MODE_PRIVATE);
        SharedPreferences.Editor editor = data.edit();
        editor.putInt("counter",counter);
        editor.commit();

        finish();
    }

    /**
     * Create the options menu
     *
     * @param menu the menu
     * @return ture if success
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * go to credits activity if it was clicked at the menu
     *
     * @param item the item in menu that was clicked
     * @return true - if it success
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = (String) item.getTitle();

        // go to credits activity if clicked
        if (title.equals("Creadits"))
        {
            Intent si = new Intent(this, CreaditsActivity.class);
            startActivity(si);
        }

        return true;
    }
}