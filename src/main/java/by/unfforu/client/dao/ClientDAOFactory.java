package by.unfforu.client.dao;

import by.unfforu.client.dao.impl.StudentClientDAOImpl;

/**
 * DAO factory class
 */
public class ClientDAOFactory
{
    /**
     * DAO factory instance
     */
    private static final ClientDAOFactory instance = new ClientDAOFactory();

    /**
     * Constructor
     */
    private ClientDAOFactory()
    {

    }

    /**
     * Get student DAO instance
     *
     * @return StudentClientDAO
     */
    public StudentClientDAO getStudentDAO()
    {
        return new StudentClientDAOImpl();
    }

    /**
     * Get DAO factory instance
     *
     * @return ClientDAOFactory
     */
    public static ClientDAOFactory getInstance()
    {
        return instance;
    }
}
