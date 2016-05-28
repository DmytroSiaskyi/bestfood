package com.bestfood.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public class BaseController {
    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageSource messageSource;

    /**
     * Message bandle translation method
     *
     * @param key  key of message
     * @param args params that message contains
     * @return     full translated message
     */
    protected String i18n(String key, Object... args) {
        return messageSource.getMessage(key, args, key, LocaleContextHolder.getLocale());
    }
}
