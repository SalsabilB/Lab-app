package fr.ensibs.device;

/**
 * An implementation of a device managed by the system, characterized
 * by an identifier, a name, and having a status which value changes
 * along the system lifetime. This class is implemented to be deployed
 * in a centralized application
 */
public class DefaultDevice implements Device
{

    /**
     * the device unique id
     */
    private String id;

    /**
     * the device "human-readable" name
     */
    private String name;

    /**
     * the device instantaneous status
     */
    private Status status;

    /**
     * Constructor: initialize the id and name attributes, but not the id
     *
     * @param name the device initial name
     */
    public DefaultDevice(String name)
    {
        this.name = name;
        this.status = Status.STOPPED;
    }

    @Override
    public String getId()
    {
        return this.id;
    }

    @Override
    public void setId(String id) throws IllegalStateException
    {
        if (this.id != null) {
            throw new IllegalStateException("Unable to set a new id. Current device id: " + this.id);
        }
        this.id = id;
    }

    @Override
    public String getName()
    {
        return this.name;
    }

    @Override
    public Status getStatus()
    {
        return this.status;
    }

    /**
     * Change the device name
     *
     * @param name the new device name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Change the device instantaneous status
     *
     * @param status the new device status
     */
    public void setStatus(Status status)
    {
        this.status = status;
        System.out.println(this.toString() + " " + status);
    }

    @Override
    public String toString()
    {
        return "[" + this.name + "," + this.id + "]";
    }
}
