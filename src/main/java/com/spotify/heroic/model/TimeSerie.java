package com.spotify.heroic.model;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class TimeSerie {
    private final String key;
    private final Map<String, String> tags;

    public TimeSerie(String key, Map<String, String> tags) {
        this.key = key;
        this.tags = tags;
    }

    @JsonCreator
    public static TimeSerie create(@JsonProperty("key") String key,
            @JsonProperty("tags") Map<String, String> tags) {
        return new TimeSerie(key, tags);
    }

    public TimeSerieSlice slice(DateRange range) {
        return new TimeSerieSlice(this, range);
    }

    public TimeSerieSlice slice(long start, long end) {
        return new TimeSerieSlice(this, new DateRange(start, end));
    }

    public TimeSerie modifyTags(Map<String, String> tags) {
        final Map<String, String> modifiedTags = new HashMap<String, String>(
                this.tags);
        modifiedTags.putAll(tags);
        return new TimeSerie(key, modifiedTags);
    }

    public TimeSerie withTags(Map<String, String> tags) {
        return new TimeSerie(key, tags);
    }
}