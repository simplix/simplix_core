package org.simplix.core.files;

import org.simplix.core.data.Conversions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class se_directory {
    
    public static List<Object> files ( List<Object> directory ) {
        
        try {
            Stream<Path> stream = Files.list ( Conversions.listToPath ( directory ) );
            return pathStreamToFileList ( stream );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }
    
    public static List<Object> files_in_tree ( List<Object> directory ) {
        
        try {
            Stream<Path> stream = Files.walk ( Conversions.listToPath ( directory ) );
            return pathStreamToFileList ( stream );
        } catch ( IOException e ) {
            throw new RuntimeException ( e );
        }
    }

    private static List<Object> pathStreamToFileList ( Stream<Path> stream ) {
        
        return pathStreamToList ( stream.filter ( path -> Files.isRegularFile ( path ) ) );
    }

    private static List<Object>  pathStreamToList ( Stream<Path> stream ) {
        
        List<Object> result = new ArrayList<> ();
        stream.forEach ( path -> result.add ( path.toString() ) );
        
        return result;
    }
}
