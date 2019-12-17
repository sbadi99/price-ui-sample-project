package com.goodrx.android.di.module;

import android.content.Context;
import com.goodrx.android.di.qualifier.ActivityContext;
import com.goodrx.android.di.scopes.ActivityScope;
import com.goodrx.android.viewmodel.PriceLiveData;
import dagger.Module;
import dagger.Provides;

/**
 * The main activity context module that provides required PriceLiveData dependency to Dagger graph.
 */
@Module public class MainActivityContextModule {
  private PriceLiveData priceLiveData;

  public Context context;

  public MainActivityContextModule(PriceLiveData priceLiveData) {
    this.priceLiveData = priceLiveData;
  }

  @Provides @ActivityScope public PriceLiveData providesPriceLiveData() {
    return priceLiveData;
  }

  @Provides @ActivityScope @ActivityContext public Context provideContext() {
    return context;
  }
}
