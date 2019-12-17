package com.goodrx.android;

import android.content.Context;
import android.location.LocationManager;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.location.LocationManagerCompat;
import androidx.multidex.MultiDexApplication;
import com.goodrx.android.di.component.ApplicationComponent;
import com.goodrx.android.di.component.DaggerApplicationComponent;
import com.goodrx.android.di.module.ContextModule;
import javax.inject.Inject;
import timber.log.Timber;

/**
 * This GoodRxApp Application class extends MultiDexApplication
 */

public class GoodRxApp extends MultiDexApplication {

  private static ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initComponent();
  }

  /**
   * Initialize Application Component (Dagger)
   */
  protected void initComponent() {
    applicationComponent =
        DaggerApplicationComponent.builder().contextModule(new ContextModule(this)).build();
    applicationComponent.injectApplication(this);
  }

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }




}
