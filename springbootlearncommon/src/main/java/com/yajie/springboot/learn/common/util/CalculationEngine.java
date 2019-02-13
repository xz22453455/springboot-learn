package com.yajie.springboot.learn.common.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 〈获取单例进行python脚本调用〉<br>
 * 〈〉
 *
 * @author Administrator
 * @create 2018/8/15 0015
 * @since 1.0.0
 */
@Component
public class CalculationEngine {

    private static String pythonPath;

    @Value("${spring.pythonScript-path}")
    public void setPythonPath(String pythonPath) {
        CalculationEngine.pythonPath = pythonPath;
    }

    private volatile static CalculationEngine pyUtil = null;

    public String callPythonScript(String algorithm, Map map) throws Exception {
        String line;
        StringBuilder reStr = new StringBuilder();
        StringBuilder sb = new StringBuilder();
        Iterator<Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, String> entry = it.next();
            String parameter = "," + entry.getKey() + "=" + entry.getValue();
            sb.append(parameter);
        }
        String parameters = new String(sb);
        parameters = parameters.substring(1);
        String[] str = new String[]{"python", pythonPath, algorithm, parameters};
        Process pr = Runtime.getRuntime().exec(str);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pr.getInputStream()));
        try {
            while ((line = bufferedReader.readLine()) != null) {
                reStr = reStr.append(line);
            }
            pr.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bufferedReader.close();
        }
        String returnStr = new String(reStr);
        return returnStr;
    }

    public static CalculationEngine getInstance() {
        if (pyUtil == null) {
            synchronized (CalculationEngine.class) {
                if (pyUtil == null) {
                    pyUtil = new CalculationEngine();
                }
            }
        }
        return pyUtil;
    }
}
