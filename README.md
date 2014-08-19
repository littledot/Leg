# Android Leg

1. Do you find yourself reading logs but not knowing where in the source code were they generated?
2. Are you tired of repeatedly copying class names just to tag log messages?
3. Have you ever performed a project-wide search for a log message, only to find the same message being logged in several different places with the same tag or no tags at all?

If you said _yes_ to any of the above, Android Leg is just the library for you!
Leg is a logger that tells you exactly where in the source code the message was generated.
Leg will automatically tag all log messages with the location of the log call, so that you, the developer can focus on the import part: composing informative log messages.



## Usage

* To log a message,
```java
Leg.e("myMessage");
```
> E/LegActivity#22 (onCreate)﹕ myMessage

Leg automatically tag all messages with the enclosing class name, line number and the enclosing method name.

* Sometimes you just want to be sure a block of code is executed, you simply don't care about the message.
```java
Leg.e();
```
> E/LegActivity#19 (onCreate)﹕

Logging blank lines is actually not allowed in android.util.Log (try `Log.("", " ")`, but Leg works around this for you.

* Sometimes you care so much about the message it needs to be in a pretty format.
```java
Leg.e("%s %d %s", "Like running with", 3, "legs");
```
> E/LegActivity#43 (onCreate)﹕ Like running with 3 legs


For maximum usability and compatibility, Leg implements all APIs exposed in android.util.Log.

* To log a message specifying a tag,
```java
Leg.e("myTag", "myMessage");
```
> E/[myTag] LegActivity#27 (onCreate)﹕ myMessage

Leg prepends your custom tags so that you can still grep it.

* To log a Throwable,
```java
Leg.e("myTag", "myMessage", new NullPointerException("Leg is awesome"));
```
> E/[myTag] LegActivity#41 (onCreate)( 1504): myMessage
> E/[myTag] LegActivity#41 (onCreate)( 1504): java.lang.NullPointerException: Leg is awesome
> E/[myTag] LegActivity#41 (onCreate)( 1504): 	at com.sdchang.leg.dev.LegActivity.onCreate(LegActivity.java:15)
> E/[myTag] LegActivity#41 (onCreate)( 1504): 	at android.app.Activity.performCreate(Activity.java:5231)
> E/[myTag] LegActivity#41 (onCreate)( 1504): 	at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1087)
> ...

With class names automatically generated for you, the sky's the limit as to what creative tags you could come up with.



## Download via Gradle

```gradle
repositories {
    maven {
        url 'https://oss.sonatype.org/content/groups/public'
    }
}
dependencies {
    compile 'com.sdchang:leg:0.0.1'
    compile 'com.sdchang:leg:0.0.1@aar'
}
```



## License

```
Copyright 2014 Sheng-Dean, Chang

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```