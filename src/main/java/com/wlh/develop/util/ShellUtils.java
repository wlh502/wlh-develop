package com.wlh.develop.util;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ShellUtils {

    public static String auth(String scriptPath){
        try {
            ProcessBuilder builder = new ProcessBuilder("/bin/chmod", "777", scriptPath);
            Process process = builder.start();
            int status = process.waitFor();
            if(status != 0){
                // todo
                System.out.println("脚本执行权限授权失败");
                return null;
            }
        }catch (Exception e){
            // todo
            System.out.println(e);
        }
        return scriptPath;
    }

    public static String exec(String bashCommand) {
        if(ObjectUtils.isEmpty(bashCommand)){
            return null;
        }
        StringBuilder result = new StringBuilder();
        try {

            String[] command = {"/bin/sh", "-c", bashCommand};
            Process process = Runtime.getRuntime().exec(command);
            int status = process.waitFor();
            if(status != 0){
                // todo 脚本执行失败
                return null;
            }
            try(
                 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            )
            {
                String line = "";
                while ((line = reader.readLine()) != null) {
                    //System.out.println(line);
                    result.append(line);
                }
            }catch (IOException e){
                // todo 流读取失败
                System.out.println(e);
            }

        }catch (Exception e){
            // todo 日志记录
            System.out.println(e);
            return null;
        }
        return result.toString();
    }
}
