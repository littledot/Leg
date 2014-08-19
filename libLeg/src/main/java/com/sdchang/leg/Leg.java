package com.sdchang.leg;

import android.util.Log;

/**
 * A Logger that tells you exactly where in the source code the message was
 * generated. Similar to LogCat.
 * <p/>
 * It's so good it's like running a sprint with a third leg.
 *
 * @author sdchang
 */
public class Leg {

	/**
	 * Offset of the enclosing method within the stack trace.
	 *
	 * @see <a href="http://stackoverflow.com/a/8592871/499125">Getting the name
	 * of the current executing method</a>
	 */
	private static final int ENCLOSING_METHOD_INDEX;

	private static boolean mIsReleaseBuild;
	private static boolean mLogReleaseBuild;

	static {
		int i = 0;
		for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
			i++;
			if (ste.getClassName().equals(Leg.class.getName())) {
				break;
			}
		}
		ENCLOSING_METHOD_INDEX = i;
	}

	/**
	 * Return the StackTraceElement of the invoking method.
	 *
	 * @param offset Additional offset from the invoking method.
	 *
	 * @return
	 */
	private static StackTraceElement getStack(int offset) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		StackTraceElement e = ste[ENCLOSING_METHOD_INDEX + offset];
		return e;
	}

	/**
	 * @param e
	 *
	 * @return
	 */
	private static String getTag(StackTraceElement e) {
		return getTag(e.getClassName());
	}

	/**
	 * Return the tag for the class.
	 *
	 * @param className
	 *
	 * @return
	 */
	private static String getTag(String className) {
		Class clazz = null;
		try {
			clazz = Class.forName(className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		if (clazz != null) {
			LegTag legTag = (LegTag) clazz.getAnnotation(LegTag.class);
			if (legTag != null) {
				return legTag.value();
			}
		}
		return null;
	}

	/**
	 * Configure whether or not to print logs in Release builds. By default, Leg
	 * will not print logs in Release builds.
	 *
	 * @param isDebug         Pass your project's BuildConfig.DEBUG
	 * @param logReleaseBuild TRUE if you want to log in release build
	 */
	public static void printReleaseBuild(boolean isDebug,
										 boolean logReleaseBuild) {
		mIsReleaseBuild = !isDebug;
		mLogReleaseBuild = logReleaseBuild;
	}

	/**
	 * Send an ASSERT log message displaying only the smart tag. Useful when you
	 * want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void a() {
		StackTraceElement invoker = getStack(1);
		log(Log.ASSERT, invoker, getTag(invoker), null, null);
	}

	/**
	 * Send an ASSERT log message.
	 *
	 * @param msg The message you would like logged.
	 */
	public static void a(String msg) {
		StackTraceElement invoker = getStack(1);
		log(Log.ASSERT, invoker, getTag(invoker), msg, null);
	}

	/**
	 * Send a ASSERT log message and log the exception.
	 *
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void a(String msg, Throwable t) {
		StackTraceElement invoker = getStack(1);
		log(Log.ASSERT, invoker, getTag(invoker), msg, t);
	}

	/**
	 * Send an ASSERT log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void a(String tag, String msg) {
		StackTraceElement invoker = getStack(1);
		log(Log.ASSERT, invoker, tag, msg, null);
	}

	/**
	 * Send an ASSERT log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void a(String tag, String msg, Throwable t) {
		StackTraceElement invoker = getStack(1);
		log(Log.ASSERT, invoker, tag, msg, t);
	}

	/**
	 * Send an ASSERT log message.
	 *
	 * @param format the format string (see {@link java.util.Formatter#format})
	 * @param args   the list of arguments passed to the formatter. If there are
	 *               more arguments than required by {@code format}, additional
	 *               arguments are ignored
	 */
	public static void a(String format, Object... args) {
		StackTraceElement invoker = getStack(1);
		log(Log.ASSERT, invoker, getTag(invoker), String.format(format, args), null);
	}

	/**
	 * Output logs to LogCat.
	 *
	 * @param priority Log type. Log.DEBUG, Log.ERROR, etc.
	 * @param e        The invoker's stack trace element
	 * @param tag      Log's tag
	 * @param msg      Log's message
	 */
	private static void log(int priority, StackTraceElement e, String tag,
							String msg, Throwable t) {

		/* Format smart tag */
		String fullClassName = e.getClassName();
		String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
		String methodName = e.getMethodName();
		String lineNumber = String.valueOf(e.getLineNumber());
		String fileName = e.getFileName();

		StringBuilder sb = new StringBuilder().append(className).append('#').append(
				lineNumber).append(" (").append(methodName).append(')');

		/* Prepend smart tag with user's tag. */
		if (tag != null) {
			sb.insert(0, "] ").insert(0, tag).insert(0, '[');
		}
		tag = sb.toString();

		StringBuilder msgBuilder = new StringBuilder();

		/* First append the exception */
		if (t != null) {
			msgBuilder.append(Log.getStackTraceString(t));
		}

		if (msg != null) {
			/* If there was an exception, prepend a newline. */
			if (msgBuilder.length() > 0) {
				msgBuilder.insert(0, '\n');
			}
			/* Now prepend the user's message. */
			msgBuilder.insert(0, msg);
		}

		if (msgBuilder.length() == 0) {
			/* A bug in IntelliJ's Android plugin will print the _next_ log in
			 * square brackets as the message of this log when you want to log a
			 * space (' '). Instead, log \u007F (DEL), which causes IntelliJ to
			 * print a space ' '. */
			msgBuilder.append('\u007F');
		}

		if (!mIsReleaseBuild || (mIsReleaseBuild && mLogReleaseBuild)) {
			Log.println(priority, tag, msgBuilder.toString());
		}
	}
}
