package com.javaforever.clocksimplejee4.modeler;

import java.math.BigDecimal;
import java.sql.Date;

import com.javaforever.clocksimplejee4.bo.AttendanceStatus;
import com.javaforever.clocksimplejee4.utils.IModel;
import com.javaforever.clocksimplejee4.utils.IModeler;
import com.javaforever.clocksimplejee4.utils.Row;

public class AttendanceStatusModeler implements IModeler {
	 
    public IModel doModel(Row r){  
        if (!validate(r)) {  
            return null;  
        }  
        else {  
            try {  
                AttendanceStatus attendanceStatus = new AttendanceStatus();  
                String[] rValue = r.getFields();  
                attendanceStatus.setUserId(Long.valueOf(rValue[0]));  
                attendanceStatus.setEmpId(Long.valueOf(rValue[1]));  
                attendanceStatus.setUsername(rValue[2]);  
                attendanceStatus.setFullName(rValue[3]);  
                attendanceStatus.setDate(Date.valueOf(rValue[4]));  
                attendanceStatus.setAttendanceStatus(rValue[5]);  
                attendanceStatus.setDescription(rValue[6]); 
                attendanceStatus.setDuration(new BigDecimal(rValue[7]));
                return attendanceStatus;  
            } catch (Exception e){  
                return null;  
            }  
        }     
    }  
      
    public boolean validate(Row r){  
        if (r==null || r.getFieldsNumber()!= 9)  
            return false;  
        for (int i=0; i < r.getFieldsNumber();i++){  
            if (r.getField(i)==null || "".equals(r.getField(i))){  
                return false;  
            }  
        }  
        try { 
            Long.parseLong(r.getField(0));  
            Long.parseLong(r.getField(1));  
            Date.valueOf(r.getField(4));  
            new BigDecimal(r.getField(7));
        } catch (Exception e){  
            return false;  
        }  
        return true;  
    }  
      
    public Row deModel(IModel attendanceStatusModel){  
        if (attendanceStatusModel == null) {  
            return Row.EMPTY_ROW;  
        }  
        else {  
            try {  
                AttendanceStatus attendanceStatus= (AttendanceStatus) attendanceStatusModel;  
                Row r = new Row(9);  
      
                r.setField(0,""+attendanceStatus.getUserId());  
                r.setField(1,""+attendanceStatus.getEmpId());  
                r.setField(2,attendanceStatus.getUsername());  
                r.setField(3,attendanceStatus.getFullName());  
                r.setField(4,""+attendanceStatus.getDate());  
                r.setField(5,attendanceStatus.getAttendanceStatus());  
                r.setField(6,attendanceStatus.getDescription());  
                r.setField(7,attendanceStatus.getDuration().toString());  
                
                return r;  
            } catch (Exception e){  
                return Row.EMPTY_ROW;  
            }  
        }     
    }  

}
