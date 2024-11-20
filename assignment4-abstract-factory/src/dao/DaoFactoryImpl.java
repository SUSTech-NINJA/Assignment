package dao;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DaoFactoryImpl implements DaoFactory {
    private static DaoFactoryImpl instance = new DaoFactoryImpl();
    private Properties properties;

    private DaoFactoryImpl() {
        properties = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("src/resource.properties"));
            properties.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized DaoFactoryImpl getInstance() {
        if (instance == null) {
            instance = new DaoFactoryImpl();
        }
        return instance;
    }

    @Override
    public ComputerDao createComputerDao() {
        return createDao(properties.getProperty("computerDao"));
    }

    @Override
    public StaffDao createStaffDao() {
        return createDao(properties.getProperty("staffDao"));
    }

    private <T> T createDao(String className) {
        try {
            return (T) Class.forName(className).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
