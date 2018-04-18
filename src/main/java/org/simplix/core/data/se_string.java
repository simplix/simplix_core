package org.simplix.core.data;

import java.util.List;

public class se_string {
    
    public static List<Object> current_OS_new_line() {
        
        return Conversions.stringToList ( StringUtils.currentOSNewLine );
    }
    
    public static List<Object> matches_regex ( List<Object> string, List<Object> regex ) {
        
        String str = Conversions.listToString ( string );
        String reg = Conversions.listToString ( regex );
        return Conversions.booleanToList ( str.matches ( reg ) );
    }
}
