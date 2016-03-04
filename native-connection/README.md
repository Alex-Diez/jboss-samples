# Configure WildFly 10 for remote native connection

1. Configure socket binding group by adding new socket binding
```
<socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:0}">
    ...
    <socket-binding name="remote-management" interface="remote-management" port="${jboss.remote-management.native.port:9995}"/>
    ...
</socket-binding-group>
```
2. Configure interfaces by adding new interface
```
<interfaces>
    ...
    <interface name="remote-management">
        <inet-address value="${jboss.bind.address.remote-management:127.0.0.1}"/>
    </interface>
    ...
<interfaces>
```
3. Configure management-interface section by adding native interface
```
<management-interfaces>
    ...
    <native-interface security-realm="ManagementRealm">
        <socket-binding native="remote-management"/>
    </native-interface>
    ...
</management-interfaces>
```
