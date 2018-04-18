package org.simplix.core.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class se_check {
    
    public static List<Object> is_equal ( List<Object> data_1, List<Object> data_2 ) {
        return se_tree.is_equal ( data_1, data_2 );
    }

    public static List<Object> is_yes_no ( List<Object> data ) {
        return Conversions.booleanToList ( CheckUtils.isYesNo ( data ) );
    }

    public static List<Object> is_number ( List<Object> data ) {
        return Conversions.booleanToList ( CheckUtils.isNumber ( data ) );
    }

}
