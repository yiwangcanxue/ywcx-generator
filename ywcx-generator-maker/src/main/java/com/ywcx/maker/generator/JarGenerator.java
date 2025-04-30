package com.ywcx.maker.generator;

import java.io.*;

import static java.nio.file.Files.newBufferedReader;

public class JarGenerator {

    public static void doGenerator(String projectDir) throws IOException, InterruptedException {
        //调用process，执行maven打包命令
        String winMavenCommand = "mvn.cmd clean package -D skipTests=true";
        String otherMavenCommand = "mvn clean package -D skipTests=true";
        String mavenCommand = winMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));
        Process process = processBuilder.start();
        //读取命令输出
        InputStream inputStream = process.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        int exitCode = process.waitFor();
        System.out.println("命令执行完毕，退出码为：" + exitCode);
    }
}
