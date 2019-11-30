package roomdemo.wiseass.com.roomdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import java.util.List;

import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository;

public class ListItemCollectionViewModel extends ViewModel {

    private ListItemRepository repository;

    ListItemCollectionViewModel(ListItemRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<ListItem>> getListItems() {
        return repository.getListOfData();
    }

    public void deleteListItem(ListItem listItem) {
        DeleteItemTask deleteItemTask = new DeleteItemTask();
        deleteItemTask.execute(listItem);
    }

    private class DeleteItemTask extends AsyncTask<ListItem, Void, Void> {

        @Override
        protected Void doInBackground(ListItem... item) {
            repository.deleteListItem(item[0]);
            return null;
        }
    }

}

