import solution.Solution;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws IOException {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String propertiesPath = rootPath + "baekjoon.properties";

        Properties baekjoonProperties = new Properties();
        baekjoonProperties.load(new FileInputStream(propertiesPath));

        String code = baekjoonProperties.getProperty("problem.code");
        String input = baekjoonProperties.getProperty("problem.input");
        String basePackage = baekjoonProperties.getProperty("solution.package");
        String prefix = baekjoonProperties.getProperty("solution.class.prefix");
        String interfaceName = baekjoonProperties.getProperty("solution.interface.name");

        System.setIn(new FileInputStream(rootPath + input));

        try {
            Class<?> solutionClass = Class.forName(basePackage + "." + prefix + code);
            Class<?> solutionInterface = Class.forName(basePackage + "." + interfaceName);
            if (!solutionInterface.isAssignableFrom(solutionClass)) {
                throw new Exception("Solution class doesn't implement interface");
            }

            Constructor<?> constructor = solutionClass.getConstructor();
            Solution solution = (Solution)constructor.newInstance();
            solution.solve();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}