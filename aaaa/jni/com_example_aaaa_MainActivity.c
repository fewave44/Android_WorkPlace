#include "com_example_aaaa_MainActivity.h"
#include <stdlib.h>
#include <stdio.h>

#ifdef __cplusplus
extern "C"
{
#endif
/*
 * Class:     com_ndk_test_JniClient
 * Method:    AddStr
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_example_aaaa_MainActivity_AddStr
  (JNIEnv *env, jclass arg, jstring instringA, jstring instringB)
{
    jstring str = (*env)->NewStringUTF(env, "HelloWorld from JNI !");
    return str;
}

/*
* Class:     com_ndk_test_JniClient
* Method:    AddInt
* Signature: (II)I
*/
JNIEXPORT jint JNICALL Java_com_example_aaaa_MainActivity_AddInt
  (JNIEnv *env, jclass arg, jint a, jint b)
{

    return a + b;
}

//jint JNI_OnLoad(JavaVM* vm, void* reserved)
//{
//	void *venv;
//	LOGI("dufresne----->JNI_OnLoad");
//	if((*vm)->GetEnv(vm,(void**)&venv,JNI_VERSION_1_4) != JNI_OK)
//	{
//		LOGE("dufresne----->ERROR:GetEnv failed");
//		return -1
//	}
//	return JNI_VERSION_1_4;
//}

#ifdef __cplusplus
}
#endif
