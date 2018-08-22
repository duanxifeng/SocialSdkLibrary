package com.march.socialsdk.exception;

import com.march.socialsdk.util.SocialLogUtil;
import com.tencent.tauth.UiError;

/**
 * CreateAt : 2016/12/5
 * Describe : 错误信息
 *
 * @author chendong
 */
public class SocialError extends Exception{

    public static final String TAG = SocialError.class.getSimpleName();

    public static final int CODE_OK = 1; // 成功

    public static final int CODE_NOT_INSTALL = 0; // 没有安装应用
    public static final int CODE_VERSION_LOW = 1; // 版本低
    public static final int CODE_SHARE_OBJ_VALID = 2; // 分享的对象参数有问题
    public static final int CODE_SHARE_BY_INTENT_FAIL = 3; // 使用 Intent 分享失败
    public static final int CODE_STORAGE_ERROR = 4; // 没有读写存储的权限
    public static final int CODE_FILE_NOT_FOUND = 5; // 文件不存在

    private int errorCode;
    private String errorMsg;
    private Exception mException;

    public SocialError(String message) {
        super(message);
        this.errorMsg = message;
    }

    public SocialError(int errorCode) {
        this.errorCode = errorCode;
        switch (errorCode) {
            case CODE_NOT_INSTALL:
                errorMsg = "应用未安装";
                break;
            case CODE_VERSION_LOW:
                errorMsg = "应用版本低,需要更高版本";
                break;
            case CODE_STORAGE_ERROR:
                errorMsg = "没有获取到读取SD卡的权限，这会导致图片缩略图无法获取";
                break;
        }
    }


    public SocialError(int errCode,String message) {
        this.errorMsg = message;
        this.errorCode = errCode;
    }


    public SocialError(int errorCode, Exception exception) {
        this.errorCode = errorCode;
        mException = exception;
    }

    public SocialError(String message, Exception exception) {
        this(message);
        mException = exception;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void printStackTrace() {
        SocialLogUtil.e(TAG, toString());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("errCode = ").append(errorCode)
                .append(", errMsg = ").append(errorMsg).append("\n");
        if (mException != null) {
            sb.append("其他错误 : ").append(mException.getMessage());
            mException.printStackTrace();
        }
        return sb.toString();
    }

    public SocialError append(String msg) {
        this.errorMsg = String.valueOf(errorMsg) + "   " + msg;
        return this;
    }

}
