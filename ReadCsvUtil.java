package com.kalamodo.outplan.utils;

import com.kalamodo.outplan.bean.CallLogInfo;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 读取csv文件工具类
 * @author wangxs
 * @date Create by 2018/9/7 14:13
 */
public class ReadCsvUtil {
    private static final String CUSTOMER_NAME="客户姓名";
    private static final String CUSTOMER_NO="客户号码";
    private static final String FIX="\uFEFF";
    private static final int SIZE=3;
    /**
     * 获取csv文件内容
     * @param path 文件
     * @return 对象信息
     */
    public static List<CallLogInfo> getResource(String path) throws IOException {
        List<CallLogInfo> allString = new ArrayList<>();
        CallLogInfo callLogInfo ;
        List<String> list = new ArrayList<>();
        // 获取文件内容
        list = getSource(path);
        // 获取文件表头
        List<String> title = Arrays.asList(list.get(0).split(","));
        String customerName = title.get(0).trim();
        String customerNo = title.get(1).trim();
        // 头部会带有"\uFEFF"值，暂时这么处理
        if(customerName.startsWith(FIX)){
            customerName = customerName.replace(FIX, "");
        }
        if(customerName.startsWith(FIX)){
            customerName = customerName.replace(FIX, "");
        }
        if(CUSTOMER_NAME.equals(customerName) && CUSTOMER_NO.equals(customerNo)   ) {
            list.remove(0);
            // 循环内容
            for(int i = 0; i<list.size();i++){
                List<String> content = Arrays.asList(list.get(i).split(","));
                // UUID用于多表关联的外键
                String uuid = UUID.randomUUID().toString();
                // 当没有添加额外参数时
                if(content.size() <= 2){
                    callLogInfo = new CallLogInfo();
                    callLogInfo.setPhoneNumberUuid(uuid);
                    callLogInfo.setPlanClName(content.get(0));
                    callLogInfo.setPlanClNumber(content.get(1));
                    allString.add(callLogInfo);
                }else {
                    /**
                     * 此处为带不定参数列的代码
                     */
                    for(int k = 2; k<content.size();k++){
                        callLogInfo = new CallLogInfo();
                        callLogInfo.setPhoneNumberUuid(uuid);
                        callLogInfo.setPlanClName(content.get(0));
                        callLogInfo.setPlanClNumber(content.get(1));
                        callLogInfo.setParamName(title.get(k));
                        callLogInfo.setParamValue(content.get(k));
                        allString.add(callLogInfo);
                    }
                }
            }
        }else {
            return  allString;
        }
        return  allString;
    }

    /**
     * 读文件数据
     * @param path
     * @return
     * @throws UnsupportedEncodingException
     */
    public static List<String> getSource(String path) throws  IOException {
        File csv = new File(path);
        BufferedReader br = null;
        FileInputStream fis = null;
        InputStreamReader isr = null;
        try {
            fis = new FileInputStream(path);
            //指定以UTF-8编码读入
            isr = new InputStreamReader(fis,"UTF-8");
            br = new BufferedReader(isr);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String line;
        String everyLine ;
        List<String> allString = new ArrayList<>();
        try {
            //读取到的内容给line变量
            while ((line = br.readLine()) != null)
            {
                everyLine = line;
                allString.add(everyLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fis != null){
                fis.close();
            }
            if(isr != null){
                isr.close();
            }
        }
        return allString;
    }
    /**
     * 删除临时文件
     *
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                if(file.delete()){
                    System.out.println("shanchu chengg ");
                }else{
                    System.out.println("222222");
                }
            }
        }
    }

}
