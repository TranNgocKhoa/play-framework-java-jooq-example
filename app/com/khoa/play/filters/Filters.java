package com.khoa.play.filters;

import play.api.http.EnabledFilters;
import play.filters.cors.CORSFilter;
import play.http.DefaultHttpFilters;
import play.mvc.EssentialFilter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class Filters extends DefaultHttpFilters {

    @Inject
    public Filters(EnabledFilters enabledFilters, LoggingFilter loggingFilter, CORSFilter corsFilter) {
        super(combine(enabledFilters.asJava().getFilters(), corsFilter.asJava(), loggingFilter.asJava()));
    }

    private static List<EssentialFilter> combine(
            List<EssentialFilter> filters, EssentialFilter corsFilter, EssentialFilter essentialFilter) {
        List<EssentialFilter> combinedFilters = new ArrayList<>(filters);
        combinedFilters.add(corsFilter);
        combinedFilters.add(essentialFilter);
        return combinedFilters;
    }
}