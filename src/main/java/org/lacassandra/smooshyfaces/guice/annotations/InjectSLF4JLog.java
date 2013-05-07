package org.lacassandra.smooshyfaces.guice.annotations;

import javax.inject.Scope;
import java.lang.annotation.*;

@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
public @interface InjectSLF4JLog {

}
