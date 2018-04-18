package org.simplix.core.os;

import org.simplix.core.data.Conversions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class se_in {
    
    static BufferedReader br = new BufferedReader ( new InputStreamReader (System.in) );

    public static List<Object> read_line() {
        
        /*
        Scanner scanner = new Scanner ( System.in );
        String line = scanner.nextLine();
System.out.println ( line );
        
        return Conversions.stringToList ( line );
*/

        // BufferedReader br = new BufferedReader ( new InputStreamReader (System.in) );
        try {
            String line = br.readLine ();
            return Conversions.stringToList ( line );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }
    
}