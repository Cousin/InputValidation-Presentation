package edu.mvcc.jyaghy09.presentation;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Fun class I made to programatically run both examples and compare their outputs
 *
 * Automatically compiles, runs, provides input and reads output
 *
 * There is definitely a better way to do this, but I was just having fun
 */
public class OutputComparer {

    /**
     * The base directory of our current runtime
     */
    private static final String basePath = System.getProperty("user.dir");

    public static void main(String[] args) throws IOException {

        // The inputs that will be provided to all examples
        final Object[] inputs = new Object[] {

                4,
                3,
                101,
                98,
                97,
                -7,
                -8,
                14,
                -4,
                "X",
                "N",
                "p",
                "P",
                7,
                -2

        };

        // Our implementations classes to compare
        final Class<?>[] implementations = new Class<?>[] {
                InputValidationBad.class,
                InputValidationGood.class
        };

        // Compile source files to class files
        compileClasses(implementations);

        // Create set to store the outputs of each implementation
        final Set<List<String>> outputs = new HashSet<>();

        for (Class<?> implementation : implementations) {

            // Create process running this compiled implementation
            ProcessBuilder processBuilder = new ProcessBuilder("java", implementation.getCanonicalName());

            // In case of errors, redirect them to the output stream so we know what's going on
            processBuilder.redirectErrorStream(true);

            // Start the process
            Process process = processBuilder.start();

            // Write all of the test inputs to the process
            try (PrintWriter printWriter = new PrintWriter(process.getOutputStream())) {
                Arrays.stream(inputs).forEach(printWriter::println);
                printWriter.flush();
            }

            // Read the output and store it in our set
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                outputs.add(bufferedReader.lines().toList());
            }

        }

        // Since our set does not allow duplicates, if there is only one element, all implementations return the same output
        if (outputs.size() == 1) {
            System.out.println("All implementations return the same output");
        } else {
            System.err.println("Implementations return different outputs");
        }

    }

    /**
     * Compile a {@link Class} to an actual .class file
     * @param clazzez Java classes to compile
     */
    private static void compileClasses(Class<?>... clazzez) {
        for (Class<?> clazz : clazzez) {
            final String fileToCompile = basePath + "/src/main/java/" + clazz.getCanonicalName().replace(".", "/") + ".java";
            final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            final int compilationResult = compiler.run(null, null, null, fileToCompile, "-d", basePath);
            if (compilationResult != 0) {
                throw new RuntimeException("Compilation failed for " + clazz.getCanonicalName());
            }
        }
    }

}