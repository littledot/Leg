package com.sdchang.leg;

import android.util.Log;

/**
 * Implementation of Leg.
 *
 * @author sdchang 8/19/14.
 */
class LegCat {

	/**
	 * Offset of the enclosing method within the stack trace.
	 *
	 * @see <a href="http://stackoverflow.com/a/8592871/499125">Getting the name
	 * of the current executing method</a>
	 */
	static final int ENCLOSING_METHOD_INDEX;

	static boolean mIsReleaseBuild;
	static boolean mLogReleaseBuild;

	static {
		int i = 0;
		for (StackTraceElement ste : Thread.currentThread().getStackTrace()) {
			i++;
			if (ste.getClassName().equals(LegCat.class.getName())) {
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
	static StackTraceElement getStack(int offset) {
		StackTraceElement[] ste = Thread.currentThread().getStackTrace();
		StackTraceElement e = ste[ENCLOSING_METHOD_INDEX + offset];
		return e;
	}

	/**
	 * Check if this is a Release build and if the user wants logging enabled
	 * for Release builds.
	 *
	 * @return TRUE if logging is enabled.
	 */
	static boolean isLogEnabled() {
		return !mIsReleaseBuild || (mIsReleaseBuild && mLogReleaseBuild);
	}

	/**
	 * Return the tag of the StackTraceElement's class.
	 *
	 * @param e
	 *
	 * @return
	 */
	static String getTag(StackTraceElement e) {
		return getTag(e.getClassName());
	}

	/**
	 * Return the tag for the class.
	 *
	 * @param className
	 *
	 * @return
	 */
	static String getTag(String className) {
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
	 * Output logs to LogCat.
	 *
	 * @param priority Log type. Log.DEBUG, Log.ERROR, etc.
	 * @param e        The invoker's stack trace element
	 * @param tag      Log's tag
	 * @param msg      Log's message
	 */
	static void log(int priority, StackTraceElement e, String tag,
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

		Log.println(priority, tag, msgBuilder.toString());
	}
}
