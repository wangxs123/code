/**
 * @author wangxs
 * @date Create by 2018/12/27 13:53
 * 时间格式化工具类，传入long型的秒数，返回xx时xx分xx秒
 */
public class FormatDate {
    private static long HOUR = 3600;
    private static long SECONDS = 60;
    public static String secondsToDateTime(long seconds) {
        long hour = 0;
        long minute = 0;
        long second = 0;
        long temp = seconds % HOUR;
        if (seconds > HOUR) {
            hour = seconds / HOUR;
            if (temp != 0) {
                if (temp > SECONDS) {
                    minute = temp / SECONDS;
                    if (temp % SECONDS != 0) {
                        second = temp % SECONDS;
                    }
                } else {
                    second = temp;
                }
            }
        } else {
            minute = seconds / SECONDS;
            if (seconds % SECONDS != 0) {
                second = seconds % SECONDS;
            }
        }
        // return (h < 10 ? "0" + h : h) + "时" + (d < 10 ? "0" + d : d) + "分"  
        //        + (s < 10 ? "0" + s : s) + "秒";
        return hour + "小时" + minute + "分钟"
                + second + "秒";
    }
}
