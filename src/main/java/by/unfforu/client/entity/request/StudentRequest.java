package by.unfforu.client.entity.request;

import java.io.Serializable;

/**
 * Request model
 */
public class StudentRequest implements Serializable
{
    /**
     * Request type
     */
    private RequestType requestType;

    /**
     * Request information
     */
    private Object body;

    /**
     * Get request type
     *
     * @return RequestType
     */
    public RequestType getRequestType()
    {
        return requestType;
    }

    /**
     * Set request type
     *
     * @param requestType request type
     */
    public void setRequestType(RequestType requestType)
    {
        this.requestType = requestType;
    }

    /**
     * Get request information
     *
     * @return Object
     */
    public Object getBody()
    {
        return this.body;
    }

    /**
     * Set request information
     *
     * @param body request information
     */
    public void setBody(Object body)
    {
        this.body = body;
    }
}
