# Lab-application
Java RMI

Part 1: 
Device manager : centralized version
The device manager application provided for this lab is intended to manage devices characterized by a name and
a status, and provide means to change and supervise the devices states. This application is currently a centralized
application composed of a single device and a device manager that manages this single device.

Part 2:
Device manager : distributed devices
Provide a new client/server version of this application composed of a single device manager and several devices
that can be launched on several hosts. This application should allow the user to enter commands to change the
device status on the device host, and display the device id and status changes on the device host. Moreover, the
application should allow the user to enter a command in the form STATUS id that should display the status of
the device having the given id.

Part 3:
Distributed device manager : improved version
Provide a new version of the client/server application that displays the status changes of a device on the hosts of
all the other devices : the device manager should inform all the devices at each change in a device status.
