# Adding to the PATH (General Topic) and Setting JAVA\_HOME

For most of the installations below, a directory will generally need to be added to the PATH environment variable. In order to do this, go to Control Panel --> System --> Advanced System Settings. Then click on the button labeled "Environment Variables..." and a new window will appear. In the System variables section, find "Path" (which should already exist) and click "Edit...". Then add a semicolon at the end of the current path and add the desired absolute directory path.

If JAVA\_HOME is not set in your System variables, you will also want to create this. Click "New..." to create a new system variable and name it "JAVA\_HOME." It's value should be the jdk directory, which can also be identified as the directory for Java files containing the bin directory. 

# Maven

1. Go to [http://maven.apache.org/download.html][].
[http://maven.apache.org/download.html]: http://maven.apache.org/download.html
2. Choose the binary zip option for Maven 3.0.4 in the "Mirrors" column and follow the instructions for installation. 
3. Add the Maven bin directory (which contains mvn.bat) to your Path.
4. To test that installation is complete, if you run the following from a command prompt, you should get version information in response:

    `$ mvn --version`

# Heroku

1. Go to [https://toolbelt.heroku.com/][] to install the Heroku Toolbelt for Windows. This installation should include the Heroku client, Foreman (see the Python instruction section for usage), and Git. 
[https://toolbelt.heroku.com/]: https://toolbelt.heroku.com/
2. Follow the instructions for installation.
3. To test that the installation is complete, you can run the following three commands, ensuring that each of the three pieces of the installation was successful:

    `$ heroku --version`  
    `$ git --version`  
    `$ foreman --version` 

4. You will also need a heroku account, which you can obtain from [https://devcenter.heroku.com/articles/quickstart][].
[https://devcenter.heroku.com/articles/quickstart]: https://devcenter.heroku.com/articles/quickstart
5. Read through the entire page from Step 4 to discover how to create an SSH public key, which is essential to using git. 

# Git
*Note: Git should be installed as part of the Heroku installation (see above)*

**To obtain the ivr-original code from Github:**

1. Open a command prompt and change directories to the location in which you would like to store the code.
2. Run the following command. In response, all files from the repository should be put on your system and you should have the ability to commit and push to the repository as well as pull from it:

    `$ git clone git@github.com:womensp2p/ivr-original.git`

# Python

1. Go to [http://www.python.org/getit/][].
[http://www.python.org/getit/]: http://www.python.org/getit/ 
2. Under the "Download Python" section, click on the Python 2.7.3 Windows version that corresponds to your machine (32 or 64 bit). This will most likely download to C:\Python27. Follow the installation instructions. 
3. Add the outermost python directory (i.e. C:\Python27) to your Path.
4. To test that installation is complete, if you run the following from a command prompt, you should get version information in response: 

     `$ python -- version`

## virtualenv and pip 

1. Open a command prompt and change directories into the outermost python directory. Run the following two commands:

    `$ curl -O https://raw.github.com/pypa/virtualenv/master/virtualenv.py`  
    `$ python virtualenv.py my_new_env`

2. To start the virtual environment, from that same directory, run:

    `$ cd my_new_env`  
    `$ cd Scripts`  
    `$ activate`

**To start the Twilio HTML services:**

1. Change directories into the location where the ivr-original files are stored.
2. Run the command: `foreman start`
3. If foreman start, does not work...If the command does work, skip to step 4.   
**IMPORTANT: Need to add some information about what to run if "foreman start" does not work!**
4. The response in the prompt should give you a process ID (pid) and tell you  which port the service is running on. For example, if the output says the service is running on http://0.0.0.0:5000, then navigate in a web browser to http://127.0.0.1:5000. You should see a Twilio webpage. 