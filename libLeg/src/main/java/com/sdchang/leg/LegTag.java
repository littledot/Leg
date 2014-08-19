package com.sdchang.leg;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author sdchang 8/16/14.
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface LegTag {
	String value();
}
