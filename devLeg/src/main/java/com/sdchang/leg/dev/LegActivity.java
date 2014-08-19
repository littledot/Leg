package com.sdchang.leg.dev;

import android.app.Activity;
import android.os.Bundle;

import com.sdchang.leg.Leg;

public class LegActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leg);

		Throwable threw = new NullPointerException("NPE WTF NOOB");
		Throwable nullThrow = null;
		String nullString = null;

		Leg.a();

		Leg.a(nullString);
		Leg.a("myMessage");

		Leg.a(nullString, nullString);
		Leg.a("myTag", nullString);
		Leg.a(nullString, "myMessage");
		Leg.a("myTag", "myMessage");

		Leg.a(nullString, nullThrow);
		Leg.a("myMessage", nullThrow);
		Leg.a(nullString, threw);
		Leg.a("myMessage", threw);

		Leg.a(nullString, nullString, nullThrow);
		Leg.a("myTag", nullString, nullThrow);
		Leg.a(nullString, "myMessage", nullThrow);
		Leg.a(nullString, nullString, threw);
		Leg.a("myTag", "myMessage", nullThrow);
		Leg.a("myTag", nullString, threw);
		Leg.a(nullString, "myMessage", threw);
		Leg.a("myTag", "myMessage", threw);

		Leg.a("%s %d %f", "hello", 12345, 98765.456123);
	}
}
