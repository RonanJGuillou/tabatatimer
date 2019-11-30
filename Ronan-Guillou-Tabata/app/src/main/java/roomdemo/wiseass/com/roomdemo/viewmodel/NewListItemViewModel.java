package roomdemo.wiseass.com.roomdemo.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository;

public class NewListItemViewModel extends ViewModel {

    private ListItemRepository repository;

    NewListItemViewModel(ListItemRepository repository) {
        this.repository = repository;
    }

    /**
     * Attach our LiveData to the Database
     */
    public void addNewItemToDatabase(ListItem listItem){
        new AddItemTask().execute(listItem);
    }

    private class AddItemTask extends AsyncTask<ListItem, Void, Void> {

        @Override
        protected Void doInBackground(ListItem... item) {
            repository.createNewListItem(item[0]);
            return null;
        }
    }
}
