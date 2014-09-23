LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_MODULE := libJNItest
LOCAL_SRC_FILES := com_example_jnitest_JNItest.cpp
LOCAL_C_INCLUDES := $(JNI_H_INCLUDE)
LOCAL_LDLIBS += -L$(SYSROOT)/usr/lib -llog
LOCAL_SHARED_LIBRARIES := libutils
LOCAL_PERLINK_MODULE := false

include $(BUILD_SHARED_LIBRARY)