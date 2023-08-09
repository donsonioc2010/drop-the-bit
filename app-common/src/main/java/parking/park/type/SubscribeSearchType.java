package parking.park.type;

import java.util.Arrays;

public enum SubscribeSearchType {
    ALL, EXPIRE, UNEXPIRE;

    public static SubscribeSearchType valueOfDefaultALL(String value) {
        return Arrays.stream(SubscribeSearchType.values()).filter(type -> type.name().equals(value)).findFirst().orElse(SubscribeSearchType.ALL);
    }
}
