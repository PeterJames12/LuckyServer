package org.luckyether.server.util;

/**
 * @author Igor Hnes on 9/4/17.
 */
public enum Ether {

    NEWBIE {
        @Override
        public String toString() {
            return "0.000001";
        }
    },
    EXPERIENCED {
        @Override
        public String toString() {
            return "0.000005";
        }
    },
    PROFESSIONAL {
        @Override
        public String toString() {
            return "0.000005";
        }
    },

    BETS_NEWBIE{
        @Override
        public String toString() {
            return "0.000001";
        }
    },

    BETS_EXPERIENCED {
        @Override
        public String toString() {
            return "0.000002";
        }
    },

    BETS_PROFESSIONAL {
        @Override
        public String toString() {
            return "0.000003";
        }
    },
}
