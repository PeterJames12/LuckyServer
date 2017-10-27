package org.luckyether.server.util;

/**
 * @author Igor Hnes on 9/4/17.
 */
public enum Ether {

    NEWBIE {
        @Override
        public String toString() {
            return "0.05";
        }
    },
    EXPERIENCED {
        @Override
        public String toString() {
            return "0.5";
        }
    },

    PROFESSIONAL {
        @Override
        public String toString() {
            return "5";
        }
    },
}
