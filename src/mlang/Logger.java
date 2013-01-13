package mlang;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import anylang.Activator;


public class Logger {

	public static void log(Throwable e) {
		Activator.getDefault().getLog()
				.log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "", e));
	}
	
	public static void debug(String msg, Object... args) {
		boolean debug = true;
		if (debug) {
			System.out.println(String.format(msg, args));
		}
	}

	public static void warn(String msg) {
		System.out.println(msg);
		Activator.getDefault().getLog().log(new Status(IStatus.WARNING, Activator.PLUGIN_ID, msg));
	}
}
