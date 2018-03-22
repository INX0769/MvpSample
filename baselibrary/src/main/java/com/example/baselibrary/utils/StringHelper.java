package com.example.baselibrary.utils;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.net.Uri;
import android.provider.MediaStore;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringHelper {
    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isEmpty(String str, String... emptyValues) {
        if (isEmpty(str)) {
            return true;
        }
        for (int i = 0; i < emptyValues.length; i++) {
            if (str.equals(emptyValues[i])) {
                return true;
            }
        }
        return false;
    }


    public static double string2Double(String str) {
        double i = 0;
        try {
            i = Double.parseDouble(str);
        } catch (Exception e) {
        }
        return i;
    }

    public static int intAdd2String(String n1, String n2) {
        return string2Int(n1) + string2Int(n2);
    }

    public static int string2Int(String str) {
        int i = 0;
        try {
            i = Integer.valueOf(str);
        } catch (Exception e) {
        }
        return i;
    }

    public static long string2Long(String str) {
        long i = 0;
        try {
            i = Long.valueOf(str);
        } catch (Exception e) {
        }
        return i;
    }

    public static float string2FloatForStar(String str) {
        float i = 5;
        try {
            i = Float.valueOf(str);
            if (Math.abs(i - (int) i) > 0 && Math.abs(i - (int) i) < 1) {
                i = (int) i + 0.5f;
            }
        } catch (Exception e) {
        }
        return i;
    }

    /**
     * 判断字符串是否为Json格式
     *
     * @param result
     * @return
     */
    public static boolean isValidJson(String result) {
        try {
            new JSONObject(result);
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            try {
                new JSONArray(result);
                return true;
            } catch (JSONException e1) {
                e1.printStackTrace();

            }
        }
        return false;
    }

    /**
     * 校验字符串是否为手机号码
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobile(String mobiles) {
        String telRegex = "[1][34578]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、4、5、7、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    /**
     * 通过URL获取文件名
     *
     * @param context
     * @param uri
     * @return
     */
    public static String getFilePathByFileUri(Context context, Uri uri) {
        String filePath = null;
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        if (cursor.moveToFirst()) {
            filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        }
        cursor.close();
        return filePath;
    }

    /**
     * 获得2位数数字 01格式
     */
    public static String get2digitsFigure(int figure) {
        StringBuilder builder = new StringBuilder();
        if ((figure / 10) == 0) {
            builder.append("0").append(figure + "");
        } else {
            builder.append(figure + "");
        }
        return builder.toString();
    }

    /**
     * 得到一个字符串的实际长度
     *
     * @param str
     * @return
     */
    public static int getStringLength(String str) {
        try {
            if (!isEmpty(str))
                return str.getBytes("gb2312").length;
        } catch (Exception e) {

        }
        return 0;
    }

    /**
     * 计算该itemText是否超出了最大范围，超出之后就截取字符串，超出部分用...代替
     *
     * @param str
     * @param maxWidth
     * @param txtPaint
     * @return
     */
    public static String subString(String str, int maxWidth, Paint txtPaint) {
        if (str != null && txtPaint != null && maxWidth > 0) {
            int currentCalItemTextWidth = (int) txtPaint.measureText(str);
            // 越界
            if (currentCalItemTextWidth > maxWidth) {
                char[] textChars = str.toCharArray();
                if (textChars != null) {
                    StringBuilder newCalItemTextBuilder = new StringBuilder();
                    // 累计字符长度
                    int calCharsWidth = 0;
                    for (int i = 0; i < textChars.length; i++) {
                        String strChar = String.valueOf(textChars[i]);
                        if (strChar != null) {
                            int charWidth = (int) txtPaint.measureText(strChar);
                            int threePointCharWidth = (int) txtPaint.measureText("...");
                            calCharsWidth += charWidth;
                            if ((calCharsWidth + threePointCharWidth) < maxWidth) {
                                newCalItemTextBuilder.append(strChar);
                            } else {
                                newCalItemTextBuilder.append("...");
                                str = newCalItemTextBuilder.toString();
                                break;
                            }
                        }
                    }
                }
            }
        }

        return str;
    }

    /**
     * 得到一个限制大小后的字符串
     *
     * @param srcStr    原字符串
     * @param maxLength 限制的长度
     * @return
     */
    public static String getMaxStrLength(String srcStr, int maxLength) {
        int strLength = 0;
        // 最新最大长度字符串
        StringBuilder newMaxStrBuilder = new StringBuilder();
        if (isEmpty(srcStr) && maxLength > 0) {
            // 匹配中文的正则表达式
            String chineseChar = "[\u4e00-\u9fa5]";
            // 叠加每个字符的长度
            for (int i = 0; i < srcStr.length(); i++) {
                String temp = srcStr.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chineseChar)) {
                    strLength += 2;
                    if (strLength <= maxLength) {
                        newMaxStrBuilder.append(temp);
                    } else
                        break;
                } else {
                    strLength += 1;
                    if (strLength <= maxLength) {
                        newMaxStrBuilder.append(temp);
                    }
                }
            }
        }

        return newMaxStrBuilder.toString();
    }

    // 计算字符串的长度
    public static int computeStringLength(String str) {
        int strLength = 0;
        if (!isEmpty(str)) {
            String chineseChar = "[\u4e00-\u9fa5]";
            for (int i = 0; i < str.length(); i++) {
                String temp = str.substring(i, i + 1);
                if (temp.matches(chineseChar)) {
                    strLength += 2;
                } else {
                    strLength += 1;
                }
            }
        }
        return strLength;
    }

    public static String hideMobile(String mobile) {
        if (isEmpty(mobile)) {
            return "";
        }
        mobile = mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        return mobile;
    }


    public static boolean stringIsValid(String str) {
        String speChat = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern pattern = Pattern.compile(speChat);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find())
            return true;
        return false;
    }

    public static boolean isNumber(String mobiles) {
        Pattern p = Pattern.compile("^[0-9]{11}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static boolean isChineseAndPinyin(String str) {
        String regex = "^[a-zA-Z\u4e00-\u9fa5]+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        return m.find();
    }

    public static String checkNull(String str, String space) {
        return str == null ? space : str;
    }

    public static String checkNull(String str) {
        return checkNull(str, "");
    }
}
