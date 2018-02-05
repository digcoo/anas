package com.slife.enums;

/**
 * Created by chen on 2017/5/23.
 * <p>
 * Email 122741482@qq.com
 * <p>
 * Describe: http 状态码
 * ----------------------------------------------------------------------------
 * 200 OK - [GET]：服务器成功返回用户请求的数据，该操作是幂等的（Idempotent）。
 * 400 INVALID REQUEST - [POST/PUT/PATCH]：用户发出的请求有错误，服务器没有进行新建或修改数据的操作，该操作是幂等的。
 * 401 Unauthorized - [*]：表示用户没有权限（令牌、用户名、密码错误）。
 * 403 Forbidden - [*] 表示用户得到授权（与401错误相对），但是访问是被禁止的。
 * 404 NOT FOUND - [*]：用户发出的请求针对的是不存在的记录，服务器没有进行操作，该操作是幂等的。
 * 406 Not Acceptable - [GET]：用户请求的格式不可得（比如用户请求JSON格式，但是只有XML格式）。
 * 410 Gone -[GET]：用户请求的资源被永久删除，且不会再得到的。
 * 422 Unprocesable entity - [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误。
 * 500 INTERNAL SERVER ERROR - [*]：服务器发生错误，用户将无法判断发出的请求是否成功。
 * 600 UN_KNOW_ERROR - 未知错误
 * ----------------------------------------------------------------------------
 */
public enum HttpCodeEnum {

    OK(200,"操作成功"),
    INVALID_REQUEST(400,"参数错误"),
    UNAUTHORIZED(401,"没有权限"),
    FORBIDDEN(403,"禁止访问"),
    NOT_FOUND(404,"资源不存在"),
    NOT_ACCEPTABLE(406,"请求的格式不正确"),
    GONE(410,"数据被删除"),
    UNPROCESABLE_ENTITY(422,"参数验证错误"),
    INTERNAL_SERVER_ERROR(500,"服务器发生错误"),
    UN_KNOW_ERROR(500,"未知错误"),
    FAIL(501,"操作失败"),
    SEND_SMS_FAIL(502,"短信发送失败"),
    PAY_ERROR(503,"支付失败"),


    //长传文件相关错误码800开头
    UPLOAD_FILE_NOT_FOUND(800,"上传文件不存在"),
    OVER_MAX_SIZE(801,"超过最大值"),



    //用户相关错误统一使用1000开头错误码
    USERNAME_OR_PASSWORD_ERR(1000,"用户名或密码错误"),
    DELETE_DEFAULT_PHOTO_ERR(1001,"默认头像不可删除"),
    USER_NOT_FOUND_ERR(1002,"用户不存在"),
    USER_NICK_DUPLICATE(1002,"用户昵称重复"),



    //商户相关错误统一使用2000开头错误码
    SHOP_NOT_EXISTS(2000,"商户不存在！"),
    SHOP_SMS_FRE(2001,"短信请求频繁"),
    SHOP_SMS_ERROR(2002,"短信验证码错误"),
    SHOP_USER_DUP(2003,"用户已注册"),
    SHOP_USER_NOT_FOUND(2004,"用户未注册"),



    //活动相关错误统一使用3000开头错误码
    AD_NOT_EXISTS(3000,"活动不存在！"),
    AD_NOT_PERMIT(3001,"活动已发布，未允许进行此操作！"),
    AD_NOT_PERIOD(3002,"距离上一个活动小于10分钟，请稍后再来试试吧！"),
    AD_OVER_LIMIT(3004,"今天免费发布活动的次数已达到上限了，请明天再来哦！"),

    ;
    private final int code;
    private final String message;

    HttpCodeEnum(final int code, final String message){
        this.code=code;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }



}
