package org.lacassandra.smooshyfaces.guice.modules;


import com.google.inject.AbstractModule;
import com.google.inject.MembersInjector;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import org.lacassandra.smooshyfaces.guice.annotations.InjectSLF4JLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class SLF4JLoggingModule extends AbstractModule {

    @Override
    protected void configure() {
        bindListener(Matchers.any(), new TypeListener() {
            @Override
            public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
                for (final Field field : type.getRawType().getDeclaredFields()) {
                    if (field.getType() == Logger.class && field.isAnnotationPresent(InjectSLF4JLog.class)) {
                        encounter.register(new MembersInjector<I>() {
                            @Override
                            public void injectMembers(final I instance) {
                                Logger logger = LoggerFactory.getLogger(field.getDeclaringClass());
                                field.setAccessible(true);
                                try {
                                    field.set(instance, logger);
                                }
                                catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        });
                    }
                }
            }
        });
    }
}
