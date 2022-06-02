package com.trantor.trantorapp.util;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;

/**
 * HeaderUtil
 *
 * @author Milver Flores Acevedo
 * @since 1.0
 */
public class HeaderUtil {

    private HeaderUtil() { }

    public static void setHeaders(HttpHeaders headers, Page<?> page) {
        headers.add("X-Total", String.valueOf(page.getTotalElements()));
        headers.add("X-Total-Pages", String.valueOf(page.getTotalPages()));
        headers.add("X-Total-Size", String.valueOf(page.getSize()));
    }
}
