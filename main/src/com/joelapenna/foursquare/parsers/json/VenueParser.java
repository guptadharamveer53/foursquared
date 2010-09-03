/**
 * Copyright 2010 Mark Wyszomierski
 */
package com.joelapenna.foursquare.parsers.json;

import com.joelapenna.foursquare.Foursquare;
import com.joelapenna.foursquare.types.Tags;
import com.joelapenna.foursquare.types.Venue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * @date July 13, 2010
 * @author Mark Wyszomierski (markww@gmail.com)
 */
public class VenueParser extends AbstractParser<Venue> {

    private static final String TAG = "VenueParser";
    private static final boolean DEBUG = Foursquare.PARSER_DEBUG;
    private static final Logger LOG = Logger.getLogger(TipParser.class.getCanonicalName());
    
    @Override
    public Venue parse(JSONObject json) throws JSONException {
        
        if (DEBUG) {
            LOG.log(Level.FINE, "Parser: In " + TAG + ": " + json.toString());
        }
        
        Venue obj = new Venue();
        if (json.has("address")) {
            obj.setAddress(json.getString("address"));
        } 
        if (json.has("checkins")) {
            obj.setCheckins(
                new GroupParser(
                    new CheckinParser()).parse(json.getJSONArray("checkins")));
        } 
        if (json.has("city")) {
            obj.setCity(json.getString("city"));
        } 
        if (json.has("cityid")) {
            obj.setCityid(json.getString("cityid"));
        } 
        if (json.has("setCrossstreet")) {
            obj.setCrossstreet(json.getString("setCrossstreet"));
        } 
        if (json.has("distance")) {
            obj.setDistance(json.getString("distance"));
        } 
        if (json.has("geolat")) {
            obj.setGeolat(json.getString("geolat"));
        } 
        if (json.has("geolong")) {
            obj.setGeolong(json.getString("geolong"));
        } 
        if (json.has("id")) {
            obj.setId(json.getString("id"));
        } 
        if (json.has("name")) {
            obj.setName(json.getString("name"));
        } 
        if (json.has("phone")) {
            obj.setPhone(json.getString("phone"));
        } 
        if (json.has("primarycategory")) {
             obj.setCategory(new CategoryParser().parse(json.getJSONObject("primarycategory")));
        } 
        if (json.has("specials")) {
            obj.setSpecials(
                new GroupParser(
                    new SpecialParser()).parse(json.getJSONArray("specials")));
        } 
        if (json.has("state")) {
            obj.setState(json.getString("state"));
        } 
        if (json.has("stats")) {
             obj.setStats(new StatsParser().parse(json.getJSONObject("stats")));
        } 
        if (json.has("tags")) {
            obj.setTags(
                new Tags(StringArrayParser.parse(json.getJSONArray("tags"))));
        }
        if (json.has("tips")) {
            obj.setTips(
                new GroupParser(
                    new TipParser()).parse(json.getJSONArray("tips")));
        } 
        if (json.has("todos")) {
            obj.setTodos(
                new GroupParser(
                    new TipParser()).parse(json.getJSONArray("todos")));
        } 
        if (json.has("twitter")) {
            obj.setTwitter(json.getString("twitter"));
        } 
        if (json.has("zip")) {
            obj.setZip(json.getString("zip"));
        }

        return obj;
    }
}