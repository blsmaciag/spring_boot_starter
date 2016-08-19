package com.gft;

import org.apache.commons.cli.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

    private static final String PROPERTY_FILE_NAME = "starter.property.file";
    private static final String ENV_SHORT_SWITCH = "env";
    private static final String ENV_LONG_SWITCH = "environment";
    private static final String ARGUMENT_NAME = "environment";

    public static void main(String[] args) throws Exception {
        loadProperConfiguration(args);
        ApplicationContext ctx = SpringApplication.run(Main.class, args);
        PropertiesConfig properties = ctx.getBean(PropertiesConfig.class);
        System.out.println("Example message loaded from property file: "+ properties.getProperty("environment.welcome.message"));
    }

    /**
     * Loads environment name from command line arguments, translates it to property file name
     * and stores it as Java system property. This property will be read by spring when application context is loaded and
     * will let it choose correct property file.
     *
     * @param args - application command line args
     * @throws Exception
     */
    private static void loadProperConfiguration(String args[]) throws Exception{
        putPropertyFileNameAsSystemProperty(extractEnvNameFromCommandLineArgs(args));
    }

    /**
     * Reads command line arguments to find the name of environment passed by user.
     * Uses apache CLI which makes parsing of arguments simpler.
     *
     * @param args - arguments the program is run with.
     * @return name of environment i.e. UAT or DEV
     * @throws Exception - thrown when
     */
    private static String extractEnvNameFromCommandLineArgs(String args[]) throws Exception {
        //apache CLI - capache ommand line
        Options options = new Options();
        Option envOption = Option.builder(ENV_SHORT_SWITCH).argName(ARGUMENT_NAME)
                .required(true)
                .hasArg()
                .longOpt(ENV_LONG_SWITCH)
                .desc("Environment configuration application needs to be run with.").build();
        options.addOption(envOption);
        CommandLineParser parser = new DefaultParser();
        CommandLine cmdl = parser.parse(options, args);
        return EnvironmentConfig.getPropertyFile(cmdl.getOptionValue("environment"));
    }

    /**
     * Sets Java system property which will be read by Spring in order to find correct property file.
     */
    private static void putPropertyFileNameAsSystemProperty(String filename) {
        System.getProperties().setProperty(PROPERTY_FILE_NAME, filename);
    }

}
