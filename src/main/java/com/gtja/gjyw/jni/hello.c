#include <jni.h>
#include <stdio.h>
#include "com_gtja_gjyw_jni_HelloJNI.h"

JNIEXPORT void JNICALL
Java_com_gtja_gjyw_jni_HelloJNI_sayHello(JNIEnv *env, jclass cls) {
    printf("Hello World from JNI!\n");
    fflush(stdout);
}