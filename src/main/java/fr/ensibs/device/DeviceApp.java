package fr.ensibs.device;

import java.io.Closeable;
import java.util.Scanner;
import java.util.Set;

/**
 * An application used to manage a single device. It can be used as a
 * standalone application as it provides a {@link #main(String[]}
 * method.
 */
public class DeviceApp implements Closeable
{

    /**
     * the device hosted by this application
     */
    private DefaultDevice myDevice;

    /**
     * the device manager that manages the device
     */
    private DeviceManager deviceManager;

    /**
     * true if the application has been closed
     */
    private boolean closed;

    /**
     * Print a usage message and exit. Used when the standalone
     * application is started with wrong command line arguments
     */
    private static void usage()
    {
        System.out.println("Usage: java DeviceApp <device_name>");
        System.out.println("An application to interract with a device");
        System.exit(-1);
    }

    /**
     * The entry point for a standalone application. Creates a single
     * device and device manager and ask the user to enter device
     * status changes
     */
    public static void main(String[] args)
    {
        if (args.length != 1 || args[0].equals("-h")) {

            usage();
        }

        DeviceApp instance = new DeviceApp(args[0], new DefaultDeviceManager());
        instance.run();
    }

    /**
     * Constructor. Create the device and register it to the device manager
     *
     * @param deviceName the name of the device
     * @param deviceManager the
     */
    public DeviceApp(String deviceName, DeviceManager deviceManager)
    {
        this.myDevice = new DefaultDevice(deviceName);
        this.deviceManager = deviceManager;
        this.deviceManager.register(myDevice);
    }

    /**
     * Ask the user to enter commands to change the device
     * status. Used by the standalona application. Cannot be used with
     * other instances of this class as it uses a {@link Scanner}
     * object to read user inputs from the standard input stream.
     */
    public void run()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println(myDevice +
                           " Enter STATUS <id>, LIST, START, STOP, QUIT commands to manage the device status");

        while (!closed) {
            String line = scanner.nextLine();
            if (line.startsWith("status") || line.startsWith("STATUS")) {
                displayStatus(line.substring(7));
            } else {
                switch (line) {
                case "list":
                case "LIST":
                    list();
                    break;
                case "stop":
                case "STOP":
                    stop();
                    break;
                case "start":
                case "START":
                    start();
                    break;
                case "quit":
                case "QUIT":
                    close();
                }
            }
        }
    }

    /**
     * Display the status of the device having the given id, if it exists
     *
     * @param deviceId the id of the device to be displayed
     */
    public void displayStatus(String deviceId)
    {
        Status status = this.deviceManager.getStatus(deviceId);
        if (status == null) {
            System.out.println("Device " + deviceId + " not found");
        } else {
            System.out.println("Device " + deviceId + ": " + status);
        }
    }
    
    /**
     * List the existing devices ids
     */
    public void list()
    {
        Set<String> ids = this.deviceManager.getRegisteredIds();
        System.out.println("Registered ids: " + ids);
    }

    /**
     * Stop the device
     */
    public void stop()
    {
        if (!closed) {
            this.setStatus(Status.STOPPING);
            try { Thread.sleep(3000); } catch (Exception e) { }
            this.setStatus(Status.STOPPED);
        }
    }

    /**
     * Start the device
     */
    public void start()
    {
        if (!closed) {
            this.setStatus(Status.STARTING);
            try { Thread.sleep(3000); } catch (Exception e) { }
            this.setStatus(Status.RUNNING);
        }
    }

    /**
     * Unregister the device
     */
    @Override
    public void close()
    {
        this.closed = true;
        this.deviceManager.unregister(myDevice.getId());
    }

    /**
     * Change the status of the device
     *
     * @param status the new status
     */
    private void setStatus(Status status)
    {
        this.myDevice.setStatus(status);
    }
}
