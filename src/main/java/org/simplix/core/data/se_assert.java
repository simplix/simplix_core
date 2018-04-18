package org.simplix.core.data;

import java.util.List;

public class se_assert {
    
    public static void is_equal ( List<Object> data_1, List<Object> data_2 ) {

        if ( ! TreeUtils.isEqual ( data_1, data_2 ) )
            throw new RuntimeException ( 
                Conversions.listToLimitedString ( data_1 ) + " is not equal to " + 
                Conversions.listToLimitedString ( data_2 ) );
    }

    public static void is_yes_no ( List<Object> data ) {
        if ( ! CheckUtils.isYesNo ( data ) )
            throw new RuntimeException ( Conversions.listToLimitedString ( data ) + " is not yes and not no." );
    }

    public static void is_number ( List<Object> data ) {
        if ( ! CheckUtils.isNumber ( data ) )
            throw new RuntimeException ( Conversions.listToLimitedString ( data ) + " is not a number." );
    }

}
