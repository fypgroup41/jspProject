package ict.servlet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

/**
 *
 * @author test
 */
public class LoggerExample {

    public static void main(String[] args) throws ParseException {

        try {
            LogManager lm = LogManager.getLogManager();
            Logger logger;

            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd___HH_mm_ss");
            Date date = new Date();

            FileHandler fh = new FileHandler(dateFormat.format(date) + ".log");

            logger = Logger.getLogger("LoggingExample1");

            lm.addLogger(logger);
            logger.setLevel(Level.INFO);
            fh.setFormatter(new XMLFormatter());

            logger.addHandler(fh);
            logger.log(Level.INFO, "test 1");
            logger.log(Level.INFO, "test 2");
            logger.log(Level.INFO, "test 3");
            /* 
            logger.severe("嚴重訊息");
            logger.warning("警示訊息"); 
            logger.info("一般訊息");
            logger.config("設定方面的訊息");
            logger.fine("細微的訊息");
            logger.finer("更細微的訊息");
            logger.finest("最細微的訊息");
             */
            fh.close();
        } catch (Exception e) {
            System.out.println("Exception thrown: " + e);
            e.printStackTrace();
        }
    }
}
