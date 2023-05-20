package com.commerce.web.global.uitil;

import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;
import java.util.Date;

@UtilityClass
public class DateUtils {

    public Date now() {
        return new Date();
    }

    public Date addTime(Date date, long ms) {
        return new Date(date.getTime() + ms);
    }

}
