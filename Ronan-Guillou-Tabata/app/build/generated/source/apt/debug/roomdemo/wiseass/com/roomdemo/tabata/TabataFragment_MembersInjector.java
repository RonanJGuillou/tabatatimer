// Generated by dagger.internal.codegen.ComponentProcessor (https://google.github.io/dagger).
package roomdemo.wiseass.com.roomdemo.tabata;

import android.arch.lifecycle.ViewModelProvider;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class TabataFragment_MembersInjector implements MembersInjector<TabataFragment> {
  private final Provider<ViewModelProvider.Factory> viewModelFactoryProvider;

  public TabataFragment_MembersInjector(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    this.viewModelFactoryProvider = viewModelFactoryProvider;
  }

  public static MembersInjector<TabataFragment> create(
      Provider<ViewModelProvider.Factory> viewModelFactoryProvider) {
    return new TabataFragment_MembersInjector(viewModelFactoryProvider);
  }

  @Override
  public void injectMembers(TabataFragment instance) {
    injectViewModelFactory(instance, viewModelFactoryProvider.get());
  }

  public static void injectViewModelFactory(
      TabataFragment instance, ViewModelProvider.Factory viewModelFactory) {
    instance.viewModelFactory = viewModelFactory;
  }
}
