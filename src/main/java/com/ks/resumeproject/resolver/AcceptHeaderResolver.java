package com.ks.resumeproject.resolver;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class AcceptHeaderResolver extends AcceptHeaderLocaleResolver {

    List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("es"));

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String header = request.getHeader("Accept-Language");
        return header == null || header.isEmpty()
                ? Locale.getDefault() : Locale.lookup(Locale.LanguageRange.parse(header), LOCALES);
    }
}
