# Inventory (academic year 2016/17)

## Team Members SI-11 

* Parić Muhamed
* Pejčinović Dario
* Popović Ahmed
* Puce Amina
* Ramić Benjamin
* Sakić Ajla

## Technologies
* Spring Boot 
* Hibernate
* MySQL
* Angular 1.6 (ES6 w/ Babel)
* Webpack 
* SASS


# Setting up your development environment

## STEP 0: INSTALL JDK, GIT & NODE.JS

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

http://git-scm.com/downloads

https://nodejs.org/dist/v6.10.2/node-v6.10.2-x64.msi

## STEP 1: INSTALL INTELLIJ IDEA ULTIMATE FOR STUDENTS (FREE)

https://www.jetbrains.com/shop/eform/students

Apply here with your @etf.unsa.ba mail, you should get the license instantly. After registration and logging in, go here:

https://account.jetbrains.com/licenses

And click Download below “JetBrains Product Pack for Students “. Download IntelliJ Idea Ultimate and install.

## STEP 2: INSTALL MYSQL

Preferred way at the moment is to install XAMPP which comes with Apache so we can use phpMyAdmin.

https://netcologne.dl.sourceforge.net/project/xampp/XAMPP%20Windows/7.0.8/xampp-win32-7.0.8-0-VC14-installer.exe

Install XAMPP. 

## STEP 3: CREATE A DATABASE

Run XAMPP Control Panel. Click Start besides Apache and besides  MySQL. Go to

http://localhost/phpmyadmin

Click New below the phpMyAdmin logo.

_Database name_: tim11
Instead of Collation, choose utf8_unicode_ci, so we support our letters like čćšđž…

Go to tim11 database and choose Privileges tab.  Click Add user account.

_User name_: EtfSI2016  
_Password_: 2016SIEtf  
_Re-type_: 2016SIEtf  
_Hostname_: Local / and type localhost

Click _**Go**_.

## STEP 4: CREATE A FOLDER CALLED ‘inventura’ WHEREVER YOU WANT

Go to that folder in cmd. Type the commands:

    cd C:\inventura  
    git clone https://github.com/SoftverInzenjeringETFSA/SI2016_TIM11.git    
    cd SI2016_TIM11   
    cd frontend  
    npm install

Last command should take a while.

## STEP 5: IMPORT THE PROJECT IN INTELLIJ

Open IntelliJ. Import Project -> (select SI2016_TIM11 folder) -> OK -> Maven -> Next -> Next -> Next -> Next -> Finished

Right click on frontend -> Mark directory as -> Excluded (otherwise IntelliJ would index thousands of files from node_modules)

## STEP 6: FINISH

That’s it for the backend! Try running the app in IntelliJ (upper right corner, play button). Then go to http://localhost:8080/accounts/all (it should only return [ ] since the database is empty)

Now try the frontend:

Open cmd and go to SI2016_TIM11/frontend. Type in gulp serve and wait for something like this to appear:

![End result](http://pokit.org/get/img/eb3f930844a2e5fb740ba50de9150f90.jpg)

**Then in your browser go to http://localhost:4200. This server should automatically refresh the page incase you change .js files in the frontend (also it should hot-swap new SASS/CSS files).**

# For any additional questions please contact persons above.