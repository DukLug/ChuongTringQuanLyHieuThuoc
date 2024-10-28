package functionalClass;

import application.PhanMemQuanLyHieuThuoc;
import userInterface.TrangChuUI;

public class GlobalExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        // Check if the throwable is an instance of Exception
        if (e instanceof Exception) {
            Exception exception = (Exception) e;
            // Retrieve the source class of the error from the stack trace
            StackTraceElement[] stackTrace = exception.getStackTrace();
            if (stackTrace.length > 0) {
                Class<?> errorSource = null;
                try {
                    errorSource = Class.forName(stackTrace[0].getClassName());
                } catch (ClassNotFoundException ex) {
                    // Default to Object class if the class is not found
                    errorSource = Object.class;
                }
                // Call hienLoi method in TrangChuUI
                PhanMemQuanLyHieuThuoc.hienLoi(errorSource, exception);
            }
        }
    }
    
    public static void registerExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
    }
}
