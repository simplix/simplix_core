package org.simplix.core.os;

import org.simplix.core.data.Conversions;

import java.util.List;

public class se_OS_command {

    /* needed?
                if token is null then
                    java
                        // doesn't work
                        // command_tokens.add ( "" );

                        if ( c_OS_is_Windows.getJavaBoolean() ) {
                            command_tokens.add ( "\"\"" );
                        } else {
                            command_tokens.add ( "''" );
                        }
                    end java
                else
                    java
                        command_tokens.add ( c_token.getJavaString() );
                    end java
                .
    */
    
    public static List<Object> run_and_wait ( List<Object> command_tokens ) {
        
        return run ( command_tokens, true );
    }

    public static void start_and_continue ( List<Object> command_tokens ) {

        run ( command_tokens, false );
    }

    /* TODO
    public static List<Object> run_OS_script_file_and_wait ( List<Object> file_path, List<Object> parameters )
    public static void start_OS_script_file_and_continue ( List<Object> file_path, List<Object> parameters )
    */

    private static List<Object> run ( List<Object> command_tokens, boolean wait ) {

        List<String> tokens = Conversions.listToStringList ( command_tokens );
        
        ProcessBuilder builder = new ProcessBuilder ( tokens );
        
        builder.redirectInput ( ProcessBuilder.Redirect.INHERIT );
        builder.redirectOutput ( ProcessBuilder.Redirect.INHERIT );
        builder.redirectError ( ProcessBuilder.Redirect.INHERIT );
        
        try {
            Process process = builder.start ();
            if ( wait ) {
                int exitValue = process.waitFor ();
                return Conversions.intToList ( exitValue );
            } else {
                return null;
            }
        } catch ( Exception e ) {
            throw new RuntimeException ( e );
        }
    }

}
