package org.lacassandra.smooshyfaces.guice.modules;

import com.google.inject.AbstractModule;
import org.lacassandra.smooshyfaces.analytics.Analytics;
import org.lacassandra.smooshyfaces.analytics.NoOpAnalytics;


public class AnalyticsModule extends AbstractModule {
    protected void configure() {
        bind(Analytics.class).to(NoOpAnalytics.class);
    }
}
