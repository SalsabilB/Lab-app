package fr.ensibs.device;

import java.util.Set;

/**
 * A manager that registers devices in the system, and assign them
 * unique identifiers and maintains informations about their names and
 * status
 */
public interface DeviceManager
{

    /**
     * Register the given device which id is not yet initialized and
     * assign it a unique id
     *
     * @param device the device to be registered
     * @return the id assigned to the device
     */
    String register(Device device);

    /**
     * Unregister the device having the given id. The device id can
     * thus be later reassigned to another device
     *
     * @param deviceId the id of the device to be unregistered
     */
    void unregister(String deviceId);

    /**
     * Give the identifiers of the devices currently registered
     *
     * @return the ids of the registered devices
     */
    Set<String> getRegisteredIds();

    /**
     * Give the status of the device having the given id, if it exists
     * and is registered
     *
     * @param deviceId the id of the device
     * @return the status if the device exists, null otherwise
     */
    Status getStatus(String deviceId);

    /**
     * Give the name of the device having the given id, if it exists
     * and is registered
     *
     * @param deviceId the id of the device
     * @return the name if the device exists, null otherwise
     */
    String getName(String deviceId);

}
