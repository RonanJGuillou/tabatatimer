package roomdemo.wiseass.com.roomdemo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import roomdemo.wiseass.com.roomdemo.data.ListItem;
import roomdemo.wiseass.com.roomdemo.data.ListItemRepository;

public class ListItemViewModel extends ViewModel {

    private ListItemRepository repository;

    ListItemViewModel(ListItemRepository repository) {
        this.repository = repository;
    }

    public LiveData<ListItem> getListItemById(String itemId){
       return repository.getListItem(itemId);
    }

}

