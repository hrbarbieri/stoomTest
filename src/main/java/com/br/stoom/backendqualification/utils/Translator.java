package com.br.stoom.backendqualification.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
public class Translator {

   private static ResourceBundleMessageSource messageSource;

   @Autowired
   Translator(ResourceBundleMessageSource messageSource) {
      Translator.messageSource = messageSource;
   }

   public static String getText(String msg, String[] parans) {
      Locale locale = LocaleContextHolder.getLocale();
      return messageSource.getMessage(msg, parans, locale);
   }
   
   public static String getText(String msg) {
	      Locale locale = LocaleContextHolder.getLocale();
	      return messageSource.getMessage(msg, null, locale);
	   }
}