package roomdemo.wiseass.com.roomdemo.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Singleton;

import roomdemo.wiseass.com.roomdemo.data.ListItemRepository;

@Singleton
public class CustomViewModelFactory implements ViewModelProvider.Factory {
    private final ListItemRepository repository;

    @Inject
    public CustomViewModelFactory(ListItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ListItemCollectionViewModel.class))
            return (T) new ListItemCollectionViewModel(repository);

        else if (modelClass.isAssignableFrom(ListItemViewModel.class))
            return (T) new ListItemViewModel(repository);

        else if (modelClass.isAssignableFrom(NewListItemViewModel.class))
            return (T) new NewListItemViewModel(repository);

        else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}
