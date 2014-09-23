package jnitest;

public class TestNativeDemo {

	static 
	{
		System.loadLibrary("TestNativeDemoCpp");
	}
	public native String testJni(String arg);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestNativeDemo ob = new TestNativeDemo();
		String result = ob.testJni("Hello,JNI");
		System.out.println("TestNaviteDemo.testJNi=" + result);
	}

}
