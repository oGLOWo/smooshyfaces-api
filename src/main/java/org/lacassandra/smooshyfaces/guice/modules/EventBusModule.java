package org.lacassandra.smooshyfaces.guice.modules;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

import java.lang.reflect.Method;
import java.util.concurrent.Executors;

public class EventBusModule extends AbstractModule {

    @Override
    protected void configure() {
        final EventBus eventBus = new AsyncEventBus("local system-wide eventbus", Executors.newCachedThreadPool());
        bind(EventBus.class).toInstance(eventBus);
        bindListener(Matchers.any(), new TypeListener() {
            @Override
            public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
                for (Method method : type.getRawType().getMethods()) {
                    if (method.isAnnotationPresent(Subscribe.class)) {
                        encounter.register(new InjectionListener<I>() {
                            @Override
                            public void afterInjection(final I injectee) {
                                eventBus.register(injectee);
                            }
                        });
                    }
                }
            }
        });
    }
}
