package com.sdchang.leg;

import android.util.Log;

import java.util.regex.Matcher;

/**
 * A Logger that tells you exactly where in the source code the message was
 * generated. Similar to LogCat.
 * <p/>
 * It's so good it's like running a sprint with a third leg.
 *
 * @author sdchang
 */
public class Leg extends LegCat {

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
	 * Send an VERBOSE log message displaying only the smart tag. Useful when
	 * you want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void v() {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.VERBOSE, invoker, getTag(invoker), null, null);
		}
	}

	/**
	 * Send an VERBOSE log message.
	 *
	 * @param msg The message you would like logged.
	 */
	public static void v(String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.VERBOSE, invoker, getTag(invoker), msg, null);
		}
	}

	/**
	 * Send a VERBOSE log message and log the exception.
	 *
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void v(String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.VERBOSE, invoker, getTag(invoker), msg, t);
		}
	}

	/**
	 * Send an VERBOSE log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void v(String tag, String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			if (tag != null) {
				Matcher matcher = stringToken.matcher(tag);
				if (matcher.find()) {
					msg = String.format(tag, msg);
					tag = getTag(invoker);
				}
			}
			log(Log.VERBOSE, invoker, tag, msg, null);
		}
	}

	/**
	 * Send an VERBOSE log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void v(String tag, String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.VERBOSE, invoker, tag, msg, t);
		}
	}

	/**
	 * Send an VERBOSE log message.
	 *
	 * @param format the format string (see {@link java.util.Formatter#format})
	 * @param args   the list of arguments passed to the formatter. If there are
	 *               more arguments than required by {@code format}, additional
	 *               arguments are ignored
	 */
	public static void v(String format, Object... args) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.VERBOSE, invoker, getTag(invoker), String.format(format, args), null);
		}
	}

	/**
	 * Send an INFO log message displaying only the smart tag. Useful when you
	 * want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void i() {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.INFO, invoker, getTag(invoker), null, null);
		}
	}

	/**
	 * Send an INFO log message.
	 *
	 * @param msg The message you would like logged.
	 */
	public static void i(String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.INFO, invoker, getTag(invoker), msg, null);
		}
	}

	/**
	 * Send a INFO log message and log the exception.
	 *
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void i(String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.INFO, invoker, getTag(invoker), msg, t);
		}
	}

	/**
	 * Send an INFO log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void i(String tag, String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			if (tag != null) {
				Matcher matcher = stringToken.matcher(tag);
				if (matcher.find()) {
					msg = String.format(tag, msg);
					tag = getTag(invoker);
				}
			}
			log(Log.INFO, invoker, tag, msg, null);
		}
	}

	/**
	 * Send an INFO log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void i(String tag, String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.INFO, invoker, tag, msg, t);
		}
	}

	/**
	 * Send an INFO log message.
	 *
	 * @param format the format string (see {@link java.util.Formatter#format})
	 * @param args   the list of arguments passed to the formatter. If there are
	 *               more arguments than required by {@code format}, additional
	 *               arguments are ignored
	 */
	public static void i(String format, Object... args) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.INFO, invoker, getTag(invoker), String.format(format, args), null);
		}
	}

	/**
	 * Send an DEBUG log message displaying only the smart tag. Useful when you
	 * want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void d() {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.DEBUG, invoker, getTag(invoker), null, null);
		}
	}

	/**
	 * Send an DEBUG log message.
	 *
	 * @param msg The message you would like logged.
	 */
	public static void d(String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.DEBUG, invoker, getTag(invoker), msg, null);
		}
	}

	/**
	 * Send a DEBUG log message and log the exception.
	 *
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void d(String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.DEBUG, invoker, getTag(invoker), msg, t);
		}
	}

	/**
	 * Send an DEBUG log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void d(String tag, String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			if (tag != null) {
				Matcher matcher = stringToken.matcher(tag);
				if (matcher.find()) {
					msg = String.format(tag, msg);
					tag = getTag(invoker);
				}
			}
			log(Log.DEBUG, invoker, tag, msg, null);
		}
	}

	/**
	 * Send an DEBUG log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void d(String tag, String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.DEBUG, invoker, tag, msg, t);
		}
	}

	/**
	 * Send an DEBUG log message.
	 *
	 * @param format the format string (see {@link java.util.Formatter#format})
	 * @param args   the list of arguments passed to the formatter. If there are
	 *               more arguments than required by {@code format}, additional
	 *               arguments are ignored
	 */
	public static void d(String format, Object... args) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.DEBUG, invoker, getTag(invoker), String.format(format, args), null);
		}
	}

	/**
	 * Send an WARNING log message displaying only the smart tag. Useful when
	 * you want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void w() {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.WARN, invoker, getTag(invoker), null, null);
		}
	}

	/**
	 * Send an WARNING log message.
	 *
	 * @param msg The message you would like logged.
	 */
	public static void w(String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.WARN, invoker, getTag(invoker), msg, null);
		}
	}

	/**
	 * Send a WARNING log message and log the exception.
	 *
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void w(String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.WARN, invoker, getTag(invoker), msg, t);
		}
	}

	/**
	 * Send an WARNING log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void w(String tag, String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			if (tag != null) {
				Matcher matcher = stringToken.matcher(tag);
				if (matcher.find()) {
					msg = String.format(tag, msg);
					tag = getTag(invoker);
				}
			}
			log(Log.WARN, invoker, tag, msg, null);
		}
	}

	/**
	 * Send an WARNING log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void w(String tag, String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.WARN, invoker, tag, msg, t);
		}
	}

	/**
	 * Send an WARNING log message.
	 *
	 * @param format the format string (see {@link java.util.Formatter#format})
	 * @param args   the list of arguments passed to the formatter. If there are
	 *               more arguments than required by {@code format}, additional
	 *               arguments are ignored
	 */
	public static void w(String format, Object... args) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.WARN, invoker, getTag(invoker), String.format(format, args), null);
		}
	}

	/**
	 * Send an ERROR log message displaying only the smart tag. Useful when you
	 * want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void e() {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ASSERT, invoker, getTag(invoker), null, null);
		}
	}

	/**
	 * Send an ERROR log message.
	 *
	 * @param msg The message you would like logged.
	 */
	public static void e(String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ERROR, invoker, getTag(invoker), msg, null);
		}
	}

	/**
	 * Send a ERROR log message and log the exception.
	 *
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void e(String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ERROR, invoker, getTag(invoker), msg, t);
		}
	}

	/**
	 * Send an ERROR log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void e(String tag, String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			if (tag != null) {
				Matcher matcher = stringToken.matcher(tag);
				if (matcher.find()) {
					msg = String.format(tag, msg);
					tag = getTag(invoker);
				}
			}
			log(Log.ERROR, invoker, tag, msg, null);
		}
	}

	/**
	 * Send an ERROR log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void e(String tag, String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ERROR, invoker, tag, msg, t);
		}
	}

	/**
	 * Send an ERROR log message.
	 *
	 * @param format the format string (see {@link java.util.Formatter#format})
	 * @param args   the list of arguments passed to the formatter. If there are
	 *               more arguments than required by {@code format}, additional
	 *               arguments are ignored
	 */
	public static void e(String format, Object... args) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ERROR, invoker, getTag(invoker), String.format(format, args), null);
		}
	}

	/**
	 * Send an ASSERT log message displaying only the smart tag. Useful when you
	 * want to verify this line of code is reached (eg. if...else branches,
	 * switch cases, goto, etc.)
	 */
	public static void a() {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ASSERT, invoker, getTag(invoker), null, null);
		}
	}

	/**
	 * Send an ASSERT log message.
	 *
	 * @param msg The message you would like logged.
	 */
	public static void a(String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ASSERT, invoker, getTag(invoker), msg, null);
		}
	}

	/**
	 * Send a ASSERT log message and log the exception.
	 *
	 * @param msg The message you would like logged.
	 * @param t   An exception to log
	 */
	public static void a(String msg, Throwable t) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ASSERT, invoker, getTag(invoker), msg, t);
		}
	}

	/**
	 * Send an ASSERT log message.
	 *
	 * @param tag Used to identify the source of a log message. It usually
	 *            identifies the class or activity where the log call occurs.
	 * @param msg The message you would like logged.
	 */
	public static void a(String tag, String msg) {
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			if (tag != null) {
				Matcher matcher = stringToken.matcher(tag);
				if (matcher.find()) {
					msg = String.format(tag, msg);
					tag = getTag(invoker);
				}
			}
			log(Log.ASSERT, invoker, tag, msg, null);
		}
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
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ASSERT, invoker, tag, msg, t);
		}
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
		if (isLogEnabled()) {
			StackTraceElement invoker = getStack(1);
			log(Log.ASSERT, invoker, getTag(invoker), String.format(format, args), null);
		}
	}
}
