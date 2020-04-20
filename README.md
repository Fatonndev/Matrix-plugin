> ## [Link to Discord support server](https://discord.gg/u8kkNbK)

***

# Matrix plugin
This plugin adds most commands to your server.

## Features of the plugin:<br>

### The relationship between Discord and Mindustry, namely:
 - Sending Discord messages to the game
 - Sending game messages to Discord
 - Logging of registered commands
 - Messages about the player's entry/exit
 - View the contents of the core in the shell
 
### New commands for adminitrators:
 - Spawn of ore with the specified radius
 - Installation of any unit
 - Enable / disable infinite resources
 - JS command in the game itself.
 - Notification for the entire server
 
### In addition, there are other fitches:
 - Animated nickname ([animated])
 - Auto moderation of words
 - Inscription at the top of the screen
 - Players can change the team color in PvP
 - Fully customizable messages and commands

## Installation

Put the plugin in ``<server folder location>/config/mods`` folder.<br>
Start the server. Configs are generated.<br>
Edit the config in the ``<server folder location>/config/mods/Matrix/config.properties`` folder.<br>
Enjoy the plugin

## Matrix 0.1 Plans
- [ ] Administration
  - [ ] Mutes
  - [ ] Temporary bans
- [ ] Discord
  - [ ] Statistics
  - [ ] Loading maps via message
  - [ ] Console
- [ ] Setting up permissions (Via a separate plugin)
- [ ] Commands
  - [X] Teleportation to the players or to the point /tp
  - [ ] Custom /help
  - [ ] Private message /m
- [ ] Code optimization
- [ ] Linking a database

## Client commands

| Command | Parameters | Description | Permission
|:---|:---|:---|:--- |
| `setteam` | `<team color>` | Sets the *team* by *color* | **Player** |
| `spawnore` | `<radius>` `<ore name>` | Spawn a *vein of ore* with a *radius* | **Administrator** |
| `setblock` | `<block name from Blocks.java>` | Puts a *block* under the player | **Administrator** |
| `infiniteresources` | `<on/off>` | Enables **infinite resources** on the map | **Administrator** |
| `bc` | `<message>` | Sends a **pop-up window** to players | **Administrator** |
| `js` | `<code>` | Executes **js code** | **Administrator** |
| `gameover` | - | Ends the game | **Administrator** |
| `tppos` | `<x>` `<y>` | **TP** to *position* | **Player** |
| `tp` | `<playerName>` | **TP** to *Player* | **Player** |

## Server commands

| Command | Parameters | Description |
|:---|:---|:--- |
| `ping` | - | Return **pong**! |
| `nogui` | - | Auto start for minecraft hosting |
