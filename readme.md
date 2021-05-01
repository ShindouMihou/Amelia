#### Amelia, a Discord Bot of Waters.

Amelia is a simple Discord bot that is used to fetch RSS feeds, and updates users
about a certain author updating their stories.

#### Requirements
* Java 11.
* MongoDB.
* A server (at least 1 GB of RAM, 2 vCPU)

#### How to install, public bot.

The Amelia bot is already hosted publicly on our servers, as such, you can add it to your own
discord server by inviting it from our website.

[Invite the bot now](https://discord.com/oauth2/authorize?client_id=786464598835986483&scope=bot&permissions=67488832)


#### How to install, self-hosting.

Steps:
1. Create a Discord bot application on https://discordapp.com/developers.
2. Install MongoDB on either your Windows or Ubuntu server: [Install MongoDB](https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/)
3. Head to your system environments and add the following:
```
amelia_token="TOKEN CAN BE FOUND ON HTTPS://DISCORDAPP.COM/DEVELOPERS"
amelia_db="CONNECTION STRING, FOR MORE INFO LOOK AT THE LINKS BELOW"
```

**For more details about connection strings, please head to the official MongoDB documentation site.**
* [Connection String](https://docs.mongodb.com/manual/reference/connection-string/)

**For information about setting environmental variables on Ubuntu:**
* [Environment Variables (1)](https://mkyong.com/linux/how-to-set-environment-variable-in-ubuntu)
* [Environment Variables (2)](https://help.ubuntu.com/community/EnvironmentVariables)

**For information about setting environmental variables on Windows:**
* [Environment Variables (1)](https://docs.oracle.com/en/database/oracle/r-enterprise/1.5.1/oread/creating-and-modifying-environment-variables-on-windows.html)
* [Environment Variables (2)](https://www.computerhope.com/issues/ch000549.htm)

4. Install OpenJDK 11 (Required), to check whether you have OpenJDK 11, please read below.

After all of that is done, make sure you have at least JDK 11 by opening your terminal and using `java --version`, it should return something amongst the likes of this.

```
openjdk 11.0.9.1 2020-11-04
OpenJDK Runtime Environment (build 11.0.9.1+1-Ubuntu-0ubuntu1.18.04)
OpenJDK 64-Bit Server VM (build 11.0.9.1+1-Ubuntu-0ubuntu1.18.04, mixed mode, sharing)
```

5. Download the `Amelia.jar` file from the releases page: [Patch v1.1.1r1](https://github.com/ManaNet/Amelia/releases/tag/v1.1.1r1)
6. Move your console to where the jar file is located through `cd [location]` then use the command `java -jar Amelia.jar`.
