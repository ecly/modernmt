/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class eu_modernmt_decoder_moses_MosesDecoder */

#ifndef _Included_eu_modernmt_decoder_moses_MosesDecoder
#define _Included_eu_modernmt_decoder_moses_MosesDecoder
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    instantiate
 * Signature: (Ljava/lang/String;JJ)J
 */
JNIEXPORT jlong JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_instantiate
  (JNIEnv *, jobject, jstring, jlong, jlong);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    getFeatures
 * Signature: ()[Leu/modernmt/decoder/moses/MosesFeature;
 */
JNIEXPORT jobjectArray JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_getFeatures
  (JNIEnv *, jobject);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    getFeatureWeightsFromPointer
 * Signature: (J)[F
 */
JNIEXPORT jfloatArray JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_getFeatureWeightsFromPointer
  (JNIEnv *, jobject, jlong);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    setFeatureWeights
 * Signature: ([Ljava/lang/String;[[F)V
 */
JNIEXPORT void JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_setFeatureWeights
  (JNIEnv *, jobject, jobjectArray, jobjectArray);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    createSession
 * Signature: ([I[F)J
 */
JNIEXPORT jlong JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_createSession
  (JNIEnv *, jobject, jintArray, jfloatArray);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    destroySession
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_destroySession
  (JNIEnv *, jobject, jlong);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    translate
 * Signature: (Ljava/lang/String;[I[FJI)Leu/modernmt/decoder/moses/TranslationXObject;
 */
JNIEXPORT jobject JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_translate
  (JNIEnv *, jobject, jstring, jintArray, jfloatArray, jlong, jint);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    updateReceived
 * Signature: (IJI[I[I[I)V
 */
JNIEXPORT void JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_updateReceived
  (JNIEnv *, jobject, jint, jlong, jint, jintArray, jintArray, jintArray);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    getLatestUpdatesIdentifier
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_getLatestUpdatesIdentifier
  (JNIEnv *, jobject);

/*
 * Class:     eu_modernmt_decoder_moses_MosesDecoder
 * Method:    dispose
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_eu_modernmt_decoder_moses_MosesDecoder_dispose
  (JNIEnv *, jobject, jlong);

#ifdef __cplusplus
}
#endif
#endif
