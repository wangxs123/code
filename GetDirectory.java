package direct.test;

import java.io.File;

public class GetDirectory {
	public static void main(String[] args) {
        String path = "E:/test1";
        test(path);
  }
  public static void test(String path){
	  	//获取路径
        File file = new File(path);
        //将统计目录的文件名或目录名放如数组
        File[] lFiles = file.listFiles();
        //判断数组是否为空
        if (lFiles==null) {
             return;
        }
        //遍历数组
        for (File file2 : lFiles) {
        	//判断该路径是否还有下级目录
             if (file2.isFile()) {
            	 //如果没有则输出路径名
                  System.out.println(file2.getPath());
             }else {
            	 //如果包含子目录则继续调用
                  test(file2.getPath());
             }
        }
  }
}

