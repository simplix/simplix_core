package org.simplix.core.files;

import org.simplix.core.data.Conversions;
import org.simplix.core.data.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class se_file_reader {
    
    public static List<Object> read ( List<Object> file_path ) {
        
        StringBuilder builder = new StringBuilder();
        
        Path filePath = Conversions.listToPath ( file_path );
        try {
            Files.lines ( filePath ).forEach ( line -> {
                builder.append ( line );
                builder.append ( StringUtils.currentOSNewLine );
            } );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
        return Conversions.stringToList ( builder.toString() );
    }

    public static List<Object> read_lines ( List<Object> file_path ) {
        
        List<Object> lines = new ArrayList<> ();
        Path filePath = Conversions.listToPath ( file_path );
        try {
            Files.lines ( filePath ).forEach ( line -> lines.add ( line ) );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
        return lines;
    }
}
