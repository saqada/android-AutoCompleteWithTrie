package saqada.com.autocompletetriesearch;

/**
 * Created by saqada on 22.02.2018.
 */

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import java.util.ArrayList;
import java.util.List;

public class AutoCompleteAdapter extends ArrayAdapter
{
    private ArrayList<Object> allItems;
    private ArrayList<Object> allItemsListClone;
    private ArrayList<Object> suggestionsList;
    private List<Object>      triableList;
    private Trie              trie;

    private FilterListeners   filterListeners;

    public AutoCompleteAdapter(Context context, int resource, ArrayList<Object> items)
    {
        super(context, resource, 0, items);

        this.allItems = items;
        this.allItemsListClone = (ArrayList<Object>) this.allItems.clone();
        this.suggestionsList = new ArrayList<>();
        this.triableList = new ArrayList<>();

        this.triableList.addAll(this.allItemsListClone);
        this.trie = new Trie(this.triableList);
    }

    public void setFilterListeners(FilterListeners filterFinishedListener)
    {
        this.filterListeners = filterFinishedListener;
    }

    @Override
    public Filter getFilter()
    {
        return nameFilter;
    }

    Filter nameFilter = new Filter()
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint)
        {
            if (constraint != null)
            {
                String constraintString = constraint.toString().trim();
                suggestionsList.clear();

                List<Integer> indicesList = trie.getIndices(trie.getRootVertex(), constraintString);

                if (indicesList != null)
                {
                    for (int index : indicesList)
                    {
                        suggestionsList.add(allItemsListClone.get(index));
                    }
                }

                FilterResults filterRes = new FilterResults();
                filterRes.values = suggestionsList;
                filterRes.count = suggestionsList.size();
                return filterRes;
            }
            else
            {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results)
        {
            if (results != null)
            {
                List<Object> filterList = (ArrayList<Object>) results.values;

                if (filterListeners != null && filterList != null)
                    filterListeners.filteringFinished(filterList);

                if (results.count > 0)
                {
                    clear();
                    addAll(filterList);
                    notifyDataSetChanged();
                }
            }
        }
    };
}
