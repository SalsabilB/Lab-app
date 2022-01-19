package fr.ensibs.device;

/**
 * A device managed by the system, characterized by an identifier, a name,
 * and having a status which value changes along the system lifetime.
 */
public interface Device
{

    /**
     * Give the device unique id, assigned by the system and that
     * nevers changes afterwards
     *
     * @return the device unique id
     */
    String getId();

    /**
     * Set the device unique id. This method should be invoked only
     * once, and provide a system-wide unique id
     *
     * @param id the unique id assigned to the device
     * @throws IllegalStateException if an id has already been
     * assigned to this device
     */
    void setId(String id) throws IllegalStateException;

    /**
     * Give the device "human-readable" name
     *
     * @return the device name
     */
    String getName();

    /**
     * Give the device instantaneous status
     *
     * @return the device status
     */
    Status getStatus();

}
