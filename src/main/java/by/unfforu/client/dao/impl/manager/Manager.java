package by.unfforu.client.dao.impl.manager;

import by.unfforu.client.entity.request.StudentRequest;
import by.unfforu.client.entity.response.StudentResponse;
import by.unfforu.client.entity.request.RequestType;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Client socket manager
 */
public class Manager
{
    /**
     * Server ip
     */
    private final String ip;

    /**
     * Server port
     */
    private final int port;

    /**
     * Constructor
     *
     * @param ip server ip
     * @param port server port
     */
    public Manager(String ip, int port)
    {
        this.ip = ip;
        this.port = port;
    }

    /**
     * Send request to the server
     *
     * @param type request type
     * @param body request body
     *
     * @return StudentResponse
     */
    public StudentResponse sendRequest(RequestType type, Object body)
    {
        Socket client = null;
        try
        {
            try
            {
                // Get new client socket instance
                client = new Socket(this.ip, this.port);

                // Initialize output and input streams
                ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(client.getInputStream());

                // Set request information
                StudentRequest request = new StudentRequest();
                request.setBody(body);
                request.setRequestType(type);

                // Write request in output stream
                out.writeObject(request);
                out.flush();

                // Get server response
                return (StudentResponse)in.readObject();
            }
            catch (IOException | ClassNotFoundException e)
            {
                System.out.printf("Error client: %s%n", e.getMessage());
            }
            finally
            {
                if ((client != null) && !client.isClosed())
                {
                    client.close();
                }
            }

        }
        catch (IOException e)
        {
            System.out.printf("Error client: %s%n", e.getMessage());
        }

        return null;
    }
}
