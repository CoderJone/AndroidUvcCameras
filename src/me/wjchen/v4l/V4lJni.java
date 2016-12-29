package me.wjchen.v4l;

import android.graphics.Bitmap;

public class V4lJni {
    /* load our native library */
    static {
        System.loadLibrary("v4ljni-common");
    }

    // �����������ʧ�ܷ���0
    public static native long openCamera(int id);
    // frame_skip��֡��������Ƶ����cpu��ռ��
    public static native long openCameraExt(int id, int width, int height, int frame_skip);
    public static native long openCameraByFd(int fd,  int vid, int pid, int camid, String usbfs, 
    		int width, int height, int frame_skip);
    

    // handleΪopenCamera�ķ���ֵ
    public static native void stopCamera(long handle);
    public static native int getWidth(int camid);
    public static native int getHeight(int camid);
    public static native int getFPS(int camid);

    // ����ARGB_8888��ʽ��bitmap, ��ʾ��Ƶר��, isFront�Ƿ���ǰ������ͷ���Ҿ�����
    public static native int getCurrBitmap(int camid, Bitmap bmp, boolean isFront);
    
    // ��ȡ��ǰ����Ƶ֡���ݣ� ʧ�ܷ���null
    public static native byte[] getCurrJpg(int camId);
    public static native byte[] getCurrBGR(int camId);
    
    // ��������ͷ�����ת���� 0��ʱ��90�ȣ� 1˳ʱ��90�ȣ� 2����ת, 3��ת180��
    public static native void setRotate(int camId, int cam_type);
    public static native int  getRotate(int camId);

    public static  byte[] BGR2Gray(byte[] bgr) {
    	int n = bgr.length;
    	if (n <= 0) {
    		return null;
    	}
    	byte[] gray = new byte[n/3];
    	int i, j;
    	for	(i = 0, j = 0; i < n; i+= 3, j++) {
    		int b = bgr[i]&0xff;
    		int g = bgr[i+1]&0xff;
    		int r = bgr[i+2]&0xff;
    		gray[j] = (byte) (((r*77)+(g*151)+(b*28)) >> 8);
    	}
    	return gray;
    }
}
