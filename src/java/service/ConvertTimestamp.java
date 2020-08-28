/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nguye
 */
public class ConvertTimestamp {

    public String convertTimeNotify(Timestamp timeTmp) {
        String result = "";
        long timeNotify = timeTmp.getTime();
        Date date = new Date();
        long timeCurrent = date.getTime();
        long hh = timeCurrent - timeNotify;
        long hours = TimeUnit.MILLISECONDS.toHours(hh);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(hh);
        if (hours > 0 && hours < 24) {
            result = hours + " giờ trước";
        } else if (minutes < 60 && minutes > 0) {
            result = minutes + " phút trước";
        } else if (hours == 0 && minutes == 0) {
            result = "vài giây trước";
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'Tháng' MM 'lúc' HH:mm");
            result = dateFormat.format(timeTmp);
        }
        return result;
    }

    public Timestamp convertDay(Integer dd, Integer mm, Integer yy) {
        Timestamp tmp = null;
        String date = dd.toString() + '/' + mm.toString() + '/' + yy.toString();
        try {
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(date);
            tmp = new java.sql.Timestamp(date1.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ConvertTimestamp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tmp;
    }
}
