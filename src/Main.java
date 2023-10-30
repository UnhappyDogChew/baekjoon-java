import solution.Solution;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        Executor executor = new Executor();
        executor.execute();
    }

    static class Executor {
        final String ROOT_PATH;
        final String PROBLEM_CODE;
        final String PROBLEM_INPUT;
        final String SOLUTION_PACKAGE;
        final String SOLUTION_PATH;
        final String SOLUTION_CLASS_PREFIX;
        final String SOLUTION_INTERFACE_NAME;
        final String SUBMISSION_PATH;
        final String SUBMISSION_TEMPLATE;

        public Executor() {
            ROOT_PATH = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            String propertiesPath = ROOT_PATH + "baekjoon.properties";

            Properties baekjoonProperties = new Properties();
            try (FileInputStream propertiesInputStream = new FileInputStream(propertiesPath)) {
                baekjoonProperties.load(propertiesInputStream);
            } catch (Exception e) {
                System.out.println("failed to load properties file: " + e.getMessage());
            }

            PROBLEM_CODE = baekjoonProperties.getProperty("problem.code");
            PROBLEM_INPUT = baekjoonProperties.getProperty("problem.input");
            SOLUTION_PACKAGE = baekjoonProperties.getProperty("solution.package");
            SOLUTION_PATH = baekjoonProperties.getProperty("solution.path");
            SOLUTION_CLASS_PREFIX = baekjoonProperties.getProperty("solution.class.prefix");
            SOLUTION_INTERFACE_NAME = baekjoonProperties.getProperty("solution.interface.name");
            SUBMISSION_PATH = baekjoonProperties.getProperty("submission.path");
            SUBMISSION_TEMPLATE = baekjoonProperties.getProperty("submission.template");
        }

        public void execute() throws IOException {
            System.setIn(new FileInputStream(ROOT_PATH + PROBLEM_INPUT));

            try {
                Class<?> solutionClass = Class.forName(SOLUTION_PACKAGE + "." + SOLUTION_CLASS_PREFIX + PROBLEM_CODE);
                Class<?> solutionInterface = Class.forName(SOLUTION_PACKAGE + "." + SOLUTION_INTERFACE_NAME);
                if (!solutionInterface.isAssignableFrom(solutionClass)) {
                    throw new Exception("Solution class doesn't implement interface");
                }

                Constructor<?> constructor = solutionClass.getConstructor();
                Solution solution = (Solution)constructor.newInstance();
                solution.solve();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            File templateFile = new File("src/" + SUBMISSION_TEMPLATE);
            File solutionFile = new File("src/" + SOLUTION_PATH + SOLUTION_CLASS_PREFIX + PROBLEM_CODE + ".java");
            File submissionFile = new File(SUBMISSION_PATH + SOLUTION_CLASS_PREFIX + PROBLEM_CODE + "_Solution.java");

            if (submissionFile.getParentFile().mkdirs()) {
                System.out.println("submission directory created at: " +
                        submissionFile.getParentFile().getAbsolutePath());
            }
            if (submissionFile.createNewFile()) {
                System.out.println("submission file created at: " +
                        submissionFile.getAbsolutePath());
            }

            try (
                    FileReader templateReader = new FileReader(templateFile);
                    BufferedReader solutionClassReader = new BufferedReader(new FileReader(solutionFile));
                    FileWriter submissionWriter = new FileWriter(submissionFile);
            ) {
                // write template first
                templateReader.transferTo(submissionWriter);

                String startLineRegex = "public class [\\w\\d]+ implements Solution \\{";
                String line = solutionClassReader.readLine();
                boolean foundFirstLine = false;
                while (line != null) {
                    if (foundFirstLine) {
                        submissionWriter.write(line + "\n");
                    }
                    if (line.matches(startLineRegex)) {
                        foundFirstLine = true;
                    }
                    line = solutionClassReader.readLine();
                }

            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
