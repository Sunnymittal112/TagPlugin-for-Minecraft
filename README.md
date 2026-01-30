# TagPlugin

TagPlugin is a server-side utility designed for Minecraft Paper 1.21.1. It facilitates the management of player prefixes (tags) through the native Scoreboard API. This ensures that player tags are persistent, lightweight, and fully integrated with the vanilla Minecraft rendering engine (Player Nameplate and Tab List).

## Overview

The primary function of TagPlugin is to assign specific titles to players without requiring complex permission groups or database connections. The plugin automatically enforces a standardized formatting style (Bold + Gold) to maintain visual consistency across the server.

## Features

* **Persistent Storage:** Utilizes Minecraft's internal Scoreboard Team system, ensuring tags remain saved even after server restarts.
* **Automatic Formatting:** Enforces a predefined professional style (Bold Gold text within Dark Gray brackets) eliminating the need for manual color code entry.
* **Multi-Word Support:** Capable of processing multi-word titles (e.g., "Senior Admin") as a single tag argument.
* **High Performance:** operaties with O(1) complexity for tag retrieval, ensuring zero impact on server tick rates (TPS).

## Installation

1.  Stop your Minecraft Server.
2.  Download the `TagPlugin.jar` file.
3.  Upload the file to the `/plugins/` directory of your server.
4.  Start the server.

## Commands and Permissions

The following commands are available for server administrators.

| Command | Arguments | Description | Permission Node |
| :--- | :--- | :--- | :--- |
| `/tag` | `<player> <text>` | Assigns a formatted tag to the specified player. | `tagplugin.admin` |
| `/resettag` | `<player>` | Removes the current tag and resets the nameplate. | `tagplugin.admin` |

### Usage Example
To assign the tag "Owner" to the player "FeTaL":

```bash
/tag FeTaL Owner
```

####Color Code Reference

For documentation purposes, the following table explains the internal color codes used by the Minecraft engine:
Code	Type	Technical Name	Visual Result
```bash
&0 - &9	Color	Standard Colors	Defines the text color (e.g., &6 is Gold).
&a - &f	Color	Light Colors	Lighter variants (e.g., &c is Red).
&l	Format	Bold	Increases font weight.
&o	Format	Italic	Slants the text.
&n	Format	Underline	Adds a line beneath the text.
&k	Format	Obfuscated	Creates moving/scrambled text.
&r	Format	Reset	Resets all previous formatting.
```
