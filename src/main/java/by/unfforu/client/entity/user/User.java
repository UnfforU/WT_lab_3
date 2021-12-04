package by.unfforu.client.entity.user;

import by.unfforu.client.entity.role.UserRole;
import java.io.Serializable;

/**
 * User model
 */
public class User implements Serializable
{
    /**
     * User id
     */
    private int id;

    /**
     * User login
     */
    private String login;

    /**
     * User password
     */
    private String password;

    /**
     * User role
     */
    private UserRole role;

    /**
     * Get user id
     *
     * @return int
     */
    public int getId()
    {
        return id;
    }

    /**
     * Set user id
     *
     * @param id user id
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * Get user login
     *
     * @return String
     */
    public String getLogin()
    {
        return login;
    }

    /**
     * Set user login
     *
     * @param login user login
     */
    public void setLogin(String login)
    {
        this.login = login;
    }

    /**
     * Get user password
     *
     * @return String
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Set user password
     *
     * @param password user password
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * Get user role
     *
     * @return UserRole
     */
    public UserRole getRole()
    {
        return role;
    }

    /**
     * Set user role
     *
     * @param role user role
     */
    public void setRole(UserRole role)
    {
        this.role = role;
    }
}
