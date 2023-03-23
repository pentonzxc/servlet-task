package com.innowise.ejbtask.util;

import java.util.List;

public class PageConstants {

    private PageConstants() {
    }


    public final static String ADMIN_PREFIX = "adm-";

    public final static List<String> ROLE_DEPENDS_PAGES = List.of(
            "list"
    );

    public final static List<String> PERMIT_ALL = List.of(
            "register",
            "login",
            "index",
            "register.jsp",
            "login.jsp",
            "index.jsp"
    );
}
