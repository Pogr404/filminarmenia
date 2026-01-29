package com.registration.localization;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * The I18n wraps Springs {@link MessageSource} and used in java classes.
 * Contains additional methods and message key constants.
 *
 */
public class I18n {
	public static final String MESSAGE = "message";
	public static final String ERROR = "error";

	private MessageSource messageSource;

	public String msg(String code, Locale locale) {
		return messageSource.getMessage(code, new Object[]{}, locale);
	}

	public String msg(String code) {
		Locale locale = LocaleContextHolder.getLocale();
		locale = locale != null ? locale : UsersLocale.defaultJavaLocale();
		return messageSource.getMessage(code, new Object[]{}, locale);
	}

	public String msg(String code, Object[] args, Locale locale) {
		return messageSource.getMessage(code, args, locale);
	}

	public String msg(String code, Object[] args) {
		Locale locale = LocaleContextHolder.getLocale();
		locale = locale != null ? locale : UsersLocale.defaultJavaLocale();
		return messageSource.getMessage(code, args, locale);
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
