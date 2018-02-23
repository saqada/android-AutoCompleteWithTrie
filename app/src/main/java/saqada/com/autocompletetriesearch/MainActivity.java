package saqada.com.autocompletetriesearch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements FilterListeners
{
    private static final String LOG_TAG = "AutoCompleteTrie";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AutoCompleteTextView autoComplete = (AutoCompleteTextView) findViewById(R.id.autoComplete);
        autoComplete.setThreshold(1);

        ArrayList<Object> stringList = getRandomFilledList();

        final AutoCompleteAdapter adapter = new AutoCompleteAdapter(this, android.R.layout.simple_list_item_1, stringList);
        adapter.setFilterListeners(this);
        autoComplete.setAdapter(adapter);
    }

    private ArrayList<Object> getRandomFilledList()
    {
        ArrayList<Object> stringList = new ArrayList<>();

        for (int i = 0; i < 10000; i++)
        {
            String someText = i % 10 + "" + i % 7 + "" + i % 5 + "" + i % 3 + "" + i % 2 + "";
            stringList.add(someText);
        }
        return stringList;
    }

    @Override
    public void filteringFinished(List<Object> filteredItems)
    {
        Log.i(LOG_TAG, "filteredItems count = " + filteredItems.size());
    }
}