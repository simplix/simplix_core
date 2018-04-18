package org.simplix.core.date_time;

import org.simplix.core.data.Conversions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class se_time {
    
    public static void wait_milli_seconds ( List<Object> milli_seconds ) {

        try {
            TimeUnit.MILLISECONDS.sleep ( Conversions.listToInt ( milli_seconds ) );
        } catch ( InterruptedException e ) {
            throw new RuntimeException ( e );
        }
        
    }
}
