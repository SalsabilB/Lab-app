package fr.ensibs.device;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A manager that registers devices in the system, and assign them
 * unique identifiers and maintains informations about their names and
 * status
 */
public class DefaultDeviceManager implements DeviceManager
{

    /**
     * the next id that can be assigned to a device
     */
    private int nextId;

    /**
     * A map that associates device ids to devices
     */
    private Map<String,Device> devices = new HashMap<>();

    /**
     * Register the given device which id is not yet initialized and
     * assign it a unique id
     *
     * @param device the device to be registered
     * @return the id assigned to the device
     */
    public String register(Device device)
    {
        String id = Integer.toString(nextId++);
        device.setId(id);
        this.devices.put(id, device);
        return id;
    }

    /**
     * Unregister the device having the given id. The device id can
     * thus be later reassigned to another device
     *
     * @param deviceId the id of the device to be unregistered
     */
    public void unregister(String deviceId)
    {
        this.devices.remove(deviceId);
    }

    /**
     * Give the identifiers of the devices currently registered
     *
     * @return the ids of the registered devices
     */
    public Set<String> getRegisteredIds()
    {
        return this.devices.keySet();
    }

    /**
     * Give the status of the device having the given id, if it exists
     * and is registered
     *
     * @param deviceId the id of the device
     * @return the status if the device exists, null otherwise
     */
    public Status getStatus(String deviceId)
    {
        Device device = this.devices.get(deviceId);
        if (device != null) {
            return device.getStatus();
        }
        return null;
    }

    /**
     * Give the name of the device having the given id, if it exists
     * and is registered
     *
     * @param deviceId the id of the device
     * @return the name if the device exists, null otherwise
     */
    public String getName(String deviceId)
    {
        Device device = this.devices.get(deviceId);
        if (device != null) {
            return device.getName();
        }
        return null;
    }

}
