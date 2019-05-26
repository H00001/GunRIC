package top.gunplan.ric.user;


import top.gunplan.netty.GunException;

import top.gunplan.netty.impl.propertys.GunProperty;
import top.gunplan.utils.AbstractGunBaseLogUtil;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;


/**
 * analyzing config information by reference
 *
 * @author dosdrtt
 */

public final class GunRicUserPropertyManageImpl {

    private final static String UNUSEFULCHARS = "#";

    private final static String ASSIGNMENTCHARS = "=";
    private final static String[] OPENANDCLODECHILDPROPERTYS = {"{", "}"};
    private final static Map<String, GunProperty> STRING_GUN_PROPERTY_HASH_MAP = new HashMap<>();


    /**
     * reference to get propertys
     *
     * @return ture or false to get field
     */
    public static boolean initProperty() {
        try {
            STRING_GUN_PROPERTY_HASH_MAP.put("ric-user", new GunRicUserProperty());
            String[] propertys = new String(Files.readAllBytes(Paths.get(Objects.
                    requireNonNull(top.gunplan.netty.common.GunNettyPropertyManagerImpl.class.getClassLoader().
                            getResource("GunRic-User.conf")).toURI()))).
                    split("\n");
            realAnalyPropertys(propertys);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public static GunRicUserProperty getProPerty() {
        return (GunRicUserProperty) STRING_GUN_PROPERTY_HASH_MAP.get("ric-user");
    }


    private static void realAnalyPropertys(String[] propertys) throws NoSuchFieldException, IllegalAccessException {
        String[] proname;
        Field fd;
        for (int now = 0; now < propertys.length; now++) {
            if (!propertys[now].startsWith(UNUSEFULCHARS)) {
                if (propertys[now].endsWith(OPENANDCLODECHILDPROPERTYS[0])) {
                    final String prohead = propertys[now].replace(OPENANDCLODECHILDPROPERTYS[0], "").trim();
                    final GunProperty obj = STRING_GUN_PROPERTY_HASH_MAP.get(prohead);
                    now++;
                    for (; now < propertys.length; now++) {
                        if (!propertys[now].trim().endsWith(OPENANDCLODECHILDPROPERTYS[1])) {
                            if (!propertys[now].startsWith(UNUSEFULCHARS)) {
                                proname = propertys[now].replace(" ", "").split(ASSIGNMENTCHARS);
                                fd = obj.getClass().getDeclaredField(proname[0]);
                                AbstractGunBaseLogUtil.info(proname[0] + ":" + proname[1].trim(), "[PROPERTY]");
                                fd.setAccessible(true);
                                fd.set(obj, isInteger(proname[1].trim()) ? Integer.valueOf(proname[1].trim()) : proname[1].trim());
                            }

                        } else {
                            break;
                        }
                    }
                    if (!obj.doRegex()) {
                        throw new GunException("property regex error:" + obj.getClass());
                    }
                }
            }
        }
    }

    private static boolean isInteger(String str) {
        if (str.startsWith("0x")) {
            return true;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

}
