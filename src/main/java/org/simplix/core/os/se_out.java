package org.simplix.core.os;

import org.simplix.core.data.TreeUtils;

import java.util.List;

public class se_out {
    
    public static void write ( List<Object> list ) {
        
        TreeUtils.forEachStringNodeInTree ( list, element -> { System.out.print ( element ); } );
    }
    
    public static void write_line ( List<Object> list ) {
        
        write ( list );
        System.out.println();
    }
    
    public static void write_lines ( List<Object> list ) {

        TreeUtils.forEachStringNodeInTree ( list, element -> { System.out.println ( element ); } );
    }
    
    public static void write_new_line() {
        
        System.out.println();
    }
    
}