package com.goodrx.android.di.component;

import com.goodrx.android.di.module.ContextModule;
import com.goodrx.android.di.module.MainActivityContextModule;
import com.goodrx.android.di.scopes.ActivityScope;
import com.goodrx.android.viewmodel.PriceLiveData;
import dagger.Component;

/**
 * This class provides the main activity component for Dagger.
 */
@ActivityScope
@Component(modules = {ContextModule.class, MainActivityContextModule.class},dependencies = ApplicationComponent.class)
public interface MainActivityComponent {
    void injectMainActivity(PriceLiveData priceLiveData);
}
