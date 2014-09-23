#include <stdio.h>
#include <stdlib.h>
//#include <utils/Log.h>
#include<Alog.h>
#include "com_example_jnitest_JNItest.h"

JNIEXPORT jstring JNICALL Java_com_example_jnitest_JNItest_GetTest
(JNIEnv *env, jclass arg)
{
	LOGE("Hello LIV!\n");
	return env->NewStringUTF("JNItest Native String");

}
