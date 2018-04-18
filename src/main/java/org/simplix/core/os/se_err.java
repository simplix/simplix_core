package org.simplix.core.os;

import org.simplix.core.data.TreeUtils;

import java.util.List;

public class se_err {

    public static void write ( List<Object> list ) {
        
        TreeUtils.forEachStringNodeInTree ( list, element -> { System.err.print ( element ); } );
    }
    
    public static void write_line ( List<Object> list ) {
        
        write ( list );
        System.err.println();
    }
    
    public static void write_lines ( List<Object> list ) {

        TreeUtils.forEachStringNodeInTree ( list, element -> { System.err.println ( element ); } );
    }
    
    public static void write_new_line() {
        
        System.err.println();
    }
    
}
