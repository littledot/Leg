package com.sdchang.leg;

import android.text.TextUtils;
import android.util.Log;

/**
 * A Logger that tells you exactly where in the source code the message was
 * generated. Similar to LogCat, Leg can also format clickable messages that
 * take developers to the source code.
 * <p/>
 * It's so good it's like running a sprint with a third leg.
 *
 * @author littledot
 */
public class Leg {

	/**
	 * Flag for dumping clickable logs.
	 */
	private static final boolean PRINT_CLICKABLE_LOG = false;

	private static final int DEBUG_VERB = 0;
	private static final int DEBUG_INFO = 1;
	private static final int DEBUG_DBUG = 2;
	private static final int DEBUG_WARN = 3;
	private static final int DEBUG_EROR = 4;
	private static final int DEBUG_ASRT = 5;
	private static final String[] LOGKEY = {"v", "i", "d", "w", "e", "a"};

	public static void a(String msgFormat, Object... args) {
		log(DEBUG_ASRT, String.format(msgFormat, args));
	}

	/**
	 * Send an INFO stack trace with an auto-generated tag (ClassName#LineNumber
	 * (MethodName)).
	 *
	 * @param msg    - The message you would like logged.
	 * @param stacks - The number of stacks to print up to this method call.
	 */
	public static void v(String msg, int stacks) {
		log(DEBUG_VERB, msg, stacks);
	}

	public static void i(String msg, int stacks) {
		log(DEBUG_INFO, msg, stacks);
	}

	public static void d(String msg, int stacks) {
		log(DEBUG_DBUG, msg, stacks);
	}

	public static void w(String msg, int stacks) {
		log(DEBUG_WARN, msg, stacks);
	}

	public static void e(String msg, int stacks) {
		log(DEBUG_EROR, msg, stacks);
	}

	public static void a(String msg, int stacks) {
		log(DEBUG_ASRT, msg, stacks);
	}

	/**
	 * Send an INFO log message with an auto-generated tag (ClassName#LineNumber
	 * (MethodName)).
	 *
	 * @param msg - The message you would like logged.
	 */
	public static void v(String msg) {
		log(DEBUG_VERB, msg);
	}

	public static void i(String msg) {
		log(DEBUG_INFO, msg);
	}

	public static void d(String msg) {
		log(DEBUG_DBUG, msg);
	}

	public static void w(String msg) {
		log(DEBUG_WARN, msg);
	}

	public static void e(String msg) {
		log(DEBUG_EROR, msg);
	}

	public static void a(String msg) {
		log(DEBUG_ASRT, msg);
	}

	/**
	 * Sends an INFO message displaying only the auto-generated tag. Useful when
	 * you want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void v() {
		log(DEBUG_VERB);
	}

	public static void i() {
		log(DEBUG_INFO);
	}

	public static void d() {
		log(DEBUG_DBUG);
	}

	public static void w() {
		log(DEBUG_WARN);
	}

	public static void e() {
		log(DEBUG_EROR);
	}

	public static void a() {
		log(DEBUG_ASRT);
	}

	private static void log(int level) {
		log(level, null, 1);
	}

	private static void log(int level, String msg) {
		log(level, msg, 1);
	}

	private static void log(int level, String msg, int stacks) {

		// find the index of the message within the stack
		int currentIndex = -1;
		StackTraceElement[] stackTraceElement = Thread.currentThread().getStackTrace();
		for (int i = 0; i < stackTraceElement.length; i++) {
			if (stackTraceElement[i].getMethodName().compareTo(LOGKEY[level]) == 0) {
				currentIndex = i + 1;
				break;
			}
		}

		if (currentIndex == -1) {
			Log.e("Leg",
					"Leg cannot find a starting point in the stack, starting from the bottom.");
			currentIndex = 0;
		}

		// format log messages
		for (int i = 0; i < stacks
				&& currentIndex + i < stackTraceElement.length; i++) {
			StackTraceElement e = stackTraceElement[currentIndex + i];

			String fullClassName = e.getClassName();
			String className = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);
			String methodName = e.getMethodName();
			String lineNumber = String.valueOf(e.getLineNumber());
			String fileName = e.getFileName();

			/**
			 * Clickable log message that brings you directly to the source
			 * code.
			 */
			String clickable;
			if (PRINT_CLICKABLE_LOG) {
				// clickable = "at " + fullClassName + "." + methodName + "("
				// + fileName + ":" + lineNumber + ")";
				clickable = new StringBuilder().append("at ").append(
						fullClassName).append('.').append(methodName).append(
						'(').append(fileName).append(':').append(lineNumber).append(
						')').toString();
			}
			/** Log metadata to be appended to a provided tag. */
			// String tagSuffix = "#" + lineNumber + " (" + methodName + ")";
			// String tag = className + tagSuffix;
			String tag = new StringBuilder().append(className).append('#').append(
					lineNumber).append(" (").append(methodName).append(')').toString();

			// prefix [i] to logtag when requesting multiple stacks
			if (stacks > 1) {
				// tag = "[" + i + "]" + tag;
				tag = new StringBuilder().append('[').append(i).append(']').append(
						tag).toString();
			}

			if (TextUtils.isEmpty(msg))
				msg = " ";

			switch (level) {

				case DEBUG_INFO:
					Log.i(tag, msg);
					if (PRINT_CLICKABLE_LOG) {
						Log.i(tag, clickable);
					}
					break;
				case DEBUG_DBUG:
					Log.d(tag, msg);
					if (PRINT_CLICKABLE_LOG) {
						Log.d(tag, clickable);
					}
					break;
				case DEBUG_WARN:
					Log.w(tag, msg);
					if (PRINT_CLICKABLE_LOG) {
						Log.w(tag, clickable);
					}
					break;
				case DEBUG_EROR:
					Log.e(tag, msg);
					if (PRINT_CLICKABLE_LOG) {
						Log.e(tag, clickable);
					}
					break;
				case DEBUG_ASRT:
					Log.println(Log.ASSERT, tag, msg);
					if (PRINT_CLICKABLE_LOG) {
						Log.println(Log.ASSERT, tag, clickable);
					}
					break;
				case DEBUG_VERB:
					Log.v(tag, msg);
					if (PRINT_CLICKABLE_LOG) {
						Log.v(tag, clickable);
					}
					break;
			}
		}
	}
}
