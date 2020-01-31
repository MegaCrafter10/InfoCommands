# InfoCommands 
### v1.0

Adds the following commands:
* **/info \<command\>** - displays information about the specified command. The information is read from the config file.

example of the config file:
```
commands:
  command: info
  command2: &2&lcolored info!
```
the plugin also supports color codes.

If a player types a command that is not specified in the config, the following message is displayed in red "There is no information available for that command"
