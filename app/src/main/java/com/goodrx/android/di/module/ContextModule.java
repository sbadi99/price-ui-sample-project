package com.goodrx.android.di.module;

import android.content.Context;
import com.goodrx.android.di.qualifier.ApplicationContext;
import com.goodrx.android.di.scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

/**
 * This class provides the the Context module so Dagger can compose the required dependency.
 */

@Module
public class ContextModule {
    public Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @ApplicationScope
    @ApplicationContext
    public Context provideContext() {
        return context;
    }
}
