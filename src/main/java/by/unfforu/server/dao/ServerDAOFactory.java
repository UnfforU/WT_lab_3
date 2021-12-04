package by.unfforu.server.dao;

import by.unfforu.server.dao.impl.StudentServerDAOImpl;

/**
 * DAO factory class
 */
public class ServerDAOFactory
{
    /**
     * DAO factory instance
     */
    private static final ServerDAOFactory instance = new ServerDAOFactory();

    /**
     * Constructor
     */
    private ServerDAOFactory()
    {

    }

    /**
     * Get student DAO instance
     *
     * @return StudentServerDAO
     */
    public StudentServerDAO getStudentDAO()
    {
        return new StudentServerDAOImpl();
    }

    /**
     * Get DAO factory instance
     *
     * @return ServerDAOFactory
     */
    public static ServerDAOFactory getInstance()
    {
        return instance;
    }
}
