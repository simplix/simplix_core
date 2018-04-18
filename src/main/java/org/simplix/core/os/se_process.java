package org.simplix.core.os;

import org.simplix.core.data.Conversions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class se_process {
    
    private static String[] process_args;
    
    // called by main method (application launch)
    public static void save_process_args ( String[] args ) {
        process_args = args;
    }
    
    public static List<Object> arguments() {
        return new ArrayList<> ( Arrays.asList ( process_args) );
    }
    
    public static void exit_with_success() {
        System.exit(0);
    }
    
    public static void exit_with_error ( List<Object> error_code ) {
        System.exit ( Conversions.listToInt ( error_code ) );
    }
    
    public static void wait_until_killed() {
    
        while ( true ) {
            try {
                TimeUnit.MILLISECONDS.sleep ( 10 );
            } catch ( InterruptedException e ) {
                throw new RuntimeException ( e );
            }
        }
    }
    
}