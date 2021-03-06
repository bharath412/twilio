/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.twilio.twiml.TwiML;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Hangup>}
 */
public class Hangup extends TwiML {
    /**
     * For XML Serialization/Deserialization
     */
    public Hangup() {
        this(new Builder());
    }

    /**
     * Create a new {@code <Hangup>} element
     */
    private Hangup(Builder b) {
        super("Hangup", Collections.<TwiML>emptyList(), b.options);
    }

    /**
     * Create a new {@code <Hangup>} element
     */
    public static class Builder {
        private Map<String, String> options = new HashMap<>();

        /**
         * Set additional attributes on this TwiML element that will appear in generated
         * XML.
         */
        public Builder option(String key, String value) {
            this.options.put(key, value);
            return this;
        }

        /**
         * Create and return resulting {@code <Hangup>} element
         */
        public Hangup build() {
            return new Hangup(this);
        }
    }
}