# Bill Management System 
This was a school project. The project goal was to build a system for customers to pay their bills at one place.
## Tools Used
* Languages used - Java for the GUI and SQL for the database
* Eclipse IDE
* Microsoft SQL Server
## Requirments
* Eclipse IDE
* JDK /*I used jdk 1.8*/
* MS SQL Server /*I used 18 */
## Usage
* Clone the repository
    `git clone https://github.com/samicsc0/BillManagmentSystem.git`
* Import it to the Eclipse IDE
* Open MS SQL
* Import the sql.sql file from the repo
* Create a database by executing the first line
    `create database BMS`
* Execute each statement by order
* Connect to to the database 'BMS' and create an admin account by executing 
    `exec insert_usr @nameh = 'admin',@uty = 2, @passh = 'admin'`
* Run the program
