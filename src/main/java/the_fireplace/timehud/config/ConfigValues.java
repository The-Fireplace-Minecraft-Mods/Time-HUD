package the_fireplace.timehud.config;

import java.awt.*;

/**
 * @author The_Fireplace
 */
public class ConfigValues {
	public static final String FORMAT_DEFAULT = "12HH:MM ZZBRNAME DATE, YEAR";
	public static String FORMAT;
	public static final String FORMAT_NAME = "cfg.format";

	public static final boolean REAL_DEFAULT = false;
	public static boolean REAL;
	public static final String REAL_NAME = "cfg.real";

	public static final boolean NEEDCLOCK_DEFAULT = false;
	public static boolean NEEDCLOCK;
	public static final String NEEDCLOCK_NAME = "cfg.needclock";

	public static final int CLOCKX_DEFAULT = 4;
	public static int CLOCKX;
	public static final String CLOCKX_NAME = "cfg.clockx";

	public static final int CLOCKY_DEFAULT = 4;
	public static int CLOCKY;
	public static final String CLOCKY_NAME = "cfg.clocky";

	public static final XJust XALIGNMENT_DEFAULT = XJust.LEFT;
	public static XJust XALIGNMENT;
	public static final String XALIGNMENT_NAME = "cfg.xalignment";

	public static final YJust YALIGNMENT_DEFAULT = YJust.TOP;
	public static YJust YALIGNMENT;
	public static final String YALIGNMENT_NAME = "cfg.yalignment";

	public static final double FONTSCALE_DEFAULT = 1.0D;
	public static double FONTSCALE;
	public static final String FONTSCALE_NAME = "cfg.fontscale";

	public static final int FONTCOLOR_DEFAULT = Color.WHITE.getRGB();
	public static int FONTCOLOR;
	public static final String FONTCOLOR_NAME = "cfg.fontcolor";
}
