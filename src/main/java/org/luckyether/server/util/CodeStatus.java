package org.luckyether.server.util;

/**
 * Code for response status.
 *
 * @author Igor Hnes on 8/13/17.
 */
public enum CodeStatus {

    SUCCESS {
        @Override
        public String toString() {
            return "SUCCESS";
        }
    },
    FAIL {
        @Override
        public String toString() {
            return "FAIL";
        }
    }
}
