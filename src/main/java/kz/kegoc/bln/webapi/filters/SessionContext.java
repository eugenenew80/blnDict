package kz.kegoc.bln.webapi.filters;

import kz.kegoc.bln.entity.common.Lang;
import kz.kegoc.bln.entity.adm.User;

public class SessionContext {
    public Lang getLang() {
        return lang;
    }
    public User getUser() {
        return user;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
    }
    public void setUser(User user) {
        this.user = user;
    }

    private Lang lang;
    private User user;
}
