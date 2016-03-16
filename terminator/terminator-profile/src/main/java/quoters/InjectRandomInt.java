package quoters;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// Can read in runtime by reflection
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
    int min();

    int max();
}