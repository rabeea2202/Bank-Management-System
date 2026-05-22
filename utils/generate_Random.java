package utils;
import models.*;
import transactions.*;
public class generate_Random {
    public static long generateRandomTransactionID() { return (long)(Math.random() * 100000000); }
    public static long generateRandomAccountNo() { return (long)(Math.random() * 100000000); }
}
