package com.goodrx.android.di.component;

import android.content.Context;
import com.goodrx.android.GoodRxApp;
import com.goodrx.android.api.RetrofitApiClient;
import com.goodrx.android.di.module.ContextModule;
import com.goodrx.android.di.module.RetrofitModule;
import com.goodrx.android.di.qualifier.ApplicationContext;
import com.goodrx.android.di.scopes.ApplicationScope;
import dagger.Component;

/**
 * This class provides the Dagger application component and App context to allow for dependency injection.
 */

@ApplicationScope
@Component(modules = { ContextModule.class, RetrofitModule.class})
public interface ApplicationComponent {

    RetrofitApiClient getApiInterface();

    @ApplicationContext Context getContext();

    void injectApplication(GoodRxApp myApplication);

}
