package com.sdchang.leg.dev;

import android.app.Activity;
import android.os.Bundle;

import com.sdchang.leg.Leg;
import com.sdchang.leg.LegTag;

public class LegActivity extends Activity {

	Throwable threw = new NullPointerException("Leg is awesome");
	Throwable nullThrow = null;
	String nullString = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_leg);

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

		Leg.a("%s %d %s", "Like running with", 3, "legs");
		Leg.a("Like running with 3 %s", "legs");
		Leg.a("Like running with %d legs", 3);

		new Inner().test();
	}

	@LegTag("innerClazz")
	private class Inner {

		private void test() {
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

			Leg.a("%s %d %s", "Like running with", 3, "legs");
			Leg.a("Like running with 3 %s", "legs");
			Leg.a("Like running with %d legs", 3);
		}
	}
}
