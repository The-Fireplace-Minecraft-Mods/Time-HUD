package the_fireplace.timehud.config;

import java.awt.*;

/**
 * @author The_Fireplace
 */
public class ConfigValues {
	//In-Game Time
	public static final boolean IGHUD_DEFAULT = true;
	public static boolean IGHUD;
	public static final String IGHUD_NAME = "cfg.ighud";

	public static final String FORMAT_DEFAULT = "12HH:MM ZZBRNAME DATE, YEAR";
	public static String FORMAT;
	public static final String FORMAT_NAME = "cfg.format";

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

	//Real Time
	public static final boolean REAL_DEFAULT = false;
	public static boolean REAL;
	public static final String REAL_NAME = "cfg.real";

	public static final String RFORMAT_DEFAULT = "12HH:MM ZZBRNAME DATE, YEAR";
	public static String RFORMAT;
	public static final String RFORMAT_NAME = "cfg.rformat";

	public static final int RCLOCKX_DEFAULT = 4;
	public static int RCLOCKX;
	public static final String RCLOCKX_NAME = "cfg.rclockx";

	public static final int RCLOCKY_DEFAULT = 4;
	public static int RCLOCKY;
	public static final String RCLOCKY_NAME = "cfg.rclocky";

	public static final XJust RXALIGNMENT_DEFAULT = XJust.LEFT;
	public static XJust RXALIGNMENT;
	public static final String RXALIGNMENT_NAME = "cfg.rxalignment";

	public static final YJust RYALIGNMENT_DEFAULT = YJust.TOP;
	public static YJust RYALIGNMENT;
	public static final String RYALIGNMENT_NAME = "cfg.ryalignment";

	public static final double RFONTSCALE_DEFAULT = 1.0D;
	public static double RFONTSCALE;
	public static final String RFONTSCALE_NAME = "cfg.rfontscale";

	public static final int RFONTCOLOR_DEFAULT = Color.WHITE.getRGB();
	public static int RFONTCOLOR;
	public static final String RFONTCOLOR_NAME = "cfg.rfontcolor";
}
